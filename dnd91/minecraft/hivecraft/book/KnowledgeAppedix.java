package mods.dnd91.minecraft.hivecraft.book;

import java.util.ArrayList;
import java.util.List;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.PacketHandler;
import mods.dnd91.minecraft.hivecraft.book.page.PageAppedix;
import mods.dnd91.minecraft.hivecraft.book.page.pages.OldTome;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.LanguageRegistry;



public class KnowledgeAppedix {
	public static int minDisplayColumn;
	public static int minDisplayRow;
	public static int maxDisplayRow;
	public static int maxDisplayColumn;
	
	public static List<Knowledge> knowledgeList = new ArrayList<Knowledge>();
	
	/** KNOWLEDGES **/
	public static Knowledge old_tome = (new Knowledge(0, "old_tome", 0,0, HiveCraft.itemHiveBook, (Knowledge)null, 0)).setIndependent().registerKnowledge();
	
	public static Knowledge collector = (new Knowledge(1, "collector", 1,0, HiveCraft.itemCollector, old_tome, 1)).registerKnowledge();
	public static Knowledge firstegg = (new Knowledge(2, "baby_steps", 2, 0, Item.egg, (Knowledge)old_tome,4)).registerKnowledge();
	public static Knowledge hatchery = (new Knowledge(3, "hatchery", 3, 0, HiveCraft.blockHatcher, firstegg,5)).registerKnowledge();
	public static Knowledge larva = (new Knowledge(4, "larva", 4,0, HiveCraft.itemLarva, hatchery, 6)).registerKnowledge();
	public static Knowledge spawnpool = (new Knowledge(5, "spawnpool", 5,0, HiveCraft.blockSpawnpool, larva, 7)).registerKnowledge();
	
	public static Knowledge sticky_crown = (new Knowledge(6, "sticky_crown", 7,0, new ItemStack(HiveCraft.itemMutation, 1, 0), spawnpool, 8)).registerKnowledge();
	public static Knowledge first_queen = (new Knowledge(7, "first_queen", 8,0, Item.shovelIron, sticky_crown, 9)).registerKnowledge();
	public static Knowledge first_nest = (new Knowledge(8, "first_nest", 9,0, HiveCraft.blockCreep, first_queen, 10)).registerKnowledge();
	
	public static Knowledge organic_compound = (new Knowledge(9, "organic_compound", -1,0, HiveCraft.blockOrganicCompound, old_tome,2)).registerKnowledge();
	public static Knowledge biomass = (new Knowledge(10, "biomass", -2, 0, HiveCraft.itemBiomass, organic_compound,3)).registerKnowledge();
	
	public static Knowledge first_drone = (new Knowledge(11, "first_drone", 7,-1, new ItemStack(HiveCraft.itemMutation, 1, 2), spawnpool, 11)).registerKnowledge();
	
