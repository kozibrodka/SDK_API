package net.kozibrodka.sdk_api.events.utils;


import net.kozibrodka.sdk_api.events.ingame.mod_SdkFlasher;
import net.kozibrodka.sdk_api.events.ingame.mod_SdkGuns;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;


public abstract class SdkItemGun extends SdkItemCustomUseDelay {

    public SdkItemGun(Identifier i)
    {
        super(i);
        numBullets = 1;
        burstShots = 0;
        damage = 0;
        muzzleVelocity = 1.5F;
        spread = 1.0F;
        recoil = 1.0F;
        soundDelay = -1;
        soundRangeFactor = 4F;
        lastSound = 0L;
        lastEmptySound = 0L;
        maxStackSize = 1;
        penetration = 0;
    }

    public int getAttack(EntityBase entity)
    {
        return 4;
    }

    public boolean useOryginal(ItemInstance itemstack, Level world, EntityBase entity, float f, float f1, float f2, float f3,
                       float f4)
    {
        return fireBullet(world, entity, itemstack, false, f, f1, f2, f3, f4);
    }

    public boolean fireBullet(Level world, EntityBase entity, ItemInstance itemstack, boolean flag, float f, float f1, float f2,
                              float f3, float f4)
    {
        if(!mod_SdkGuns.reloadTimes.containsKey(entity))
        {
            int i;
            if(entity instanceof PlayerBase)
            {
                i = SdkTools.useItemInInventory((PlayerBase)entity, requiredBullet.id);
            } else
            if(entity.passenger != null && (entity.passenger instanceof PlayerBase))
            {
                if(!(entity instanceof WW2Cannon || entity instanceof WW2Plane || entity instanceof WW2Tank)) { //addon
                    i = SdkTools.useItemInInventory((PlayerBase) entity.passenger, requiredBullet.id);
                }else{
                    i = 1;
                }
            } else
            {
                i = 1;
            }
            if(i > 0)
            {
                if(world.getLevelTime() - lastSound < 0L)
                {
                    lastSound = world.getLevelTime() - (long)soundDelay;
                }
                if(soundDelay == 0 || lastSound == 0L || world.getLevelTime() - lastSound > (long)soundDelay)
                {
                    world.playSound(entity, firingSound, 1.0F, 1.0F / (rand.nextFloat() * 0.1F + 0.95F));
                    lastSound = world.getLevelTime();
                }
                if(!world.isServerSide)
                {
                    for(int j = 0; j < numBullets; j++)
                    {
                        SdkEntityBullet sdkentitybullet = getBulletEntity(world, entity, f, f1, f2, f3, f4);
                        if(sdkentitybullet != null)
                        {
                            world.spawnEntity(sdkentitybullet);
                        }
                    }

                    SdkEntityBulletCasing sdkentitybulletcasing = getBulletCasingEntity(world, entity, f1);
                    if((entity instanceof PlayerBase) && mod_SdkGuns.ammoCasings && sdkentitybulletcasing != null)
                    {
                        world.spawnEntity(sdkentitybulletcasing);
                    }
                    if(mod_SdkGuns.muzzleFlash)
                    {
                        mod_SdkFlasher.LightEntity(world, entity, 15, 2); //MUZZLE FLASH, na poxniej
                    }
                    if(!flag && burstShots > 0)
                    {
                        mod_SdkGuns.burstShots.put(entity, new SdkBurstShotEntry(burstShots, itemstack));
                    }
                }
                if(entity instanceof PlayerBase)
                {
                    double d = Math.min(recoil, entity.pitch + 90F);
                    double d1 = world.rand.nextFloat() * recoil * 0.5F - recoil * 0.25F;
                    if(!entity.method_1373()) //isSneaking
                    {
                        d *= 2D;
                        d1 *= 2D;
                        if(SdkMap.minigunList.contains(this))
                        {
                            d *= 2D;
                            d1 *= 2D;
                        }
                    }
                    if(!entity.onGround)
                    {
                        d *= 2D;
                        d1 *= 2D;
                    }
                    mod_SdkGuns.currentRecoilV += d;
                    mod_SdkGuns.currentRecoilH += d1;
                    entity.pitch -= d;
                    entity.yaw += d1;
                    if(i == 2 && !(SdkMap.noReloadList.contains(itemstack.getType())))
                    {
                        mod_SdkGuns.handleReload(world, (PlayerBase)entity, true);
                    }
                }
                return true;
            }
            if(lastEmptySound == 0L || world.getLevelTime() - lastEmptySound > 20L)
            {
                world.playSound(entity, "sdk_api:gunempty", 1.0F, 1.0F / (rand.nextFloat() * 0.1F + 0.95F)); //TODO: można to REWRITE
                lastEmptySound = world.getLevelTime();
            }
        }
        return false;
    }

    public boolean isRendered3d()
    {
        return true;
    }

    public abstract SdkEntityBullet getBulletEntity(Level world, EntityBase entity, float f, float f1, float f2, float f3, float f4);

    public abstract SdkEntityBulletCasing getBulletCasingEntity(Level world, EntityBase entity, float f);

    public String firingSound;
    public ItemBase requiredBullet;
    public int numBullets;
    public int burstShots;
    public int damage;
    public float muzzleVelocity;
    public float spread;
    public float recoil;
    public int soundDelay;
    public float soundRangeFactor;
    protected long lastSound;
    protected long lastEmptySound;
    /**
     * penetration:
     *  0 - no damage to vehicles
     *  1 - damages cars, and undermentioned
     *  2 - damages planes, and undermentioned
     *  3 - damages tank, and undermentioned
     */
    public int penetration;
}
