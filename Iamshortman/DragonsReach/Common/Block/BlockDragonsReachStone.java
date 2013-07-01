package Iamshortman.DragonsReach.Common.Block;

import Iamshortman.DragonsReach.Common.Block.BlockDragonsReach;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockDragonsReachStone extends BlockDragonsReach 
{

	public BlockDragonsReachStone(int par1) 
	{
		super(par1, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

}
