package me.zipestudio.ds.mixin;

import me.zipestudio.ds.DisrobeSoundsServer;
import me.zipestudio.ds.client.DisrobeSoundsClient;
import me.zipestudio.ds.utils.ArmorSoundUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//? if >=1.21.11 {
/*import net.minecraft.world.entity.animal.equine.AbstractHorse;
*///?} else {
import net.minecraft.world.entity.animal.horse.AbstractHorse;
//?}

//? if >=1.21.2 {
/*import net.minecraft.core.component.DataComponents;
*///?} else {
import net.minecraft.world.item.Equipable;
//?}

@Mixin(AbstractContainerMenu.class)
public abstract class AbstractContainerMenuMixin {

    @Shadow
    @Final
    public NonNullList<Slot> slots;

    @Unique
    private List<Integer> equipmentSlotIndices;

    @Unique
    private Map<Integer, ItemStack> trackedSlots;

    @Inject(method = "doClick", at = @At("HEAD"))
    private void beforeClick(int slotIndex, int button, ClickType actionType, Player player, CallbackInfo ci) {

        if (equipmentSlotIndices == null) {
            equipmentSlotIndices = new ArrayList<>();
            AbstractContainerMenu menu = (AbstractContainerMenu) (Object) this;
            for (int i = 0; i < slots.size(); i++) {
                if (isEquipmentSlot(menu, player, slots.get(i), i)) {
                    equipmentSlotIndices.add(i);
                }
            }
        }

        trackedSlots = new HashMap<>();
        for (int idx : equipmentSlotIndices) {
            ItemStack stack = slots.get(idx).getItem();
            if (!stack.isEmpty()) {
                trackedSlots.put(idx, stack.copy());
            }
        }

    }

    @Inject(method = "doClick", at = @At("RETURN"))
    private void afterClick(int slotIndex, int button, ClickType actionType, Player player, CallbackInfo ci) {

        if (player == null || trackedSlots == null) return;
        AbstractContainerMenu menu = (AbstractContainerMenu) (Object) this;

        for (Map.Entry<Integer, ItemStack> entry : trackedSlots.entrySet()) {

            int idx = entry.getKey();
            ItemStack before = entry.getValue();
            ItemStack after = slots.get(idx).getItem();

            if (after.isEmpty() && isEquipmentItem(before)) {

                SoundEvent sound = ArmorSoundUtils.getSoundFromStack(before);
                if (sound == null) continue;

                LivingEntity source = player;
                //? if >=1.21.11 {
                /*if (menu instanceof AbstractMountInventoryMenu horseMenu) {
                    LivingEntity horse = horseMenu.mount;
                    if (horse != null && horse.isAlive()) {
                        source = horse;
                    }
                }
                *///?} else {
                if (menu instanceof HorseInventoryMenu horseMenu) {
                    AbstractHorse horse = horseMenu.horse;
                    if (horse != null && horse.isAlive()) {
                        source = horse;
                    }
                }
                //?}

                source.playSound(sound);
            }
        }

        trackedSlots = null;
    }

    @Unique
    private boolean isEquipmentSlot(AbstractContainerMenu menu, Player player, Slot slot, int index) {

        //? if >=1.21 {
        /*if (slot instanceof ArmorSlot) {
            return true;
        }
        *///?}

        //? if >=1.21.11 {
        /*if (menu instanceof AbstractMountInventoryMenu && (slot.index == 0 || slot.index == 1)) {
            return true;
        }
        *///?} else {
        if (menu instanceof HorseInventoryMenu && (slot.index == 0 || slot.index == 1)) {
            return true;
        }
        //?}

        if (menu instanceof InventoryMenu && index >= 5 && index <= 8) {
            return true;
        }

        return false;
    }

    @Unique
    private boolean isEquipmentItem(ItemStack stack) {

        //? if >=1.21.2 {
        /*if (stack.has(DataComponents.EQUIPPABLE)) return true;
         *///?} else {
        if (Equipable.get(stack) != null) return true;
        //?}

        if (stack.getItem() == Items.SADDLE) return true;

        //? if <1.21 {

        if (stack.is(ItemTags.WOOL_CARPETS)) {
            return true;
        }

        //? if >=1.20.5 {
        /*if (stack.getItem() instanceof net.minecraft.world.item.AnimalArmorItem) {
            return true;
        }
        *///?} else {
        if (stack.getItem() instanceof net.minecraft.world.item.HorseArmorItem) {
            return true;
        }
        //?}
        //?}

        return false;
    }

}