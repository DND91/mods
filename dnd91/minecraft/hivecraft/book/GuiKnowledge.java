package mods.dnd91.minecraft.hivecraft.book;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.stats.Achievement;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class GuiKnowledge extends Gui {
	private Minecraft theGame;
	
	private int knowledgeWindowWidth;
	private int knowledgeWindowHeight;
	
	private String knowledgeGetLocalText;
	private String knowledgeStatName;
	
	private Knowledge theKnowledge;
	private long knowledgeTime;
	
	private RenderItem itemRender;
	private boolean haveKnowledge;
	
	public GuiKnowledge(Minecraft par1Minecraft)
    {
        this.theGame = par1Minecraft;
        this.itemRender = new RenderItem();
    }
	
	public void queueTakenAchievement(Knowledge know)
    {
        this.knowledgeGetLocalText = StatCollector.translateToLocal("knowledge.get");
        this.knowledgeStatName = know.getTitle();
        this.knowledgeTime = Minecraft.getSystemTime();
        this.theKnowledge = know;
        this.haveKnowledge = false;
    }
	
	public void queueAchievementInformation(Knowledge know)
    {
        this.knowledgeGetLocalText = know.getTitle();
        this.knowledgeStatName = know.getDesc();
        this.knowledgeTime = Minecraft.getSystemTime() - 2500L;
        this.theKnowledge = know;
        this.haveKnowledge = true;
    }
	
	private void updateKnowledgeWindowScale()
    {
        GL11.glViewport(0, 0, this.theGame.displayWidth, this.theGame.displayHeight);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        this.knowledgeWindowWidth = this.theGame.displayWidth;
        this.knowledgeWindowHeight = this.theGame.displayHeight;
        ScaledResolution scaledresolution = new ScaledResolution(this.theGame.gameSettings, this.theGame.displayWidth, this.theGame.displayHeight);
        this.knowledgeWindowWidth = scaledresolution.getScaledWidth();
        this.knowledgeWindowHeight = scaledresolution.getScaledHeight();
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, (double)this.knowledgeWindowWidth, (double)this.knowledgeWindowHeight, 0.0D, 1000.0D, 3000.0D);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
    }
	
	public void updateKnowledgeWindow()
    {
        if (this.theKnowledge != null && this.knowledgeTime != 0L)
        {
            double d0 = (double)(Minecraft.getSystemTime() - this.knowledgeTime) / 3000.0D;

            if (!this.haveKnowledge && (d0 < 0.0D || d0 > 1.0D))
            {
                this.knowledgeTime = 0L;
            }
            else
            {
                this.updateKnowledgeWindowScale();
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glDepthMask(false);
                double d1 = d0 * 2.0D;

                if (d1 > 1.0D)
                {
                    d1 = 2.0D - d1;
                }

                d1 *= 4.0D;
                d1 = 1.0D - d1;

                if (d1 < 0.0D)
                {
                    d1 = 0.0D;
                }

                d1 *= d1;
                d1 *= d1;
                int i = this.knowledgeWindowWidth - 160;
                int j = 0 - (int)(d1 * 36.0D);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                this.theGame.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/gui/KnowledgesBG.png");
                GL11.glDisable(GL11.GL_LIGHTING);
                this.drawTexturedModalRect(i, j, 96, 202, 160, 32);

                if (this.haveKnowledge)
                {
                    this.theGame.fontRenderer.drawSplitString(this.knowledgeStatName, i + 30, j + 7, 120, -1);
                }
                else
                {
                    this.theGame.fontRenderer.drawString(this.knowledgeGetLocalText, i + 30, j + 7, -256);
                    this.theGame.fontRenderer.drawString(this.knowledgeStatName, i + 30, j + 18, -1);
                }

                RenderHelper.enableGUIStandardItemLighting();
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glEnable(GL12.GL_RESCALE_NORMAL);
                GL11.glEnable(GL11.GL_COLOR_MATERIAL);
                GL11.glEnable(GL11.GL_LIGHTING);
                this.itemRender.renderItemAndEffectIntoGUI(this.theGame.fontRenderer, this.theGame.renderEngine, this.theKnowledge.theItemStack, i + 8, j + 8);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDepthMask(true);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                
            }
        }
        this.theGame.renderEngine.bindTexture("/gui/icons.png");
    }
	
}
