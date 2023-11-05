package net.glasslauncher.sdk_api.events.utils;

import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.sdk_api.mixin.BlockFireAccessor;
import net.glasslauncher.sdk_api.mixin.EntityBaseAccessor;
import net.glasslauncher.sdk_api.mixin.LivingAccessor;
import net.minecraft.block.BlockBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.util.ScreenScaler;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

public class SdkTools {

    public SdkTools()
    {
    }

//    public static void addSounds()
//    {
//        if(!soundsAdded)
//        {
//            soundsAdded = true;
//            File file = new File(Minecraft.getMinecraftDir(), "resources/");
//            String as[] = {
//                    "sound", "music", "streaming"
//            };
//            for(int i = 0; i < as.length; i++)
//            {
//                File file1 = new File(file, "sdk");
//                if(!file1.exists())
//                {
//                    RuntimeException runtimeexception = new RuntimeException("sdk folder not found in the %APPDATA%\\.minecraft\\resources folder.");
//                    ModLoader.getLogger().throwing("SdkTools", "addSounds", runtimeexception);
//                    ThrowException("sdk folder not found in the %APPDATA%\\.minecraft\\resources folder.", runtimeexception);
//                    return;
//                }
//                File afile[] = (new File(file, (new StringBuilder()).append("sdk/").append(as[i]).toString())).listFiles();
//                if(afile == null)
//                {
//                    continue;
//                }
//                for(int j = 0; j < afile.length; j++)
//                {
//                    minecraft.installResource((new StringBuilder()).append(as[i]).append("/").append("sdk/").append(afile[j].getName()).toString(), afile[j]);
//                }
//
//            }
//
//        }
//    }

    public static void renderTextureOverlay(String s, float f)
    {
        ScreenScaler scaledresolution = new ScreenScaler(minecraft.options, minecraft.actualWidth, minecraft.actualHeight);
        int i = scaledresolution.getScaledWidth();
        int j = scaledresolution.getScaledHeight();
        GL11.glEnable(3042 /*GL_BLEND*/);
        GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, f);
        GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, minecraft.textureManager.getTextureId(s));
        Tessellator tessellator = Tessellator.INSTANCE;
        tessellator.start();
        tessellator.vertex(0.0D, j, -90D, 0.0D, 1.0D);
        tessellator.vertex(i, j, -90D, 1.0D, 1.0D);
        tessellator.vertex(i, 0.0D, -90D, 1.0D, 0.0D);
        tessellator.vertex(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
        GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, f);
    }

