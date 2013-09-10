package mods.dnd91.minecraft.hivecraft.structure.buildplan;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.structure.queenNest.TileEntityQueenNest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDebugBluePlan extends BlockContainer{

	public BlockDebugBluePlan(int par1) {
		super(par1, HiveCraft.materialBiomass);
		this.setUnlocalizedName("blockDebugBluePlan");
		this.setCreativeTab(HiveCraft.tabHiveCraft);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityDebugBluePlan();
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		if (par1World.isRemote)
	    {
			return true;
	    }

		TileEntity ent = par1World.getBlockTileEntity(par2,par3,par4);
		if(ent == null || !(ent instanceof TileEntityDebugBluePlan))
			return false;
		
		TileEntityDebugBluePlan debug = (TileEntityDebugBluePlan)ent;
		
		if(debug.plan.isValid(Block.dirt.blockID)){
			System.out.println("IT IS VALID!");
		}else{
			System.out.println("NO VALID!");
		}
		
        return true;
    }

}
