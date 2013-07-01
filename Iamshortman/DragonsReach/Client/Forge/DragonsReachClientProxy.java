package Iamshortman.DragonsReach.Client.Forge;

import net.minecraft.entity.player.EntityPlayer;
import Iamshortman.DragonsReach.Common.Forge.DragonsReachCommonProxy;
import Iamshortman.DragonsReach.Common.Network.PacketDamageArmor;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.PacketDispatcher;

public class DragonsReachClientProxy extends DragonsReachCommonProxy
{

	@Override
	public void load(FMLInitializationEvent evt)
	{
		
	}

	@Override
	public void RenderStuff()
	{
		//stuff
	}

	@Override
	public void postInit(FMLPostInitializationEvent evt)
	{
		
	}

	@Override
	public void preInit(FMLPreInitializationEvent evt)
	{
		
	}

	public void damageArmorItem(int amount, EntityPlayer player, int slot) 
	{
		PacketDispatcher.sendPacketToServer(new PacketDamageArmor(amount, slot).getPacket());
	}
}
