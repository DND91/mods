package mods.dnd91.minecraft.hivecraft.hatchling.ai;

import java.util.Random;

import mods.dnd91.minecraft.hivecraft.PacketHandler;
import mods.dnd91.minecraft.hivecraft.hatchling.drone.EntityDrone;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class EntityAIGoodDumpTo extends EntityAIBase{

	EntityDrone drone;
	float speed;
	float range = 32.0f;
	private static Random random = new Random();
	
	int blockID;
	int meta;
	int x,y,z;
	int side;
	
	boolean cont = false;
	int count = 0;
	int loop = 0;
	static final int MAX_COUNT = 5;
	static final int MAX_LOOP = 2;
	
	public EntityAIGoodDumpTo(EntityDrone d, float m, NBTTagCompound position){
		drone = d;
		speed = m;
		this.setMutexBits(1);
		blockID = position.getInteger("blockID");
		meta = position.getInteger("meta");
		x = position.getInteger("posX");
		y = position.getInteger("posY");
		z = position.getInteger("posZ");
		side = position.getInteger("side");
	}

	@Override
	public boolean shouldExecute() {
		if(this.drone.inventory == null)
			return false;
		
		if(this.drone.getNavigator().noPath() && hasSomething()){
			cont = true;
			return true;
		}
		return false;
	}
	
	private boolean hasSomething(){
		for(int i = 0; i < drone.inventory.length; i++){
			if(this.drone.inventory[i] != null)
				return true;
		}
		return false;
	}
	
	private boolean hasSpace(){
		for(int i = 0; i < drone.inventory.length; i++){
			if(this.drone.inventory[i] == null || this.drone.inventory[i].stackSize < this.drone.inventory[i].getItem().getItemStackLimit())
				return true;
		}
		return false;
	}
	
	@Override
	public void startExecuting()
    {
        this.drone.getNavigator().tryMoveToXYZ(x, y, z, this.speed); 
    }
	
	@Override
	public boolean continueExecuting()
    {
        return cont;
    }
	
	
	@Override
	public void updateTask() {
		if(drone.getDistanceSq(x, y, z) <= 8.0F){
			this.drone.getNavigator().clearPathEntity();
			count++;
			if(MAX_COUNT <= count){
				loop++;
				count = 0;
				if(drone.worldObj.getBlockId(x, y, z) != blockID){
					cont = false;
					return;
				}
				if(drone.worldObj.getBlockMetadata(x, y, z) != meta){
					cont = false;
					return;
				}
				TileEntity tileEntity = drone.worldObj.getBlockTileEntity(x, y, z);
				if(tileEntity == null){
					cont = false;
					return;
				}
				if(tileEntity instanceof ISidedInventory){
					ISidedInventory inv = (ISidedInventory)tileEntity;
					handleISidedInventory(inv);
				}else if(tileEntity instanceof IInventory){
					IInventory inv = (IInventory)tileEntity;
					handleIInventory(inv);
				}else{
					cont = false;
					return;
				}
				
			if(loop == MAX_LOOP || !hasSomething()){
				cont = false;
			}
			}
		}else if(this.drone.getNavigator().noPath()){
			this.drone.getNavigator().tryMoveToXYZ(x, y, z, this.speed);
		}
	}
	
	@Override
	public void resetTask() {
		cont = false;
		count = 0;
		loop = 0;
	}
	
	public void handleISidedInventory(ISidedInventory inv){
		int[] slots = inv.getAccessibleSlotsFromSide(side);
		if(slots == null){
			cont = false;
			return;
		}
		for(int j = 0; j < drone.inventory.length; j++)
		for(int i = 0; i < slots.length; i++){
			if(drone.inventory[j] == null || !inv.isStackValidForSlot(slots[i], drone.inventory[j]))
				continue;
			
			ItemStack item = inv.getStackInSlot(slots[i]);
			if(item == null){
				inv.setInventorySlotContents(slots[i], drone.inventory[j]);
				drone.inventory[j] = null;
				PacketHandler.sendHiveTory(drone);
			}else{
				if(!(drone.inventory[j].itemID == item.itemID && drone.inventory[j].getItemDamage() == item.getItemDamage()))
					continue;
				
				ItemStack stack = drone.inventory[j];
				int t = stack.stackSize + item.stackSize - item.getItem().getItemStackLimit();
				if(t <= 0){
					item.stackSize = item.getItem().getItemStackLimit() + t;
					stack.stackSize = 0;
					drone.inventory[j] = null;
					PacketHandler.sendHiveTory(drone);
				}else{
					item.stackSize = item.getItem().getItemStackLimit();
					stack.stackSize = t;
				}
			}
			
		}
	}
	
	public void handleIInventory(IInventory inv){
		int slots = inv.getSizeInventory();
		if(slots == 0){
			cont = false;
			return;
		}
		for(int j = 0; j < drone.inventory.length; j++)
		for(int i = 0; i < slots; i++){
			if(drone.inventory[j] == null || !inv.isStackValidForSlot(i, drone.inventory[j]))
				continue;
			
			ItemStack item = inv.getStackInSlot(i);
			if(item == null){
				inv.setInventorySlotContents(i, drone.inventory[j]);
				drone.inventory[j] = null;
				PacketHandler.sendHiveTory(drone);
			}else{
				if(!(drone.inventory[j].itemID == item.itemID && drone.inventory[j].getItemDamage() == item.getItemDamage()))
					continue;
				
				ItemStack stack = drone.inventory[j];
				int t = stack.stackSize + item.stackSize - item.getItem().getItemStackLimit();
				if(t <= 0){
					item.stackSize = item.getItem().getItemStackLimit() + t;
					stack.stackSize = 0;
					drone.inventory[j] = null;
					PacketHandler.sendHiveTory(drone);
				}else{
					item.stackSize = item.getItem().getItemStackLimit();
					stack.stackSize = t;
				}
			}
			
		}
	}

}
