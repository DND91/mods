package mods.dnd91.minecraft.hivecraft.larva;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntitySpawnbubbelsFX extends EntityFX {
	float bubbleType = 0.0F;
	Random random = new Random();
	public EntitySpawnbubbelsFX(World par1World, double par2, double par4,
			double par6, double par8, double par10, double par12) {
		super(par1World, par2, par4, par6, par8, par10, par12);
		bubbleType = random.nextInt(6);
		motionX *= 0.005F;
		motionY *= 0.08F;
		motionZ *= 0.005F;
		this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
	}

	@Override
	public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
		RenderEngine renderEngine = FMLClientHandler.instance().getClient().renderEngine;
		par1Tessellator.draw();
		par1Tessellator.startDrawingQuads();
		
		renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/misc/SpawnpoolBubbels.png");
		
		float f6 = 0;
        float f7 = 0;
        float f8 = 15;
        float f9 = 15;
        float f10 = 0.1F * this.particleScale;

        f7 = 0.16666f*bubbleType; //Max U
        f6 = f7-0.16666f; //Min U
        
        f8 = 0; //Min V
        f9 = 1; //Max V
        
        //f10 = 1.0f; //Scale
        
        float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)par2 - interpPosX);
        float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)par2 - interpPosY);
        float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)par2 - interpPosZ);
        float f14 = 1.0F;
        par1Tessellator.setColorRGBA_F(this.particleRed * f14, this.particleGreen * f14, this.particleBlue * f14, this.particleAlpha);
        par1Tessellator.addVertexWithUV((double)(f11 - par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 - par5 * f10 - par7 * f10), (double)f7, (double)f9);
        par1Tessellator.addVertexWithUV((double)(f11 - par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 - par5 * f10 + par7 * f10), (double)f7, (double)f8);
        par1Tessellator.addVertexWithUV((double)(f11 + par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 + par5 * f10 + par7 * f10), (double)f6, (double)f8);
        par1Tessellator.addVertexWithUV((double)(f11 + par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 + par5 * f10 - par7 * f10), (double)f6, (double)f9);
        
        par1Tessellator.setBrightness(100);
        
        par1Tessellator.draw();
        FMLClientHandler.instance().getClient().renderEngine.bindTexture("/particles.png");
        par1Tessellator.startDrawingQuads();
    }

}
