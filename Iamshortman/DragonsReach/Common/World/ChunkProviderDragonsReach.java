package Iamshortman.DragonsReach.Common.World;

import java.util.List;
import java.util.Random;

import Iamshortman.DragonsReach.DragonsReachMod;
import Iamshortman.DragonsReach.Common.Block.BlockDragonsReach;
import Iamshortman.DragonsReach.Common.World.Biome.BiomeGenDragonsReachBase;

import cpw.mods.fml.common.IWorldGenerator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.MapGenScatteredFeature;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

/**
 * @Author Iamshortman
 * File: ChunkProviderDragonsReach.java
 * Created: Jul 27, 2013, 9:01:33 PM
 * Description: Provides the Chunks given the world seeds.
 */
public class ChunkProviderDragonsReach implements IChunkProvider
{
	private int MaxWorldHeight = 160;
	
	/** RNG. */
	private Random rand;

	/** A NoiseGeneratorOctaves used in generating terrain */
	private NoiseGeneratorOctaves noiseGen1;

	/** A NoiseGeneratorOctaves used in generating terrain */
	private NoiseGeneratorOctaves noiseGen2;

	/** A NoiseGeneratorOctaves used in generating terrain */
	private NoiseGeneratorOctaves noiseGen3;

	/** A NoiseGeneratorOctaves used in generating terrain */
	private NoiseGeneratorOctaves noiseGen4;

	public NoiseGeneratorOctaves mobSpawnerNoise;

	/** Reference to the World object. */
	private World worldObj;

	/** are map structures going to be generated (e.g. strongholds) */
	private final boolean mapFeaturesEnabled;

	/** Holds the overall noise array used in chunk generation */
	private double[] noiseArray;
	private double[] stoneNoise = new double[256];
	private MapGenBase caveGenerator = new MapGenCaves();

	/** The biomes that are used to generate the chunk */
	private BiomeGenBase[] biomesForGeneration;

	/** A double array that hold terrain noise from noiseGen3 */
	double[] noise3;

	/** A double array that hold terrain noise */
	double[] noise1;

	/** A double array that hold terrain noise from noiseGen2 */
	double[] noise2;

	/**
	 * Used to store the 5x5 parabolic field that is used during terrain
	 * generation.
	 */
	float[] parabolicField;

	public ChunkProviderDragonsReach(World par1World, long par2, boolean par4)
	{
		this.worldObj = par1World;
		this.mapFeaturesEnabled = par4;
		this.rand = new Random(par2);
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
		this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
		this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
	}


