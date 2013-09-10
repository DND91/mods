package mods.dnd91.minecraft.hivecraft.book.page.pages;

import mods.dnd91.minecraft.hivecraft.book.page.Page;

public class Hatchery implements Page{

	@Override
	public String textOnPage(int p) {
		String s = "";
		switch(p){
		case 0:
			s = "Sep§kni§r 19, §k20§r§m01§r00 - Nesting Grounds, The Stable";
			s += "Made a stone pickaxe, dug out a big chunk of stone under ";
			s += "The Stable. It's all dark, moist and warm. ";
			s += "According to the tome diffrent species have diffrent ";
			s += "hatching requirements. But there is 5 things to think about ";
			s += "darkness, light, roof, moist and heat. ";
			break;
		case 1:
			s = "Sep§kni§r 23, §k20§r§m01§r00 - Nesting Grounds, The Stable";
			s += "The hatching nest is done and i crafted 8 hatchers, prepering for the end. ";
			s += "Shara dropped by today leaving some notes about the creatures we are playing with. ";
			s += "Some of hers had started showing of... psychic abilities and super fast rate of ";
			s += "mutation, making the lesser onces explode in an inferno of mutation. ";
			break;
		}
		return s;
	}

	@Override
	public int numberOfPages() {
		return 2;
	}

}
