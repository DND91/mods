package mods.dnd91.minecraft.hivecraft.generation;

import java.util.Random;


import mods.dnd91.minecraft.hivecraft.HiveCraft;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBioNest extends WorldGenerator {
	
	private int bioNestID = HiveCraft.blockBioNest.blockID;
	private int bioNestMeta;
	
	private int size_of_nest;
	
	public WorldGenBioNest(int meta, int number){
		size_of_nest = number;
		bioNestMeta = meta;
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		if(random.nextInt(10)==0)
			world.setBlock(i, j, k, this.bioNestID, this.bioNestMeta, 2);
		world.setBlock(i, j-1, k, this.bioNestID, this.bioNestMeta, 2);
		
		for(int x = i-1; x < i+2; x++)
		for(int y = j-4; y < j-1; y++)
		for(int z = k-1; z < k+2; z++){
			if(random.nextInt(15) == 0)
				world.setBlock(x, y, z, this.bioNestID, this.bioNestMeta, 2);
		}
		
		return true;
	}

}
