package Iamshortman.DragonsReach.Client.Render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;

public class ItemKatanaRender implements IItemRenderer
{
	private Minecraft mc;
	public ItemKatanaRender()
	{
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		if(type == type.EQUIPPED || type == type.ENTITY)
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
		if (type == type.EQUIPPED)
		{
			EntityLiving living = (EntityLiving) data[1];
			if(living == mc.thePlayer && mc.gameSettings.thirdPersonView == 0)
			{
				
				GL11.glTranslatef(1.0F, 0.0F, 0.6F);
				
				//Yaw
				GL11.glRotatef(-45, 0, 1, 0);
				//Pitch
				GL11.glRotatef(-90.0F, 0, 0, 1);
				//Roll
				GL11.glRotatef(180.0F, 1, 0, 0);
				
				float scale = 3.0F;
				GL11.glScalef(scale - 1.0F, scale, scale);
				this.Render();
			}
			else
			{
			//Yaw
			GL11.glRotatef(-45, 0, 1, 0);
			//Pitch
			GL11.glRotatef(-20.0F, 0, 0, 1);
			//Roll
			GL11.glRotatef(90.0F, 1, 0, 0);
			
			GL11.glTranslatef(1.2F, 0.0F, 0.0F);
			GL11.glTranslatef(0.0F, 0.0F, -0.83F);
			
			float scale = 3.0F;
			GL11.glScalef(scale - 1.0F, scale, scale);
			this.Render();
			}
		}
		else if(type == type.ENTITY)
		{
			GL11.glRotatef(-20.0F, 0, 0, 1);
			GL11.glRotatef(90.0F, 1, 0, 0);
			
			float scale = 3.0F;
			GL11.glScalef(scale - 1.0F, scale, scale);
			this.Render();
		}
	}

	private void Render()
	{
		FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/Dragon'sReach/textures/items/SwordaUV.png");
		AdvancedModelLoader.loadModel("/mods/Dragon'sReach/models/Katana.obj").renderAll();
	}
	
}
