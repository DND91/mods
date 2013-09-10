package mods.dnd91.minecraft.hivecraft.structure.queenNest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.structure.block.TileEntityHiveStructure;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityQueenNest extends TileEntityHiveStructure implements ISidedInventory{
	private ItemStack[] queensItemStacks = new ItemStack[19];
	
	public int hatchTime = 0;
	public int foodLevel = 0;
	
	private int hatcherRunTime = 1000;
	private int foodLimit = 10000;
	
	
	public TileEntityQueenNest(){
	}
	
	public TileEntityQueenNest(NBTTagCompound comp) {
		this.genetics = comp;
	}
	
	public void updateBlocks(World world, int x, int y, int z){
		for(int modX = -1; modX < 2; modX++)
		for(int modY = -1; modY < 2; modY++)
		for(int modZ = -1; modZ < 2; modZ++)
			world.markBlockForUpdate(x+modX, y+modY, z+modZ);
	}

	@Override
	public void updateEntity(){
		super.updateEntity();

		boolean flag = this.foodLevel > 0;
        boolean flag1 = false;
        
        if(this.queensItemStacks[12] != null && this.foodLevel < this.foodLimit){
        	ItemFood food = (ItemFood)this.queensItemStacks[12].getItem();
        	if(food != null){
        		if((this.foodLevel + food.getHealAmount()*food.getSaturationModifier()*500) <= this.foodLimit){
        			this.foodLevel += food.getHealAmount()*food.getSaturationModifier()*500;
        			
        			if(!worldObj.isRemote){
        			this.queensItemStacks[12].stackSize--;
        			if(this.queensItemStacks[12].stackSize == 0)
        				this.queensItemStacks[12] = null;
        			}
        		}
        	}
        }
        
        
        if (this.foodLevel > 0 && !isFull())
        {
            --this.foodLevel;
            ++this.hatchTime;
        }
        
        if(genetics != null){
        	
			this.hatcherRunTime = this.genetics.getInteger("matureTimeEgg");
        }

        if (!this.worldObj.isRemote)
        {
            if (flag)
            {                
                if (this.hatchTime >= hatcherRunTime && !isFull())
                {
                    this.hatchTime = 0;
                    this.layEgg();
                    flag1 = true;
                }
            }
            else
            {
                this.hatchTime = 0;
            }

            if (flag != this.hatchTime > 0)
            {
                flag1 = true;
                
            }
        }
        
        if (flag1)
        {
            this.onInventoryChanged();
        }
	}

	private void layEgg() {
		for(int i = 3; i < 12; i++)
			if(queensItemStacks[i] == null){
				int familyName = this.genetics.getInteger("familyName");
				int colorID = this.genetics.getInteger("colorID");
				NBTTagCompound compound = (NBTTagCompound)this.genetics.copy();
				ItemStack itemstack = new ItemStack(HiveCraft.itemEggsack, 1, familyName*(ItemDye.dyeColorNames.length)+colorID);
				itemstack.setTagCompound(compound);
				queensItemStacks[i] = itemstack;
				return;
			}
	}
	
	private boolean isFull(){
		for(int i = 3; i < 12; i++)
			if(queensItemStacks[i] == null)
				return false;
		return true;
	}
	
	public boolean isValid(){
		 for(int x = -1; x < 2; x++)
		 for(int y = 0; y < 3; y++)
		 for(int z = -1; z < 2; z++){
		 if(this.worldObj.getBlockId(x+(int)xCoord, y+(int)yCoord, z+(int)zCoord) != HiveCraft.queenNestID)
			 return false;
		 }
		 
		 return true;
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);

        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.queensItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.queensItemStacks.length)
            {
                this.queensItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.hatchTime = par1NBTTagCompound.getShort("HatchTime");
        this.foodLevel = par1NBTTagCompound.getShort("FoodLevel");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        
        par1NBTTagCompound.setShort("HatchTime", (short)this.hatchTime);
        par1NBTTagCompound.setShort("FoodLevel", (short)this.foodLevel);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.queensItemStacks.length; ++i)
        {
            if (this.queensItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.queensItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);
    }

	@Override
	public int getSizeInventory() {
		return queensItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return queensItemStacks[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.queensItemStacks[i] != null)
        {
            ItemStack itemstack;

            if (this.queensItemStacks[i].stackSize <= j)
            {
                itemstack = this.queensItemStacks[i];
                this.queensItemStacks[i] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.queensItemStacks[i].splitStack(j);

                if (this.queensItemStacks[i].stackSize == 0)
                {
                    this.queensItemStacks[i] = null;
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
		if (this.queensItemStacks[i] != null)
        {
            ItemStack itemstack = this.queensItemStacks[i];
            this.queensItemStacks[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.queensItemStacks[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
        	itemstack.stackSize = this.getInventoryStackLimit();
        }
	}

	@Override
	public String getInvName() {
		return "container.queennest";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	private int[] slotsintop = {0};
	
	
	
	@SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    public int getHatchProgressScaled(int par1)
    {
        return this.hatchTime * par1 / this.hatcherRunTime;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    public int getFoodProgressScaled(int par1)
    {
        return this.foodLevel * par1 / this.foodLimit;
    }

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return slotsintop;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return this.isStackValidForSlot(i, itemstack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return j != 0 || i != 1 || itemstack.itemID == Item.bucketEmpty.itemID;
	}
}
