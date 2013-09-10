package mods.dnd91.minecraft.hivecraft.generation;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class HiveWorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
		case -1:
		    generateNether(world, random, chunkX * 16, chunkZ * 16);
		    break;
		case 0:
		    generateSurface(world, random, chunkX * 16, chunkZ * 16);
		    break;
		case 1:
		    generateEnd(world, random, chunkX * 16, chunkZ * 16);
		    break;
		default:
			generateOther(world, random, chunkX*16,chunkZ*16);
			break;
		}
	}

	private void generateOther(World world, Random random, int i, int j) {
		
	}

	private void generateEnd(World world, Random random, int i, int j) {
		
	}

	private void generateSurface(World world, Random random, int i, int j) {
		if(random.nextInt(100) == 0){
			
			BiomeGenBase gen = world.getBiomeGenForCoords(i, j);
			int x = i + random.nextInt(16);
			int z = j + random.nextInt(16);
			int y = world.getHeightValue(x, z);
			
			if(gen == BiomeGenBase.desert){
				System.out.println("GENERATING SOMETHING AT -> x: "+x+" y: "+y+" z: "+z);
				(new WorldGenBioNest(0, random.nextInt(5))).generate(world, random, x, y, z);
			}else if(gen == BiomeGenBase.plains){
				System.out.println("GENERATING SOMETHING AT -> x: "+x+" y: "+y+" z: "+z);
				(new WorldGenBioNest(1, random.nextInt(3))).generate(world, random, x, y, z);
			}else if(gen == BiomeGenBase.forest || gen == BiomeGenBase.forestHills){
				System.out.println("GENERATING SOMETHING AT -> x: "+x+" y: "+y+" z: "+z);
				(new WorldGenBioNest(2, random.nextInt(10))).generate(world, random, x, y, z);
			}
			
		}
	}

	private void generateNether(World world, Random random, int i, int j) {
		
	}

}
