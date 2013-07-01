package Iamshortman.DragonsReach.Common.Enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLiving;

public class EnchantmentDragonsReachFrost extends EnchantmentDragonsReach
{

	public EnchantmentDragonsReachFrost(int par1, int par2)
	{
		super(par1, par2, EnumEnchantmentType.weapon);
		this.setName("Frost");
	}

	@Override
	public int calcModifierLiving(int level,EntityLiving living)
	{
		if(living.worldObj.isRemote)
		{
			return 0;
		}
		System.out.println(living);
		
		return 0;
		
	}

	@Override
    public boolean canApplyTogether(Enchantment par1Enchantment)
    {
		if(par1Enchantment == Enchantment.fireAspect)
		{
			return false;
		}
		
		return true;
    }
	
	@Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 50;
    }

	@Override
    public int getMaxLevel()
    {
        return 2;
    }
}
