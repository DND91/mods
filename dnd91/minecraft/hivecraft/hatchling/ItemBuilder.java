package mods.dnd91.minecraft.hivecraft.hatchling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.genetics.Genpool;
import mods.dnd91.minecraft.hivecraft.hatchling.builder.EntityBuilder;
import mods.dnd91.minecraft.hivecraft.hatchling.builder.TileEntityBuilder;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemBuilder extends ItemHatchling {

	private static Random random = new Random();
	private static Map<Integer, Icon> dmgToIcon = new HashMap<Integer, Icon>();
	
	public ItemBuilder(int id) {
		super(id);
		this.maxStackSize = 1;
		this.setMaxDamage(0);
        this.setHasSubtypes(true);
		this.setCreativeTab(HiveCraft.tabHiveCraft);
		setUnlocalizedName("builder");
	}
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
		NBTTagCompound nbtCompound = par1ItemStack.getTagCompound();
		if(nbtCompound == null)
			return super.getUnlocalizedName() + ".Builder";
		
		int colorID = nbtCompound.getInteger("colorID");
		int familyName = nbtCompound.getInteger("familyName");
		
        return super.getUnlocalizedName() + "." +ItemDye.dyeColorNames[colorID]+ HiveCraft.familyNames[familyName]+"Builder";          
    }
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add("A Bit Fat Builder");
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10)
    {
		if(world.isRemote)
			return true;
		int x = posX;
		int y = posY;
		int z = posZ;
		
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
		switch(side){
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
		
		TileEntityBuilder entity = new TileEntityBuilder(stack.getTagCompound());
		world.setBlock(x, y, z, HiveCraft.blockBuilder.blockID);
		world.setBlockTileEntity(x, y, z, entity);
		
        return true;
    }

}
