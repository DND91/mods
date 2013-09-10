package mods.dnd91.minecraft.hivecraft.client.gui;

import mods.dnd91.minecraft.hivecraft.larva.ContainerSpawnpool;
import mods.dnd91.minecraft.hivecraft.larva.TileEntitySpawnpool;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;


public class GuiSpawnpool extends GuiContainer {
    private TileEntitySpawnpool SpawnpoolInventory;

    public GuiSpawnpool(InventoryPlayer par1InventoryPlayer, TileEntitySpawnpool par2TileEntityFurnace)
    {
        super(new ContainerSpawnpool(par1InventoryPlayer, par2TileEntityFurnace));
        this.SpawnpoolInventory = par2TileEntityFurnace;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = "Organic Spawnpool";
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/gui/Spawnpool.png");
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        //if (this.SpawnpoolInventory.isBurning())
        //{
        //    i1 = this.SpawnpoolInventory.getBurnTimeRemainingScaled(12);
        //    this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        //}

        i1 = this.SpawnpoolInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
        
        //23,60
        i1 = this.SpawnpoolInventory.getFoodProgressScaled(60);
        this.drawTexturedModalRect(k + 140, l + 70-i1, 200, 90-i1, 23, i1);
        this.drawTexturedModalRect(k + 140, l + 10, 176, 31, 23, 60);
    }
}
