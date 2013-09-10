package mods.dnd91.minecraft.hivecraft.structure.interfaces;

public interface IHiveCraftHeatHandler {
	/**
	 * 
	 * @param h - heat you want to put in
	 * @return heat that could not be added
	 */
	public int addHeat(int h);
	
	/**
	 * 
	 * @param h - how much heat you want to take
	 * @return how much heat you get
	 */
	public int takeHeat(int h);
	
	/**
	 * 
	 * @return returns the max heat the thing can hold
	 */
	public int getMaxHeat();
	
	/**
	 * 
	 * @return
	 */
	public float getEff();
}
