package Iamshortman.DragonsReach.Common.Block;

import Iamshortman.DragonsReach.Common.TileEntity.TileEntityRainbowBridgeEmitter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

/**
 * @Author Iamshortman
 * File: BlockDragonsReachRainbowBridgeEmitter.java
 * Created: Jul 27, 2013, 9:01:33 PM
 * Description: The rainbow bridge emitter block.
 */
public class BlockDragonsReachRainbowBridgeEmitter extends BlockContainer
{
	@SideOnly(Side.CLIENT)
	protected Icon emitterFront;

	public BlockDragonsReachRainbowBridgeEmitter(int par1)
	{
		super(par1, Material.iron);
        this.setCreativeTab(CreativeTabs.tabRedstone);
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		TileEntity tileEntity = new TileEntityRainbowBridgeEmitter();
		tileEntity.setWorldObj(world);
		return tileEntity;
	}

	/**
	 * How many world ticks before ticking
	 */
	public int tickRate(World par1World)
	{
		return 4;
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
		this.setEmitterDefaultDirection(par1World, par2, par3, par4);
	}

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
    	TileEntityRainbowBridgeEmitter emitter = (TileEntityRainbowBridgeEmitter) par1World.getBlockTileEntity(par2, par3, par4);
    	if(emitter == null)
    	{
    		return null;
    	}
    	
    	int[] offset = emitter.getMetaOffset(emitter.getBlockMetadata());
    	double[] pos1 = {par2, par3, par4};
    	double[] pos2 = {par2 + 1, par3 + 1, par4 + 1};
    	double[] point1 = pos1;
    	double[] point2 = pos2;
    	//TODO Update Length On Client Side Or this will never work.
    	
    	//This Section is just a bunch of complicated Math i though up while at work
    	if(emitter.length > 0)
    	{
        	for(int i = 0; i < offset.length; i++)
        	{
        		if(offset[i] == -1)
        		{
        			point1 = pos1;
        			point1[i] -= emitter.length;
        			point2 = pos2;
        			break;
        		}
        		else if(offset[i] == 1)
        		{
        			point1 = pos1;
        			point2 = pos2;
        			point2[i] += emitter.length;
        			break;
        		}
        		
        	}
    	}
        return AxisAlignedBB.getBoundingBox(point1[0], point1[1], point1[2], point2[0], point2[1], point2[2]);
    }
	
	
	/**
	 * sets Dispenser block direction so that the front faces an non-opaque
	 * block; chooses west to be direction if all surrounding blocks are opaque.
	 */
	private void setEmitterDefaultDirection(World par1World, int par2, int par3, int par4)
	{
		if (!par1World.isRemote)
		{
			int l = par1World.getBlockId(par2, par3, par4 - 1);
			int i1 = par1World.getBlockId(par2, par3, par4 + 1);
			int j1 = par1World.getBlockId(par2 - 1, par3, par4);
			int k1 = par1World.getBlockId(par2 + 1, par3, par4);
			byte b0 = 3;

			if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
			{
				b0 = 3;
			}

			if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
			{
				b0 = 2;
			}

			if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
			{
				b0 = 5;
			}

			if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
			{
				b0 = 4;
			}

			par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
		}
	}

	@Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
    {
        int l = BlockPistonBase.determineOrientation(par1World, par2, par3, par4, par5EntityLiving);
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		if(par1 == par2)
		{
			return this.emitterFront;
		}
		else
		{
			return this.blockIcon;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = BlockDragonsReach.registerIconForMod(par1IconRegister, "rainbowBridgeEmitter");
		this.emitterFront = BlockDragonsReach.registerIconForMod(par1IconRegister, "rainbowBridgeEmitter_front");
	}

}
