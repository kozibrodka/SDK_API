package net.kozibrodka.sdk_api.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Minecraft.class)
public interface MinecraftClassAccessor {

    @Accessor
    void setAttackCooldown(int cooldown);
}
