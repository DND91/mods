package mods.dnd91.minecraft.hivecraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class HiveCraftTab extends CreativeTabs {

	public HiveCraftTab(String par2Str) {
		super(CreativeTabs.getNextID(), par2Str);
		iconStack = new ItemStack(HiveCraft.itemBiomass);
	}
	
	public static ItemStack iconStack;
	@Override
	public ItemStack getIconItemStack(){
		return iconStack;
	}
	
	public String getTabLabel()
	{
	return "HiveCraft";
	}

}
