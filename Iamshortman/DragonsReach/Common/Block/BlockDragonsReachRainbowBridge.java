package Iamshortman.DragonsReach.Common.Block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockDragonsReachRainbowBridge extends BlockDragonsReach
{

	public BlockDragonsReachRainbowBridge(int par1)
	{
		super(par1, Material.glass);
        this.setCreativeTab(CreativeTabs.tabRedstone);
		this.setBlockUnbreakable();

	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public int quantityDropped(Random r)
	{
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube()
	{
	         return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock()
	{
	         return false;
	}

}
