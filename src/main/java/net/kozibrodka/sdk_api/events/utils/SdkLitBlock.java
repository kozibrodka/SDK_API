package net.kozibrodka.sdk_api.events.utils;

public class SdkLitBlock
{

    public SdkLitBlock(SdkPoint3d sdkpoint3d, int ai[], int i)
    {
        blockLocation = sdkpoint3d;
        lightValues = ai;
        lightLevel = i;
    }

    public SdkPoint3d getBlockLocation()
    {
        return blockLocation;
    }

    public int[] getLightValues()
    {
        return lightValues;
    }

    public int getLightLevel()
    {
        return lightLevel;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof SdkLitBlock)
        {
            return blockLocation.equals(((SdkLitBlock)obj).blockLocation);
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return blockLocation.hashCode();
    }

    private SdkPoint3d blockLocation;
    private int lightValues[];
    private int lightLevel;
}
