package Glufy.Pulse.mixin.meteor;

import Glufy.Pulse.utils.ConfigModifier;
import Glufy.Pulse.utils.RejectsConfig;
import meteordevelopment.meteorclient.gui.tabs.builtin.ConfigTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ConfigTab.class)
public class ConfigTabMixin {
	// No idea why CallbackInfoReturnable, but fabric crashes otherwise lol
	@Inject(method = "createScreen", at=@At("HEAD"), remap = false)
	private void onCreateScreen(CallbackInfoReturnable<?> cir) {
		ConfigModifier.get().hiddenModules.set(RejectsConfig.get().getHiddenModules());
	}
}