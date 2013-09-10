package mods.dnd91.minecraft.hivecraft.hatchling.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.IBioNest;
import mods.dnd91.minecraft.hivecraft.structure.block.TileEntityCocoon;
import mods.dnd91.minecraft.hivecraft.structure.buildplan.BaseArrays;
import mods.dnd91.minecraft.hivecraft.structure.queenNest.TileEntityQueenNest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBuilder extends BlockContainer implements IBioNest{

	private static Random random = new Random();
	private static Map<Integer, Icon> idToTexturePath = new HashMap<Integer,Icon>();
	private static boolean inited = false;
	
	@Override
	public void registerIcons(IconRegister ires){
		if(inited)
			return;
		idToTexturePath.put(0, ires.registerIcon("dnd91/minecraft/hivecraft:OrganicCompound"));
		idToTexturePath.put(1, ires.registerIcon("dnd91/minecraft/hivecraft:OrganicCompound"));
		
		inited = true;
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
		return idToTexturePath.get(0);
    }
	
	public BlockBuilder(int par1, Material par2Material) {
		super(par1, par2Material);
		
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9)
    {
		if(world.isRemote)
			return true;
		TileEntityBuilder ent = (TileEntityBuilder)world.getBlockTileEntity(x, y, z);
		world.setBlock((int)x, (int)y, (int)z, HiveCraft.blockCreep.blockID);
		if(ent == null){
			System.out.println("WTF!!!!");
			return false;
		}
		int type = ent.genetics.getInteger("buildType");
		int build = ent.genetics.getInteger("tooBecome");
		 
		world.setBlockTileEntity((int)x, (int)y, (int)z, new TileEntityCocoon(ent.genetics, BaseArrays.masterBuildPlan(type, build)));
        return true;
    }
	
	@Override
	public int quantityDropped(Random par1Random)
    {
        return 0;
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBuilder();
	}

	@Override
	public boolean isMature(ItemStack item, World world, int x, int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onCollected(EntityPlayer player,
			ItemStack item, World world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		TileEntityBuilder nest = (TileEntityBuilder)world.getBlockTileEntity(x, y, z);
        if(nest == null || nest.genetics == null){
        	System.out.println("ERROR! SERVER! Item dont have genetics!");
        	if(nest == null)
        		System.out.println("NEST IS NULL!");
        	else if(nest.genetics == null)
        		System.out.println("GENETICS IS NULL!");
        	return list;
        }
        	int color = nest.genetics.getInteger("colorID");
        	int family = nest.genetics.getInteger("familyName");
        	
        	ItemStack stack = new ItemStack(HiveCraft.itemBuilder, 1, family*(ItemDye.dyeColorNames.length)+color);
            stack.setTagCompound(nest.genetics);
            list.add(stack);
		
		return list;
	}
}
