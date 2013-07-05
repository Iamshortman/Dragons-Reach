package Iamshortman.DragonsReach.Common.Items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Used To Render Blocks Rotated in inventory like furnaces 
 * @author josh
 *
 */
public class ItemBlockRotatable extends ItemBlock
{

	public ItemBlockRotatable(int par1)
	{
		super(par1);
	}
	
	@Override
	public int getItemDamageFromStack(ItemStack item)
	{
		return 3;
	}

}
