package Iamshortman.DragonsReach.Common.Items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * @Author Iamshortman
 * File: ItemBlockRotatable.java
 * Created: Jul 27, 2013, 9:03:17 PM
 * Description: Used To render blocks rotated in inventory like furnaces. 
 */
public class ItemBlockRotatable extends ItemBlock
{

	public ItemBlockRotatable(int par1)
	{
		super(par1);
	}
	

	@Override
	public int getDamage(ItemStack stack) 
	{
		return 3;
	}

}
