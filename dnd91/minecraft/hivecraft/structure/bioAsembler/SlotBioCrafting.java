package mods.dnd91.minecraft.hivecraft.structure.bioAsembler;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class SlotBioCrafting extends Slot{

	private TileEntityBioAsembler asembler;
	private int startMatrix, lengthMatrix;
	
	private EntityPlayer thePlayer;
	
	private int amountCrafted;
	
	public SlotBioCrafting(EntityPlayer player, TileEntityBioAsembler a, IInventory r, int sM, int lM, int p, int x,
			int y) {
		super(r, p, x, y);
		thePlayer = player;
		startMatrix = sM;
		lengthMatrix = lM;
		asembler = a;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack){
		return false;
	}
	
	@Override
	public ItemStack decrStackSize(int par1)
    {
        if (this.getHasStack())
        {
            this.amountCrafted += Math.min(par1, this.getStack().stackSize);
        }

        return super.decrStackSize(par1);
    }
	
	protected void onCrafting(ItemStack par1ItemStack, int par2)
    {
        this.amountCrafted += par2;
        this.onCrafting(par1ItemStack);
    }
	
	protected void onCrafting(ItemStack par1ItemStack)
    {
        par1ItemStack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.amountCrafted);
        this.amountCrafted = 0;

        //TODO: ADD ACHIVMENTS FOR CRAFTING LOOK AT SlotCrafting
    }

	public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
        GameRegistry.onItemCrafted(par1EntityPlayer, par2ItemStack, asembler); //TODO HMMM??? What does this do...
        this.onCrafting(par2ItemStack);

        for (int i = 0; i < lengthMatrix; ++i)
        {
            ItemStack itemstack1 = this.asembler.getStackInSlot(startMatrix+i);

            if (itemstack1 != null)
            {
                this.asembler.decrStackSize(startMatrix+i, 1);

                if (itemstack1.getItem().hasContainerItem())
                {
                    ItemStack itemstack2 = itemstack1.getItem().getContainerItemStack(itemstack1);

                    if (itemstack2.isItemStackDamageable() && itemstack2.getItemDamage() > itemstack2.getMaxDamage())
                    {
                        MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(thePlayer, itemstack2));
                        itemstack2 = null;
                    }

                    if (itemstack2 != null && (!itemstack1.getItem().doesContainerItemLeaveCraftingGrid(itemstack1) || !this.thePlayer.inventory.addItemStackToInventory(itemstack2)))
                    {
                        if (this.asembler.getStackInSlot(startMatrix+i) == null)
                        {
                            this.asembler.setInventorySlotContents(startMatrix+i, itemstack2);
                        }
                        else
                        {
                            this.thePlayer.dropPlayerItem(itemstack2);
                        }
                    }
                }
            }
        }
    }
}
