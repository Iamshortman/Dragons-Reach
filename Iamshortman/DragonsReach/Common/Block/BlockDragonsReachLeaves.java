package Iamshortman.DragonsReach.Common.Block;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockDragonsReachLeaves extends BlockDragonsReach implements IShearable
{
	
    int[] adjacentTreeBlocks;

    @SideOnly(Side.CLIENT)
    private Icon leaves_opaque;
    
	public BlockDragonsReachLeaves(int par1)
	{
		super(par1, Material.leaves);
		this.setTickRandomly(true);
	}

	 public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
	    {
	        byte b0 = 1;
	        int j1 = b0 + 1;

	        if (par1World.checkChunksExist(par2 - j1, par3 - j1, par4 - j1, par2 + j1, par3 + j1, par4 + j1))
	        {
	            for (int k1 = -b0; k1 <= b0; ++k1)
	            {
	                for (int l1 = -b0; l1 <= b0; ++l1)
	                {
	                    for (int i2 = -b0; i2 <= b0; ++i2)
	                    {
	                        int j2 = par1World.getBlockId(par2 + k1, par3 + l1, par4 + i2);

	                        if (Block.blocksList[j2] != null)
	                        {
	                            Block.blocksList[j2].beginLeavesDecay(par1World, par2 + k1, par3 + l1, par4 + i2);
	                        }
	                    }
	                }
	            }
	        }
	    }

	    /**
	     * Ticks the block if it's been scheduled
	     */
	    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	    {
	        if (!par1World.isRemote)
	        {
	                byte b0 = 4;
	                int i1 = b0 + 1;
	                byte b1 = 32;
	                int j1 = b1 * b1;
	                int k1 = b1 / 2;

	                if (this.adjacentTreeBlocks == null)
	                {
	                    this.adjacentTreeBlocks = new int[b1 * b1 * b1];
	                }

	                int l1;

	                if (par1World.checkChunksExist(par2 - i1, par3 - i1, par4 - i1, par2 + i1, par3 + i1, par4 + i1))
	                {
	                    int i2;
	                    int j2;
	                    int k2;

	                    for (l1 = -b0; l1 <= b0; ++l1)
	                    {
	                        for (i2 = -b0; i2 <= b0; ++i2)
	                        {
	                            for (j2 = -b0; j2 <= b0; ++j2)
	                            {
	                                k2 = par1World.getBlockId(par2 + l1, par3 + i2, par4 + j2);

	                                Block block = Block.blocksList[k2];

	                                if (block != null && block.canSustainLeaves(par1World, par2 + l1, par3 + i2, par4 + j2))
	                                {
	                                    this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
	                                }
	                                else if (block != null && block.isLeaves(par1World, par2 + l1, par3 + i2, par4 + j2))
	                                {
	                                    this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
	                                }
	                                else
	                                {
	                                    this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
	                                }
	                            }
	                        }
	                    }

	                    for (l1 = 1; l1 <= 4; ++l1)
	                    {
	                        for (i2 = -b0; i2 <= b0; ++i2)
	                        {
	                            for (j2 = -b0; j2 <= b0; ++j2)
	                            {
	                                for (k2 = -b0; k2 <= b0; ++k2)
	                                {
	                                    if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] == l1 - 1)
	                                    {
	                                        if (this.adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
	                                        {
	                                            this.adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
	                                        }

	                                        if (this.adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
	                                        {
	                                            this.adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
	                                        }

	                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2)
	                                        {
	                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;
	                                        }

	                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2)
	                                        {
	                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;
	                                        }

	                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] == -2)
	                                        {
	                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] = l1;
	                                        }

	                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2)
	                                        {
	                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
	                                        }
	                                    }
	                                }
	                            }
	                        }
	                    }
	                }

	                l1 = this.adjacentTreeBlocks[k1 * j1 + k1 * b1 + k1];

	                if (l1 >= 0)
	                {

	                }
	                else
	                {
	                    this.removeLeaves(par1World, par2, par3, par4);
	                }

	        }
	    }

	    private void removeLeaves(World par1World, int par2, int par3, int par4)
	    {
	        this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
	        par1World.setBlockToAir(par2, par3, par4);
	    }

	    
	@Override
    public boolean isOpaqueCube()
    {
        return !Minecraft.getMinecraft().gameSettings.fancyGraphics;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        int i1 = par1IBlockAccess.getBlockId(par2, par3, par4);
        return Minecraft.getMinecraft().gameSettings.fancyGraphics && i1 == this.blockID ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }

	@Override
    @SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
    {
		if(!Minecraft.getMinecraft().gameSettings.fancyGraphics)
		{
			return this.leaves_opaque;
		}
        return this.blockIcon;
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		String temp = this.getUnlocalizedName().substring(12);
		this.blockIcon = this.registerIconForMod(par1IconRegister, temp);
    	this.leaves_opaque = this.registerIconForMod(par1IconRegister, temp + "_opaque");
    }
	
	@Override
	public boolean isShearable(ItemStack item, World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune)
	{
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1));
		return ret;
	}
	
    @Override
    public void beginLeavesDecay(World world, int x, int y, int z)
    {
        world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 4);
    }

    @Override
    public boolean isLeaves(World world, int x, int y, int z)
    {
        return true;
    }
    
    @Override
    public int quantityDropped(Random par1Random)
    {
        return par1Random.nextInt(20) == 0 ? 1 : 0;
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return BlockDragonsReach.blackWoodSapling.blockID;
    }
}
