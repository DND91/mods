package mods.dnd91.minecraft.hivecraft.book.page.pages;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.book.page.Page;
import net.minecraft.block.Block;

public class Collector implements Page {

	@Override
	public String textOnPage(int p) {
		String s = "";
		switch(p){
		case 0:
			s =  "Au§kni§r 08, §k20§r§m01§r00 - Desert of U, Minecraftia\n";
			s += "\n";
			s += "Took a month to travle here and where the travle filled with ";
			s += "strange creatures! The skeleton of a dead villager rised just by my ";
			s += "side and start shooting arrows at me! Had to flee for my life... ";
			break;
		case 1:
			s += "Did meet Tom, he looked a bit scared. Like something was following him. ";
			s += "Most have been my imagination. ";
			s += "At the moment im looking for something that look like this\n";
			s += "\t\n\n<"+HiveCraft.blockBioNest.blockID+":0><"+HiveCraft.blockBioNest.blockID+":1><"+HiveCraft.blockBioNest.blockID+":2>\t";
			break;
		}
		return s;
	}

	@Override
	public int numberOfPages() {
		return 2;
	}

}
