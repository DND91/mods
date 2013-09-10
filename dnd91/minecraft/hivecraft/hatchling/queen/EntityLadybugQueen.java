package mods.dnd91.minecraft.hivecraft.hatchling.queen;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.book.KnowledgeAppedix;
import mods.dnd91.minecraft.hivecraft.hatchling.EntityHatchling;
import mods.dnd91.minecraft.hivecraft.structure.block.TileEntityCocoon;
import mods.dnd91.minecraft.hivecraft.structure.buildplan.BaseArrays;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityLadybugQueen extends EntityHatchling implements IInventory{
	
	public EntityLadybugQueen(World par1World)  //Client Side and init for server.
	 {
		super(par1World);
		this.texture = "/mods/dnd91/minecraft/hivecraft/textures/entitys/QueenMagicWorm.png";//idToTexturePath.get(0);
		this.moveSpeed = 0.5f;
		this.tasks.addTask(0, new EntityAITempt(this, 0.50F, Item.goldNugget.itemID, false));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		container.setSize(4);
	 }
	
	public EntityLadybugQueen(World par1World, NBTTagCompound compound) //Server Side
	 {
		super(par1World, compound);
		this.moveSpeed = 0.0f;
		
       this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
       this.tasks.addTask(1, new EntityAITempt(this, 0.50F, Item.goldNugget.itemID, false));

       color = compound.getInteger("colorID");
       
       container.setSize(4);
	 }
	
	
	@Override
	public String getTexture()
   {
			return texture; 
   }
	
	public String getColorTexture()
   {
		return "/mods/dnd91/minecraft/hivecraft/textures/entitys/QueenMagicWorm2.png";
   }
	
	
	public int getAttackStrength(Entity par1Entity)
   {
    return 0;
   }
	 
	 public int getMaxHealth() 
	 {
	  return 20;
	 }
	 
	 public int getTotalArmorValue()
	 {
		 return 0;
	 }
	 
	 public void onLivingUpdate()
	 {
		 
		 super.onLivingUpdate();
	 }
	 
	 public boolean isThereSpace(){
		 for(int x = -1; x < 2; x++)
		 for(int y = 0; y < 3; y++)
		 for(int z = -1; z < 2; z++){
			 if(!this.worldObj.isAirBlock(x+(int)posX, y+(int)posY, z+(int)posZ))
				 return false;
		 }
		 
		 return true;
	 }
	 
	 public boolean canEvolve(){
		 return true;
	 }
	 
	 public void evolve(){
		 worldObj.setBlock((int)posX, (int)posY, (int)posZ, HiveCraft.blockCreep.blockID);
		 
		 int queenNest = this.genetics.getBuilding("QueenNest");
		 
		 worldObj.setBlockTileEntity((int)posX, (int)posY, (int)posZ, new TileEntityCocoon(this.genetics.getTagCompound(), BaseArrays.getQueenNest(queenNest)));
	 }
	 
	 @Override
	 public boolean interact(EntityPlayer player){ //TODO: WTF! FIX THIS DUDE! 
		 if(player.worldObj.isRemote)
		 	return false;
		 
		 if(player.isSneaking()){	 
			if(isThereSpace() && canEvolve()){
				this.isDead = true;
				this.health = 0;
				this.evolve();
				KnowledgeAppedix.unlockKnowledge(player, KnowledgeAppedix.first_nest);
				return true;
			}else
				return false;
		 }

		 player.openGui(HiveCraft.instance, 5, player.worldObj, (int)posX, (int)posY, (int)posZ);
		 return true;
	 }
	 
	 protected String getLivingSound()
	 {
		 return "mob.zombie.say";
	 }
	 
	 protected String getHurtSound()
	 {
		return "mob.zombie.hurt";
	 }
	    
	 protected String getDeathSound()
	 {
		 return "mob.zombie.death";
	 }
	    
	 protected void playStepSound(int par1, int par2, int par3, int par4)
	 {
		 this.worldObj.playSoundAtEntity(this, "mob.zombie.step", 0.15F, 1.0F);
	 }
	 
	 protected int getDropItemId()
	 {
		 return HiveCraft.itemBiomass.itemID;
	 }
	 
	 @Override
	 public void writeEntityToNBT(NBTTagCompound compound){
		 super.writeEntityToNBT(compound);
		 
	 }
	 
	 @Override
	 public void readEntityFromNBT(NBTTagCompound compound){
		 super.readEntityFromNBT(compound);
		 
	    }
	 

	@Override
	public String getInvName() {
		return "container.queen";
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return entityplayer.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
	}
}
