package net.yebbowknight.universal.event;

import net.yebbowknight.universal.Crossovermod;
import net.yebbowknight.universal.entity.ModEntityTypes;
import net.yebbowknight.universal.entity.custom.RaccoonEntity;
import net.yebbowknight.universal.entity.custom.TigerEntity;
import net.yebbowknight.universal.event.loot.DowsingRodInIglooAdditionModifier;
import net.yebbowknight.universal.event.loot.TurnipSeedsFromGrassAdditionModifier;
import net.yebbowknight.universal.recipe.CobaltBlasterRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = Crossovermod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new TurnipSeedsFromGrassAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(Crossovermod.MOD_ID,"turnip_seeds_from_grass")),
                new DowsingRodInIglooAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(Crossovermod.MOD_ID,"dowsing_rod_in_igloo"))
        );
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.RACCOON.get(), RaccoonEntity.setAttributes());
        event.put(ModEntityTypes.TIGER.get(), TigerEntity.setAttributes());
    }

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, CobaltBlasterRecipe.Type.ID, CobaltBlasterRecipe.Type.INSTANCE);
    }
}