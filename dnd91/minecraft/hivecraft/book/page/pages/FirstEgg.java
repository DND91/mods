package mods.dnd91.minecraft.hivecraft.book.page.pages;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.book.page.Page;
import net.minecraft.block.Block;

public class FirstEgg implements Page{

	@Override
	public String textOnPage(int p) {
		String s = "";
		switch(p){
		case 0:
			s =  "Au§kni§r 16, §k20§r§m01§r00 - Desert of U, Minecraftia\n";
			s += "\n";
			s += "Found a nest today and while excavating i found one egg. ";
			s += "To my suprise it was a Magic Worm egg. According to the tome ";
			s += "it is quite a rare spicie.\n";
			s += "Time to move back home.";
			break;
		case 1:
			s = "Au§kni§r 18, §k20§r§m01§r00 - New Found Wasteland\n";
			s += "Im certain that there was no wasteland on the path home. ";
			s += "Looks like something eaten away everything. After looking over ";
			s += "the maps i have come to the conclustion this wasteland was once ";
			s += "part och Quickwood. Wounder what horrible faith broth it to this.";
			break;
		case 2:
			s =  "Au§kni§r 20, §k20§r§m01§r00 - Wasteland lake\n";
			s += "After exploring the wasteland for a while i happend to stumble uppon ";
			s += "a \"lake\" or whats left of it... its more of a green thick sludge now. ";
			s += "With a stick i found out that the bottom is full of extremely agressiv larvas, ";
			s += "chewing trough the wood like it was butter. Looks loke §knether void§r have started ";
			s += "sepping trough reality it self. Wounder if the nether is once more open for visit.";
			break;
		case 3:
			s =  "Sep§kni§r 18, §k20§r§m01§r00 - The Stable, Minecraftia\n";
			s += "\n";
			s += "Finaly home! Trip was... special indeed. Someone had experimented on some "; 
			s += "villagers. But now for a more pressing matter, i have skim trough the tome. ";
			s += "looking for something that can help me hatch the egg and yes indeed i found something. ";
			s += "A hatchery, looking something like this when crafting. Just need to build a nest. ";
			s += "\n\n";
			s += "\t\n<"+Block.wood.blockID+":1><"+Block.dirt.blockID+"><"+Block.wood.blockID+":1>\n";
			s += "\n<"+Block.wood.blockID+":1><"+Block.wood.blockID+":1><"+Block.wood.blockID+":1>\t";
			break;
		}
		return s;
	}

	@Override
	public int numberOfPages() {
		return 2;
	}

}
