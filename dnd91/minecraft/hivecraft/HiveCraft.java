package mods.dnd91.minecraft.hivecraft;

import mods.dnd91.minecraft.hivecraft.backlog.AscenderRecipes;
import mods.dnd91.minecraft.hivecraft.backlog.BlockAscender;
import mods.dnd91.minecraft.hivecraft.backlog.TileEntityAscender;
import mods.dnd91.minecraft.hivecraft.book.HiveBookEventHandler;
import mods.dnd91.minecraft.hivecraft.book.ItemHiveBook;
import mods.dnd91.minecraft.hivecraft.book.KnowledgeAppedix;
import mods.dnd91.minecraft.hivecraft.client.gui.GuiHandler;
import mods.dnd91.minecraft.hivecraft.eggs.BlockHatcher;
import mods.dnd91.minecraft.hivecraft.eggs.ItemEggsack;
import mods.dnd91.minecraft.hivecraft.eggs.TileEntityHatcher;
import mods.dnd91.minecraft.hivecraft.generation.HiveWorldGenerator;
import mods.dnd91.minecraft.hivecraft.genetics.FamilyAppedix;
import mods.dnd91.minecraft.hivecraft.genetics.Genpool;
import mods.dnd91.minecraft.hivecraft.hatchling.ItemBuilder;
import mods.dnd91.minecraft.hivecraft.hatchling.ItemDrone;
import mods.dnd91.minecraft.hivecraft.hatchling.ItemHatchling;
import mods.dnd91.minecraft.hivecraft.hatchling.builder.BlockBuilder;
import mods.dnd91.minecraft.hivecraft.hatchling.builder.EntityBuilder;
import mods.dnd91.minecraft.hivecraft.hatchling.builder.TileEntityBuilder;
import mods.dnd91.minecraft.hivecraft.hatchling.drone.EntityDrone;
import mods.dnd91.minecraft.hivecraft.hatchling.drone.program.ItemDumpTo;
import mods.dnd91.minecraft.hivecraft.hatchling.drone.program.ItemPickUp;
import mods.dnd91.minecraft.hivecraft.hatchling.queen.EntityLadybugQueen;
import mods.dnd91.minecraft.hivecraft.hivenetwork.BlockNode;
import mods.dnd91.minecraft.hivecraft.hivenetwork.BlockPath;
import mods.dnd91.minecraft.hivecraft.hivenetwork.BlockSuperNode;
import mods.dnd91.minecraft.hivecraft.hivenetwork.TileEntityNode;
import mods.dnd91.minecraft.hivecraft.hivenetwork.TileEntityPath;
import mods.dnd91.minecraft.hivecraft.hivenetwork.TileEntitySuperNode;
import mods.dnd91.minecraft.hivecraft.larva.BlockSpawnpool;
import mods.dnd91.minecraft.hivecraft.larva.ItemLarva;
import mods.dnd91.minecraft.hivecraft.larva.TileEntitySpawnpool;
import mods.dnd91.minecraft.hivecraft.structure.bioAsembler.BlockBioAsembler;
import mods.dnd91.minecraft.hivecraft.structure.bioAsembler.TileEntityBioAsembler;
import mods.dnd91.minecraft.hivecraft.structure.bioGrinder.BlockGrinder;
import mods.dnd91.minecraft.hivecraft.structure.bioGrinder.TileEntityGrinder;
import mods.dnd91.minecraft.hivecraft.structure.bioVent.BlockVent;
import mods.dnd91.minecraft.hivecraft.structure.bioVent.TileEntityVent;
import mods.dnd91.minecraft.hivecraft.structure.bioWorks.BlockBioWorks;
import mods.dnd91.minecraft.hivecraft.structure.bioWorks.TileEntityBioWorks;
import mods.dnd91.minecraft.hivecraft.structure.block.BlockCocoon;
import mods.dnd91.minecraft.hivecraft.structure.block.BlockSoftEarth;
import mods.dnd91.minecraft.hivecraft.structure.block.TileEntityCocoon;
import mods.dnd91.minecraft.hivecraft.structure.buildplan.BlockDebugBluePlan;
import mods.dnd91.minecraft.hivecraft.structure.buildplan.TileEntityDebugBluePlan;
import mods.dnd91.minecraft.hivecraft.structure.queenNest.BlockQueenNest;
import mods.dnd91.minecraft.hivecraft.structure.queenNest.TileEntityQueenNest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFluid;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.Configuration;

