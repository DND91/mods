package mods.dnd91.minecraft.hivecraft.structure.bioGrinder;

import java.util.Random;

import cpw.mods.fml.common.network.PacketDispatcher;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.genetics.Genetics;
import mods.dnd91.minecraft.hivecraft.structure.interfaces.IHiveCraftBuild;
import mods.dnd91.minecraft.hivecraft.structure.interfaces.IHiveCraftHeatHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

//Grind Speed = ((Agressivness + Strength) * (quality / 100)
//Heat Creation = (Base + Agressivness) * (quality / 100)
//Heat Tolerance = (Base*Resilience+Base*Health) * (quality / 100)
//Heat Vent = (Base + Base * (Resilience/100)) * (quality / 100) per sek


public class TileEntityGrinder extends TileEntity implements IHiveCraftBuild, ISidedInventory{
	
	private static Random random = new Random();
	Genetics genetics = null;
	public int stage = 0;
	public int actionType = 0;
	public int currentTick = 0;
	
	private ItemStack[] inventory = new ItemStack[2];
	
	public void updateEntity()
    {     
		currentTick++;
        if(this.worldObj.isRemote)
        	return;
        if(stage == 100 && currentTick % 8 == 2){
        	if(worldObj.isAirBlock(xCoord, yCoord+1, zCoord)){
        		worldObj.setBlock(xCoord, yCoord+1, zCoord, HiveCraft.blockBioVent.blockID);
        	}else{
        		Object te = worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord);
        		if(te != null && te instanceof IHiveCraftHeatHandler){
        			IHiveCraftHeatHandler hh = (IHiveCraftHeatHandler)te;
        			hh.addHeat(12);
        		}
        	}
        }else if(stage < 100 && currentTick % 8 == 2){
        	stage++;
        	PacketDispatcher.sendPacketToAllPlayers(this.getDescriptionPacket());
        }
    }
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inventory.length)
            {
                this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.stage = par1NBTTagCompound.getInteger("stage");
        this.actionType = par1NBTTagCompound.getInteger("actionType");

    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("stage", this.stage);
        par1NBTTagCompound.setInteger("actionType", this.actionType);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);
    }
	
	@Override
	public void setGenetics(NBTTagCompound gen) {
		this.genetics = new Genetics(gen);
		
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return this.inventory.length;
	}



	@Override
	public ItemStack getStackInSlot(int i) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ItemStack decrStackSize(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public Packet getDescriptionPacket()
    {
		NBTTagCompound compound = new NBTTagCompound();
		NBTTagList list = new NBTTagList();
		if(this.inventory[0] != null){
			NBTTagCompound item = new NBTTagCompound();
			this.inventory[0].writeToNBT(item);
			item.setInteger("slot", 0);
			list.appendTag(item);
		}
		if(this.inventory[1] != null){
			NBTTagCompound item = new NBTTagCompound();
			this.inventory[1].writeToNBT(item);
			item.setInteger("slot", 1);
			list.appendTag(item);
		}
		compound.setTag("items", list);
		compound.setInteger("stage", stage);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, compound);
    }
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
		NBTTagCompound compound = pkt.customParam1;
		if(compound.hasKey("items")){
			NBTTagList list = compound.getTagList("items");
			for(int i = 0; i < list.tagCount(); i++){
				NBTTagCompound item = (NBTTagCompound) list.tagAt(i);
				ItemStack temp = ItemStack.loadItemStackFromNBT(item);
				int slot = item.getInteger("slot");
				this.setInventorySlotContents(slot, temp);
			}
			this.onInventoryChanged();
		}
		if(compound.hasKey("stage")){
			stage = compound.getInteger("stage");
		}
		
    }
}
