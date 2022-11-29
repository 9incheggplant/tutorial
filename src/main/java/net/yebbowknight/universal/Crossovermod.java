package net.yebbowknight.universal;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yebbowknight.universal.block.ModBlocks;
import net.yebbowknight.universal.block.ModWoodTypes;
import net.yebbowknight.universal.block.entity.ModBlockEntities;
import net.yebbowknight.universal.config.CrossoverClientConfigs;
import net.yebbowknight.universal.config.CrossoverCommonConfigs;
import net.yebbowknight.universal.effect.ModEffects;
import net.yebbowknight.universal.enchantment.ModEnchantments;
import net.yebbowknight.universal.entity.ModEntityTypes;
import net.yebbowknight.universal.entity.client.ModBoatRenderer;
import net.yebbowknight.universal.entity.client.RaccoonRenderer;
import net.yebbowknight.universal.entity.client.TigerRenderer;
import net.yebbowknight.universal.fluid.ModFluids;
import net.yebbowknight.universal.item.ModItems;
import net.yebbowknight.universal.painting.ModPaintings;
import net.yebbowknight.universal.potion.ModPotions;
import net.yebbowknight.universal.recipe.ModRecipes;
import net.yebbowknight.universal.screen.CobaltBlasterScreen;
import net.yebbowknight.universal.screen.ModMenuTypes;
import net.yebbowknight.universal.sound.ModSounds;
import net.yebbowknight.universal.util.BetterBrewingRecipe;
import net.yebbowknight.universal.util.ModItemProperties;
import net.yebbowknight.universal.world.structure.ModStructures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Crossovermod.MOD_ID)
public class Crossovermod
{
    public static final String MOD_ID = "crossover";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    // very important Comment
    public Crossovermod() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        ModEnchantments.register(eventBus);
        ModSounds.register(eventBus);
        ModPaintings.register(eventBus);
        ModFluids.register(eventBus);

        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);

        ModRecipes.register(eventBus);
        ModEffects.register(eventBus);

        ModPotions.register(eventBus);
        ModEntityTypes.register(eventBus);
        ModStructures.register(eventBus);

        GeckoLib.initialize();

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CrossoverClientConfigs.SPEC, "crossover-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CrossoverCommonConfigs.SPEC, "crossover-common.toml");

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHERRY_BLOSSOM_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHERRY_BLOSSOM_TRAPDOOR.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TURNIP_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINK_ROSE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_PINK_ROSE.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.COBALT_BLASTER.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModFluids.HONEY_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.HONEY_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.HONEY_FLOWING.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHERRY_BLOSSOM_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHERRY_BLOSSOM_SAPLING.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WINTER_WINDOW.get(), RenderType.translucent());


        ModItemProperties.addCustomItemProperties();

        MenuScreens.register(ModMenuTypes.COBALT_BLASTER_MENU.get(), CobaltBlasterScreen::new);

        WoodType.register(ModWoodTypes.CHERRY_BLOSSOM);

        EntityRenderers.register(ModEntityTypes.RACCOON.get(), RaccoonRenderer::new);
        EntityRenderers.register(ModEntityTypes.TIGER.get(), TigerRenderer::new);

        EntityRenderers.register(ModEntityTypes.BOAT_ENTITY.get(), ModBoatRenderer::new);
}
    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ModItems.TURNIP_SEEDS.get(),0.35f);
            ComposterBlock.COMPOSTABLES.put(ModItems.TURNIP.get(),5f);

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.PINK_ROSE.getId(), ModBlocks.POTTED_PINK_ROSE);

            BlockEntityRenderers.register(ModBlockEntities.SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);
            Sheets.addWoodType(ModWoodTypes.CHERRY_BLOSSOM);

            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD,
                    ModItems.COBALT_INGOT.get(), ModPotions.FREEZE_POTION.get()));

        });
    }
}
