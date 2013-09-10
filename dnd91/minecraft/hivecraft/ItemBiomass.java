package mods.dnd91.minecraft.hivecraft;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemBiomass extends Item {
	public ItemBiomass(int id) {
        super(id);
        setMaxStackSize(16);
        setUnlocalizedName("Biomass");
	}
	
	@Override
	public void registerIcons(IconRegister ires){
		this.itemIcon = ires.registerIcon("dnd91/minecraft/hivecraft:Biomass");
		
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if(par1ItemStack.getTagCompound() == null)
			return;
		NBTTagCompound compound = par1ItemStack.getTagCompound();
		
		int familyName = compound.getInteger("familyName");
		int colorID = compound.getInteger("colorID");
		
		par3List.add("Family " + HiveCraft.familyNames[familyName]);
		par3List.add("Color " + colorID);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack itemstack)
    {
		if(itemstack.hasTagCompound())
			return EnumRarity.epic;
		else
			return EnumRarity.common;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public boolean hasEffect(ItemStack itemstack)
    {
		if(itemstack.hasTagCompound())
			return true;
		else
			return false;
    }
	
}
