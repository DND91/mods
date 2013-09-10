package mods.dnd91.minecraft.hivecraft.genetics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.genetics.family.IFamily;
import mods.dnd91.minecraft.hivecraft.genetics.family.MagicWorm;
import mods.dnd91.minecraft.hivecraft.hatchling.drone.EntityDrone;
import mods.dnd91.minecraft.hivecraft.hatchling.queen.EntityLadybugQueen;
import mods.dnd91.minecraft.hivecraft.larva.ISpawnpoolRecipe;
import mods.dnd91.minecraft.hivecraft.larva.SpawnpoolBlockRecipe;
import mods.dnd91.minecraft.hivecraft.larva.SpawnpoolEntityRecipe;
import mods.dnd91.minecraft.hivecraft.larva.SpawnpoolRecipes;
import mods.dnd91.minecraft.hivecraft.structure.buildplan.BlockPlan;



public class FamilyAppedix {
	/* REGISTERD HEAT AND MOIST LEVELS */
	public static int getTemparature(World world, int x, int y, int z, int range){
		if(range < 1)
			return 0;
		
		//Closer to bottom or top = colder. Middle value = biome temp
		int R = (int) Math.ceil(range / 2.0);
		int temparature = (int) (world.getBiomeGenForCoords(x, z).temperature*100.0F);
		//temparature += Math.cos((y*Math.PI)/256)*100;
		
		for(int x1 = x - R; x1 <= (x+R); x1++)
		for(int y1 = y - R; y1 <= (y+R); y1++)
		for(int z1 = z - R; z1 <= (z+R); z1++)
		temparature += FamilyAppedix.getHeat(world.getBlockId(x1, y1, z1));
		
		return temparature;
	}
	
	public static int getHumidity(World world, int x, int y, int z, int range){
		if(range < 1)
			return 0;
		
		int R = (int) Math.ceil(range / 2.0);
		int moist = (int) (world.getBiomeGenForCoords(x, z).rainfall*100.0F);
		
		
		for(int x1 = x - R; x1 <= (x+R); x1++)
		for(int y1 = y - R; y1 <= (y+R); y1++)
		for(int z1 = z - R; z1 <= (z+R); z1++)
			moist += FamilyAppedix.getMoist(world.getBlockId(x1, y1, z1));
		
		return moist;
	}
	
	static private HashMap<Integer, Integer> moistLevels = new HashMap<Integer, Integer>();
	static public void registerMoist(Integer blockID, Integer moistLevel){
		if(moistLevels.get(blockID) != null)
			throw new IllegalArgumentException("RML.Texture " + Block.blocksList[blockID] + " already has a value");
		moistLevels.put(blockID, moistLevel);
	}
	
	static public int getMoist(Integer blockID){
		if(!moistLevels.containsKey(blockID))
			return 0;
		return moistLevels.get(blockID);
	}
	
	static private HashMap<Integer, Integer> heatLevels = new HashMap<Integer, Integer>();
	static public void registerHeat(Integer blockID, Integer heatLevel){
		if(heatLevels.get(blockID) != null)
			throw new IllegalArgumentException("RHL.Texture " + Block.blocksList[blockID] + " already has a value");
		heatLevels.put(blockID, heatLevel);
	}
	
	static public int getHeat(Integer blockID){
		if(!heatLevels.containsKey(blockID))
			return 0;
		return heatLevels.get(blockID);
	}
	
	/* REGISTER TEXTURES AND ICONS */
	
	static public void registerIcons(IconRegister ires){
		for(Map.Entry path : texturePathsForHatchlings.entrySet())
			iconsForHatchlings.put((Integer) path.getKey(), ires.registerIcon((String) path.getValue()));
	}
	
	/* REGISTERED ITEM TEXTURES FOR HATCHLINGS */
	//"dnd91/minecraft/hivecraft:QueenMagicWorm"
	static private HashMap<Integer, String> texturePathsForHatchlings = new HashMap<Integer, String>();
	static private HashMap<Integer, Icon> iconsForHatchlings = new HashMap<Integer, Icon>();
	
	/**
	 * 
	 * @param hatchlingID - registerd in the system.
	 * @param path for example "dnd91/minecraft/hivecraft:QueenMagicWorm"
	 */
	static public void registerHatchlingItemTexture(int hatchlingID, String path){
		if(texturePathsForHatchlings.get(hatchlingID) != null)
			throw new IllegalArgumentException("RHI.Texture " + hatchlingID + " already has a texture. Tried to replace with " + path);
		texturePathsForHatchlings.put(hatchlingID, path);
	}
	
	static public Icon getIconHatchling(int hatchlingID){
		if(iconsForHatchlings.get(hatchlingID) == null)
			throw new IllegalArgumentException("GHI.Icon " + hatchlingID + " is not.");
		return iconsForHatchlings.get(hatchlingID);
	}
	
