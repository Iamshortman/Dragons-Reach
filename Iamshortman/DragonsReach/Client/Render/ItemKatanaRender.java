package Iamshortman.DragonsReach.Client.Render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;

public class ItemKatanaRender implements IItemRenderer
{

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		if(type == type.EQUIPPED)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		if (type == type.EQUIPPED )
		{
			
			//Yaw
			GL11.glRotatef(-45, 0, 1, 0);
			//Pitch
			GL11.glRotatef(-20.0F, 0, 0, 1);
			//Roll
			GL11.glRotatef(90.0F, 1, 0, 0);
			
			GL11.glTranslatef(1.2F, 0.0F, 0.0F);
			GL11.glTranslatef(0.0F, 0.0F, -0.83F);
		}
		FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/Dragon'sReach/textures/items/SwordUV.png");
		float scale = 3.0F;
		GL11.glScalef(scale - 1.0F, scale, scale);
		AdvancedModelLoader.loadModel("/mods/Dragon'sReach/models/Katana.obj").renderAll();
	}

}
