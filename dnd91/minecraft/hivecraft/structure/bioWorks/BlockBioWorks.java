package mods.dnd91.minecraft.hivecraft.structure.bioWorks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.structure.queenNest.TileEntityQueenNest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBioWorks extends BlockContainer{
	private Icon[] iconArray = new Icon[5];
	
	public BlockBioWorks(int id){
		super(id, HiveCraft.materialBiomass);
		this.setCreativeTab(HiveCraft.tabHiveCraft);
		this.setUnlocalizedName("blockBioWorks");
		this.setHardness(1.0f);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister reg){
		this.iconArray[0] = reg.registerIcon("dnd91/minecraft/hivecraft:BioWorksCore");
		this.iconArray[1] = reg.registerIcon("dnd91/minecraft/hivecraft:BioWorksCore");
		this.iconArray[2] = reg.registerIcon("dnd91/minecraft/hivecraft:BioWorksHard");
		this.iconArray[3] = reg.registerIcon("dnd91/minecraft/hivecraft:BioWorksNormal");
		this.iconArray[4] = reg.registerIcon("dnd91/minecraft/hivecraft:BioWorksOpen");
	}
	
	@Override
	public Icon getIcon(int side, int meta){
		int m = meta & 15;
		return this.iconArray[m];
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBioWorks(null);
	}
	
	@Override
	public int getRenderBlockPass()
    {
        return 0;
    }
	
	@Override
	public boolean isOpaqueCube(){
		return true;
	}
	
	@Override
	public boolean renderAsNormalBlock()
    {
        return true;
    }
	
	public TileEntityBioWorks getTE(World world, int x, int y, int z){
		TileEntityBioWorks queen = (TileEntityBioWorks)world.getBlockTileEntity(x,y,z);
		
		TileEntityBioWorks master = (TileEntityBioWorks) queen.getMaster();
		
		return master;
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		if (par1World.isRemote)
	    {
			return true;
	    }

		TileEntityBioWorks ent = getTE(par1World, par2,par3,par4);
		if(ent == null)
			return false;
		par5EntityPlayer.openGui(HiveCraft.instance, 8, par1World, ent.xCoord, ent.yCoord, ent.zCoord);

        return true;
    }
	
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		super.onEntityWalking(par1World, par2, par3, par4, par5Entity);
		if(par5Entity instanceof EntityItem){
			TileEntityBioWorks works = (TileEntityBioWorks)par1World.getBlockTileEntity(par2,par3,par4);
			if(!works.isMaster() || works.isFull() || par5Entity.isDead)
				return;
			
			EntityItem item = (EntityItem)par5Entity;
			if(item.getEntityItem().getItem().itemID == HiveCraft.itemBiomass.itemID && !item.getEntityItem().hasTagCompound()){
				works.insertBiomass(item.getEntityItem());
				if(item.getEntityItem().stackSize == 0)
					item.setDead();
			}
			
		}
	}
	
	/**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
	@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float f = 0.0625F;
        return AxisAlignedBB.getAABBPool().getAABB((double)((float)par2 + f), (double)par3, (double)((float)par4 + f), (double)((float)(par2 + 1) - f), (double)((float)(par3 + 1) - f), (double)((float)(par4 + 1) - f));
    }
    
    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5){

		int i = par1IBlockAccess.getBlockId(par2, par3, par4);
		return i == blockID ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
	}
}