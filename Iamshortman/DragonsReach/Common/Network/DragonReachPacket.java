package Iamshortman.DragonsReach.Common.Network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.common.network.Player;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

public abstract class DragonReachPacket 
{
	public static HashMap<String, Class> packetTypes = new HashMap();
	public static List<String> channels = new ArrayList();
	public boolean isServer;
	
	public DragonReachPacket()
	{
		if(!packetTypes.containsKey(this.getChannel()))
		{
			packetTypes.put(this.getChannel(), this.getClass());
			channels.add(this.getChannel());
		}
	}
	
	public DragonReachPacket(Packet250CustomPayload packet)
	{
		this();
	}
	
	public abstract Packet250CustomPayload getPacket();
	public abstract void applyPacket(Player player);
	public abstract String getChannel();
	public abstract boolean shouldApplyOnServer();
	public abstract boolean shouldApplyOnClient();
}
