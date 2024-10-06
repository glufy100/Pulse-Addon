package Glufy.Pulse.utils;

import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.config.Config;
import meteordevelopment.meteorclient.systems.modules.Module;

import java.util.List;

public class ConfigModifier {

    private static ConfigModifier INSTANCE;

    public final SettingGroup sgPulse = Config.get().settings.createGroup("Pulse");

    public final Setting<PulseConfig.HttpAllowed> httpAllowed = sgPulse.add(new EnumSetting.Builder<PulseConfig.HttpAllowed>()
            .name("http-allowed")
            .description("Changes what api endpoints can be reached.")
            .defaultValue(PulseConfig.get().httpAllowed)
            .onChanged(v -> PulseConfig.get().httpAllowed = v)
            .build()
    );

    public final Setting<String> httpUserAgent = sgPulse.add(new StringSetting.Builder()
            .name("http-user-agent")
            .description("Changes the HTTP user agent. Empty for none.")
            .defaultValue(PulseConfig.get().httpUserAgent)
            .onChanged(v -> PulseConfig.get().httpUserAgent = v)
            .build()
    );

    public final Setting<List<Module>> hiddenModules = sgPulse.add(new ModuleListSetting.Builder()
            .name("hidden-modules")
            .description("Which modules to hide.")
            .defaultValue(List.of())
            .defaultValue(PulseConfig.get().getHiddenModules())
            .onChanged(v -> PulseConfig.get().setHiddenModules(v))
            .build()
    );

    public final Setting<Boolean> loadSystemFonts = sgPulse.add(new BoolSetting.Builder()
            .name("load-system-fonts")
            .description("Disabling this for faster launch. You can put font into meteor-client/fonts folder. Restart to take effect.")
            .defaultValue(true)
            .defaultValue(PulseConfig.get().loadSystemFonts)
            .onChanged(v -> PulseConfig.get().loadSystemFonts = v)
            .build()
    );

    public final Setting<Boolean> duplicateModuleNames = sgPulse.add(new BoolSetting.Builder()
            .name("duplicate-module-names")
            .description("Allow duplicate module names. Best for addon compatibility.")
            .defaultValue(false)
            .defaultValue(PulseConfig.get().duplicateModuleNames)
            .onChanged(v -> PulseConfig.get().duplicateModuleNames = v)
            .build()
    );

    public static ConfigModifier get() {
        if (INSTANCE == null) INSTANCE = new ConfigModifier();
        return INSTANCE;
    }
}
