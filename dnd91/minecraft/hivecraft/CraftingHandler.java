package mods.dnd91.minecraft.hivecraft;

import mods.dnd91.minecraft.hivecraft.book.KnowledgeAppedix;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public class CraftingHandler implements ICraftingHandler{

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item,
			IInventory craftMatrix) {
		int Id = item.getItem().itemID;
		int dmg = item.getItemDamage();
		
		if(Id == HiveCraft.blockHatcher.blockID){
			KnowledgeAppedix.unlockKnowledge(player, KnowledgeAppedix.hatchery);
		}else if(Id == HiveCraft.blockOrganicCompound.blockID){
			KnowledgeAppedix.unlockKnowledge(player, KnowledgeAppedix.organic_compound);
		}else if(Id == HiveCraft.itemCollector.itemID){
			KnowledgeAppedix.unlockKnowledge(player, KnowledgeAppedix.collector);
		}else if(Id == HiveCraft.blockSpawnpool.blockID){
			KnowledgeAppedix.unlockKnowledge(player, KnowledgeAppedix.spawnpool);
		}else if(Id == HiveCraft.itemMutation.itemID && dmg == 0){
			KnowledgeAppedix.unlockKnowledge(player, KnowledgeAppedix.sticky_crown);
		}
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
		int Id = item.getItem().itemID;
		if(Id == HiveCraft.itemBiomass.itemID){
			KnowledgeAppedix.unlockKnowledge(player, KnowledgeAppedix.biomass);
		}
	}
	
}
