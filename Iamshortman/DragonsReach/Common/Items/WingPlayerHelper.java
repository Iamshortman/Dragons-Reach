package Iamshortman.DragonsReach.Common.Items;

import Iamshortman.DragonsReach.DragonsReachMod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WingPlayerHelper
{

	static int counter = 0;
	
	public static void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack, float glideValue, int jumpsAllowed)
	{
		
		if(!player.onGround && !player.isInWater() && !player.isSneaking() && !player.capabilities.isFlying && player.motionY < 0)
		{
	    	if(world.isRemote)
	    	{
    			player.motionY *= glideValue;
	    	}
	    	else
	    	{
    			player.fallDistance = 0;
	    	}
	    	
		}
	
		if(world.isRemote && !player.capabilities.isFlying)
		{
			if(Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed() && counter < jumpsAllowed && !player.onGround)
	    	{
	    		player.motionY += 0.85F;
	    		
	    		if(player.motionY > 0.95F)
	    		{
	    			player.motionY = 0.95F;
	    		}
	    		DragonsReachMod.proxy.damageArmorItem(1, player, 2);
	    		counter++;
	    	}
		}
		
		if(world.isRemote && player.onGround)
		{
			counter = 0;
		}
	}

}
