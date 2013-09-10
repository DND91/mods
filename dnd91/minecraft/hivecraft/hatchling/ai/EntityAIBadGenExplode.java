package mods.dnd91.minecraft.hivecraft.hatchling.ai;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;

public class EntityAIBadGenExplode extends EntityAIBase{
	EntityLiving entity;
	World world;
	float explotionSize;
	
	private static Random random = new Random();
	
	public EntityAIBadGenExplode(EntityLiving ent, World wor,float expS){
		entity = ent;
		world = wor;
		this.setMutexBits(2);
		explotionSize = expS;
	}
	
	/**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return random.nextInt(25) == 0;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.entity.getNavigator().clearPathEntity();
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        world.createExplosion(entity, entity.posX, entity.posY, entity.posZ, explotionSize, true);
        entity.setDead();
    }
}
