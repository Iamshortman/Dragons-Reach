package Iamshortman.DragonsReach;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import Iamshortman.DragonsReach.DragonsReachMod;
import Iamshortman.DragonsReach.Common.Block.BlockDragonsReachDirt;
import Iamshortman.DragonsReach.Common.Block.BlockDragonsReachGrass;
import Iamshortman.DragonsReach.Common.Block.BlockDragonsReachPortal;
import Iamshortman.DragonsReach.Common.Block.BlockDragonsReachStone;
import Iamshortman.DragonsReach.Common.Block.BlockDragonsReach;
import Iamshortman.DragonsReach.Common.Forge.DragonsReachCommonProxy;
import Iamshortman.DragonsReach.Common.Forge.DragonsReachConfig;
import Iamshortman.DragonsReach.Common.Items.ItemDragonsReach;
import Iamshortman.DragonsReach.Common.TileEntity.TileEntityRainbowBridgeEmitter;
import Iamshortman.DragonsReach.Common.World.WorldProviderDragonsReach;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = DragonsReachMod.modID, name = "Dragon's Reach", version = "1.5.2v1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, clientPacketHandlerSpec = @SidedPacketHandler(channels =
{ "DRMDamageArmor" }, packetHandler = Iamshortman.DragonsReach.Client.Network.ClientPacketHandler.class), serverPacketHandlerSpec = @SidedPacketHandler(channels =
{ "DRMDamageArmor" }, packetHandler = Iamshortman.DragonsReach.Common.Network.CommonPacketHandler.class))
/**
 * @Author Iamshortman
 * File: DragonsReachMod.java
 * Created: Jul 27, 2013, 9:01:33 PM
 * Description: The main mod file for Dragon's Reach.
 */
public class DragonsReachMod
{
	public static final String modID = "DragonsReach";

	@Instance(value = modID)
	public static DragonsReachMod Instance;

	@SidedProxy(clientSide = "Iamshortman.DragonsReach.Client.Forge.DragonsReachClientProxy", serverSide = "Iamshortman.DragonsReach.Common.Forge.DragonsReachCommonProxy")
	public static DragonsReachCommonProxy proxy;

	// World Stuff
	public static int dimension = 43;

	@EventHandler
	public void preInit(FMLPreInitializationEvent evt)
	{
		proxy.preInit(evt);
		DragonsReachConfig.Load(evt);
	}

	@EventHandler
	public void load(FMLInitializationEvent evt)
	{
		proxy.load(evt);
		proxy.RenderStuff();
		BlockDragonsReach.initBlocks();
		ItemDragonsReach.initItems();

		// Tile Entities Registering
		GameRegistry.registerTileEntity(TileEntityRainbowBridgeEmitter.class, "RainbowBridgeEmitter");

		// Removed Enchantments till i really do something with them
		// EnchantmentDragonsReach.initEnchantments();

		// Dimension Stuff
		DimensionManager.registerProviderType(dimension, WorldProviderDragonsReach.class, false);
		DimensionManager.registerDimension(dimension, dimension);

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent evt)
	{
		proxy.postInit(evt);
	}

	@EventHandler
	public void onServerStart(FMLServerStartedEvent evt)
	{

	}

}
