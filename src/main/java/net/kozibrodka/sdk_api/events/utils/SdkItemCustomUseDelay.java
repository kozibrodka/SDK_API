package net.kozibrodka.sdk_api.events.utils;


import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class SdkItemCustomUseDelay extends TemplateItem {

    public SdkItemCustomUseDelay(Identifier i)
    {
        super(i);
        stopArmSwing = false;
        useDelay = 5;
        minecraft_aa = null;
        minecraft_ticksRan = null;
        lastUses = new HashMap();
    }

    public boolean isUseable(Level world, ItemInstance itemstack)
    {
        if(world.getLevelTime() == doNotUseThisTick)
        {
            return false;
        }
        int i = getMinecraftLastClick();
        int j = getMinecraftTicksRan();
        if(lastUses.containsKey(itemstack))
        {
            SdkCustomUseDelayEntry sdkcustomusedelayentry = (SdkCustomUseDelayEntry)lastUses.get(itemstack);
            return sdkcustomusedelayentry.lastMinecraftUse == i && j - i == useDelay;
        } else
        {
            return false;
        }
    }

    public ItemInstance use(ItemInstance itemstack, Level world, PlayerBase entityplayer)
    {
        if(world.getLevelTime() == doNotUseThisTick)
        {
            return itemstack;
        } else
        {
            return onItemRightClickEntity(itemstack, world, entityplayer, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        }
    }

    public ItemInstance onItemRightClickEntity(ItemInstance itemstack, Level world, EntityBase entity, float f, float f1, float f2, float f3,
                                            float f4)
    {
        //
        if(entity.vehicle instanceof WW2Plane || entity.vehicle instanceof WW2Tank || entity.vehicle instanceof WW2Cannon)
        {
            return itemstack;
        }
        //
        long l = 0L;
        int i = 0;
        if(lastUses.containsKey(itemstack))
        {
            SdkCustomUseDelayEntry sdkcustomusedelayentry = (SdkCustomUseDelayEntry)lastUses.get(itemstack);
            l = sdkcustomusedelayentry.lastUse;
            i = sdkcustomusedelayentry.lastMinecraftUse;
        }
        if(world.getLevelTime() - l < 0L)
        {
            l = world.getLevelTime() - (long)useDelay;
            i = getMinecraftTicksRan() - useDelay;
        }
        if(world.getLevelTime() - l >= (long)useDelay && useOryginal(itemstack, world, entity, f, f1, f2, f3, f4))
        {
            l = world.getLevelTime();
            i = getMinecraftTicksRan();
        }
        lastUses.put(itemstack, new SdkCustomUseDelayEntry(l, i));
        return itemstack;
    }

    public abstract boolean useOryginal(ItemInstance itemstack, Level world, EntityBase entity, float f, float f1, float f2, float f3,
                                float f4);

    private int getMinecraftLastClick()
    {
        try
        {
            if(minecraft_aa == null)
            {
                try
                {
                    minecraft_aa = (net.minecraft.client.Minecraft.class).getDeclaredField("ag");  //REMAP , do wywalenia?
                }
                catch(NoSuchFieldException nosuchfieldexception)
                {
                    minecraft_aa = (net.minecraft.client.Minecraft.class).getDeclaredField("mouseTicksProcessed");  //REMAPED
                }
                minecraft_aa.setAccessible(true);
            }
            return minecraft_aa.getInt(SdkTools.minecraft);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            System.out.println(illegalaccessexception);
//            ModLoader.getLogger().throwing("SdkItemCustomUseDelay", "getMinecraftLastClick", illegalaccessexception);
//            SdkTools.ThrowException("An impossible error has occurred!", illegalaccessexception);
            return 0;
        }
        catch(NoSuchFieldException nosuchfieldexception1)
        {
            System.out.println(nosuchfieldexception1);
//            ModLoader.getLogger().throwing("SdkItemCustomUseDelay", "getMinecraftLastClick", nosuchfieldexception1);
//            SdkTools.ThrowException("SDK, you forgot to update your obfuscated reflection!", nosuchfieldexception1);
            return 0;
        }
    }

    private int getMinecraftTicksRan()
    {
        try
        {
            if(minecraft_ticksRan == null)
            {
                try
                {
                    minecraft_ticksRan = (net.minecraft.client.Minecraft.class).getDeclaredField("ticksPlayed");  //REMAPed
                }
                catch(NoSuchFieldException nosuchfieldexception)
                {
                    minecraft_ticksRan = (net.minecraft.client.Minecraft.class).getDeclaredField("V");
                }
                minecraft_ticksRan.setAccessible(true);
            }
            return minecraft_ticksRan.getInt(SdkTools.minecraft);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            System.out.println(illegalaccessexception);
//            ModLoader.getLogger().throwing("SdkItemCustomUseDelay", "getMinecraftTicksRan", illegalaccessexception);
//            SdkTools.ThrowException("An impossible error has occurred!", illegalaccessexception);
            return 0;
        }
        catch(NoSuchFieldException nosuchfieldexception1)
        {
            System.out.println(nosuchfieldexception1);
//            ModLoader.getLogger().throwing("SdkItemCustomUseDelay", "getMinecraftTicksRan", nosuchfieldexception1);
//            SdkTools.ThrowException("SDK, you forgot to update your obfuscated reflection!", nosuchfieldexception1);
            return 0;
        }
    }

    public boolean stopArmSwing;
    public int useDelay;
    private Field minecraft_aa;
    private Field minecraft_ticksRan;
    private Map lastUses;
    public static long doNotUseThisTick = 0L;
}
