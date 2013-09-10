package mods.dnd91.minecraft.hivecraft.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAscender extends ModelBase
{
  //fields
    ModelRenderer bottom;
    ModelRenderer middel;
    ModelRenderer top;
  
  public ModelAscender()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      bottom = new ModelRenderer(this, 0, 0);
      bottom.addBox(-8F, 0F, -8F, 16, 2, 16);
      bottom.setRotationPoint(0F, 22F, 0F);
      bottom.setTextureSize(128, 128);
      setRotation(bottom, 0F, 0F, 0F);
      
      middel = new ModelRenderer(this, 4, 19);
      middel.addBox(-7F, 0F, -7F, 14, 2, 14);
      middel.setRotationPoint(0F, 20F, 0F);
      middel.setTextureSize(128, 128);
      setRotation(middel, 0F, 0F, 0F);
      
      top = new ModelRenderer(this, 13, 36);
      top.addBox(-5F, 0F, -5F, 10, 3, 10);
      top.setRotationPoint(0F, 17F, 0F);
      top.setTextureSize(128, 128);
      setRotation(top, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    //super.render(entity, f, f1, f2, f3, f4, f5);
    //setRotationAngles(f, f1, f2, f3, f4, f5);
   // bottom.render(f5);
   // middel.render(f5);
   // top.render(f5);
  }
  
  public void renderModel(float f5)
  {
     bottom.render(f5);
     middel.render(f5);
     top.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    //model.rotateAngleX = x;
    //model.rotateAngleY = y;
    //model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    //super.setRotationAngles(f, f1, f2, f3, f4, f5);
  }

}