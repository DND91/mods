package mods.dnd91.minecraft.hivecraft.larva;

import mods.dnd91.minecraft.hivecraft.IMutation;
import mods.dnd91.minecraft.hivecraft.eggs.IEgg;
import mods.dnd91.minecraft.hivecraft.eggs.SlotBiomass;
import mods.dnd91.minecraft.hivecraft.eggs.SlotFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ContainerSpawnpool extends Container {

	private TileEntitySpawnpool spawnpool;
    private int lastCookTime = 0;
    private int lastBurnTime = 0;
    private int lastItemBurnTime = 0;

    public ContainerSpawnpool(InventoryPlayer par1InventoryPlayer, TileEntitySpawnpool par2TileEntitySpawnpool)
    {
        this.spawnpool = par2TileEntitySpawnpool; 
        this.addSlotToContainer(new Slot(par2TileEntitySpawnpool, 0, 15, 15)); //To become
        this.addSlotToContainer(new SlotFood(par2TileEntitySpawnpool, 1, 57, 15));
        this.addSlotToContainer(new SlotLarva(par2TileEntitySpawnpool, 2, 36, 36));
        this.addSlotToContainer(new Slot(par2TileEntitySpawnpool, 3, 15, 58)); //Mutation
        this.addSlotToContainer(new Slot(par2TileEntitySpawnpool, 4, 36, 58)); //Mutation
        this.addSlotToContainer(new Slot(par2TileEntitySpawnpool, 5, 57, 58)); //Mutation
        this.addSlotToContainer(new SlotSpawnpool(par1InventoryPlayer.player, par2TileEntitySpawnpool, 6, 116, 35));
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.spawnpool.spawnpoolCookTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.spawnpool.spawnpoolBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.spawnpool.currentItemBurnTime);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.spawnpool.spawnpoolCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.spawnpool.spawnpoolCookTime);
            }

            if (this.lastBurnTime != this.spawnpool.spawnpoolBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.spawnpool.spawnpoolBurnTime);
            }

            if (this.lastItemBurnTime != this.spawnpool.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.spawnpool.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.spawnpool.spawnpoolCookTime;
        this.lastBurnTime = this.spawnpool.spawnpoolBurnTime;
        this.lastItemBurnTime = this.spawnpool.currentItemBurnTime;
    }

   // @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.spawnpool.spawnpoolCookTime = par2;
        }

        if (par1 == 1)
        {
            this.spawnpool.spawnpoolBurnTime = par2;
        }

        if (par1 == 2)
        {
            this.spawnpool.currentItemBurnTime = par2;
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.spawnpool.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     * // 0 - To become, 1 - Food, 2 - Larva, 3-5 - Mutation, 6 - Spawn, 7+ Player
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 6)
            {
                if (!this.mergeItemStack(itemstack1, 7, 43, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (!(0 <= par2 && par2 <= 5))
            {
                if (itemstack1.getItem() instanceof ILarva)
                {
                    if (!this.mergeItemStack(itemstack1, 2, 3, false))
                    {
                        return null;
                    }
                }
                else if (itemstack1.getItem() instanceof ItemFood)
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (itemstack1.getItem() instanceof IMutation)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    if (!this.mergeItemStack(itemstack1, 3, 6, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 7 && par2 < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 34, 43, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 34 && par2 < 43 && !this.mergeItemStack(itemstack1, 7, 34, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 7, 43, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }

}
