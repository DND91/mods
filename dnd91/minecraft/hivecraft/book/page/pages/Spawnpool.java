package mods.dnd91.minecraft.hivecraft.book.page.pages;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.book.page.Page;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class Spawnpool implements Page {

	@Override
	public String textOnPage(int p) {
		String s = "";
		switch(p){
		case 0:
			s = "So you finaly made it this far. ";
			s += "This kind of research takes time before you get anywhere. ";
			s += "But now, when you have. This, so called spawnpool, will be of ";
			s += "great centralization of your work in the begining. ";
			s += "There is some preperations needed. ";
			s += "And alot of food. ";
			break;
		case 1:
			s = "Explonation of the spawnpools interface.\n";
			s += "Gold nugget, main mutator. Tell the larva what to become.\n";
			s += "Raw porkchop, food input\n";
			s += "Slingry brown worm thingy is where you put the larva\n";
			s += "3 Ghast tears, upgrades.\n";
			s += "We will only work with the first ghast tears slot.";
			break;
		case 2:
			s = "How to make a queen:\n";
			s += "You will need to make a queen the first thing you do\n";
			s += "Gold nugget will need a sticky crown:\n";
			s += "\t\n<"+Item.silk.itemID+"><"+Item.stick.itemID+"><"+Item.silk.itemID+">\n\n";
			s += "<"+Item.stick.itemID+"><"+Block.glass.blockID+"><"+Item.stick.itemID+">\n\n";
			s += "<"+Item.silk.itemID+"><"+Block.sapling.blockID+"><"+Item.silk.itemID+">\n\t";
			s += "Then put in the larva and crown in the spawnpool and wait.";
			break;
		case 3:
			s = "How to make a builder:\n";
			
			break;
		case 4:
			s = "How to make a drone:\n";
			s += "Gold nugget will need some standard drone tools:\n";
			s += "\t\n<"+Item.stick.itemID+">\n\n";
			s += "<"+HiveCraft.itemBiomass.itemID+">\n\t";
			s += "Then put the larva and tools in the spawnpool and wait.";
			break;
		}
		return s;
	}

	@Override
	public int numberOfPages() {
		return 5;
	}

}
