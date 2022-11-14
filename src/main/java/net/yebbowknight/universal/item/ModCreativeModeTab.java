package net.yebbowknight.universal.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab CROSS_TAB = new CreativeModeTab("crossovermodtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.FIRE_FLOWER.get());
        }
    };

}
