package mods.dnd91.minecraft.hivecraft.backlog;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;


import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.HiveCraftWorldData;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.biome.BiomeGenBase;

public class AscenderRecipes {
	   private static final AscenderRecipes ascenderBase = new AscenderRecipes();
	   static Random random = new Random();
	    public static final AscenderRecipes ascending()
	    {
	        return ascenderBase;
	    }

	    private AscenderRecipes()
	    {
	       
	    }

		public boolean canAscend(ItemStack itemStack) {
			Item item = itemStack.getItem();

			return item instanceof ItemFood;
		}
		
		

	       
}
