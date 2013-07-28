package Iamshortman.DragonsReach.Common.Block;

import Iamshortman.DragonsReach.Common.Block.BlockDragonsReach;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * @Author Iamshortman
 * File: BlockDragonsReachStone.java
 * Created: Jul 27, 2013, 9:01:33 PM
 * Description: The basic stone block class, really isn't useful unless cobble stone is added.
 */
public class BlockDragonsReachStone extends BlockDragonsReach 
{

	public BlockDragonsReachStone(int par1) 
	{
		super(par1, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

}
