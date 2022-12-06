package net.yebbowknight.universal.event;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yebbowknight.universal.Crossovermod;
import net.yebbowknight.universal.entity.ModEntityTypes;
import net.yebbowknight.universal.entity.client.armor.CobaltArmorRenderer;
import net.yebbowknight.universal.entity.custom.RaccoonEntity;
import net.yebbowknight.universal.entity.custom.TigerEntity;
import net.yebbowknight.universal.item.custom.CobaltArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = Crossovermod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
 //   @SubscribeEvent
 //   public static void registerModifierSerializers(@Nonnull final RegisterEvent event) {
 //       event.register(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, helper -> {
 //           helper.register(new ResourceLocation(Crossovermod.MOD_ID,"turnip_seeds_from_grass"),
 //                   new TurnipSeedsFromGrassAdditionModifier.Serializer());
 //           helper.register(new ResourceLocation(Crossovermod.MOD_ID,"dowsing_rod_in_igloo"),
 //                   new DowsingRodInIglooAdditionModifier.Serializer());
  //      });

 //     event.register(ForgeRegistries.Keys.RECIPE_TYPES, recipeTypeRegisterHelper -> {
 //         recipeTypeRegisterHelper.register(new ResourceLocation(Crossovermod.MOD_ID, CobaltBlasterRecipe.Type.ID),
 //                 CobaltBlasterRecipe.Type.INSTANCE);
 //     });
 // }
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.RACCOON.get(), RaccoonEntity.setAttributes());
        event.put(ModEntityTypes.TIGER.get(), TigerEntity.setAttributes());
    }

    @SubscribeEvent
    public static void registerRecipeTypes(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(CobaltArmorItem.class, new CobaltArmorRenderer());
    }
}