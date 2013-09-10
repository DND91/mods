package mods.dnd91.minecraft.hivecraft.hivenetwork;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.dnd91.minecraft.hivecraft.HiveCraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockPath extends BlockContainer {
	
	@SideOnly(Side.CLIENT)
	private Icon[] iconArray = new Icon[2];
	
	public BlockPath(int par1) {
		super(par1, HiveCraft.materialBiomass);
		this.setCreativeTab(HiveCraft.tabHiveCraft);
		this.setUnlocalizedName("blockPath");
	}
	
	@Override
	public int getRenderType(){
		return 31;
	}
	
	@Override
	public int quantityDropped(Random par1Random)
    {
        return 1;
    }
	
	@Override
	public int idDropped(int par1, Random random, int par3){
		return HiveCraft.pathID;
	}

	@Override
	public void breakBlock(World world, int par2, int par3, int par4, int par5, int par6){
		super.breakBlock(world, par2, par3, par4, par5, par6);
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta){
		int j1 = meta & 15;
        byte b0 = 0;

        switch (side)
        {
            case 0:
            case 1:
                b0 = 0;
                break;
            case 2:
            case 3:
                b0 = 2;
                break;
            case 4:
            case 5:
                b0 = 4;
        }

        return j1 | b0;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta){
		int k = meta & 12;
		int l = meta & 3;
		if(side == meta || side == TileEntityNode.sideToSide(meta))
			return this.iconArray[0];
		else
			return this.iconArray[1];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister ires){
		this.iconArray[0] = ires.registerIcon("dnd91/minecraft/hivecraft:PathTop");
		this.iconArray[1] = ires.registerIcon("dnd91/minecraft/hivecraft:PathSide");
	}

	@Override
	   public TileEntity createNewTileEntity(World world) {
	      return new TileEntityPath();
	   }
	
	
}
