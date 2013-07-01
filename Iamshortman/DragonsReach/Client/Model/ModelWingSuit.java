package Iamshortman.DragonsReach.Client.Model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelWingSuit extends ModelBiped
{
	public ModelRenderer wing;
	public static final float radPerDeg = 0.0174532925F;
	
	public ModelWingSuit()
	{
		this(0.0F);
	}

	public ModelWingSuit(float f)
	{
		super(f, 0.0F, 64, 32);
		wing = new ModelRenderer(this,0,0);
		wing.addBox(1.5F, 0.0F, 2.0F, 8, 16, 0);
	}
	
	@Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
    	super.render(par1Entity, par2, par3, par4, par5, par6, par7);
    	this.setTexture("/mods/Dragon'sReach/textures/wings/leatherWing.png");
    	this.wing.render(par7);
    	GL11.glScalef(-1.0F, 1.0F, 1.0F);
    	this.wing.render(par7);
    	GL11.glScalef(-1.0F, 1.0F, 1.0F);
    	Minecraft.getMinecraft().renderEngine.resetBoundTexture();
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
    	super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
    	if(!par7Entity.onGround && !this.isSneak)
    	{
        	this.wing.rotateAngleZ = -25 * radPerDeg;
        	this.wing.rotateAngleX = 25 * radPerDeg;
        	this.wing.rotateAngleY = -25 * radPerDeg;
        	this.wing.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * 0.20F * 0.5F) + (30 * radPerDeg);
        	this.wing.rotateAngleY = (MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * 0.20F * 0.5F) + (-25 * radPerDeg);
    	}
    	else
    	{
    		if(this.isSneak)
    	    {
    	    		this.wing.rotateAngleX = 0.5F;
            		this.wing.rotateAngleY = -30 * radPerDeg;
    	    }
    		else
    		{
        		this.wing.rotateAngleY = -40 * radPerDeg;
    		}
    	}
    }
    
    public void setTexture(String str)
    {
    	Minecraft.getMinecraft().renderEngine.bindTexture(str);
    }
}
