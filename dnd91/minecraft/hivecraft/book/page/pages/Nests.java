package mods.dnd91.minecraft.hivecraft.book.page.pages;

import mods.dnd91.minecraft.hivecraft.book.page.Page;

public class Nests implements Page {

	@Override
	public String textOnPage(int p) {
		String s = "";
		switch(p){
		case 0:
			s = "The queens nest\n";
			s += "Comes in many diffrent sizes and... well you will see. ";
			s += "They are the center of your hive and only mantain one at a given time. ";
			s += "Insects from diffrent queens will attack eachother. That goes for buildings too. ";
			s += "You can alway isolate them with alot of space. ";
			break;
		case 1:
			s = "The queen nest will take food and lay eggs. ";
			s += "You will be able to add mutations directly on them. ";
			s += "Guide them early and they will prospure.";
			s += "Beware that you might... get bad gens. ";
			break;
		}
		return s;
	}

	@Override
	public int numberOfPages() {
		return 2;
	}

}
