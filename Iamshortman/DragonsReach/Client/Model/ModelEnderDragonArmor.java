package Iamshortman.DragonsReach.Client.Model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelEnderDragonArmor extends ModelBiped
{
	public ModelRenderer wing;
	public static final float radPerDeg = 0.0174532925F;
	
	public ModelEnderDragonArmor(float f, int slot)
	{
		super(f);
		this.wing = new ModelRenderer(this,0,0);
		this.wing.addBox(0.0F, 0.0F, 0.0F, 16, 2, 2,f);
		this.wing.setRotationPoint(1.0F, 2.0F, 2.0F);
		
		ModelRenderer wingArm1 = new ModelRenderer(this, 0, 0);
		wingArm1.addBox(6F, 0F, 0.5F, 1, 8, 1, f);
		this.wing.addChild(wingArm1);
		
		ModelRenderer wingArm2 = new ModelRenderer(this, 0, 0);
		wingArm2.addBox(15F, 0F, 0.5F, 1, 8, 1, f);
		wingArm2.rotateAngleZ = 0 * radPerDeg;
		this.wing.addChild(wingArm2);
		
		this.bipedHead.showModel = false;
		this.bipedHeadwear.showModel = false;
		this.bipedBody.showModel = false;
		this.bipedRightArm.showModel = false;
		this.bipedLeftArm.showModel = false;
		this.bipedRightLeg.showModel = false;
		this.bipedLeftLeg.showModel = false;
		
		if(slot == 0)
		{
			ModelRenderer horn = new ModelRenderer(this, 0, 0);
			horn.addBox(2, -10.5F, -1.5F, 1, 2, 3, f);
			
			ModelRenderer horn1 = new ModelRenderer(this, 0, 0);
			horn.addBox(-3, -10.5F, -1.5F, 1, 2, 3, f);
			
			ModelRenderer mouth = new ModelRenderer(this, 0, 16);
			mouth.addBox(-3.5F, -4, -6, 7, 4, 2);
			
			this.bipedHead.showModel = true;
			this.wing.showModel = false;
			this.bipedHead.addChild(horn);
			this.bipedHead.addChild(horn1);
			this.bipedHead.addChild(mouth);
		}
		else if (slot == 1)
		{
			this.bipedBody.showModel = true;
			//this.bipedRightArm.showModel = true;
			//this.bipedLeftArm.showModel = true;
			this.wing.showModel = true;
		}

	}
	
	@Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.wing.render(par7);
		//GL11.glScalef(-1.0F, 1.0F, 1.0F);
		//this.wing.render(par7);
		//GL11.glScalef(-1.0F, 1.0F, 1.0F);
    }
	
    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
    	super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
    	if(!par7Entity.onGround && !this.isSneak)
    	{
        	
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
        		this.wing.rotateAngleZ = -25 * radPerDeg;
        		this.wing.rotateAngleY = -5 * radPerDeg;
    		}
    	}
    }
}
