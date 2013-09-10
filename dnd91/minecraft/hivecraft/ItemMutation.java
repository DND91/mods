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
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;

public class ItemMutation extends Item implements IMutation{
	Icon[] iconArray = new Icon[3];
	
	
	public ItemMutation(int id) {
        super(id);
        setMaxStackSize(64);
        setCreativeTab(HiveCraft.tabHiveCraft);
        setUnlocalizedName("Mutation");
        setHasSubtypes(true);
	}
	
	@Override
	public void registerIcons(IconRegister ires){
		this.iconArray[0] = ires.registerIcon("dnd91/minecraft/hivecraft:QueenCrownT1");
		this.iconArray[1] = ires.registerIcon("dnd91/minecraft/hivecraft:CraftMass");
		this.iconArray[2] = ires.registerIcon("dnd91/minecraft/hivecraft:DroneToolsT1");
	}
	
	@Override
	public Icon getIconFromDamage(int par1)
    {
        return this.iconArray[par1];
    }
	
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		for(int i = 0; i < iconArray.length; i++)
         par3List.add(new ItemStack(par1, 1, i));
    }
	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
		switch(itemstack.getItemDamage()){
		case 0:
			list.add("We will not judge you for your craftmanship...");
			list.add("But this crown says alot about it.");
			break;
		case 1:
			list.add("Tell thouse pesky larvas how to build...");
			break;
		case 2:
			list.add("Tools of the trade for the standard drone");
			break;
		}
	}
	
	@Override
	public String getItemDisplayName(ItemStack itemstack)
    {
        switch(itemstack.getItemDamage()){
        case 0:
        	return "Sticky Queen Crown";
        case 1:
        	return "CraftMass";
        case 2:
        	return "Standard Drone Tools";
        }
        return "WHAT THE FUCK ARE YOU DONIG!?";
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
