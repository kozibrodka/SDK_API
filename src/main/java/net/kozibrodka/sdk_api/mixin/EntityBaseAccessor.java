package net.kozibrodka.sdk_api.mixin;

import net.minecraft.entity.EntityBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Random;

@Mixin(EntityBase.class)
public interface EntityBaseAccessor {

    @Accessor
    Random getRand();

    @Accessor
    int getField_1613();

    @Invoker
    void invokeSetRotation(float f, float g);

}
