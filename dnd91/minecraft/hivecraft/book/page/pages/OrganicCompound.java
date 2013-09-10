package mods.dnd91.minecraft.hivecraft.book.page.pages;

import mods.dnd91.minecraft.hivecraft.book.page.Page;

public class OrganicCompound implements Page{

	@Override
	public String textOnPage(int p) {
		String s = "";
		switch(p){
		case 0:
			s =  "Ju§kni§r 08, §k20§r§m01§r00 - The Stable, Minecraftia\n";
			s += "\n";
			s += "Basicly, the core component is Biomass. ";
			s += "But before you can get Biomass you need to make ";
			s += "Organic Compounds. Smell horrible and you probobly get ";
			s += "sick if you eat one. ";
			s += "With alot of heat you can purifie a sample so it can ascend to ";
			s += "Biomass as the tome put it.";
			break;
		case 1:
			s = "Prepared alot of it. Even filled up my hopper over the furnace. ";
			s += "Wounder of it is going for the rest of us. Just got words from ";
			s += "Simon that he had just hatched his first egg. Im... im.. so.. ";
			s += "Only thing coming to my mind is.. That basterd.";
			break;
		}
		return s;
	}

	@Override
	public int numberOfPages() {
		return 2;
	}

}
