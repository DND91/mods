package mods.dnd91.minecraft.hivecraft;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.dnd91.minecraft.hivecraft.book.KnowledgeAppedix;
import mods.dnd91.minecraft.hivecraft.genetics.Genetics;
import mods.dnd91.minecraft.hivecraft.genetics.Genpool;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockBioNest extends Block implements IBioNest{
	@SideOnly(Side.CLIENT)
	private Icon[] iconArray = new Icon[3];

	public BlockBioNest(int par1) {
		super(par1, HiveCraft.materialBiomass);
		this.setCreativeTab(HiveCraft.tabHiveCraft);
		this.setUnlocalizedName("blockBioNest");
	}
	
	@Override
	public void registerIcons(IconRegister ires){
		iconArray[0] = ires.registerIcon("dnd91/minecraft/hivecraft:BioNest_Desert");
		iconArray[1] = ires.registerIcon("dnd91/minecraft/hivecraft:BioNest_Forest");
		iconArray[2] = ires.registerIcon("dnd91/minecraft/hivecraft:BioNest_Plains");
	}
	
	@Override
	public Icon getIcon(int side, int meta)
    {
        return iconArray[meta];
    }
	
	@Override
	public int idDropped(int meta, Random par2Random, int par3)
    {
		switch(meta){
		case 0:
			return Block.sand.blockID;
		case 1:
		case 2:
			return Block.dirt.blockID;
		}
		return Block.blockClay.blockID;
    }

	@Override
	public ArrayList<ItemStack> onCollected(EntityPlayer player, ItemStack item, World world, int x,
			int y, int z, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		int meta = world.getBlockMetadata(x, y, z);
		switch(meta){
		case 0:
			list.add(new ItemStack(Block.sand, 1));
			break;
		case 1:
		case 2:
			list.add(new ItemStack(Block.dirt, 1));
			break;
		}
		
		if(KnowledgeAppedix.hasKnowledgeUnlocked(player, KnowledgeAppedix.firstegg)){
			list.add(Genpool.getRandomGeneticBiomass());
		}else if(KnowledgeAppedix.canUnlockKnowledge(player, KnowledgeAppedix.firstegg)){
			list.add(Genpool.getRandomEggsack());
			KnowledgeAppedix.unlockKnowledge(player, KnowledgeAppedix.firstegg);
		}
		
		return list;
	}

	@Override
	public boolean isMature(ItemStack item, World world, int x, int y, int z) {
		return true;
	}
	
	

}
