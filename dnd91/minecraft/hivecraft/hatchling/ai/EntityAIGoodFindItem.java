package mods.dnd91.minecraft.hivecraft.hatchling.ai;

import java.util.List;
import java.util.Random;

import mods.dnd91.minecraft.hivecraft.hatchling.drone.EntityDrone;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

public class EntityAIGoodFindItem extends EntityAIBase{
	
	EntityDrone drone;
	float speed;
	float range = 16.0f;
	private static Random random = new Random();
	
	public EntityAIGoodFindItem(EntityDrone d, float m){
		drone = d;
		speed = m;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		
		if(!this.drone.getNavigator().noPath())
    		return false;
		
		double x = drone.posX;
		double y = drone.posY;
		double z = drone.posZ;
		
		AxisAlignedBB box = AxisAlignedBB.getBoundingBox(x - range, y - range, z - range, x + range, y + range, z + range);
		
		List<EntityItem> itemList = drone.worldObj.getEntitiesWithinAABB(EntityItem.class, box);
		
		if(itemList == null || itemList.size() == 0)
			return false;
		
		
		EntityItem item = itemList.get(random.nextInt(itemList.size()));
		
		if(item == null)
			return false;
		
		drone.target = item;
		boolean flag = drone.inventory == null ? false : hasInvetorySpace(drone.inventory, item.getEntityItem());
		if(!flag)
			drone.target = null;
		
		return flag;
	}
	
	private boolean hasInvetorySpace(ItemStack[] itemStack, ItemStack target){
		for(int l = 0; l < itemStack.length; l++){
			if(itemStack[l] == null)
				return true;
			else if(itemStack[l].itemID == target.itemID && itemStack[l].getItemDamage() == target.getItemDamage() && itemStack[l].stackSize < drone.getInventoryStackLimit())
				return true;
		}
		return false;
	}
	
	public void startExecuting()
    {
        this.drone.getNavigator().tryMoveToXYZ(drone.target.posX, drone.target.posY, drone.target.posZ, this.speed);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.drone.getNavigator().noPath();
    }
	
	

}
