package mods.dnd91.minecraft.hivecraft.book.page.pages;

import mods.dnd91.minecraft.hivecraft.book.page.Page;

public class Biomass implements Page{

	@Override
	public String textOnPage(int p) {
		String s = "";
		switch(p){
		case 0:
			s =  "Ju§kni§r 08, §k20§r§m01§r00 - Oven Facility, Minecraftia\n";
			s += "\n";
			s += "Had to scramble together alot of resources and make a ";
			s += "Funace or Oven Facility call it what you want. ";
			s += "The first sample of biomass finaly got done. ";
			s += "Driping as a slimeball, but in someway pure.";
			s += "All of life is made of this pure material. Incredible";
			break;
		}
		return s;
	}

	@Override
	public int numberOfPages() {
		return 1;
	}

}
