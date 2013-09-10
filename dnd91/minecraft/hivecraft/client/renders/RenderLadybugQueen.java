package mods.dnd91.minecraft.hivecraft.client.renders;

import org.lwjgl.opengl.GL11;

import mods.dnd91.minecraft.hivecraft.client.models.ModelQueenMagic;
import mods.dnd91.minecraft.hivecraft.hatchling.queen.EntityLadybugQueen;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySheep;

public class RenderLadybugQueen extends RenderLiving{
	protected ModelQueenMagic model;
	public RenderLadybugQueen(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		model = ((ModelQueenMagic)mainModel);
		this.setRenderPassModel(mainModel);
	}

	public void renderTutorial(EntityLadybugQueen entity, double par2, double par4, double par6, float par8, float par9)
    {
	 	entity.updateTexture();
	 	this.setRenderPassModel(mainModel);
	 	
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }
 
 public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        renderTutorial((EntityLadybugQueen)par1EntityLiving, par2, par4, par6, par8, par9);
    }
 
 public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        renderTutorial((EntityLadybugQueen)par1Entity, par2, par4, par6, par8, par9);
    }
 
 protected int setWoolColorAndRender(EntityLadybugQueen queen, int par2, float par3)
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
     return this.setWoolColorAndRender((EntityLadybugQueen)par1EntityLiving, par2, par3);
 }
	
}
