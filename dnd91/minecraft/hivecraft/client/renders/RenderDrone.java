package mods.dnd91.minecraft.hivecraft.client.renders;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;

import org.lwjgl.opengl.GL11;

import mods.dnd91.minecraft.hivecraft.client.models.ModelDroneStandard;
import mods.dnd91.minecraft.hivecraft.hatchling.drone.EntityDrone;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.MinecraftForgeClient;

public class RenderDrone extends RenderLiving{
	protected ModelDroneStandard model;
	protected RenderItem item = new RenderItem();
	
	public RenderDrone(ModelDroneStandard m, float f) {
		super(m, f);
		model = ((ModelDroneStandard)mainModel);
		this.setRenderPassModel(mainModel);
		item.setRenderManager(this.renderManager);
	}
	
	public void renderDrone(EntityDrone entity, double par2, double par4, double par6, float par8, float par9)
    {
	 	entity.updateTexture();
	 	super.doRenderLiving(entity, par2, par4, par6, par8, par9);
	 	
    }
	
	@Override
	protected void renderEquippedItems(EntityLiving entity, float par2){
		float f1 = 1.0F;
		GL11.glColor3f(f1, f1, f1);
		super.renderEquippedItems(entity, par2);
		
		ItemStack itemstack = null;
		
		if(((EntityDrone)entity).getStackInSlot(0) != null){
	 		itemstack = ((EntityDrone)entity).getStackInSlot(0);
	 	}
		
		if (itemstack != null)
        {
            GL11.glPushMatrix();
            float f2;

            this.model.armLeft.postRender(0.0625F);
            this.model.armRight.postRender(0.0625F);
            GL11.glTranslatef(-0.0625F, -1.4375F, 0.0625F);

            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, EQUIPPED);
            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack, BLOCK_3D));

            if (itemstack.getItem() instanceof ItemBlock && (is3D || RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType())))
            {
                f2 = 0.5F;
                GL11.glTranslatef(0.0625F, -0.0625F, 0.4375F);
                GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
                f2 *= 0.75F;
                //GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                //GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f2, -f2, f2);
            }
            /*else if (itemstack.itemID == Item.bow.itemID)
            {
                f2 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f2, -f2, f2);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }*/
            else if (Item.itemsList[itemstack.itemID].isFull3D())
            {
                f2 = 0.625F;

                if (Item.itemsList[itemstack.itemID].shouldRotateAroundWhenRendering())
                {
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(-0.25F, -0.125F, 0.0F);
                }

                GL11.glTranslatef(0.0F, 0.1250F, 0.75F);
                GL11.glScalef(f2, -f2, f2);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                
                //GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                //GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                //GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                
            }
            else
            {
            	f2 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(f2, f2, f2);
                
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 0.5F, 0.0F, 0.5F);
            }

            this.renderManager.itemRenderer.renderItem(entity, itemstack, 0);

            if (itemstack.getItem().requiresMultipleRenderPasses())
            {
                for (int x = 1; x < itemstack.getItem().getRenderPasses(itemstack.getItemDamage()); x++)
                {
                    this.renderManager.itemRenderer.renderItem(entity, itemstack, x);
                }
            }

            GL11.glPopMatrix();
        }
	}
 
 public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
	 renderDrone((EntityDrone)par1EntityLiving, par2, par4, par6, par8, par9);
    }
 
 public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
	 renderDrone((EntityDrone)par1Entity, par2, par4, par6, par8, par9);
    }
 
 protected int setWoolColorAndRender(EntityDrone drone, int par2, float par3)
 {
     if (par2 == 0)
     {
         this.loadTexture(drone.getColorTexture());
         float f1 = 1.0F;
         int j = drone.getDataWatcher().getWatchableObjectInt(13);
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
     return this.setWoolColorAndRender((EntityDrone)par1EntityLiving, par2, par3);
 }

}
