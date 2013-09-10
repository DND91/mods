package mods.dnd91.minecraft.hivecraft.structure.buildplan;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.structure.block.TileEntityCocoon;
import mods.dnd91.minecraft.hivecraft.structure.block.TileEntityHiveStructure;
import net.minecraft.block.BlockContainer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class BasePlan {				  // Y  X  Z
	public BlockPlan[][][] prioPlan = BaseArrays.BaseArray();
	private TileEntity e;
	
	/** The tileentity will be the center of the building **/
	public BasePlan(TileEntity ent, BlockPlan[][][] plan){
		this.prioPlan = plan;
		this.e = ent;
	}
	
	public boolean putBlock(int blockID){
		for(int prio = 0; prio < 10; prio++)
		for(int y = 0; y < 5; y++)
		for(int x = -2; x < 3; x++)
		for(int z = -2; z < 3; z++){
			if(this.prioPlan[y][x+2][z+2] == null)
				continue;
			if(this.prioPlan[y][x+2][z+2].prio != prio)
				continue;
			int trueX = this.e.xCoord + x;
			int trueY = this.e.yCoord + y;
			int trueZ = this.e.zCoord + z;
			int currentBlock = this.e.worldObj.getBlockId(trueX, trueY, trueZ);
			if(!this.e.worldObj.isAirBlock(trueX, trueY, trueZ))
				continue;
			this.e.worldObj.setBlock(trueX, trueY, trueZ, prioPlan[y][x+2][z+2].blockID, prioPlan[y][x+2][z+2].meta, 1 | 2);
			return true;
		}
		return false;
	}
	
	public boolean isValid(int blockID){
		return this.isValid(blockID, false, false);
	}
	
	public boolean isCreeped(){
		return this.isValid(HiveCraft.creepID, true, false);
	}
	
	public boolean isCreepedNoStart(){
		return this.isValid(HiveCraft.creepID, false, false);
	}
	
	public boolean isValid(int blockID, boolean cheackStart, boolean airOk){
		for(int y = 0; y < 5; y++)
		for(int x = -2; x < 3; x++)
		for(int z = -2; z < 3; z++){
			if(!cheackStart && 0 == y && y == x && x == z)
				continue;
			if(prioPlan[y][x+2][z+2] == null)
				continue;
			int trueX = this.e.xCoord + x;
			int trueY = this.e.yCoord + y;
			int trueZ = this.e.zCoord + z;
			if(airOk && this.e.worldObj.isAirBlock(trueX, trueY, trueZ))
				continue;
			if(blockID != this.e.worldObj.getBlockId(trueX, trueY, trueZ))
				return false;
		}
		return true;
	}
	
	public void forceFill(){
		this.forceFill(false);
	}
	
	public void forceFill(boolean forceStart){
		for(int y = 0; y < 5; y++)
		for(int x = -2; x < 3; x++)
		for(int z = -2; z < 3; z++){
			if(!forceStart && 0 == y && y == x && x == z)
				continue;
			if(this.prioPlan[y][x+2][z+2] == null)
				continue;
			int trueX = this.e.xCoord + x;
			int trueY = this.e.yCoord + y;
			int trueZ = this.e.zCoord + z;
			
			this.e.worldObj.setBlock(trueX, trueY, trueZ, prioPlan[y][x+2][z+2].blockID, prioPlan[y][x+2][z+2].meta, 1 | 2);
			//this.e.worldObj.markBlockForRenderUpdate(trueX, trueY, trueZ);
		}
	}
	
	public boolean setMaster(NBTTagCompound genetics){
			for(int y = 0; y < 5; y++)
			for(int x = -2; x < 3; x++)
			for(int z = -2; z < 3; z++){
				if(prioPlan[y][x+2][z+2] == null)
					continue;
				if(this.prioPlan[y][x+2][z+2].prio != 0)
					continue;
				int trueX = this.e.xCoord + x;
				int trueY = this.e.yCoord + y;
				int trueZ = this.e.zCoord + z;
				int currentBlock = this.e.worldObj.getBlockId(trueX, trueY, trueZ);
				TileEntity ent = e.worldObj.getBlockTileEntity(trueX, trueY, trueZ);
				
				if(ent == null || !(ent instanceof TileEntityHiveStructure))
					return false;
				TileEntityHiveStructure hive = (TileEntityHiveStructure) ent;
				hive.youAreTheOne(genetics);
			}
			return true;
	}
	
	public boolean putCreep(){
		for(int prio = 0; prio < 10; prio++)
		for(int y = 0; y < 5; y++)
		for(int x = -2; x < 3; x++)
		for(int z = -2; z < 3; z++){
			if(this.prioPlan[y][x+2][z+2] == null)
				continue;
			if(this.prioPlan[y][x+2][z+2].prio != prio)
				continue;
			int trueX = this.e.xCoord + x;
			int trueY = this.e.yCoord + y;
			int trueZ = this.e.zCoord + z;
			int currentBlock = this.e.worldObj.getBlockId(trueX, trueY, trueZ);
			if(!this.e.worldObj.isAirBlock(trueX, trueY, trueZ))
				continue;
			this.e.worldObj.setBlock(trueX, trueY, trueZ, HiveCraft.blockCreep.blockID);
			this.e.worldObj.setBlockTileEntity(trueX, trueY, trueZ, new TileEntityCocoon(e.xCoord,e.yCoord,e.zCoord));
			return true;
		}
		return false;
	}
	
}
