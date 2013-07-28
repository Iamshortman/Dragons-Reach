package Iamshortman.DragonsReach.Common.Items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * @Author Iamshortman
 * File: ItemDragonsReachEnderStaff.java
 * Created: Jul 27, 2013, 9:01:33 PM
 * Description: A teleportation staff.
 */
public class ItemDragonsReachEnderStaff extends ItemDragonsReach
{

	public ItemDragonsReachEnderStaff(int par1)
	{
		super(par1);
		this.setFull3D();
		this.setMaxStackSize(1);
		this.setMaxDamage(1000);
		this.canRepair = false;
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			MovingObjectPosition movingobjectposition = player.rayTrace(10000, 1.0F);
			if (movingobjectposition == null)
			{
				Vec3 vec = player.getLookVec();
				int dist = 50;
				vec.xCoord *= dist;
				vec.yCoord *= dist;
				vec.zCoord *= dist;

				vec.xCoord += player.posX;
				vec.yCoord += player.posY;
				vec.zCoord += player.posZ;

				player.setPositionAndUpdate(vec.xCoord, vec.yCoord, vec.zCoord);
			}
			else
			{
				double x = movingobjectposition.blockX;
				double y = movingobjectposition.blockY;
				double z = movingobjectposition.blockZ;
				int i = MathHelper.floor_double(x);
				int j = MathHelper.floor_double(y);
				int k = MathHelper.floor_double(z);
				
				player.setPositionAndUpdate(i, j + 1, k);
			}
		}

		return itemStack;
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
	{

	}

}
