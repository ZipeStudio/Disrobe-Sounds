package me.zipestudio.ds.utils;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

//? if >=1.21.2 {
/*import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.equipment.Equippable;
*///?} else {
import net.minecraft.world.item.Equipable;
//?}

public class ArmorSoundUtils {

    public static SoundEvent getSoundFromStack(ItemStack stack) {

        //? if >=1.21.2 {
        /*Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
        if (equippable != null) {
            return getCorrectSound(equippable.equipSound());
        }
        *///?} else {
        Equipable equip = Equipable.get(stack);
        if (equip != null) {
            return getCorrectSound(equip.getEquipSound());
        }
        //?}

        if (stack.getItem() == Items.SADDLE) {
            return getCorrectSound(SoundEvents.HORSE_SADDLE);
        }

        //? if <1.21 {

        //? if >=1.20.5 {
        /*if (stack.getItem() instanceof net.minecraft.world.item.AnimalArmorItem) {
            return getCorrectSound(SoundEvents.HORSE_ARMOR);
        }
        *///?} else {
        if (stack.getItem() instanceof net.minecraft.world.item.HorseArmorItem) {
            return getCorrectSound(SoundEvents.HORSE_ARMOR);
        }
        //?}

        if (stack.is(ItemTags.WOOL_CARPETS)) {
            return getCorrectSound(SoundEvents.LLAMA_SWAG);
        }

        //?}


        return null;
    }

    private static SoundEvent getCorrectSound(SoundEvent soundEvent) {
        return soundEvent;
    }

    private static SoundEvent getCorrectSound(Holder<SoundEvent> holder) {
        return holder.value();
    }


}
