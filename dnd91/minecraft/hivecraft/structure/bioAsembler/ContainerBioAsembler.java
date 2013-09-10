package mods.dnd91.minecraft.hivecraft.structure.bioAsembler;

import mods.dnd91.minecraft.hivecraft.eggs.SlotFood;
import mods.dnd91.minecraft.hivecraft.structure.block.SlotOutput;
import mods.dnd91.minecraft.hivecraft.structure.queenNest.TileEntityQueenNest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class ContainerBioAsembler extends Container{
	
	private TileEntityBioAsembler asembler = null;
	public BioAsembelingResult bioResult = new BioAsembelingResult();
	
	private int lastFoodLevel = 0;
    private int lastWaterLevel = 0;
    private int lastBiomassLevel = 0;
    
    private int lastCostFood = 0;
    private int lastCostWater = 0;
    private int lastCostBiomass = 0;
	
	
	public ContainerBioAsembler(InventoryPlayer par1InventoryPlayer, TileEntityBioAsembler ent)
    {
		this.asembler = ent;
		this.addSlotToContainer(new Slot(ent, 0, 9, 13));
		this.addSlotToContainer(new Slot(ent, 1, 29, 13));
		this.addSlotToContainer(new Slot(ent, 2, 49, 13));
		this.addSlotToContainer(new Slot(ent, 3, 9, 33));
		this.addSlotToContainer(new Slot(ent, 4, 29, 33));
		this.addSlotToContainer(new Slot(ent, 5, 49, 33));
		this.addSlotToContainer(new Slot(ent, 6, 9, 53));
		this.addSlotToContainer(new Slot(ent, 7, 29, 53));
		this.addSlotToContainer(new Slot(ent, 8, 49, 53));
		
		this.addSlotToContainer(new SlotOutput(ent, 9, 73, 33)); //CO
		
		this.addSlotToContainer(new SlotFood(ent, 10, 102, 68));
		this.addSlotToContainer(new Slot(ent, 11, 127, 68));
		this.addSlotToContainer(new Slot(ent, 12, 152, 68));
		
		for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return asembler.isUseableByPlayer(entityplayer);
	}
	
	public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.asembler.currentFood);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.asembler.currentWater);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.asembler.currentBiomass);
        par1ICrafting.sendProgressBarUpdate(this, 3, this.asembler.costFood);
        par1ICrafting.sendProgressBarUpdate(this, 4, this.asembler.costWater);
        par1ICrafting.sendProgressBarUpdate(this, 5, this.asembler.costBiomass);
    }
	
	 public void detectAndSendChanges()
	    {
	        super.detectAndSendChanges();

	        for (int i = 0; i < this.crafters.size(); ++i)
	        {
	            ICrafting icrafting = (ICrafting)this.crafters.get(i);

	            if (this.lastFoodLevel != this.asembler.currentFood)
	            {
	                icrafting.sendProgressBarUpdate(this, 0, this.asembler.currentFood);
	            }

	            if (this.lastWaterLevel != this.asembler.currentWater)
	            {
	                icrafting.sendProgressBarUpdate(this, 1, this.asembler.currentWater);
	            }

	            if (this.lastBiomassLevel != this.asembler.currentBiomass)
	            {
	                icrafting.sendProgressBarUpdate(this, 2, this.asembler.currentBiomass);
	            }
	            
	            if (this.lastCostFood != this.asembler.costFood)
	            {
	                icrafting.sendProgressBarUpdate(this, 3, this.asembler.costFood);
	            }
	            
	            if (this.lastCostWater != this.asembler.costWater)
	            {
	                icrafting.sendProgressBarUpdate(this, 4, this.asembler.costWater);
	            }
	            
	            if (this.lastCostBiomass != this.asembler.costBiomass)
	            {
	                icrafting.sendProgressBarUpdate(this, 5, this.asembler.costBiomass);
	            }
	        }

	        this.lastFoodLevel = this.asembler.currentFood;
	        this.lastWaterLevel = this.asembler.currentWater;
	        this.lastBiomassLevel = this.asembler.currentBiomass;
	        
	        this.lastCostFood = this.asembler.costFood;
	        this.lastCostWater = this.asembler.costWater;
	        this.lastCostBiomass = this.asembler.costBiomass;
	    }

	   // @SideOnly(Side.CLIENT)
	    public void updateProgressBar(int par1, int par2)
	    {
	        if (par1 == 0)
	        {
	            this.asembler.currentFood = par2;
	        }

	        if (par1 == 1)
	        {
	            this.asembler.currentWater = par2;
	        }

	        if (par1 == 2)
	        {
	            this.asembler.currentBiomass = par2;
	        }
	        
	        if (par1 == 3)
	        {
	            this.asembler.costFood = par2;
	        }
	        
	        if (par1 == 4)
	        {
	            this.asembler.costWater = par2;
	        }
	        
	        if (par1 == 5)
	        {
	            this.asembler.costBiomass = par2;
	        }
	    }

}
