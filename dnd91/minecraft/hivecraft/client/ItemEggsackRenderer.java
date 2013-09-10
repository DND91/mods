package mods.dnd91.minecraft.hivecraft.client;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;


import mods.dnd91.minecraft.hivecraft.HiveCraftWorldData;
import mods.dnd91.minecraft.hivecraft.client.models.ModelOvalEgg;
import mods.dnd91.minecraft.hivecraft.client.models.ModelTentacleEgg;
import mods.dnd91.minecraft.hivecraft.genetics.Genetics;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.IItemRenderer;

public class ItemEggsackRenderer implements IItemRenderer {

	ModelOvalEgg ovalEgg;
	ModelTentacleEgg tentEgg;
	
	public ItemEggsackRenderer(){
		super();
		ovalEgg = new ModelOvalEgg();
	    tentEgg = new ModelTentacleEgg();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO Auto-generated method stub
		return type == ItemRenderType.INVENTORY || type == ItemRenderType.EQUIPPED || type == ItemRenderType.ENTITY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
		// TODO Auto-generated method stub
		if(type == ItemRenderType.INVENTORY){
		int i = 0;
		RenderBlocks blockRenderer = (RenderBlocks)data[0];
	      NBTTagCompound nbtcompound = stack.getTagCompound();
	      
	      if(nbtcompound == null){
	    	  return;
	      }
	      int colorID = nbtcompound.getInteger("colorID");
	      int familyName = nbtcompound.getInteger("familyName");
	                  //directory of the model's exture file
	      switch(familyName){
	      case 1: //Magic Worm
	    	  
	    	  blockRenderer.minecraftRB.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/models/ModelTentEgg1.png");
	          GL11.glPushMatrix(); 
	          GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	          GL11.glColor3f(1.0F, 1.0F, 1.0F);
	          //GL11.glTranslatef((float)d + 0.5F, (float)d1 + 2.5F, (float)d2 + 0.5F);
	          GL11.glScalef(1.0F, 1.0F, 1.0F);
	          GL11.glTranslatef(8.0F, -10.0F, 0.0F);
	          tentEgg.renderModel(1.0F); 
	          GL11.glPopMatrix();
	          
	          blockRenderer.minecraftRB.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/models/ModelTentEgg2.png");
	          
	          GL11.glPushMatrix();
	          GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	          GL11.glColor3f(EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][0], EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][1], EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][2]);
	          //GL11.glTranslatef((float)d + 0.5F, (float)d1 + 2.5F, (float)d2 + 0.5F);
	          GL11.glScalef(1.0F, 1.0F, 1.0F);
	          GL11.glTranslatef(8.0F, -10.0F, 0.0F);
	          tentEgg.renderModel(1.0F); 
	          GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	          GL11.glPopMatrix();
	          GL11.glColor3f(1.0F, 1.0F, 1.0F);
	    	  break;
	      case 2: //Sprinkel Fire
	    	  blockRenderer.minecraftRB.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/models/ModelOvalEgg1.png");
	          
	          GL11.glPushMatrix(); 
	          GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	          GL11.glColor3f(1.0F, 1.0F, 1.0F);
	          GL11.glScalef(1.0F, 1.0F, 1.0F);
	          GL11.glTranslatef(7.0F, -10.0F, -10.0F);
	          ovalEgg.renderModel(1.0F); 
	          GL11.glPopMatrix();
	          
