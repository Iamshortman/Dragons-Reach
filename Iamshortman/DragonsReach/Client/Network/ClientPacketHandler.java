package Iamshortman.DragonsReach.Client.Network;

import java.lang.reflect.Constructor;

import Iamshortman.DragonsReach.Common.Network.DragonReachPacket;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 * @Author Iamshortman
 * File: ClientPacketHandler.java
 * Created: Jul 27, 2013, 9:01:33 PM
 * Description: The Client-side specific packet handler. 
 */
public class ClientPacketHandler implements IPacketHandler
{
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		if(DragonReachPacket.packetTypes.containsKey(packet.channel))
		{
			Class packetClass = DragonReachPacket.packetTypes.get(packet.channel);
			try
			{
			Constructor constructor = packetClass.getConstructor(Packet250CustomPayload.class);
			DragonReachPacket packetHandler = (DragonReachPacket) constructor.newInstance(packet);
			packetHandler.isServer = false;
			if(packetHandler.shouldApplyOnClient())
			{
				packetHandler.applyPacket(player);
			}
			}
			catch(Exception e)
			{
				
			}
		}
	}
	
}
