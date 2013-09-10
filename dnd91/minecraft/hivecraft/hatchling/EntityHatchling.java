package mods.dnd91.minecraft.hivecraft.hatchling;

import java.util.Random;

import mods.dnd91.minecraft.hivecraft.PacketHandler;
import mods.dnd91.minecraft.hivecraft.genetics.Genetics;
import mods.dnd91.minecraft.hivecraft.hatchling.ai.EntityAIBadGenExplode;
import mods.dnd91.minecraft.hivecraft.hatchling.ai.EntityAIBadGenPanic;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityHatchling extends EntityMob implements IInventory{
	protected static Random random = new Random();
	
	public Genetics genetics = null;
	protected int color = 0;
	
	public EntityItem target = null;
	public ItemStack[] inventory = null;
	public int inventorySize = 0;
	protected ContainerHatchling container = new ContainerHatchling(this);
	
	public EntityHatchling(World world)  //Client Side and init for server.
	 {
		super(world);
		this.texture = "/mods/dnd91/minecraft/hivecraft/textures/entitys/QueenMagicWorm.png";//idToTexturePath.get(0);
	 }
	
	public EntityHatchling(World world, NBTTagCompound compound) //Server Side
	 {
		super(world);
        color = compound.getInteger("colorID");
        this.texture = getTexture();
		this.genetics = new Genetics(compound.getCompoundTag("genetics"));		
		this.loadAI(genetics.getAI());
	 }
	
	protected void loadAI(NBTTagCompound ai){
		int quality = genetics.quality();
		int counter = 0;
		if(quality <= 10){
			tasks.addTask(counter++, new EntityAIBadGenExplode(this, this.worldObj, 3));
			tasks.addTask(counter++, new EntityAIBadGenPanic(this, 0.8f));	
		}else if(quality <= 25){
			tasks.addTask(counter++, new EntityAIBadGenPanic(this, 0.8f));	
		}else if(quality <= 50){
			tasks.addTask(counter++, new EntityAIWander(this, 0.4f));
			tasks.addTask(counter++, new EntityAIFleeSun(this, 0.6f));
		}else if(quality <= 75){
			tasks.addTask(counter++, new EntityAIWander(this, 0.4f));
			tasks.addTask(counter++, new EntityAIFleeSun(this, 0.6f));
			tasks.addTask(counter++, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0f));
		}else if(quality <= 100){
			tasks.addTask(counter++, new EntityAIWander(this, 0.4f));
			tasks.addTask(counter++, new EntityAIFleeSun(this, 0.6f));
			tasks.addTask(counter++, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0f));
		}
	}

	@Override
	public int getMaxHealth() {
		return 0;
	}
	
	@Override
	 public void writeEntityToNBT(NBTTagCompound compound){
		 super.writeEntityToNBT(compound);
		 
		 compound.setInteger("Color", color);
		 compound.setInteger("inventorySize", inventorySize);
		 
		 if(this.genetics != null)
			 compound.setCompoundTag("Genetics", this.genetics.getTagCompound());
		 
		 if(this.inventory != null){
			 NBTTagList nbt_list = new NBTTagList();
			 for(int l = 0; l < inventory.length; l++){
				 if(inventory[l] == null)
					 continue;

				 NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	             nbttagcompound1.setByte("Slot", (byte)l);
	             this.inventory[l].writeToNBT(nbttagcompound1);
	             nbt_list.appendTag(nbttagcompound1);
			 } 
			 compound.setTag("Inventory", nbt_list);
		 }
		 
		 
	 }
	 
	 
	 
	 @Override
	 public void readEntityFromNBT(NBTTagCompound compound){
		 super.readEntityFromNBT(compound);
		 this.genetics = new Genetics(compound.getCompoundTag("Genetics"));
		 if(this.genetics != null)
			 this.loadAI(genetics.getAI());
		 
		 color = compound.getInteger("Color");
		 inventorySize = compound.getInteger("inventorySize");
		 
		 if(compound.hasKey("Inventory")){
			 NBTTagList nbt_list = compound.getTagList("Inventory");
			 
			 for (int i = 0; i < nbt_list.tagCount(); ++i)
		        {
		            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbt_list.tagAt(i);
		            int j = nbttagcompound1.getByte("Slot") & 255;

		            if (j >= 0 && j < this.inventory.length)
		            {
		                this.inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		            }
		        } 
			 PacketHandler.sendHiveTory(this);
		 }
	 }
	 
	 @Override
	 protected boolean isAIEnabled()
	 {
		 return true;
	 }
	 
	 @Override
	 protected void entityInit()
	 {
		 super.entityInit();
	     this.dataWatcher.addObject(13, new Integer(color));
	 }
	 
	 @Override
	 protected void updateAITick()
	 {
	      this.dataWatcher.updateObject(13, Integer.valueOf(color));
	 }
	 
	 @Override
	 public EnumCreatureAttribute getCreatureAttribute()
	 {
		 return EnumCreatureAttribute.ARTHROPOD;
	 }
	 
	 public void itemInteract(EntityItem item){}
	 
	 public String getTexture(){ return ""; }
	 
	 
	 private boolean isSetInventoty = false;
	 public void updateTexture() {
		this.color = this.getDataWatcher().getWatchableObjectInt(13);
		this.texture = getTexture();
		if(!isSetInventoty && worldObj.isRemote){
			PacketHandler.callForUpdateHiveTory(this);
			isSetInventoty = true;
		}
			
	}

	public String getColorTexture() { return ""; }

	@Override
	public int getSizeInventory() {
		if(inventory == null)
			return 0;
		else
			return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		if(inventory == null)
			return null;
		else
			return inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if(inventory == null)
			return null;
		
		if (this.inventory[i] != null)
        {
            ItemStack itemstack;

            if (this.inventory[i].stackSize <= j)
            {
                itemstack = this.inventory[i];
                this.inventory[i] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.inventory[i].splitStack(j);

                if (this.inventory[i].stackSize == 0)
                {
                    this.inventory[i] = null;
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
		if(inventory == null)
			return null;
		
		if (this.inventory[i] != null)
        {
            ItemStack itemstack = this.inventory[i];
            this.inventory[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.inventory[i] = itemstack;

	    if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
	    {
	    	itemstack.stackSize = this.getInventoryStackLimit();
	    }

	    this.onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "container.hatchling";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		if(inventory == null)
			return 0;
		return 64;
	}

	@Override
	public void onInventoryChanged() { }

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return false;
	}

	@Override
	public void openChest() { }

	@Override
	public void closeChest() { }

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return false;
	}
	
	@Override
	public void onDeath(DamageSource par1DamageSource)
    {
		super.onDeath(par1DamageSource);
		if(this.inventory == null || worldObj.isRemote)
			return;
		
		for(int l = 0; l < this.inventory.length; l++){
			if(inventory[l] != null)
				this.entityDropItem(inventory[l], random.nextFloat());
		}
		
		
	}

}
