package Iamshortman.DragonsReach.Common.Block;

import java.util.Random;

import Iamshortman.DragonsReach.Common.World.WorldGenBlackWoodTree;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockDragonsReachSapling extends BlockDragonsReach
{

	public BlockDragonsReachSapling(int par1)
	{
		super(par1, Material.plants);
        this.setTickRandomly(true);
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            super.updateTick(par1World, par2, par3, par4, par5Random);

            
            int random = par5Random.nextInt(7);
            System.out.println(random);
            if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && random == 0)
            {
                this.GrowTree(par1World, par2, par3, par4, par5Random);
            }
        }
    }
	
	
	private void GrowTree(World world, int x, int y, int z, Random rand)
	{
		world.setBlockToAir(x, y, z);
		new WorldGenBlackWoodTree(true).generate(world, rand, x, y, z);
	}

	@Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
    	if(!this.canPlaceBlockAt(par1World, par2, par3, par4))
    	{
    		par1World.setBlockToAir(par2, par3, par4);
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
    	}
    }
	
	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		int id = par1World.getBlockId(par2, par3 - 1, par4);
		return id == BlockDragonsReach.grass.blockID || id == BlockDragonsReach.dirt.blockID;
	}
	
	@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }

	@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

	@Override
    public int getRenderType()
    {
        return 1;
    }
}
