package mods.dnd91.minecraft.hivecraft.book.page;

import java.util.ArrayList;
import java.util.List;

import mods.dnd91.minecraft.hivecraft.book.Knowledge;
import mods.dnd91.minecraft.hivecraft.book.page.pages.Biomass;
import mods.dnd91.minecraft.hivecraft.book.page.pages.Collector;
import mods.dnd91.minecraft.hivecraft.book.page.pages.Crowns;
import mods.dnd91.minecraft.hivecraft.book.page.pages.Drones;
import mods.dnd91.minecraft.hivecraft.book.page.pages.FirstEgg;
import mods.dnd91.minecraft.hivecraft.book.page.pages.Hatchery;
import mods.dnd91.minecraft.hivecraft.book.page.pages.Larva;
import mods.dnd91.minecraft.hivecraft.book.page.pages.Nests;
import mods.dnd91.minecraft.hivecraft.book.page.pages.OldTome;
import mods.dnd91.minecraft.hivecraft.book.page.pages.OrganicCompound;
import mods.dnd91.minecraft.hivecraft.book.page.pages.Queens;
import mods.dnd91.minecraft.hivecraft.book.page.pages.Spawnpool;

import cpw.mods.fml.common.registry.LanguageRegistry;


public class PageAppedix {
	public static List<Page> pageList = new ArrayList<Page>();
	
	public static void init() {
		pageList.add(0, new OldTome());
		pageList.add(1, new Collector());
		pageList.add(2, new OrganicCompound());
		pageList.add(3, new Biomass());
		pageList.add(4, new FirstEgg());
		pageList.add(5, new Hatchery());
		pageList.add(6, new Larva());
		pageList.add(7, new Spawnpool());
		pageList.add(8, new Crowns());
		pageList.add(9, new Queens());
		pageList.add(10, new Nests());
		pageList.add(11, new Drones());
	}
	
}
