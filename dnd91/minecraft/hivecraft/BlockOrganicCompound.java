package mods.dnd91.minecraft.hivecraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockOrganicCompound extends Block {
	public BlockOrganicCompound (int id, Material material) {
        super(id, material);
        float f = 0.0F;
        float f1 = 0.0F;
        this.setBlockBounds(0.0F + f1, 0.0F + f, 0.0F + f1, 1.0F + f1, 1.0F + f, 1.0F + f1);
        this.setHardness(0.5F);
        this.setStepSound(Block.soundSnowFootstep);
        this.setUnlocalizedName("blockBiomass");
        this.setCreativeTab(HiveCraft.tabHiveCraft);
        this.setLightOpacity(3);
        //this.setTickRandomly(true);
	}
	
	@Override
	public void registerIcons(IconRegister ires){
		this.blockIcon = ires.registerIcon("dnd91/minecraft/hivecraft:OrganicCompound");
	}
}
