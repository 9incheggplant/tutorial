package net.yebbowknight.universal.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.yebbowknight.universal.item.ModItems;
import net.yebbowknight.universal.sound.ModSounds;
import net.yebbowknight.universal.util.InventoryUtil;
import net.yebbowknight.universal.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DowsingRodItem extends Item {
    public DowsingRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++) {
                BlockState blockBelow = pContext.getLevel().getBlockState(positionClicked.below(i));

                if(isValuableBlock(blockBelow)) {
                    outputValuableCoordinates(positionClicked.below(i), player, blockBelow.getBlock());
                    foundBlock = true;

                    if(InventoryUtil.hasPlayerStackInInventory(player, ModItems.DATA_TABLET.get())) {
                        addNbtToDataTablet(player, positionClicked.below(i), blockBelow.getBlock());
                    }

                    pContext.getLevel().playSound(player, positionClicked, ModSounds.DOWSING_ROD_FOUND_ORE.get(),
                            SoundSource.BLOCKS, 1f, 1f);

                    break;
                }
            }

            if(!foundBlock) {
                player.sendSystemMessage(Component.translatable("item.mccourse.dowsing_rod.no_valuables"));
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return super.useOn(pContext);
    }

    private void addNbtToDataTablet(Player player, BlockPos pos, Block blockBelow) {
        ItemStack dataTablet =
                player.getInventory().getItem(InventoryUtil.getFirstInventoryIndex(player, ModItems.DATA_TABLET.get()));

        CompoundTag nbtData = new CompoundTag();
        nbtData.putString("mccourse.last_ore", "Found " + blockBelow.getName() + " at (" +
                pos.getX() + ", "+ pos.getY() + ", "+ pos.getZ() + ")");

        dataTablet.setTag(nbtData);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents,
                                TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.mccourse.dowsing_rod.tooltip.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.mccourse.dowsing_rod.tooltip"));
        }
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block blockBelow) {
        player.sendSystemMessage(Component.literal("Found " + blockBelow.getName() + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + "," + blockPos.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModTags.Blocks.DOWSING_ROD_VALUABLES);
    }
}