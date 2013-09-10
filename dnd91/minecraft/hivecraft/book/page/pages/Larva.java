package mods.dnd91.minecraft.hivecraft.book.page.pages;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.book.page.Page;
import net.minecraft.block.Block;

public class Larva implements Page {

	@Override
	public String textOnPage(int p) {
		String s = "";
		switch(p){
		case 0:
			s = "At the larva stage the being can't be used for that much. ";
			s += "She will need to mature and evolve. All larvas are female. ";
			s += "As you dvelve deeper you might be lucky and see a male larva. ";
			s += "Rare and often seen as cancer by other hive member. ";
			break;
		case 1:
			s = "To mature and evolve the larva you will need to make a spawnpool.\n";
			s += "\t\n<"+Block.cobblestone.blockID+"><"+Block.glass.blockID+"><"+Block.cobblestone.blockID+">\n\n";
			s += "<"+Block.cobblestone.blockID+"><"+Block.glass.blockID+"><"+Block.cobblestone.blockID+">\n\n";
			s += "<"+HiveCraft.itemBiomass.itemID+"><"+Block.cobblestone.blockID+"><"+HiveCraft.itemBiomass.itemID+">\n\t";
			s += "Ignore the glass.";
			break;
		}
		return s;
	}

	@Override
	public int numberOfPages() {
		return 2;
	}

}
