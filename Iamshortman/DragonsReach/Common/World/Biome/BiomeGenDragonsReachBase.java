package Iamshortman.DragonsReach.Common.World.Biome;

import java.util.Random;

import Iamshortman.DragonsReach.ColorHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public abstract class BiomeGenDragonsReachBase extends BiomeGenBase 
{
	//TODO 3 Biomes Planned- Flat Planes, Shear Cliffs, Blood Forests(Planes with Trees)
	public static final BiomeGenDragonsReachBase Plains = new BiomeGenDragonsReachPlains(243);
	public static final BiomeGenDragonsReachBase Forest = new BiomeGenDragonsReachForest(244);
	public static final BiomeGenDragonsReachBase Hills = new BiomeGenDragonsReachHills(245);
	
	public BiomeGenDragonsReachBase(int par1) 
	{
		super(par1);
		this.setDisableRain();
		this.setColor(ColorHelper.getColorFromRBG(148, 211, 0));
	}

	/**
	 * Gen For all the Biome Specific Stuff Like Grass, Flowers, Trees.
	 */
	public abstract void DecorateBiome(World world, Random rand, int ChunkX, int ChunkZ, int AbsoluteX, int AbsoluteZ);
}
