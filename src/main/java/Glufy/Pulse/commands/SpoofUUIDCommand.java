package Glufy.Pulse.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import meteordevelopment.meteorclient.commands.Command;
import net.minecraft.command.CommandSource;

import java.util.UUID;

public class SpoofUUIDCommand extends Command {
    public SpoofUUIDCommand() {
        super("spoof-uuid", "Spoof your UUID.", "spoofuuid");
    }

    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.executes(context -> {
            UUID randomUUID = UUID.randomUUID();
            mc.player.setUuid(randomUUID);
            info("Spoofed UUID to: " + randomUUID);
            return SINGLE_SUCCESS;
        });
    }
}