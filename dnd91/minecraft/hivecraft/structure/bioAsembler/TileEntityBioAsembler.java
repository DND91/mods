package mods.dnd91.minecraft.hivecraft.structure.bioAsembler;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.structure.block.TileEntityHiveStructure;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBioAsembler extends TileEntityHiveStructure implements ISidedInventory {
	public ItemStack[] bioStacks = new ItemStack[13];
	
	public int currentFood = 0;
	private int maxFood = 10000;
	
	public int currentWater = 0;
	private int maxWater = 10000;
	
	public int currentBiomass = 0;
	private int maxBiomass = 10000;
	
	int costFood = 0;
	int costWater = 0;
	int costBiomass = 0;
	
	public TileEntityBioAsembler() {}
	
	public TileEntityBioAsembler(NBTTagCompound comp){
		
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		
		if(this.bioStacks[10] != null && this.currentFood < this.maxFood){
        	ItemFood food = (ItemFood)this.bioStacks[10].getItem();
        	if(food != null){
        		if((this.currentFood + food.getHealAmount()*food.getSaturationModifier()*500) <= this.maxFood){
        			this.currentFood += food.getHealAmount()*food.getSaturationModifier()*500;
        			
        			if(!worldObj.isRemote){
        				onCraftMatrixChanged(this);
        				this.decrStackSize(10, 1);
        			}
        		}
        	}
        }

		if(this.bioStacks[11] != null && this.currentWater < this.maxWater){
        	Item bucket = this.bioStacks[11].getItem();
        	if(bucket != null && bucket.itemID == Item.bucketWater.itemID){
        		if((this.currentWater + 1*500) <= this.maxWater){
        			this.currentWater += 1*500;
        			
        			if(!worldObj.isRemote){
        				onCraftMatrixChanged(this);
        				this.bioStacks[11] = new ItemStack(Item.bucketEmpty, 1);       				
        			}
        		}
        	}
        }
		
		if(this.bioStacks[12] != null && this.currentBiomass < this.maxBiomass){
        	Item bucket = this.bioStacks[12].getItem();
        	if(bucket != null && bucket.itemID == HiveCraft.itemBiomass.itemID){
        		if((this.currentBiomass + 1*500) <= this.maxBiomass){
        			this.currentBiomass += 1*500;
        			
        			if(!worldObj.isRemote){
        				onCraftMatrixChanged(this);
        				this.decrStackSize(12, 1);
        			}
        		}
        	}
        }
		
	}
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        
        this.currentFood = par1NBTTagCompound.getInteger("currentFood");
        this.currentWater = par1NBTTagCompound.getInteger("currentWater");
        this.currentBiomass = par1NBTTagCompound.getInteger("currentBiomass");
        
        this.costFood = par1NBTTagCompound.getInteger("costFood");
        this.costWater = par1NBTTagCompound.getInteger("costWater");
        this.costBiomass = par1NBTTagCompound.getInteger("costBiomass");

        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.bioStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.bioStacks.length)
            {
                this.bioStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        
        par1NBTTagCompound.setInteger("currentFood", currentFood);
        par1NBTTagCompound.setInteger("currentWater", currentWater);
        par1NBTTagCompound.setInteger("currentBiomass", currentBiomass);
        
        par1NBTTagCompound.setInteger("costFood", costFood);
        par1NBTTagCompound.setInteger("costWater", costWater);
        par1NBTTagCompound.setInteger("costBiomass", costBiomass);
        
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.bioStacks.length; ++i)
        {
            if (this.bioStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.bioStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);
    }
	

	@Override
	public int getSizeInventory() {
		return bioStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return bioStacks[i];
	}

	private void lessenCrafted(){
		IBioAsemblerRecipe irep = BioAsemblerManager.getInstance().findMatchingIRecipe(this, this.worldObj);
		
		this.currentFood -= irep.getCostFood();
		this.currentWater -= irep.getCostWater();
		this.currentBiomass -= irep.getCostBiomass();
		
		for(int i = 0; i < 9; i++)
			decrStackSize(i,1);
	}
	
	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.bioStacks[i] != null)
        {
            ItemStack itemstack;

            if (this.bioStacks[i].stackSize <= j)
            {
                itemstack = this.bioStacks[i];
                this.bioStacks[i] = null;
                if(i < 9)
                	this.onCraftMatrixChanged(this);
                else if(i == 9)
                	lessenCrafted();
                return itemstack;
            }
            else
            {
                itemstack = this.bioStacks[i].splitStack(j);

                if (this.bioStacks[i].stackSize == 0)
                {
                    this.bioStacks[i] = null;
                }
                if(i < 9)
                	this.onCraftMatrixChanged(this);
                else if(i == 9)
                	lessenCrafted();
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
		if (this.bioStacks[i] != null)
        {
            ItemStack itemstack = this.bioStacks[i];
            this.bioStacks[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.bioStacks[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
        	itemstack.stackSize = this.getInventoryStackLimit();
        }
        
        if(i < 9)
        	this.onCraftMatrixChanged(this);
	}

	@Override
	public String getInvName() {
		return "container.bioasembler";
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
	public void openChest() {	}

	@Override
	public void closeChest() {	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
	
	public void onCraftMatrixChanged(IInventory par1IInventory)
    {
		bioStacks[9] = BioAsemblerManager.getInstance().findMatchingRecipe(this, this.worldObj);
    
		if(bioStacks[9] != null){
			IBioAsemblerRecipe irep = BioAsemblerManager.getInstance().findMatchingIRecipe(this, worldObj);
			costFood = irep.getCostFood();
			costWater = irep.getCostWater();
			costBiomass = irep.getCostBiomass();
		}else{
			costFood = -1;
			costWater = -1;
			costBiomass = -1;
		}
    }
	
	public ItemStack getStackInRowAndColumn(int par1, int par2)
    {
        if (par1 >= 0 && par1 < 3)
        {
            int k = par1 + par2 * 3;
            return this.getStackInSlot(k);
        }
        else
        {
            return null;
        }
    }
	
	public int getFoodProgressScaled(int par1)
    {
        return this.currentFood * par1 / maxFood;
    }
    
    public int getWaterProgressScaled(int par1)
    {
        return this.currentWater * par1 / this.maxWater;
    }
    
    public int getBiomassProgressScaled(int par1)
    {
        return this.currentBiomass * par1 / this.maxBiomass;
    }
    
    public int getFoodCostScale(int par1)
    {
        return this.costFood * par1 / maxFood;
    }
    
    public int getWaterCostScale(int par1)
    {
        return this.costWater * par1 / this.maxWater;
    }
    
    public int getBiomassCostScale(int par1)
    {
        return this.costBiomass * par1 / this.maxBiomass;
    }

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
