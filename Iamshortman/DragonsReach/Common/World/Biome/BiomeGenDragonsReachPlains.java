package Iamshortman.DragonsReach.Common.World.Biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenLakes;

public class BiomeGenDragonsReachPlains extends BiomeGenDragonsReachBase
{

	public BiomeGenDragonsReachPlains(int par1)
	{
		super(par1);
		this.setBiomeName("Dragon's Reach Plains");
		this.setMinMaxHeight(0.1F, 0.3F);
	}

	@Override
	public void DecorateBiome(World world, Random rand, int ChunkX, int ChunkZ, int AbsoluteX, int AbsoluteZ)
	{
        int k1;
        int l1;
        int i2;
        
        if (rand.nextInt(4) == 0)
        {
            k1 = AbsoluteX + rand.nextInt(16) + 8;
            l1 = rand.nextInt(128);
            i2 = AbsoluteZ + rand.nextInt(16) + 8;
            (new WorldGenLakes(Block.waterStill.blockID)).generate(world, rand, k1, l1, i2);
        }

        if (rand.nextInt(6) == 0)
        {
            k1 = AbsoluteX + rand.nextInt(16) + 8;
            l1 = rand.nextInt(rand.nextInt(120) + 8);
            i2 = AbsoluteZ + rand.nextInt(16) + 8;

            if (l1 < 63 || rand.nextInt(10) == 0)
            {
                (new WorldGenLakes(Block.lavaStill.blockID)).generate(world, rand, k1, l1, i2);
            }
        }
	}

}
