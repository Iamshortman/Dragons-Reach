package Iamshortman.DragonsReach.Common.World.Biome;

import java.util.Random;

import Iamshortman.DragonsReach.Common.World.WorldGenBlackWoodTree;

import net.minecraft.world.World;

public class BiomeGenDragonsReachForest extends BiomeGenDragonsReachBase
{

	public BiomeGenDragonsReachForest(int par1)
	{
		super(par1);
		this.setBiomeName("Dragon's Reach Forest");
		this.setMinMaxHeight(0.1F, 0.3F);
	}

	@Override
	public void DecorateBiome(World world, Random rand, int ChunkX, int ChunkZ, int AbsoluteX, int AbsoluteZ)
	{
		for (int j3 = 0; j3 < 5 + rand.nextInt(10); j3++)
		{
			int x = AbsoluteX + rand.nextInt(16);
			int z = AbsoluteZ + rand.nextInt(16);
			int y = world.getHeightValue(x, z);
			if (y != 0)
			{
				new WorldGenBlackWoodTree(false).generate(world, rand, x, y, z);
			}
		}
	}

}