//    public static void setBurnRate(int i, int j, int k)
//    {
//        if(blockFireSetBurnRate == null)
//        {
//            try
//            {
//                try
//                {
//                    blockFireSetBurnRate = (net.minecraft.src.BlockFire.class).getDeclaredMethod("a", new Class[] {
//                            Integer.TYPE, Integer.TYPE, Integer.TYPE
//                    });
//                }
//                catch(NoSuchMethodException nosuchmethodexception)
//                {
//                    blockFireSetBurnRate = (net.minecraft.src.BlockFire.class).getDeclaredMethod("setBurnRate", new Class[] {
//                            Integer.TYPE, Integer.TYPE, Integer.TYPE
//                    });
//                }
//                blockFireSetBurnRate.setAccessible(true);
//            }
//            catch(NoSuchMethodException nosuchmethodexception1)
//            {
//                ModLoader.getLogger().throwing("SdkTools", "setBurnRate", nosuchmethodexception1);
//                ThrowException("SDK, you forgot to update your obfuscated reflection!", nosuchmethodexception1);
//                return;
//            }
//        }
//        try
//        {
//            blockFireSetBurnRate.invoke(Block.fire, new Object[] {
//                    Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k)
//            });
//        }
//        catch(InvocationTargetException invocationtargetexception)
//        {
//            ModLoader.getLogger().throwing("SdkTools", "setBurnRate", invocationtargetexception);
//            ThrowException("An impossible error has occurred!", invocationtargetexception);
//            return;
//        }
//        catch(IllegalAccessException illegalaccessexception)
//        {
//            ModLoader.getLogger().throwing("SdkTools", "setBurnRate", illegalaccessexception);
//            ThrowException("An impossible error has occurred!", illegalaccessexception);
//            return;
//        }
//    }
//
//    public static void setShapedRecipe(boolean flag, ItemStack itemstack, Object aobj[])
//    {
//        removeShapedRecipe(itemstack, aobj);
//        if(flag)
//        {
//            addShapedRecipe(itemstack, aobj);
//        }
//    }
//
//    public static void addShapedRecipe(ItemStack itemstack, Object aobj[])
//    {
//        CraftingManager.getInstance().addRecipe(itemstack, aobj);
//    }
//
//    public static boolean removeShapedRecipe(ItemStack itemstack, Object aobj[])
//    {
//        try
//        {
//            if(recipes == null)
//            {
//                recipes = (List)ModLoader.getPrivateValue(net.minecraft.src.CraftingManager.class, CraftingManager.getInstance(), 1);
//            }
//            String s = "";
//            int i = 0;
//            int j = 0;
//            int k = 0;
//            if(aobj[i] instanceof String[])
//            {
//                String as[] = (String[])aobj[i++];
//                for(int l = 0; l < as.length; l++)
//                {
//                    String s2 = as[l];
//                    k++;
//                    j = s2.length();
//                    s = (new StringBuilder()).append(s).append(s2).toString();
//                }
//
//            } else
//            {
//                do
//                {
//                    String s1 = (String)aobj[i++];
//                    k++;
//                    j = s1.length();
//                    s = (new StringBuilder()).append(s).append(s1).toString();
//                } while(aobj[i] instanceof String);
//            }
//            HashMap hashmap = new HashMap();
//            for(; i < aobj.length; i += 2)
//            {
//                Character character = (Character)aobj[i];
//                ItemStack itemstack1 = null;
//                if(aobj[i + 1] instanceof Item)
//                {
//                    itemstack1 = new ItemStack((Item)aobj[i + 1]);
//                } else
//                if(aobj[i + 1] instanceof Block)
//                {
//                    itemstack1 = new ItemStack((Block)aobj[i + 1], 1, -1);
//                } else
//                if(aobj[i + 1] instanceof ItemStack)
//                {
//                    itemstack1 = (ItemStack)aobj[i + 1];
//                }
//                hashmap.put(character, itemstack1);
//            }
//
//            ItemStack aitemstack[] = new ItemStack[j * k];
//            for(int i1 = 0; i1 < j * k; i1++)
//            {
//                char c = s.charAt(i1);
//                if(hashmap.containsKey(Character.valueOf(c)))
//                {
//                    aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c))).copy();
//                } else
//                {
//                    aitemstack[i1] = null;
//                }
//            }
//
//            ShapedRecipes shapedrecipes = new ShapedRecipes(j, k, aitemstack, itemstack);
//            for(int j1 = 0; j1 < recipes.size(); j1++)
//            {
//                IRecipe irecipe = (IRecipe)recipes.get(j1);
//                if(irecipe instanceof ShapedRecipes)
//                {
//                    ShapedRecipes shapedrecipes1 = (ShapedRecipes)irecipe;
//                    if(shapedrecipes.getRecipeSize() == shapedrecipes1.getRecipeSize())
//                    {
//                        ItemStack itemstack2 = (ItemStack)ModLoader.getPrivateValue(net.minecraft.src.ShapedRecipes.class, shapedrecipes, 3);
//                        ItemStack itemstack3 = (ItemStack)ModLoader.getPrivateValue(net.minecraft.src.ShapedRecipes.class, shapedrecipes1, 3);
//                        if(itemstack2 != null && itemstack3 != null && ItemStack.areItemStacksEqual(itemstack2, itemstack3))
//                        {
//                            ItemStack aitemstack1[] = (ItemStack[])ModLoader.getPrivateValue(net.minecraft.src.ShapedRecipes.class, shapedrecipes, 2);
//                            ItemStack aitemstack2[] = (ItemStack[])ModLoader.getPrivateValue(net.minecraft.src.ShapedRecipes.class, shapedrecipes1, 2);
//                            boolean flag = true;
//                            int k1 = 0;
//                            do
//                            {
//                                if(k1 >= aitemstack1.length)
//                                {
//                                    break;
//                                }
//                                if((aitemstack1[k1] != null || aitemstack2[k1] != null) && (aitemstack1[k1] == null || aitemstack2[k1] == null || !ItemStack.areItemStacksEqual(aitemstack1[k1], aitemstack2[k1])))
//                                {
//                                    flag = false;
//                                    break;
//                                }
//                                k1++;
//                            } while(true);
//                            if(flag)
//                            {
//                                recipes.remove(j1);
//                                return true;
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
//        catch(IllegalArgumentException illegalargumentexception)
//        {
//            ModLoader.getLogger().throwing("SdkTools", "removeShapedRecipe", illegalargumentexception);
//            ThrowException("An impossible error has occurred!", illegalargumentexception);
//            return false;
//        }
//        catch(SecurityException securityexception)
//        {
//            ModLoader.getLogger().throwing("SdkTools", "removeShapedRecipe", securityexception);
//            ThrowException("An impossible error has occurred!", securityexception);
//            return false;
//        }
//        catch(NoSuchFieldException nosuchfieldexception)
//        {
//            ModLoader.getLogger().throwing("SdkTools", "removeShapedRecipe", nosuchfieldexception);
//            ThrowException("An impossible error has occurred!", nosuchfieldexception);
//            return false;
//        }
//        return false;
//    }
//
//    public static void setShapelessRecipe(boolean flag, ItemStack itemstack, Object aobj[])
//    {
//        removeShapelessRecipe(itemstack, aobj);
//        if(flag)
//        {
//            addShapelessRecipe(itemstack, aobj);
//        }
//    }
//
//    public static void addShapelessRecipe(ItemStack itemstack, Object aobj[])
//    {
//        CraftingManager.getInstance().addShapelessRecipe(itemstack, aobj);
//    }
//
//    public static boolean removeShapelessRecipe(ItemStack itemstack, Object aobj[])
//    {
//        try
//        {
//            if(recipes == null)
//            {
//                recipes = (List)ModLoader.getPrivateValue(net.minecraft.src.CraftingManager.class, CraftingManager.getInstance(), 1);
//            }
//            ArrayList arraylist = new ArrayList();
//            for(int i = 0; i < aobj.length; i++)
//            {
//                Object obj = aobj[i];
//                if(obj instanceof ItemStack)
//                {
//                    arraylist.add(((ItemStack)obj).copy());
//                    continue;
//                }
//                if(obj instanceof Item)
//                {
//                    arraylist.add(new ItemStack((Item)obj));
//                    continue;
//                }
//                if(obj instanceof Block)
//                {
//                    arraylist.add(new ItemStack((Block)obj));
//                } else
//                {
//                    throw new RuntimeException("Invalid shapeless recipe!");
//                }
//            }
//
//            for(int j = 0; j < recipes.size(); j++)
//            {
//                if(recipes.get(j) instanceof ShapelessRecipes)
//                {
//                    ShapelessRecipes shapelessrecipes = (ShapelessRecipes)recipes.get(j);
//                    ItemStack itemstack1 = (ItemStack)ModLoader.getPrivateValue(net.minecraft.src.ShapelessRecipes.class, shapelessrecipes, 0);
//                    List list = (List)ModLoader.getPrivateValue(net.minecraft.src.ShapelessRecipes.class, shapelessrecipes, 1);
//                    if(ItemStack.areItemStacksEqual(itemstack1, itemstack) && arraylist.size() == list.size())
//                    {
//                        boolean flag = true;
//                        for(int k = 0; k < arraylist.size(); k++)
//                        {
//                            ItemStack itemstack2 = (ItemStack)list.get(k);
//                            ItemStack itemstack3 = (ItemStack)arraylist.get(k);
//                            if(!ItemStack.areItemStacksEqual(itemstack2, itemstack3))
//                            {
//                                flag = false;
//                            }
//                        }
//
//                        if(flag)
//                        {
//                            return true;
//                        }
//                    }
//                }
//            }
//
//        }
//        catch(IllegalArgumentException illegalargumentexception)
//        {
//            ModLoader.getLogger().throwing("SdkTools", "removeShapelessRecipe", illegalargumentexception);
//            ThrowException("An impossible error has occurred!", illegalargumentexception);
//            return false;
//        }
//        catch(SecurityException securityexception)
//        {
//            ModLoader.getLogger().throwing("SdkTools", "removeShapelessRecipe", securityexception);
//            ThrowException("An impossible error has occurred!", securityexception);
//            return false;
//        }
//        catch(NoSuchFieldException nosuchfieldexception)
//        {
//            ModLoader.getLogger().throwing("SdkTools", "removeShapelessRecipe", nosuchfieldexception);
//            ThrowException("An impossible error has occurred!", nosuchfieldexception);
//            return false;
//        }
//        return false;
//    }

    public static void attackEntityIgnoreDelay(Living entityliving, EntityBase entity, int i)
    {
        int j;
        int k;
        int l;
        try
        {
            j = ((Integer)((LivingAccessor)entityliving).getField_1058());
            k = ((Integer)((EntityBaseAccessor)entityliving).getField_1613());
            l = ((Integer)((LivingAccessor)entityliving).getField_1009());

            if((float)k > (float)l / 2.0F)
            {
                entityliving.damage(entity, j + i);
            } else
            {
                entityliving.damage(entity, i);
            }
        }
        catch(Exception exception)
        {

        }
    }

