package mods.dnd91.minecraft.hivecraft.book.page.pages;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.book.page.Page;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class OldTome implements Page{

	@Override
	public String textOnPage(int p) {
		String s = "";
		switch(p){
		case 0:
			s =  "Ju§kni§r 05, §k20§r§m01§r00 - Vermin Tunnel, Minecraftia\n";
			s += "\n";
			s += "We had to take shellter in the vermin tunnels under the ruins  of ";
			s += "§kKraghz§r. The brood is at the moment scouting above. ";
			s += "Hell with the end for unleaching this herasy upon us. But who can blame them, ";
			s += "for once we are the tricksters and betrayers. ";
			s += "Tomorrow i will walk trough the swamp east of here and hope the old hag don't get me.";
			s += "The rest will seek the fortune at home, for we do not wan't to revile all.";
			s += "\n\n";
			s += "Good night, my Ather Star";
			break;
		case 1:
			s =  "Ju§kni§r 06, §k20§r§m01§r00 - The Stable, Minecraftia\n";
			s += "\n";
			s += "The sun finaly rose over the horizion showing its pure tears. ";
			s += "With luck i was the first to come alive after the cold night. ";
			s += "I looked upon my friend and gave Shara a farewell kiss, then left. ";
			s += "The trip went well, the hag was still asleep when i passed by. ";
			s += "I started the translation of the tomes we \"borrowed\" and tomorrow ";
			s += "will the writing comence.";
			break;
		case 2:
			s =  "Ju§kni§r 07, §k20§r§m01§r00 - The Stable, Minecraftia\n";
			s += "\n";
			s += "The first pages of the tome where easy and i have now made some ";
			s += "bio compound cooking at the moment in the furnace to become... ";
			s += "Think the correct word for it will be §nBiomass§r. Never seen that word before.";
			s += "Anyways... ";
			s += "Incase some dirt with biologic stuff(like sapplings or meat), mix and cook for 2 hours. ";
			s += "A quick look trough the tome gives me the grasp that this mass ";
			s += "will be needed §nalot.§r";
			break;
		case 3:
			s =  "Ju§kni§r 09, §k20§r§m01§r00 - The Swamp, Minecraftia\n";
			s += "\n";
			s += "Stumbeld over some old note left in the tome. ";
			s += "Was hard work to translate but finaly got it. There is some left that i will ";
			s += "work on later. ";
			s += "Translated to this recipe and im now out hunting for the components. ";
			s += "Think it is a tool and used for collecting eggs from nests. ";
			s += "\n\n\t<"+Item.dyePowder.itemID+":1><"+Block.planks.blockID+":0>E";
			s += "<"+Block.planks.blockID+":0><"+Item.dyePowder.itemID+":11>\t\n";
			break;
		case 4:
			s =  "Ju§kni§r 10, §k20§r§m01§r00 - Quickwood, Minecraftia\n";
			s += "\n";
			s += "FINALY! I got all that i needed. Heading home. ";
			s += "Took the oppertunity of the trip to hunt for ";
			s += "more Biomass ingredients, 2 chickens and 3 pigs. ";
			s += "Passing by the swamp someone had broken into the ";
			s += "old hags house. Claw markings. I can guess who they where. ";
			break;
		case 5:
			s =  "Ju§kni§r 11, §k20§r§m01§r00 - The Stable, Minecraftia\n";
			s += "\n";
			s += "Wow, had forgot how hard crafting is. ";
			s += "Took alot longer then i thought, to craft the collector tool. ";
			s += "But finaly made it. Started to cook up some more ";
			s += "organic compounds. Will have to go out tomorrow again. ";
			s += "I could brave the night, it is often safer then day light. ";
			s += "But strange things have started to happen, last night i had to fight ";
			s += "a HUGE, and i mean HUGE spider.";
			break;
		}
		return s;
	}

	@Override
	public int numberOfPages() {
		return 6;
	}

}
