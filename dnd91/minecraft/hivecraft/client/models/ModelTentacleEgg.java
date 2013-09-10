package mods.dnd91.minecraft.hivecraft.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTentacleEgg extends ModelBase
{
  //fields
    ModelRenderer bottom;
    ModelRenderer body;
    ModelRenderer fmtent;
    ModelRenderer frtent;
    ModelRenderer fltent;
    ModelRenderer bmtent;
    ModelRenderer bltent;
    ModelRenderer brtent;
    ModelRenderer rmtent;
    ModelRenderer lmtent;
  
  public ModelTentacleEgg()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      bottom = new ModelRenderer(this, 4, 0);
      bottom.addBox(-3F, 0F, -3F, 6, 1, 6);
      bottom.setRotationPoint(0F, 23F, 0F);
      bottom.setTextureSize(64, 32);
      bottom.mirror = true;
      setRotation(bottom, 0F, 0F, 0F);
      body = new ModelRenderer(this, 0, 7);
      body.addBox(-4F, 0F, -4F, 8, 9, 8);
      body.setRotationPoint(0F, 14F, 0F);
      body.setTextureSize(64, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      fmtent = new ModelRenderer(this, 0, 0);
      fmtent.addBox(-0.5F, -2F, -1F, 1, 2, 1);
      fmtent.setRotationPoint(0F, 14F, -3F);
      fmtent.setTextureSize(64, 32);
      fmtent.mirror = true;
      setRotation(fmtent, 0F, 0F, 0F);
      frtent = new ModelRenderer(this, 0, 0);
      frtent.addBox(0F, -2F, -1F, 1, 2, 1);
      frtent.setRotationPoint(3F, 14F, -3F);
      frtent.setTextureSize(64, 32);
      frtent.mirror = true;
      setRotation(frtent, 0F, 0F, 0F);
      fltent = new ModelRenderer(this, 0, 0);
      fltent.addBox(-1F, -2F, -1F, 1, 2, 1);
      fltent.setRotationPoint(-3F, 14F, -3F);
      fltent.setTextureSize(64, 32);
      fltent.mirror = true;
      setRotation(fltent, 0F, 0F, 0F);
      bmtent = new ModelRenderer(this, 0, 0);
      bmtent.addBox(-0.5F, -2F, 0F, 1, 2, 1);
      bmtent.setRotationPoint(0F, 14F, 3F);
      bmtent.setTextureSize(64, 32);
      bmtent.mirror = true;
      setRotation(bmtent, 0F, 0F, 0F);
      bltent = new ModelRenderer(this, 0, 0);
      bltent.addBox(0F, -2F, 0F, 1, 2, 1);
      bltent.setRotationPoint(3F, 14F, 3F);
      bltent.setTextureSize(64, 32);
      bltent.mirror = true;
      setRotation(bltent, 0F, 0F, 0F);
      brtent = new ModelRenderer(this, 0, 0);
      brtent.addBox(-1F, -2F, 0F, 1, 2, 1);
      brtent.setRotationPoint(-3F, 14F, 3F);
      brtent.setTextureSize(64, 32);
      brtent.mirror = true;
      setRotation(brtent, 0F, 0F, 0F);
      rmtent = new ModelRenderer(this, 0, 0);
      rmtent.addBox(-1F, -2F, -0.5F, 1, 2, 1);
      rmtent.setRotationPoint(-3F, 14F, 0F);
      rmtent.setTextureSize(64, 32);
      rmtent.mirror = true;
      setRotation(rmtent, 0F, 0F, 0F);
      lmtent = new ModelRenderer(this, 0, 0);
      lmtent.addBox(0F, -2F, -0.5F, 1, 2, 1);
      lmtent.setRotationPoint(3F, 14F, 0F);
      lmtent.setTextureSize(64, 32);
      lmtent.mirror = true;
      setRotation(lmtent, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    bottom.render(f5);
    body.render(f5);
    fmtent.render(f5);
    frtent.render(f5);
    fltent.render(f5);
    bmtent.render(f5);
    bltent.render(f5);
    brtent.render(f5);
    rmtent.render(f5);
    lmtent.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    //super.setRotationAngles(f, f1, f2, f3, f4, f5);
  }

public void renderModel(float f5) {
	bottom.render(f5);
    body.render(f5);
    fmtent.render(f5);
    frtent.render(f5);
    fltent.render(f5);
    bmtent.render(f5);
    bltent.render(f5);
    brtent.render(f5);
    rmtent.render(f5);
    lmtent.render(f5);
}

}
