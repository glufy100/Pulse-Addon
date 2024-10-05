package Glufy.Pulse.mixin.meteor;

import meteordevelopment.meteorclient.gui.renderer.GuiRenderer;
import meteordevelopment.meteorclient.renderer.Renderer2D;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GuiRenderer.class)
public interface GuiRendererAccessor {
    @Accessor(value = "r", remap = false)
    Renderer2D getRenderer2D();
}
