package mods.dnd91.minecraft.hivecraft.client.renders;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;


import mods.dnd91.minecraft.hivecraft.HiveCraftWorldData;
import mods.dnd91.minecraft.hivecraft.client.models.ModelOvalEgg;
import mods.dnd91.minecraft.hivecraft.client.models.ModelTentacleEgg;
import mods.dnd91.minecraft.hivecraft.eggs.TileEntityHatcher;
import mods.dnd91.minecraft.hivecraft.genetics.Genetics;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class RenderHatcher extends TileEntitySpecialRenderer {
	protected RenderItem item = new RenderItem();
	protected RenderManager renderManager = RenderManager.instance;
   
   public RenderHatcher()
   {
	   item.setRenderManager(renderManager);
   }
   
   @Override
   public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
         double d2, float f) {
      renderHatcherModelAt((TileEntityHatcher)tileentity, d0,d1,d2,f);
      
   }
   
   private void renderHatcherModelAt(TileEntityHatcher te, double d, double d1, double d2, float f)
   {
      //GL11.glTranslatef((float)d + 0.5F, (float)d1 + 2.5F, (float)d2 + 0.5F);
      ItemStack itemstack = te.getStackInSlot(2);
      if(itemstack == null)
    	  itemstack = te.getStackInSlot(0);

      if (itemstack != null)
      {
    	  EntityItem entityitem = new EntityItem(te.worldObj, 0.0D, 0.0D, 0.0D, itemstack);
          entityitem.getEntityItem().stackSize = 1;
          entityitem.hoverStart = 0.0F;
          
          GL11.glPushMatrix();
          float f2 = (float)(d) + 0.5F;
          float f3 = (float)(d1) - 0.5F;
          float f4 = (float)(d2) + 0.5F;

          GL11.glTranslatef((float)f2 + 0.0F, (float)f3 + 1.5125F, (float)f4 + 0.0F);
          GL11.glScalef(1.5F, 1.5F, 1.5F);
          //GL11.glTranslatef(0.0F,  1.0F, 0.0F);
          
          RenderItem.renderInFrame = true;
          RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
          RenderItem.renderInFrame = false;
          
          GL11.glPopMatrix();
      }
      
   }

}