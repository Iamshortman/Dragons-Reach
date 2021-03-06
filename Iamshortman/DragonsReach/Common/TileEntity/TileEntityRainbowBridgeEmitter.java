package Iamshortman.DragonsReach.Common.TileEntity;

import Iamshortman.DragonsReach.Common.Block.BlockDragonsReach;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * @Author Iamshortman
 * File: TileEntityRainbowBridgeEmitter.java
 * Created: Jul 27, 2013, 9:01:33 PM
 * Description: The Rainbow Bridge Emitter's Tile Entity.
 */
public class TileEntityRainbowBridgeEmitter extends TileEntity
{
	public double length = 0;

	public TileEntityRainbowBridgeEmitter()
	{
	
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (!this.worldObj.isRemote)
		{
			int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
			if (this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
			{
				this.EmitBridge(meta);
			}
			else
			{
				this.unEmitBridge();
			}
		}
	}
	
	private void EmitBridge(int blockMetadata)
	{
		if(this.length < 64)
		{
			this.length += 0.5F;
		}
		else if(this.length > 64)
		{
			this.length = 64;
		}
	}

	private void unEmitBridge()
	{
		if(this.length > 0)
		{
			this.length -= 0.5F;
		}
		else if(this.length < 0)
		{
			this.length = 0;
		}
	}

	public int[] getMetaOffset(int metadata)
	{
		// X, Y, and Z movement offsets
		int offset[] = {0,0,0};
		switch(metadata)
		{
		case 0:
			//Downward
			offset[1] = -1;
			break;
		case 1:
			//Upwards
			offset[1] = 1;
			break;
		case 2:
			//North
			offset[2] = -1;
			break;
		case 3:
			//South
			offset[2] = 1;
			break;
		case 4:
			//West
			offset[0] = -1;
			break;
		case 5:
			//East
			offset[0] = 1;
			break;
		}
		return offset;
	}
	
	
	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound)
	{
		super.writeToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setDouble("length", length);
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound)
	{
		super.readFromNBT(par1nbtTagCompound);
		this.length = par1nbtTagCompound.getDouble("length");
	}
}
