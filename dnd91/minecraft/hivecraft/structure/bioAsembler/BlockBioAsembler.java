package mods.dnd91.minecraft.hivecraft.structure.bioAsembler;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.structure.queenNest.TileEntityQueenNest;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockBioAsembler extends BlockContainer{
	private Icon[] iconArray = new Icon[5];

	private Random random = new Random();
	
	public BlockBioAsembler(int par1) {
		super(par1, HiveCraft.materialBiomass);
		this.setCreativeTab(HiveCraft.tabHiveCraft);
		this.setUnlocalizedName("blockBioAsembler");
		this.setHardness(1.0f);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray[0] = par1IconRegister.registerIcon("dnd91/minecraft/hivecraft:BioAsemblerCore");
        this.iconArray[1] = par1IconRegister.registerIcon("dnd91/minecraft/hivecraft:BioAsemblerCore");
        this.iconArray[2] = par1IconRegister.registerIcon("dnd91/minecraft/hivecraft:BioAsemblerHard");
        this.iconArray[3] = par1IconRegister.registerIcon("dnd91/minecraft/hivecraft:BioAsemblerNormal");
        this.iconArray[4] = par1IconRegister.registerIcon("dnd91/minecraft/hivecraft:BioAsemblerOpen");
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta){
		int m = meta & 15;
		return this.iconArray[m];
	}
	
	@Override
	public int onBlockPlaced(World world, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        return super.onBlockPlaced(world, par2, par3, par4, par5, par6, par7, par8, par9);
    }

	public TileEntityBioAsembler getTE(World world, int x, int y, int z){
		TileEntityBioAsembler queen = (TileEntityBioAsembler)world.getBlockTileEntity(x,y,z);
		
		TileEntityBioAsembler master = (TileEntityBioAsembler) queen.getMaster();
		
		return master;
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		if (par1World.isRemote)
	    {
			return true;
	    }

		TileEntityBioAsembler ent = getTE(par1World, par2,par3,par4);
		if(ent == null)
			return false;
		System.out.println("TRYING TO OPEN SCREEN");
		par5EntityPlayer.openGui(HiveCraft.instance, 7, par1World, ent.xCoord, ent.yCoord, ent.zCoord);

        return true;
    }
	
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBioAsembler(null);
	}
	
	@Override
	public int quantityDropped(Random par1Random)
    {
        return 0;
    }
	
	@Override //TODO: FIX!
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		for(int i = 0; i < 5;i++)
			par3List.add(new ItemStack(par1, 1, i));
    }
}
