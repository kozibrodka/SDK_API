package net.kozibrodka.sdk_api.events.ingame;

import net.kozibrodka.sdk_api.events.init.KeyBindingListener;
import net.kozibrodka.sdk_api.events.parachute.SdkEntityParachute;
import net.kozibrodka.sdk_api.events.utils.SdkMap;
import net.kozibrodka.sdk_api.events.utils.SdkTools;
import net.kozibrodka.sdk_api.mixin.EntityBaseAccessor;
import net.kozibrodka.sdk_api.mixin.GameRendererAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import org.lwjgl.input.Keyboard;

public class mod_SdkUtility {

    public boolean OnTickInGame(Minecraft minecraft)
    {
        if(minecraft.level != null && gameClock != minecraft.level.getLevelTime())
        {
            gameClock = minecraft.level.getLevelTime();
            handleZoom(minecraft);
            OnTickInGameTick(minecraft);
        }
        renderNightvisionOverlay(minecraft);
        renderScopeOverlay(minecraft);
        return true;
    }

    public boolean OnTickInGameTick(Minecraft minecraft)
    {
        ItemInstance itemstack = minecraft.player.inventory.getArmourItem(2);
        if(itemstack != null && SdkMap.scubaTankList.contains(itemstack.itemId))
        {
            minecraft.player.air = ((EntityBaseAccessor)minecraft.player).getField_1648(); //max air
        }
        ItemInstance itemstack1 = minecraft.player.inventory.getArmourItem(3);
        if(itemstack1 == null || !SdkMap.nightvisionList.contains(itemstack.itemId))
        {
            nightvisionEnabled = false;
        }
        handleZoom(minecraft);
        if(minecraft.currentScreen == null && !Keyboard.isKeyDown(KeyBindingListener.keyBinding_parachute.key) && parachuteKeyDown)
        {
            handleParachuteKey(minecraft);
        }
        parachuteKeyDown = Keyboard.isKeyDown(KeyBindingListener.keyBinding_parachute.key);
        if(minecraft.currentScreen == null && !Keyboard.isKeyDown(KeyBindingListener.keyBinding_nightvision.key) && nightvisionKeyDown)
        {
            handleNightvisionKey();
        }
        nightvisionKeyDown = Keyboard.isKeyDown(KeyBindingListener.keyBinding_nightvision.key);
        return true;
    }

    private void renderScopeOverlay(Minecraft minecraft)
    {
        if(!minecraft.options.thirdPerson && currentZoom != 1.0F && minecraft.currentScreen == null)
        {
            SdkTools.renderTextureOverlay("/assets/sdk_api/stationapi/textures/item/miscTelescope.png", 1.0F);
        }
    }

    public static boolean nightvisionEnabled()
    {
        ItemInstance itemstack = SdkTools.minecraft.player.inventory.getArmourItem(3);
        return itemstack != null && SdkMap.nightvisionList.contains(itemstack.itemId) && !SdkTools.minecraft.options.thirdPerson && SdkTools.minecraft.currentScreen == null && nightvisionEnabled;
    }

    private void renderNightvisionOverlay(Minecraft minecraft)
    {
        if(nightvisionEnabled())
        {
            SdkTools.renderTextureOverlay("/assets/sdk_api/stationapi/textures/item/miscNightvision.png", 1.0F);
        }
    }

    private void handleZoom(Minecraft minecraft)
    {
        ItemInstance itemstack = minecraft.player.inventory.getHeldItem();
        if(itemstack == null || !SdkMap.telescopeList.contains(itemstack.getType()) || minecraft.player.inventory.selectedHotbarSlot != lastZoomSlot || minecraft.options.thirdPerson || minecraft.currentScreen != null)
        {
            currentZoomIndex = 0;
        }
        lastZoomSlot = minecraft.player.inventory.selectedHotbarSlot;
        float f = MAX_ZOOMS[currentZoomIndex];
        if(currentZoom > f)
        {
            currentZoom = Math.max(f, currentZoom - 0.0625F);
        } else
        if(currentZoom < f)
        {
            currentZoom = Math.min(f, currentZoom + 0.0625F);
        }
        if(currentZoom != lastZoom)
        {
            try
            {
                ((GameRendererAccessor)minecraft.gameRenderer).setField_2331(Float.valueOf(1.0F / currentZoom));
//                    ModLoader.setPrivateValue(net.minecraft.src.EntityRenderer.class, minecraft.entityRenderer, "E", Float.valueOf(1.0F / currentZoom));
            }
            catch(Exception exception)
            {
//                ModLoader.getLogger().throwing("mod_SdkGuns", "handleZoom", nosuchfieldexception1);
//                SdkTools.ThrowException("SDK, you forgot to update your obfuscated reflection!", nosuchfieldexception1);
                return;
            }
        }
        lastZoom = currentZoom;
    }

    public static void handleNightvisionKey()
    {
        nightvisionEnabled = !nightvisionEnabled;
    }

    public void handleParachuteKey(Minecraft minecraft)
    {
        ItemInstance itemstack = minecraft.player.inventory.getArmourItem(2);
        if(itemstack != null && SdkMap.parachuteList.contains(itemstack.itemId))
        {
            useParachute(itemstack, minecraft.level, minecraft.player);
        }
    }

    private void useParachute(ItemInstance itemstack, Level world, PlayerBase entityplayer)
    {
        if(!SdkTools.onGroundOrInWater(world, entityplayer))
        {
            itemstack.applyDamage(1, SdkTools.minecraft.player);
            if(itemstack.count == 0)
            {
                boolean flag = false;
                int i = 0;
                do
                {
                    if(i >= entityplayer.inventory.armour.length)
                    {
                        break;
                    }
                    if(entityplayer.inventory.armour[i] == itemstack)
                    {
                        entityplayer.inventory.armour[i] = null;
                        flag = true;
                        break;
                    }
                    i++;
                } while(true);
                if(!flag)
                {
                    int j = 0;
                    do
                    {
                        if(j >= entityplayer.inventory.main.length)
                        {
                            break;
                        }
                        if(entityplayer.inventory.main[j] == itemstack)
                        {
                            entityplayer.inventory.main[j] = null;
                            boolean flag1 = true;
                            break;
                        }
                        j++;
                    } while(true);
                }
            }
            world.playSound(entityplayer, "ofensywa.parachute", 0.5F, 1.0F / (SdkTools.random.nextFloat() * 0.1F + 0.95F));
            if(!world.isServerSide)
            { //TODO: Chuj wie...
                world.spawnEntity(new SdkEntityParachute(world, entityplayer));
            }
        }
    }

    public static void useZoom()
    {
        currentZoomIndex = (currentZoomIndex + 1) % MAX_ZOOMS.length;
    }

    private static final float DEFAULT_ZOOM = 1F;
    private static final float MAX_ZOOMS[] = {
            1.0F, 0.5F, 0.25F, 0.125F, 0.0625F
    };
    private static final float ZOOM_INCREMENT = 0.0625F;
    private static float currentZoom;
    private static int currentZoomIndex = 0;
    private static int lastZoomSlot = 0;
    private static float lastZoom;
    private long gameClock = 0L;

    static
    {
        currentZoom = 1.0F;
        lastZoom = currentZoom;
    }

    private static boolean parachuteKeyDown = false;
    private static boolean nightvisionKeyDown = false;
    public static boolean nightvisionEnabled = false;
}
