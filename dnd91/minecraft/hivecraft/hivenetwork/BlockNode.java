package mods.dnd91.minecraft.hivecraft.hivenetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.backlog.TileEntityAscender;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockNode extends BlockContainer{
	public BlockNode(int par1) {
		super(par1, HiveCraft.materialBiomass);
		this.setCreativeTab(HiveCraft.tabHiveCraft);
		this.setUnlocalizedName("blockNode");
	}
	
	@Override
	public void breakBlock(World world, int par2, int par3, int par4, int par5, int par6){
		super.breakBlock(world, par2, par3, par4, par5, par6);
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta){
		return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
	}
	
	@Override
	public int quantityDropped(Random par1Random)
    {
        return 1;
    }
	
	@Override
	public int idDropped(int par1, Random random, int par3){
		return HiveCraft.nodeID;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("dnd91/minecraft/hivecraft:PathTop");
    }
	 /* DEBUG */
	 @Override
	 public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	 {
		 TileEntityNode ent = (TileEntityNode)par1World.getBlockTileEntity(par2, par3, par4);
		 if(ent == null)
			 return false;
		OrderPackage order = new OrderPackage("HELLO", "YEY", ent);
		ent.sendToAllSides(order);
	    return true;
	 }
	 
	 @Override
	   public TileEntity createNewTileEntity(World world) {
	      return new TileEntityNode();
	   }
}
