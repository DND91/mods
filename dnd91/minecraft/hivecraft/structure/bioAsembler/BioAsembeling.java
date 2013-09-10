package mods.dnd91.minecraft.hivecraft.structure.bioAsembler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class BioAsembeling implements IInventory {

	private ItemStack[] stackList;
	
	private int inventoryWidth;
	
	private Container eventHandler;
	
	public BioAsembeling(Container cont, int x, int y){
		int k = x*y;
		this.stackList = new ItemStack[k];
		this.eventHandler = cont;
		this.inventoryWidth = x;
	}
	
	@Override
	public int getSizeInventory() {
		return stackList.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		// TODO Auto-generated method stub
		return i >= this.getSizeInventory() ? null : this.stackList[i];
	}
	
	public ItemStack getStackInRowAndColumn(int par1, int par2)
    {
        if (par1 >= 0 && par1 < this.inventoryWidth)
        {
            int k = par1 + par2 * this.inventoryWidth;
            return this.getStackInSlot(k);
        }
        else
        {
            return null;
        }
    }

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.stackList[i] != null)
        {
            ItemStack itemstack;

            if (this.stackList[i].stackSize <= j)
            {
                itemstack = this.stackList[i];
                this.stackList[i] = null;
                this.eventHandler.onCraftMatrixChanged(this);
                return itemstack;
            }
            else
            {
                itemstack = this.stackList[i].splitStack(j);

                if (this.stackList[i].stackSize == 0)
                {
                    this.stackList[i] = null;
                }

                this.eventHandler.onCraftMatrixChanged(this);
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
		 if (this.stackList[i] != null)
	        {
	            ItemStack itemstack = this.stackList[i];
	            this.stackList[i] = null;
	            return itemstack;
	        }
	        else
	        {
	            return null;
	        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.stackList[i] = itemstack;
        this.eventHandler.onCraftMatrixChanged(this);
	}

	@Override
	public String getInvName() {
		return "container.bioasebeling";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public void onInventoryChanged() {	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {	}

	@Override
	public void closeChest() {	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

}
