package mods.dnd91.minecraft.hivecraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.IBlockLiquid;
import net.minecraftforge.liquids.ILiquid;

public class BlockStationaryBioAcid extends BlockStationary implements ILiquid {

	protected BlockStationaryBioAcid(int par1) {
		super(par1, Material.water);
		this.setHardness(100.0f);
		this.setLightOpacity(3);
		this.disableStats();
		this.setCreativeTab(HiveCraft.tabHiveCraft);
		this.setUnlocalizedName("blockBioAcidStill");
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.theIcon = new Icon[] {
                iconRegister.registerIcon("dnd91/minecraft/hivecraft:Acid"),
                iconRegister.registerIcon("dnd91/minecraft/hivecraft:Acid") };
    }

	@Override
	public int stillLiquidId() {
		return HiveCraft.blockBioAcidStill.blockID;
	}

	@Override
	public boolean isMetaSensitive() {
		return false;
	}

	@Override
	public int stillLiquidMeta() {
		return 0;
	}
	
	@Override
	public int getFireSpreadSpeed(World world, int x, int y, int z, int metadata, ForgeDirection face) {
		return 300;
	}

	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face) {
		return 1;
	}

	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face) {
		return true;
	}

	@Override
	public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side) {
		return true;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		super.onEntityCollidedWithBlock(par1World, par2, par3, par4, par5Entity);
		
		if(par5Entity == null)
			return;
		if(par5Entity instanceof EntityPlayer || par5Entity instanceof EntityItem || par5Entity instanceof EntityMob)
		if(par1World.rand.nextInt(80) == 0){
			double d5 = 0;
			double d7 = 0;
			double d6 = 0;
			for(int i = 0; i < 3; i++){
				d5 = (double)((float)par2 + par1World.rand.nextFloat());
				d7 = (double)par3 + this.maxY;
				d6 = (double)((float)par4 + par1World.rand.nextFloat());
				par1World.spawnParticle("lava", d5, d7, d6, 0.0D, 0.0D, 0.0D);
			}
			par1World.playSound(d5, d7, d6, "liquid.lavapop", 0.2F + par1World.rand.nextFloat() * 0.2F, 0.9F + par1World.rand.nextFloat() * 0.15F, false);
			
			
			if(!par1World.isRemote && par5Entity instanceof EntityItem){
				EntityItem item = (EntityItem)par5Entity;
				if(item.getEntityItem().getItem().itemID != HiveCraft.itemBiomass.itemID || (item.getEntityItem().getItem() == HiveCraft.itemBiomass && item.getEntityItem().hasTagCompound())){
					int cost = BiomassAppedix.getBiomassContent(item.getEntityItem().getItem());
					
					if(cost == 100 || cost > item.getEntityItem().stackSize)
						return;
					
					EntityItem entityitem = new EntityItem(par1World, item.posX, item.posY, item.posZ, new ItemStack(HiveCraft.itemBiomass, 1, 1));
					entityitem.getEntityItem().setTagCompound(null);
	                entityitem.motionX = item.motionX;
	                entityitem.motionY = item.motionY;
	                entityitem.motionZ = item.motionZ;
	                
	                par1World.spawnEntityInWorld(entityitem);
	                item.getEntityItem().stackSize -= cost;
	                if(item.getEntityItem().stackSize == 0)
	                	item.setDead();
				}
			}else
				par5Entity.attackEntityFrom(HiveCraft.liquefied, 4);
		}
        
	}

}
