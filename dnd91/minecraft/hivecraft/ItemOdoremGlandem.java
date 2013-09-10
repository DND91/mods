package mods.dnd91.minecraft.hivecraft;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemOdoremGlandem extends Item{

	public ItemOdoremGlandem(int par1) {
		super(par1);
		setMaxStackSize(1);
		setCreativeTab(HiveCraft.tabHiveCraft);
		setUnlocalizedName("itemOdoremGlandem");
	}
	
	@Override
	public void registerIcons(IconRegister ires){
		this.itemIcon = ires.registerIcon("dnd91/minecraft/hivecraft:Collector");
	}
	
	/*
	 * NBTTagCompound ItemStack
	 * currentSlot - the current selected slot by the player
	 * Slot0ID, Slot1ID, Slot2ID, Slot3ID - NBTTagCompounds
	 * 
	 * NBTTagCompound SlotXID
	 * type - -1. empty, 0. block, 1. item, 2. order, 3. booked
	 * DATA
	 * TYPE block:
	 * id - id of block
	 * meta - meta of block
	 * side - side of block
	 * x,y,z - coordinates of block
	 * TYPE item:
	 * itemID - Item ID
	 * dmg - Item Damage Value
	 * TYPE order:
	 * itemID - Item ID of the item to be drawn
	 * dmg - Item Damage Value of the item be drawn
	 * orderID - Order ID, ID of Order Item, 
	 */
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
		if(!player.worldObj.isRemote){// && !player.isSneaking()){
			 int blockID = world.getBlockId(x, y, z);
			 int meta = world.getBlockMetadata(x, y, z);
			 NBTTagCompound compound = itemStack.getTagCompound();
			 if(compound == null){
				 itemStack.setTagCompound(new NBTTagCompound());
				 compound = itemStack.getTagCompound();
				 compound.setInteger("currentSlot", 0);
				 return true;
			 }
			 int currentSlot = compound.getInteger("currentSlot");
			 
			 NBTTagCompound cc = compound.getCompoundTag("Slot"+currentSlot+"ID");
			 int type = cc.getInteger("type");
			 
			 if(type == -1 || type == 2)
				 return false;
			 
			 NBTTagCompound comp = new NBTTagCompound();
			 comp.setInteger("type", 0);
			 comp.setInteger("blockID", blockID);
			 comp.setInteger("meta",  meta);
			 comp.setInteger("side", par7);
			 comp.setInteger("posX", x);
			 comp.setInteger("posY", y);
			 comp.setInteger("posZ", z);
			 
			 compound.setCompoundTag("Slot"+currentSlot+"ID", comp);
			 
			 return true;
		 } 
		
        return true;
    }
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		if(!player.worldObj.isRemote && player.isSneaking()){
			 NBTTagCompound compound = itemStack.getTagCompound();
			 if(compound == null){
				 itemStack.setTagCompound(new NBTTagCompound());
				 compound = itemStack.getTagCompound();
				 compound.setInteger("currentSlot", 0);
			 }
			 int currentSlot = compound.getInteger("currentSlot");
			 
			 int type = -1;
			 if(compound.hasKey("Slot"+currentSlot+"ID")){
				 NBTTagCompound cc = compound.getCompoundTag("Slot"+currentSlot+"ID");
				 type = cc.getInteger("type");
				 //type - -1. empty, 0. block, 1. item, 2. order, 3. booked
				 switch(type){
				 case 0: //Block
				 case 1: //Item
					 type = 3;
					 break;
				 case 2: //Order
					 int size = cc.getInteger("program_size");
					 
					 for(int i = currentSlot+1; i<currentSlot+size+1; i++){
						 NBTTagCompound comp = new NBTTagCompound();
						 comp.setInteger("type", -1);
						 compound.setCompoundTag("Slot"+i+"ID", comp);
					 }
					 type = -1;
				 }
				 
			 }
			 
			 NBTTagCompound comp = new NBTTagCompound();
			 comp.setInteger("type", type);

			 compound.setCompoundTag("Slot"+currentSlot+"ID", comp);
		 }
        return itemStack;
    }

}
