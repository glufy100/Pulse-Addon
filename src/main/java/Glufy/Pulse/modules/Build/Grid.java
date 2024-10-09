package Glufy.Pulse.modules.Build;

import Glufy.Pulse.PulseAddon;
import meteordevelopment.meteorclient.events.render.Render3DEvent;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.renderer.ShapeMode;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.player.FindItemResult;
import meteordevelopment.meteorclient.utils.player.InvUtils;
import meteordevelopment.meteorclient.utils.render.color.SettingColor;
import meteordevelopment.meteorclient.utils.world.BlockUtils;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class Grid extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();
    private final SettingGroup sgRange = settings.createGroup("Range");
    private final SettingGroup sgGrid = settings.createGroup("Grid");
    private final SettingGroup sgRender = settings.createGroup("Render");
    private final Setting<FilterMode> filterMode = sgGeneral.add(new EnumSetting.Builder<FilterMode>()
            .name("filter-mode")
            .description("Filter placements based on mode.")
            .defaultValue(FilterMode.StaggeredOdd)
            .build()
    );
    private final Setting<List<Block>> blocks = sgGeneral.add(new BlockListSetting.Builder()
            .name("blocks")
            .description("Blocks to use for placements.")
            .defaultValue(Blocks.TORCH, Blocks.WALL_TORCH)
            .build()
    );
    private final Setting<Boolean> place = sgGeneral.add(new BoolSetting.Builder()
            .name("place")
            .description("Place blocks.")
            .defaultValue(true)
            .build()
    );
    private final Setting<Boolean> rotate = sgGeneral.add(new BoolSetting.Builder()
            .name("rotate")
            .description("Rotate head when placing a block.")
            .defaultValue(true)
            .visible(place::get)
            .build()
    );
    private final Setting<Boolean> dynamicHeight = sgGeneral.add(new BoolSetting.Builder()
            .name("dynamic-height")
            .description("Attaches placements to the surface.")
            .defaultValue(true)
            .build()
    );
    private final Setting<Integer> gridSize = sgGrid.add(new IntSetting.Builder()
            .name("grid-size")
            .description("Grid size.")
            .defaultValue(2)
            .min(1)
            .build()
    );
    private final Setting<Integer> gridGap = sgGrid.add(new IntSetting.Builder()
            .name("grid-gap")
            .description("Gap between placements.")
            .defaultValue(13)
            .min(1)
            .build()
    );
    private final Setting<Integer> switchRange = sgRange.add(new IntSetting.Builder()
            .name("switch-range")
            .description("Range to switch to the nearest placement of grid.")
            .defaultValue(3)
            .min(1)
            .build()
    );
    private final Setting<Integer> placeRange = sgRange.add(new IntSetting.Builder()
            .name("place-range")
            .description("Range to place blocks.")
            .defaultValue(3)
            .min(1)
            .build()
    );
    private final Setting<Boolean> show = sgRender.add(new BoolSetting.Builder()
            .name("show")
            .description("Show placements.")
            .defaultValue(true)
            .build()
    );
    private final Setting<SettingColor> sC1 = sgRender.add(new ColorSetting.Builder()
            .name("main-placement-side-color")
            .description("The color of the sides of the blocks being rendered.")
            .defaultValue(new SettingColor(0, 255, 0, 40))
            .build()
    );
    private final Setting<SettingColor> lC1 = sgRender.add(new ColorSetting.Builder()
            .name("main-placement-line-color")
            .description("The color of the lines of the blocks being rendered.")
            .defaultValue(new SettingColor(0, 255, 0, 100))
            .build()
    );
    private final Setting<SettingColor> sC2 = sgRender.add(new ColorSetting.Builder()
            .name("placement-side-color")
            .description("The color of the sides of the blocks being rendered.")
            .defaultValue(new SettingColor(255, 255, 0, 40))
            .build()
    );
    private final Setting<SettingColor> lC2 = sgRender.add(new ColorSetting.Builder()
            .name("placement-line-color")
            .description("The color of the lines of the blocks being rendered.")
            .defaultValue(new SettingColor(255, 255, 0, 100))
            .build()
    );
    BlockPos centerPos = null;
    List<BlockPos> placements = new ArrayList<>();

    public Grid() {
        super(PulseAddon.Build, "grid", "Allows you to place blocks on a grid.");
    }

    @Override
    public void onDeactivate() {
        centerPos = null;
        placements.clear();
    }

    private BlockPos findNearest() {
        Iterable<BlockPos> blockPosIterable = BlockPos.iterateOutwards(mc.player.getBlockPos(), switchRange.get(), switchRange.get(), switchRange.get());
        for (BlockPos blockPos : blockPosIterable) {
            if (blocks.get().contains(mc.world.getBlockState(blockPos).getBlock())) return blockPos;
        }
        return null;
    }

    private BlockPos getHeight(BlockPos blockPos) {
        while (!mc.world.getBlockState(blockPos).isReplaceable() && blockPos.getY() < mc.world.getTopY()) {
            blockPos = blockPos.up(1);
        }
        while (mc.world.getBlockState(blockPos.down(1)).isReplaceable() && blockPos.getY() > mc.world.getBottomY()) {
            blockPos = blockPos.down(1);
        }
        return blockPos;
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        BlockPos nearPos = findNearest();
        if (nearPos != null) centerPos = nearPos;
        if (centerPos == null) return;

        placements.clear();

        for (int x = -gridSize.get(); x <= gridSize.get(); x++) {
            for (int z = -gridSize.get(); z <= gridSize.get(); z++) {
                if (x == 0 && z == 0) continue;
                if (filterMode.get() == FilterMode.StaggeredEven && (x & 1) == (z & 1)) continue;
                if (filterMode.get() == FilterMode.StaggeredOdd && (x & 1) != (z & 1)) continue;

                BlockPos finalBlockPos = centerPos.add(x * gridGap.get(), 0, z * gridGap.get());
                if (dynamicHeight.get()) finalBlockPos = getHeight(finalBlockPos);
                placements.add(finalBlockPos);
            }
        }

        for (BlockPos finalBlockPos : placements) {
            if (place.get() && mc.world.getBlockState(finalBlockPos).isReplaceable() && finalBlockPos.isWithinDistance(mc.player.getBlockPos(), placeRange.get())) {
                for (Block block : blocks.get()) {
                    FindItemResult item = InvUtils.findInHotbar(block.asItem());
                    BlockUtils.place(finalBlockPos, item, rotate.get(), 0);
                }
            }
        }
    }

    @EventHandler
    private void onRender(Render3DEvent event) {
        if (centerPos != null && show.get()) {
            event.renderer.box(centerPos, sC1.get(), lC1.get(), ShapeMode.Both, 0);
            for (BlockPos placement : placements) {
                event.renderer.box(placement, sC2.get(), lC2.get(), ShapeMode.Both, 0);
            }
        }
    }

    public enum FilterMode {
        None,
        StaggeredEven,
        StaggeredOdd
    }
}