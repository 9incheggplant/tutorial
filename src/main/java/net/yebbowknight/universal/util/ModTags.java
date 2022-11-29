package net.yebbowknight.universal.util;

import net.yebbowknight.universal.Crossovermod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> DOWSING_ROD_VALUABLES =
                tag("dowsing_rod_valuables");
        public static final TagKey<Block> PAXEL_MINEABLE =
                tag("mineable/paxel");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Crossovermod.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }


    public static class Items {
        public static final TagKey<Item> COBALT_INGOTS = forgeTag("ingots/cobalt");
        public static final TagKey<Item> COBALT_NUGGETS = forgeTag("nuggets/cobalt");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Crossovermod.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}