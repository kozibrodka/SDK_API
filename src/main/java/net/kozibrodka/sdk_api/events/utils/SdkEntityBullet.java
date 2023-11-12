package net.kozibrodka.sdk_api.events.utils;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.sdk_api.events.ingame.mod_SdkGuns;
import net.minecraft.block.BlockBase;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.monster.MonsterEntityType;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.Box;
import net.minecraft.util.maths.MathHelper;
import net.minecraft.util.maths.Vec3f;

import java.util.List;

public abstract class SdkEntityBullet extends EntityBase {

    public SdkEntityBullet(Level world) {
        super(world);
        soundRangeFactor = 8F;
        serverSoundPlayed = false;
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inGround = false;
        timeInAir = 0;
        setSize(0.0625F, 0.03125F);
    }

    public SdkEntityBullet(Level world, double d, double d1, double d2) {
        this(world);
        setPosition(d, d1, d2);
        standingEyeHeight = 0.0F;
        serverSpawned = true;
    }

    public abstract void playServerSound(Level world);

    public SdkEntityBullet(Level world, EntityBase entity, SdkItemGun sdkitemgun, float f, float f1, float f2, float f3,
                           float f4) {
        this(world);
        owner = entity;
        damage = sdkitemgun.damage;
        float f5 = entity.yaw;
        float f6 = f5 * 0.01745329F;
        double d = f * MathHelper.cos(f6) - f2 * MathHelper.sin(f6);
        double d1 = f * MathHelper.sin(f6) + f2 * MathHelper.cos(f6);
        setPositionAndAngles(entity.x + d, entity.y + (double) entity.getStandingEyeHeight() + (double) f1, entity.z + d1, entity.yaw + f3, entity.pitch + f4);   //dodatek  - 0.77999997884035D
        x -= MathHelper.cos((yaw / 180F) * 3.141593F) * 0.16F;
        y -= 0.10000000149011612D;
        z -= MathHelper.sin((yaw / 180F) * 3.141593F) * 0.16F;
        setPosition(x, y, z); //y - 0.8 naprawia
        standingEyeHeight = 0.0F;
        float f7 = sdkitemgun.spread;
        if (entity instanceof Living) {
            if (entity instanceof PlayerBase) {
                float f8 = sdkitemgun.recoil / (float) sdkitemgun.useDelay;
                float f9 = f8 / 0.1F;
                if (f9 > 0.0F) {
                    f7 = (float) ((double) f7 * (1.0D + mod_SdkGuns.currentRecoilV / (double) f9));
                }
            }
            boolean flag = Math.abs(entity.velocityX) > 0.10000000000000001D || Math.abs(entity.velocityY) > 0.10000000000000001D || Math.abs(entity.velocityZ) > 0.10000000000000001D;
            if (flag) {
                f7 *= 2.0F;
                if (SdkMap.minigunList.contains(sdkitemgun)) {
                    f7 *= 2.0F;
                }
            }
            if (!entity.onGround) {
                f7 *= 2.0F;
                if (SdkMap.minigunList.contains(sdkitemgun)) {
                    f7 *= 2.0F;
                }
            }
            if ((entity instanceof PlayerBase) && (SdkMap.sniperList.contains(sdkitemgun))) {
                PlayerBase entityplayer = (PlayerBase) entity;
                if (flag) {
                    f7 = (float) ((double) f7 + 0.25D);
                }
                if (!entity.onGround) {
                    f7 = (float) ((double) f7 + 0.25D);
                }
                if (!entityplayer.method_1373()) {    //isSneaking
                    f7 = (float) ((double) f7 + 0.25D);
                }
                if (!mod_SdkGuns.getSniperZoomedIn()) {
                    f7 = 8F;
                }
            }
        }
        if (entity.passenger != null && (entity instanceof PlayerBase)) {
            owner = entity.passenger;
        }
        velocityX = -MathHelper.sin((yaw / 180F) * 3.141593F) * MathHelper.cos((pitch / 180F) * 3.141593F);
        velocityZ = MathHelper.cos((yaw / 180F) * 3.141593F) * MathHelper.cos((pitch / 180F) * 3.141593F);
        velocityY = -MathHelper.sin((pitch / 180F) * 3.141593F);
        setBulletHeading(velocityX, velocityY, velocityZ, sdkitemgun.muzzleVelocity, f7 / 2.0F);
        double d2 = 0.0D;
        double d3 = 0.0D;
        double d4 = 0.0D;
        if (entity.vehicle != null) {
            d2 = entity.vehicle.velocityX;
            d3 = entity.vehicle.onGround ? 0.0D : entity.vehicle.velocityY;
            d4 = entity.vehicle.velocityZ;
        } else if (entity.passenger != null) {
            d2 = entity.velocityX;
            d3 = entity.onGround ? 0.0D : entity.velocityY;
            d4 = entity.velocityZ;
        }
        velocityX += d2;
        velocityY += d3;
        velocityZ += d4;
    }

