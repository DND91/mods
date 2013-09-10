package mods.dnd91.minecraft.hivecraft.hatchling.builder;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cpw.mods.fml.common.network.PacketDispatcher;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.client.models.ModelBuilderMagic;
import mods.dnd91.minecraft.hivecraft.client.models.ModelQueenFire;
import mods.dnd91.minecraft.hivecraft.client.models.ModelQueenMagic;
import mods.dnd91.minecraft.hivecraft.genetics.FamilyAppedix;
import mods.dnd91.minecraft.hivecraft.genetics.Genetics;
import mods.dnd91.minecraft.hivecraft.hatchling.EntityHatchling;
import mods.dnd91.minecraft.hivecraft.structure.block.TileEntityCocoon;
import mods.dnd91.minecraft.hivecraft.structure.buildplan.BaseArrays;
import mods.dnd91.minecraft.hivecraft.structure.buildplan.BasePlan;
import mods.dnd91.minecraft.hivecraft.structure.buildplan.BlockPlan;
import mods.dnd91.minecraft.hivecraft.structure.queenNest.TileEntityQueenNest;
import net.minecraft.block.BlockCloth;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.world.World;

public class EntityBuilder extends EntityHatchling implements IInventory{
	
	private static Map<Integer, String> idToTexturePath = new HashMap<Integer,String>();
	static{
		idToTexturePath.put(0, "/mods/dnd91/minecraft/hivecraft/textures/entitys/BuilderMagicWorm.png");
		idToTexturePath.put(1, "/mods/dnd91/minecraft/hivecraft/textures/entitys/BuilderSprinkleFire.png");
	}
	
	private static Map<Integer, String> idToColorPath = new HashMap<Integer,String>();
	static{
		idToColorPath.put(0, "/mods/dnd91/minecraft/hivecraft/textures/entitys/BuilderMagicWorm2.png");
		idToColorPath.put(1, "/mods/dnd91/minecraft/hivecraft/textures/entitys/BuilderSprinkleFire2.png");
	}
	
	
	private static Map<Integer, ModelBase> idToModel = new HashMap<Integer, ModelBase>();
	static{
		idToModel.put(0, new ModelBuilderMagic());
		idToModel.put(1, new ModelSlime(16));
	}
	
	private static Random random = new Random();
	public int mutationID = 0;

	private ItemStack[] queenItemStacks = new ItemStack[4];
	
	private int model = 0;
	private int color = 0;
	
	public EntityBuilder(World par1World)  //Client Side and init for server.
	 {
		super(par1World);
		this.texture = "/mods/dnd91/minecraft/hivecraft/textures/entitys/QueenMagicWorm.png";//idToTexturePath.get(0);
		this.moveSpeed = 0.5f;
		this.tasks.addTask(0, new EntityAITempt(this, 0.50F, Item.goldNugget.itemID, false));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		
	 }
	
	public EntityBuilder(World par1World, NBTTagCompound compound) //Server Side
	 {
		super(par1World, compound);
		this.moveSpeed = 0.0f;
		
        this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(1, new EntityAITempt(this, 0.50F, Item.goldNugget.itemID, false));
       
        setModelID(compound.getInteger("queenModel"));
        color = compound.getInteger("colorID");
        
        this.texture = idToTexturePath.get(getModelID());
        
	 }
	
	
	
	public int getModelID() {
		return model;
	}

	public void setModelID(int model) {
		this.model = model;
	}

	public String getTexture()
    {
			return idToTexturePath.get(getModelID()); 
    }
	
	public String getColorTexture()
    {
		return idToColorPath.get(getModelID());
    }
	
	public ModelBase getModel(){
		return this.idToModel.get(getModelID());
	}
	
	public int getAttackStrength(Entity par1Entity)
    {
     return 0;
    }
	 
	 public int getMaxHealth() 
	 {
	  return 20;
	 }
	 
	 public EnumCreatureAttribute getCreatureAttribute()
	 {
		 return EnumCreatureAttribute.ARTHROPOD;
	 }
	 
	 protected boolean isAIEnabled()
	 {
		 return true;
	 }
	 
