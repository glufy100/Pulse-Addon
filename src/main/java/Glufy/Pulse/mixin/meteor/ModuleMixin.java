package Glufy.Pulse.mixin.meteor;

import Glufy.Pulse.utils.PulseConfig;
import Glufy.Pulse.utils.PulseUtils;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Module.class, remap = false)
public class ModuleMixin {
    @Mutable @Shadow public String name;

    @Mutable @Shadow public String title;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(Category category, String name, String description, CallbackInfo info) {
        if (PulseConfig.get().duplicateModuleNames) {
            this.name = PulseUtils.getModuleName(name);
            this.title = Utils.nameToTitle(this.name);
        }
    }
}