    protected void initDataTracker() {
    }

    public void setBulletHeading(double d, double d1, double d2, float f,
                                 float f1) {
        float f2 = MathHelper.sqrt(d * d + d1 * d1 + d2 * d2);
        d /= f2;
        d1 /= f2;
        d2 /= f2;
        d += rand.nextGaussian() * 0.0074999998323619366D * (double) f1;
        d1 += rand.nextGaussian() * 0.0074999998323619366D * (double) f1;
        d2 += rand.nextGaussian() * 0.0074999998323619366D * (double) f1;
        d *= f;
        d1 *= f;
        d2 *= f;
        velocityX = d;
        velocityY = d1;
        velocityZ = d2;
        float f3 = MathHelper.sqrt(d * d + d2 * d2);
        prevYaw = yaw = (float) ((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
        prevPitch = pitch = (float) ((Math.atan2(d1, f3) * 180D) / 3.1415927410125732D);
        timeInTile = 0;
    }

    public boolean shouldRenderAtDistance(double d) {
        return true;
    }

    public static Minecraft mc = Minecraft.class.cast(FabricLoader.getInstance().getGameInstance()); //proba zamiast  ModLoader.getMinecraftInstance().thePlayer

    public void tick() {
        super.tick();
        if (serverSpawned && !serverSoundPlayed && owner != mc.player) {
            playServerSound(level);
            serverSoundPlayed = true;
        }
        if (timeInAir == 200) {
            remove();
        }
        if (prevPitch == 0.0F && prevYaw == 0.0F) {
            float f = MathHelper.sqrt(velocityX * velocityX + velocityZ * velocityZ);
            prevYaw = yaw = (float) ((Math.atan2(velocityX, velocityZ) * 180D) / 3.1415927410125732D);
            prevPitch = pitch = (float) ((Math.atan2(velocityY, f) * 180D) / 3.1415927410125732D);
        }
        if (inGround) {
            int i = level.getTileId(xTile, yTile, zTile);
            if (i != inTile) {
                inGround = false;
                velocityX *= rand.nextFloat() * 0.2F;
                velocityY *= rand.nextFloat() * 0.2F;
                velocityZ *= rand.nextFloat() * 0.2F;
                timeInTile = 0;
                timeInAir = 0;
            } else {
                timeInTile++;
                if (timeInTile == 200) {
                    remove();
                }
                return;
            }
        } else {
            timeInAir++;
        }
        Vec3f vec3d = Vec3f.from(x, y, z);
        Vec3f vec3d1 = Vec3f.from(x + velocityX, y + velocityY, z + velocityZ);
        HitResult movingobjectposition = level.method_160(vec3d, vec3d1);  //raytraceblocks
        vec3d = Vec3f.from(x, y, z);
        vec3d1 = Vec3f.from(x + velocityX, y + velocityY, z + velocityZ);
        if (movingobjectposition != null) {
            vec3d1 = Vec3f.from(movingobjectposition.field_1988.x, movingobjectposition.field_1988.y, movingobjectposition.field_1988.z);
        }
        EntityBase entity = null;
        List list = level.getEntities(this, boundingBox.method_86(velocityX, velocityY, velocityZ).expand(1.0D, 1.0D, 1.0D));
        double d = 0.0D;
        for (int j = 0; j < list.size(); j++) {
            EntityBase entity1 = (EntityBase) list.get(j);
            if (!entity1.method_1356() || (entity1 == owner || owner != null && entity1 == owner.vehicle || owner != null && entity1 == owner.passenger) && timeInAir < 5 || serverSpawned) {
                continue;
            }
            float f4 = 0.3F;
            Box axisalignedbb = entity1.boundingBox.expand(f4, f4, f4);
            HitResult movingobjectposition1 = axisalignedbb.method_89(vec3d, vec3d1);
            if (movingobjectposition1 == null) {
                continue;
            }
            double d1 = vec3d.method_1294(movingobjectposition1.field_1988);
            if (d1 < d || d == 0.0D) {
                entity = entity1;
                d = d1;
            }
        }

        if (entity != null) {
            movingobjectposition = new HitResult(entity);
        }
        if (movingobjectposition != null) {
            int k = level.getTileId(movingobjectposition.x, movingobjectposition.y, movingobjectposition.z);
            if (movingobjectposition.field_1989 != null || k != BlockBase.TALLGRASS.id) {
                if (movingobjectposition.field_1989 != null) {
                    int l = damage;
                    if ((owner instanceof MonsterEntityType) && (movingobjectposition.field_1989 instanceof PlayerBase)) {
                        if (level.difficulty == 0) {
                            l = 0;
                        }
                        if (level.difficulty == 1) {
                            l = l / 3 + 1;
                        }
                        if (level.difficulty == 3) {
                            l = (l * 3) / 2;
                        }
                    }
                    if (movingobjectposition.field_1989 instanceof Living) {
                        SdkTools.attackEntityIgnoreDelay((Living) movingobjectposition.field_1989, owner, l);
                    } else {
                        if(SdkMap.pojazdList.contains(movingobjectposition.field_1989))
                        {
                            movingobjectposition.field_1989.damage(this, l);
                        }else {
                            movingobjectposition.field_1989.damage(owner, l);
                        }
                    }
                } else {
                    xTile = movingobjectposition.x;
                    yTile = movingobjectposition.y;
                    zTile = movingobjectposition.z;
                    inTile = k;
                    velocityX = (float) (movingobjectposition.field_1988.x - x);
                    velocityY = (float) (movingobjectposition.field_1988.y - y);
                    velocityZ = (float) (movingobjectposition.field_1988.z - z);
                    float f2 = MathHelper.sqrt(velocityX * velocityX + velocityY * velocityY + velocityZ * velocityZ);
                    x -= (velocityX / (double) f2) * 0.05000000074505806D;
                    y -= (velocityY / (double) f2) * 0.05000000074505806D;
                    z -= (velocityZ / (double) f2) * 0.05000000074505806D;
                    inGround = true;
                    if (inTile == BlockBase.GLASS.id && mod_SdkGuns.bulletsDestroyGlass) {
                        SdkTools.minecraft.particleManager.addTileBreakParticles(xTile, yTile, zTile, BlockBase.GLASS.id & 0xff, BlockBase.GLASS.id >> 8 & 0xff);
                        SdkTools.minecraft.soundHelper.playSound(BlockBase.GLASS.sounds.getBreakSound(), (float) xTile + 0.5F, (float) yTile + 0.5F, (float) zTile + 0.5F, (BlockBase.GLASS.sounds.getVolume() + 1.0F) / 2.0F, BlockBase.GLASS.sounds.getPitch() * 0.8F);
                        level.setTile(xTile, yTile, zTile, 0);
                        BlockBase.GLASS.activate(level, xTile, yTile, zTile, level.getTileMeta(xTile, yTile, zTile));
                    }
                    if (inTile == BlockBase.LEAVES.id && mod_SdkGuns.bulletsDestroyGlass) {
                        SdkTools.minecraft.particleManager.addTileBreakParticles(xTile, yTile, zTile, BlockBase.LEAVES.id & 0xff, BlockBase.LEAVES.id >> 8 & 0xff);
                        SdkTools.minecraft.soundHelper.playSound(BlockBase.LEAVES.sounds.getBreakSound(), (float) xTile + 0.5F, (float) yTile + 0.5F, (float) zTile + 0.5F, (BlockBase.LEAVES.sounds.getVolume() + 1.0F) / 2.0F, BlockBase.LEAVES.sounds.getPitch() * 0.8F);
                        level.setTile(xTile, yTile, zTile, 0);
                        BlockBase.LEAVES.activate(level, xTile, yTile, zTile, level.getTileMeta(xTile, yTile, zTile));
                    }
                }
                if(SdkMap.hitsoundList.contains(this)) //TODO: można by to jakoś REWRITE?
                {
                    level.playSound(this, "ofensywa:bullethit", 1.0F, 1.2F / (rand.nextFloat() * 0.1F + 0.9F)); //oryginalna glosnosc 1.0
                }else{
                    level.playSound(this, "ofensywa:impact", 0.8F, 1.0F / (rand.nextFloat() * 0.1F + 0.95F));  //oryginalna glosnosc 0.2
                }
                remove();
            }
        }
        x += velocityX;
        y += velocityY;
        z += velocityZ;
        float f1 = MathHelper.sqrt(velocityX * velocityX + velocityZ * velocityZ);
        yaw = (float) ((Math.atan2(velocityX, velocityZ) * 180D) / 3.1415927410125732D);
        for (pitch = (float) ((Math.atan2(velocityY, f1) * 180D) / 3.1415927410125732D); pitch - prevPitch < -180F; prevPitch -= 360F) {
        }
        for (; pitch - prevPitch >= 180F; prevPitch += 360F) {
        }
        for (; yaw - prevYaw < -180F; prevYaw -= 360F) {
        }
        for (; yaw - prevYaw >= 180F; prevYaw += 360F) {
        }
        pitch = prevPitch + (pitch - prevPitch) * 0.2F;
        yaw = prevYaw + (yaw - prevYaw) * 0.2F;
        float f3 = 1.0F;
        float f5 = 0.005F;   //oryginal 0.0F; -opad
        if (method_1334()) {
            for (int i1 = 0; i1 < 4; i1++) {
                float f6 = 0.25F;
                level.addParticle("bubble", x - velocityX * (double) f6, y - velocityY * (double) f6, z - velocityZ * (double) f6, velocityX, velocityY, velocityZ);
            }

            f3 = 0.8F;
            f5 = 0.03F;
        }
        velocityX *= f3;
        velocityY *= f3;
        velocityZ *= f3;
        velocityY -= f5;
        setPosition(x, y, z);
    }

    public void writeCustomDataToTag(CompoundTag nbttagcompound) {
        nbttagcompound.put("xTile", (short) xTile);
        nbttagcompound.put("yTile", (short) yTile);
        nbttagcompound.put("zTile", (short) zTile);
        nbttagcompound.put("inTile", (byte) inTile);
        nbttagcompound.put("inGround", (byte) (inGround ? 1 : 0));
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound) {
        xTile = nbttagcompound.getShort("xTile");
        yTile = nbttagcompound.getShort("yTile");
        zTile = nbttagcompound.getShort("zTile");
        inTile = nbttagcompound.getByte("inTile") & 0xff;
        inGround = nbttagcompound.getByte("inGround") == 1;
    }

    public float getEyeHeight() {
        return 0.0F;
    }

    public void remove() {
        super.remove();
        owner = null;
    }

    protected int xTile;
    protected int yTile;
    protected int zTile;
    protected int inTile;
    protected boolean inGround;
    public EntityBase owner;
    protected int timeInTile;
    protected int timeInAir;
    protected int damage;
    protected boolean serverSpawned;
    protected String firingSound;
    protected float soundRangeFactor;
    protected boolean serverSoundPlayed;
}
