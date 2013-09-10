package mods.dnd91.minecraft.hivecraft.structure.queenNest;

import java.util.Arrays;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.hivenetwork.TileEntityNode;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

//http://minalien.com/tutorial-multi-block-structures-in-minecraft/

public class BlockQueenNest extends BlockContainer{
	
	private Icon[] iconArray = new Icon[5];

	private Random random = new Random();
	

	public BlockQueenNest(int par1) {
		super(par1, HiveCraft.materialBiomass);
		this.setCreativeTab(HiveCraft.tabHiveCraft);
		this.setUnlocalizedName("blockQueenNest");
		this.setHardness(1.0f);
		//this.setTickRandomly(true);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray[0] = par1IconRegister.registerIcon("dnd91/minecraft/hivecraft:Cocoon");
        this.iconArray[1] = par1IconRegister.registerIcon("dnd91/minecraft/hivecraft:QueenNestCore");
        this.iconArray[2] = par1IconRegister.registerIcon("dnd91/minecraft/hivecraft:QueenNestHard");
        this.iconArray[3] = par1IconRegister.registerIcon("dnd91/minecraft/hivecraft:QueenNestNormal");
        this.iconArray[4] = par1IconRegister.registerIcon("dnd91/minecraft/hivecraft:QueenNestOpen");
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta){
		int m = meta & 15;
		return this.iconArray[m];
	}

	public boolean canMultiBlock(World world, int x, int y, int z){
		boolean canMorph = true;
		for(int modX = -1; modX < 2; modX++)
		for(int modY = -1; modY < 2; modY++)
		for(int modZ = -1; modZ < 2; modZ++)
			if(world.getBlockId(x+modX, y+modY, z+modZ) != HiveCraft.queenNestID || (world.getBlockMetadata(x+modX, y+modY, z+modZ) == HiveCraft.queenNestID && world.getBlockMetadata(x+modX, y+modY, z+modZ) != 0))
				canMorph = false;
		return canMorph;
	}
	
	public void updateBlocks(World world, int x, int y, int z){
		for(int modX = -1; modX < 2; modX++)
		for(int modY = -1; modY < 2; modY++)
		for(int modZ = -1; modZ < 2; modZ++)
			world.markBlockForUpdate(x+modX, y+modY, z+modZ);
	}
	
	@Override
	public int onBlockPlaced(World world, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        return par9;
    }

	public TileEntityQueenNest getTE(World world, int x, int y, int z){
		TileEntityQueenNest queen = (TileEntityQueenNest)world.getBlockTileEntity(x,y,z);
		
		TileEntityQueenNest master = (TileEntityQueenNest) queen.getMaster();
		
		return master;
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		if (par1World.isRemote)
	    {
			return true;
	    }

		TileEntityQueenNest ent = getTE(par1World, par2,par3,par4);
		if(ent == null)
			return false;
		par5EntityPlayer.openGui(HiveCraft.instance, 6, par1World, ent.xCoord, ent.yCoord, ent.zCoord);

        return true;
    }
	
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
		//par6 meta
		if(!par1World.isRemote && par6 == 1){
			float f = this.random.nextFloat() * 0.8F + 0.1F;
            float f1 = this.random.nextFloat() * 0.8F + 0.1F;
            float f2 = this.random.nextFloat() * 0.8F + 0.1F;
            TileEntityQueenNest nest = (TileEntityQueenNest)par1World.getBlockTileEntity(par2, par3, par4);
            if(nest == null || nest.genetics == null){
            	System.out.println("ERROR! SERVER! Nest dont have genetics!");
            	return;
            }
                EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(HiveCraft.itemBiomass, 1, 1));

                entityitem.getEntityItem().setTagCompound(nest.genetics);

                float f3 = 0.05F;
                entityitem.motionX = (double)((float)this.random.nextGaussian() * f3);
                entityitem.motionY = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
                entityitem.motionZ = (double)((float)this.random.nextGaussian() * f3);
                par1World.spawnEntityInWorld(entityitem);
		}
			
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityQueenNest(null);
	}
	
	@Override
	public int quantityDropped(Random par1Random)
    {
        return 0;
    }

	
}
