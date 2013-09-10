package mods.dnd91.minecraft.hivecraft.structure.bioVent;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.structure.bioGrinder.TileEntityGrinder;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockVent extends BlockContainer{

	public BlockVent(int par1) {
		super(par1, HiveCraft.materialBiomass);
		this.setUnlocalizedName("blockBioVent");
        this.setCreativeTab(HiveCraft.tabHiveCraft);
	}

	@Override
	   public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	   {
	       if (par5EntityPlayer.isSneaking())
	       {
	           return true;
	       }
	       else
	       {
	           TileEntityVent grinder = (TileEntityVent)par1World.getBlockTileEntity(par2, par3, par4);

	           if (grinder != null)
	           {
	        	  if(par1World.isRemote){
	        		  System.out.println("CLIENT STAGE -> " + grinder.stage);
	        	  }else{
	        		  System.out.println("--- SERVER ---");
	        		  System.out.println("STAGE " + grinder.stage);
	        		  System.out.println("HEAT" + grinder.heat);
	        	  }
	           }

	           return true;
	       }
	   }
	
	@Override
	   public boolean renderAsNormalBlock()
	   {
	      return false;
	   }
	   
	   @Override
	   public int getRenderType()
	   {
	      return -1;
	   } // where and what to render

	   @Override
	   public boolean isOpaqueCube() {
	      return false;
	   } // make it opaque cube, or else you will be able to see trough the world !


	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityVent();
	}

}
