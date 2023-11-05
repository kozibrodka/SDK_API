package net.glasslauncher.sdkAPI.mixin;

import net.glasslauncher.sdkAPI.events.ingame.mod_SdkFlasher;
import net.glasslauncher.sdkAPI.events.ingame.mod_SdkGuns;
import net.glasslauncher.sdkAPI.events.ingame.mod_SdkUtility;
import net.minecraft.client.Minecraft;
import net.minecraft.sortme.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Shadow
    protected Minecraft minecraft;

    @Inject(method = "method_1844", at = @At(value = "RETURN"))
    private void sdk_mod_tick(CallbackInfo ci) {
        if(minecraft.level != null) {
            this.sdk_mod_tick(this.minecraft);
        }
    }

    public void sdk_mod_tick(Minecraft minecraft)
    {
        mod1.OnTickInGame(minecraft);
        mod2.OnTickInGameTick(minecraft);
        mod3.OnTickInGame(minecraft);
    }

    mod_SdkGuns mod1 = new mod_SdkGuns();
    mod_SdkFlasher mod2 = new mod_SdkFlasher();
    mod_SdkUtility mod3 = new mod_SdkUtility();
}
