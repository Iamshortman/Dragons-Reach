package Iamshortman.DragonsReach.Common.World;

import Iamshortman.DragonsReach.DragonsReachMod;
import Iamshortman.DragonsReach.Client.Render.DragonsReachSkyProvider;
import Iamshortman.DragonsReach.Common.World.ChunkProviderDragonsReach;
import Iamshortman.DragonsReach.Common.World.Biome.BiomeGenDragonsReachBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3Pool;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

/**
 * @Author Iamshortman
 * File: WorldProviderDragonsReach.java
 * Created: Jul 27, 2013, 9:01:33 PM
 * Description: Provides some special information about the Dragon's Reach.
 */
public class WorldProviderDragonsReach extends WorldProvider
{	
	public WorldProviderDragonsReach()
	{
		this.setDimension(DragonsReachMod.Instance.dimension);
	}

	/**
	 * Used For Registering ChunkManager but i also use it for anything that need a world instance because it
	 * is called right after it get the world
	 */
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerDragonsReach(this.worldObj.getSeed());
		if(this.worldObj.isRemote)
		{
			this.setSkyRenderer(new DragonsReachSkyProvider());
		}
	}
	
	@Override
	public String getDimensionName() 
	{
		return "DragonReach";
	}
	
    @SideOnly(Side.CLIENT)
    /**
     * the y level at which clouds are rendered.
     */
    public float getCloudHeight()
    {
        return 0.0F;
    }
	
    @Override
    public float[] calcSunriseSunsetColors(float par1, float par2)
    {
    	return null;
    }
    
    protected void generateLightBrightnessTable()
    {
        float f = 0.0F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }
	
	
	@Override
	public boolean canRespawnHere()
	{
		return true;
	}
	
	@Override
	public boolean isSkyColored()
	{
		return true;
	}
	
	@Override
	public String getDepartMessage()
	{
		return "Leaving The Dragon's Reach";
	}
	
	@Override
	public String getWelcomeMessage()
	{
		return "Entering The Dragon's Reach";
	}
	
	@Override
	public boolean canSnowAt(int x, int y, int z)
	{
		return false;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks)
    {
        float f1 = this.worldObj.getCelestialAngle(partialTicks);
        float f2 = MathHelper.cos(f1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }
        
        float f7 = (0.76F * f2) + 0.3F;
        return Vec3.createVectorHelper(0.5F * f7, 0.25F * f7, 0.8F * f7);
    }
	
	@Override
	public boolean canDoRainSnowIce(Chunk chunk)
	{
		return false;
	}
	
	@Override
    public boolean getWorldHasVoidParticles()
    {
        return false;
    }
	
	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderDragonsReach(worldObj, worldObj.getSeed(), true);
	}
}