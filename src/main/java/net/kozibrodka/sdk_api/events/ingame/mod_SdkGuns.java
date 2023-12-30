package net.kozibrodka.sdk_api.events.ingame;

import com.mojang.datafixers.util.Pair;
import net.kozibrodka.sdk_api.events.init.KeyBindingListener;
import net.kozibrodka.sdk_api.events.utils.*;
import net.kozibrodka.sdk_api.mixin.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.util.ScreenScaler;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.maths.MathHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class mod_SdkGuns {
    public mod_SdkGuns()
    {

    }


    public boolean OnTickInGameTick(Minecraft minecraft)
    {
        if(!minecraft.player.isAlive())
        {
            currentZoom = 1.0F;
            if(reloadTimes.containsKey(minecraft.player))
            {
                reloadTimes.remove(minecraft.player);
            }
            if(flashTimes.containsKey(minecraft.player))
            {
                flashTimes.remove(minecraft.player);
            }
            currentRecoilV = 0.0D;
            currentRecoilH = 0.0D;
        }
        handleRecoil(minecraft);
        handleReload();
        handleFlash();
        handleZoom(minecraft);
        handleBurstShots(minecraft.level);
        if(minecraft.currentScreen == null && !Keyboard.isKeyDown(KeyBindingListener.keyBinding_zoom.key) && zoomKeyDown)
        {
            toggleZoomEnabled();
        }
        zoomKeyDown = Keyboard.isKeyDown(KeyBindingListener.keyBinding_zoom.key);
        if(minecraft.currentScreen == null && !Keyboard.isKeyDown(KeyBindingListener.keyBinding_reload.key) && reloadKeyDown)
        {
            handleReload(minecraft.level, minecraft.player, false);
            handleReloadKeyVehicle(minecraft, minecraft.player);
        }
        reloadKeyDown = Keyboard.isKeyDown(KeyBindingListener.keyBinding_reload.key);
        if(!Keyboard.isKeyDown(KeyBindingListener.keyBinding_altinventory.key) && atvInventoryKeyDown)
        {
            handleInventoryAtvKey(minecraft, minecraft.player);
        }
        atvInventoryKeyDown = Keyboard.isKeyDown(KeyBindingListener.keyBinding_altinventory.key);
        if(minecraft.currentScreen == null && Keyboard.isKeyDown(KeyBindingListener.keyBinding_altfire.key))
        {
            handleAtvFireKey(minecraft, minecraft.player);
        }
        if(!Keyboard.isKeyDown(KeyBindingListener.keyBinding_exit.key) && exitKeyDown)
        {
            handleExitKey(minecraft, minecraft.player);
        }
        exitKeyDown = Keyboard.isKeyDown(KeyBindingListener.keyBinding_exit.key);
        if(!Keyboard.isKeyDown(KeyBindingListener.keyBinding_rocket.key) && rocketKeyDown)
        {
            handleRocketKey(minecraft, minecraft.player);
        }
        rocketKeyDown = Keyboard.isKeyDown(KeyBindingListener.keyBinding_rocket.key);
        if(!Keyboard.isKeyDown(KeyBindingListener.keyBinding_bomb.key) && bombKeyDown)
        {
            handleBombKey(minecraft, minecraft.player);
        }
        bombKeyDown = Keyboard.isKeyDown(KeyBindingListener.keyBinding_bomb.key);
        handleGuns(minecraft);
        handleJetPack(minecraft);
        return true;
    }

    public boolean OnTickInGame(Minecraft minecraft)
    {
        if(minecraft.level != null && gameClock != minecraft.level.getLevelTime())
        {
            gameClock = minecraft.level.getLevelTime();
            OnTickInGameTick(minecraft);
        }
        renderScopeOverlay(minecraft);
        renderFlash(minecraft.player);
        renderAmmo(minecraft);
        handleArmSwing(minecraft);
        return true;
    }

    private void handleReload()
    {
        for(Iterator iterator = reloadTimes.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            int i = ((Integer)entry.getValue()).intValue() - 1;
            if(i <= 0)
            {
                iterator.remove();
            } else
            {
                entry.setValue(Integer.valueOf(i));
            }
        }

    }

    public static void handleReload(Level world, PlayerBase entityplayer, boolean flag)
    {
        if(!reloadTimes.containsKey(entityplayer))
        {
            ItemInstance itemstack = entityplayer.getHeldItem();
            if(itemstack != null && (itemstack.getType() instanceof SdkItemGun))
            {
                ItemBase item = ((SdkItemGun)itemstack.getType()).requiredBullet;
                int i = -1;
                int j = -1;
                int k = -1;
                boolean flag1 = false;
                do
                {
                    k = -1;
                    PlayerInventory inventoryplayer = entityplayer.inventory;
                    for(int l = i + 1; l < inventoryplayer.main.length; l++)
                    {
                        if(inventoryplayer.main[l] == null || inventoryplayer.main[l].itemId != item.id)
                        {
                            continue;
                        }
                        if(item.getDurability() > 0)
                        {
                            int i1 = item.getDurability() + 1;
                            if(i == -1)
                            {
                                j = i1 - inventoryplayer.main[l].getDamage();
                                if(!flag && item.getDurability() > 0 && j == item.getDurability() + 1)
                                {
                                    break;
                                }
                            } else
                            {
                                if(!flag1)
                                {
                                    reload(world, entityplayer);
                                    flag1 = true;
                                }
                                k = i1 - inventoryplayer.main[l].getDamage();
                                int k1 = Math.min(i1 - j, k);
                                j += k1;
                                k -= k1;
                                setItemDamage(inventoryplayer.main[i], i1 - j);
                                setItemDamage(inventoryplayer.main[l], i1 - k);
                                if(k == 0)
                                {
                                    inventoryplayer.main[l] = new ItemInstance(ItemBase.bucket);
                                }
                                break;
                            }
                        } else
                        if(i == -1)
                        {
                            j = inventoryplayer.main[l].count;
                            if(!flag && item.getDurability() == 0 && j == ((ItemBaseAccessor)item).getMaxStackSize())
                            {
                                break;
                            }
                        } else
                        {
                            if(!flag1)
                            {
                                reload(world, entityplayer);
                                flag1 = true;
                            }
                            k = inventoryplayer.main[l].count;
                            int j1 = Math.min(((ItemBaseAccessor)item).getMaxStackSize() - j, k);
                            j += j1;
                            k -= j1;
                            inventoryplayer.main[i].count = j;
                            inventoryplayer.main[l].count = k;
                            if(k == 0)
                            {
                                inventoryplayer.main[l] = null;
                            }
                            break;
                        }
                        if(i == -1)
                        {
                            i = l;
                        }
                    }

                    if(i == -1)
                    {
                        break;
                    }
                    if(flag1 || !flag)
                    {
                        continue;
                    }
                    reload(world, entityplayer);
                    break;
                } while(k != -1 && (item.getDurability() != 0 || j != ((ItemBaseAccessor)item).getMaxStackSize()) && (item.getDurability() <= 0 || j != item.getDurability() + 1));
            }
        }
    }

    public static void reload(Level world, PlayerBase entityplayer)
    {
        world.playSound(entityplayer, "sdk_api:reload", 1.0F, 1.0F / (((EntityBaseAccessor)entityplayer).getRand().nextFloat() * 0.1F + 0.95F));
        reloadTimes.put(entityplayer, Integer.valueOf(40));
    }

    private void handleRecoil(Minecraft minecraft)
    {
        double d = 0.0D;
        double d1 = currentRecoilV;
        if(minecraft.player != null && currentRecoilV > 0.0D)
        {
            d = Math.min(Math.max(currentRecoilV * 0.10000000149011612D, 0.5D), currentRecoilV);
            currentRecoilV -= d;
            minecraft.player.pitch += d;
        }
        if(minecraft.player != null && Math.abs(currentRecoilH) > 0.0D)
        {
            double d2;
            if(currentRecoilH > 0.0D)
            {
                d2 = Math.min(Math.max((currentRecoilH * 0.10000000149011612D) / 2D, 0.25D), currentRecoilH);
            } else
            {
                d2 = Math.max(Math.min((currentRecoilH * 0.10000000149011612D) / 2D, -0.25D), currentRecoilH);
            }
            if(d != 0.0D)
            {
                double d3 = (d / d1) * currentRecoilH;
                if(currentRecoilH > 0.0D)
                {
                    d2 = Math.min(d3, d2);
                } else
                {
                    d2 = Math.max(d3, d2);
                }
            }
            currentRecoilH -= d2;
            minecraft.player.yaw -= d2;
        }
    }

    private void handleFlash()   // klasa Pair moze coś zawodzić
    {
        for(Iterator iterator = flashTimes.entrySet().iterator(); iterator.hasNext();)
        {
            Map.Entry entry = (Map.Entry)iterator.next();
            int i = (Integer) ((Pair) entry.getValue()).getFirst() - 1;
            if(i <= 0)
            {
                ((LivingAccessor)((Living)entry.getKey())).setMovementSpeed((Float) ((Pair) entry.getValue()).getSecond());
                iterator.remove();
            } else
            {
                entry.setValue(new Pair(i, ((Pair)entry.getValue()).getSecond()));
            }
        }
    }

    public boolean handleJetPack(Minecraft minecraft)
    {
        ItemInstance itemstack = minecraft.player.inventory.getArmourItem(2);
        if(minecraft.currentScreen == null && itemstack != null && SdkMap.jetpackList.contains(itemstack.itemId))
        {
            if(SdkTools.onGroundOrInWater(minecraft.level, minecraft.player) || minecraft.player.vehicle != null)
            {
                jetPackReady = false;
            } else
            if(!Keyboard.isKeyDown(minecraft.options.jumpKey.key))
            {
                jetPackReady = true;
            } else
            if(jetPackReady && useJetPackFuel(minecraft))
            {
                minecraft.player.velocityY = Math.min(minecraft.player.velocityY + 0.059999999999999998D + 0.059999999999999998D, 0.29999999999999999D);
                ((EntityBaseAccessor)minecraft.player).setFallDistance(0.0F);
                for(int i = 0; i < 16; i++)
                {
                    spawnJetPackParticle(minecraft, "flame");
                    spawnJetPackParticle(minecraft, "smoke");
                }

                if(minecraft.level.getLevelTime() - jetPackLastSound < 0L)
                {
                    jetPackLastSound = minecraft.level.getLevelTime() - 15L;
                }
                if(jetPackLastSound == 0L || minecraft.level.getLevelTime() - jetPackLastSound > 15L)
                {
                    minecraft.level.playSound(minecraft.player, "sdk:jetpack", 0.25F, 1.0F / (SdkTools.random.nextFloat() * 0.1F + 0.95F));
                    jetPackLastSound = minecraft.level.getLevelTime();
                }
                return true;
            }
        }
        return false;
    }

    public void spawnJetPackParticle(Minecraft minecraft, String s)
    {
        spawnJetPackParticle(minecraft, s, 0.17499999999999999D, -0.90000000000000002D, -0.29999999999999999D);
        spawnJetPackParticle(minecraft, s, -0.17499999999999999D, -0.90000000000000002D, -0.29999999999999999D);
    }

    public void spawnJetPackParticle(Minecraft minecraft, String s, double d, double d1, double d2)
    {
        float f = -(minecraft.player.field_1012 * 0.01745329F);
        double d3 = d2 * (double) MathHelper.sin(f) + d * (double)MathHelper.cos(f);
        double d4 = d2 * (double)MathHelper.cos(f) - d * (double)MathHelper.sin(f);
        minecraft.level.addParticle(s, minecraft.player.x + d3, minecraft.player.y + d1, minecraft.player.z + d4, SdkTools.random.nextDouble() * 0.10000000000000001D - 0.050000000000000003D, ((SdkTools.random.nextDouble() * 0.10000000000000001D - 0.050000000000000003D) + minecraft.player.velocityY) - 0.5D, SdkTools.random.nextDouble() * 0.10000000000000001D - 0.050000000000000003D);
    }

    private boolean useJetPackFuel(Minecraft minecraft)
    {
        if(SdkTools.useItemInInventory(minecraft.player, (Integer) SdkMap.oilList.get(0)) > 0)
        {
//            setJetPack(true);
            return true;
        } else
        {
            return false;
        }
    }

    private void handleZoom(Minecraft minecraft)
    {
        boolean flag = false;
        float f = 1.0F;
        ItemInstance itemstack = minecraft.player.inventory.getHeldItem();
        if(minecraft.options != null && minecraft.player != null && minecraft.player.inventory != null)
        {
            if(itemstack == null || !SdkMap.scopedList.contains(itemstack.getType()) || minecraft.player.inventory.selectedHotbarSlot != lastZoomSlot || minecraft.options.thirdPerson || minecraft.currentScreen != null)
            {
                zoomEnabled = false;
            }
            lastZoomSlot = minecraft.player.inventory.selectedHotbarSlot;
            if(zoomEnabled && !minecraft.options.thirdPerson)
            {
                if(SdkMap.sniperList.contains(itemstack.getType()))
                {
                    f = 0.125F;
                } else
                {
                    f = 0.25F;
                }
                if(currentZoom > f)
                {
                    flag = true;
                }
            }
        }
        if(zoomEnabled && flag)
        {
            currentZoom = Math.max(f, currentZoom - 0.075F);
        } else
        if(!zoomEnabled && currentZoom < 1.0F)
        {
            currentZoom = Math.min(1.0F, currentZoom + 0.075F);
        }
        if(currentZoom == 0.125F && !sniperZoomedIn && itemstack != null && SdkMap.sniperList.contains(itemstack.getType()))
        {
            sniperZoomedIn = true;
            minecraft.options.mouseSensitivity = minecraft.options.mouseSensitivity / 2;        //addon
        } else
        if(currentZoom != 0.125F && sniperZoomedIn)
        {
            sniperZoomedIn = false;
            minecraft.options.mouseSensitivity = minecraft.options.mouseSensitivity * 2;        //addon
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

    private void handleBurstShots(Level world)
    {
        for(Iterator iterator = burstShots.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            SdkBurstShotEntry sdkburstshotentry = (SdkBurstShotEntry)entry.getValue();
            sdkburstshotentry.burstShots--;
            EntityBase entity = (EntityBase)entry.getKey();
            ItemBase item = null;
            if(entity instanceof PlayerBase)
            {
                ItemInstance itemstack = ((PlayerBase)entity).getHeldItem();
                if(itemstack == sdkburstshotentry.itemStack)
                {
                    item = itemstack.getType();
                }
            } else
            {
                item = ((SdkBurstShotEntry)entry.getValue()).itemStack.getType();
            }
            boolean flag = false;
            if(item != null && ((SdkItemGun)item).burstShots > 0)
            {
                flag = ((SdkItemGun)item).fireBullet(world, entity, sdkburstshotentry.itemStack, true, sdkburstshotentry.xOffset, sdkburstshotentry.yOffset, sdkburstshotentry.zOffset, sdkburstshotentry.rotationYawOffset, sdkburstshotentry.rotationPitchOffset);
            }
            if(sdkburstshotentry.burstShots <= 0 || !flag)
            {
                iterator.remove();
            } else
            {
                entry.setValue(sdkburstshotentry);
            }
        }
    }

    private void handleGuns(Minecraft minecraft)
    {
        if(minecraft.currentScreen == null && Mouse.isButtonDown(1))
        {
            ItemInstance itemstack = minecraft.player.getHeldItem();
            if(itemstack != null && (itemstack.getType() instanceof SdkItemCustomUseDelay))
            {
                SdkItemCustomUseDelay sdkitemcustomusedelay = (SdkItemCustomUseDelay)itemstack.getType();
                if(sdkitemcustomusedelay.isUseable(minecraft.level, itemstack))
                {
                    try
                    {
                        if(minecraft_clickMouse == null || minecraft_aa == null || minecraft_ticksRan == null)
                        {
                            try
                            {
                                minecraft_clickMouse = (net.minecraft.client.Minecraft.class).getDeclaredMethod("method_2107", Integer.TYPE);
                            }
                            catch(NoSuchMethodException nosuchmethodexception)
                            {
                                minecraft_clickMouse = (net.minecraft.client.Minecraft.class).getDeclaredMethod("a", Integer.TYPE);
                            }
                            minecraft_clickMouse.setAccessible(true);
                            try
                            {
                                minecraft_aa = (net.minecraft.client.Minecraft.class).getDeclaredField("mouseTicksProcessed");
                            }
                            catch(NoSuchFieldException nosuchfieldexception)
                            {
                                minecraft_aa = (net.minecraft.client.Minecraft.class).getDeclaredField("ag");
                            }
                            minecraft_aa.setAccessible(true);
                            try
                            {
                                minecraft_ticksRan = (net.minecraft.client.Minecraft.class).getDeclaredField("ticksPlayed");
                            }
                            catch(NoSuchFieldException nosuchfieldexception1)
                            {
                                minecraft_ticksRan = (net.minecraft.client.Minecraft.class).getDeclaredField("V");
                            }
                            minecraft_ticksRan.setAccessible(true);
                        }
                        minecraft_clickMouse.invoke(minecraft, Integer.valueOf(1));
                        minecraft_aa.setInt(minecraft, minecraft_ticksRan.getInt(minecraft));
                    }
                    catch(Exception exception)
                    {
                        System.out.println(exception);
                        return;
                    }
                }
            }
        }
    }

    private void renderFlash(PlayerBase entityplayer)
    {
        if(getFlash(entityplayer) != 0.0F && SdkTools.minecraft.currentScreen == null)
        {
            SdkTools.renderTextureOverlay("/assets/sdk_api/stationapi/textures/item/miscFlash.png", getFlash(entityplayer));
        }
    }

    public static float getFlash(PlayerBase entityplayer)
    {
        if(flashTimes.containsKey(entityplayer))
        {
            return (float) (Integer) ((Pair) flashTimes.get(entityplayer)).getFirst() / 1000F;
        } else
        {
            return 0.0F;
        }
    }

    private void handleArmSwing(Minecraft minecraft)
    {
        if(minecraft.player != null && minecraft.player.inventory != null)
        {
            ItemInstance itemstack = minecraft.player.inventory.getHeldItem();
            if(itemstack != null && (itemstack.getType() instanceof SdkItemCustomUseDelay))
            {
                SdkItemCustomUseDelay sdkitemcustomusedelay = (SdkItemCustomUseDelay)itemstack.getType();
                if(sdkitemcustomusedelay.stopArmSwing)
                {
                    try
                    {
                        ((MinecraftClassAccessor)minecraft).setAttackCooldown(2);
//                        ModLoader.setPrivateValue(Minecraft.class, minecraft, "leftClickCounter", Integer.valueOf(2));
                    }
                    catch(Exception exception)  //uproszczenie w stosunku do oryginalu
                    {
                        System.out.println(exception);
                    }
                }
            }
        }
    }

    private void renderScopeOverlay(Minecraft minecraft)
    {
        if(!minecraft.options.thirdPerson && currentZoom != 1.0F && minecraft.currentScreen == null)
        {
            SdkTools.renderTextureOverlay("/assets/sdk_api/stationapi/textures/item/miscScope.png", 1.0F);
        }
    }

    private void renderAmmo(Minecraft minecraft)
    {
        if(showAmmoBar && minecraft.interactionManager.method_1722() && minecraft.currentScreen == null && (minecraft.options.thirdPerson || currentZoom == 1.0F))
        {
            ItemInstance itemstack = minecraft.player.getHeldItem();
            if(itemstack != null)
            {
                ItemBase item = itemstack.getType();
                if(item instanceof SdkItemGun)
                {
                    int i = getNumberInInventory(minecraft.player.inventory, ((SdkItemGun)item).requiredBullet.id);
                    ScreenScaler scaledresolution = new ScreenScaler(minecraft.options, minecraft.actualWidth, minecraft.actualHeight);
                    int j = scaledresolution.getScaledWidth();
                    int k = scaledresolution.getScaledHeight();
                    String s = Integer.valueOf(i > 0 ? i - 1 : 0).toString();
                    int l = minecraft.textRenderer.getTextWidth(s);
                    minecraft.textRenderer.drawText(s, (j / 2 + 91) - l, k - 32 - 8, 0xffffff);
                    int i1 = getNumberInFirstStackInInventory(minecraft.player.inventory, ((SdkItemGun)item).requiredBullet.id);
                    Tessellator tessellator = Tessellator.INSTANCE;
                    tessellator.start();
                    if(SdkMap.oilAmmoList.contains(item))
                    {
                        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, minecraft.textureManager.getTextureId("/assets/sdk_api/stationapi/textures/item/guiAmmoOil.png"));
                        for(int j1 = i1 - 1; j1 >= 0; j1--)
                        {
                            int i2 = (j / 2 + 91) - j1 - 1 - 14;
                            int l2 = k - 32 - 9;
                            tessellator.vertex(i2, l2 + 9, -90D, 0.0D, 1.0D);
                            tessellator.vertex(i2 + 1, l2 + 9, -90D, 1.0D, 1.0D);
                            tessellator.vertex(i2 + 1, l2, -90D, 1.0D, 0.0D);
                            tessellator.vertex(i2, l2, -90D, 0.0D, 0.0D);
                        }

                    } else
                    if(SdkMap.redAmmoList.contains(item))
                    {
                        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, minecraft.textureManager.getTextureId("/assets/sdk_api/stationapi/textures/item/guiAmmoRedstone.png"));
                        for(int k1 = i1 - 1; k1 >= 0; k1--)
                        {
                            int j2 = (j / 2 + 91) - k1 - 1 - 14;
                            int i3 = k - 32 - 9;
                            tessellator.vertex(j2, i3 + 9, -90D, 0.0D, 1.0D);
                            tessellator.vertex(j2 + 1, i3 + 9, -90D, 1.0D, 1.0D);
                            tessellator.vertex(j2 + 1, i3, -90D, 1.0D, 0.0D);
                            tessellator.vertex(j2, i3, -90D, 0.0D, 0.0D);
                        }

                    } else
                    {
                        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, minecraft.textureManager.getTextureId("/assets/sdk_api/stationapi/textures/item/guiAmmoBullet.png"));
                        for(int l1 = i1 - 1; l1 >= 0; l1--)
                        {
                            int k2 = (j / 2 + 91) - l1 * 2 - 3 - 14;
                            int j3 = k - 32 - 9;
                            tessellator.vertex(k2, j3 + 9, -90D, 0.0D, 1.0D);
                            tessellator.vertex(k2 + 3, j3 + 9, -90D, 1.0D, 1.0D);
                            tessellator.vertex(k2 + 3, j3, -90D, 1.0D, 0.0D);
                            tessellator.vertex(k2, j3, -90D, 0.0D, 0.0D);
                        }

                    }
                    tessellator.draw();
                }
            }
        }
    }

    public static int getNumberInInventory(PlayerInventory inventoryplayer, int i)
    {
        int j = 0;
        for(int k = 0; k < inventoryplayer.main.length; k++)
        {
            if(inventoryplayer.main[k] != null && inventoryplayer.main[k].itemId == i)
            {
                j++;
            }
        }

        return j;
    }

    public static int getNumberInFirstStackInInventory(PlayerInventory inventoryplayer, int i)
    {
        for(int j = 0; j < inventoryplayer.main.length; j++)
        {
            if(inventoryplayer.main[j] != null && inventoryplayer.main[j].itemId == i)
            {
                if(inventoryplayer.main[j].getType().getDurability() > 0)
                {
                    return (inventoryplayer.main[j].getType().getDurability() - inventoryplayer.main[j].getDamage()) + 1;
                } else
                {
                    return inventoryplayer.main[j].count;
                }
            }
        }

        return -1;
    }

    public static void setItemDamage(ItemInstance iteminstance, int i)
    {
        iteminstance.setDamage(i);   //zamiana z accessora, czy cos zmieni? + usunalem try catcha
//            ((ItemInstanceAccessor)iteminstance).setDamage(i);
//            ModLoader.setPrivateValue(net.minecraft.src.ItemStack.class, itemstack, 3, Integer.valueOf(i));
    }

    private void handleInventoryAtvKey(Minecraft minecraft, PlayerBase entityplayer)
    {
        System.out.println("INVENT");
        if(!minecraft.hasLevel())
        {
            if(entityplayer.vehicle instanceof SdkVehicle){
                ((SdkVehicle)entityplayer.vehicle).inventoryAtvKey(minecraft, entityplayer);
            }
            if(entityplayer.vehicle instanceof WW2Plane){
                ((WW2Plane)entityplayer.vehicle).inventoryKey(minecraft, entityplayer);
            }
            if(entityplayer.vehicle instanceof WW2Tank){
                ((WW2Tank)entityplayer.vehicle).inventoryKey(minecraft, entityplayer);
            }
            if(entityplayer.vehicle instanceof WW2Truck){
                ((WW2Truck)entityplayer.vehicle).inventoryKey(minecraft, entityplayer);
            }

        } else
        {
//            ModLoaderMp.SendKey(this, 0); //packet
        }
    }

    private void handleAtvFireKey(Minecraft minecraft, PlayerBase entityplayer) //Fire Vehicle CKM/Plane CKM
    {
        if(!minecraft.hasLevel())
        {
            if(entityplayer.vehicle instanceof SdkVehicle){
                ((SdkVehicle)entityplayer.vehicle).altFireKey(entityplayer);
            }
            if(entityplayer.vehicle instanceof WW2Plane){
                ((WW2Plane)entityplayer.vehicle).fireKey(entityplayer);
            }
            if(entityplayer.vehicle instanceof WW2Tank){
                ((WW2Tank)entityplayer.vehicle).fireSecondaryKey(entityplayer);
            }
        } else
        {
//            ModLoaderMp.SendKey(this, 1); //packet
        }
    }
    private void handleBombKey(Minecraft minecraft, PlayerBase entityplayer){ //Fire Vehicle Shell/Plane Bomb
        if(!minecraft.hasLevel())
        {
            if(entityplayer.vehicle instanceof WW2Plane){
                ((WW2Plane)entityplayer.vehicle).bombKey(entityplayer);
            }
            if(entityplayer.vehicle instanceof WW2Tank){
                ((WW2Tank)entityplayer.vehicle).firePrimaryKey(entityplayer);
            }
        } else
        {

        }
    }

    private void handleExitKey(Minecraft minecraft, PlayerBase entityplayer){ //Vehicle Exit
        if(!minecraft.hasLevel()) {
            if (entityplayer.vehicle instanceof WW2Plane) {
                ((WW2Plane) entityplayer.vehicle).exitKey(entityplayer);
            }
            if (entityplayer.vehicle instanceof WW2Tank) {
                ((WW2Tank) entityplayer.vehicle).exitKey(entityplayer);
            }
            if (entityplayer.vehicle instanceof WW2Truck) {
                ((WW2Truck) entityplayer.vehicle).exitKey(entityplayer);
            }
            if (entityplayer.vehicle instanceof WW2Cannon) {
                ((WW2Cannon) entityplayer.vehicle).exitKey(entityplayer);
            }
        }
    }

    private void handleRocketKey(Minecraft minecraft, PlayerBase entityplayer){// Plane Rocket/ Vehicle Tow
        if(!minecraft.hasLevel()) {
            if (entityplayer.vehicle instanceof WW2Plane) {
                ((WW2Plane) entityplayer.vehicle).rocketKey(entityplayer);
            }
            if (entityplayer.vehicle instanceof WW2Tank) {
                ((WW2Tank) entityplayer.vehicle).towKey(entityplayer);
            }
        }
    }

    private void handleReloadKeyVehicle(Minecraft minecraft, PlayerBase entityplayer){
        if(!minecraft.hasLevel()) {
            if (entityplayer.vehicle instanceof WW2Plane) {
                ((WW2Plane) entityplayer.vehicle).reloadKey(entityplayer);
            }
            if (entityplayer.vehicle instanceof WW2Tank) {
                ((WW2Tank) entityplayer.vehicle).reloadKey(entityplayer);
            }
        }
    }


    private static void toggleZoomEnabled()
    {
        zoomEnabled = !zoomEnabled;
    }

    public static boolean getSniperZoomedIn()
    {
        return sniperZoomedIn;
    }

    private long gameClock = 0L;
    public static int debugKey = 50;
    private static boolean debugKeyDown = false;
    public static Map reloadTimes = new HashMap();
    public static final double RECOIL_FIX_FACTOR = 0.10000000149011612D;
    public static final double MIN_RECOIL_FIX = 0.5D;
    public static double currentRecoilV = 0.0D;
    public static double currentRecoilH = 0.0D;
    public static final float DEFAULT_ZOOM = 1F;
    public static final float MAX_ZOOM_SNIPER = 0.125F;
    public static final float MAX_ZOOM_SG552 = 0.25F;
    private static final float ZOOM_INCREMENT = 0.075F;
    private static float currentZoom;
    private static int lastZoomSlot = 0;
    private static boolean zoomEnabled = false;
    private static float lastZoom;
    private static boolean sniperZoomedIn = false;
    public static Map flashTimes = new HashMap();
    public static boolean guns = true;
    public static boolean bulletsDestroyGlass = true;
    public static boolean showAmmoBar = true;
    public static boolean muzzleFlash = true;
    public static boolean grenades = true;
    public static boolean explosionsDestroyBlocks = true;
    public static boolean laserSetsFireToBlocks = true;
    public static boolean nuke = true;
    public static float nukeBlastDiameter = 8F;
    public static int nukeFuse = 80;
    public static boolean oil = true;
    public static boolean lighter = true;
    public static boolean cannon = true;
    public static boolean sentries = true;
    public static boolean sentriesKillAnimals = false;
    public static boolean atv = true;
    public static boolean jetPack = true;
    public static boolean ammoRestrictions = true;
    public static int monsterSpawns = 70;
    public static boolean ammoCasings = true;

    public static int zoomKey = 44;
    public static int reloadKey = 19;
    public static int atvInventoryKey = 23;
    public static int atvFireKey = 57;

    public static int blockIdNuke = 110;
    public static int blockIdLighter = 112;
    public static int blockIdOil = 113;
    public static int blockIdCannon = 114;
    public static boolean zoomKeyDown = false;
    public static boolean reloadKeyDown = false;
    public static boolean atvInventoryKeyDown = false;
    public static boolean exitKeyDown = false;
    public static boolean rocketKeyDown = false;
    public static boolean bombKeyDown = false;
    public static Map burstShots = new HashMap();
    private Method minecraft_clickMouse;
    private Field minecraft_aa;
    private Field minecraft_ticksRan;
    private boolean jetPackReady;
    private boolean jetPackOn;
    private long jetPackLastSound;
    private static final double JET_PACK_LIFT = 0.059999999999999998D;
    private static final double JET_PACK_MAX_LIFT = 0.29999999999999999D;
    private static final int JET_PACK_SOUND_INTERVAL = 15;
    static
    {
        currentZoom = 1.0F;
        lastZoom = currentZoom;
    }
}
