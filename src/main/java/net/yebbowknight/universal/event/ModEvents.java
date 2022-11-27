 package net.yebbowknight.universal.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenOpenEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.yebbowknight.universal.Crossovermod;
import net.yebbowknight.universal.command.ReturnHomeCommand;
import net.yebbowknight.universal.command.SetHomeCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import net.yebbowknight.universal.config.CrossoverClientConfigs;
import net.yebbowknight.universal.item.ModItems;
import net.yebbowknight.universal.util.KaupenTitleScreen;

import java.util.List;

 @Mod.EventBusSubscriber(modid = Crossovermod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new SetHomeCommand(event.getDispatcher());
        new ReturnHomeCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
        if(!event.getOriginal().getLevel().isClientSide()) {
            event.getPlayer().getPersistentData().putIntArray(Crossovermod.MOD_ID + "homepos",
                    event.getOriginal().getPersistentData().getIntArray(Crossovermod.MOD_ID + "homepos"));
        }
    }
    @SubscribeEvent
    public static void setEntityOnFireWhenHit(LivingDamageEvent event) {
        if(!event.getEntity().level.isClientSide()) {
            if(event.getSource().getDirectEntity() instanceof Player) {
                Player player = (Player)event.getSource().getDirectEntity();
                if(player.getMainHandItem().getItem() == Items.NETHER_BRICK) {
                    player.getMainHandItem().shrink(1);
                    event.getEntityLiving().setSecondsOnFire(2);
                }
            }
        }
    }
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void openGui(ScreenOpenEvent event) {
        if (CrossoverClientConfigs.CUSTOM_TITLE_SCREEN.get() && event.getScreen() instanceof TitleScreen && !(event.getScreen() instanceof KaupenTitleScreen)) {
            event.setScreen(new KaupenTitleScreen());
        }
    }

     @SubscribeEvent
     public static void addCustomTrades(VillagerTradesEvent event) {
         if(event.getType() == VillagerProfession.FARMER) {
             Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
             ItemStack stack = new ItemStack(ModItems.TURNIP.get(), 12);
             int villagerLevel = 1;

             trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                     new ItemStack(Items.EMERALD, 2),
                     stack,10,3,0.02F));
         }

         if(event.getType() == VillagerProfession.TOOLSMITH) {
             Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
             ItemStack stack = new ItemStack(ModItems.COBALT_PAXEL.get(), 1);
             int villagerLevel = 3;

             trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                     new ItemStack(Items.EMERALD, 12),
                     stack,4,5,0.09F));
         }
     }
}
