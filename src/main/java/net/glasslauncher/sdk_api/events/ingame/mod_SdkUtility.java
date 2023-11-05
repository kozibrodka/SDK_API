package net.glasslauncher.sdk_api.events.ingame;

import net.glasslauncher.sdk_api.events.utils.SdkMap;
import net.glasslauncher.sdk_api.events.utils.SdkTools;
import net.glasslauncher.sdk_api.mixin.GameRendererAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemInstance;

public class mod_SdkUtility {
    public boolean OnTickInGame(Minecraft minecraft)
    {
        if(minecraft.level != null && gameClock != minecraft.level.getLevelTime())
        {
            gameClock = minecraft.level.getLevelTime();
            handleZoom(minecraft);
        }
        renderScopeOverlay(minecraft);
        return true;
    }

    private void renderScopeOverlay(Minecraft minecraft)
    {
        if(!minecraft.options.thirdPerson && currentZoom != 1.0F && minecraft.currentScreen == null)
        {
            SdkTools.renderTextureOverlay("/assets/ofensywa/stationapi/textures/item/sdk/miscTelescope.png", 1.0F);
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
}