	public static void init() {
		LanguageRegistry.instance().addStringLocalization("knowledge.get", "Oh, did you get something new?");
		LanguageRegistry.instance().addStringLocalization("knowledge.taken", "Collected");
		
		LanguageRegistry.instance().addStringLocalization("knowledge.old_tome.title", "Introduction");
		LanguageRegistry.instance().addStringLocalization("knowledge.old_tome.text", "As i sit here...");
		
		LanguageRegistry.instance().addStringLocalization("knowledge.organic_compound.title", "Chapter 0: Organic Fluff");
		LanguageRegistry.instance().addStringLocalization("knowledge.organic_compound.text", "Organic stuff around dirt.");
		
		LanguageRegistry.instance().addStringLocalization("knowledge.biomass.title", "Chapter 1: Biomass");
		LanguageRegistry.instance().addStringLocalization("knowledge.biomass.text", "What can we do with this goo ball?");
		
		LanguageRegistry.instance().addStringLocalization("knowledge.collector.title", "Chapter 2: The Collector");
		LanguageRegistry.instance().addStringLocalization("knowledge.collector.text", "Important...");
		
		LanguageRegistry.instance().addStringLocalization("knowledge.baby_steps.title", "Chapter 3: Baby Steps");
		LanguageRegistry.instance().addStringLocalization("knowledge.baby_steps.text", "Oh the first steps are always the hardest.");
		
		LanguageRegistry.instance().addStringLocalization("knowledge.hatchery.title", "Chapter 4: Hatchery");
		LanguageRegistry.instance().addStringLocalization("knowledge.hatchery.text", "The Hatchery is a nice thing.");
		
		LanguageRegistry.instance().addStringLocalization("knowledge.larva.title", "Chapter 5: Larvas");
		LanguageRegistry.instance().addStringLocalization("knowledge.larva.text", "What can we do with this goo ball?");
		
		LanguageRegistry.instance().addStringLocalization("knowledge.spawnpool.title", "Chapter 6: Spawnpool");
		LanguageRegistry.instance().addStringLocalization("knowledge.spawnpool.text", "Even in my deepest dreams i can hear there chuckeling...");
		
		LanguageRegistry.instance().addStringLocalization("knowledge.sticky_crown.title", "Chapter 7: Crowns");
		LanguageRegistry.instance().addStringLocalization("knowledge.sticky_crown.text", "Queens head gear...");
		
		LanguageRegistry.instance().addStringLocalization("knowledge.first_queen.title", "Chapter 8: Queens");
		LanguageRegistry.instance().addStringLocalization("knowledge.first_queen.text", "Queens");
		
		LanguageRegistry.instance().addStringLocalization("knowledge.first_nest.title", "Chapter 9: Nests");
		LanguageRegistry.instance().addStringLocalization("knowledge.first_nest.text", "Home for the queen...");
		
		LanguageRegistry.instance().addStringLocalization("knowledge.first_drone.title", "Chapter 10: Drones");
		LanguageRegistry.instance().addStringLocalization("knowledge.first_drone.text", "Stinky");
		
		PageAppedix.init();
	}
	
	static
    {
        System.out.println(knowledgeList.size() + " knowledges");
    }
	
	public static NBTTagCompound makeHiveBook(){
		NBTTagCompound compound = new NBTTagCompound();
		return compound;
	}
	
	private static void unlockKnowledge(NBTTagCompound compound, Knowledge know){
		compound.setBoolean(know.getName(), true);
	}
	
	public static void unlockKnowledge(EntityPlayer player, Knowledge know){
		NBTTagCompound compound = player.getEntityData();
		if(!compound.hasKey(player.username+".HiveBook"))
			return;
		
		NBTTagCompound hivebook = compound.getCompoundTag(player.username+".HiveBook");
		
		if(!hivebook.hasKey(know.getName()) || !hivebook.getBoolean(know.getName())){
			unlockKnowledge(hivebook, know);
			if(player.worldObj.isRemote)
				HiveBookEventHandler.guiKnow.queueTakenAchievement(know);
			PacketHandler.sendUpdate((Player)player);
		}
	}
	
	public static boolean hasKnowledgeUnlocked(EntityPlayer player, Knowledge know){
		NBTTagCompound compound = player.getEntityData();
		if(!compound.hasKey(player.username+".HiveBook"))
			return false;
		
		NBTTagCompound hivebook = compound.getCompoundTag(player.username+".HiveBook");
		return hasKnowledgeUnlocked(hivebook, know);
	}
	
	public static boolean hasKnowledgeUnlocked(NBTTagCompound compound, Knowledge know){
		return compound.hasKey(know.getName());
	}
	
	public static boolean canUnlockKnowledge(EntityPlayer player, Knowledge know){
		NBTTagCompound compound = player.getEntityData();
		if(!compound.hasKey(player.username+".HiveBook"))
			return false;
		
		NBTTagCompound hivebook = compound.getCompoundTag(player.username+".HiveBook");
		return canUnlockKnowledge(hivebook, know);
	}
	
	public static boolean canUnlockKnowledge(NBTTagCompound compound, Knowledge know){
		return  know.parentKnowledge == null || hasKnowledgeUnlocked(compound, know.parentKnowledge);
	}
	
	
}
