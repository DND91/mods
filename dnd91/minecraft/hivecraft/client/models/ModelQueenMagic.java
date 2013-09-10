package mods.dnd91.minecraft.hivecraft.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelQueenMagic extends ModelBase
{
  //fields
    ModelRenderer body;
    ModelRenderer tail;
    ModelRenderer lbwing;
    ModelRenderer lswing;
    ModelRenderer rbwing;
    ModelRenderer rswing;
    ModelRenderer leftantena;
    ModelRenderer rightantena;
  
  public ModelQueenMagic()
  {
    textureWidth = 32;
    textureHeight = 32;
    
      body = new ModelRenderer(this, 0, 0);
      body.addBox(-2.5F, -1F, 0F, 5, 2, 10);
      body.setRotationPoint(0F, 23F, -5F);
      body.setTextureSize(32, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      tail = new ModelRenderer(this, 22, 27);
      tail.addBox(-0.5F, -0.5F, 0F, 1, 1, 1);
      tail.setRotationPoint(0F, 23F, 5F);
      tail.setTextureSize(32, 32);
      tail.mirror = true;
      setRotation(tail, 0F, 0F, 0F);
      lbwing = new ModelRenderer(this, 0, 13);
      lbwing.addBox(0F, 0F, 0F, 2, 3, 6);
      lbwing.setRotationPoint(1F, 21F, -2F);
      lbwing.setTextureSize(32, 32);
      lbwing.mirror = true;
      setRotation(lbwing, 0F, 0F, 0F);
      lswing = new ModelRenderer(this, 0, 23);
      lswing.addBox(2F, 1F, 1F, 1, 2, 4);
      lswing.setRotationPoint(1F, 21F, -2F);
      lswing.setTextureSize(32, 32);
      lswing.mirror = true;
      setRotation(lswing, 0F, 0F, 0F);
      rbwing = new ModelRenderer(this, 16, 13);
      rbwing.addBox(-2F, 0F, 0F, 2, 3, 6);
      rbwing.setRotationPoint(-1F, 21F, -2F);
      rbwing.setTextureSize(32, 32);
      rbwing.mirror = true;
      setRotation(rbwing, 0F, 0F, 0F);
      rswing = new ModelRenderer(this, 11, 23);
      rswing.addBox(-3F, 1F, 1F, 1, 2, 4);
      rswing.setRotationPoint(-1F, 21F, -2F);
      rswing.setTextureSize(32, 32);
      rswing.mirror = true;
      setRotation(rswing, 0F, 0F, 0F);
      leftantena = new ModelRenderer(this, 22, 23);
      leftantena.addBox(-0.5F, -1F, -0.5F, 1, 1, 1);
      leftantena.setRotationPoint(1.466667F, 22F, -4F);
      leftantena.setTextureSize(32, 32);
      leftantena.mirror = true;
      setRotation(leftantena, 0F, 0F, 0F);
      rightantena = new ModelRenderer(this, 27, 23);
      rightantena.addBox(-0.5F, -1F, -0.5F, 1, 1, 1);
      rightantena.setRotationPoint(-1.5F, 22F, -4F);
      rightantena.setTextureSize(32, 32);
      rightantena.mirror = true;
      setRotation(rightantena, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    body.render(f5);
    tail.render(f5);
    lbwing.render(f5);
    lswing.render(f5);
    rbwing.render(f5);
    rswing.render(f5);
    leftantena.render(f5);
    rightantena.render(f5);
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
