package mods.dnd91.minecraft.hivecraft.backlog;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.genetics.Genetics;
import mods.dnd91.minecraft.hivecraft.genetics.Genpool;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ForgeDummyContainer;

public class TileEntityAscender extends TileEntity implements ISidedInventory {
	public boolean isActive;
	private static final int[] field_102010_d = new int[] {0};
    private static final int[] field_102011_e = new int[] {2, 1};
    private static final int[] field_102009_f = new int[] {1};
    private static Random random = new Random();

    /**
     * The ItemStacks that hold the items currently being used in the ascender
     */
    private ItemStack[] ascenderItemStacks = new ItemStack[3];

    /** The number of ticks that the ascender will keep burning */
    public int ascenderBurnTime = 0;

    private int ascenderRunTime = 800;
    
    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the ascender burning for
     */
    public int currentItemBurnTime = 0;

    /** The number of ticks that the current item has been cooking for */
    public int ascenderCookTime = 0;
    private String field_94130_e;
    
    public TileEntityAscender() {}

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.ascenderItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
        return this.ascenderItemStacks[par1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.ascenderItemStacks[par1] != null)
        {
            ItemStack itemstack;

            if (this.ascenderItemStacks[par1].stackSize <= par2)
            {
                itemstack = this.ascenderItemStacks[par1];
                this.ascenderItemStacks[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.ascenderItemStacks[par1].splitStack(par2);

                if (this.ascenderItemStacks[par1].stackSize == 0)
                {
                    this.ascenderItemStacks[par1] = null;
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
        if (this.ascenderItemStacks[par1] != null)
        {
            ItemStack itemstack = this.ascenderItemStacks[par1];
            this.ascenderItemStacks[par1] = null;
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
        this.ascenderItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return this.isInvNameLocalized() ? this.field_94130_e : "container.ascender";
    }

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    public boolean isInvNameLocalized()
    {
        return this.field_94130_e != null && this.field_94130_e.length() > 0;
    }

    public void func_94129_a(String par1Str)
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
        this.ascenderItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.ascenderItemStacks.length)
            {
                this.ascenderItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.ascenderBurnTime = par1NBTTagCompound.getShort("BurnTime");
        this.ascenderCookTime = par1NBTTagCompound.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.ascenderItemStacks[1]);

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
        par1NBTTagCompound.setShort("BurnTime", (short)this.ascenderBurnTime);
        par1NBTTagCompound.setShort("CookTime", (short)this.ascenderCookTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.ascenderItemStacks.length; ++i)
        {
            if (this.ascenderItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.ascenderItemStacks[i].writeToNBT(nbttagcompound1);
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
        return this.ascenderCookTime * par1 / ascenderRunTime;
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
            this.currentItemBurnTime = ascenderRunTime;
        }

        return this.ascenderBurnTime * par1 / this.currentItemBurnTime;
    }

    /**
     * Returns true if the ascender is currently burning
     */
    public boolean isBurning()
    {
        return this.ascenderBurnTime > 0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean flag = this.ascenderBurnTime > 0;
        boolean flag1 = false;

        if (this.ascenderBurnTime > 0)
        {
            --this.ascenderBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.ascenderBurnTime == 0 && this.canSmelt())
            {
                this.currentItemBurnTime = this.ascenderBurnTime = getItemBurnTime(this.ascenderItemStacks[1]);

                if (this.ascenderBurnTime > 0)
                {
                    flag1 = true;

                    if (this.ascenderItemStacks[1] != null)
                    {
                        --this.ascenderItemStacks[1].stackSize;

                        if (this.ascenderItemStacks[1].stackSize == 0)
                        {
                            this.ascenderItemStacks[1] = this.ascenderItemStacks[1].getItem().getContainerItemStack(ascenderItemStacks[1]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.ascenderCookTime;

                if (this.ascenderCookTime == ascenderRunTime)
                {
                    this.ascenderCookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            }
            else
            {
                this.ascenderCookTime = 0;
            }

            if (flag != this.ascenderBurnTime > 0)
            {
                flag1 = true;
                this.isActive = this.ascenderBurnTime > 0;
                //BlockAscender.updateAscenderBlockState(this.ascenderBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1)
        {
            this.onInventoryChanged();
        }
    }

    /**
     * Returns true if the ascender can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (this.ascenderItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            if (!AscenderRecipes.ascending().canAscend(this.ascenderItemStacks[0])) return false;
            if (this.ascenderItemStacks[2] == null) return true;
            return false;
        }
    }

    /**
     * Turn one item from the ascender source stack into the appropriate smelted item in the ascender result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
        	if((random.nextInt(10)) == 0 || true){ //TODO: SET TO BETTER VALUE
        		ItemStack itemStack = Genpool.getRandomEggsack();
        		if (this.ascenderItemStacks[2] == null)
                {
                    this.ascenderItemStacks[2] = itemStack.copy();
                }
        	}

            --this.ascenderItemStacks[0].stackSize;

            if (this.ascenderItemStacks[0].stackSize <= 0)
            {
                this.ascenderItemStacks[0] = null;
            }
        }
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the ascender burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack par0ItemStack)
    {
        if (par0ItemStack == null)
        {
            return 0;
        }
        else
        {
            int i = par0ItemStack.getItem().itemID;
            Item item = par0ItemStack.getItem();
            
            if(HiveCraft.itemBiomass.itemID == i)
            {
            	return 20000;
            }

            return 0;
        }
    }

    /**
     * Return true if item is a fuel source (getItemBurnTime() > 0).
     */
    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getItemBurnTime(par0ItemStack) > 0;
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
        return par1 == 2 ? false : (par1 == 1 ? isItemFuel(par2ItemStack) : true);
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
		return null;
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
