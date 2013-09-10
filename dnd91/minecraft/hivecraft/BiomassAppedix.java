package mods.dnd91.minecraft.hivecraft;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BiomassAppedix {

	static public int getBiomassValue(ItemStack stack){
		return getBiomassValue(stack.getItem());
	}
	
	static public int getBiomassValue(Item item){
		if(item == Item.appleRed){
			return 100;
		}else{
			return 0;
		}
	}
	
	static public int getBiomassContent(Item item){
		if(item == Item.appleRed)
			return 6;
		else if(item == HiveCraft.itemBiomass)
			return 1;
		else			
			return 100;
	}
	
}
