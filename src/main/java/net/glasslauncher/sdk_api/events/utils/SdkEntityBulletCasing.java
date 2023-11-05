package net.glasslauncher.sdk_api.events.utils;

import net.glasslauncher.sdk_api.events.ingame.mod_SdkGuns;
import net.glasslauncher.sdk_api.events.init.ItemCasingListener;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.Box;
import net.minecraft.util.maths.MathHelper;
import net.minecraft.util.maths.Vec3f;

import java.util.List;


public class SdkEntityBulletCasing extends EntityBase {

    public SdkEntityBulletCasing(Level world)
    {
        super(world);
        droppedItem = ItemCasingListener.itemBulletCasing;
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inGround = false;
        timeInAir = 0;
        setSize(0.0625F, 0.03125F);
    }

    public SdkEntityBulletCasing(Level world, double d, double d1, double d2)
    {
        super(world);
        droppedItem = ItemCasingListener.itemBulletCasing;
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inGround = false;
        timeInAir = 0;
        setSize(0.0625F, 0.03125F);
        setPosition(d, d1, d2);
        standingEyeHeight = 0.0F;
    }

    public SdkEntityBulletCasing(Level world, EntityBase entity, float f)
    {
        super(world);
        droppedItem = ItemCasingListener.itemBulletCasing;
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inGround = false;
        timeInAir = 0;
        owner = entity;
        createdByPlayer = owner instanceof PlayerBase;
        setSize(0.0625F, 0.03125F);
        setPositionAndAngles(entity.x, entity.y + (double)entity.getStandingEyeHeight() + (double)f, entity.z, entity.yaw, entity.pitch); // dodatek  - 0.77999997884035D
        if(entity instanceof PlayerBase)
        {
            x -= MathHelper.cos((yaw / 180F) * 3.141593F) * 0.16F;
            y -= 0.10000000149011612D;
            z -= MathHelper.sin((yaw / 180F) * 3.141593F) * 0.16F;
        }
        yaw += 90F;
        if(yaw > 360F)
        {
            yaw -= 360F;
        }
        pitch -= 30F;
        if(pitch < -90F)
        {
            pitch = (pitch + 180F) * -1F;
            yaw *= -1F;
        }
        x -= MathHelper.cos((yaw / 180F) * 3.141593F) * 0.16F;
        y -= 0.10000000149011612D;
        z -= MathHelper.sin((yaw / 180F) * 3.141593F) * 0.16F;
        setPosition(x, y, z);
        standingEyeHeight = 0.0F;
        velocityX = -MathHelper.sin((yaw / 180F) * 3.141593F) * MathHelper.cos((pitch / 180F) * 3.141593F);
        velocityZ = MathHelper.cos((yaw / 180F) * 3.141593F) * MathHelper.cos((pitch / 180F) * 3.141593F);
        velocityY = -MathHelper.sin((pitch / 180F) * 3.141593F);
        setPositionAndRotation(velocityX, velocityY, velocityZ, 0.25F, 1.0F);
    }

    protected void initDataTracker()
    {
    }

    public boolean shouldRenderAtDistance(double d)
    {
        return true;
    }

    public void setPositionAndRotation(double d, double d1, double d2, float f,
                                       float f1)
    {
        float f2 = MathHelper.sqrt(d * d + d1 * d1 + d2 * d2);
        d /= f2;
        d1 /= f2;
        d2 /= f2;
        d += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d1 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d2 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d *= f;
        d1 *= f;
        d2 *= f;
        velocityX = d;
        velocityY = d1;
        velocityZ = d2;
        float f3 = MathHelper.sqrt(d * d + d2 * d2);
        prevYaw = yaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
        prevPitch = pitch = (float)((Math.atan2(d1, f3) * 180D) / 3.1415927410125732D);
        timeInTile = 0;
    }

