package net.kozibrodka.sdk_api.events.parachute;

import net.kozibrodka.sdk_api.mixin.EntityBaseAccessor;
import net.kozibrodka.sdk_api.mixin.EntityRendererAccessor;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.entity.EntityBase;
import org.lwjgl.opengl.GL11;

public class SdkRenderParachute extends EntityRenderer
{

    public SdkRenderParachute()
    {
//        ((EntityRendererAccessor)this).setField_2676(new SdkModelParachute());
        field_2678 = 0.0F;
        model = new SdkModelParachute();
//        super.field_2676 = new SdkModelParachute();
    }

    @Override
    public void render(EntityBase entity, double d, double d1, double d2, float f, float f1) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1 + 1, (float)d2);
        bindTexture("/assets/sdk_api/stationapi/textures/block/mobParachute.png");
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        model.render(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F); //0.0625F
        GL11.glPopMatrix();
    }

    //    @Override
//    public void render(EntityBase arg, double d, double e, double f, float g, float h) {
//        model.render(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
//    }
    protected EntityModelBase model;

}
