package net.kozibrodka.sdk_api.mixin;

import net.kozibrodka.sdk_api.events.utils.WW2Cannon;
import net.kozibrodka.sdk_api.events.utils.WW2Plane;
import net.kozibrodka.sdk_api.events.utils.WW2Tank;
import net.kozibrodka.sdk_api.events.utils.WW2Truck;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerBase.class)
public abstract class MixinPlayerBase extends Living {

    public MixinPlayerBase(Level arg) {
        super(arg);
    }

    @Shadow
    public abstract boolean damage(EntityBase arg, int i);

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerBase;increaseStat(Lnet/minecraft/stat/Stat;I)V"), cancellable = true)
    private void InjectedDMG2(EntityBase arg, int i, CallbackInfoReturnable<Boolean> cir){
        if(this.vehicle instanceof WW2Tank){
            if(((WW2Tank)this.vehicle).getPercentHealth() < 10){
                cir.setReturnValue(damage(arg,i/10));
            }
            cir.setReturnValue(damage(arg,0));
        }
        if(this.vehicle instanceof WW2Plane){
            if(((WW2Plane)this.vehicle).getPercentHealth() < 10){
                cir.setReturnValue(damage(arg,i/5));
            }
            cir.setReturnValue(damage(arg,i/10));
        }
        if(this.vehicle instanceof WW2Truck){
            if(((WW2Truck)this.vehicle).getPercentHealth() < 20){
                cir.setReturnValue(damage(arg,i));
            }
            cir.setReturnValue(damage(arg,i/3));
        }
        if(this.vehicle instanceof WW2Cannon){
            if(((WW2Cannon)this.vehicle).getPercentHealth() < 10){
                cir.setReturnValue(damage(arg,i/2));
            }
            cir.setReturnValue(damage(arg,i/3));
        }
    }
}
