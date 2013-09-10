package mods.dnd91.minecraft.hivecraft.structure.buildplan;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDebugBluePlan extends TileEntity{
	
	public TileEntityDebugBluePlan(){}
	
	private int tickCount = 0;
	public BasePlan plan = new BasePlan(this, BaseArrays.BioWorksPoolDesign());
	public boolean done = false;
	
	@Override
	public void updateEntity(){
		if(done)
			return;
		
		tickCount++;
		
		if(tickCount % 10 == 0){
			plan.putCreep();
			if(plan.isCreepedNoStart()){
				plan.forceFill(true);
			}
		}
	}

}
