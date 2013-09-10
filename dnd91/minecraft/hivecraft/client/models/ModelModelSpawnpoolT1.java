// Date: 2013-08-20 01:40:15
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package mods.dnd91.minecraft.hivecraft.client.models;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.larva.TileEntitySpawnpool;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class ModelModelSpawnpoolT1 extends ModelBase
{
  //fields
    ModelRenderer bottom;
    ModelRenderer backleftstick;
    ModelRenderer frontrightstick;
    ModelRenderer backrightstick;
    ModelRenderer frontleftstick;
    ModelRenderer front;
    ModelRenderer back;
    ModelRenderer left;
    ModelRenderer rigth;
    ModelRenderer middleGoo;
  
  public ModelModelSpawnpoolT1()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      bottom = new ModelRenderer(this, 64, 0);
      bottom.addBox(-8F, 0F, -8F, 16, 1, 16);
      bottom.setRotationPoint(0F, 23F, 0F);
      bottom.setTextureSize(128, 128);
      bottom.mirror = true;
      setRotation(bottom, 0F, 0F, 0F);
      backleftstick = new ModelRenderer(this, 0, 0);
      backleftstick.addBox(7F, -15F, 7F, 1, 15, 1);
      backleftstick.setRotationPoint(0F, 23F, 0F);
      backleftstick.setTextureSize(128, 128);
      backleftstick.mirror = true;
      setRotation(backleftstick, 0F, 0F, 0F);
      frontrightstick = new ModelRenderer(this, 0, 0);
      frontrightstick.addBox(-8F, -15F, -8F, 1, 15, 1);
      frontrightstick.setRotationPoint(0F, 23F, 0F);
      frontrightstick.setTextureSize(128, 128);
      frontrightstick.mirror = true;
      setRotation(frontrightstick, 0F, 0F, 0F);
      backrightstick = new ModelRenderer(this, 0, 0);
      backrightstick.addBox(-8F, -15F, 7F, 1, 15, 1);
      backrightstick.setRotationPoint(0F, 23F, 0F);
      backrightstick.setTextureSize(128, 128);
      backrightstick.mirror = true;
      setRotation(backrightstick, 0F, 0F, 0F);
      frontleftstick = new ModelRenderer(this, 0, 0);
      frontleftstick.addBox(7F, -15F, -8F, 1, 15, 1);
      frontleftstick.setRotationPoint(0F, 23F, 0F);
      frontleftstick.setTextureSize(128, 128);
      frontleftstick.mirror = true;
      setRotation(frontleftstick, 0F, 0F, 0F);
      front = new ModelRenderer(this, 0, 80);
      front.addBox(-7F, -15F, 7F, 14, 15, 1);
      front.setRotationPoint(0F, 23F, 0F);
      front.setTextureSize(128, 128);
      front.mirror = true;
      setRotation(front, 0F, 0F, 0F);
      back = new ModelRenderer(this, 0, 80);
      back.addBox(-7F, -15F, -8F, 14, 15, 1);
      back.setRotationPoint(0F, 23F, 0F);
      back.setTextureSize(128, 128);
      back.mirror = true;
      setRotation(back, 0F, 0F, 0F);
      left = new ModelRenderer(this, 0, 35);
      left.addBox(7F, -15F, -7F, 1, 15, 14);
      left.setRotationPoint(0F, 23F, 0F);
      left.setTextureSize(128, 128);
      left.mirror = true;
      setRotation(left, 0F, 0F, 0F);
      rigth = new ModelRenderer(this, 0, 35);
      rigth.addBox(-8F, -15F, -7F, 1, 15, 14);
      rigth.setRotationPoint(0F, 23F, 0F);
      rigth.setTextureSize(128, 128);
      rigth.mirror = true;
      setRotation(rigth, 0F, 0F, 0F);
      middleGoo = new ModelRenderer(this, 0, 100);
      middleGoo.addBox(-8F, -12F, -8F, 16, 12, 16);
      middleGoo.setRotationPoint(0F, 23F, 0F);
      middleGoo.setTextureSize(128, 128);
      
      //middleGoo.mirror = true;
      setRotation(middleGoo, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(entity, f, f1, f2, f3, f4, f5);
    bottom.render(f5);
    backleftstick.render(f5);
    frontrightstick.render(f5);
    backrightstick.render(f5);
    frontleftstick.render(f5);
    front.render(f5);
    back.render(f5);
    left.render(f5);
    rigth.render(f5);
    middleGoo.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(Entity ent, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
  }
  
public boolean isBlockAt(World world, int x, int y, int z, int blockID){
	return world.getBlockId(x, y, z) == blockID;
}

public void renderModel(TileEntitySpawnpool te, float f5) {
	int x = te.xCoord;
	int y = te.yCoord;
	int z = te.zCoord;
	
	middleGoo.render(f5);
	
	if(!isBlockAt(te.worldObj, x,y-1,z, HiveCraft.blockSpawnpool.blockID))
		bottom.render(f5);
	
	if(!(isBlockAt(te.worldObj, x+1,y,z, HiveCraft.blockSpawnpool.blockID) &&
    		isBlockAt(te.worldObj, x,y,z-1, HiveCraft.blockSpawnpool.blockID) &&
    		isBlockAt(te.worldObj, x+1,y,z-1, HiveCraft.blockSpawnpool.blockID)))
		backleftstick.render(f5);
    
    if(!(isBlockAt(te.worldObj, x-1,y,z, HiveCraft.blockSpawnpool.blockID) &&
    		isBlockAt(te.worldObj, x,y,z+1, HiveCraft.blockSpawnpool.blockID) &&
    		isBlockAt(te.worldObj, x-1,y,z+1, HiveCraft.blockSpawnpool.blockID)))
    frontrightstick.render(f5);
    
    if(!(isBlockAt(te.worldObj, x-1,y,z, HiveCraft.blockSpawnpool.blockID) &&
    		isBlockAt(te.worldObj, x,y,z-1, HiveCraft.blockSpawnpool.blockID) &&
    		isBlockAt(te.worldObj, x-1,y,z-1, HiveCraft.blockSpawnpool.blockID)))
    	backrightstick.render(f5);
    
    if(!(isBlockAt(te.worldObj, x+1,y,z, HiveCraft.blockSpawnpool.blockID) &&
    		isBlockAt(te.worldObj, x,y,z+1, HiveCraft.blockSpawnpool.blockID) &&
    		isBlockAt(te.worldObj, x+1,y,z+1, HiveCraft.blockSpawnpool.blockID)))
    	frontleftstick.render(f5);
    
    if(!isBlockAt(te.worldObj, x,y,z-1, HiveCraft.blockSpawnpool.blockID))
    	front.render(f5);
    
    if(!isBlockAt(te.worldObj, x,y,z+1, HiveCraft.blockSpawnpool.blockID))
    	back.render(f5);
    
    if(!isBlockAt(te.worldObj, x+1,y,z, HiveCraft.blockSpawnpool.blockID))
    	left.render(f5);
    
    if(!isBlockAt(te.worldObj, x-1,y,z, HiveCraft.blockSpawnpool.blockID))
    	rigth.render(f5);
    
}

}
