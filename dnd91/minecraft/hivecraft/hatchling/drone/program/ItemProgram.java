package mods.dnd91.minecraft.hivecraft.hatchling.drone.program;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.hatchling.drone.EntityDrone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemProgram extends Item{
	public static int MAX_SIZE = 4;
	public int program_size = 0; // Always +1
	
	public ItemProgram(int par1) {
		super(par1);
		setCreativeTab(HiveCraft.tabHiveCraft);
		this.setMaxStackSize(1);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		ItemStack stack = getOdoremGlandem(player);
		if(stack == null){
			player.sendChatToPlayer("Missing Odorem Glandem");
			return itemStack;
		}
		
		NBTTagCompound program = getProgram(itemStack);
		if(program == null)
			return itemStack;
		
		NBTTagCompound og = stack.getTagCompound();
		if(og == null){
			stack.setTagCompound(new NBTTagCompound());
			og = stack.getTagCompound();
			og.setInteger("currentSlot", 0);
		}
		int current = og.getInteger("currentSlot");
		
		if(4 <= (current+program_size)){
			player.addChatMessage("Not enought space");
			return itemStack;
		}else if(og.hasKey("Slot"+current+"ID")){
			int type = og.getCompoundTag("Slot"+current+"ID").getInteger("type");
			if(type == 3 || type == 2 || type == 0 || type == 1){
				player.addChatMessage("Space booked");
				return itemStack;
			}
		}
		
		for(int i = current+1; i<current+program_size+1; i++){
			 NBTTagCompound comp = new NBTTagCompound();
			 comp.setInteger("type", 3);
			 og.setCompoundTag("Slot"+i+"ID", comp);
		 }
		
		program.setInteger("program_size", this.program_size);
		og.setCompoundTag("Slot"+current+"ID", program);

		return itemStack;
    }
	
	protected NBTTagCompound getProgram(ItemStack itemStack){
		return null;
	}
	
	protected ItemStack getOdoremGlandem(EntityPlayer player){
		for(int i = 0; i < 10; i++){
			if(player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].itemID == HiveCraft.itemOdoremGlandem.itemID){
				return player.inventory.mainInventory[i];
			}
		}
		return null;
	}
	
	public void setUpAI(EntityDrone drone, NBTTagCompound program, int pos){
		
	}

}
