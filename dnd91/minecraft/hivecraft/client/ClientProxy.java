package mods.dnd91.minecraft.hivecraft.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import mods.dnd91.minecraft.hivecraft.CommonProxy;
import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.ItemOdoremGlandem;
import mods.dnd91.minecraft.hivecraft.backlog.TileEntityAscender;
import mods.dnd91.minecraft.hivecraft.client.gui.GuiOverlay;
import mods.dnd91.minecraft.hivecraft.client.models.ModelBuilderMagic;
import mods.dnd91.minecraft.hivecraft.client.models.ModelDroneStandard;
import mods.dnd91.minecraft.hivecraft.client.models.ModelQueenMagic;
import mods.dnd91.minecraft.hivecraft.client.renders.RenderAscender;
import mods.dnd91.minecraft.hivecraft.client.renders.RenderBuilder;
import mods.dnd91.minecraft.hivecraft.client.renders.RenderDrone;
import mods.dnd91.minecraft.hivecraft.client.renders.RenderGrinder;
import mods.dnd91.minecraft.hivecraft.client.renders.RenderHatcher;
import mods.dnd91.minecraft.hivecraft.client.renders.RenderLadybugQueen;
import mods.dnd91.minecraft.hivecraft.client.renders.RenderNode;
import mods.dnd91.minecraft.hivecraft.client.renders.RenderSpawnpool;
import mods.dnd91.minecraft.hivecraft.client.renders.RenderSuperNode;
import mods.dnd91.minecraft.hivecraft.client.renders.RenderVent;
import mods.dnd91.minecraft.hivecraft.eggs.TileEntityHatcher;
import mods.dnd91.minecraft.hivecraft.hatchling.builder.EntityBuilder;
import mods.dnd91.minecraft.hivecraft.hatchling.drone.EntityDrone;
import mods.dnd91.minecraft.hivecraft.hatchling.queen.EntityLadybugQueen;
import mods.dnd91.minecraft.hivecraft.hivenetwork.TileEntityNode;
import mods.dnd91.minecraft.hivecraft.hivenetwork.TileEntitySuperNode;
import mods.dnd91.minecraft.hivecraft.larva.TileEntitySpawnpool;
import mods.dnd91.minecraft.hivecraft.structure.bioGrinder.TileEntityGrinder;
import mods.dnd91.minecraft.hivecraft.structure.bioVent.TileEntityVent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	
	 @Override
     public void registerRenderers() {
		 ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAscender.class, new RenderAscender());
		 ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHatcher.class, new RenderHatcher());
		 ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpawnpool.class, new RenderSpawnpool());
		 ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNode.class, new RenderNode());
		 ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySuperNode.class, new RenderSuperNode());
		 ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGrinder.class, new RenderGrinder());
		 ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVent.class, new RenderVent());
		 
		 RenderingRegistry.registerEntityRenderingHandler(EntityLadybugQueen.class, new RenderLadybugQueen(new ModelQueenMagic(), 0.3F));
		 RenderingRegistry.registerEntityRenderingHandler(EntityBuilder.class, new RenderBuilder(new ModelBuilderMagic(), 0.3F));
		 RenderingRegistry.registerEntityRenderingHandler(EntityDrone.class,  new RenderDrone(new ModelDroneStandard(), 0.3F));
		 
		 //MinecraftForgeClient.registerItemRenderer(HiveCraft.itemEggsack.itemID, new ItemEggsackRenderer());
		 MinecraftForgeClient.registerItemRenderer(HiveCraft.itemOdoremGlandem.itemID, new ItemOdoremGlandemRenderer());
		 MinecraftForge.EVENT_BUS.register(new GuiOverlay(Minecraft.getMinecraft()));
		 HiveCraft.bioArmorRenderID = RenderingRegistry.addNewArmourRendererPrefix("BioArmor");
	 }
     
}