	 public int getTotalArmorValue()
	 {
		 return 0;
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
		 boolean flag1 = this.queenItemStacks[0] != null && this.queenItemStacks[0].getItem() == HiveCraft.itemBiomass && this.queenItemStacks[0].stackSize == 9;
		 boolean flag2 = this.queenItemStacks[1] != null && this.queenItemStacks[1].getItem() == Item.ingotGold && this.queenItemStacks[1].stackSize == 4;
		 boolean flag3 = this.queenItemStacks[2] != null && this.queenItemStacks[2].getItem() == Item.egg && this.queenItemStacks[2].stackSize == 6;
		 boolean flag4 = this.queenItemStacks[3] != null && this.queenItemStacks[3].getItem() == Item.fermentedSpiderEye && this.queenItemStacks[3].stackSize == 10;
		 
		 System.out.println("1: " + flag1 + " 2: " + flag2 + " 3: " + flag3 + " 4: " + flag4);
		 return flag1 && flag2 && flag3 && flag4 || true; //TODO: FIX!
	 }
	 
	 public void evolve(){
		 worldObj.setBlock((int)posX, (int)posY, (int)posZ, HiveCraft.blockCreep.blockID);
		 
		 String buildName = this.genetics.getTagCompound().getString("buildName");
		 int buildID = this.genetics.getBuilding(buildName);
		 if(buildID == -1)
			 return;
		 
		 BlockPlan buildPlan = FamilyAppedix.getBuilding(buildID);
		 
		 if(buildPlan == null)
			 return;
		 
		 //worldObj.setBlockTileEntity((int)posX, (int)posY, (int)posZ, new TileEntityCocoon(this.genetics.getTagCompound(), buildPlan));
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
				return true;
			}else
				return false;
		 }

		 //player.openGui(HiveCraft.instance, 5, player.worldObj, this.entityId, (int)posY, (int)posZ);
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
		 
		 compound.setInteger("ModelID", getModelID());
		 compound.setInteger("Color", color);
		 
		 
		 NBTTagList slotlist = new NBTTagList();
		 for(int i = 0; i < this.queenItemStacks.length; ++i){
			 if(this.queenItemStacks[i] != null){
				 NBTTagCompound slotCompound = new NBTTagCompound();
				 slotCompound.setByte("Slot",(byte)i);
				 this.queenItemStacks[i].writeToNBT(slotCompound);
				 slotlist.appendTag(slotCompound);
			 }
		 }
		 
		 compound.setTag("Items", slotlist);
		 
	 }
	 
	 
	 
	 @Override
	 public void readEntityFromNBT(NBTTagCompound compound){
		 super.readEntityFromNBT(compound);
		 
		 setModelID(compound.getInteger("ModelID"));
		 color = compound.getInteger("Color");
		 
		 
		 NBTTagList slotlist = compound.getTagList("Items");
		 
		 for(int i = 0; i < slotlist.tagCount(); ++i){
			 NBTTagCompound item = (NBTTagCompound)slotlist.tagAt(i);
			 int j = item.getByte("Slot") & 255;
			 if(j >= 0 && j < this.queenItemStacks.length)
				 this.queenItemStacks[j] = ItemStack.loadItemStackFromNBT(item);
		 }
		 
	    }
	 
	@Override
	public int getSizeInventory() {
		return this.queenItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.queenItemStacks[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.queenItemStacks[i] != null)
        {
            ItemStack itemstack;

            if (this.queenItemStacks[i].stackSize <= j)
            {
                itemstack = this.queenItemStacks[i];
                this.queenItemStacks[i] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.queenItemStacks[i].splitStack(j);

                if (this.queenItemStacks[i].stackSize == 0)
                {
                    this.queenItemStacks[i] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.queenItemStacks[i] != null)
        {
            ItemStack itemstack = this.queenItemStacks[i];
            this.queenItemStacks[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		 this.queenItemStacks[i] = itemstack;

	    if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
	    {
	    	itemstack.stackSize = this.getInventoryStackLimit();
	    }

	    this.onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "container.queen";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void onInventoryChanged() {
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return entityplayer.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {	}

	@Override
	public void closeChest() {	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
}
