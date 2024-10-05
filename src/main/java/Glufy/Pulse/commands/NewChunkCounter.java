package Glufy.Pulse.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import meteordevelopment.meteorclient.commands.Command;
import meteordevelopment.meteorclient.utils.player.ChatUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import Glufy.Pulse.modules.Render.NewerNewChunks;

public class NewChunkCounter extends Command {
    public NewChunkCounter() {
        super("newchunkcount", "Counts how many chunks have been saved with NewerNewChunks.");
    }
    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        NewerNewChunks n=new NewerNewChunks();
        builder.then(literal("NewChunks").executes(ctx -> {
            n.chunkcounterticks=0;
            NewerNewChunks.chunkcounter =true;
            int chunks = NewerNewChunks.newchunksfound;
            ChatUtils.sendMsg(Text.of(chunks+"  NewChunk locations have been saved by NewerNewChunks in this dimension."));
            return SINGLE_SUCCESS;
        }));
        builder.then(literal("OldChunks").executes(ctx -> {
            n.chunkcounterticks=0;
            NewerNewChunks.chunkcounter =true;
            int chunks = NewerNewChunks.oldchunksfound;
            ChatUtils.sendMsg(Text.of(chunks+"  OldChunk locations have been saved by NewerNewChunks in this dimension."));
            return SINGLE_SUCCESS;
        }));
        builder.then(literal("BeingUpdatedChunks").executes(ctx -> {
            n.chunkcounterticks=0;
            NewerNewChunks.chunkcounter =true;
            int chunks = NewerNewChunks.beingUpdatedOldChunksfound;
            ChatUtils.sendMsg(Text.of(chunks+"  BeingUpdatedChunks locations have been saved by NewerNewChunks in this dimension."));
            return SINGLE_SUCCESS;
        }));
        builder.then(literal("OldGenerationChunks").executes(ctx -> {
            n.chunkcounterticks=0;
            NewerNewChunks.chunkcounter =true;
            int chunks = NewerNewChunks.OldGenerationOldChunksfound;
            ChatUtils.sendMsg(Text.of(chunks+"  OldGenerationChunks locations have been saved by NewerNewChunks in this dimension."));
            return SINGLE_SUCCESS;
        }));
        builder.then(literal("BlockExploitChunks").executes(ctx -> {
            n.chunkcounterticks=0;
            NewerNewChunks.chunkcounter =true;
            int chunks = NewerNewChunks.tickexploitchunksfound;
            ChatUtils.sendMsg(Text.of(chunks+"  BlockExploitChunk locations have been saved by NewerNewChunks in this dimension."));
            return SINGLE_SUCCESS;
        }));
        builder.executes(ctx -> {
            n.chunkcounterticks=0;
            NewerNewChunks.chunkcounter =true;
            int chunks1 = NewerNewChunks.newchunksfound;
            int chunks3 = NewerNewChunks.tickexploitchunksfound;
            int chunks2 = NewerNewChunks.oldchunksfound;
            int chunks4 = NewerNewChunks.beingUpdatedOldChunksfound;
            int chunks5 = NewerNewChunks.OldGenerationOldChunksfound;
            ChatUtils.sendMsg(Text.of("New: "+chunks1+" | BlockExploitChunk: "+chunks3+" | Old: "+chunks2+" | BeingUpdatedChunks: "+chunks4+" |  OldGenerationChunks: "+chunks5+" | Chunk locations have been saved by NewerNewChunks in this dimension."));
            return SINGLE_SUCCESS;
        });
    }
}