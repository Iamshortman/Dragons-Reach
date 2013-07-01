package Iamshortman.DragonsReach.Common.Enchantment;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class EnchantmentEventHandler
{
	@ForgeSubscribe
	public void onLivingAttack(LivingAttackEvent evt)
	{
		if(evt.entityLiving.worldObj.isRemote)
		{
			return;
		}
		
		if(evt.source != null && evt.source.getEntity() != null)
		{
			Entity entity = evt.source.getEntity();
			if(entity instanceof EntityLiving)
			{
				EntityLiving living = (EntityLiving) entity;
				//System.out.println(living);
			}
		}
		
	}
	
}
