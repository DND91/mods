package mods.dnd91.minecraft.hivecraft.client.gui;

import org.lwjgl.opengl.GL11;


import mods.dnd91.minecraft.hivecraft.structure.queenNest.ContainerQueenNest;
import mods.dnd91.minecraft.hivecraft.structure.queenNest.TileEntityQueenNest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GuiQueenNest extends GuiContainer{
	private TileEntityQueenNest queen = null;
	
	public GuiQueenNest(InventoryPlayer inventory,
			TileEntityQueenNest tileEntity) {
		super(new ContainerQueenNest(inventory, tileEntity));
		this.queen = tileEntity;
	}

	/**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = "Queens Nest";
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/gui/QueenNest.png");
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        i1 = this.queen.getHatchProgressScaled(8);
        this.drawTexturedModalRect(k + 132, l + 110 - i1, 176, 105 - i1, 16, i1);
        
        //23,60
        i1 = this.queen.getFoodProgressScaled(60);
        this.drawTexturedModalRect(k + 86, l + 72-i1, 200, 90-i1, 23, i1);
        this.drawTexturedModalRect(k + 86, l + 12, 176, 31, 23, 60);
    }

}
