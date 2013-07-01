package Iamshortman.DragonsReach.Common.Items;

import Iamshortman.DragonsReach.Client.Model.ModelEnderDragonArmor;
import Iamshortman.DragonsReach.Client.Model.ModelWingSuit;
import Iamshortman.DragonsReach.Common.Items.WingPlayerHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemEnderDragonArmor extends ItemArmor
{
	public float glideValue;
	public int jumpsAllowed;
	
	public ItemEnderDragonArmor(int par1,int type)
	{
		super(par1, EnumArmorMaterial.DIAMOND, 0, type);
		this.setCreativeTab(CreativeTabs.tabTransport);
		glideValue = .80F;
		jumpsAllowed = 5;
	}

	// registers icons
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("Dragon'sReach:EnderArmor");
	}
	
	@Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
    	if(slot == 0)
    	{
    		return "/mods/Dragon'sReach/textures/armor/Ender_0.png";
    	}
    	else if(slot == 1)
    	{
    		return "";
    	}
    	return super.getArmorTexture(stack, entity, slot, layer);
    }
	
	/*@Override
    public Icon getIcon(ItemStack stack, int pass)
    {
		return Item.helmetDiamond.getIcon(stack, pass);
    }*/
	
	@Override
    public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack)
    {
		if(!player.capabilities.isCreativeMode && this.armorType == 1)
		{
			WingPlayerHelper.onArmorTickUpdate(world, player, itemStack, glideValue, jumpsAllowed);	
		}
    }
	
	
	@Override
    public ModelBiped getArmorModel(EntityLiving entityLiving, ItemStack itemStack, int armorSlot)
    {
		
		ModelBiped var7 = new ModelEnderDragonArmor(0.5F, armorSlot);
		var7.isSneak = entityLiving.isSneaking();
		var7.isRiding = entityLiving.isRiding();

        return var7;
    }
}
