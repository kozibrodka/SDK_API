package net.kozibrodka.sdk_api.events.init;

import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

public class ww2Parts {
    @Entrypoint.Namespace
    public static final Namespace MOD_ID = Null.get();

    @EventListener(priority = ListenerPriority.HIGHEST)
    public void registerItems(ItemRegistryEvent event) {
        if(FabricLoader.getInstance().isModLoaded("planes")) { //FabricLoader.getInstance().isModLoaded("planes")
            biplaneWing = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "biplaneWing")).setTranslationKey(MOD_ID, "biplaneWing").setMaxStackSize(8);
            woodenTail = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "woodenTail")).setTranslationKey(MOD_ID, "woodenTail").setMaxStackSize(4);
            woodenPropeller = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "woodenPropeller")).setTranslationKey(MOD_ID, "woodenPropeller").setMaxStackSize(4);
            triplaneWing = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "triplaneWing")).setTranslationKey(MOD_ID, "triplaneWing").setMaxStackSize(8);
            woodenCockpit = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "woodenCockpit")).setTranslationKey(MOD_ID, "woodenCockpit").setMaxStackSize(4);
            wheel = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "wheel")).setTranslationKey(MOD_ID, "wheel").setMaxStackSize(12);
            metalWingMG = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "metalWingMG")).setTranslationKey(MOD_ID, "metalWingMG").setMaxStackSize(8);
            metalTail = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "metalTail")).setTranslationKey(MOD_ID, "metalTail").setMaxStackSize(4);
            bombBay = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "bombBay")).setTranslationKey(MOD_ID, "bombBay").setMaxStackSize(4);
            metalNose = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "metalNose")).setTranslationKey(MOD_ID, "metalNose").setMaxStackSize(4);
            metalWing = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "metalWing")).setTranslationKey(MOD_ID, "metalWing").setMaxStackSize(8);
            metalCockpit = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "metalCockpit")).setTranslationKey(MOD_ID, "metalCockpit").setMaxStackSize(4);
            advancedMetalCockpit = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "advancedMetalCockpit")).setTranslationKey(MOD_ID, "advancedMetalCockpit").setMaxStackSize(2);
            passengerBay = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "passengerBay")).setTranslationKey(MOD_ID, "passengerBay").setMaxStackSize(4);
            metalPropeller = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "metalPropeller")).setTranslationKey(MOD_ID, "metalPropeller").setMaxStackSize(4);
            metalWingpPropv4 = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "metalWingpPropv4")).setTranslationKey(MOD_ID, "metalWingpPropv4").setMaxStackSize(8);
            metalWingpPropv6 = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "metalWingpPropv6")).setTranslationKey(MOD_ID, "metalWingpPropv6").setMaxStackSize(8);
            metalWingpPropv8 = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "metalWingpPropv8")).setTranslationKey(MOD_ID, "metalWingpPropv8").setMaxStackSize(8);
            metalWingpPropRot = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "metalWingpPropRot")).setTranslationKey(MOD_ID, "metalWingpPropRot").setMaxStackSize(8);
            vehicleSeat = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "vehicleSeat")).setTranslationKey(MOD_ID, "vehicleSeat").setMaxStackSize(8);
        }
        if(FabricLoader.getInstance().isModLoaded("vehicles") || FabricLoader.getInstance().isModLoaded("planes")) {
            smallEngine = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "smallEngine")).setTranslationKey(MOD_ID, "smallEngine").setMaxStackSize(4);
            mediumEngine = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "mediumEngine")).setTranslationKey(MOD_ID, "mediumEngine").setMaxStackSize(4);
            largeEngine = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "largeEngine")).setTranslationKey(MOD_ID, "largeEngine").setMaxStackSize(4);
            rotaryEngine = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "rotaryEngine")).setTranslationKey(MOD_ID, "rotaryEngine").setMaxStackSize(4);
            piston = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "piston")).setTranslationKey(MOD_ID, "piston").setMaxStackSize(16);
            machinegun = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "machinegun")).setTranslationKey(MOD_ID, "machinegun").setMaxStackSize(8);
            symbolGerman = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "symbolGerman")).setTranslationKey(MOD_ID, "symbolGerman");
            symbolAmerican = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "symbolAmerican")).setTranslationKey(MOD_ID, "symbolAmerican");
            symbolBritish = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "symbolBritish")).setTranslationKey(MOD_ID, "symbolBritish");
            symbolRussian = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "symbolRussian")).setTranslationKey(MOD_ID, "symbolRussian");
        }
        if(FabricLoader.getInstance().isModLoaded("vehicles")) {
            redstoneCoil = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "redstoneCoil")).setTranslationKey(MOD_ID, "redstoneCoil").setMaxStackSize(16);
            denseredRedstoneCoil = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "denseredRedstoneCoil")).setTranslationKey(MOD_ID, "denseredRedstoneCoil").setMaxStackSize(16);
            largeWheel = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "largeWheel")).setTranslationKey(MOD_ID, "largeWheel").setMaxStackSize(4);
            smallCarChassis = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "smallCarChassis")).setTranslationKey(MOD_ID, "smallCarChassis").setMaxStackSize(4);
            lightTankBody = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "lightTankBody")).setTranslationKey(MOD_ID, "lightTankBody").setMaxStackSize(4);
            mediumTankBody = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "mediumTankBody")).setTranslationKey(MOD_ID, "mediumTankBody").setMaxStackSize(4);
            heavyTankBody = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "heavyTankBody")).setTranslationKey(MOD_ID, "heavyTankBody").setMaxStackSize(4);
            advancedTankBody = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "advancedTankBody")).setTranslationKey(MOD_ID, "advancedTankBody").setMaxStackSize(4);
            tankBarrel = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "tankBarrel")).setTranslationKey(MOD_ID, "tankBarrel").setMaxStackSize(4);
            gunShield = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "gunShield")).setTranslationKey(MOD_ID, "gunShield").setMaxStackSize(4);
            lightTankTurret = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "lightTankTurret")).setTranslationKey(MOD_ID, "lightTankTurret").setMaxStackSize(4);
            mediumTankTurret = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "mediumTankTurret")).setTranslationKey(MOD_ID, "mediumTankTurret").setMaxStackSize(4);
            heavyTankTurret = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "heavyTankTurret")).setTranslationKey(MOD_ID, "heavyTankTurret").setMaxStackSize(4);
            trackPiece = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "trackPiece")).setTranslationKey(MOD_ID, "trackPiece").setMaxStackSize(32);
            towBar = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "towBar")).setTranslationKey(MOD_ID, "towBar").setMaxStackSize(4);
            caterpillarTrack = (TemplateItem) new TemplateItem(Identifier.of(MOD_ID, "caterpillarTrack")).setTranslationKey(MOD_ID, "caterpillarTrack").setMaxStackSize(8);
        }
    }

    @EventListener(priority = ListenerPriority.HIGHEST)
    public void registerTextures(TextureRegisterEvent event) {
        if(FabricLoader.getInstance().isModLoaded("planes")) {
            biplaneWing.setTexture(Identifier.of(MOD_ID, "item/BiplaneWing"));
            woodenTail.setTexture(Identifier.of(MOD_ID, "item/WoodenTail"));
            woodenPropeller.setTexture(Identifier.of(MOD_ID, "item/WoodenPropeller"));
            triplaneWing.setTexture(Identifier.of(MOD_ID, "item/TriplaneWing"));
            woodenCockpit.setTexture(Identifier.of(MOD_ID, "item/WoodenCockpit"));
            wheel.setTexture(Identifier.of(MOD_ID, "item/Wheel"));
            metalWingMG.setTexture(Identifier.of(MOD_ID, "item/MetalWingMG"));
            metalTail.setTexture(Identifier.of(MOD_ID, "item/MetalTail"));
            bombBay.setTexture(Identifier.of(MOD_ID, "item/BombBay"));
            metalNose.setTexture(Identifier.of(MOD_ID, "item/MetalNose"));
            metalWing.setTexture(Identifier.of(MOD_ID, "item/MetalWing"));
            metalCockpit.setTexture(Identifier.of(MOD_ID, "item/MetalCockpit"));
            advancedMetalCockpit.setTexture(Identifier.of(MOD_ID, "item/AdvancedMetalCockpit"));
            passengerBay.setTexture(Identifier.of(MOD_ID, "item/PassengerBay"));
            metalPropeller.setTexture(Identifier.of(MOD_ID, "item/MetalPropeller"));
            metalWingpPropv4.setTexture(Identifier.of(MOD_ID, "item/MetalWingProp"));
            metalWingpPropv6.setTexture(Identifier.of(MOD_ID, "item/MetalWingProp"));
            metalWingpPropv8.setTexture(Identifier.of(MOD_ID, "item/MetalWingProp"));
            metalWingpPropRot.setTexture(Identifier.of(MOD_ID, "item/MetalWingProp"));
            vehicleSeat.setTexture(Identifier.of(MOD_ID, "item/vehicleseat"));
        }

        if(FabricLoader.getInstance().isModLoaded("vehicles") || FabricLoader.getInstance().isModLoaded("planes")) {
            smallEngine.setTexture(Identifier.of(MOD_ID, "item/V4Engine"));
            mediumEngine.setTexture(Identifier.of(MOD_ID, "item/V6Engine"));
            largeEngine.setTexture(Identifier.of(MOD_ID, "item/V8Engine"));
            rotaryEngine.setTexture(Identifier.of(MOD_ID, "item/RotaryEngine"));
            piston.setTexture(Identifier.of(MOD_ID, "item/EnginePiston"));
            machinegun.setTexture(Identifier.of(MOD_ID, "item/Machinegun"));
            symbolGerman.setTexture(Identifier.of(MOD_ID, "item/SymbolGerman"));
            symbolAmerican.setTexture(Identifier.of(MOD_ID, "item/SymbolAmerican"));
            symbolBritish.setTexture(Identifier.of(MOD_ID, "item/SymbolBritish"));
            symbolRussian.setTexture(Identifier.of(MOD_ID, "item/SymbolRussian"));
        }
        if(FabricLoader.getInstance().isModLoaded("vehicles")) {
            redstoneCoil.setTexture(Identifier.of(MOD_ID, "item/RedstoneCoil"));
            denseredRedstoneCoil.setTexture(Identifier.of(MOD_ID, "item/DenseredRedstoneCoil"));
            largeWheel.setTexture(Identifier.of(MOD_ID, "item/LargeWheel"));
            smallCarChassis.setTexture(Identifier.of(MOD_ID, "item/SmallCarChassis"));
            lightTankBody.setTexture(Identifier.of(MOD_ID, "item/LightTankBody"));
            mediumTankBody.setTexture(Identifier.of(MOD_ID, "item/MediumTankBody"));
            heavyTankBody.setTexture(Identifier.of(MOD_ID, "item/HeavyTankBody"));
            advancedTankBody.setTexture(Identifier.of(MOD_ID, "item/AdvancedTankBody"));
            tankBarrel.setTexture(Identifier.of(MOD_ID, "item/TankBarrel"));
            gunShield.setTexture(Identifier.of(MOD_ID, "item/GunShield"));
            lightTankTurret.setTexture(Identifier.of(MOD_ID, "item/LightTankTurret"));
            mediumTankTurret.setTexture(Identifier.of(MOD_ID, "item/MediumTankTurret"));
            heavyTankTurret.setTexture(Identifier.of(MOD_ID, "item/HeavyTankTurret"));
            trackPiece.setTexture(Identifier.of(MOD_ID, "item/TrackPiece"));
            towBar.setTexture(Identifier.of(MOD_ID, "item/Towbar"));
            caterpillarTrack.setTexture(Identifier.of(MOD_ID, "item/CaterpillarTrack"));
        }


    }

    public static TemplateItem biplaneWing;
    public static TemplateItem woodenTail;
    public static TemplateItem woodenPropeller;
    public static TemplateItem triplaneWing;
    public static TemplateItem woodenCockpit;
    public static TemplateItem wheel;
    public static TemplateItem metalWingMG;
    public static TemplateItem metalTail;
    public static TemplateItem bombBay;
    public static TemplateItem machinegun;
    public static TemplateItem metalWing;
    public static TemplateItem metalNose;
    public static TemplateItem metalCockpit;
    public static TemplateItem advancedMetalCockpit;
    public static TemplateItem smallEngine;
    public static TemplateItem mediumEngine;
    public static TemplateItem largeEngine;
    public static TemplateItem rotaryEngine;
    public static TemplateItem piston;
    public static TemplateItem passengerBay;
    public static TemplateItem metalPropeller;
    public static TemplateItem metalWingpPropv4;
    public static TemplateItem metalWingpPropv6;
    public static TemplateItem metalWingpPropv8;
    public static TemplateItem metalWingpPropRot;
    public static TemplateItem vehicleSeat;
    public static TemplateItem redstoneCoil;
    public static TemplateItem denseredRedstoneCoil;
    public static TemplateItem largeWheel;
    public static TemplateItem smallCarChassis;
    public static TemplateItem lightTankBody;
    public static TemplateItem mediumTankBody;
    public static TemplateItem heavyTankBody;
    public static TemplateItem advancedTankBody;
    public static TemplateItem tankBarrel;
    public static TemplateItem gunShield;
    public static TemplateItem lightTankTurret;
    public static TemplateItem mediumTankTurret;
    public static TemplateItem heavyTankTurret;
    public static TemplateItem trackPiece;
    public static TemplateItem towBar;
    public static TemplateItem caterpillarTrack;
    public static TemplateItem symbolGerman;
    public static TemplateItem symbolAmerican;
    public static TemplateItem symbolBritish;
    public static TemplateItem symbolRussian;
}
