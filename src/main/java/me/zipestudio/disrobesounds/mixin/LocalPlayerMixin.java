package me.zipestudio.disrobesounds.mixin;

import me.zipestudio.disrobesounds.utils.ArmorSoundUtils;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.EnumMap;
import java.util.Map;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {

    @Unique
    private static final EquipmentSlot[] TRACKED_SLOTS = {
            EquipmentSlot.HEAD,
            EquipmentSlot.CHEST,
            EquipmentSlot.LEGS,
            EquipmentSlot.FEET,
            EquipmentSlot.OFFHAND
    };

    @Unique
    private final Map<EquipmentSlot, ItemStack> trackedEquipment = new EnumMap<>(EquipmentSlot.class);

    @Unique
    private boolean equipmentTracked;

    @Inject(method = "tick", at = @At("HEAD"))
    private void trackEquipment(CallbackInfo ci) {

        LocalPlayer player = (LocalPlayer) (Object) this;

        boolean playSounds = equipmentTracked && player.isAlive();
        equipmentTracked = true;

        for (EquipmentSlot slot : TRACKED_SLOTS) {

            ItemStack before = trackedEquipment.getOrDefault(slot, ItemStack.EMPTY);
            ItemStack after = player.getItemBySlot(slot);
            trackedEquipment.put(slot, after.copy());

            if (!playSounds) continue;

            if (!ArmorSoundUtils.isEquippableTo(before, slot)) continue;
            if (ArmorSoundUtils.isEquippableTo(after, slot)) continue;
            if (ArmorSoundUtils.isWornOut(before)) continue;

            SoundEvent sound = ArmorSoundUtils.getSoundFromStack(before);
            if (sound == null) continue;

            ArmorSoundUtils.playAtEntity(player, sound);
            break;
        }

    }

}