    public void tick()
    {
        super.tick();
        if(inGround)
        {
            if(!(owner instanceof PlayerBase) && !level.isServerSide)
            {
                remove();
            }
            int i = level.getTileId(xTile, yTile, zTile);
            if(i != inTile)
            {
                inGround = false;
                velocityX *= rand.nextFloat() * 0.2F;
                velocityY *= rand.nextFloat() * 0.2F;
                velocityZ *= rand.nextFloat() * 0.2F;
                timeInTile = 0;
                timeInAir = 0;
            } else
            {
                timeInTile++;
                if(timeInTile == 1200)
                {
                    remove();
                }
                return;
            }
        } else
        {
            timeInAir++;
        }
        Vec3f vec3d = Vec3f.from(x, y, z);
        Vec3f vec3d1 = Vec3f.from(x + velocityX, y + velocityY, z + velocityZ);
        HitResult movingobjectposition = level.method_160(vec3d, vec3d1);
        vec3d = Vec3f.from(x, y, z);
        vec3d1 = Vec3f.from(x + velocityX, y + velocityY, z + velocityZ);
        if(movingobjectposition != null)
        {
            vec3d1 = Vec3f.from(movingobjectposition.field_1988.x, movingobjectposition.field_1988.y, movingobjectposition.field_1988.z);
        }
        EntityBase entity = null;
        List list = level.getEntities(this, boundingBox.method_86(velocityX, velocityY, velocityZ).expand(1.0D, 1.0D, 1.0D));
        double d = 0.0D;
        for(int j = 0; j < list.size(); j++)
        {
            EntityBase entity1 = (EntityBase)list.get(j);
            if(!entity1.method_1356() || entity1 == owner && timeInAir < 5)
            {
                continue;
            }
            float f3 = 0.3F;
            Box axisalignedbb = entity1.boundingBox.expand(f3, f3, f3);
            HitResult movingobjectposition1 = axisalignedbb.method_89(vec3d, vec3d1);
            if(movingobjectposition1 == null)
            {
                continue;
            }
            double d2 = vec3d.method_1294(movingobjectposition1.field_1988);
            if(d2 < d || d == 0.0D)
            {
                entity = entity1;
                d = d2;
            }
        }

        if(entity != null)
        {
            movingobjectposition = new HitResult(entity);
        }
        if(movingobjectposition != null && movingobjectposition.field_1989 == null)
        {
            xTile = movingobjectposition.x;
            yTile = movingobjectposition.y;
            zTile = movingobjectposition.z;
            inTile = level.getTileId(xTile, yTile, zTile);
            velocityX = (float)(movingobjectposition.field_1988.x - x);
            velocityY = (float)(movingobjectposition.field_1988.y - y);
            velocityZ = (float)(movingobjectposition.field_1988.z - z);
            float f = MathHelper.sqrt(velocityX * velocityX + velocityY * velocityY + velocityZ * velocityZ);
            double d1 = 0.025000000000000001D;
            x -= (velocityX / (double)f) * d1;
            y -= (velocityY / (double)f) * d1;
            z -= (velocityZ / (double)f) * d1;
            inGround = true;
        }
        x += velocityX;
        y += velocityY;
        z += velocityZ;
        float f1 = MathHelper.sqrt(velocityX * velocityX + velocityZ * velocityZ);
        yaw = (float)((Math.atan2(velocityX, velocityZ) * 180D) / 3.1415927410125732D);
        for(pitch = (float)((Math.atan2(velocityY, f1) * 180D) / 3.1415927410125732D); pitch - prevPitch < -180F; prevPitch -= 360F) { }
        for(; pitch - prevPitch >= 180F; prevPitch += 360F) { }
        for(; yaw - prevYaw < -180F; prevYaw -= 360F) { }
        for(; yaw - prevYaw >= 180F; prevYaw += 360F) { }
        pitch = prevPitch + (pitch - prevPitch) * 0.2F;
        yaw = prevYaw + (yaw - prevYaw) * 0.2F;
        float f2 = 0.99F;
        float f4 = 0.1F;
        if(method_1334())
        {
            for(int k = 0; k < 4; k++)
            {
                float f5 = 0.25F;
                level.addParticle("bubble", x - velocityX * (double)f5, y - velocityY * (double)f5, z - velocityZ * (double)f5, velocityX, velocityY, velocityZ);
            }

            f2 = 0.8F;
        }
        velocityX *= f2;
        velocityY *= f2;
        velocityZ *= f2;
        velocityY -= f4;
        setPosition(x, y, z);
    }

    public void writeCustomDataToTag(CompoundTag nbttagcompound)
    {
        nbttagcompound.put("xTile", (short)xTile);
        nbttagcompound.put("yTile", (short)yTile);
        nbttagcompound.put("zTile", (short)zTile);
        nbttagcompound.put("inTile", (byte)inTile);
        nbttagcompound.put("inGround", (byte)(inGround ? 1 : 0));
        nbttagcompound.put("createdByPlayer", (byte)(createdByPlayer ? 1 : 0));
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound)
    {
        xTile = nbttagcompound.getShort("xTile");
        yTile = nbttagcompound.getShort("yTile");
        zTile = nbttagcompound.getShort("zTile");
        inTile = nbttagcompound.getByte("inTile") & 0xff;
        inGround = nbttagcompound.getByte("inGround") == 1;
        createdByPlayer = nbttagcompound.getByte("createdByPlayer") == 1;
    }

    public void onPlayerCollision(PlayerBase entityplayer)
    {
        if(level.isServerSide)
        {
            return;
        }
        if(mod_SdkGuns.ammoRestrictions && droppedItem != null && createdByPlayer && timeInTile > 5 && inGround && entityplayer.inventory.addStack(new ItemInstance(droppedItem.id, 1, 0)))
        {
            level.playSound(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            entityplayer.onItemPickup(this, 1);
            remove();
        }
    }

    public float getEyeHeight()
    {
        return 0.0F;
    }

    public void remove()
    {
        super.remove();
        owner = null;
    }

    public ItemBase droppedItem;
    private int xTile;
    private int yTile;
    private int zTile;
    private int inTile;
    private boolean inGround;
    public EntityBase owner;
    private int timeInTile;
    private int timeInAir;
    private boolean createdByPlayer;

}
