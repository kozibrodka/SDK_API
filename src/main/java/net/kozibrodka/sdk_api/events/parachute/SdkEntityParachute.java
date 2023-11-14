package net.kozibrodka.sdk_api.events.parachute;

import net.kozibrodka.sdk_api.events.utils.SdkTools;
import net.kozibrodka.sdk_api.mixin.EntityBaseAccessor;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;

public class SdkEntityParachute extends Living
{

    public SdkEntityParachute(Level world)
    {
        super(world);
        justServerSpawned = false;
        texture = "/sdk/mobParachute.png";
        standingEyeHeight = 0.0F;
        health = 4;
    }

    public SdkEntityParachute(Level world, double d, double d1, double d2)
    {
        this(world);
        setPosition(d, d1, d2);
        justServerSpawned = true;
    }

    public SdkEntityParachute(Level world, EntityBase entity)
    {
        this(world);
        method_1362(entity.yaw, 0.0F);
        entityWearing = entity;
        standingEyeHeight = 0.0F;
        health = 4;
        prevX = x;
        prevY = y;
        prevZ = z;
        setMotionAndPosition();
    }

    public boolean shouldRenderAtDistance(double d)
    {
        return true;
    }

    public void tick()
    {
        if(entityWearing == null)
        {
            if(level.isServerSide && !justServerSpawned)
            {
                removed = true;
                return;
            }
            entityWearing = getNearestPlayer();
            justServerSpawned = false;
            if(entityWearing == null)
            {
                return;
            }
        }
        if(SdkTools.onGroundOrInWater(level, entityWearing))
        {
            removed = true;
            return;
        }
        if(entityWearing.velocityY < -0.25D)
        {
            entityWearing.velocityY = -0.25D;
        }
        ((EntityBaseAccessor)entityWearing).setFallDistance(0.0F);
        setMotionAndPosition();
    }

    public PlayerBase getNearestPlayer()
    {
        return getNearestPlayer(x, y, z);
    }

    public PlayerBase getNearestPlayer(double d, double d1, double d2)
    {
        double d3 = -1D;
        PlayerBase entityplayer = null;
        for(int i = 0; i < level.entities.size(); i++)
        {
            EntityBase entity = (EntityBase)level.entities.get(i);
            if(!(entity instanceof PlayerBase) || !entity.isAlive())
            {
                continue;
            }
            double d4 = entity.squaredDistanceTo(d, d1, d2);
            if(d3 == -1D || d4 < d3)
            {
                d3 = d4;
                entityplayer = (PlayerBase) entity;
            }
        }

        return entityplayer;
    }

    private void setMotionAndPosition()
    {
        setPosition(entityWearing.x, entityWearing.y + (double)(entityWearing.height / 2.0F) + 0.0D, entityWearing.z);
        velocityX = entityWearing.velocityX;
        velocityY = entityWearing.velocityY;
        velocityZ = entityWearing.velocityZ;
        yaw = entityWearing.yaw;
    }

    public void onCollideWithPlayer(PlayerBase entityplayer)
    {
    }

    EntityBase entityWearing;
    boolean justServerSpawned;
    private static final float HEIGHT = 0F;
    private static final float MAX_FALL_SPEED = 0.25F;
}
