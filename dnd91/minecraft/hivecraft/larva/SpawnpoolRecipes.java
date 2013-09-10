package mods.dnd91.minecraft.hivecraft.larva;

import java.util.ArrayList;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.HiveCraftWorldData;
import mods.dnd91.minecraft.hivecraft.TickHandler;
import mods.dnd91.minecraft.hivecraft.backlog.AscenderRecipes;
import mods.dnd91.minecraft.hivecraft.genetics.Genetics;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SpawnpoolRecipes {
	private static SpawnpoolRecipes instance = new SpawnpoolRecipes();
	public static SpawnpoolRecipes spawnpool() {
		return instance;
	}
	
	private ArrayList<ISpawnpoolRecipe> recepiers = new ArrayList<ISpawnpoolRecipe>();
	
	public void registerRecipe(ISpawnpoolRecipe rep){
		recepiers.add(rep);
	}
	
	public ItemStack getHatchlingFromLarva(World world, ItemStack gold_nugget_stack,
				ItemStack larva_stack, ItemStack upgrade1_stack,
				ItemStack upgrade2_stack, ItemStack upgrade3_stack) {
		
		ItemStack[] slots = {gold_nugget_stack,null, larva_stack, upgrade1_stack, upgrade2_stack,upgrade3_stack};
		
		for(ISpawnpoolRecipe rep : recepiers){
			if(rep.matches(slots, world))
				return rep.getSpawnpoolResult(new Genetics((NBTTagCompound) larva_stack.getTagCompound().getCompoundTag("genetics")), slots);
		}
		
		return null;
		
		/*
			if(gold_nugget_stack.itemID == HiveCraft.itemMutation.itemID && gold_nugget_stack.getItemDamage() == 0){
				ItemStack stack = new ItemStack(HiveCraft.itemQueen,1, larva_stack.getItemDamage());
				stack.setTagCompound((NBTTagCompound)larva_stack.getTagCompound().copy());
				return stack;
			}
			
			if(gold_nugget_stack.itemID == HiveCraft.itemMutation.itemID){
				if(gold_nugget_stack.getItemDamage() == 1){ //Basic Craftmass
					if(upgrade1_stack != null)
					if(upgrade1_stack.itemID == Block.workbench.blockID){
						ItemStack stack = new ItemStack(HiveCraft.itemBuilder,1,larva_stack.getItemDamage());
						NBTTagCompound compound = (NBTTagCompound) larva_stack.getTagCompound().copy();
						
						compound.setInteger("buildType", 1);
						compound.setInteger("tooBecome", compound.getInteger("BioAsembler"));
						
						stack.setTagCompound(compound);
						return stack;
					}else if(upgrade1_stack.itemID == Block.furnaceIdle.blockID){
						ItemStack stack = new ItemStack(HiveCraft.itemBuilder,1,larva_stack.getItemDamage());
						NBTTagCompound compound = (NBTTagCompound) larva_stack.getTagCompound().copy();
						
						compound.setInteger("buildType", 2);
						compound.setInteger("tooBecome", compound.getInteger("BioAsembler"));
						
						stack.setTagCompound(compound);
						return stack;
					}
				}else if(gold_nugget_stack.getItemDamage() == 2){//Standard Drone Tools
					ItemStack stack = new ItemStack(HiveCraft.itemDrone,1,larva_stack.getItemDamage());
					NBTTagCompound compound = (NBTTagCompound) larva_stack.getTagCompound().copy();
					
					stack.setTagCompound(compound);
					return stack;
				}
			}
			
			
		
			return null;
			*/
	}

	public boolean canEvolve(World world, ItemStack gold_nugget_stack,
			ItemStack larva_stack, ItemStack upgrade1_stack,
			ItemStack upgrade2_stack, ItemStack upgrade3_stack) {
		
		ItemStack[] slots = {gold_nugget_stack,null, larva_stack, upgrade1_stack, upgrade2_stack,upgrade3_stack};
		
		for(ISpawnpoolRecipe rep : recepiers){
			if(rep.matches(slots, world))
				return true;
		}
		
		return false;
	}

}
