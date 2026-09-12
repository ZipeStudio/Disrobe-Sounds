package me.zipestudio.disrobesounds.mixin;

import com.mojang.datafixers.util.Pair;
import me.zipestudio.disrobesounds.utils.ArmorSoundUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if <1.21.5 {
/*import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Saddleable;
import net.minecraft.world.item.Items;
*///?}

//? if <1.20.5 {
/*import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.item.DyeColor;
*///?}

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin {

    @Inject(method = "handleSetEquipment", at = @At("HEAD"))
    private void beforeSetEquipment(ClientboundSetEquipmentPacket packet, CallbackInfo ci) {

        Minecraft minecraft = Minecraft.getInstance();

        if (!minecraft.isSameThread()) return;

        ClientLevel level = minecraft.level;
        if (level == null) return;

        if (!(level.getEntity(packet.getEntity()) instanceof LivingEntity entity)) return;
        if (entity == minecraft.player) return;
        if (!entity.isAlive() || entity.isSilent()) return;

        for (Pair<EquipmentSlot, ItemStack> pair : packet.getSlots()) {

            EquipmentSlot slot = pair.getFirst();
            ItemStack before = entity.getItemBySlot(slot);
            ItemStack after = pair.getSecond();

            if (!ArmorSoundUtils.isEquippableTo(before, slot)) continue;
            if (ArmorSoundUtils.isEquippableTo(after, slot)) continue;
            if (ArmorSoundUtils.isWornOut(before)) continue;

            SoundEvent sound = ArmorSoundUtils.getSoundFromStack(before);
            if (sound == null) continue;

            ArmorSoundUtils.playAtEntity(entity, sound);
            break;
        }

    }

    //? if <1.21.5 {
    /*@Unique
    private Saddleable saddledBefore;

    //? if <1.20.5 {
    /^@Unique
    private Llama carpetedBefore;
    ^///?}

    @Inject(method = "handleSetEntityData", at = @At("HEAD"))
    private void beforeSetEntityData(ClientboundSetEntityDataPacket packet, CallbackInfo ci) {

        saddledBefore = null;
        //? if <1.20.5 {
        /^carpetedBefore = null;
        ^///?}

        Minecraft minecraft = Minecraft.getInstance();
        if (!minecraft.isSameThread()) return;

        ClientLevel level = minecraft.level;
        if (level == null) return;

        Entity entity = level.getEntity(packet.id());

        //? if <1.20.5 {
        /^if (entity instanceof Llama llama && llama.getSwag() != null) {
            carpetedBefore = llama;
        }
        ^///?}

        if (!(entity instanceof Saddleable saddleable)) return;
        if (!saddleable.isSaddled()) return;

        saddledBefore = saddleable;
    }

    @Inject(method = "handleSetEntityData", at = @At("TAIL"))
    private void afterSetEntityData(ClientboundSetEntityDataPacket packet, CallbackInfo ci) {

        //? if <1.20.5 {
        /^Llama llama = carpetedBefore;
        carpetedBefore = null;

        if (llama != null && llama.getSwag() == null && llama.isAlive() && !llama.isSilent()) {
            SoundEvent carpetSound = ArmorSoundUtils.getSoundFromStack(new ItemStack(Items.WHITE_CARPET));
            if (carpetSound != null) {
                ArmorSoundUtils.playAtEntity(llama, carpetSound);
            }
        }
        ^///?}

        Saddleable saddleable = saddledBefore;
        saddledBefore = null;

        if (saddleable == null) return;
        if (saddleable.isSaddled()) return;
        if (!(saddleable instanceof LivingEntity entity)) return;
        if (!entity.isAlive() || entity.isSilent()) return;

        SoundEvent sound = ArmorSoundUtils.getSoundFromStack(new ItemStack(Items.SADDLE));
        if (sound == null) return;

        ArmorSoundUtils.playAtEntity(entity, sound);
    }
    *///?}

}