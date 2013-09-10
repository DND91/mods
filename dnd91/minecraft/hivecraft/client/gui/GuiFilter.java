package mods.dnd91.minecraft.hivecraft.client.gui;

import mods.dnd91.minecraft.hivecraft.hatchling.drone.ContainerFilter;
import mods.dnd91.minecraft.hivecraft.hatchling.drone.EntityDrone;
import mods.dnd91.minecraft.hivecraft.hatchling.queen.ContainerQueen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

public class GuiFilter extends GuiContainer{
	private EntityDrone inv;

    public GuiFilter(InventoryPlayer par1InventoryPlayer, EntityDrone par2TileEntityFurnace)
    {
        super(new ContainerFilter(par1InventoryPlayer, par2TileEntityFurnace));
        this.inv = par2TileEntityFurnace;
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = "Filter";
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 5, 5, 4210752);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/gui/Filter.png");
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;
	}

}
