package Iamshortman.DragonsReach.Common.World.Biome;

import java.util.Random;

import net.minecraft.world.World;

public class BiomeGenDragonsReachHills extends BiomeGenDragonsReachBase
{

	public BiomeGenDragonsReachHills(int par1)
	{
		super(par1);
		this.setBiomeName("Dragon's Reach Mountians");
		this.setMinMaxHeight(0.3F, 1.6F);
	}

	@Override
	public void DecorateBiome(World world, Random rand, int ChunkX, int ChunkZ, int AbsoluteX, int AbsoluteZ)
	{
		
	}

}
