package Iamshortman.DragonsReach.Common.Forge;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @Author Iamshortman
 * File: DragonsReachCommonProxy.java
 * Created: Jul 27, 2013, 9:01:33 PM
 * Description: The common specific proxy used for anything that needs to be done on both server and client side. 
 */
public class DragonsReachCommonProxy
{

	public void load(FMLInitializationEvent evt)
	{
		
	}

	public void RenderStuff()
	{
		//Nothing Server-Side
	}

	public void postInit(FMLPostInitializationEvent evt)
	{
		
	}

	public void preInit(FMLPreInitializationEvent evt)
	{
		
	}

	public void damageArmorItem(int i, EntityPlayer player, int j) 
	{
		//Nothing Server-Side
	}


}
