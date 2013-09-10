package mods.dnd91.minecraft.hivecraft.larva;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.HiveCraftWorldData;
import mods.dnd91.minecraft.hivecraft.backlog.AscenderRecipes;
import mods.dnd91.minecraft.hivecraft.genetics.Genetics;
import mods.dnd91.minecraft.hivecraft.genetics.Genpool;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCloth;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemLarva extends Item implements ILarva{

	private static Random random = new Random();
	private static Icon[] icons = new Icon[2];
	
	public ItemLarva(int id, int dmg) {
		super(id);
		this.maxStackSize = 1;
		this.setMaxDamage(0);
        this.setHasSubtypes(true);
		this.setCreativeTab(HiveCraft.tabHiveCraft);
		setUnlocalizedName("larva");
		
		
	}
	
	@Override
	public void registerIcons(IconRegister ires){
		icons[0] = ires.registerIcon("dnd91/minecraft/hivecraft:Larva");
		icons[1] = ires.registerIcon("dnd91/minecraft/hivecraft:Larva_2_1");
	}
	
	@Override
	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
        return icons[stack.getItemDamage()];
    }
	
	@Override
	public Icon getIconFromDamage(int par1)
    {
        return icons[par1];
    }
	
	@Override
	public String getItemDisplayName(ItemStack par1ItemStack)
    {
		if(par1ItemStack.hasTagCompound()){
			if(par1ItemStack.getTagCompound().hasKey("genetics")){
				Genetics genetics = new Genetics(par1ItemStack.getTagCompound().getCompoundTag("genetics"));
				return genetics.getName() + " Larva";
			}else{
				return super.getItemDisplayName(par1ItemStack);
			}
		}else{
			return super.getItemDisplayName(par1ItemStack);
		}
    }
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add("Small squrmly larva");
		par3List.add("-- Additional Info --");
		if(par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("genetics")){
			par3List.add("§2HAS §7genetics.");
			Integer i = par1ItemStack.getTagCompound().getCompoundTag("genetics").getInteger("familyID");
			par3List.add("Family id" + i.toString());
		}else
			par3List.add("§4NO §7genetics");
	}
	
}
