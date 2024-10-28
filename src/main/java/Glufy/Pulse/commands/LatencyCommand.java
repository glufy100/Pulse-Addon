package Glufy.Pulse.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import meteordevelopment.meteorclient.commands.Command;
import meteordevelopment.meteorclient.utils.player.PlayerUtils;
import net.minecraft.command.CommandSource;

public class LatencyCommand extends Command {
    public LatencyCommand() {
        super("latency", "returns your ping");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.executes(context -> {
            info("Your latency is: " + PlayerUtils.getPing());
            return SINGLE_SUCCESS;
        });
    }
}