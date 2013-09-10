package mods.dnd91.minecraft.hivecraft.client.gui;

import org.lwjgl.opengl.GL11;


import mods.dnd91.minecraft.hivecraft.backlog.ContainerAscender;
import mods.dnd91.minecraft.hivecraft.backlog.TileEntityAscender;
import mods.dnd91.minecraft.hivecraft.structure.bioAsembler.ContainerBioAsembler;
import mods.dnd91.minecraft.hivecraft.structure.bioAsembler.TileEntityBioAsembler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

public class GuiBioAsembler extends GuiContainer{

	private TileEntityBioAsembler ascenderInventory;

    public GuiBioAsembler(InventoryPlayer par1InventoryPlayer, TileEntityBioAsembler par2TileEntityFurnace)
    {
        super(new ContainerBioAsembler(par1InventoryPlayer, par2TileEntityFurnace));
        this.ascenderInventory = par2TileEntityFurnace;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = "Organic Asembler";
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/gui/BioAsembler.png");
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;
        
        //Food
        i1 = this.ascenderInventory.getFoodProgressScaled(60);
        this.drawTexturedModalRect(k + 98, l + 64-i1, 200, 90-i1, 23, i1);
        this.drawTexturedModalRect(k + 98, l + 5, 176, 31, 23, 60);
        
        //Water
        i1 = this.ascenderInventory.getWaterProgressScaled(60);
        this.drawTexturedModalRect(k + 123, l + 64-i1, 200, 90-i1, 23, i1);
        this.drawTexturedModalRect(k + 123, l + 5, 176, 31, 23, 60);
        
        //Biomass
        i1 = this.ascenderInventory.getBiomassProgressScaled(60);
        this.drawTexturedModalRect(k + 148, l + 64-i1, 200, 90-i1, 23, i1);
        this.drawTexturedModalRect(k + 148, l + 5, 176, 31, 23, 60);
        
        if(this.ascenderInventory.getFoodCostScale(60) > 0){
        	i1 = this.ascenderInventory.getFoodCostScale(60);
        	int baseX = k + 98;
        	int baseY = l + 64-i1;
        	this.drawRect(baseX, baseY-1, baseX+23, baseY+0, 0xFF000000);
        	i1 = this.ascenderInventory.getWaterCostScale(60);
        	baseX = k + 123;
        	baseY = l + 64-i1;
        	this.drawRect(baseX, baseY-1, baseX+23, baseY+0, 0xFF000000);
        	i1 = this.ascenderInventory.getBiomassCostScale(60);
        	baseX = k + 148;
        	baseY = l + 64-i1;
        	this.drawRect(baseX, baseY-1, baseX+23, baseY+0, 0xFF000000);
        }
    }

}
