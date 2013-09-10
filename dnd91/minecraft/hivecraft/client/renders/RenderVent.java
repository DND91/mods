package mods.dnd91.minecraft.hivecraft.client.renders;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import mods.dnd91.minecraft.hivecraft.client.models.struct.ModelGrinder;
import mods.dnd91.minecraft.hivecraft.client.models.struct.ModelVentPipe;
import mods.dnd91.minecraft.hivecraft.structure.bioGrinder.TileEntityGrinder;
import mods.dnd91.minecraft.hivecraft.structure.bioVent.TileEntityVent;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderVent extends TileEntitySpecialRenderer{
	
	ModelVentPipe model = new ModelVentPipe();
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
			double d2, float f) {
		renderGrinderModelAt((TileEntityVent)tileentity, d0,d1,d2,f);
	}

	private void renderGrinderModelAt(TileEntityVent te, double d, double d1, double d2, float f)
	   {
	      int i = 0;

	      if(te.worldObj != null) 
	      {
	         i = (te.worldObj.getBlockMetadata(te.xCoord, te.yCoord, te.zCoord)); 
	      }

	                  //directory of the model's texture file
	      bindTextureByName("/mods/dnd91/minecraft/hivecraft/textures/models/struct/ModelVentSpire.png");
	      
	      GL11.glPushMatrix(); 
	      GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	      GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
	      GL11.glScalef(1.0F, -1.0F, -1.0F);
	      //GL11.glTranslatef(0.5F, 1.0F, 0.5F);
	      model.renderModel(te, 0.0625F); 
	      GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	      GL11.glPopMatrix();
	      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	   }
}
