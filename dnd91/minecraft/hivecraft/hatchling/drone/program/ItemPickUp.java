package mods.dnd91.minecraft.hivecraft.hatchling.drone.program;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.hatchling.ai.EntityAIGoodPickUp;
import mods.dnd91.minecraft.hivecraft.hatchling.drone.EntityDrone;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemPickUp extends ItemProgram{

	public ItemPickUp(int par1) {
		super(par1);
		this.program_size = 1;
		setUnlocalizedName("itemPickUp");
	}
	
	@Override
	public void registerIcons(IconRegister ires){
		this.itemIcon = ires.registerIcon("dnd91/minecraft/hivecraft:PickUp");
	}
	
	@Override
	protected NBTTagCompound getProgram(ItemStack itemStack){
		NBTTagCompound comp = new NBTTagCompound();
		comp.setInteger("type", 2);
		comp.setInteger("itemID", itemID);
		comp.setInteger("dmg",  0);
		comp.setInteger("orderID", itemID);
		return comp;
	}
	
	@Override
	public void setUpAI(EntityDrone drone, NBTTagCompound program, int pos){
		int n = pos + 1;
		drone.tasks.addTask(1, new EntityAIGoodPickUp(drone, 0.5F, program.getCompoundTag("Slot"+n+"ID")));
		
	}

}
