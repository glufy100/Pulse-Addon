package Glufy.Pulse;

import Glufy.Pulse.commands.*;
import Glufy.Pulse.gui.hud.RadarHud;
import Glufy.Pulse.gui.themes.rounded.MeteorRoundedGuiTheme;
import Glufy.Pulse.modules.Automation.*;
import Glufy.Pulse.modules.Build.*;
import Glufy.Pulse.modules.Build.highwayborers.*;
import Glufy.Pulse.modules.Combat.*;
import Glufy.Pulse.modules.Crash.*;
import Glufy.Pulse.modules.Dupe.*;
import Glufy.Pulse.modules.Misc.*;
import Glufy.Pulse.modules.Movement.*;
import Glufy.Pulse.modules.Player.*;
import Glufy.Pulse.modules.Render.*;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.commands.Commands;
import meteordevelopment.meteorclient.gui.GuiThemes;
import meteordevelopment.meteorclient.systems.Systems;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class PulseAddon extends MeteorAddon {
    public static final Logger LOG = LoggerFactory.getLogger("Pulse");
    public static final Category Automation = new Category("Automation", Items.REDSTONE.getDefaultStack());
    public static final Category Build = new Category("Build", Items.BRICKS.getDefaultStack());
    public static final Category Crash = new Category("Crash", Items.MACE.getDefaultStack());
    public static final Category Dupe = new Category("Dupe", Items.DIAMOND_BLOCK.getDefaultStack());
    public static final HudGroup HUD_GROUP = new HudGroup("Pulse");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Meteor Pulse Addon");

        // Modules
        Modules modules = Modules.get();
        modules.add(new AimAssist());
        modules.add(new AntiBot());
        modules.add(new AntiCrash());
        modules.add(new AntiSpawnpoint());
        modules.add(new AntiVanish());
        modules.add(new ArrowDmg());
        modules.add(new AutoBedTrap());
        modules.add(new AutoCraft());
        modules.add(new AutoExtinguish());
        modules.add(new AutoFarm());
        modules.add(new AutoGrind());
        modules.add(new AutoLogin());
        modules.add(new AutoPot());
        modules.add(new AutoSoup());
        modules.add(new AutoTNT());
        modules.add(new AutoWither());
        modules.add(new BoatGlitch());
        modules.add(new BlockIn());
        modules.add(new BoatPhase());
        modules.add(new Boost());
        modules.add(new BungeeCordSpoof());
        modules.add(new ChatBot());
        modules.add(new ChestAura());
        modules.add(new ChorusExploit());
        modules.add(new ColorSigns());
        modules.add(new Confuse());
        modules.add(new CoordLogger());
        modules.add(new CustomPackets());
        modules.add(new ExtraElytra());
        modules.add(new FullFlight());
        modules.add(new GamemodeNotifier());
        modules.add(new GhostMode());
        modules.add(new Glide());
        modules.add(new ItemGenerator());
        modules.add(new InteractionMenu());
        modules.add(new Jetpack());
        modules.add(new KnockbackPlus());
        modules.add(new LawnBot());
        modules.add(new Lavacast());
        modules.add(new MossBot());
        modules.add(new NewChunks());
        modules.add(new NoJumpDelay());
        modules.add(new ObsidianFarm());
        modules.add(new OreSim());
        modules.add(new PacketFly());
        modules.add(new Painter());
        modules.add(new Rendering());
        modules.add(new RoboWalk());
        modules.add(new ShieldBypass());
        modules.add(new SilentDisconnect());
        modules.add(new SkeletonESP());
        modules.add(new SoundLocator());
        modules.add(new TreeAura());
        modules.add(new VehicleOneHit());
        modules.add(new AutoEnchant());
        modules.add(new AutoRename());
        modules.add(new AirstrikePlus());
        modules.add(new AnHero());
        modules.add(new AutoCommand());
        modules.add(new AutoDisplays());
        modules.add(new AutoDrop());
        modules.add(new AutoLavaCaster());
        modules.add(new AutoMountain());
        modules.add(new AutoScoreboard());
        modules.add(new AutoStaircase());
        modules.add(new AutoTitles());
        modules.add(new BaseFinder());
        modules.add(new BlockListMineCommand());
        modules.add(new BoatKill());
        modules.add(new BoomPlus());
        modules.add(new ExplosionAura());
        modules.add(new FlightAntikick());
        modules.add(new HandOfGod());
        modules.add(new HoleAndTunnelAndStairsESP());
        modules.add(new InstaMineNuker());
        modules.add(new InstantKill());
        modules.add(new InstaSafetyBox());
        modules.add(new LavaAura());
        modules.add(new MaceKill());
        modules.add(new NbtEditor());
        modules.add(new NewerNewChunks());
        modules.add(new OPplayerTPmodule());
        modules.add(new OPServerKillModule());
        modules.add(new RedstoneNuker());
        modules.add(new StorageLooter());
        modules.add(new Teleport());
        modules.add(new TPFly());
        modules.add(new TrailMaker());
        modules.add(new AfkLogout());
        modules.add(new AutoCenter());
        modules.add(new AutoWalkHIG());
        modules.add(new AxisViewer());
        modules.add(new HighwayBuilderHIG());
        modules.add(new HighwayTools());
        modules.add(new HotbarManager());
        modules.add(new LiquidFillerHIG());
        modules.add(new OffhandManager());
        modules.add(new ScaffoldHIG());
        modules.add(new AxisBorer());
        modules.add(new NegNegBorer());
        modules.add(new NegPosBorer());
        modules.add(new PosNegBorer());
        modules.add(new PosPosBorer());
        modules.add(new JumpFlight());
        modules.add(new AutoInteract());
        modules.add(new AutoLeave());
        modules.add(new AutoMud());
        modules.add(new AutoSneak());
        modules.add(new AutoSleep());
        modules.add(new Grid());
        modules.add(new ForceOPSign());

        //Dupe Module
        modules.add(new ShulkerDupe());
        modules.add(new BookAndQuillDupe());
        modules.add(new ItemFrameDupe());
        modules.add(new XsDupe());

        //Crash Module
        modules.add(new AACCrash());
        modules.add(new AdvancedCrash());
        modules.add(new AutoLagSign());
        modules.add(new BookCrash());
        modules.add(new BungeeCrash());
        modules.add(new CompletionCrash());
        modules.add(new ContainerCrash());
        modules.add(new CraftingCrash());
        modules.add(new CreativeCrash());
        modules.add(new EntityCrash());
        modules.add(new ErrorCrash());
        modules.add(new ExceptionCrash());
        modules.add(new InteractCrash());
        modules.add(new JigSawCrash());
        modules.add(new LecternCrash());
        modules.add(new MessageLagger());
        modules.add(new MovementCrash());
        modules.add(new PacketSpammer());
        modules.add(new SequenceCrash());
        modules.add(new SignCrash());
        modules.add(new StorageCrash());
        modules.add(new SwingCrash());
        modules.add(new TradeCrash());
        modules.add(new UDPFlood());
        modules.add(new WindowCrash());

        // Commands
        Commands.add(new CenterCommand());
        Commands.add(new ClearChatCommand());
        Commands.add(new GhostCommand());
        Commands.add(new GiveCommand());
        Commands.add(new HeadsCommand());
        Commands.add(new KickCommand());
        Commands.add(new LocateCommand());
        Commands.add(new PanicCommand());
        Commands.add(new ReconnectCommand());
        Commands.add(new ServerCommand());
        Commands.add(new SaveSkinCommand());
        Commands.add(new SeedCommand());
        Commands.add(new SetBlockCommand());
        Commands.add(new SetVelocityCommand());
        Commands.add(new TeleportCommand());
        Commands.add(new TerrainExport());
        Commands.add(new WorldInfoCommand());
        Commands.add(new ViewNbtCommand());
        Commands.add(new AutoVclipCommand());
        Commands.add(new AutoVaultClipCommand());
        Commands.add(new NewChunkCounter());
        Commands.add(new GarbageCleanerCommand());
        Commands.add(new LavaTimeCalculator());
        Commands.add(new CasterTimer());
        Commands.add(new CrashItemCommand());
        Commands.add(new CheckHostCommand());
        Commands.add(new CheckSSLCommand());
        Commands.add(new ClearCommand());
        Commands.add(new Coordinates());
        Commands.add(new DNSLookupCommand());
        Commands.add(new IPBlacklistCommand());
        Commands.add(new LatencyCommand());
        Commands.add(new NetProxyCommand());
        Commands.add(new NetProxyDisconnectCommand());
        Commands.add(new PingCommand());
        Commands.add(new SpoofNameCommand());
        Commands.add(new SpoofUUIDCommand());
        Commands.add(new SubnetCalculatorCommand());
        Commands.add(new Title());
        Commands.add(new TracerouteCommand());
        Commands.add(new TwoBTwoTSeenCommand());
        Commands.add(new TwoBTwoTStatsCommand());
        Commands.add(new WebhookDeleteCommand());
        Commands.add(new WebhookSendCommand());

        // HUD
        Hud hud = Systems.get(Hud.class);
        hud.register(RadarHud.INFO);

        // Themes
        GuiThemes.add(new MeteorRoundedGuiTheme());
    }


    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(Automation);
        Modules.registerCategory(Build);
        Modules.registerCategory(Dupe);
        Modules.registerCategory(Crash);
    }

    @Override
    public String getWebsite() {
        return "https://github.com/glufy100/Pulse-Addon";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("Glufy", "Pulse");
    }

    @Override
    public String getCommit() {
        String commit = FabricLoader
                .getInstance()
                .getModContainer("Pulse")
                .get().getMetadata()
                .getCustomValue("github:sha")
                .getAsString();
        LOG.info(String.format("Pulse version: %s", commit));
        return commit.isEmpty() ? null : commit.trim();
    }

    public String getPackage() {
        return "Glufy.Pulse";
    }
}