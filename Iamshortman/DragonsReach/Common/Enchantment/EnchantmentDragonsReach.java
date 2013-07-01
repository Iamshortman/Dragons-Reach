package Iamshortman.DragonsReach.Common.Enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;

public abstract class EnchantmentDragonsReach extends Enchantment
{
	public EnchantmentDragonsReach(int par1, int par2, EnumEnchantmentType par3EnumEnchantmentType)
	{
		super(par1, par2, par3EnumEnchantmentType);
	}
	
	@Override
	public int calcModifierLiving(int level,EntityLiving living)
	{
		return 0;
	}
	
	@Override
    public int calcModifierDamage(int level, DamageSource par2DamageSource)
    {
        return 0;
    }
	
	//All the Enchantments
	public static Enchantment Frost;
	
	public static void initEnchantments()
	{
		//MinecraftForge.EVENT_BUS.register(new EnchantmentEventHandler());
		Frost = new EnchantmentDragonsReachFrost(140, 1);
	}
}
