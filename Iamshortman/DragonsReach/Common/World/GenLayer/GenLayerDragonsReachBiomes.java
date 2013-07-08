package Iamshortman.DragonsReach.Common.World.GenLayer;

import Iamshortman.DragonsReach.Common.World.Biome.BiomeGenDragonsReachBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerDragonsReachBiomes extends GenLayer
{

	protected BiomeGenDragonsReachBase[] allowedBiomes =
	{ BiomeGenDragonsReachBase.Hills, BiomeGenDragonsReachBase.Forest};

	public GenLayerDragonsReachBiomes(long seed)
	{
		super(seed);
	}

	public GenLayerDragonsReachBiomes(long seed, GenLayer layer)
	{
		super(seed);
		this.parent = layer;
	}

	@Override
	/**
	 * Returns the Biome Array
	 */
	public int[] getInts(int x, int z, int width, int depth)
	{
		int[] dest = IntCache.getIntCache(width * depth);

		for (int dz = 0; dz < depth; dz++)
		{
			for (int dx = 0; dx < width; dx++)
			{
				this.initChunkSeed(dx + x, dz + z);
				dest[(dx + dz * width)] = this.allowedBiomes[nextInt(this.allowedBiomes.length)].biomeID;
			}
		}
		return dest;

	}

	public static GenLayer[] makeTheWorld(long seed)
	{
		GenLayer biomes = new GenLayerDragonsReachBiomes(1L);

		// more GenLayerZoom = bigger biomes
		biomes = new GenLayerZoom(1000L, biomes);
		biomes = new GenLayerZoom(1001L, biomes);
		biomes = new GenLayerZoom(1002L, biomes);
		biomes = new GenLayerZoom(1003L, biomes);
		biomes = new GenLayerZoom(1004L, biomes);
		biomes = new GenLayerZoom(1005L, biomes);
		GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);
		biomes.initWorldGenSeed(seed);
		genlayervoronoizoom.initWorldGenSeed(seed);

		return new GenLayer[]
		{ biomes, genlayervoronoizoom };
	}

}
