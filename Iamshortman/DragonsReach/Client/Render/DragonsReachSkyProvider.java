package Iamshortman.DragonsReach.Client.Render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.ObfuscationReflectionHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.IRenderHandler;


/**
 * @Author Iamshortman
 * File: DragonsReachSkyProvider.java
 * Created: Jul 27, 2013, 9:01:33 PM
 * Description: Renders the custom sky in the Dragon's Reach. 
 */
public class DragonsReachSkyProvider extends IRenderHandler
{
	private int starGLCallList;
	private int glSkyList;
	private int glSkyList2;
	
	public DragonsReachSkyProvider()
	{
		RenderGlobal global =  Minecraft.getMinecraft().renderGlobal;
		this.starGLCallList = ObfuscationReflectionHelper.getPrivateValue(RenderGlobal.class, global, "starGLCallList");
		this.glSkyList = ObfuscationReflectionHelper.getPrivateValue(RenderGlobal.class, global, "glSkyList");
		this.glSkyList2 = ObfuscationReflectionHelper.getPrivateValue(RenderGlobal.class, global, "glSkyList2");
	}
	
	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc)
	{	
        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glPushMatrix();
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderHelper.disableStandardItemLighting();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        
        float f4 = 1.0F;
        float f18 = world.getStarBrightness(partialTicks) * f4;

        if (f18 > 0.0F)
        {
            GL11.glColor4f(f18, f18, f18, f18);
            GL11.glCallList(this.starGLCallList); 
        }  
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
		
        
        
        Vec3 vec3 = world.getSkyColor(mc.renderViewEntity, partialTicks);
        float f1 = (float)vec3.xCoord;
        float f2 = (float)vec3.yCoord;
        float f3 = (float)vec3.zCoord;

        if (mc.gameSettings.anaglyph)
        {
            float f5 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
            float f6 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
            f4 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
            f1 = f5;
            f2 = f6;
            f3 = f4;
        }
        GL11.glColor3f(f1, f2, f3);
		
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderHelper.disableStandardItemLighting();
        GL11.glDepthMask(false);
        Tessellator tessellator = Tessellator.instance;

        for (int i = 0; i < 6; ++i)
        {
            GL11.glPushMatrix();

            if (i == 1)
            {
                GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (i == 2)
            {
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (i == 3)
            {
                GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
            }

            if (i == 4)
            {
                GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            }

            if (i == 5)
            {
                GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
            }

            tessellator.startDrawingQuads();
            tessellator.addVertex(-100.0D, -100.0D, -100.0D);
            tessellator.addVertex(-100.0D, -100.0D, 100.0D);
            tessellator.addVertex(100.0D, -100.0D, 100.0D);
            tessellator.addVertex(100.0D, -100.0D, -100.0D);
            tessellator.draw();
            GL11.glPopMatrix();
        }

        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glPopMatrix();
	}
}
