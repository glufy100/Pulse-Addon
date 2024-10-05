package Glufy.Pulse.mixin.meteor.modules;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.util.math.Vec3d;

import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.movement.Flight;

import static meteordevelopment.meteorclient.MeteorClient.mc;

@Mixin(Flight.class)
public class FlightMixin {
    @Shadow(remap = false)
    @Final
    private SettingGroup sgGeneral;

    private Setting<Boolean> stopMomentum = null;

    @Inject(method = "<init>", at=@At("TAIL"), remap = false)
    private void onInit(CallbackInfo ci) {
        stopMomentum = sgGeneral.add(new BoolSetting.Builder()
            .name("stop-momentum")
            .description("Stops momentum on flight disable")
            .defaultValue(false)
            .build()
        );
    }

    @Inject(method = "onDeactivate", at=@At("TAIL"), remap = false)
    private void onDeactivate(CallbackInfo ci) {
        if (mc.player == null || stopMomentum == null || !stopMomentum.get()) return;

        mc.options.forwardKey.setPressed(false);
        mc.options.leftKey.setPressed(false);
        mc.options.backKey.setPressed(false);
        mc.options.rightKey.setPressed(false);

        mc.player.setVelocity(Vec3d.ZERO);
    }
}
