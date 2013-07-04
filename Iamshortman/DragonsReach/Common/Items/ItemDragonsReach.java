package Iamshortman.DragonsReach.Common.Items;

import Iamshortman.DragonsReach.Common.Forge.DragonsReachConfig;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemDragonsReach extends Item
{
	public ItemDragonsReach(int par1)
	{
		super(par1);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("Dragon'sReach:" + this.getUnlocalizedName());
	}
	
	//My Items Start here
	public static Item enderStaff;
	
	public static void initItems()
	{
		enderStaff = new ItemDragonsReachEnderStaff(DragonsReachConfig.enderStaffID).setUnlocalizedName("EnderStaff");
	}
	
	public static void setName(Item item, String name)
	{
		LanguageRegistry.addName(item, name);
	}
}
