package mods.dnd91.minecraft.hivecraft.genetics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.genetics.family.IFamily;
import mods.dnd91.minecraft.hivecraft.structure.buildplan.BlockPlan;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Genetics
 * Isolator between static genetics and current genetics at
 * individual level. Will only save information diffrent from
 * the static.
 * 
 * @author DND91
 */
public class Genetics implements IFamily{
	private static Random random = new Random();
	NBTTagCompound genetics = null;
	
	int familyID;
	IFamily family;
	
	public Genetics(NBTTagCompound g){
		genetics = g;
		familyID = g.getInteger("familyID");
		family = FamilyAppedix.getFamily(familyID);
	}

	String name = null;
	@Override
	public String getName() {
		if(name == null && genetics.hasKey("familyName")){
			name = genetics.getString("familyName");
		}else if(name == null){
			name = family.getName();
		}
		return name;
	}
	
	public void setName(String n){
		name = n;
		genetics.setString("familyName", name);
	}
	
	public void setNeedDarkness(Boolean b){
		genetics.setBoolean("needDarkness", b);
	}

	int maxLight = -1;
	@Override
	public int lightValue() {
		if(maxLight == -1 && genetics.hasKey("lightValue")){
			maxLight = genetics.getInteger("lightValue");
		}else if(maxLight == -1){
			maxLight = family.lightValue();
		}
		return maxLight;
	}
	
	public void setMaxLight(int i){
		maxLight = i;
		genetics.setInteger("lightValue", i);
	}

	@Override
	public boolean needRoof() {
		if(genetics.hasKey("needRoof")){
			return genetics.getBoolean("needRoof");
		}else{
			return family.needRoof();
		}
	}

	int matureTimeEgg = -1;
	@Override
	public int matureTimeEgg() {
		if(matureTimeEgg == -1 && genetics.hasKey("matureTimeEgg")){
			matureTimeEgg = genetics.getInteger("matureTimeEgg");
		}else if(matureTimeEgg == -1){
			matureTimeEgg = family.matureTimeEgg();
		}
		return matureTimeEgg;
	}
	
	public void setMatureTimeEgg(int i){
		matureTimeEgg = i;
		genetics.setInteger("matureTimeEgg", i);
	}

	int matureTimeLarva = -1;
	@Override
	public int matureTimeLarva() {
		if(matureTimeLarva == -1 && genetics.hasKey("matureTimeLarva")){
			matureTimeLarva = genetics.getInteger("matureTimeLarva");
		}else if(matureTimeLarva == -1){
			matureTimeLarva = family.matureTimeLarva();
		}
		return matureTimeLarva;
	}
	
	public void setMatureTimeLarva(int i){
		matureTimeLarva = i;
		genetics.setInteger("matureTimeLarva", i);
	}

	int agressivness = -1;
	@Override
	public int agressivness() {
		if(agressivness == -1 && genetics.hasKey("agressivness")){
			agressivness = genetics.getInteger("agressivness");
		}else if(agressivness == -1){
			agressivness = family.agressivness();
		}
		return agressivness;
	}
	
	public void setAgressivness(int i){
		agressivness = i;
		genetics.setInteger("agressivness", i);
	}

	int resilience = -1;
	@Override
	public int resilience() {
		if(resilience == -1 && genetics.hasKey("resilience")){
			resilience = genetics.getInteger("resilience");
		}else if(resilience == -1){
			resilience = family.resilience();
		}
		return resilience;
	}
	
	public void setResilience(int i){
		resilience = i;
		genetics.setInteger("resilience", i);
	}

	int dominant = -1;
	@Override
	public int dominant() {
		if(dominant == -1 && genetics.hasKey("dominant")){
			dominant = genetics.getInteger("dominant");
		}else if(dominant == -1){
			dominant = family.dominant();
		}
		return dominant;
	}
	
	public void setDominant(int i){
		dominant = i;
		genetics.setInteger("dominant", i);
	}

	int strength = -1;
	@Override
	public int strength() {
		if(strength == -1 && genetics.hasKey("strength")){
			strength = genetics.getInteger("strength");
		}else if(strength == -1){
			strength = family.strength();
		}
		return strength;
	}
	
	public void setStrength(int i){
		strength = i;
		genetics.setInteger("strength", i);
	}

	int health = -1;
	@Override
	public int health() {
		if(health == -1 && genetics.hasKey("health")){
			health = genetics.getInteger("health");
		}else if(health == -1){
			health = family.health();
		}
		return health;
	}
	
	public void setHealth(int i){
		health = i;
		genetics.setInteger("health", i);
	}

	int linkStrength = -1;
	@Override
	public int linkStrength() {
		if(linkStrength == -1 && genetics.hasKey("linkStrength")){
			linkStrength = genetics.getInteger("linkStrength");
		}else if(linkStrength == -1){
			linkStrength = family.linkStrength();
		}
		return linkStrength;
	}
	
	public void setLinkStrength(int i){
		linkStrength = i;
		genetics.setInteger("linkStrength", i);
	}

	@Override
	public int getHatchling(String craft, String occupation) {
		if(genetics.hasKey(craft+occupation))
			return genetics.getInteger(craft+occupation);
		else
			return family.getHatchling(craft, occupation);
	}

	@Override
	public int getBuilding(String name) {
		if(genetics.hasKey(name))
			return genetics.getInteger(name);
		else
			return family.getBuilding(name);
	}

	@Override
	public int quality() {
		if(genetics.hasKey("quality"))
			return genetics.getInteger("quality");
		else
			return family.quality();
	}
	
	public NBTTagCompound getTagCompound(){
		return this.genetics;
	}
	
	public NBTTagCompound getAI(){
		return this.genetics.getCompoundTag("AI");
	}
	public boolean hasProgram(){
		return this.genetics.hasKey("program");
	}
	public void setProgram(NBTTagCompound program){
		genetics.setCompoundTag("program", program);
	}
	public NBTTagCompound getProgram(){
		return this.genetics.getCompoundTag("program");
	}

	@Override
	public ItemStack getLarva() {
		if(genetics.hasKey("larva"))
			return ItemStack.loadItemStackFromNBT(genetics.getCompoundTag("larva"));
		else
			return family.getLarva();
	}

	@Override
	public ItemStack getEgg() {
		if(genetics.hasKey("egg"))
			return ItemStack.loadItemStackFromNBT(genetics.getCompoundTag("egg"));
		else
			return family.getEgg();
	}

	int moistValue = -1;
	@Override
	public int moistValue() {
		if(moistValue == -1 && genetics.hasKey("moistValue")){
			moistValue = genetics.getInteger("moistValue");
		}else if(moistValue == -1){
			moistValue = family.moistValue();
		}
		return moistValue;
	}

	int heatValue = -1;
	@Override
	public int heatValue() {
		if(heatValue == -1 && genetics.hasKey("heatValue")){
			heatValue = genetics.getInteger("heatValue");
		}else if(heatValue == -1){
			heatValue = family.heatValue();
		}
		return heatValue;
	}
	
}
