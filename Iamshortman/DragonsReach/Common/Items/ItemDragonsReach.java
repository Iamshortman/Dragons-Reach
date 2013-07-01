package Iamshortman.DragonsReach.Common.Items;

import Iamshortman.DragonsReach.Common.Items.ItemEnderDragonArmor;
import Iamshortman.DragonsReach.Common.Items.ItemWings;
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
		String temp = this.getUnlocalizedName().substring(6 + 5);
		this.itemIcon = par1IconRegister.registerIcon("Dragon'sReach:" + temp);
	}
	//My Items Start here
	
	public static Item wings;
	public static Item EnderDragonHead;
	public static Item EnderDragonChestPlate;
	
	
	public static void initItems()
	{
		wings = new ItemWings(5000).setUnlocalizedName("WingSuit");
		setName(wings, "Leather Wingpack");
		
		EnderDragonHead = new ItemEnderDragonArmor(5001,0);
		EnderDragonChestPlate = new ItemEnderDragonArmor(5002,1);
	}
	
	public static void setName(Item item, String name)
	{
		LanguageRegistry.addName(item, name);
	}
}
