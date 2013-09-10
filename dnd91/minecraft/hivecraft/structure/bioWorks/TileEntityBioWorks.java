package mods.dnd91.minecraft.hivecraft.structure.bioWorks;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.structure.block.TileEntityHiveStructure;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityBioWorks extends TileEntityHiveStructure implements ISidedInventory {
	private ItemStack[] bioworksInventory = new ItemStack[38];
	
	public TileEntityBioWorks(){
	}
	
	public TileEntityBioWorks(NBTTagCompound comp) {
		this.genetics = comp;
	}
	
	public boolean isFull(){
		for(int i = 0; i < 36; i++)
			if(bioworksInventory[i] == null || bioworksInventory[i].stackSize < 64)
				return false;
		return true;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if(this.worldObj.isRemote || !this.isMaster())
			return;
		
		for(int i = 1; i < 3; i++){
			int tmpY = this.yCoord+i;
			if(this.worldObj.isAirBlock(xCoord, tmpY, zCoord))
			if(this.bioworksInventory[36] != null && this.bioworksInventory[37] != null)
			if(this.bioworksInventory[36].getItem().itemID == Item.bucketWater.itemID)
			if(this.bioworksInventory[37].getItem().itemID == HiveCraft.itemBiomass.itemID)
			if(this.bioworksInventory[37].stackSize >= 8){
				this.bioworksInventory[36] = new ItemStack(Item.bucketEmpty);
				this.bioworksInventory[37].stackSize -= 8;
				if(this.bioworksInventory[37].stackSize == 0)
					this.bioworksInventory[37] = null;
				this.worldObj.setBlock(xCoord, tmpY, zCoord, HiveCraft.blockBioAcidStill.blockID);
			}
			
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);

        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.bioworksInventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.bioworksInventory.length)
            {
                this.bioworksInventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

    }
	
	@Override
    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.bioworksInventory.length; ++i)
        {
            if (this.bioworksInventory[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.bioworksInventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);
    }
	
	@Override
	public int getSizeInventory() {
		return bioworksInventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return bioworksInventory[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.bioworksInventory[i] != null)
        {
            ItemStack itemstack;

            if (this.bioworksInventory[i].stackSize <= j)
            {
                itemstack = this.bioworksInventory[i];
                this.bioworksInventory[i] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.bioworksInventory[i].splitStack(j);

                if (this.bioworksInventory[i].stackSize == 0)
                {
                    this.bioworksInventory[i] = null;
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
		if (this.bioworksInventory[i] != null)
        {
            ItemStack itemstack = this.bioworksInventory[i];
            this.bioworksInventory[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.bioworksInventory[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
        	itemstack.stackSize = this.getInventoryStackLimit();
        }
	}

	@Override
	public String getInvName() {
		return "container.bioworks";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 16;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {	}

	@Override
	public void closeChest() {	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return i >= 36;
	}

	private int[] slotsintop = {0};
	
	public void insertBiomass(ItemStack itemStack){
		for(int i = 0; i < 36; i++){
			if(bioworksInventory[i] == null){
				bioworksInventory[i] = new ItemStack(itemStack.getItem(), itemStack.stackSize);
				itemStack.stackSize = 0;
				return;
			}
			
			if(bioworksInventory[i].getItem() != itemStack.getItem())
				return;
			
			if(bioworksInventory[i].stackSize == this.getInventoryStackLimit())
				continue;
			
			int j = this.getInventoryStackLimit() - bioworksInventory[i].stackSize;
			if(itemStack.stackSize <= j){
				bioworksInventory[i].stackSize += itemStack.stackSize;
				itemStack.stackSize = 0;
				return;
			}else{
				bioworksInventory[i].stackSize += j;
				itemStack.stackSize -= j;
			}	
		}
	}


	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return null;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return this.isStackValidForSlot(i, itemstack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return false;
	}
}
