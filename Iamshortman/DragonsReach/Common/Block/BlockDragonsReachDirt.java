package Iamshortman.DragonsReach.Common.Block;

import Iamshortman.DragonsReach.Common.Block.BlockDragonsReach;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * @Author Iamshortman
 * File: BlockDragonsReachDirt.java
 * Created: Jul 27, 2013, 9:01:33 PM
 * Description: The basic dirt block class.
 */
public class BlockDragonsReachDirt extends BlockDragonsReach 
{

	public BlockDragonsReachDirt(int par1) 
	{
        super(par1, Material.ground);
        this.setCreativeTab(CreativeTabs.tabBlock);
	}

}
