package mods.dnd91.minecraft.hivecraft;

import java.util.EnumSet;

import net.minecraft.world.World;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler {
	

	public static HiveCraftWorldData worldData = null;
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if(type.equals(EnumSet.of(TickType.WORLDLOAD))){
			World world = null;
			for(int i = 0; i < tickData.length; i++)
				if(tickData[i] instanceof World)
					world = (World)tickData[i];
			if(world == null){
				return;
			}
			
			worldData = HiveCraftWorldData.forWorld(world);
			if(worldData.isNew){
				//Genetics.init();
				//Mutations.init();
				//worldData.markDirty();
				//worldData.isNew = false;
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		// TODO Auto-generated method stub
		return EnumSet.of(TickType.WORLDLOAD);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "HiveCraft_Ticker";
	}
	
}
