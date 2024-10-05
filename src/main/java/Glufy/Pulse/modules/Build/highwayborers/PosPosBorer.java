package Glufy.Pulse.modules.Build.highwayborers;

import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class PosPosBorer extends BorerModule {
    public PosPosBorer() {
        super("PosPos-borer", "Digs +X +Z highway.", 1, 2, 2, 2);
    }

    @Override
    @EventHandler
    public void onTick(TickEvent.Pre event) {
        if (mc.player == null || mc.world == null) return;

        // Previous floored block position of player
        BlockPos prevBlockPos = playerPos;

        playerPos = new BlockPos(
            MathHelper.floor(mc.player.getX()),
            keepY.get() != -1 ? keepY.get() : MathHelper.floor(mc.player.getY()),
            MathHelper.floor(mc.player.getZ())
        );

        if (playerPos != prevBlockPos || Util.getMeasuringTimeMs() - lastUpdateTime > 800) {
            switch (mode.get()) {
                case THIN -> {
                    do2x3(playerPos.add(xOffset.get(), 0, zOffset.get()));
                    do2x3(playerPos.add(xOffset.get() * -3, 0, zOffset.get() * -3));
                }
                case HIGHWAY -> {
                    doHighway4(playerPos.add(xOffset.get(), 0, zOffset.get()));
                    doHighway4(playerPos.add(xOffset.get() * -3, 0, zOffset.get() * -3));
                }
            }
            lastUpdateTime = Util.getMeasuringTimeMs();
        }
        packets = 0;
    }
}
