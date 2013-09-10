package mods.dnd91.minecraft.hivecraft.hatchling;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.genetics.FamilyAppedix;
import mods.dnd91.minecraft.hivecraft.genetics.Genetics;
import mods.dnd91.minecraft.hivecraft.hatchling.builder.TileEntityBuilder;
import mods.dnd91.minecraft.hivecraft.hatchling.drone.EntityDrone;
import mods.dnd91.minecraft.hivecraft.structure.buildplan.BlockPlan;
import mods.dnd91.minecraft.hivecraft.structure.interfaces.IHiveCraftBuild;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemHatchling extends Item {

	/*
	 * NBTTagCompound to the item stacks can be two diffrent stats:
	 * Entity and Block/Building
	 * Common:
	 * - isEntity Entity or Block
	 * Entity:
	 * - ID is Integer
	 * Block/Building:
	 * - Building/Block Name in exp. bioWorks, bioAsembler.
	 */
	public static ItemStack getHatchling(int id, boolean isEntity){
		
		return null;
	}
	
	public ItemHatchling(int id) {
		super(id);
		this.maxStackSize = 1;
		this.setMaxDamage(0);
		setUnlocalizedName("itemHatchling");
	}
	
	@Override
	public void registerIcons(IconRegister ires){
		FamilyAppedix.registerIcons(ires);
	}
	
	@Override
	public Icon getIconFromDamage(int par1)
    {
        return FamilyAppedix.getIconHatchling(par1);
    }
	
	@Override
	public String getItemDisplayName(ItemStack par1ItemStack)
    {
		if(par1ItemStack.hasTagCompound()){
			if(par1ItemStack.getTagCompound().hasKey("genetics")){
				Genetics genetics = new Genetics(par1ItemStack.getTagCompound().getCompoundTag("genetics"));
				return genetics.getName() + " Hatchling";
			}else{
				return super.getItemDisplayName(par1ItemStack);
			}
		}else{
			return super.getItemDisplayName(par1ItemStack);
		}
    }
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add("-- Additional Info --");
		if(par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("genetics")){
			par3List.add("§2HAS §7genetics.");
			NBTTagCompound comp = par1ItemStack.getTagCompound().getCompoundTag("genetics");
			par3List.add("Family id " + ((Integer)comp.getInteger("familyID")).toString());
			if(comp.getBoolean("isEntity")){
				par3List.add("ID " + ((Integer)comp.getInteger("ID")).toString());
				par3List.add("Craft " + comp.getString("craft"));
				par3List.add("Occupation " + comp.getString("occupation"));
			}else{
				par3List.add("ID " + ((Integer)comp.getInteger("ID")).toString());
				par3List.add("Name " + comp.getString("name"));
			}
		}else
			par3List.add("§4NO §7genetics");
	}
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
		if(par3World.isRemote)
			return true;
		
		NBTTagCompound compound = par1ItemStack.getTagCompound().getCompoundTag("genetics");
		boolean isEntity = compound.getBoolean("isEntity");
		
		if(isEntity){
			int id = compound.getInteger("ID");
			Class c = FamilyAppedix.getHatchling(id);
			Entity entity = null;
			try {
				entity = (Entity) c.getConstructor(World.class, NBTTagCompound.class).newInstance(par3World, par1ItemStack.getTagCompound());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			
			entity.setLocationAndAngles(par2EntityPlayer.posX, par2EntityPlayer.posY, par2EntityPlayer.posZ, MathHelper.wrapAngleTo180_float(par3World.rand.nextFloat() * 360.0F), 0.0F);
        	((EntityLiving)entity).initCreature();
			par3World.spawnEntityInWorld(entity);
		
			
		}else{
			int x = par4;
			int y = par5;
			int z = par6;
			
			/**
			 * 
			 * 0: Down y--
			 * 1: Up y++
			 * 2: north z--
			 * 3: south z++
			 * 4: west x--
			 * 5: east x++
			 *
			 */
			switch(par7){
			case 0: 
				y--;
				break;
			case 1:
				y++;
				break;
			case 2:
				z--;
				break;
			case 3:
				z++;
				break;
			case 4:
				x--;
				break;
			case 5:
				x++;
				break;
			}
			
			int id = compound.getInteger("ID");
			BlockPlan blockPlan = FamilyAppedix.getBuilding(id);
			par3World.setBlock(x, y, z, blockPlan.blockID, blockPlan.meta, 1 | 2);
			TileEntity tileEntity = par3World.getBlockTileEntity(x, y, z);
			if(tileEntity instanceof IHiveCraftBuild){
				IHiveCraftBuild build = (IHiveCraftBuild)tileEntity;
				build.setGenetics(compound);
			}
		}
		
		par1ItemStack.stackSize--;
        return true;
    }

}