	public void generateTerrain(int par1, int par2, short[] var2)
	{
		byte byte0 = 2;
		int k = byte0 + 1;
		byte byte1 = (byte) ((this.MaxWorldHeight / 4) + 1);
		int l = byte0 + 1;

		this.noiseArray = this.initializeNoiseFieldSkylandTop(this.noiseArray, par1 * byte0, 0, par2 * byte0, k, byte1, l);
		this.noiseArray = this.initializeNoiseFieldSkylandLower(this.noiseArray, par1 * byte0, 0, par2 * byte0, k, byte1, l);
		for (int i1 = 0; i1 < byte0; i1++)
			for (int j1 = 0; j1 < byte0; j1++)
				for (int k1 = 0; k1 < (this.MaxWorldHeight / 4); k1++)
				{
					double d = 0.25D;
					double d1 = this.noiseArray[(((i1 + 0) * l + (j1 + 0)) * byte1 + (k1 + 0))];
					double d2 = this.noiseArray[(((i1 + 0) * l + (j1 + 1)) * byte1 + (k1 + 0))];
					double d3 = this.noiseArray[(((i1 + 1) * l + (j1 + 0)) * byte1 + (k1 + 0))];
					double d4 = this.noiseArray[(((i1 + 1) * l + (j1 + 1)) * byte1 + (k1 + 0))];
					double d5 = (this.noiseArray[(((i1 + 0) * l + (j1 + 0)) * byte1 + (k1 + 1))] - d1) * d;
					double d6 = (this.noiseArray[(((i1 + 0) * l + (j1 + 1)) * byte1 + (k1 + 1))] - d2) * d;
					double d7 = (this.noiseArray[(((i1 + 1) * l + (j1 + 0)) * byte1 + (k1 + 1))] - d3) * d;
					double d8 = (this.noiseArray[(((i1 + 1) * l + (j1 + 1)) * byte1 + (k1 + 1))] - d4) * d;
					for (int l1 = 0; l1 < 4; l1++)
					{
						double d9 = 0.125D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;
						for (int i2 = 0; i2 < 8; i2++)
						{
							int index = xyzToArrayIndex(i2 + i1 * 8, k1 * 4 + l1, j1 * 8);
							
							int c = xyzToArrayIndex(0,0,1);
							double d14 = 0.125D;
							double d15 = d10;
							double d16 = (d11 - d10) * d14;
							for (int k2 = 0; k2 < 8; k2++)
							{
								int l2 = 0;
								if (d15 > 0.0D)
								{
									l2 = BlockDragonsReach.stone.blockID;
								}
								var2[index] = (short) (l2);
								index += c;
								d15 += d16;
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
	}

	public static int xyzToArrayIndex(int x, int y, int z)
	{
		return y << 8 | z << 4 | x;
	}

	/**
	 * Replaces the stone that was placed in with blocks that match the biome
	 */
	public void replaceBlocksForBiome(int par1, int par2, short[] ids, BiomeGenBase[] par4ArrayOfBiomeGenBase)
	{
		byte b0 = 0;
		double d0 = 0.03125D;
		this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

		for (int x = 0; x < 16; ++x)
		{
			for (int z = 0; z < 16; ++z)
			{
				BiomeGenBase biomegenbase = par4ArrayOfBiomeGenBase[z + x * 16];
				float f = biomegenbase.getFloatTemperature();
				int i1 = (int) (this.stoneNoise[x + z * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
				int j1 = -1;
				short byte0 = (short) BlockDragonsReach.grass.blockID;
				short byte1 = (short) BlockDragonsReach.dirt.blockID;

				for (int y = (this.MaxWorldHeight - 1); y >= 0; y--)
				{
					int index = xyzToArrayIndex(x, y, z);
					
					short byte2 = ids[index];
					
					if (byte2 == 0)
					{
						j1 = -1;
						continue;
					}
					
					if (byte2 != BlockDragonsReach.stone.blockID)
					{
						continue;
					}
					
					if (j1 == -1)
					{
						if (i1 <= 0)
						{
							byte0 = 0;
							byte1 = (short) BlockDragonsReach.stone.blockID;
						}
						
						j1 = i1;
						
						if (y >= 0)
						{
							ids[index] = byte0;
						}
						else
						{
							ids[index] = byte1;
						}
						continue;
					}
					
					if (j1 <= 0)
					{
						continue;
					}
					
					j1--;
					ids[index] = byte1;
				}
			}
		}
	}

	/**
	 * loads or generates the chunk at the chunk location specified
	 */
	public Chunk loadChunk(int par1, int par2)
	{
		return this.provideChunk(par1, par2);
	}

	/**
	 * Will return back a chunk, if it doesn't exist and its not a MP client it
	 * will generates all the blocks for the specified chunk from the map seed
	 * and chunk seed
	 */
	public Chunk provideChunk(int par1, int par2)
	{
		this.rand.setSeed((long) par1 * 341873128712L + (long) par2 * 132897987541L);
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);

		short[] ids = new short[32768 * 2];
		this.generateTerrain(par1, par2, ids);
		this.replaceBlocksForBiome(par1, par2, ids, this.biomesForGeneration);

		byte[] meta = new byte[32768 * 2];
		for (int i = 0; i < meta.length; i++)
		{
			meta[i] = 0;
		}
		
		Chunk chunk = new Chunk(this.worldObj, ids, meta, par1, par2);

		byte[] abyte1 = new byte[this.biomesForGeneration.length];
		for (int k = 0; k < this.biomesForGeneration.length; ++k)
		{
			abyte1[k] = (byte) this.biomesForGeneration[k].biomeID;
		}
		chunk.setBiomeArray(abyte1);
		chunk.generateSkylightMap();

		return chunk;
	}

	/**
	 * generates a subset of the level's terrain Upper Level data. Takes 7 arguments: the
	 * [empty] noise array, the position, and the size.
	 */
	private double[] initializeNoiseFieldSkylandTop(double[] par1ArrayOfDouble, int i, int j, int k, int xSize, int ySize, int zSize)
	{
		if (par1ArrayOfDouble == null)
		{
			par1ArrayOfDouble = new double[xSize * ySize * zSize];
		}
		double d = 684.412D * 4.5D;
		double d1 = 684.412D;
		
		noise3 = noiseGen3.generateNoiseOctaves(noise3, i, j, k, xSize, ySize, zSize, d / 30D, d1 / 160D, d / 30D);
		noise1 = noiseGen1.generateNoiseOctaves(noise1, i, j, k, xSize, ySize, zSize, d, d1, d);
		noise2 = noiseGen2.generateNoiseOctaves(noise2, i, j, k, xSize, ySize, zSize, d, d1, d);
		int k1 = 0;
		int l1 = 0;

		int i2 = 16 / xSize;
		for (int x = 0; x < xSize; x++)
		{
			int k2 = x * i2 + i2 / 2;
			for (int z = 0; z < zSize; z++)
			{
				for (int j3 = 0; j3 < ySize; j3++)
				{
					//Value that is that will be put into the array
					double d8 = 0.0D;
					
					double d10 = noise1[k1] / 512D;
					double d11 = noise2[k1] / 512D;
					double d12 = (noise3[k1] / 10D + 1.0D) / 2D;
					
					if (d12 < 0.0D)
					{
						d8 = d10;
					}
					else if (d12 > 1.0D)
					{
						d8 = d11;
					}
					else
					{
						d8 = d10 + (d11 - d10) * d12;
					}
							
					//Smooths at the top and bottom of the gen. I think
					int k3 = 32;
					if (j3 > ySize - k3)
					{
						double d13 = (float) (j3 - (ySize - k3)) / ((float) k3 - 1.0F);
						d8 = d8 * (1.0D - d13) + -30D * d13;
					}
					k3 = 8;
					if (j3 < k3)
					{
						double d14 = (float) (k3 - j3) / ((float) k3 - 1.0F);
						d8 = d8 * (1.0D - d14) + -30D * d14;
					}
				
					par1ArrayOfDouble[k1] = d8;
					k1++;
				}

			}

		}

		return par1ArrayOfDouble;
	}

	/**
	 * generates a subset of the level's terrain Lower Level data. Takes 7 arguments: the
	 * [empty] noise array, the position, and the size.
	 */
	private double[] initializeNoiseFieldSkylandLower(double[] par1ArrayOfDouble, int i, int j, int k, int xSize, int ySize, int zSize)
	{
		if (par1ArrayOfDouble == null)
		{
			par1ArrayOfDouble = new double[xSize * ySize * zSize];
		}
		
		double d = 684.412D * 2.0D;
		double d1 = 684.412D;
		
		noise3 = noiseGen3.generateNoiseOctaves(noise3, i, j, k, xSize, ySize, zSize, d / 30D, d1 / 160D, d / 30D);
		noise1 = noiseGen1.generateNoiseOctaves(noise1, i, j, k, xSize, ySize, zSize, d, d1, d);
		noise2 = noiseGen2.generateNoiseOctaves(noise2, i, j, k, xSize, ySize, zSize, d, d1, d);
		int k1 = 0;
		int l1 = 0;

		int i2 = 16 / xSize;
		for (int x = 0; x < xSize; x++)
		{
			int k2 = x * i2 + i2 / 2;
			for (int z = 0; z < zSize; z++)
			{
				for (int j3 = 0; j3 < ySize; j3++)
				{
					//Value that is that will be put into the array
					double d8 = 0.0D;
					
					double d10 = noise1[k1] / 512D;
					double d11 = noise2[k1] / 512D;
					double d12 = (noise3[k1] / 10D + 1.0D) / 2D;
					
					if (d12 < 0.0D)
					{
						d8 = d10;
					}
					else if (d12 > 1.0D)
					{
						d8 = d11;
					}
					else
					{
						d8 = d10 + (d11 - d10) * d12;
					}
					
					//Make this Part of the Gen lower then the other
					d8 -= 25D;
					
					//Smooths at the top and bottom of the gen. I think
					int k3 = 32;
					if (j3 > ySize - k3)
					{
						double d13 = (float) (j3 - (ySize - k3)) / ((float) k3 - 1.0F);
						d8 = d8 * (1.0D - d13) + -30D * d13;
					}
					k3 = 8;
					if (j3 < k3)
					{
						double d14 = (float) (k3 - j3) / ((float) k3 - 1.0F);
						d8 = d8 * (1.0D - d14) + -30D * d14;
					}
				
					//Ignores all ready Generated Areas. 
					if(par1ArrayOfDouble[k1] == 0)
					{
						par1ArrayOfDouble[k1] = d8;	
					}
					
					k1++;
				}

			}

		}

		return par1ArrayOfDouble;
	}
	/**
	 * Checks to see if a chunk exists at x, y
	 */
	public boolean chunkExists(int par1, int par2)
	{
		return true;
	}

	/**
	 * Populates chunk with ores etc etc
	 */
	public void populate(IChunkProvider par1IChunkProvider, int ChunkX, int ChunkZ)
	{
		int AbsoluteX = ChunkX * 16;
		int AbsoluteZ = ChunkZ * 16;
		
		BiomeGenDragonsReachBase biome = (BiomeGenDragonsReachBase) this.worldObj.getBiomeGenForCoords(AbsoluteX + 16, AbsoluteZ + 16);
		biome.DecorateBiome(worldObj, rand, ChunkX, ChunkZ, AbsoluteX, AbsoluteZ);
		
		//Dont need to worry about Populateing right now
		if(true)
		{
			return;
		}

		for (int i3 = 0; i3 < 20; i3++)
		{
			int j6 = AbsoluteX + rand.nextInt(16);
			int k9 = rand.nextInt(128);
			int i14 = AbsoluteZ + rand.nextInt(16);
			(new WorldGenMinable(BlockDragonsReach.oreCoal.blockID, 16, BlockDragonsReach.stone.blockID)).generate(worldObj, rand, j6, k9, i14);
		}

		for (int j3 = 0; j3 < 20; j3++)
		{
			int k6 = AbsoluteX + rand.nextInt(16);
			int l9 = rand.nextInt(64);
			int j14 = AbsoluteZ + rand.nextInt(16);
			(new WorldGenMinable(BlockDragonsReach.oreIron.blockID, 8, BlockDragonsReach.stone.blockID)).generate(worldObj, rand, k6, l9, j14);
		}

		for (int k3 = 0; k3 < 2; k3++)
		{
			int l6 = AbsoluteX + rand.nextInt(16);
			int i10 = rand.nextInt(32);
			int k14 = AbsoluteZ + rand.nextInt(16);
			(new WorldGenMinable(BlockDragonsReach.oreGold.blockID, 8, BlockDragonsReach.stone.blockID)).generate(worldObj, rand, l6, i10, k14);
		}

		for (int i4 = 0; i4 < 1; i4++)
		{
			int j7 = AbsoluteX + rand.nextInt(16);
			int k10 = rand.nextInt(24);
			int i15 = AbsoluteZ + rand.nextInt(16);
			(new WorldGenMinable(BlockDragonsReach.oreDiamond.blockID, 7, BlockDragonsReach.stone.blockID)).generate(worldObj, rand, j7, k10, i15);
		}

		/*
		 * for(int j3 = 0; j3 < 4; j3++) { int k6 = k + rand.nextInt(16); int l9
		 * = rand.nextInt(32); int j14 = l + rand.nextInt(16); (new
		 * WorldGenMinable(BlockDragonsReach.oreBloodstone.blockID, 4,
		 * BlockDragonsReach.stone.blockID)).generate(worldObj, rand, k6, l9,
		 * j14); }
		 */

		//BiomeGenDragonsReachBase biome = (BiomeGenDragonsReachBase) this.worldObj.getBiomeGenForCoords(AbsoluteX + 16, AbsoluteZ + 16);
		//biome.DecorateBiome(worldObj, rand, ChunkX, ChunkZ, AbsoluteX, AbsoluteZ);
	}

	/**
	 * Two modes of operation: if passed true, save all Chunks in one go. If
	 * passed false, save up to two chunks. Return true if all chunks have been
	 * saved.
	 */
	public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
	{
		return true;
	}

	/**
	 * Unloads the 100 oldest chunks from memory, due to a bug with
	 * chunkSet.add() never being called it thinks the list is always empty and
	 * will not remove any chunks.
	 */
	public boolean unload100OldestChunks()
	{
		return false;
	}

	/**
	 * Returns if the IChunkProvider supports saving.
	 */
	public boolean canSave()
	{
		return true;
	}

	/**
	 * Converts the instance data to a readable string.
	 */
	public String makeString()
	{
		return "RandomLevelSource";
	}

	/**
	 * Returns a list of creatures of the specified type that can spawn at the
	 * given location.
	 */
	public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
	{
		return null;
	}

	/**
	 * Returns the location of the closest structure of the specified type. If
	 * not found returns null.
	 */
	public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5)
	{
		// TODO Make this work for things
		return null;
	}

	public int getLoadedChunkCount()
	{
		return 0;
	}

	public void recreateStructures(int par1, int par2)
	{
		if (this.mapFeaturesEnabled)
		{

		}
	}

	@Override
	public boolean unloadQueuedChunks()
	{
		return false;
	}

	@Override
	public void func_104112_b()
	{

	}

}
