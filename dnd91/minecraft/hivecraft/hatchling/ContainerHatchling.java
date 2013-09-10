package mods.dnd91.minecraft.hivecraft.hatchling;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerHatchling extends Container{
	private EntityHatchling hatchling;
	private int lastInventorySize = 0;
	
	public ContainerHatchling(EntityHatchling h){
		this.hatchling = h;
		if(h.inventory != null){
			for(int i = 0; i < h.inventory.length; i++){
				this.addSlotToContainer(new Slot(h, i, i*15, 0));
			}
		}
	}
	
	public void setSize(int i){
		hatchling.inventorySize = i;
		hatchling.inventory = new ItemStack[i];
		this.inventorySlots.clear();
		for(int j = 0; j < i; j++){
			this.addSlotToContainer(new Slot(hatchling, j, j*15, 0));
		}
		this.detectAndSendChanges();
	}
	
	public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.hatchling.inventorySize);
    }
	
	 public void detectAndSendChanges()
	    {
	        super.detectAndSendChanges();

	        for (int i = 0; i < this.crafters.size(); ++i)
	        {
	            ICrafting icrafting = (ICrafting)this.crafters.get(i);

	            if (this.lastInventorySize != this.hatchling.inventorySize)
	            {
	                icrafting.sendProgressBarUpdate(this, 0, this.hatchling.inventorySize);
	            }
	        }

	        this.lastInventorySize = this.hatchling.inventorySize;
	    }
	 
	 
	 @SideOnly(Side.CLIENT)
	 public void updateProgressBar(int par1, int par2)
	 {
	     if (par1 == 0)
	     {
	    	 System.out.println("MEEP!");
	         this.setSize(par2);
	     }
	 }

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return false;
	}
	
	
	
}
