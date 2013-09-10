package mods.dnd91.minecraft.hivecraft.hatchling.ai;

import java.util.Random;

import mods.dnd91.minecraft.hivecraft.hatchling.drone.EntityDrone;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class EntityAIGoodPickUp extends EntityAIBase{
	
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
	
	public EntityAIGoodPickUp(EntityDrone d, float m, NBTTagCompound position){
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
		
		if(this.drone.getNavigator().noPath() && hasSpace()){
			cont = true;
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
	
	private boolean somethingInFilter(){
		IInventory inv = drone.filter;
		for(int i = 0; i < inv.getSizeInventory(); i++){
			ItemStack stack = inv.getStackInSlot(i);
			if(stack != null)
				return true;
			
		}
		return false;
	}
	
	private boolean filter(ItemStack item){
		if(!somethingInFilter())
			return true;
		IInventory inv = drone.filter;
		
		
		
		for(int i = 0; i < inv.getSizeInventory(); i++){
			ItemStack stack = inv.getStackInSlot(i);
			if(stack == null)
				continue;
			if(item.getItem().isItemTool(item)){
				if(stack.itemID == item.itemID)
					return true;
			}else{
				if(stack.itemID == item.itemID && stack.getItemDamage() == item.getItemDamage())
					return true;
			}
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
				if(tileEntity == null || !(tileEntity instanceof IInventory)){
					cont = false;
					return;
				}
				IInventory inv = (IInventory)tileEntity;
				int slots = inv.getSizeInventory();
				if(slots == 0){
					cont = false;
					return;
				}
				for(int i = 0; i < slots; i++){
					ItemStack stack = inv.decrStackSize(i, 64);
					if(stack == null)
						continue;
					if(!filter(stack)){
						inv.setInventorySlotContents(i, stack);
						continue;
					}
					drone.itemInteract(stack);
					if(stack.stackSize > 0){
						inv.setInventorySlotContents(i, stack);
					}
				}
			if(loop == MAX_LOOP){
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
	
}
