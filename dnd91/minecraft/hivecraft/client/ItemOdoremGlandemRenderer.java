package mods.dnd91.minecraft.hivecraft.client;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import mods.dnd91.minecraft.hivecraft.client.models.ModelOdoremGlandem;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.IItemRenderer;

public class ItemOdoremGlandemRenderer implements IItemRenderer{

	ModelOdoremGlandem model = new ModelOdoremGlandem();
	RenderItem renderer = new RenderItem();
	public static ItemStack viewStack = new ItemStack(0,1,0);
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type != ItemRenderType.FIRST_PERSON_MAP;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		if(helper == ItemRendererHelper.ENTITY_BOBBING)
			return false;
		
		if(type == ItemRenderType.EQUIPPED && helper == ItemRendererHelper.EQUIPPED_BLOCK)
			return true;
		if(helper == ItemRendererHelper.BLOCK_3D)
			return true;
		if(type == ItemRenderType.INVENTORY && helper == ItemRendererHelper.INVENTORY_BLOCK)
			return true;
		return false;
	}
	

	float rot = 0.0F;
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		RenderBlocks blockRenderer;
		switch(type){
		case ENTITY:
			blockRenderer = (RenderBlocks)data[0];
			EntityItem entity = (EntityItem)data[1];
			blockRenderer.minecraftRB.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/models/ModelOdoremGlandem.png");
	        GL11.glPushMatrix(); 
	        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	        GL11.glColor3f(1.0F, 1.0F, 1.0F);
	        //GL11.glTranslatef((float)d + 0.5F, (float)d1 + 2.5F, (float)d2 + 0.5F);
	        
	        float rotation = entity.age * 5.0f;
	        float heigth = entity.age * 0.01f;
	        
	        heigth = heigth > 10.0f ? 10.0f : heigth;
	        
	        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
	        
	        //GL11.glRotatef(rotation, 1.0F, 0.0F, 0.0F);
	        //GL11.glRotatef(rotation, 0.0F, 0.0F, 1.0F);
	        
	        GL11.glScalef(0.125F, 0.125F, 0.125F);
	        GL11.glTranslatef(-2.0F, 6.0F + heigth, 2.0F);;
	        //GL11.glRotatef(180,1,1,0);
	        model.renderModel(1.0F); 
	        GL11.glPopMatrix();
			break;
		case EQUIPPED:
			blockRenderer = (RenderBlocks)data[0];
			blockRenderer.minecraftRB.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/models/ModelOdoremGlandem.png");
	        GL11.glPushMatrix(); 
	        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	        GL11.glColor3f(1.0F, 1.0F, 1.0F);
	        //GL11.glTranslatef((float)d + 0.5F, (float)d1 + 2.5F, (float)d2 + 0.5F);
	        GL11.glScalef(0.25F, 0.25F, 0.25F);
	        GL11.glTranslatef(0.0F, 4.0F, 4.0F);;
	        model.renderModel(1F); 
	        GL11.glPopMatrix();
	        
	        
	        
			break;
		case EQUIPPED_FIRST_PERSON:
			blockRenderer = (RenderBlocks)data[0];
			blockRenderer.minecraftRB.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/models/ModelOdoremGlandem.png");
	        GL11.glPushMatrix(); 
	        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	        GL11.glColor3f(1.0F, 1.0F, 1.0F);
	        GL11.glScalef(0.125F, 0.125F, 0.125F);
	        GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
	        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
	        GL11.glTranslatef(4.0F, 2.0F, 4.0F);
	        model.renderModel(1F); 
	        GL11.glPopMatrix();
	        
	        NBTTagCompound compound = item.getTagCompound();
	        if(compound != null && compound.hasKey("blockID")){
	        	int blockID = compound.getInteger("blockID");
	        	if(viewStack.itemID != blockID)
	        		viewStack.itemID = blockID;
	        	//this.renderItemStack(blockRenderer);
	        }
	        
			break;
		case INVENTORY:
			blockRenderer = (RenderBlocks)data[0];
			blockRenderer.minecraftRB.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/models/ModelOdoremGlandem.png");
	        GL11.glPushMatrix(); 
	        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	        GL11.glColor3f(1.0F, 1.0F, 1.0F);
	        //GL11.glTranslatef((float)d + 0.5F, (float)d1 + 2.5F, (float)d2 + 0.5F);
	        GL11.glScalef(0.25F, 0.25F, 0.25F);
	        GL11.glTranslatef(-4.0F, 0.5F, 0.0F);
	        model.renderModel(1.0F); 
	        GL11.glPopMatrix();
			break;
		default:
			
			break;
		}
	}
	
	private void renderItemStack(RenderBlocks blockRenderer){
		GL11.glPushMatrix(); 
    	//GL11.glColor3f(1.0F, 1.0F, 1.0F);
    	
    	//GL11.glScaled(0.125F, 0.125F, 0.125F);
    	
    	//GL11.glTranslatef(0.0F, 0.0F, 10.0F);
    	
    	//renderer.zLevel = 5.0F;
    	rot += 0.05F;
    	
    	//GL11.glScaled(0.125F, 0.125F, 0.125F);
    	
    	//GL11.glRotatef(rot, 1.0F, 1.0F, 1.0F); //Spins around
    	
    	//GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        
    	//renderer.zLevel = 1.0F;
		renderer.renderItemAndEffectIntoGUI(blockRenderer.minecraftRB.fontRenderer, blockRenderer.minecraftRB.renderEngine, viewStack, 0, 0);
		//renderer.zLevel = 0.0F;

        GL11.glPopMatrix();
	}

}
