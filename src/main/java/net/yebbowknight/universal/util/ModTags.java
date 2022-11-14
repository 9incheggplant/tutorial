package net.yebbowknight.universal.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.yebbowknight.universal.Crossovermod;

public class ModTags {
    public static class Blocks {
        public static final Tags.IOptionalNamedTag<Block> DOWSING_ROD_VALUABLES =
                tag("dowsing_rod_valuables");
        public static final Tags.IOptionalNamedTag<Block> PAXEL_MINEABLE =
                tag("mineable/paxel");



        private static Tags.IOptionalNamedTag<Block> tag(String name) {
            return BlockTags.createOptional(new ResourceLocation(Crossovermod.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Block> forgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }

    }

    public static class Items {
        public static final Tags.IOptionalNamedTag<Item> COBALT_INGOTS = forgeTag("ingots/cobalt");
        public static final Tags.IOptionalNamedTag<Item> COBALT_NUGGETS = forgeTag("nuggets/cobalt");


        private static Tags.IOptionalNamedTag<Item> tag(String name) {
            return ItemTags.createOptional(new ResourceLocation(Crossovermod.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Item> forgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }
}
