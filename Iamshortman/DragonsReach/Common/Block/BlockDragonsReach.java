package Iamshortman.DragonsReach.Common.Block;

import Iamshortman.DragonsReach.DragonsReachMod;
import Iamshortman.DragonsReach.Common.Block.BlockDragonsReachDirt;
import Iamshortman.DragonsReach.Common.Block.BlockDragonsReachGrass;
import Iamshortman.DragonsReach.Common.Block.BlockDragonsReachOre;
import Iamshortman.DragonsReach.Common.Block.BlockDragonsReachPortal;
import Iamshortman.DragonsReach.Common.Block.BlockDragonsReachStone;
import Iamshortman.DragonsReach.Common.Forge.DragonsReachConfig;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class BlockDragonsReach extends Block
{
	public BlockDragonsReach(int par1, Material par2Material)
	{
		super(par1, par2Material);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		String temp = this.getUnlocalizedName().substring(6 + 5);
		this.blockIcon = par1IconRegister.registerIcon("Dragon'sReach:" + temp);
	}
	//End of Block Class
	
	public static Block portal;
	public static Block grass;
	public static Block dirt;
	public static Block stone;
	public static Block oreCoal;
	public static Block oreDiamond;
	public static Block oreIron;
	public static Block oreGold;
	//public static Block oreBloodstone;
	public static Block blackWoodLog;
	public static Block blackWoodPlanks;
	public static Block blackWoodLeaves;
	
	public static void initBlocks()
	{
		portal = new BlockDragonsReachPortal(DragonsReachConfig.portalID).setUnlocalizedName("Dragonportal");
		registerBlock(portal, "Dragon's Reach Portal");
		
		grass = new BlockDragonsReachGrass(DragonsReachConfig.grassID).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Dragongrass");
		registerBlock(grass, "Dragon's Reach Grass");
		
		dirt = new BlockDragonsReachDirt(DragonsReachConfig.dirtID).setHardness(0.5F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("Dragondirt");
		registerBlock(dirt, "Dragon's Reach Dirt");
		
		stone = new BlockDragonsReachStone(DragonsReachConfig.stoneID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Dragonstone");
		registerBlock(stone, "Dragon's Reach Stone");
		
		oreCoal = new BlockDragonsReachOre(DragonsReachConfig.oreCoalID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("DragonoreCoal");
		registerBlock(oreCoal, "Coal Ore");
		OreDictionary.registerOre("oreCoal", oreCoal);
		GameRegistry.addSmelting(oreCoal.blockID, new ItemStack(Item.coal), 0.1F);
		
		oreDiamond = new BlockDragonsReachOre(DragonsReachConfig.oreDiamondID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("DragonoreDiamond");
		registerBlock(oreDiamond, "Diamond Ore");
		OreDictionary.registerOre("oreDiamond", oreDiamond);
		GameRegistry.addSmelting(oreDiamond.blockID, new ItemStack(Item.diamond), 1.0F);
		
		oreIron = new BlockDragonsReachOre(DragonsReachConfig.oreIronID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("DragonoreIron");
		registerBlock(oreIron, "Iron Ore");
		OreDictionary.registerOre("oreIron", oreIron);
		GameRegistry.addSmelting(oreIron.blockID, new ItemStack(Item.ingotIron), 0.7F);
		
		oreGold = new BlockDragonsReachOre(DragonsReachConfig.oreGoldID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("DragonoreGold");
		registerBlock(oreGold, "Gold Ore");
		OreDictionary.registerOre("oreGold", oreGold);
		GameRegistry.addSmelting(oreGold.blockID, new ItemStack(Item.ingotGold), 1.0F);
		
		//Removed till a form of Magic is added
		//oreBloodstone = new BlockDragonsReachOre(DragonsReachConfig.oreBloodstoneID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setLightValue(0.3F).setUnlocalizedName("DragonoreBloodstone");
		//registerBlock(oreBloodstone, "Bloodstone Ore");
		//OreDictionary.registerOre("oreBloodstone", oreBloodstone);
		//GameRegistry.addSmelting(oreBloodstone.blockID, new ItemStack(Item.ingotBloodstone), 1.0F);
		
		blackWoodLog = new BlockDragonsReachLog(2222).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("Dragonlog");
		registerBlock(blackWoodLog, "BlackWood Log");
		OreDictionary.registerOre("logWood", blackWoodLog);
		
		blackWoodPlanks = new BlockDragonsReach(2223, Material.wood).setCreativeTab(CreativeTabs.tabBlock).setHardness(2.0F).setResistance(5.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("Dragonwood");
		registerBlock(blackWoodPlanks, "BlackWood Planks");
		OreDictionary.registerOre("plankWood", blackWoodPlanks);
		
		blackWoodLeaves = new BlockDragonsReachLeaves(2224).setCreativeTab(CreativeTabs.tabBlock).setUnlocalizedName("Dragonleaves");
		registerBlock(blackWoodLeaves, "BlackWood Leaves");
	}
	
	/**
	 * Registers the Block with both GameRegistry and LanguageRegistry
	 * @param block
	 * @param name
	 */
	public static void registerBlock(Block block, String name)
	{
		GameRegistry.registerBlock(block, ItemBlock.class, name, DragonsReachMod.modID);
		LanguageRegistry.addName(block, name);
	}
}