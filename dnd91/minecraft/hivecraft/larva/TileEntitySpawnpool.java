package mods.dnd91.minecraft.hivecraft.larva;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.HiveCraftWorldData;
import mods.dnd91.minecraft.hivecraft.backlog.AscenderRecipes;
import mods.dnd91.minecraft.hivecraft.genetics.Genetics;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ForgeDummyContainer;

public class TileEntitySpawnpool extends TileEntity implements ISidedInventory {
	public boolean isActive;
	private static final int[] field_102010_d = new int[] {0};
    private static final int[] field_102011_e = new int[] {2, 1};
    private static final int[] field_102009_f = new int[] {1};
    private static Random random = new Random();
    Genetics genetics = null;

    /**
     * The ItemStacks that hold the items currently being used in the spawnpool
     */
    private ItemStack[] spawnpoolItemStacks = new ItemStack[7];

    /** The number of ticks that the spawnpool will keep burning */
    public int spawnpoolBurnTime = 0;

    private int spawnpoolRunTime = 800;
    
    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the spawnpool burning for
     */
    public int currentItemBurnTime = 0;
    public int currentFoodLevel = 0;
    public int maxFoodLevel = 10000;

    /** The number of ticks that the current item has been cooking for */
    public int spawnpoolCookTime = 0;
    private String field_94130_e;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.spawnpoolItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
        return this.spawnpoolItemStacks[par1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.spawnpoolItemStacks[par1] != null)
        {
            ItemStack itemstack;

            if (this.spawnpoolItemStacks[par1].stackSize <= par2)
            {
                itemstack = this.spawnpoolItemStacks[par1];
                this.spawnpoolItemStacks[par1] = null;
                if(par1 == 2){
                	genetics = null;
                }
                if(!this.canEvolve())
                	this.spawnpoolCookTime = 0;
                
                return itemstack;
            }
            else
            {
                itemstack = this.spawnpoolItemStacks[par1].splitStack(par2);

                if (this.spawnpoolItemStacks[par1].stackSize == 0)
                {
                    this.spawnpoolItemStacks[par1] = null;
                    if(par1 == 2)
                    	genetics = null;
                    if(!this.canEvolve())
                    	this.spawnpoolCookTime = 0;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.spawnpoolItemStacks[par1] != null)
        {
            ItemStack itemstack = this.spawnpoolItemStacks[par1];
            this.spawnpoolItemStacks[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.spawnpoolItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
        if(par1 == 2 && par2ItemStack != null){
        	genetics = new Genetics(par2ItemStack.stackTagCompound.getCompoundTag("genetics"));
        	this.spawnpoolCookTime = 0;
        }
        
        if(par1 == 0 || par1 == 2)
        	this.spawnpoolCookTime = 0;
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return this.isInvNameLocalized() ? this.field_94130_e : "container.spawnpool";
    }

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    public boolean isInvNameLocalized()
    {
        return this.field_94130_e != null && this.field_94130_e.length() > 0;
    }

    public void setInvNameLocalized(String par1Str)
    {
        this.field_94130_e = par1Str;
        
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.spawnpoolItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.spawnpoolItemStacks.length)
            {
                this.spawnpoolItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
                if(b0 == 2){
                	genetics = new Genetics(spawnpoolItemStacks[b0].getTagCompound().getCompoundTag("genetics"));
                }
            }
        }

        this.spawnpoolBurnTime = par1NBTTagCompound.getShort("BurnTime");
        this.spawnpoolCookTime = par1NBTTagCompound.getShort("CookTime");
        this.currentFoodLevel = par1NBTTagCompound.getShort("currentFoodLevel");

        if (par1NBTTagCompound.hasKey("CustomName"))
        {
            this.field_94130_e = par1NBTTagCompound.getString("CustomName");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("BurnTime", (short)this.spawnpoolBurnTime);
        par1NBTTagCompound.setShort("CookTime", (short)this.spawnpoolCookTime);
        par1NBTTagCompound.setShort("currentFoodLevel", (short)this.currentFoodLevel);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.spawnpoolItemStacks.length; ++i)
        {
            if (this.spawnpoolItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.spawnpoolItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

        if (this.isInvNameLocalized())
        {
            par1NBTTagCompound.setString("CustomName", this.field_94130_e);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    public int getCookProgressScaled(int par1)
    {
        return this.spawnpoolCookTime * par1 / spawnpoolRunTime;
    }
    
    public int getFoodProgressScaled(int par1)
    {
        return this.currentFoodLevel * par1 / this.maxFoodLevel;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = spawnpoolRunTime;
        }

        return this.spawnpoolBurnTime * par1 / this.currentItemBurnTime;
    }

    /**
     * Returns true if the spawnpool is currently burning
     */
    public boolean isBurning()
    {
        return this.spawnpoolBurnTime > 0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {        
        if(this.spawnpoolItemStacks[1] != null && this.currentFoodLevel < this.maxFoodLevel){
        	ItemFood food = (ItemFood)this.spawnpoolItemStacks[1].getItem();
        	if(food != null){
        		if((this.currentFoodLevel + food.getHealAmount()*food.getSaturationModifier()*500) <= this.maxFoodLevel){
        			this.currentFoodLevel += food.getHealAmount()*food.getSaturationModifier()*500;
        			
        			if(!worldObj.isRemote){
        			this.spawnpoolItemStacks[1].stackSize--;
        			if(this.spawnpoolItemStacks[1].stackSize == 0)
        				this.spawnpoolItemStacks[1] = null;
        			}
        		}
        	}
        }

        if (this.currentFoodLevel > 0 && this.spawnpoolItemStacks[0] != null && this.spawnpoolItemStacks[2] != null && this.canEvolve())
        {
            --this.currentFoodLevel;
            ++spawnpoolCookTime;
        }

        if (!this.worldObj.isRemote)
        {
        	if(this.canEvolve() && genetics != null){
        		this.spawnpoolRunTime = genetics.matureTimeLarva();
        		if (this.spawnpoolCookTime >= this.spawnpoolRunTime)
        		{
        			spawnpoolCookTime = 0;
        			this.evolveItem();
        		}
        	}
        }
    }
    
    @Override
    public void onInventoryChanged(){ //TODO: Fix if player trys to cheat with larva
    	super.onInventoryChanged();
    	//spawnpoolBurnTime = this.spawnpoolRunTime;
    }

    /**
     * Returns true if the spawnpool can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canEvolve()
    {
        if (this.spawnpoolItemStacks[2] == null)
        {
            return false;
        }
        else
        {
            if (!SpawnpoolRecipes.spawnpool().canEvolve(this.worldObj, this.spawnpoolItemStacks[0], this.spawnpoolItemStacks[2], this.spawnpoolItemStacks[3], this.spawnpoolItemStacks[4], this.spawnpoolItemStacks[5])) return false;
            if (this.spawnpoolItemStacks[6] == null) return true;
            return false;
        }
    }

    /**
     * Turn one item from the spawnpool source stack into the appropriate smelted item in the spawnpool result stack
     */
    public void evolveItem()
    {
        if (this.canEvolve())
        {
        	//TODO: Fix Transformation("Recipes") for larvas to hatchlings
        	ItemStack itemStack = SpawnpoolRecipes.spawnpool().getHatchlingFromLarva(worldObj, this.spawnpoolItemStacks[0], this.spawnpoolItemStacks[2], this.spawnpoolItemStacks[3], this.spawnpoolItemStacks[4], this.spawnpoolItemStacks[5]);
        	if(itemStack == null)
        		return;
           this.spawnpoolItemStacks[6] = itemStack;
      	
           if(this.spawnpoolItemStacks[0] != null)
        	   --this.spawnpoolItemStacks[0].stackSize;
           if(this.spawnpoolItemStacks[2] != null)
        	   --this.spawnpoolItemStacks[2].stackSize;
           if(this.spawnpoolItemStacks[3] != null)
           		--this.spawnpoolItemStacks[3].stackSize;
            
            for(int i = 0; i < this.spawnpoolItemStacks.length; i++)
            	if (this.spawnpoolItemStacks[i] != null && this.spawnpoolItemStacks[i].stackSize <= 0)
                {
                    this.spawnpoolItemStacks[i] = null;
                    if(i == 2)
                    	genetics = null;
                }
        }
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return false;
    }

    /**
     * Get the size of the side inventory.
     */
    public int[] getSizeInventorySide(int par1)
    {
        return par1 == 0 ? field_102011_e : (par1 == 1 ? field_102010_d : field_102009_f);
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
	
	@Override
	public Packet getDescriptionPacket()
    {
		NBTTagCompound compound = new NBTTagCompound();
		NBTTagList list = new NBTTagList();
		if(this.spawnpoolItemStacks[2] != null){
			NBTTagCompound item = new NBTTagCompound();
			this.spawnpoolItemStacks[2].writeToNBT(item);
			item.setInteger("slot", 2);
			list.appendTag(item);
		}
		if(this.spawnpoolItemStacks[6] != null){
			NBTTagCompound item = new NBTTagCompound();
			this.spawnpoolItemStacks[6].writeToNBT(item);
			item.setInteger("slot", 6);
			list.appendTag(item);
		}
		compound.setTag("items", list);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, compound);
    }
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
		NBTTagCompound compound = pkt.customParam1;
		if(!compound.hasKey("items"))
			return;
		NBTTagList list = compound.getTagList("items");
		for(int i = 0; i < list.tagCount(); i++){
			NBTTagCompound item = (NBTTagCompound) list.tagAt(i);
			ItemStack temp = ItemStack.loadItemStackFromNBT(item);
			int slot = item.getInteger("slot");
			this.setInventorySlotContents(slot, temp);
		}
		this.onInventoryChanged();
    }
			
}
