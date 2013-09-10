package mods.dnd91.minecraft.hivecraft.client.gui;

import java.util.List;

import mods.dnd91.minecraft.hivecraft.eggs.ContainerHatcher;
import mods.dnd91.minecraft.hivecraft.eggs.TileEntityHatcher;
import mods.dnd91.minecraft.hivecraft.genetics.Genetics;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;


public class GuiHatcher extends GuiContainer {
    private TileEntityHatcher hatcherInventory;

    public GuiHatcher(InventoryPlayer par1InventoryPlayer, TileEntityHatcher par2TileEntityFurnace)
    {
        super(new ContainerHatcher(par1InventoryPlayer, par2TileEntityFurnace));
        this.hatcherInventory = par2TileEntityFurnace;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = "Organic Hatcher";
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
        this.fontRenderer.drawString(hatcherInventory.moist+" H%", 8, 48, 4210752);
        this.fontRenderer.drawString(hatcherInventory.heat+" T%", 8, 58, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/gui/hatcher.png");
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        if (this.hatcherInventory.isBurning())
        {
            i1 = this.hatcherInventory.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = this.hatcherInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
        
        if(hatcherInventory.genetics != null){

        	this.drawTexturedModalRect(k + 4, l + 4, 176, 49, 17, 17);
        	
        	if(this.hatcherInventory.needMoreLight())
        		this.drawTexturedModalRect(k + 4, l + 4, 212, 31, 18, 18);
        	else if(this.hatcherInventory.needLessLight())
        		this.drawTexturedModalRect(k + 4, l + 4, 229, 31, 18, 18);
        	else
        		this.drawTexturedModalRect(k + 4, l + 4, 212, 49, 18, 18);
        	
        	if(!hatcherInventory.genetics.needRoof()){
        		this.drawTexturedModalRect(k + 4 + 18, l + 4, 194, 67, 17, 17);
        	}else{
        		this.drawTexturedModalRect(k + 4 + 18, l + 4, 176, 67, 17, 17);
        	}
        	
        	if(this.hatcherInventory.hasRoof()){
        		this.drawTexturedModalRect(k + 4 + 18, l + 4, 212, 49, 18, 18);
        	}else{
        		this.drawTexturedModalRect(k + 4 + 18, l + 4, 194, 31, 18, 18);
        	}
        	
        	this.drawTexturedModalRect(k + 4, l + 4 + 18, 176, 85, 17, 17);
        	
        	if(this.hatcherInventory.needMoreHeat())
        		this.drawTexturedModalRect(k + 4, l + 4 + 18, 212, 31, 18, 18);
        	else if(this.hatcherInventory.needLessHeat())
        		this.drawTexturedModalRect(k + 4, l + 4 + 18, 229, 31, 18, 18);
        	else
        		this.drawTexturedModalRect(k + 4, l + 4 + 18, 212, 49, 18, 18);
        	
        	this.drawTexturedModalRect(k + 4 + 18, l + 4 + 18, 176, 103, 17, 17);
        	
        	if(this.hatcherInventory.needMoreMoist())
        		this.drawTexturedModalRect(k + 4 + 18, l + 4 + 18, 212, 31, 18, 18);
        	else if(this.hatcherInventory.needLessMoist())
        		this.drawTexturedModalRect(k + 4 + 18, l + 4 + 18, 229, 31, 18, 18);
        	else
        		this.drawTexturedModalRect(k + 4 + 18, l + 4 + 18, 212, 49, 18, 18);
        }
    }
}
