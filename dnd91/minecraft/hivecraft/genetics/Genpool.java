package mods.dnd91.minecraft.hivecraft.genetics;

import java.util.Random;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.genetics.family.IFamily;
import net.minecraft.block.Block;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Genpool {
	private static Random random = new Random();
	
	public static ItemStack getRandomEggsack(){
    	NBTTagCompound nbtCompound = new NBTTagCompound();
    	NBTTagCompound genetics = new NBTTagCompound();
    	
    	int choice = random.nextInt(FamilyAppedix.getFamilyLength());
    	choice = FamilyAppedix.getFamilyAt(choice);
    	IFamily family = FamilyAppedix.getFamily(choice);
    	ItemStack stack = family.getEgg();
    	
    	genetics.setInteger("familyID", choice);
    	nbtCompound.setCompoundTag("genetics", genetics);
    	stack.setTagCompound(nbtCompound);
    	
    	return stack;
    } 
	
	public static ItemStack getRandomGeneticBiomass(){
		ItemStack itemstack = new ItemStack(HiveCraft.itemBiomass, 1);
		
		return itemstack;
	}
	
	public static void setFamilyIDInformation(int j, NBTTagCompound compound){
		NBTTagCompound genetics = new NBTTagCompound();
		genetics.setInteger("quality", 10);
		switch(j){
		case 0:
			familyZero(genetics);
			break;
		case 1:
			familyOne(genetics);
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		};
		compound.setCompoundTag("genetics", genetics);
	}
	
	public static void familyZero(NBTTagCompound nbttagcompound){
		/** Basic Info Section **/
		nbttagcompound.setInteger("familyName", 0);
		nbttagcompound.setInteger("colorID", random.nextInt(ItemDye.dyeColorNames.length));
		
		/** Habitat Section **/
		nbttagcompound.setBoolean("needDarkness", true);
		nbttagcompound.setInteger("lightValue", 13);
		nbttagcompound.setBoolean("needRoof", true);
				
		/** Age Section **/
		nbttagcompound.setInteger("matureTimeEgg", 800);
		nbttagcompound.setInteger("matureTimeLarva", 1600);
		
		/** Stat Section **/
		nbttagcompound.setInteger("agressivness",10);
		nbttagcompound.setInteger("resilience",10);
		nbttagcompound.setInteger("dominant",10);
		nbttagcompound.setInteger("strength", 2);
		nbttagcompound.setInteger("baseHealth",10);
		nbttagcompound.setInteger("linkLength", 64);	
		
		/** Models & Textures **/
		nbttagcompound.setInteger("eggModel", 0);
		nbttagcompound.setInteger("larvModel", 0);
		nbttagcompound.setInteger("queenModel", 0);
		nbttagcompound.setInteger("droneModel", 0);
		nbttagcompound.setInteger("soldierModel", 0);
		nbttagcompound.setInteger("builderModel",0);
		
		/** Buildings **/
		nbttagcompound.setInteger("buildType", 0);
		nbttagcompound.setInteger("tooBecome", 0);
		
		nbttagcompound.setInteger("QueenNest", 0);
		nbttagcompound.setInteger("BioAsembler", 0);
		nbttagcompound.setInteger("BioWorks", 0);
		
		NBTTagCompound ai = new NBTTagCompound();
		
		//ADD AI
		
		nbttagcompound.setTag("AI", ai);
	}
	
	public static void familyOne(NBTTagCompound nbttagcompound){
		/** Basic Info Section **/
		nbttagcompound.setInteger("familyName", 1);
		nbttagcompound.setInteger("colorID", random.nextInt(ItemDye.dyeColorNames.length));

		/** Habitat Section **/
		nbttagcompound.setBoolean("needDarkness", false);
		nbttagcompound.setInteger("lightValue", 0);
		nbttagcompound.setBoolean("needRoof", true);
		
		/** Age Section **/
		nbttagcompound.setInteger("matureTimeEgg", 1600);
		nbttagcompound.setInteger("matureTimeLarva", 1600);
		nbttagcompound.setInteger("matureTimeChild", 3200);
		nbttagcompound.setInteger("matureTimeAdult", 32000);
		nbttagcompound.setInteger("matureTimeOld", 24000);
		nbttagcompound.setInteger("matureTimeAncient", 10000);
		nbttagcompound.setInteger("ancientChance", 1);
		
		/** Stat Section **/
		nbttagcompound.setInteger("agressivness",2);
		nbttagcompound.setInteger("resilience",50);
		nbttagcompound.setInteger("dominant",25);
		nbttagcompound.setInteger("strength", 3);
		nbttagcompound.setInteger("baseHealth",6);
		nbttagcompound.setInteger("linkLength", 128);
		
		/** Models & Textures **/
		nbttagcompound.setInteger("eggModel", 1);
		nbttagcompound.setInteger("larvModel", 1);
		nbttagcompound.setInteger("queenModel", 1);
		nbttagcompound.setInteger("droneModel", 1);
		nbttagcompound.setInteger("soldierModel", 1);
		nbttagcompound.setInteger("builderModel", 1);
		
		/** Buildings **/
		nbttagcompound.setInteger("tooBecome", 0);
		nbttagcompound.setInteger("buildType", 0);
		
		nbttagcompound.setInteger("QueenNest", 1);
		nbttagcompound.setInteger("BioAsembler", 1);
		nbttagcompound.setInteger("BioWorks", 1);
	}
}
