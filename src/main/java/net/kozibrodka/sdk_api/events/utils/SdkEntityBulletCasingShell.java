package net.kozibrodka.sdk_api.events.utils;

import net.kozibrodka.sdk_api.events.init.ItemCasingListener;
import net.minecraft.entity.EntityBase;
import net.minecraft.level.Level;

public class SdkEntityBulletCasingShell extends SdkEntityBulletCasing {

    public SdkEntityBulletCasingShell(Level world)
    {
        super(world);
        droppedItem = null;
    }

    public SdkEntityBulletCasingShell(Level world, double d, double d1, double d2)
    {
        super(world, d, d1, d2);
    }

    public SdkEntityBulletCasingShell(Level world, EntityBase entity, float f)
    {
        super(world, entity, f);
        droppedItem = ItemCasingListener.itemBulletCasingShell;
    }

}
