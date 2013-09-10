package mods.dnd91.minecraft.hivecraft.larva;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.genetics.FamilyAppedix;
import mods.dnd91.minecraft.hivecraft.genetics.Genetics;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SpawnpoolEntityRecipe implements ISpawnpoolRecipe{
	/*
	 * Gold Nugget Slot - Mutate too
	 * First, second, third mutator
	 * 
	 */
	
	ItemStack goldnugget = null;
	ItemStack first = null;
	ItemStack second = null;
	ItemStack third = null;
	String craft = null;
	String occupation = null;
	
	public SpawnpoolEntityRecipe(ItemStack gold, ItemStack f, ItemStack s, ItemStack t, String c, String o){
		goldnugget = gold;
		first = f;
		second = s;
		third = t;
		craft = c;
		occupation = o;
	}
	
	boolean match(ItemStack repi, ItemStack slot){
		if(repi == null && slot == null)
			return true;
		if(repi != null && slot == null)
			return false;
		if(repi == null && slot != null)
			return false;
		
		if(repi.itemID != slot.itemID)
			return false;
		
		if(!repi.getItem().isItemTool(repi) && repi.getItemDamage() != slot.getItemDamage())
			return false;
		
		return true;
	}
	
	public boolean matches(ItemStack[] stacks, World world){
		if(!match(goldnugget, stacks[0]))
			return false;
		
		if(!match(first, stacks[3]))
			return false;
		
		if(!match(second, stacks[4]))
			return false;
		
		if(!match(third, stacks[5]))
			return false;
		
		return true;
	}
	
	public ItemStack getSpawnpoolResult(Genetics genetics, ItemStack[] stacks){
		int id = genetics.getHatchling(craft, occupation);
		if(id == -1)
			return null;
		
		ItemStack item = new ItemStack(HiveCraft.itemHatchling,1,id);
		NBTTagCompound compound = new NBTTagCompound();
		NBTTagCompound gens = (NBTTagCompound) genetics.getTagCompound().copy();
		
		gens.setBoolean("isEntity", true);
		gens.setInteger("ID", id);
		gens.setString("craft", craft);
		gens.setString("occupation", occupation);
		
		compound.setCompoundTag("genetics", gens);
		item.setTagCompound(compound);
		item.setItemDamage(id);
		
		return item;
	}
}
