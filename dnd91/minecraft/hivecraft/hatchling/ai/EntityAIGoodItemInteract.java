package mods.dnd91.minecraft.hivecraft.hatchling.ai;

import mods.dnd91.minecraft.hivecraft.hatchling.EntityHatchling;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIGoodItemInteract extends EntityAIBase{
	EntityHatchling hatchling;
	
	public EntityAIGoodItemInteract(EntityHatchling h){
		hatchling = h;
		this.setMutexBits(3);
	}
	
	@Override
	public boolean shouldExecute() {
		return hatchling.target != null && this.hatchling.getDistanceSq(hatchling.target.posX, this.hatchling.target.boundingBox.minY, this.hatchling.target.posZ) <= 4.0f; 
	}
	
	@Override
	public void startExecuting() {
		hatchling.itemInteract(hatchling.target);
		hatchling.target = null;
	}

}
