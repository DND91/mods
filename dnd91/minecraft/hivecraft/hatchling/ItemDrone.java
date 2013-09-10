package mods.dnd91.minecraft.hivecraft.hatchling;

import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.genetics.Genpool;
import mods.dnd91.minecraft.hivecraft.hatchling.builder.EntityBuilder;
import mods.dnd91.minecraft.hivecraft.hatchling.drone.EntityDrone;



public class ItemDrone extends ItemHatchling{
	
	/** How the drone snaps up messenges in the hive
	 *  0. Container Lissener
	 *  1. Position Lissener
	 *  2. Pipe Lissener 
	 *  3. ???
	 *   **/
	private static Random random = new Random();
	private int lissnertype = 0;
	
	public ItemDrone(int id) {
		super(id);
		this.maxStackSize = 1;
		this.setMaxDamage(0);
        this.setHasSubtypes(true);
		this.setCreativeTab(HiveCraft.tabHiveCraft);
		setUnlocalizedName("drone");
	}
	
	@Override
	public void registerIcons(IconRegister ires){
		this.itemIcon = ires.registerIcon("dnd91/minecraft/hivecraft:BuilderMagicWorm");
	}
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
		NBTTagCompound nbtCompound = par1ItemStack.getTagCompound();
		if(nbtCompound == null)
			return super.getUnlocalizedName() + ".Drone";
		
		int colorID = nbtCompound.getInteger("colorID");
		int familyName = nbtCompound.getInteger("familyName");
		
        return super.getUnlocalizedName() + "." +ItemDye.dyeColorNames[colorID]+ HiveCraft.familyNames[familyName]+"Drone";          
    }
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
		
		if(par3World.isRemote)
			return true;
		
		Entity builder = new EntityDrone(par3World, par1ItemStack.getTagCompound());
		
		builder.setLocationAndAngles(par2EntityPlayer.posX, par2EntityPlayer.posY, par2EntityPlayer.posZ, MathHelper.wrapAngleTo180_float(par3World.rand.nextFloat() * 360.0F), 0.0F);
        ((EntityDrone)builder).initCreature();
		par3World.spawnEntityInWorld(builder);
		
		par1ItemStack.stackSize--;
		if(par1ItemStack.stackSize == 0)
			par1ItemStack = null;
		
        return true;
    }

}