//    public static void ThrowException(String s, Throwable throwable)
//    {
//        try
//        {
//            ModLoader.setPrivateValue(java.lang.Throwable.class, throwable, "detailMessage", s);
//        }
//        catch(NoSuchFieldException nosuchfieldexception) { }
//        ModLoader.ThrowException(s, throwable);
//    }

    public static boolean isFlammable(int i)
    {
        int ai[];
        try {
            ai = (int[]) ((BlockFireAccessor) BlockBase.FIRE).getSpreadDelayChance();
            return ai[i] != 0 || i == BlockBase.NETHERRACK.id;
//                ai = (int[])ModLoader.getPrivateValue(net.minecraft.src.BlockFire.class, Block.fire, "b");
        } catch (Exception exception) {
//                ai = (int[])ModLoader.getPrivateValue(net.minecraft.src.BlockFire.class, Block.fire, "abilityToCatchFire");
        }
        return false;
    }

    public static int useItemInInventory(PlayerBase entityplayer, int i)
    {
        int j = getInventorySlotContainItem(entityplayer.inventory, i);
        if(j < 0)
        {
            return 0;
        }
        if(ItemBase.byId[i].getDurability() > 0)
        {
            entityplayer.inventory.main[j].applyDamage(1, entityplayer);
            if(entityplayer.inventory.main[j].count == 0)
            {
                entityplayer.inventory.main[j] = new ItemInstance(ItemBase.bucket);
                if(getInventorySlotContainItem(entityplayer.inventory, i) >= 0)
                {
                    return 2;
                }
            }
        } else
        if(--entityplayer.inventory.main[j].count <= 0)
        {
            entityplayer.inventory.main[j] = null;
            if(getInventorySlotContainItem(entityplayer.inventory, i) >= 0)
            {
                return 2;
            }
        }
        return 1;
    }

    private static int getInventorySlotContainItem(PlayerInventory inventoryplayer, int i)
    {
        for(int j = 0; j < inventoryplayer.main.length; j++)
        {
            if(inventoryplayer.main[j] != null && inventoryplayer.main[j].itemId == i)
            {
                return j;
            }
        }

        return -1;
    }

