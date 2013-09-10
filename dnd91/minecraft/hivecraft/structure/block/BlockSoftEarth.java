package mods.dnd91.minecraft.hivecraft.structure.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.dnd91.minecraft.hivecraft.HiveCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockSoftEarth extends Block{

	public BlockSoftEarth(int par1) {
		super(par1, HiveCraft.materialBiomass);
		this.setCreativeTab(HiveCraft.tabHiveCraft);
		this.setUnlocalizedName("blockSoftEarth");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("dnd91/minecraft/hivecraft:SoftEarth");
    }
	
}
