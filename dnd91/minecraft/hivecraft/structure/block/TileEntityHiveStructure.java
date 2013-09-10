package mods.dnd91.minecraft.hivecraft.structure.block;

import java.util.ArrayList;
import java.util.List;


import mods.dnd91.minecraft.hivecraft.structure.buildplan.BaseArrays;
import mods.dnd91.minecraft.hivecraft.structure.buildplan.BasePlan;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

/** BaseArrays for more information on meta sets**/

public class TileEntityHiveStructure  extends TileEntity{
	public class InfoBlockHolder{
		int x = 0;
		int y = 0;
		int z = 0;
		int meta = 0;
		
		public InfoBlockHolder() {}
		public InfoBlockHolder(int X, int Y, int Z, int M)  { x = X; y = Y; z = Z; meta = M; }
		public InfoBlockHolder(TileEntity ent) { x = ent.xCoord; y = ent.yCoord; z = ent.zCoord; meta = ent.blockMetadata; }
		
		public void readFromNBT(NBTTagCompound compound){
			compound.setInteger("posX", x);
			compound.setInteger("posY", y);
			compound.setInteger("posZ", z);
			compound.setInteger("meta", meta);
		}
		
		public void writeToNBT(NBTTagCompound compound){
			x = compound.getInteger("posX");
			y = compound.getInteger("posY");
			z = compound.getInteger("posZ");
			meta = compound.getInteger("meta");
		}
	}
	
	private boolean isMaster = false;
	private boolean foundMaster = false;
	private int masterX = 0;
	private int masterY = 0;
	private int masterZ = 0;
	
	public NBTTagCompound genetics = null;
	protected List<InfoBlockHolder> structureInfo = null;	
	
	protected TileEntityHiveStructure() {}
	
	@Override
	public void updateEntity(){
		if(!isMaster && !foundMaster)
		for(int x = -1; x < 2; x++)
		for(int y = -1; y < 2; y++)
		for(int z = -1; z < 2; z++){
			int trueX = xCoord + x;
			int trueY = yCoord + y;
			int trueZ = zCoord + z;
			TileEntity ent = worldObj.getBlockTileEntity(trueX, trueY, trueZ);
			if(ent == null || !(ent instanceof TileEntityHiveStructure))
				continue;
			TileEntityHiveStructure hive = (TileEntityHiveStructure)ent;
			if(hive.foundMaster()){
				masterX = hive.getMasterX();
				masterY = hive.getMasterY();
				masterZ = hive.getMasterZ();
				foundMaster = true;
			}else if(hive.isMaster()){
				masterX = trueX;
				masterY = trueY;
				masterZ = trueZ;
				foundMaster = true;
			}
		}
		
		if(foundMaster){
			TileEntity ent = worldObj.getBlockTileEntity(masterX, masterY, masterZ);
			if(ent == null || !(ent instanceof TileEntityHiveStructure))
				foundMaster = false;
		}
	}	
	
	
	public void youAreTheOne(NBTTagCompound c) { isMaster = true;  genetics = c; structureInfo = new ArrayList<InfoBlockHolder>(); }
	public boolean isMaster(){ return isMaster; }
	public boolean foundMaster() { return foundMaster; }
	public int getMasterX() { return masterX; }
	public int getMasterY() { return masterY; }
	public int getMasterZ() { return masterZ; }

	@Override
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        isMaster = compound.getBoolean("isMaster");
    	foundMaster = compound.getBoolean("foundMaster");
    	masterX = compound.getInteger("masterX");
    	masterY = compound.getInteger("masterY");
    	masterZ = compound.getInteger("masterZ");
    	if(compound.hasKey("Genetics"))
    		genetics = compound.getCompoundTag("Genetics");
    	if(compound.hasKey("StructInfo")){
    		structureInfo = new ArrayList<InfoBlockHolder>();
    		NBTTagList nbtlist = compound.getTagList("StructInfo");
    		
    		for(int i = 0; i < nbtlist.tagCount(); i ++){
    			NBTTagCompound c = (NBTTagCompound) nbtlist.tagAt(i);
    			InfoBlockHolder info = new InfoBlockHolder();
    			info.readFromNBT(c);
    			structureInfo.add(info);
    		}
    	}
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setBoolean("isMaster", isMaster);
    	compound.setBoolean("foundMaster", foundMaster);
    	compound.setInteger("masterX", masterX);
    	compound.setInteger("masterY", masterY);
    	compound.setInteger("masterZ", masterZ);
    	if(genetics != null)
    		compound.setCompoundTag("Genetics", genetics);
    	if(structureInfo != null){
    		NBTTagList nbtlist = new NBTTagList();
    		for(int i = 0; i < structureInfo.size(); i ++){
    			NBTTagCompound c = new NBTTagCompound();
    			structureInfo.get(i).writeToNBT(c);
    			nbtlist.appendTag(c);
    		}
    		compound.setTag("StructInfo", nbtlist);
    	}
    }
    
    public TileEntityHiveStructure getMaster(){
    	if(this.isMaster)
    		return this;
    	if(!this.foundMaster)
    		return null;
    	return (TileEntityHiveStructure)worldObj.getBlockTileEntity(getMasterX(), getMasterY(), getMasterZ());
    }
	
}
