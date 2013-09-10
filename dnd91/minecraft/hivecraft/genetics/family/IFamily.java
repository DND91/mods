package mods.dnd91.minecraft.hivecraft.genetics.family;

import net.minecraft.item.ItemStack;
import mods.dnd91.minecraft.hivecraft.structure.buildplan.BlockPlan;

public interface IFamily {
	
	public String getName();
	
	/** Hatching parameters **/
	public int lightValue();
	public boolean needRoof();
	public int moistValue();
	public int heatValue();
	
	public int matureTimeEgg();
	public int matureTimeLarva();
	
	public int agressivness();
	public int resilience();
	public int dominant();
	public int strength();
	public int health();
	public int linkStrength();
	
	public int quality();
	
	public ItemStack getLarva();
	public ItemStack getEgg();

	/**
	 * 
	 * @param craft - Queen, Drone, Builder, Worker, Soldier
	 * @param occupation - "SubCraft". Drone -> Carrier
	 * @return id of hatchling from the static hatchling list found in FamilyAppedix
	 * return -1 if false
	 */
	public int getHatchling(String craft, String occupation);
	
	/**
	 * 
	 * @param name - name for building. bioWorks, bioAssembler
	 * @return the scimatics for the plan return null if there is none
	 */
	public int getBuilding(String name);	
}