	static public void registerRecipe(ISpawnpoolRecipe rep){
		SpawnpoolRecipes.spawnpool().registerRecipe(rep);
	}
	
	
	/* REGISTERD HATCHLINGS */
	static private HashMap<Integer, Class> registerdHachtlings = new HashMap<Integer, Class>();
	
	static public Class getHatchling(Integer id){
		if(!registerdHachtlings.containsKey(id))
			throw new IllegalArgumentException("H.Slot " + id + " is empty.");
		return registerdHachtlings.get(id);
	}
	
	static public void registerHatchling(Class c, Integer id){
		if(registerdHachtlings.containsKey(id))
			throw new IllegalArgumentException("H.Slot " + id + " is already occupied by " + registerdHachtlings.get(id) + " when adding " + c);
		registerdHachtlings.put(id, c);
	}
	
	/* REGISTERD FAMILIES */
	static private HashMap<Integer, IFamily> registerdFamilies = new HashMap<Integer, IFamily>();
	
	static public int getFamilyAt(Integer pos){
		return ((Integer) registerdFamilies.keySet().toArray()[pos]);
	}
	
	static public IFamily getFamily(Integer id){
		if(!registerdFamilies.containsKey(id))
			throw new IllegalArgumentException("F.Slot " + id + " is empty.");
		return registerdFamilies.get(id);
		//return registerdFamilies.get(id);
	}
	
	static public void registerFamily(Integer id, IFamily family){
		if(registerdFamilies.containsKey(id))
			throw new IllegalArgumentException("F.Slot " + id + " is already occupied by " + registerdFamilies.get(id) + " when adding " + family.getName());
		registerdFamilies.put(id, family);
	}
	
	static public int getFamilyLength(){ return registerdFamilies.size(); }
	
	/** REGISTER BUILDINGS *
	 * 	For buildings with itemHatchling we will only care for id of block and meta.
	 * */
	static private HashMap<Integer, BlockPlan> registerdBuildings = new HashMap<Integer, BlockPlan>();
	
	static public BlockPlan getBuilding(Integer id){
		if(!registerdBuildings.containsKey(id))
			throw new IllegalArgumentException("B.Slot " + id + " is empty.");
		return registerdBuildings.get(id);
	}
	
	static public void registerBuilding(Integer id, BlockPlan buildPlan){
		if(registerdBuildings.containsKey(id))
			throw new IllegalArgumentException("B.Slot " + id + " is already occupied by " + registerdBuildings.get(id) + " when adding " + buildPlan);
		registerdBuildings.put(id, buildPlan);
	}
	
	/* Register Family */
	public static final int magicWorm = 1;
	
	/* Register Hatchlings and stuff */
	private static final int debug = 0;
	public static final int ladybugQueen = 1;
	public static final int fastRunnerDrone = 2;
	
	/* Register HatchBlocks */
	public static final int debugBlock = 1000;
	
	public static void init() {
		registerHatchlingItemTexture(debug, "dnd91/minecraft/hivecraft:Debug");
		
		registerFamily(magicWorm, new MagicWorm());
		
		registerBuilding(debugBlock, new BlockPlan(0, Block.blockGold.blockID, 0));
		registerHatchlingItemTexture(debugBlock, "dnd91/minecraft/hivecraft:BuilderMagicWorm");
		
		registerHatchling(EntityLadybugQueen.class, ladybugQueen);
		registerHatchlingItemTexture(ladybugQueen, "dnd91/minecraft/hivecraft:QueenMagicWorm");
		
		registerHatchling(EntityDrone.class, fastRunnerDrone);
		registerHatchlingItemTexture(fastRunnerDrone, "dnd91/minecraft/hivecraft:Larva_2_1");
		
		registerRecipe(new SpawnpoolEntityRecipe(new ItemStack(HiveCraft.itemMutation,1,0), null, null, null, "queen", "any"));
		registerRecipe(new SpawnpoolEntityRecipe(new ItemStack(HiveCraft.itemMutation,1,2), new ItemStack(Block.chest,1,0), null, null, "drone", "carrier"));
		registerRecipe(new SpawnpoolBlockRecipe(new ItemStack(HiveCraft.itemMutation, 1, 1), null, null, null, "test"));
		
		/* REGISTER HEAT & HUMITIDY */
		registerHeat(Block.ice.blockID, -10);
		registerHeat(Block.blockSnow.blockID, -5);
		registerHeat(Block.leaves.blockID, 1);
		registerHeat(Block.blockRedstone.blockID, 3);
		registerHeat(Block.furnaceBurning.blockID, 5);
		registerHeat(Block.glowStone.blockID, 6);
		registerHeat(Block.lavaStill.blockID, 10);
		
		registerMoist(Block.lavaStill.blockID, -10);
		registerMoist(Block.cobblestoneMossy.blockID, 1);
		registerMoist(Block.leaves.blockID, 2);
		registerMoist(Block.waterStill.blockID, 5);
		
	}
}
