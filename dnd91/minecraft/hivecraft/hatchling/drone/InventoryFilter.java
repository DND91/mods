package mods.dnd91.minecraft.hivecraft.hatchling.drone;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class InventoryFilter implements IInventory{
	public ItemStack[] filterArray = new ItemStack[1];
	public int filterSize = 1;
	@Override
	public int getSizeInventory() {
		if(filterArray == null)
			return 0;
		else
			return filterArray.length;
	}
	@Override
	public ItemStack decrStackSize(int i, int j) {
		if(filterArray == null)
			return null;
		
		if (this.filterArray[i] != null)
        {
            ItemStack itemstack;

            if (this.filterArray[i].stackSize <= j)
            {
                itemstack = this.filterArray[i];
                this.filterArray[i] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.filterArray[i].splitStack(j);

                if (this.filterArray[i].stackSize == 0)
                {
                    this.filterArray[i] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if(filterArray == null)
			return null;
		
		if (this.filterArray[i] != null)
        {
            ItemStack itemstack = this.filterArray[i];
            this.filterArray[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.filterArray[i] = itemstack;

	    if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
	    {
	    	itemstack.stackSize = this.getInventoryStackLimit();
	    }

	    this.onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "container.hatchling";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		if(filterArray == null)
			return 0;
		return 64;
	}

	@Override
	public void onInventoryChanged() { }

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return false;
	}

	@Override
	public void openChest() { }

	@Override
	public void closeChest() { }

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return false;
	}
	@Override
	public ItemStack getStackInSlot(int i) {
		if(filterArray == null)
			return null;
		else
			return filterArray[i];
	}
	
}
