package Glufy.Pulse.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import meteordevelopment.meteorclient.commands.Command;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public class SpoofNameCommand extends Command {
    public SpoofNameCommand() {
        super("spoof-name", "Spoofs your minecraft username locally.", "spoofuser");
    }

    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.then(argument("username", StringArgumentType.greedyString()).executes(context -> {
            String username = context.getArgument("username", String.class);
            if (!username.isEmpty()) {
                mc.player.setCustomName(Text.of(username));
                info("Successfully spoofed your username to: " + username);
            } else {
                error("Incomplete command. Must be .spoofname {username}.");
            }
            return SINGLE_SUCCESS;
        }));
    }
}