//    public static int useItemInHotbar(EntityPlayer entityplayer, int i)
//    {
//        int j = getHotbarSlotContainItem(entityplayer.inventory, i);
//        if(j < 0)
//        {
//            return 0;
//        }
//        if(Item.itemsList[i].getMaxDamage() > 0)
//        {
//            entityplayer.inventory.mainInventory[j].damageItem(1, entityplayer);
//            if(entityplayer.inventory.mainInventory[j].stackSize == 0)
//            {
//                entityplayer.inventory.mainInventory[j] = new ItemStack(Item.bucketEmpty);
//                if(getHotbarSlotContainItem(entityplayer.inventory, i) >= 0)
//                {
//                    return 2;
//                }
//            }
//        } else
//        if(--entityplayer.inventory.mainInventory[j].stackSize <= 0)
//        {
//            entityplayer.inventory.mainInventory[j] = null;
//            if(getHotbarSlotContainItem(entityplayer.inventory, i) >= 0)
//            {
//                return 2;
//            }
//        }
//        return 1;
//    }
//
//    private static int getHotbarSlotContainItem(InventoryPlayer inventoryplayer, int i)
//    {
//        for(int j = 0; j < 9; j++)
//        {
//            if(inventoryplayer.mainInventory[j] != null && inventoryplayer.mainInventory[j].itemID == i)
//            {
//                return j;
//            }
//        }
//
//        return -1;
//    }
//
//    public static boolean onGroundOrInWater(World world, Entity entity)
//    {
//        return entity.onGround || world.handleMaterialAcceleration(entity.boundingBox, Material.water, entity);
//    }
//
//    public static void setUtilityArmour(ItemArmor itemarmor)
//    {
//        try
//        {
//            itemarmor.setMaxDamage(0);
//            ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, itemarmor, 4, Integer.valueOf(0));
//        }
//        catch(NoSuchFieldException nosuchfieldexception)
//        {
//            ModLoader.getLogger().throwing("SdkTools", "setUtilityArmour", nosuchfieldexception);
//            ThrowException("SDK, you forgot to update your obfuscated reflection!", nosuchfieldexception);
//        }
//    }
//
//    public static void replaceInventory(EntityPlayer entityplayer)
//    {
//        try
//        {
//            if(entityplayer.inventory.getClass() == (net.minecraft.src.InventoryPlayer.class))
//            {
//                InventoryPlayer inventoryplayer = entityplayer.inventory;
//                SdkInventoryPlayer sdkinventoryplayer = new SdkInventoryPlayer(entityplayer.inventory);
//                entityplayer.inventory = sdkinventoryplayer;
//                for(int i = 0; i < entityplayer.inventorySlots.slots.size(); i++)
//                {
//                    Slot slot = (Slot)entityplayer.inventorySlots.slots.get(i);
//                    IInventory iinventory = (IInventory)ModLoader.getPrivateValue(net.minecraft.src.Slot.class, slot, 1);
//                    if(iinventory == inventoryplayer)
//                    {
//                        ModLoader.setPrivateValue(net.minecraft.src.Slot.class, slot, 1, sdkinventoryplayer);
//                    }
//                }
//
//                for(int j = 0; j < entityplayer.craftingInventory.slots.size(); j++)
//                {
//                    Slot slot1 = (Slot)entityplayer.craftingInventory.slots.get(j);
//                    IInventory iinventory1 = (IInventory)ModLoader.getPrivateValue(net.minecraft.src.Slot.class, slot1, 1);
//                    if(iinventory1 == inventoryplayer)
//                    {
//                        ModLoader.setPrivateValue(net.minecraft.src.Slot.class, slot1, 1, sdkinventoryplayer);
//                    }
//                }
//
//            }
//        }
//        catch(NoSuchFieldException nosuchfieldexception)
//        {
//            ModLoader.getLogger().throwing("SdkTools", "replaceInventory", nosuchfieldexception);
//            ThrowException("SDK, you forgot to update your obfuscated reflection!", nosuchfieldexception);
//        }
//    }

    private static Method blockFireSetBurnRate = null;
    private static boolean soundsAdded = false;
    public static Minecraft minecraft = Minecraft.class.cast(FabricLoader.getInstance().getGameInstance());;
    public static Random random = new Random();
    private static List recipes;
}
