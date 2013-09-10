package mods.dnd91.minecraft.hivecraft.book.page.pages;

import mods.dnd91.minecraft.hivecraft.book.page.Page;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class Crowns implements Page {

	@Override
	public String textOnPage(int p) {
		String s = "";
		switch(p){
		case 0:
			s = "The crown is a main mutation piece and come in many diffrent grades. ";
			s += "The first one you made was the sticky crown and count to the so called ";
			s += "tier one. ";
			break;
		case 1:
			s = "Sticky Crown\n";
			s += "Used for the standard low queen, a good starter.\n";
			s += "Recipe:\n";
			s += "\t\n<"+Item.silk.itemID+"><"+Item.stick.itemID+"><"+Item.silk.itemID+">\n\n";
			s += "<"+Item.stick.itemID+"><"+Block.glass.blockID+"><"+Item.stick.itemID+">\n\n";
			s += "<"+Item.silk.itemID+"><"+Block.sapling.blockID+"><"+Item.silk.itemID+">\n\n\t";
			break;
		}
		return s;
	}

	@Override
	public int numberOfPages() {
		return 2;
	}

}
