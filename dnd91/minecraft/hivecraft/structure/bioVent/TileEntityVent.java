package mods.dnd91.minecraft.hivecraft.structure.bioVent;

import java.util.Random;

import cpw.mods.fml.common.network.PacketDispatcher;

import mods.dnd91.minecraft.hivecraft.genetics.Genetics;
import mods.dnd91.minecraft.hivecraft.structure.interfaces.IHiveCraftBuild;
import mods.dnd91.minecraft.hivecraft.structure.interfaces.IHiveCraftHeatHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityVent extends TileEntity implements IHiveCraftBuild, IHiveCraftHeatHandler, ISidedInventory{
	
	private static Random random = new Random();
	Genetics genetics = null;
	public int stage = 0;
	public int actionType = 0;
	public int heat = 0;
	public int currentTick = 0;
		
	public void updateEntity()
    {     
		currentTick++;
        if(this.worldObj.isRemote)
        	return;

        if(stage < 53 && currentTick % 20 == 2){
        	stage++;
        	PacketDispatcher.sendPacketToAllPlayers(this.getDescriptionPacket());
        }else if(53 <= stage && currentTick % 20 == 2 && heat >= (getMaxHeat()*0.1F)){
        	if(genetics != null)
        		heat -= genetics.resilience();
        	else
        		heat -= 10;
        	if(stage < 60 && heat >= (getMaxHeat()*0.9F)){
        		stage++;
        		heat = 0;
        		PacketDispatcher.sendPacketToAllPlayers(this.getDescriptionPacket());
        	}
        }
    }
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        this.stage = par1NBTTagCompound.getInteger("stage");
        this.actionType = par1NBTTagCompound.getInteger("actionType");
        this.heat = par1NBTTagCompound.getInteger("heat");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("stage", this.stage);
        par1NBTTagCompound.setInteger("actionType", this.actionType);
        par1NBTTagCompound.setInteger("heat", this.heat);
    }
	
	
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
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
	public void setGenetics(NBTTagCompound gen) {
		this.genetics = new Genetics(gen);
	}
	
	@Override
	public Packet getDescriptionPacket()
    {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("stage", stage);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, compound);
    }
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
		NBTTagCompound compound = pkt.customParam1;
		if(compound.hasKey("stage")){
			stage = compound.getInteger("stage");
		}
		
    }

	@Override
	public int addHeat(int h) {
		if(this.stage < 53)
			return h;
		int maxHeat = getMaxHeat();
		if(maxHeat < (h + heat)){
			h = maxHeat - (h+heat)*-1;
			heat = maxHeat;
		}else{
			heat += h;
			h = 0;
		}
		return h;
	}

	@Override
	public int takeHeat(int h) {
		if((heat - h) < 0){
			h = heat;
			heat = 0;
		}else
		{
			heat -= h;
		}
		return h;
	}

	@Override
	public int getMaxHeat() {
		if(genetics == null)
			return 2000;
		return this.genetics.health()*this.genetics.resilience()*this.genetics.heatValue();
	}

	@Override
	public float getEff() {
		// TODO Auto-generated method stub
		return 0;
	}

}