@Mod(modid="HiveCraft", name="HiveCraft", version="0.0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, channels={"HiveBook", "HiveTories", "HiveOG"}, packetHandler = PacketHandler.class)
public class HiveCraft {
	
	public static World currentWorld = null;
	public static CreativeTabs tabHiveCraft;
	public static final String[] familyNames = new String[] {"Magic Worm","Sprinkel Fire"};
	public static final float[][] bodyColorTable = new float[][] {{1.0F, 1.0F, 1.0F}, {0.85F, 0.5F, 0.2F}, {0.7F, 0.3F, 0.85F}, {0.4F, 0.6F, 0.85F}, {0.9F, 0.9F, 0.2F}, {0.5F, 0.8F, 0.1F}, {0.95F, 0.5F, 0.65F}, {0.3F, 0.3F, 0.3F}, {0.6F, 0.6F, 0.6F}, {0.3F, 0.5F, 0.6F}, {0.5F, 0.25F, 0.7F}, {0.2F, 0.3F, 0.7F}, {0.4F, 0.3F, 0.2F}, {0.4F, 0.5F, 0.2F}, {0.6F, 0.2F, 0.2F}, {0.1F, 0.1F, 0.1F}};
	public static CraftingHandler craftHandler = new CraftingHandler();
   
   public static int modelAscenderID;
   
   public static int organicCompoundID;
   public static int ascenderID;
   public static int hatcherID;
   public static int spawnpoolID;
   public static int pathID;
   public static int nodeID;
   public static int superNodeID;
   public static int softEarthID;
   public static int queenNestID;
   public static int debugPlanID ;
   public static int creepID;
   public static int bioAsemblerID;
   public static int bioWorksID;
   public static int bioAcidStillID;
   public static int bioAcidFlowID;
   public static int bioNestID;
   public static int blockBuilderID;
   public static int bioGrinderID;
   public static int bioVentID;
   
   public static int biomassID;
   public static int eggsackID;
   public static int larvaID;
   public static int bioHelmID;
   public static int bioPlateID;
   public static int bioLegsID;
   public static int bioBootsID;
   public static int builderID;
   public static int mutationID;
   public static int hiveBookID;
   public static int collectorID;
   public static int odoremGlandemID;
   public static int itemPickUpID;
   public static int itemDumpToID;
   public static int droneID;
   public static int hatchlingID;
   
   public static int startEntityId = 300;
   
   
   public static Block blockOrganicCompound;
   public static Block blockAscender;
   public static Block blockHatcher;
   public static Block blockSpawnpool;
   public static Block blockPath;
   public static Block blockNode;
   public static Block blockSuperNode;
   public static Block blockSoftEarth;
   public static Block blockQueenNest;
   public static Block blockDebugPlan;
   public static Block blockCreep;
   public static Block blockBioAsembler;
   public static Block blockBioWorks;
   public static BlockFluid blockBioAcidFlow;
   public static Block blockBioAcidStill;
   public static Block blockBioNest;
   public static Block blockBuilder;
   public static Block blockBioGrinder;
   public static Block blockBioVent;

   public static Item itemBiomass;
   public static Item itemEggsack;
   public static Item itemLarva;
   public static Item itemBioHelm;
   public static Item itemBioPlate;
   public static Item itemBioLegs;
   public static Item itemBioBoots;
   public static Item itemBuilder;
   public static Item itemMutation;
   public static Item itemHiveBook;
   public static Item itemCollector;
   public static Item itemOdoremGlandem;
   public static Item itemPickUp;
   public static Item itemDumpTo;
   public static Item itemDrone;
   public static Item itemHatchling;
   
   public static DamageSource liquefied = new DamageSourceLiquefied("liquefied");
   
   public static int bioArmorRenderID = 0;
   
   public static Material materialBiomass = new MaterialBiomass(MapColor.foliageColor);
   public static EnumArmorMaterial bioArmorMaterial = EnumHelper.addArmorMaterial("Bio", 40, new int[]{4,6,3,3}, 10);
   // The instance of your mod that Forge uses.
    @Instance("HiveCraft")
    public static HiveCraft instance;
    
    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide="mods.dnd91.minecraft.hivecraft.client.ClientProxy", serverSide="mods.dnd91.minecraft.hivecraft.CommonProxy")
    public static CommonProxy proxy;
	
	public void debugMode(){
		blockDebugPlan = new BlockDebugBluePlan(this.debugPlanID);
        GameRegistry.registerBlock(blockDebugPlan, "blockDebugPlan");
        LanguageRegistry.addName(blockDebugPlan, "Debug Plan");
        
	}
	
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
            // Load Configure files
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    	config.load();
    	
    	organicCompoundID = config.getBlock("organicCompoundID", 260).getInt();
    	ascenderID = config.getBlock("ascenderID", 261).getInt();
    	hatcherID = config.getBlock("hatcherID", 262).getInt();
    	spawnpoolID = config.getBlock("spawnpoolID", 263).getInt();
    	pathID = config.getBlock("pathID", 264).getInt();
    	nodeID = config.getBlock("nodeID", 265).getInt();
    	superNodeID = config.getBlock("superNodeID", 266).getInt();
    	softEarthID = config.getBlock("softEarthID", 267).getInt();
    	queenNestID = config.getBlock("queenNestID", 268).getInt();
    	debugPlanID = config.getBlock("debugPlanID", 269).getInt();
    	creepID = config.getBlock("creepID", 270).getInt();
    	bioAsemblerID = config.getBlock("bioAsemblerID", 271).getInt();
    	bioWorksID = config.getBlock("bioWorksID", 272).getInt();
    	bioAcidStillID = config.getBlock("bioAcidStillID", 273).getInt();
    	bioAcidFlowID = config.getBlock("bioAcidFlowID", 274).getInt();
    	bioNestID = config.getBlock("bioNestID", 275).getInt();
    	blockBuilderID = config.getBlock("blockBuilderID", 276).getInt();
    	bioGrinderID = config.getBlock("bioGrinderID", 277).getInt();
    	bioVentID = config.getBlock("blockVentID", 278).getInt();
    	   
    	biomassID = config.getItem("biomassID", 4200).getInt();
    	eggsackID = config.getItem("eggsackID", 4201).getInt();
    	larvaID = config.getItem("larvaID", 4202).getInt();
    	bioHelmID = config.getItem("bioHelmID", 4204).getInt();
    	bioPlateID = config.getItem("bioPlateID", 4205).getInt();
    	bioLegsID = config.getItem("bioLegsID", 4206).getInt();
    	bioBootsID = config.getItem("bioBootsID", 4207).getInt();
    	builderID = config.getItem("builderID", 4208).getInt();
    	mutationID = config.getItem("mutationID", 4209).getInt();
    	hiveBookID = config.getItem("hiveBookID", 4210).getInt();
    	collectorID = config.getItem("collectorID", 4211).getInt();
    	odoremGlandemID = config.getItem("odoremGlandemID", 4212).getInt();
    	itemPickUpID = config.getItem("itemPickUpID", 4213).getInt();
    	itemDumpToID = config.getItem("itemDumpToID", 4214).getInt();
    	droneID = config.getItem("droneID", 4215).getInt();
    	hatchlingID = config.getItem("hatchlingID", 4216).getInt();
    	
    	config.save();
    }
    
    @Init
    public void load(FMLInitializationEvent event) {
    	
    	NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
    	NetworkRegistry.instance().registerConnectionHandler(new ConnectionHandler());
    	GameRegistry.registerCraftingHandler(craftHandler);
    	GameRegistry.registerWorldGenerator(new HiveWorldGenerator());
    	MinecraftForge.EVENT_BUS.register(new HiveBookEventHandler());
    	
    	itemBiomass = new ItemBiomass(biomassID);
        GameRegistry.registerItem(itemBiomass, "biomass");
        LanguageRegistry.addName(itemBiomass, "Biomass");
    	
    	tabHiveCraft = new HiveCraftTab("HiveCraft");
    	
    	itemBiomass.setCreativeTab(tabHiveCraft);
    	
    	GameRegistry.registerTileEntity(TileEntityAscender.class, "orgasc");
        GameRegistry.registerTileEntity(TileEntityHatcher.class, "hatcher_hc");
        GameRegistry.registerTileEntity(TileEntitySpawnpool.class, "spawnpool_hc");
        GameRegistry.registerTileEntity(TileEntityNode.class, "basic_node");
        GameRegistry.registerTileEntity(TileEntitySuperNode.class, "super_node");
        GameRegistry.registerTileEntity(TileEntityPath.class, "path_node");
        GameRegistry.registerTileEntity(TileEntityQueenNest.class, "queen_nest");
        GameRegistry.registerTileEntity(TileEntityDebugBluePlan.class, "debug_plan");
        GameRegistry.registerTileEntity(TileEntityCocoon.class, "hive_cocoon");
        GameRegistry.registerTileEntity(TileEntityBioAsembler.class, "bio_asembler");
        GameRegistry.registerTileEntity(TileEntityBioWorks.class, "bio_works");
        GameRegistry.registerTileEntity(TileEntityBuilder.class, "hivecraft_builder");
        GameRegistry.registerTileEntity(TileEntityGrinder.class, "hivecraft_bio_grinder");
        GameRegistry.registerTileEntity(TileEntityVent.class, "hivecraft_bio_vent");
    	
    	blockBioAcidFlow = new BlockFlowingBioAcid(bioAcidFlowID);
        GameRegistry.registerBlock(blockBioAcidFlow, "blockBioAcidFlow");
        blockBioAcidFlow.setUnlocalizedName("blockBioAcidFlow");
        LanguageRegistry.addName(blockBioAcidFlow, "Bio Acid Flow");
        
        
        blockBioAcidStill = new BlockStationaryBioAcid(bioAcidStillID);
        GameRegistry.registerBlock(blockBioAcidStill, "blockBioAcidStill");
        blockBioAcidStill.setUnlocalizedName("blockBioAcidStill");
        LanguageRegistry.addName(blockBioAcidStill, "Bio Acid Still");
        
    	
          blockOrganicCompound = new BlockOrganicCompound(organicCompoundID, materialBiomass);
          GameRegistry.registerBlock(blockOrganicCompound, "blockOrganicCompound");
          LanguageRegistry.addName(blockOrganicCompound, "Organic Compound");
          MinecraftForge.setBlockHarvestLevel(blockOrganicCompound, "shovel", 1);
          
          blockBuilder = new BlockBuilder(blockBuilderID, materialBiomass);
          GameRegistry.registerBlock(blockBuilder, "blockBuilder");
          LanguageRegistry.addName(blockBuilder, "Builder");
          
          blockBioNest = new BlockBioNest(bioNestID);
          GameRegistry.registerBlock(blockBioNest, "blockBioNest");
          LanguageRegistry.addName(blockBioNest, "Bio Nest");
          
          blockAscender = new BlockAscender(ascenderID, materialBiomass);
          GameRegistry.registerBlock(blockAscender, "blockAscender");
          LanguageRegistry.addName(blockAscender, "Organic Ascender");
          MinecraftForge.setBlockHarvestLevel(blockAscender, "shovel", 1);
          
          blockHatcher = new BlockHatcher(hatcherID);
          GameRegistry.registerBlock(blockHatcher, "blockHatcher");
          LanguageRegistry.addName(blockHatcher, "Hatcher");
          MinecraftForge.setBlockHarvestLevel(blockHatcher, "pickaxe", 1);
          
          blockSpawnpool = new BlockSpawnpool(spawnpoolID, Material.iron);
          GameRegistry.registerBlock(blockSpawnpool, "blockSpawnpool");
          LanguageRegistry.addName(blockSpawnpool, "Spawnpool");
          MinecraftForge.setBlockHarvestLevel(blockSpawnpool, "pickaxe", 1);
          
          blockPath = new BlockPath(pathID);
          GameRegistry.registerBlock(blockPath, "blockPath");
          LanguageRegistry.addName(blockPath, "Path");
          MinecraftForge.setBlockHarvestLevel(blockPath, "shovel", 1);
          
          blockNode = new BlockNode(nodeID);
          GameRegistry.registerBlock(blockNode, "blockNode");
          LanguageRegistry.addName(blockNode, "Node");
          MinecraftForge.setBlockHarvestLevel(blockNode, "shovel", 1);
          
          blockSuperNode = new BlockSuperNode(superNodeID);
          GameRegistry.registerBlock(blockSuperNode, "blockSuperNode");
          LanguageRegistry.addName(blockSuperNode, "Master Node");
          MinecraftForge.setBlockHarvestLevel(blockSuperNode, "shovel", 1);
          
          blockSoftEarth = new BlockSoftEarth(softEarthID);
          GameRegistry.registerBlock(blockSoftEarth, "blockSoftEarth");
          LanguageRegistry.addName(blockSoftEarth, "Soft Earth");
          MinecraftForge.setBlockHarvestLevel(blockSoftEarth, "shovel", 1);
          
          blockQueenNest = new BlockQueenNest(queenNestID);
          GameRegistry.registerBlock(blockQueenNest, "blockQueenNest");
          LanguageRegistry.addName(blockQueenNest, "Queen Nest");
          MinecraftForge.setBlockHarvestLevel(blockQueenNest, "shovel", 1);
          
          blockCreep = new BlockCocoon(creepID);
          GameRegistry.registerBlock(blockCreep, "blockCreep");
          LanguageRegistry.addName(blockCreep, "");
          
          blockBioAsembler = new BlockBioAsembler(bioAsemblerID);
          GameRegistry.registerBlock(blockBioAsembler, "blockBioAsembler");
          LanguageRegistry.addName(blockBioAsembler, "blockBioAsembler");
          
          blockBioWorks = new BlockBioWorks(bioWorksID);
          GameRegistry.registerBlock(blockBioWorks, "blockBioWorks");
          LanguageRegistry.addName(blockBioWorks, "BioWorksCore");
          
          blockBioGrinder = new BlockGrinder(bioGrinderID);
          GameRegistry.registerBlock(blockBioGrinder, "blockBioGrinder");
          LanguageRegistry.addName(blockBioGrinder, "Grinder");
          
          blockBioVent = new BlockVent(bioVentID);
          GameRegistry.registerBlock(blockBioVent, "blockBioVent");
          LanguageRegistry.addName(blockBioVent, "Vent");

          
          itemMutation = new ItemMutation(mutationID);
          GameRegistry.registerItem(itemMutation, "itemMutation");
          LanguageRegistry.addName(itemMutation, "Mutation");
          
          itemCollector = new ItemCollector(collectorID);
          GameRegistry.registerItem(itemCollector, "itemCollector");
          LanguageRegistry.addName(itemCollector, "Basic Collection Tool");
          
          itemPickUp = new ItemPickUp(itemPickUpID);
          GameRegistry.registerItem(itemPickUp, "itemPickUp");
          LanguageRegistry.addName(itemPickUp, "Strange Smell(Pick up)");
          
          itemDumpTo = new ItemDumpTo(itemDumpToID);
          GameRegistry.registerItem(itemDumpTo, "itemDumpTo");
          LanguageRegistry.addName(itemDumpTo, "Strange Smell(Dump to)");
          
          itemOdoremGlandem = new ItemOdoremGlandem(odoremGlandemID);
          GameRegistry.registerItem(itemOdoremGlandem, "itemOdoremGlandem");
          LanguageRegistry.addName(itemOdoremGlandem, "Odorem Glandem");
          
          itemHiveBook = new ItemHiveBook(hiveBookID);
          GameRegistry.registerItem(itemHiveBook, "itemHiveBook");
          LanguageRegistry.addName(itemHiveBook, "Old Tome");
          
          itemHatchling = new ItemHatchling(hatchlingID);
          GameRegistry.registerItem(itemHatchling, "itemHatchling");
          LanguageRegistry.addName(itemHatchling, "itemHatchling");
          
          itemEggsack = new ItemEggsack(eggsackID, 0);
          GameRegistry.registerItem(itemEggsack, "eggsack");
          LanguageRegistry.addName(itemEggsack, "debug eggsack");
          LanguageRegistry.addName(new ItemStack(itemEggsack, 1,0), "Magic Worm Egg");
          
          
          itemLarva = new ItemLarva(larvaID, 0);
          GameRegistry.registerItem(itemLarva, "larva");
          LanguageRegistry.addName(itemLarva, "debug larva");
          
          itemBuilder = new ItemBuilder(builderID);
          GameRegistry.registerItem(itemBuilder, "builder");
          LanguageRegistry.addName(itemBuilder, "builder");
          
          itemDrone = new ItemDrone(droneID);
          GameRegistry.registerItem(itemDrone, "drone");
          LanguageRegistry.addName(itemDrone, "drone");
          
          OreDictionary.registerOre("dirt", Block.dirt);
          OreDictionary.registerOre("larva", new ItemStack(itemLarva, 1, OreDictionary.WILDCARD_VALUE));
          OreDictionary.registerOre("compostable", new ItemStack(Block.sapling, 1, OreDictionary.WILDCARD_VALUE));
          OreDictionary.registerOre("compostable", Item.appleRed);
          OreDictionary.registerOre("compostable", Item.beefCooked);
          OreDictionary.registerOre("compostable", Item.beefRaw);
          OreDictionary.registerOre("compostable", Item.porkCooked);
          OreDictionary.registerOre("compostable", Item.porkRaw);
          OreDictionary.registerOre("compostable", Item.melon);
          OreDictionary.registerOre("compostable", Item.chickenCooked);
          OreDictionary.registerOre("compostable", Item.chickenRaw);
          OreDictionary.registerOre("compostable", Item.wheat);
          OreDictionary.registerOre("compostable", Item.cookie);
          OreDictionary.registerOre("compostable", Item.carrot);
          OreDictionary.registerOre("compostable", Item.pumpkinPie);
          OreDictionary.registerOre("compostable", Item.potato);
          OreDictionary.registerOre("compostable", Item.bakedPotato);
          OreDictionary.registerOre("compostable", new ItemStack(Block.leaves, 1, OreDictionary.WILDCARD_VALUE));
          
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockOrganicCompound, 4), true, new Object[]{
                    "xxx","xyx","xxx", 
                    Character.valueOf('x'), "compostable",
                    Character.valueOf('y'), "dirt"}));
            
            GameRegistry.addRecipe(new ItemStack(blockSpawnpool), "x x", "x x", "yxy", //Spawnpool
                    'x', new ItemStack(Block.cobblestone), 'y', new ItemStack(itemBiomass));
            
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemMutation, 1, 0), true, new Object[]{
            	"sxs", "x x", "sts", //Sticky Queen
                'x', new ItemStack(Item.stick), 's', new ItemStack(Item.silk),
                't', "treeSapling"}));
            
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemMutation, 1, 1), true, new Object[]{
            	" x ", "xyx", " x ", //Craftmass
                'x', new ItemStack(itemBiomass), 'y', "plankWood"}));
            
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemOdoremGlandem, 1), true, new Object[]{
            	" x ", "xyx", " x ", //Odorem Glandem
                'x', new ItemStack(itemBiomass), 'y', "larva"}));
            
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemMutation, 1, 2), true, new Object[]{
            	"y", "x", //Drone Tools T1
                'x', new ItemStack(itemBiomass), 'y', new ItemStack(Item.stick)}));
            
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockHatcher, 1), true, new Object[]{
            	"xyx", "xxx", //Hatcher
                'x', "logWood", 'y', new ItemStack(Block.dirt)}));
            
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemCollector, 1), true, new Object[]{
            	"rw", "wy", //Collector
                'r', new ItemStack(Item.dyePowder,1,1), 'w', "plankWood",
                'y', new ItemStack(Item.dyePowder,1,11)}));

            
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemHiveBook, 1), true, new Object[]{
                "xx","xx", 
                Character.valueOf('x'), "compostable"}));

            
            GameRegistry.addSmelting(organicCompoundID, new ItemStack(itemBiomass), 0.2f);
            
            EntityRegistry.registerModEntity(EntityBuilder.class, "Builder", 2, this, 80, 3, true);
            LanguageRegistry.instance().addStringLocalization("entity.HiveCraft.Builder.name", "Builder");
            
            EntityRegistry.registerModEntity(EntityDrone.class, "Drone", 3, this, 80, 3, true);
            LanguageRegistry.instance().addStringLocalization("entity.HiveCraft.Drone.name", "Drone");
            
            EntityRegistry.registerModEntity(EntityLadybugQueen.class, "LadybugQueen", 4, this, 80, 3, true);
            LanguageRegistry.instance().addStringLocalization("entity.HiveCraft.LadybugQueen.name", "Ladybug Queen");
            
            registerEntityEgg(EntityDrone.class, 0xffff00, 0xff0000);
            
            proxy.registerRenderers();
           TickRegistry.registerTickHandler(new TickHandler(), Side.SERVER);
           LanguageRegistry.instance().addStringLocalization("itemGroup.HiveCraft", "en_US", "HiveCraftTab");
           
           /** ADD ARMOR HERE! **/
           itemBioHelm = new ItemBioArmor(this.bioHelmID, this.bioArmorMaterial, this.bioArmorRenderID, 0);
           GameRegistry.registerItem(itemBioHelm, "itemBioHelm");
           LanguageRegistry.addName(itemBioHelm, "Bio Helmet");
           
           itemBioPlate = new ItemBioArmor(this.bioPlateID, this.bioArmorMaterial, this.bioArmorRenderID, 1);
           GameRegistry.registerItem(itemBioPlate, "itemBioPlate");
           LanguageRegistry.addName(itemBioPlate, "Bio Plate");
           
           itemBioLegs = new ItemBioArmor(this.bioLegsID, this.bioArmorMaterial, this.bioArmorRenderID, 2);
           GameRegistry.registerItem(itemBioLegs, "itemBioLegs");
           LanguageRegistry.addName(itemBioLegs, "Bio Leggings");
           
           itemBioBoots = new ItemBioArmor(this.bioBootsID, this.bioArmorMaterial, this.bioArmorRenderID, 3);
           GameRegistry.registerItem(itemBioBoots, "itemBioBoots");
           LanguageRegistry.addName(itemBioBoots, "Bio Boots");
    
           debugMode();
           KnowledgeAppedix.init();
           FamilyAppedix.init();
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
            // Get along with other mods
    	
    }
    
    public static int getUniqueEntityId() 
    {
     do 
     {
      startEntityId++;
     } 
     while (EntityList.getStringFromID(startEntityId) != null);

      return startEntityId;
    }
    
    public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) 
    {
     int id = getUniqueEntityId();
     EntityList.IDtoClassMapping.put(id, entity);
     EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
    }
}