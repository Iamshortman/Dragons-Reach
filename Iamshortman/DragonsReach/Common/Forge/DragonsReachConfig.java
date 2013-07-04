package Iamshortman.DragonsReach.Common.Forge;


import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class DragonsReachConfig
{
	//Blocks
	public static int portalID;
	public static int grassID;
	public static int dirtID;
	public static int stoneID;
	public static int oreCoalID;
	public static int oreDiamondID;
	public static int oreIronID;
	public static int oreGoldID;
	public static int oreBloodstoneID;
	
	//Items
	public static int enderStaffID;
	
	public static void Load(FMLPreInitializationEvent evt)
	{
		Configuration config = new Configuration(evt.getSuggestedConfigurationFile());

		config.load();
		
		//Blocks
		portalID = config.getBlock("portal", 3001).getInt();
		grassID = config.getBlock("grass", 3002).getInt();
		dirtID = config.getBlock("dirt", 3003).getInt();
		stoneID = config.getBlock("stone", 3004).getInt();
		oreCoalID = config.getBlock("oreCoal", 3005).getInt();
		oreDiamondID = config.getBlock("oreDiamond", 3006).getInt();
		oreIronID = config.getBlock("oreIron", 3007).getInt();
		oreGoldID = config.getBlock("oreGold", 3008).getInt();
		oreBloodstoneID = config.getBlock("oreBloodstone", 3009).getInt();
		
		//Items
		enderStaffID = config.getItem("Ender Staff", 3060).getInt();
		
		config.save();
	}

}
