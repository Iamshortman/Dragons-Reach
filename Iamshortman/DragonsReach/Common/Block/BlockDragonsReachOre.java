package Iamshortman.DragonsReach.Common.Block;

import java.util.Random;

import Iamshortman.DragonsReach.Common.Block.BlockDragonsReach;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockDragonsReachOre extends BlockDragonsReach
{
    public BlockDragonsReachOre(int par1)
    {
        super(par1, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
    	if(this.blockID == BlockDragonsReach.oreCoal.blockID)
    	{
    		return Item.coal.itemID;
    	}
    	else if(this.blockID == BlockDragonsReach.oreDiamond.blockID)	
    	{
    		return Item.diamond.itemID;
    	}
    	
    	
    	return this.blockID;
    }


    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);

        if (this.idDropped(par5, par1World.rand, par7) != this.blockID)
        {
            int j1 = 0;

            if (this.blockID == BlockDragonsReach.oreCoal.blockID)
            {
                j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 0, 2);
            }
            else if (this.blockID == BlockDragonsReach.oreDiamond.blockID)
            {
                j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 3, 7);
            }


            this.dropXpOnBlockBreak(par1World, par2, par3, par4, j1);
        }
    }

}

