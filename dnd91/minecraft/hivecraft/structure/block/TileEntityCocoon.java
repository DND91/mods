package mods.dnd91.minecraft.hivecraft.structure.block;

import mods.dnd91.minecraft.hivecraft.structure.buildplan.BaseArrays;
import mods.dnd91.minecraft.hivecraft.structure.buildplan.BasePlan;
import mods.dnd91.minecraft.hivecraft.structure.buildplan.BlockPlan;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCocoon extends TileEntity{
	private boolean isMaster = false;
	int masterX = 0, masterY = 0,masterZ = 0;
	
	private int decayRate = 2000;
	private boolean isDecaying = false;
	
	private int foodLevel = 0;
	private int maxFoodLevel = 60000;
	
	private int creepLevel = 0;
	private int maxCreepLevel = 5; //TODO: BALANCE
	
	private NBTTagCompound genetics = null;
	private BasePlan plan = null;
	
	private int warmup = 0;
	
	public TileEntityCocoon(){}
	
	public TileEntityCocoon(NBTTagCompound tag, BlockPlan[][][] p){
		isMaster = true;
		genetics = tag;
		plan = new BasePlan(this, p);
	}
	
	public TileEntityCocoon(int x, int y, int z){
		isMaster = false;
		masterX = x;
		masterY = y;
		masterZ = z;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        
        decayRate = compound.getInteger("decayRate");
        isDecaying = compound.getBoolean("isDecaying");
        
        boolean tmp = compound.getBoolean("isMaster");
        if(tmp == false){
        	masterX = compound.getInteger("masterX");
        	masterY = compound.getInteger("masterY");
        	masterZ = compound.getInteger("masterZ");
        	
        }else{
        	isMaster = true;
        	foodLevel = compound.getInteger("foodLevel");
        	creepLevel = compound.getInteger("creepLevel");
        	genetics = compound.getCompoundTag("Genetics");
        	
        	//TODO: Hur ska man spara buildBlock och baseplan??
        	int id = compound.getInteger("buildBlockID");
        	
        	NBTTagList list = (NBTTagList) compound.getTag("planDiagram");
        	
        	BlockPlan[][][] p = BaseArrays.BaseArray();
        	for(int i = 0; i < list.tagCount(); i++){
        		NBTTagCompound pos = (NBTTagCompound) list.tagAt(i);
        		int x = pos.getInteger("x");
        		int y = pos.getInteger("y");
        		int z = pos.getInteger("z");
        		int bid = pos.getInteger("bid");
        		int met = pos.getInteger("met");
        		boolean mas = pos.getBoolean("mas");
        		p[y][x][z] = new BlockPlan(bid,met,mas);
        	}
        	
        	plan = new BasePlan(this, p);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        
        compound.setInteger("decayRate", decayRate);
        compound.setBoolean("isDecaying", isDecaying);
        
        compound.setBoolean("isMaster", isMaster);
        
        if(!isMaster){
        	compound.setInteger("masterX", masterX);
        	compound.setInteger("masterY", masterY);
        	compound.setInteger("masterZ", masterZ);
        	
        }else{
        	compound.setInteger("foodLevel", foodLevel);
        	compound.setInteger("creepLevel", creepLevel);
        	compound.setCompoundTag("Genetics", genetics);
        	
        	NBTTagList list = new NBTTagList();
        	
        	for(int y = 0; y < 5; y++)
        	for(int x = 0; x < 5; x++)
        	for(int z = 0; z < 5; z++){
        		NBTTagCompound pos = new NBTTagCompound();
        		if(plan.prioPlan[y][x][z] == null)
        			continue;
        		pos.setInteger("x",x);
        		pos.setInteger("y",y);
        		pos.setInteger("z",z);
        		pos.setInteger("bid",plan.prioPlan[y][x][z].blockID);
        		pos.setInteger("met",plan.prioPlan[y][x][z].meta);
        		pos.setBoolean("mas",plan.prioPlan[y][x][z].isMaster);
        		list.appendTag(pos);
        	}
        	
        	compound.setTag("planDiagram", list);
        }
    }

    @Override
    public void updateEntity() {
    	
    	if(this.foodLevel > 0 || true){ //TODO: FIX
			this.creepLevel++;
		 	//this.foodLevel--;
		}
    	boolean creepFlag = false;
    	if(this.creepLevel > this.maxCreepLevel){
			this.creepLevel = 0;
			creepFlag = true;
    	}
    	
    	
    	
    	if(worldObj.isRemote)
    		return;
    	
    	if(this.isDecaying){
    		this.decayRate--;
    		if(this.decayRate <= 0 && !worldObj.isRemote){
    			worldObj.destroyBlock(xCoord, yCoord, zCoord, false);
    			worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord); //TODO: ERROR?
    			worldObj.setBlock(xCoord, yCoord, zCoord, Block.cobblestoneMossy.blockID);
    		}
    		return;
    	}
    	
    	int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
    	if(meta == 0 && this.isMaster)
    		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 1 & 2);
    	
    	if(!isMaster && meta == 0){
    		TileEntity mast = (TileEntity)worldObj.getBlockTileEntity(masterX, masterY, masterZ);
    		if(mast == null || !(mast instanceof TileEntityCocoon)){
    			this.isDecaying = true;
    			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 2, 1 & 2);
    		}
    		return;
    	}
    	
    	if(this.isMaster && creepFlag){
    			boolean crepped = plan.isCreeped();
    			if(!crepped){
    				plan.putCreep();
    			}else{
    				plan.forceFill(true);
    				plan.setMaster(genetics);
    			}
    	}
    	
    }
	
	

}
