package Iamshortman.DragonsReach.Common.Network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import Iamshortman.DragonsReach.Common.Network.DragonReachPacket;

import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketDamageArmor extends DragonReachPacket
{
	int damageAmount;
	int armorSlot;
	
	public PacketDamageArmor(int amount, int slot) 
	{
		super();
		this.damageAmount = amount;
		this.armorSlot = slot;
	}
	
	public PacketDamageArmor(Packet250CustomPayload packet) 
	{
		super();
		try 
		{
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(packet.data));
			this.damageAmount = in.readInt();
			this.armorSlot = in.readInt();
		} 
		catch (IOException e) 
		{
			throw new RuntimeException(e);
		}
	}
	

	@Override
	public Packet250CustomPayload getPacket() 
	{
		ByteArrayOutputStream baos = null;
		try
		{
		baos = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(baos);
		out.writeInt(this.damageAmount);//damageAmount
		out.writeInt(this.armorSlot);//armorSlot
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		
		return new Packet250CustomPayload(this.getChannel(), baos.toByteArray());
	}

	@Override
	public void applyPacket(Player player) 
	{
		if(this.isServer)
		{
			EntityPlayer entPlayer = (EntityPlayer) player;
			if(entPlayer.inventory.armorItemInSlot(this.armorSlot) != null)
			{
				ItemStack item = entPlayer.inventory.armorInventory[this.armorSlot];
				item.damageItem(this.damageAmount, entPlayer);
				if(item.stackSize == 0)
				{
					entPlayer.inventory.armorInventory[this.armorSlot] = null;
				}
				
			}
		}
	}

	@Override
	public String getChannel() 
	{
		return "DRMDamageArmor";
	}

	@Override
	public boolean shouldApplyOnServer() 
	{
		return true;
	}

	@Override
	public boolean shouldApplyOnClient() 
	{
		return false;
	}

}
