package mods.dnd91.minecraft.hivecraft;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IBioNest {
	public boolean isMature(ItemStack item, World world, int x, int y, int z);
	
	public ArrayList<ItemStack> onCollected(EntityPlayer player, ItemStack item, World world, int x, int y, int z, int fortune);
}
