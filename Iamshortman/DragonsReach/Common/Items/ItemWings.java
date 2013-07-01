package Iamshortman.DragonsReach.Common.Items;

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
import net.minecraftforge.client.GuiIngameForge;

public class ItemWings extends ItemArmor
{
	public float glideValue;
	public int jumpsAllowed;
	
	public ItemWings(int par1)
	{
		super(par1, EnumArmorMaterial.CLOTH, 0, 1);
		this.setCreativeTab(CreativeTabs.tabTransport);
		glideValue = .80F;
		jumpsAllowed = 3;
	}

	// registers icons
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		
	}
	
	@Override
    public Icon getIcon(ItemStack stack, int pass)
    {
		return Item.plateLeather.getIcon(stack, pass);
    }
	
	@Override
    public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack)
    {
		if(!player.capabilities.isCreativeMode)
		{
			WingPlayerHelper.onArmorTickUpdate(world, player, itemStack, glideValue, jumpsAllowed);	
		}
    }
	
	
	@Override
    public ModelBiped getArmorModel(EntityLiving entityLiving, ItemStack itemStack, int armorSlot)
    {
		if(armorSlot != 1)
		{
			return null;
		}
		
		ModelBiped var7 = new ModelWingSuit(1.0F);

		var7.bipedHead.showModel = false;
		var7.bipedHeadwear.showModel = false;
		var7.bipedBody.showModel = true;
		var7.bipedRightArm.showModel = true;
		var7.bipedLeftArm.showModel = true;
		var7.bipedRightLeg.showModel = false;
		var7.bipedLeftLeg.showModel = false;
		var7.isSneak = entityLiving.isSneaking();
		var7.isRiding = entityLiving.isRiding();
        ItemStack itemstack = entityLiving.getHeldItem();

        return var7;
    }
}
