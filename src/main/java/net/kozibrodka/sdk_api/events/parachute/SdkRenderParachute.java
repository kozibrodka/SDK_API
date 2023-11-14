package net.kozibrodka.sdk_api.events.parachute;

import net.kozibrodka.sdk_api.mixin.EntityBaseAccessor;
import net.kozibrodka.sdk_api.mixin.EntityRendererAccessor;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.EntityBase;

public class SdkRenderParachute extends EntityRenderer
{

    public SdkRenderParachute()
    {
        ((EntityRendererAccessor)this).setField_2676(new SdkModelParachute());
//        super.field_2676 = new SdkModelParachute();
    }

    @Override
    public void render(EntityBase arg, double d, double e, double f, float g, float h) {
//        model.render(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    }
    protected SdkModelParachute model;

}
