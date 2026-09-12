package me.zipestudio.disrobesounds.utils;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

//? if >=1.21.2 {
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.equipment.Equippable;
//?} else {
/*import net.minecraft.world.item.Equipable;
*///?}

public class ArmorSoundUtils {

    public static SoundEvent getSoundFromStack(ItemStack stack) {

        //? if >=1.21.2 {
        Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
        if (equippable != null) {
            return getCorrectSound(equippable.equipSound());
        }
        //?} else {
        /*Equipable equip = Equipable.get(stack);
        if (equip != null) {
            return getCorrectSound(equip.getEquipSound());
        }
        *///?}

        if (stack.getItem() == Items.SADDLE) {
            return getCorrectSound(SoundEvents.HORSE_SADDLE);
        }

        //? if <1.21 {

        /*//? if >=1.20.5 {
        if (stack.getItem() instanceof net.minecraft.world.item.AnimalArmorItem) {
            return getCorrectSound(SoundEvents.HORSE_ARMOR);
        }
        //?} else {
        /^if (stack.getItem() instanceof net.minecraft.world.item.HorseArmorItem) {
            return getCorrectSound(SoundEvents.HORSE_ARMOR);
        }
        ^///?}

        if (stack.is(ItemTags.WOOL_CARPETS)) {
            return getCorrectSound(SoundEvents.LLAMA_SWAG);
        }

        *///?}


        return null;
    }

    public static void playAtEntity(Entity entity, SoundEvent sound) {
        entity.level().playLocalSound(entity.getX(), entity.getY(), entity.getZ(), sound, entity.getSoundSource(), 1.0F, 1.0F, false);
    }

    public static boolean isEquippableTo(ItemStack stack, EquipmentSlot slot) {

        //? if >=1.21.2 {
        Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
        return equippable != null && equippable.slot() == slot;
        //?} else {
        /*Equipable equip = Equipable.get(stack);
        return equip != null && equip.getEquipmentSlot() == slot;
        *///?}

    }

    public static boolean isWornOut(ItemStack stack) {
        return stack.isDamageableItem() && stack.getDamageValue() >= stack.getMaxDamage() - 1;
    }

    private static SoundEvent getCorrectSound(Holder<SoundEvent> holder) {
        return holder.value();
    }

    private static SoundEvent getCorrectSound(SoundEvent soundEvent) {
        return soundEvent;
    }

}
