package mods.dnd91.minecraft.hivecraft.genetics.family;

import net.minecraft.item.ItemStack;
import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.genetics.FamilyAppedix;

public class MagicWorm implements IFamily{

	@Override
	public String getName() {
		return "Magic Worm";
	}

	@Override
	public boolean needRoof() {
		return true;
	}

	@Override
	public int matureTimeEgg() {
		return 800;
	}

	@Override
	public int matureTimeLarva() {
		return 1600;
	}

	@Override
	public int agressivness() {
		return 10;
	}

	@Override
	public int resilience() {
		return 10;
	}

	@Override
	public int dominant() {
		return 10;
	}

	@Override
	public int strength() {
		return 2;
	}

	@Override
	public int health() {
		return 10;
	}

	@Override
	public int linkStrength() {
		return 64;
	}

	@Override
	public int getHatchling(String craft, String occupation) {
		// TODO Auto-generated method stub
		if(craft == "queen" && occupation == "any"){
			return FamilyAppedix.ladybugQueen;
		}else if(craft == "drone" && occupation == "carrier"){
			return FamilyAppedix.fastRunnerDrone;
		}
		return -1;
	}

	@Override
	public int getBuilding(String name) {
		// TODO Auto-generated method stub
		if(name == "test")
			return FamilyAppedix.debugBlock;
		else
			return -1;
	}

	@Override
	public int quality() {
		return 10;
	}

	@Override
	public ItemStack getLarva() {
		return new ItemStack(HiveCraft.itemLarva,1,0);
	}

	@Override
	public ItemStack getEgg() {
		return new ItemStack(HiveCraft.itemEggsack, 1,0);
	}

	@Override
	public int lightValue() {
		return 8;
	}

	@Override
	public int moistValue() {
		return 10;
	}

	@Override
	public int heatValue() {
		return 10;
	}
	
}
