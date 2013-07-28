package Iamshortman.DragonsReach.Common.Block;

import Iamshortman.DragonsReach.DragonsReachMod;
import Iamshortman.DragonsReach.Common.Block.BlockDragonsReachDirt;
import Iamshortman.DragonsReach.Common.Block.BlockDragonsReachGrass;
import Iamshortman.DragonsReach.Common.Block.BlockDragonsReachOre;
import Iamshortman.DragonsReach.Common.Block.BlockDragonsReachPortal;
import Iamshortman.DragonsReach.Common.Block.BlockDragonsReachStone;
import Iamshortman.DragonsReach.Common.Forge.DragonsReachConfig;
import Iamshortman.DragonsReach.Common.Items.ItemBlockRotatable;
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
import net.minecraft.util.Icon;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @Author Iamshortman
 * File: BlockDragonsReach.java
 * Created: Jul 27, 2013, 9:01:33 PM
 * Description: The basic block class for all of Dragon's Reach, contains all the registering and texture functions needed for blocks.
 */
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
		//For Vanilla Named Blocks so that names do not become messed up i add Dragon_ to the front.
		this.blockIcon = registerIconForMod(par1IconRegister, this.getUnlocalizedName().substring(12));
	}
	
	/**
	 * Registers Icons to the Dragon's Reach file
	 * @param par1IconRegister
	 * @param the file name
	 * @return the icon
	 */
	public static Icon registerIconForMod(IconRegister par1IconRegister, String name)
	{
		return par1IconRegister.registerIcon("dragonsreach:" + name);
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
	public static Block blackWoodSapling;
	
	public static Block rainbowBridgeEmitter;
	
	public static void initBlocks()
	{
		portal = new BlockDragonsReachPortal(DragonsReachConfig.portalID).setUnlocalizedName("Dragon_portal");
		registerBlock(portal, "Dragon's Reach Portal");
		
		grass = new BlockDragonsReachGrass(DragonsReachConfig.grassID).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Dragon_grass");
		registerBlock(grass, "Dragon's Reach Grass");
		
		dirt = new BlockDragonsReachDirt(DragonsReachConfig.dirtID).setHardness(0.5F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("Dragon_dirt");
		registerBlock(dirt, "Dragon's Reach Dirt");
		
		stone = new BlockDragonsReachStone(DragonsReachConfig.stoneID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Dragon_stone");
		registerBlock(stone, "Dragon's Reach Stone");
		
		oreCoal = new BlockDragonsReachOre(DragonsReachConfig.oreCoalID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Dragon_oreCoal");
		registerBlock(oreCoal, "Coal Ore");
		OreDictionary.registerOre("oreCoal", oreCoal);
		GameRegistry.addSmelting(oreCoal.blockID, new ItemStack(Item.coal), 0.1F);
		
		oreDiamond = new BlockDragonsReachOre(DragonsReachConfig.oreDiamondID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Dragon_oreDiamond");
		registerBlock(oreDiamond, "Diamond Ore");
		OreDictionary.registerOre("oreDiamond", oreDiamond);
		GameRegistry.addSmelting(oreDiamond.blockID, new ItemStack(Item.diamond), 1.0F);
		
		oreIron = new BlockDragonsReachOre(DragonsReachConfig.oreIronID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Dragon_oreIron");
		registerBlock(oreIron, "Iron Ore");
		OreDictionary.registerOre("oreIron", oreIron);
		GameRegistry.addSmelting(oreIron.blockID, new ItemStack(Item.ingotIron), 0.7F);
		
		oreGold = new BlockDragonsReachOre(DragonsReachConfig.oreGoldID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Dragon_oreGold");
		registerBlock(oreGold, "Gold Ore");
		OreDictionary.registerOre("oreGold", oreGold);
		GameRegistry.addSmelting(oreGold.blockID, new ItemStack(Item.ingotGold), 1.0F);
		
		//Removed till a form of Magic is added
		//oreBloodstone = new BlockDragonsReachOre(DragonsReachConfig.oreBloodstoneID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setLightValue(0.3F).setUnlocalizedName("DragonoreBloodstone");
		//registerBlock(oreBloodstone, "Bloodstone Ore");
		//OreDictionary.registerOre("oreBloodstone", oreBloodstone);
		//GameRegistry.addSmelting(oreBloodstone.blockID, new ItemStack(Item.ingotBloodstone), 1.0F);
		
		blackWoodLog = new BlockDragonsReachLog(2222).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("Dragon_log");
		registerBlock(blackWoodLog, "BlackWood Log");
		OreDictionary.registerOre("logWood", blackWoodLog);
		
		blackWoodPlanks = new BlockDragonsReach(2223, Material.wood).setCreativeTab(CreativeTabs.tabBlock).setHardness(2.0F).setResistance(5.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("Dragon_wood");
		registerBlock(blackWoodPlanks, "BlackWood Planks");
		OreDictionary.registerOre("plankWood", blackWoodPlanks);
		
		blackWoodLeaves = new BlockDragonsReachLeaves(2224).setCreativeTab(CreativeTabs.tabBlock).setHardness(0.2F).setLightOpacity(1).setStepSound(soundGrassFootstep).setUnlocalizedName("Dragon_leaves");
		registerBlock(blackWoodLeaves, "BlackWood Leaves");
		
		blackWoodSapling = new BlockDragonsReachSapling(2225).setHardness(0.0F).setStepSound(soundGrassFootstep).setUnlocalizedName("Dragon_sapling");
		registerBlock(blackWoodSapling, "BlackWood Sapling");
		
		rainbowBridgeEmitter = new BlockDragonsReachRainbowBridgeEmitter(2226).setUnlocalizedName("rainbowBridgeEmitter");
		registerBlock(rainbowBridgeEmitter, ItemBlockRotatable.class, "Rainbow Bridge Emitter");
	}
	
	/**
	 * Registers the Block with both GameRegistry and LanguageRegistry
	 * @param block
	 * @param name
	 */
	public static void registerBlock(Block block, String name)
	{
		registerBlock(block, ItemBlock.class, name);
	}

	/**
	 * Registers the Block with both GameRegistry and LanguageRegistry
	 * @param block
	 * @param name
	 * @param itemBlock Class
	 */
	public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlock, String name)
	{
		GameRegistry.registerBlock(block, itemBlock, name, DragonsReachMod.modID);
		LanguageRegistry.addName(block, name);
	}
}
