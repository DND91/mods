package mods.dnd91.minecraft.hivecraft.larva;

import mods.dnd91.minecraft.hivecraft.genetics.Genetics;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ISpawnpoolRecipe {
	/*
	 * Gold Nugget Slot - Mutate too
	 * First, second, third mutator
	 */
	
	public boolean matches(ItemStack[] stacks, World world);
	
	public ItemStack getSpawnpoolResult(Genetics genetics, ItemStack[] stacks);
	
}
