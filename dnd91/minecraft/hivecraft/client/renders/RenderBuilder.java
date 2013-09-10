package mods.dnd91.minecraft.hivecraft.client.renders;

import org.lwjgl.opengl.GL11;


import mods.dnd91.minecraft.hivecraft.client.models.ModelBuilderMagic;
import mods.dnd91.minecraft.hivecraft.client.models.ModelQueenMagic;
import mods.dnd91.minecraft.hivecraft.hatchling.builder.EntityBuilder;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntitySheep;

public class RenderBuilder extends RenderLiving
{
 protected ModelBuilderMagic model;
 protected ModelBase scaleBase = null;
 
 public RenderBuilder (ModelBuilderMagic modelTutorial, float f)
 {
  super(modelTutorial, f);
  model = ((ModelBuilderMagic)mainModel);
  this.setRenderPassModel(mainModel);
 }
 
 public void renderTutorial(EntityBuilder entity, double par2, double par4, double par6, float par8, float par9)
    {
	 	entity.updateTexture();
	 	mainModel = entity.getModel();
	 	this.setRenderPassModel(mainModel);
	 	
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }
 
 protected void scaleBuilder(EntityBuilder builder, float par2)
 {
	 if(builder.getModelID() == 1){
     GL11.glScalef(4.0f, 4.0f, 4.0f);
	 }
 }
 
 
 protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
 {
     this.scaleBuilder((EntityBuilder)par1EntityLiving, par2);
 }
 
 public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        renderTutorial((EntityBuilder)par1EntityLiving, par2, par4, par6, par8, par9);
    }
 
 public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        renderTutorial((EntityBuilder)par1Entity, par2, par4, par6, par8, par9);
    }
 
 protected int setFamilyColorAndRender(EntityBuilder queen, int par2, float par3)
 {
     if (par2 == 0)
     {
         this.loadTexture(queen.getColorTexture());
         float f1 = 1.0F;
         int j = queen.getDataWatcher().getWatchableObjectInt(13);
         j = j >= EntitySheep.fleeceColorTable.length ? 12 : j;
         j = EntitySheep.fleeceColorTable.length - j - 1;
         GL11.glColor3f(f1 * EntitySheep.fleeceColorTable[j][0], f1 * EntitySheep.fleeceColorTable[j][1], f1 * EntitySheep.fleeceColorTable[j][2]);
         return 1;
     }
     else
     {
         return -1;
     }
 }

 protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
 {
     return this.setFamilyColorAndRender((EntityBuilder)par1EntityLiving, par2, par3);
 }
}