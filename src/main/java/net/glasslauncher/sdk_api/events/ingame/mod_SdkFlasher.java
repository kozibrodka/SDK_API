package net.glasslauncher.sdk_api.events.ingame;

import net.glasslauncher.sdk_api.events.utils.SdkLitBlock;
import net.glasslauncher.sdk_api.events.utils.SdkPoint3d;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityBase;
import net.minecraft.level.Level;
import net.minecraft.level.LightType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class mod_SdkFlasher {
    public mod_SdkFlasher()
    {
    }

    public boolean OnTickInGameTick(Minecraft minecraft)
    {
        if(minecraft.level != null)
        {
            for(Iterator iterator = litBlocks.entrySet().iterator(); iterator.hasNext();)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                int i = (Integer) entry.getValue() - 1;
                if(i <= 0)
                {
                    unlightBlock(minecraft.level, (SdkLitBlock)entry.getKey());
                    iterator.remove();
                } else
                {
                    entry.setValue(i);
                }
            }

        }
        return true;
    }

    public static void LightEntity(Level world, EntityBase entity, int i, int j)
    {
        LightBlock(world, (int)Math.floor(entity.x), (int)Math.floor(entity.y), (int)Math.floor(entity.z), i, j);
    }

    public static void LightBlock(Level world, int i, int j, int k, int l, int i1)
    {
        LightBlock(world, new SdkPoint3d(i, j, k), l, i1);
    }

    private static void LightBlock(Level world, SdkPoint3d sdkpoint3d, int i, int j)
    {
        clearLitBlocks(world);
        int k = i - 1;
        int l = k * 2 + 1;
        int ai[] = new int[l * l * l];
        for(int i1 = -k; i1 <= k; i1++)
        {
            for(int j1 = -k; j1 <= k; j1++)
            {
                for(int k1 = -k; k1 <= k; k1++)
                {
                    int l1 = (k1 + k) * l * l + (j1 + k) * l + (i1 + k);
                    ai[l1] = -1;
                    if(world.getTileId((Integer) sdkpoint3d.x + i1, (Integer) sdkpoint3d.y + j1, (Integer) sdkpoint3d.z + k1) != 0)
                    {
                        continue;
                    }
                    int i2 = Math.abs(i1) + Math.abs(j1) + Math.abs(k1);
                    int j2 = Math.max(0, i - i2);
                    int k2 = world.placeTile((Integer) sdkpoint3d.x + i1, (Integer) sdkpoint3d.y + j1, (Integer) sdkpoint3d.z + k1);
                    if(j2 > k2)
                    {
                        ai[l1] = k2;
                        world.method_205(LightType.field_2758, (Integer) sdkpoint3d.x + i1, (Integer) sdkpoint3d.y + j1, (Integer) sdkpoint3d.z + k1, j2);
                    }
                }

            }

        }

        SdkLitBlock sdklitblock = new SdkLitBlock(sdkpoint3d, ai, i);
        litBlocks.put(sdklitblock, j);
    }

    private static void clearLitBlocks(Level world)
    {
        for(Iterator iterator = litBlocks.entrySet().iterator(); iterator.hasNext(); iterator.remove())
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            unlightBlock(world, (SdkLitBlock)entry.getKey());
        }

    }

    private static void unlightBlock(Level world, SdkLitBlock sdklitblock)
    {
        SdkPoint3d sdkpoint3d = sdklitblock.getBlockLocation();
        int ai[] = sdklitblock.getLightValues();
        int i = sdklitblock.getLightLevel();
        int j = i - 1;
        int k = j * 2 + 1;
        for(int l = -j; l <= j; l++)
        {
            for(int i1 = -j; i1 <= j; i1++)
            {
                for(int j1 = -j; j1 <= j; j1++)
                {
                    int k1 = (j1 + j) * k * k + (i1 + j) * k + (l + j);
                    if(ai[k1] != -1 && world.getTileId((Integer) sdkpoint3d.x + l, (Integer) sdkpoint3d.y + i1, (Integer) sdkpoint3d.z + j1) == 0)
                    {
                        world.method_205(LightType.field_2758, (Integer) sdkpoint3d.x + l, (Integer) sdkpoint3d.y + i1, (Integer) sdkpoint3d.z + j1, ai[k1]);
                    }
                }

            }

        }

    }

    private static Map litBlocks = new HashMap();
}
