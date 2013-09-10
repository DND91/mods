package mods.dnd91.minecraft.hivecraft.book.page.pages;

import mods.dnd91.minecraft.hivecraft.book.page.Page;

public class Queens implements Page {

	@Override
	public String textOnPage(int p) {
		String s = "";
		switch(p){
		case 0:
			s = "A queen";
			s += "Shift + RMB and she will evolve to a nest. \n";
			s += "Once placed you cant pick her up. \n";
			break;
		}
		return s;
	}

	@Override
	public int numberOfPages() {
		return 1;
	}

}
