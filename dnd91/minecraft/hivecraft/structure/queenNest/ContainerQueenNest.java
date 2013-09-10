package mods.dnd91.minecraft.hivecraft.structure.queenNest;

import mods.dnd91.minecraft.hivecraft.backlog.TileEntityAscender;
import mods.dnd91.minecraft.hivecraft.eggs.SlotEgg;
import mods.dnd91.minecraft.hivecraft.eggs.SlotFood;
import mods.dnd91.minecraft.hivecraft.structure.block.SlotOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class ContainerQueenNest extends Container{
	
	private TileEntityQueenNest queen = null;
	private int lastHatchTime = 0;
	private int lastFoodMeter = 0;
	
	
	public ContainerQueenNest(InventoryPlayer par1InventoryPlayer, TileEntityQueenNest ent)
    {
		this.queen = ent;
		this.addSlotToContainer(new Slot(ent, 0, 8, 13));
		this.addSlotToContainer(new Slot(ent, 1, 29, 13));
		this.addSlotToContainer(new Slot(ent, 2, 50, 13));
		this.addSlotToContainer(new SlotOutput(ent, 3, 112, 15)); //E
		this.addSlotToContainer(new SlotOutput(ent, 4, 132, 15));
		this.addSlotToContainer(new SlotOutput(ent, 5, 152, 15));
		this.addSlotToContainer(new SlotOutput(ent, 6, 112, 35));
		this.addSlotToContainer(new SlotOutput(ent, 7, 132, 35));
		this.addSlotToContainer(new SlotOutput(ent, 8, 152, 35));
		this.addSlotToContainer(new SlotOutput(ent, 9, 112, 55));
		this.addSlotToContainer(new SlotOutput(ent, 10, 132, 55));
		this.addSlotToContainer(new SlotOutput(ent, 11, 152, 55)); //EE
		this.addSlotToContainer(new SlotFood(ent, 12, 67, 55));
		this.addSlotToContainer(new Slot(ent, 13, 48, 76));
		this.addSlotToContainer(new Slot(ent, 14, 16, 96));
		this.addSlotToContainer(new Slot(ent, 15, 37, 96));
		this.addSlotToContainer(new Slot(ent, 16, 59, 96));
		this.addSlotToContainer(new Slot(ent, 17, 81, 96));
		this.addSlotToContainer(new Slot(ent, 18, 48, 116));
		
		for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }
	
	@Override
	public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.queen.hatchTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.queen.foodLevel);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
	@Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastHatchTime != this.queen.hatchTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.queen.hatchTime);
            }

            if (this.lastFoodMeter != this.queen.foodLevel)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.queen.foodLevel);
            }
        }

        this.lastHatchTime = this.queen.hatchTime;
        this.lastFoodMeter = this.queen.foodLevel;
    }
	
	@Override
	public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.queen.hatchTime = par2;
        }

        if (par1 == 1)
        {
            this.queen.foodLevel = par2;
        }

    }
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.queen.isUseableByPlayer(entityplayer);
	}

}