	          blockRenderer.minecraftRB.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/models/ModelOvalEgg2.png");
	          GL11.glPushMatrix();
	          GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	          GL11.glColor3f(EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][0], EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][1], EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][2]);
	          GL11.glScalef(1.0F, 1.0F, 1.0F);
	          GL11.glTranslatef(7.0F, -10.0F, -10.0F);
	          ovalEgg.renderModel(1.0F); 
	          GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	          GL11.glPopMatrix();
	          GL11.glColor3f(1.0F, 1.0F, 1.0F);
	          
	    	  break;
	      }
		}else if(type == ItemRenderType.EQUIPPED){
			int i = 0;
			RenderBlocks blockRenderer = (RenderBlocks)data[0];
		      NBTTagCompound nbtcompound = stack.getTagCompound();
		      
		      if(nbtcompound == null){
		    	  return;
		      }
		      int colorID = nbtcompound.getInteger("colorID");
		      int familyName = nbtcompound.getInteger("familyName");
              //directory of the model's exture file
		      switch(familyName){
		      case 0: //Magic Worm
		    	  
		    	  blockRenderer.minecraftRB.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/models/ModelTentEgg1.png");
		          GL11.glPushMatrix(); 
		          GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		          GL11.glColor3f(1.0F, 1.0F, 1.0F);
		          //GL11.glTranslatef((float)d + 0.5F, (float)d1 + 2.5F, (float)d2 + 0.5F);
		          GL11.glScalef(0.0625F, -0.0625F, 0.0625F);
		          GL11.glTranslatef(5.0F, -10.0F, -1.0F);;
		          tentEgg.renderModel(0.0625F); 
		          GL11.glPopMatrix();
		          
		          blockRenderer.minecraftRB.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/models/ModelTentEgg2.png");
		          
		          GL11.glPushMatrix();
		          GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		          GL11.glColor3f(EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][0], EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][1], EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][2]);
		          //GL11.glTranslatef((float)d + 0.5F, (float)d1 + 2.5F, (float)d2 + 0.5F);
		          GL11.glScalef(0.0625F, -0.0625F, 0.0625F);
		          GL11.glTranslatef(5.0F, -10.0F, -1.0F);
		          tentEgg.renderModel(0.0625F); 
		          GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		          GL11.glPopMatrix();
		          GL11.glColor3f(1.0F, 1.0F, 1.0F);
		    	  break;
		      case 1: //Sprinkel Fire
		    	  blockRenderer.minecraftRB.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/models/ModelOvalEgg1.png");
		          
		          GL11.glPushMatrix(); 
		          GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		          GL11.glColor3f(1.0F, 1.0F, 1.0F);
		          GL11.glScalef(0.0625F, -0.0625F, 0.0625F);
		          GL11.glTranslatef(5.0F, -10.0F, -1.0F);;
		          ovalEgg.renderModel(0.0625F); 
		          GL11.glPopMatrix();
		          
		          blockRenderer.minecraftRB.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/models/ModelOvalEgg2.png");
		          GL11.glPushMatrix();
		          GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		          GL11.glColor3f(EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][0], EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][1], EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][2]);
		          GL11.glScalef(0.0625F, -0.0625F, 0.0625F);
		          GL11.glTranslatef(5.0F, -10.0F, -1.0F);;
		          ovalEgg.renderModel(0.0625F); 
		          GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		          GL11.glPopMatrix();
		          GL11.glColor3f(1.0F, 1.0F, 1.0F);
		          
		    	  break;
		      }
		  }else if(type == ItemRenderType.ENTITY){
			  int i = 0;
				RenderBlocks blockRenderer = (RenderBlocks)data[0];
			      NBTTagCompound nbtcompound = stack.getTagCompound();
			      
			      if(nbtcompound == null){
			    	  return;
			      }
			      int colorID = nbtcompound.getInteger("colorID");
			                  //directory of the model's exture file
			      int familyName = nbtcompound.getInteger("familyName");
                  //directory of the model's exture file
			      switch(familyName){
			      case 0: //Magic Worm
			    	  
			    	  blockRenderer.minecraftRB.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/models/ModelTentEgg1.png");
			          GL11.glPushMatrix(); 
			          GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			          GL11.glColor3f(1.0F, 1.0F, 1.0F);
			          //GL11.glTranslatef((float)d + 0.5F, (float)d1 + 2.5F, (float)d2 + 0.5F);
			          GL11.glScalef(0.0625F, 0.0625F, 0.0625F);
			          GL11.glTranslatef(5.0F, 0.0F, -1.0F);;
			          GL11.glRotatef(180,1,1,0);
			          tentEgg.renderModel(0.0625F); 
			          GL11.glPopMatrix();
			          
			          blockRenderer.minecraftRB.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/models/ModelTentEgg2.png");
			          
			          GL11.glPushMatrix();
			          GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			          GL11.glColor3f(EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][0], EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][1], EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][2]);
			          //GL11.glTranslatef((float)d + 0.5F, (float)d1 + 2.5F, (float)d2 + 0.5F);
			          GL11.glScalef(0.0625F, 0.0625F, 0.0625F);
			          GL11.glTranslatef(5.0F, 0.0F, -1.0F);
			          GL11.glRotatef(180,1,1,0);
			          tentEgg.renderModel(0.0625F); 
			          GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			          GL11.glPopMatrix();
			          GL11.glColor3f(1.0F, 1.0F, 1.0F);
			    	  break;
			      case 1: //Sprinkel Fire
			    	  blockRenderer.minecraftRB.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/models/ModelOvalEgg1.png");
			          
			          GL11.glPushMatrix(); 
			          GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			          GL11.glColor3f(1.0F, 1.0F, 1.0F);
			          GL11.glScalef(0.0625F, 0.0625F, 0.0625F);
			          GL11.glTranslatef(5.0F, 0.0F, -1.0F);
			          GL11.glRotatef(180,1,1,0);
			          ovalEgg.renderModel(0.0625F); 
			          GL11.glPopMatrix();
			          
			          blockRenderer.minecraftRB.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/models/ModelOvalEgg2.png");
			          GL11.glPushMatrix();
			          GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			          GL11.glColor3f(EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][0], EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][1], EntitySheep.fleeceColorTable[EntitySheep.fleeceColorTable.length-colorID-1][2]);
			          GL11.glScalef(0.0625F, 0.0625F, 0.0625F);
			          GL11.glTranslatef(5.0F, 0.0F, -1.0F);
			          GL11.glRotatef(180,1,1,0);
			          ovalEgg.renderModel(0.0625F); 
			          GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			          GL11.glPopMatrix();
			          GL11.glColor3f(1.0F, 1.0F, 1.0F);
			          
			    	  break;
			      }  
		  }
			
		}
	}