package mods.dnd91.minecraft.hivecraft.structure.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.dnd91.minecraft.hivecraft.HiveCraft;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;


public class BlockCocoon extends BlockContainer {
	
	@SideOnly(Side.CLIENT)
	private Icon[] iconArray = new Icon[5];

	public BlockCocoon(int par1) {
		super(par1, HiveCraft.materialBiomass);
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray[0] = par1IconRegister.registerIcon("dnd91/minecraft/hivecraft:Creep");
        this.iconArray[1] = par1IconRegister.registerIcon("dnd91/minecraft/hivecraft:QueenNestCore");
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta){
		int m = meta & 1;
		return this.iconArray[m];
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		
		return new TileEntityCocoon();
	}

}
