package mods.dnd91.minecraft.hivecraft.backlog;

import java.util.Random;


import mods.dnd91.minecraft.hivecraft.HiveCraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import mods.dnd91.minecraft.hivecraft.backlog.TileEntityAscender;

public class BlockAscender extends BlockContainer {
	
private final Random ascenderRand = new Random();
	
   public BlockAscender (int id, Material material) {
        super(id, material);
        this.setHardness(0.5F);
        this.setStepSound(Block.soundSnowFootstep);
        this.setUnlocalizedName("blockAscender");
        this.setCreativeTab(HiveCraft.tabHiveCraft);
        this.setLightOpacity(3);
        this.setTickRandomly(true);
   }
   
   public int idDropped(int par1, Random par2Random, int par3)
   {
       return HiveCraft.ascenderID;
   }
   
   @Override
   public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
   {
       if (par1World.isRemote || par5EntityPlayer.isSneaking())
       {
           return true;
       }
       else
       {
           TileEntityAscender tileentityascender = (TileEntityAscender)par1World.getBlockTileEntity(par2, par3, par4);

           if (tileentityascender != null)
           {
        	   par5EntityPlayer.openGui(HiveCraft.instance, 0, par1World, par2, par3, par4);
           }

           return true;
       }
   }
   
   @Override
   public void registerIcons(IconRegister ires){
      this.blockIcon = ires.registerIcon("dnd91/minecraft/hivecraft:Ascender");
      
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
      return new TileEntityAscender();
   }
   
   @Override
   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
   {
	   TileEntityAscender teAscender = (TileEntityAscender)par1World.getBlockTileEntity(par2, par3, par4);
	   if(teAscender == null)
		   return;
	   
       if (teAscender.ascenderBurnTime > 0)
       {
           float f = (float)par2 + 0.5F;
           float f1 = (float)par3 + 0.8F + par5Random.nextFloat() * 6.0F / 16.0F;
           float f2 = (float)par4 + 1.0F;
           float f3 = 0.52F;
           float f4 = par5Random.nextFloat() * 0.6F - 0.3F;

           par1World.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
           par1World.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);

       }
   }
   
   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
   {
           TileEntityAscender tileentityfurnace = (TileEntityAscender)par1World.getBlockTileEntity(par2, par3, par4);

           if (tileentityfurnace != null)
           {
               for (int j1 = 0; j1 < tileentityfurnace.getSizeInventory(); ++j1)
               {
                   ItemStack itemstack = tileentityfurnace.getStackInSlot(j1);

                   if (itemstack != null)
                   {
                       float f = this.ascenderRand.nextFloat() * 0.8F + 0.1F;
                       float f1 = this.ascenderRand.nextFloat() * 0.8F + 0.1F;
                       float f2 = this.ascenderRand.nextFloat() * 0.8F + 0.1F;

                       while (itemstack.stackSize > 0)
                       {
                           int k1 = this.ascenderRand.nextInt(21) + 10;

                           if (k1 > itemstack.stackSize)
                           {
                               k1 = itemstack.stackSize;
                           }

                           itemstack.stackSize -= k1;
                           EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));

                           if (itemstack.hasTagCompound())
                           {
                               entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                           }

                           float f3 = 0.05F;
                           entityitem.motionX = (double)((float)this.ascenderRand.nextGaussian() * f3);
                           entityitem.motionY = (double)((float)this.ascenderRand.nextGaussian() * f3 + 0.2F);
                           entityitem.motionZ = (double)((float)this.ascenderRand.nextGaussian() * f3);
                           par1World.spawnEntityInWorld(entityitem);
                       }
                   }
               }

               par1World.func_96440_m(par2, par3, par4, par5);
           }
       

       super.breakBlock(par1World, par2, par3, par4, par5, par6);
   }
}