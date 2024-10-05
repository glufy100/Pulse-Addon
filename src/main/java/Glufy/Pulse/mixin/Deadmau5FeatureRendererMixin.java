package Glufy.Pulse.mixin;

import Glufy.Pulse.modules.Render.Rendering;
import meteordevelopment.meteorclient.systems.modules.Modules;

import net.minecraft.client.render.entity.feature.Deadmau5FeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Deadmau5FeatureRenderer.class)
public class Deadmau5FeatureRendererMixin {
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z"))
    private boolean redirectAllow(String s, Object name){
        Rendering renderingModule = Modules.get().get(Rendering.class);
        if (renderingModule != null && renderingModule.deadmau5EarsEnabled()) return true;
        return name.equals(s);
    }
}
