package net.kozibrodka.sdk_api.events.init;

import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import net.modificationstation.stationapi.api.util.Null;

public class ww2Parts {
    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener(priority = ListenerPriority.HIGHEST)
    public void registerItems(ItemRegistryEvent event) {
        if(FabricLoader.getInstance().isModLoaded("planes")) { //FabricLoader.getInstance().isModLoaded("planes")
            biplaneWing = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "biplaneWing")).setTranslationKey(MOD_ID, "biplaneWing").setMaxStackSize(8);
            woodenTail = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "woodenTail")).setTranslationKey(MOD_ID, "woodenTail").setMaxStackSize(4);
            woodenPropeller = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "woodenPropeller")).setTranslationKey(MOD_ID, "woodenPropeller").setMaxStackSize(4);
            triplaneWing = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "triplaneWing")).setTranslationKey(MOD_ID, "triplaneWing").setMaxStackSize(8);
            woodenCockpit = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "woodenCockpit")).setTranslationKey(MOD_ID, "woodenCockpit").setMaxStackSize(4);
            wheel = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "wheel")).setTranslationKey(MOD_ID, "wheel").setMaxStackSize(12);
            metalWingMG = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "metalWingMG")).setTranslationKey(MOD_ID, "metalWingMG").setMaxStackSize(8);
            metalTail = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "metalTail")).setTranslationKey(MOD_ID, "metalTail").setMaxStackSize(4);
            bombBay = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "bombBay")).setTranslationKey(MOD_ID, "bombBay").setMaxStackSize(4);
            metalNose = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "metalNose")).setTranslationKey(MOD_ID, "metalNose").setMaxStackSize(4);
            metalWing = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "metalWing")).setTranslationKey(MOD_ID, "metalWing").setMaxStackSize(8);
            metalCockpit = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "metalCockpit")).setTranslationKey(MOD_ID, "metalCockpit").setMaxStackSize(4);
            advancedMetalCockpit = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "advancedMetalCockpit")).setTranslationKey(MOD_ID, "advancedMetalCockpit").setMaxStackSize(2);
            passengerBay = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "passengerBay")).setTranslationKey(MOD_ID, "passengerBay").setMaxStackSize(4);
            metalPropeller = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "metalPropeller")).setTranslationKey(MOD_ID, "metalPropeller").setMaxStackSize(4);
            metalWingpPropv4 = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "metalWingpPropv4")).setTranslationKey(MOD_ID, "metalWingpPropv4").setMaxStackSize(8);
            metalWingpPropv6 = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "metalWingpPropv6")).setTranslationKey(MOD_ID, "metalWingpPropv6").setMaxStackSize(8);
            metalWingpPropv8 = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "metalWingpPropv8")).setTranslationKey(MOD_ID, "metalWingpPropv8").setMaxStackSize(8);
            metalWingpPropRot = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "metalWingpPropRot")).setTranslationKey(MOD_ID, "metalWingpPropRot").setMaxStackSize(8);
            vehicleSeat = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "vehicleSeat")).setTranslationKey(MOD_ID, "vehicleSeat").setMaxStackSize(8);
        }
        if(FabricLoader.getInstance().isModLoaded("vehicles") || FabricLoader.getInstance().isModLoaded("planes")) {
            smallEngine = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "smallEngine")).setTranslationKey(MOD_ID, "smallEngine").setMaxStackSize(4);
            mediumEngine = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "mediumEngine")).setTranslationKey(MOD_ID, "mediumEngine").setMaxStackSize(4);
            largeEngine = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "largeEngine")).setTranslationKey(MOD_ID, "largeEngine").setMaxStackSize(4);
            rotaryEngine = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "rotaryEngine")).setTranslationKey(MOD_ID, "rotaryEngine").setMaxStackSize(4);
            piston = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "piston")).setTranslationKey(MOD_ID, "piston").setMaxStackSize(16);
            machinegun = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "machinegun")).setTranslationKey(MOD_ID, "machinegun").setMaxStackSize(8);
            symbolGerman = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "symbolGerman")).setTranslationKey(MOD_ID, "symbolGerman");
            symbolAmerican = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "symbolAmerican")).setTranslationKey(MOD_ID, "symbolAmerican");
            symbolBritish = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "symbolBritish")).setTranslationKey(MOD_ID, "symbolBritish");
            symbolRussian = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "symbolRussian")).setTranslationKey(MOD_ID, "symbolRussian");
        }
        if(FabricLoader.getInstance().isModLoaded("vehicles")) {
            redstoneCoil = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "redstoneCoil")).setTranslationKey(MOD_ID, "redstoneCoil").setMaxStackSize(16);
            denseredRedstoneCoil = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "denseredRedstoneCoil")).setTranslationKey(MOD_ID, "denseredRedstoneCoil").setMaxStackSize(16);
            largeWheel = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "largeWheel")).setTranslationKey(MOD_ID, "largeWheel").setMaxStackSize(4);
            smallCarChassis = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "smallCarChassis")).setTranslationKey(MOD_ID, "smallCarChassis").setMaxStackSize(4);
            lightTankBody = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "lightTankBody")).setTranslationKey(MOD_ID, "lightTankBody").setMaxStackSize(4);
            mediumTankBody = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "mediumTankBody")).setTranslationKey(MOD_ID, "mediumTankBody").setMaxStackSize(4);
            heavyTankBody = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "heavyTankBody")).setTranslationKey(MOD_ID, "heavyTankBody").setMaxStackSize(4);
            advancedTankBody = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "advancedTankBody")).setTranslationKey(MOD_ID, "advancedTankBody").setMaxStackSize(4);
            tankBarrel = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "tankBarrel")).setTranslationKey(MOD_ID, "tankBarrel").setMaxStackSize(4);
            gunShield = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "gunShield")).setTranslationKey(MOD_ID, "gunShield").setMaxStackSize(4);
            lightTankTurret = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "lightTankTurret")).setTranslationKey(MOD_ID, "lightTankTurret").setMaxStackSize(4);
            mediumTankTurret = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "mediumTankTurret")).setTranslationKey(MOD_ID, "mediumTankTurret").setMaxStackSize(4);
            heavyTankTurret = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "heavyTankTurret")).setTranslationKey(MOD_ID, "heavyTankTurret").setMaxStackSize(4);
            trackPiece = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "trackPiece")).setTranslationKey(MOD_ID, "trackPiece").setMaxStackSize(32);
            towBar = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "towBar")).setTranslationKey(MOD_ID, "towBar").setMaxStackSize(4);
            caterpillarTrack = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "caterpillarTrack")).setTranslationKey(MOD_ID, "caterpillarTrack").setMaxStackSize(8);
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

    public static TemplateItemBase biplaneWing;
    public static TemplateItemBase woodenTail;
    public static TemplateItemBase woodenPropeller;
    public static TemplateItemBase triplaneWing;
    public static TemplateItemBase woodenCockpit;
    public static TemplateItemBase wheel;
    public static TemplateItemBase metalWingMG;
    public static TemplateItemBase metalTail;
    public static TemplateItemBase bombBay;
    public static TemplateItemBase machinegun;
    public static TemplateItemBase metalWing;
    public static TemplateItemBase metalNose;
    public static TemplateItemBase metalCockpit;
    public static TemplateItemBase advancedMetalCockpit;
    public static TemplateItemBase smallEngine;
    public static TemplateItemBase mediumEngine;
    public static TemplateItemBase largeEngine;
    public static TemplateItemBase rotaryEngine;
    public static TemplateItemBase piston;
    public static TemplateItemBase passengerBay;
    public static TemplateItemBase metalPropeller;
    public static TemplateItemBase metalWingpPropv4;
    public static TemplateItemBase metalWingpPropv6;
    public static TemplateItemBase metalWingpPropv8;
    public static TemplateItemBase metalWingpPropRot;
    public static TemplateItemBase vehicleSeat;
    public static TemplateItemBase redstoneCoil;
    public static TemplateItemBase denseredRedstoneCoil;
    public static TemplateItemBase largeWheel;
    public static TemplateItemBase smallCarChassis;
    public static TemplateItemBase lightTankBody;
    public static TemplateItemBase mediumTankBody;
    public static TemplateItemBase heavyTankBody;
    public static TemplateItemBase advancedTankBody;
    public static TemplateItemBase tankBarrel;
    public static TemplateItemBase gunShield;
    public static TemplateItemBase lightTankTurret;
    public static TemplateItemBase mediumTankTurret;
    public static TemplateItemBase heavyTankTurret;
    public static TemplateItemBase trackPiece;
    public static TemplateItemBase towBar;
    public static TemplateItemBase caterpillarTrack;
    public static TemplateItemBase symbolGerman;
    public static TemplateItemBase symbolAmerican;
    public static TemplateItemBase symbolBritish;
    public static TemplateItemBase symbolRussian;
}
