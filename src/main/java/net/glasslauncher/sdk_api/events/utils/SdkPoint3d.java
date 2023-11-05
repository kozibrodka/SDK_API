package net.glasslauncher.sdk_api.events.utils;

public class SdkPoint3d
{

    public SdkPoint3d(Object obj, Object obj1, Object obj2)
    {
        x = obj;
        y = obj1;
        z = obj2;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        if(!(obj instanceof SdkPoint3d))
        {
            return false;
        } else
        {
            SdkPoint3d sdkpoint3d = (SdkPoint3d)obj;
            return x == sdkpoint3d.x && y == sdkpoint3d.y && z == sdkpoint3d.z;
        }
    }

    public int hashCode()
    {
        int i = 1;
        i = i * 31 + x.hashCode();
        i = i * 31 + y.hashCode();
        i = i * 31 + z.hashCode();
        return i;
    }

    public Object x;
    public Object y;
    public Object z;
}
