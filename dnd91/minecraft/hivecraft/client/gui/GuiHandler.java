package mods.dnd91.minecraft.hivecraft.client.gui;

import java.util.List;

import mods.dnd91.minecraft.hivecraft.backlog.ContainerAscender;
import mods.dnd91.minecraft.hivecraft.backlog.TileEntityAscender;
import mods.dnd91.minecraft.hivecraft.book.GuiKnowledges;
import mods.dnd91.minecraft.hivecraft.book.ServerHiveBook;
import mods.dnd91.minecraft.hivecraft.book.page.GuiPage;
import mods.dnd91.minecraft.hivecraft.book.page.PageAppedix;
import mods.dnd91.minecraft.hivecraft.eggs.ContainerHatcher;
import mods.dnd91.minecraft.hivecraft.eggs.TileEntityHatcher;
import mods.dnd91.minecraft.hivecraft.hatchling.drone.ContainerFilter;
import mods.dnd91.minecraft.hivecraft.hatchling.drone.EntityDrone;
import mods.dnd91.minecraft.hivecraft.hatchling.queen.ContainerQueen;
import mods.dnd91.minecraft.hivecraft.larva.ContainerSpawnpool;
import mods.dnd91.minecraft.hivecraft.larva.TileEntitySpawnpool;
import mods.dnd91.minecraft.hivecraft.structure.bioAsembler.ContainerBioAsembler;
import mods.dnd91.minecraft.hivecraft.structure.bioAsembler.TileEntityBioAsembler;
import mods.dnd91.minecraft.hivecraft.structure.bioWorks.ContainerBioWorks;
import mods.dnd91.minecraft.hivecraft.structure.bioWorks.TileEntityBioWorks;
import mods.dnd91.minecraft.hivecraft.structure.queenNest.ContainerQueenNest;
import mods.dnd91.minecraft.hivecraft.structure.queenNest.TileEntityQueenNest;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		switch(ID){
		case 106:
			EntityDrone filter = (EntityDrone)world.getEntityByID(x);
			if(filter != null){
				return new ContainerFilter(player.inventory, filter);
			}
			break;
		}
		
		if(ID != 5)
		if(tileEntity != null)
		if(tileEntity instanceof TileEntityAscender)
		{
			return new ContainerAscender(player.inventory, (TileEntityAscender) tileEntity);
		}else if(tileEntity instanceof TileEntityHatcher)
		{
			return new ContainerHatcher(player.inventory, (TileEntityHatcher) tileEntity);
		}else if(tileEntity instanceof TileEntitySpawnpool){
			return new ContainerSpawnpool(player.inventory, (TileEntitySpawnpool) tileEntity);
		}else if(tileEntity instanceof TileEntityQueenNest){
			return new ContainerQueenNest(player.inventory, (TileEntityQueenNest) tileEntity);
		}else if(tileEntity instanceof TileEntityBioAsembler){
			return new ContainerBioAsembler(player.inventory, (TileEntityBioAsembler) tileEntity);
		}else if(tileEntity instanceof TileEntityBioWorks){
			return new ContainerBioWorks(player.inventory, (TileEntityBioWorks) tileEntity);
		}
		
		if(ID == 100){
			return null;
		}else if(500 <= ID && ID < 600)
			return null;
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		switch(ID){
		case 106:
			EntityDrone filter = (EntityDrone)world.getEntityByID(x);
			if(filter != null){
				return new GuiFilter(player.inventory, filter);
			}
			break;
		}

		if(tileEntity instanceof TileEntityAscender)
		{
			return new GuiAscender(player.inventory, (TileEntityAscender) tileEntity);
		}else if(tileEntity instanceof TileEntityHatcher)
		{
			return new GuiHatcher(player.inventory, (TileEntityHatcher)tileEntity);
		}else if(tileEntity instanceof TileEntitySpawnpool){
			return new GuiSpawnpool(player.inventory, (TileEntitySpawnpool)tileEntity);
		}else if(tileEntity instanceof TileEntityQueenNest){
			return new GuiQueenNest(player.inventory, (TileEntityQueenNest)tileEntity);
		}else if(tileEntity instanceof TileEntityBioAsembler){
			return new GuiBioAsembler(player.inventory, (TileEntityBioAsembler)tileEntity);
		}else if(tileEntity instanceof TileEntityBioWorks){
			return new GuiBioWorks(player.inventory, (TileEntityBioWorks)tileEntity);
		}
		
		if(ID == 100){
			NBTTagCompound compound = (NBTTagCompound) player.getEntityData().getTag(player.username+".HiveBook");
			if(compound == null)
				return null;
			return new GuiKnowledges(compound, player);
		}else if(500 <= ID && ID < 600){
			return new GuiPage(PageAppedix.pageList.get(ID - 500));
		}
		return null;
	}
	
}
