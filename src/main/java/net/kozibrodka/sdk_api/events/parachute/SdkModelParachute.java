package net.kozibrodka.sdk_api.events.parachute;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;

public class SdkModelParachute extends EntityModelBase
{

    public SdkModelParachute()
    {
        top = new Cuboid(0, 0);
        top.method_1817(-8F, 8F, -8F, 16, 1, 16);
        side1 = new Cuboid(0, 0);
        side1.method_1817(-8F, 8F, -8F, 16, 16, 1);
        side2 = new Cuboid(0, 0);
        side2.method_1817(-8F, 8F, 7F, 16, 16, 1);
        side3 = new Cuboid(0, 0);
        side3.method_1817(-8F, 8F, -8F, 1, 16, 16);
        side4 = new Cuboid(0, 0);
        side4.method_1817(7F, 8F, -8F, 1, 16, 16);
        models = (new Cuboid[] {
                top, side1, side2, side3, side4
        });
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        for(int i = 0; i < models.length; i++)
        {
            models[i].yaw = f3 / 57.29578F;
            models[i].pitch = f4 / 57.29578F;
        }

    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5);
        for(int i = 0; i < models.length; i++)
        {
            models[i].method_1815(f5);
        }

    }

    Cuboid top;
    Cuboid side1;
    Cuboid side2;
    Cuboid side3;
    Cuboid side4;
    Cuboid models[];
}
