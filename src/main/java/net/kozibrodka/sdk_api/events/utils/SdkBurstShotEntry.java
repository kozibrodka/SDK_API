package net.kozibrodka.sdk_api.events.utils;

import net.minecraft.item.ItemInstance;

public class SdkBurstShotEntry
{

    public SdkBurstShotEntry(int i, ItemInstance itemstack)
    {
        this(i, itemstack, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    }

    public SdkBurstShotEntry(int i, ItemInstance itemstack, float f, float f1, float f2, float f3, float f4)
    {
        burstShots = i;
        itemStack = itemstack;
        xOffset = f;
        yOffset = f1;
        zOffset = f2;
        rotationYawOffset = f3;
        rotationPitchOffset = f4;
    }

    public int burstShots;
    public ItemInstance itemStack;
    public float xOffset;
    public float yOffset;
    public float zOffset;
    public float rotationYawOffset;
    public float rotationPitchOffset;
}
