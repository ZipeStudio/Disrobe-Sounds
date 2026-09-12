package me.zipestudio.disrobesounds.mixin;

import net.minecraft.world.inventory.AbstractContainerMenu;
import org.spongepowered.asm.mixin.Mixin;

//? if >=1.21.5 {
import me.zipestudio.disrobesounds.utils.ArmorSoundUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if >=26.1 {
import net.minecraft.world.inventory.ContainerInput;
//?} else {
/*import net.minecraft.world.inventory.ClickType;
*///?}

//? if >=1.21.11 {
import net.minecraft.world.inventory.AbstractMountInventoryMenu;
//?} else {
/*import net.minecraft.world.inventory.HorseInventoryMenu;
*///?}
//?}

@Mixin(AbstractContainerMenu.class)
public abstract class AbstractContainerMenuMixin {

    //? if >=1.21.5 {
    @Unique
    private static final int MOUNT_GEAR_SLOTS = 2;

    @Shadow
    @Final
    public NonNullList<Slot> slots;

    @Unique
    private ItemStack[] trackedMountGear;

    //? if >=26.1 {
    @Inject(method = "doClick", at = @At("HEAD"))
    private void beforeClick(int slotIndex, int buttonNum, ContainerInput containerInput, Player player, CallbackInfo ci) {
    //?} else {
    /*@Inject(method = "doClick", at = @At("HEAD"))
    private void beforeClick(int slotIndex, int button, ClickType actionType, Player player, CallbackInfo ci) {
    *///?}

        trackedMountGear = null;

        if (player == null || !player.level().isClientSide()) return;
        if (!isMountMenu()) return;

        trackedMountGear = new ItemStack[MOUNT_GEAR_SLOTS];
        for (int i = 0; i < MOUNT_GEAR_SLOTS && i < slots.size(); i++) {
            trackedMountGear[i] = slots.get(i).getItem().copy();
        }
    }

    //? if >=26.1 {
    @Inject(method = "doClick", at = @At("RETURN"))
    private void afterClick(int slotIndex, int buttonNum, ContainerInput containerInput, Player player, CallbackInfo ci) {
    //?} else {
    /*@Inject(method = "doClick", at = @At("RETURN"))
    private void afterClick(int slotIndex, int button, ClickType actionType, Player player, CallbackInfo ci) {
    *///?}

        ItemStack[] tracked = trackedMountGear;
        trackedMountGear = null;

        if (tracked == null || player == null) return;

        for (int i = 0; i < tracked.length; i++) {

            ItemStack before = tracked[i];
            if (before == null || before.isEmpty()) continue;
            if (i >= slots.size() || !slots.get(i).getItem().isEmpty()) continue;
            if (ArmorSoundUtils.isWornOut(before)) continue;

            SoundEvent sound = ArmorSoundUtils.getSoundFromStack(before);
            if (sound == null) continue;

            LivingEntity mount = mount();
            if (mount == null) continue;

            ArmorSoundUtils.playAtEntity(mount, sound);
            break;
        }
    }

    @Unique
    private LivingEntity mount() {

        //? if >=1.21.11 {
        LivingEntity mount = ((AbstractMountInventoryMenu) (Object) this).mount;
        //?} else {
        /*LivingEntity mount = ((HorseInventoryMenu) (Object) this).horse;
        *///?}

        return mount != null && mount.isAlive() ? mount : null;
    }

    @Unique
    private boolean isMountMenu() {
        //? if >=1.21.11 {
        return ((Object) this) instanceof AbstractMountInventoryMenu;
        //?} else {
        /*return ((Object) this) instanceof HorseInventoryMenu;
        *///?}
    }
    //?}

}
