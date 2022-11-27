package net.yebbowknight.universal.world;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yebbowknight.universal.Crossovermod;
import net.yebbowknight.universal.world.gen.ModEntityGeneration;
import net.yebbowknight.universal.world.gen.ModFlowerGeneration;
import net.yebbowknight.universal.world.gen.ModOreGeneration;
import net.yebbowknight.universal.world.gen.ModTreeGeneration;

@Mod.EventBusSubscriber(modid = Crossovermod.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModOreGeneration.generateOres(event);

        ModTreeGeneration.generateTrees(event);
        ModFlowerGeneration.generateFlowers(event);

        ModEntityGeneration.onEntitySpawn(event);

    }
}
