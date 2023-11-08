package net.kozibrodka.sdk_api.mixin;

import net.minecraft.entity.Living;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Living.class)
public interface LivingAccessor {

    @Accessor
    void setMovementSpeed(float movementspeed);

    @Accessor
    int getField_1009();

    @Accessor
    int getField_1058();

    @Accessor
    float getMovementSpeed();


}
