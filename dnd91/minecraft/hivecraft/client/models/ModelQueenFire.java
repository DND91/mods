package mods.dnd91.minecraft.hivecraft.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelQueenFire extends ModelBase
{
  //fields
    ModelRenderer body;
    ModelRenderer head;
    ModelRenderer lfleg;
    ModelRenderer lbleg;
    ModelRenderer rfleg;
    ModelRenderer rbleg;
  
  public ModelQueenFire()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      body = new ModelRenderer(this, 0, 0);
      body.addBox(-5F, 0F, -5F, 10, 10, 10);
      body.setRotationPoint(0F, 12F, 0F);
      body.setTextureSize(64, 64);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      head = new ModelRenderer(this, 0, 20);
      head.addBox(-3F, -2F, -6F, 6, 4, 6);
      head.setRotationPoint(0F, 19F, -5F);
      head.setTextureSize(64, 64);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      lfleg = new ModelRenderer(this, 40, 0);
      lfleg.addBox(0F, -0.5F, -0.5F, 6, 1, 1);
      lfleg.setRotationPoint(5F, 21F, -3F);
      lfleg.setTextureSize(64, 64);
      lfleg.mirror = true;
      setRotation(lfleg, 0F, 0.3141593F, 0.4886922F);
      lbleg = new ModelRenderer(this, 40, 0);
      lbleg.addBox(0F, -0.5F, -0.5F, 6, 1, 1);
      lbleg.setRotationPoint(5F, 21F, 3F);
      lbleg.setTextureSize(64, 64);
      lbleg.mirror = true;
      setRotation(lbleg, 0F, -0.5235988F, 0.4956735F);
      rfleg = new ModelRenderer(this, 40, 0);
      rfleg.addBox(0F, -0.5F, -0.5F, 6, 1, 1);
      rfleg.setRotationPoint(-5F, 21F, -3F);
      rfleg.setTextureSize(64, 64);
      rfleg.mirror = true;
      setRotation(rfleg, 0F, 2.932153F, -0.4886922F);
      rbleg = new ModelRenderer(this, 40, 0);
      rbleg.addBox(0F, -0.5F, -0.5F, 6, 1, 1);
      rbleg.setRotationPoint(-5F, 21F, 3F);
      rbleg.setTextureSize(64, 64);
      rbleg.mirror = true;
      setRotation(rbleg, 0F, -2.617994F, -0.4886922F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    body.render(f5);
    head.render(f5);
    lfleg.render(f5);
    lbleg.render(f5);
    rfleg.render(f5);
    rbleg.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
