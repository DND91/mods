package mods.dnd91.minecraft.hivecraft.book.page.pages;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.book.page.Page;

public class Drones implements Page{

	@Override
	public String textOnPage(int p) {
		String s = "";
		switch(p){
		case 0:
			s = "Drones\n";
			s +="Amongst the most stupied and fantastic ";
			s +="creatures i ever worked with. They are ";
			s +="'programmable' with smells and easly distracted. ";
			break;
		case 1:
			s = "Odorem Glandem\n";
			s += "Also nicked stink bomb.\n";
			
		case 2:
			s = "Pick up\n";
			s += "\t\n<"+HiveCraft.itemBiomass.itemID+">\n\n";
			s += "<"+Block.chest.blockID+">\n\t";
			break;
		case 3:
			s = "Dump to\n";
			s += "\t\n<"+Block.chest.blockID+">\n";
			s += "<"+HiveCraft.itemBiomass.itemID+">\n\t";
			break;
		}
		return s;
	}

	@Override
	public int numberOfPages() {
		return 4;
	}

}
