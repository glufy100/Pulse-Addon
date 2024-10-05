package Glufy.Pulse.modules.Combat;

import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.player.ChatUtils;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import net.minecraft.network.packet.c2s.play.VehicleMoveC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class BoatKill extends Module {
    private final SettingGroup settingGroup = settings.getDefaultGroup();

    // all heights should work fine. and since we are in a boat. tp limit rises to 400 instead of 200.
    private final Setting<Integer> height = settingGroup.add(new IntSetting.Builder()
            .name("Height")
            .description("Height to use for boatKill")
            .defaultValue(111)
            .min(1)
            .sliderRange(1,200)
            .build()
    );

    public BoatKill() {
        super(Categories.Combat, "BoatKill", "Kill everyone in a boat using funny packets.");
    }


    @Override
    public void onActivate() {
        if (!(mc.player.getVehicle() instanceof BoatEntity boat)) {
            ChatUtils.sendMsg(Text.of("you must be on the boat."));
            toggle();
            return;
        }

        Vec3d oPos = boat.getPos();

        // cba calculating the actual packets for this. 15 should be more than enough
        for (int i = 0; i < 15; i++) {
            moveTo(oPos);
        }

        moveTo(oPos.add(0,height.get(),0));

        // floating point is what makes the boat break.
        moveTo(oPos.add(0,0.0001,0));

        mc.player.networkHandler.sendPacket(new PlayerInputC2SPacket(0,0,false,true));
        mc.player.dismountVehicle();
        toggle();
    }

    public void moveTo(Vec3d pos){
        mc.player.getVehicle().setPosition(pos);
        mc.player.networkHandler.sendPacket(new VehicleMoveC2SPacket(mc.player.getVehicle()));
    }
}