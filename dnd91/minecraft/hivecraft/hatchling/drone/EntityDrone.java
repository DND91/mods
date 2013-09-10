package mods.dnd91.minecraft.hivecraft.hatchling.drone;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.PacketHandler;
import mods.dnd91.minecraft.hivecraft.book.KnowledgeAppedix;
import mods.dnd91.minecraft.hivecraft.client.models.ModelDroneStandard;
import mods.dnd91.minecraft.hivecraft.client.models.ModelQueenFire;
import mods.dnd91.minecraft.hivecraft.client.models.ModelQueenMagic;
import mods.dnd91.minecraft.hivecraft.hatchling.EntityHatchling;
import mods.dnd91.minecraft.hivecraft.hatchling.ai.EntityAIGoodFindItem;
import mods.dnd91.minecraft.hivecraft.hatchling.ai.EntityAIGoodItemInteract;
import mods.dnd91.minecraft.hivecraft.hatchling.drone.program.ItemProgram;

public class EntityDrone extends EntityHatchling{
	
	public InventoryFilter filter = new InventoryFilter();
	
	public EntityDrone(World world)  //Client Side and init for server.
	 {
		super(world);
		this.texture = "/mods/dnd91/minecraft/hivecraft/textures/entitys/ModelDroneStandard.png";//idToTexturePath.get(0);
		this.moveSpeed = 0.5f;
	 }
	
	public EntityDrone(World world, NBTTagCompound compound) //Server Side
	 {
		super(world,compound);
		
	 }
	
	@Override
	protected void loadAI(NBTTagCompound ai){
		//TODO Load AI!
		container.setSize(1);
		//this.tasks.addTask(0, new EntityAIGoodItemInteract(this));
		//this.tasks.addTask(1, new EntityAIGoodFindItem(this, 0.5f));
		
	}
	
	@Override
	 public void writeEntityToNBT(NBTTagCompound compound){
		 super.writeEntityToNBT(compound);
		 
		 if(this.filter.filterArray != null){
			 this.filter.filterSize = filter.filterArray.length;
			 compound.setInteger("filterSize", filter.filterSize);
			 NBTTagList nbt_list = new NBTTagList();
			 for(int l = 0; l < filter.filterSize; l++){
				 if(filter.filterArray[l] == null)
					 continue;

				 NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	             nbttagcompound1.setByte("Slot", (byte)l);
	             this.filter.filterArray[l].writeToNBT(nbttagcompound1);
	             nbt_list.appendTag(nbttagcompound1);
			 } 
			 compound.setTag("filter", nbt_list);
		 }
		 //TODO Save Inventory
	 }

	 @Override
	 public void readEntityFromNBT(NBTTagCompound compound){
		 super.readEntityFromNBT(compound);
		 //TODO Load Inventory
		 if(genetics.hasProgram())
			 this.setUpProgram(genetics.getProgram());
		 
		 if(compound.hasKey("filter")){
			 this.filter.filterSize = compound.getInteger("filterSize");
			 if(filter.filterSize != filter.filterArray.length)
				 filter.filterArray = new ItemStack[filter.filterSize];
			 
			 NBTTagList nbt_list = compound.getTagList("filter");
			 
			 for (int i = 0; i < nbt_list.tagCount(); ++i)
		        {
		            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbt_list.tagAt(i);
		            int j = nbttagcompound1.getByte("Slot") & 255;

		            if (j >= 0 && j < filter.filterSize)
		            {
		                this.filter.filterArray[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		            }
		        } 
		 }
		 
	 }
	 
	@Override
	public int getAttackStrength(Entity par1Entity)
    {
     return 10;
    }
	 
	@Override
	 public int getMaxHealth() 
	 {
	  return 6;
	 }
	 
	 
	@Override
	 public int getTotalArmorValue()
	 {
		 return 0;
	 }
	 
	 
	 
	 protected int getDropItemId()
	 {
		 return HiveCraft.itemBiomass.itemID;
	 }
	 
	 @Override
		public String getTexture()
		{
			return texture; 
		}

		@Override
		public String getColorTexture() {
			return "/mods/dnd91/minecraft/hivecraft/textures/entitys/ModelDroneStandard2.png";
		}
		
		 @Override
		 public boolean interact(EntityPlayer player){
			 if(inventory == null || player.worldObj.isRemote)
			 	return false;
			 
			 if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == HiveCraft.itemOdoremGlandem.itemID){
				 ItemStack stack = player.inventory.getCurrentItem();
				 NBTTagCompound program = stack.getTagCompound();
				 if(program == null)
					 return false;
				 genetics.setProgram(program);
				 setUpProgram(program);
			 }else if(player.isSneaking()){
				 System.out.println("Entity: "+this);
				 for(int i = 0; i < tasks.taskEntries.size(); i++){
					 System.out.println(i+". Task: "+tasks.taskEntries.get(i));
				 }
			 }else{
				 player.openGui(HiveCraft.instance, 106, player.worldObj, this.entityId, (int)posY, (int)posZ);
			 }
			 
			 return true;
		 }
		 
		 public void setUpProgram(NBTTagCompound program){
			 this.tasks.taskEntries.clear();
			 if(this.genetics != null)
				 this.loadAI(genetics.getAI());
			 
			 for(int i = 0; i < 4; i++){
				 if(!program.hasKey("Slot"+i+"ID"))
					 continue;
				 NBTTagCompound compound = program.getCompoundTag("Slot"+i+"ID");
				 int type = compound.getInteger("type");
				 if(type != 2)
					 continue;
				 int orderID = compound.getInteger("orderID");
				 ItemProgram item = (ItemProgram) Item.itemsList[orderID];
				 item.setUpAI(this, program, i);
			 }
		 }
	 
	 public void itemInteract(EntityItem item){
		 if(item != null && !item.isDead){
			 ItemStack stack = item.getEntityItem();
			 itemInteract(stack);
			 if(stack.stackSize == 0)
				 item.setDead();
		 }
	 }
	 
	 public void itemInteract(ItemStack item){
			 for(int l = 0; l < inventory.length; l++){
				 if(inventory[l] == null){
					 Slot s = ((Slot)this.container.inventorySlots.get(l));
					 s.putStack(item.copy());
					 item.stackSize = 0;
					 PacketHandler.sendHiveTory(this);
					 return;
				 }else if(inventory[l].stackSize < this.getInventoryStackLimit() && inventory[l].getItem() == item.getItem()){
					  if(inventory[l].getItemDamage() != item.getItemDamage())
						  continue;
					  int newSize = inventory[l].stackSize + item.stackSize;
					  if(newSize > this.getInventoryStackLimit()){
						  item.stackSize = newSize - this.getInventoryStackLimit(); 
						  newSize = this.getInventoryStackLimit();
					  }else
						  item.stackSize = 0;
					  
					  inventory[l].stackSize = newSize;
					  PacketHandler.sendHiveTory(this);
					  return;
				 }
			 } 
	 }
	 
}
