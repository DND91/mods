package mods.dnd91.minecraft.hivecraft.book;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.dnd91.minecraft.hivecraft.HiveCraft;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSmallButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.AchievementPage;

@SideOnly(Side.CLIENT)
public class GuiKnowledges extends GuiScreen{
	private static final int guiMapTop = KnowledgeAppedix.minDisplayColumn * 24 - 112;
	private static final int guiMapLeft = KnowledgeAppedix.minDisplayRow * 24 - 112;
	
	private static final int guiMapBottom = KnowledgeAppedix.maxDisplayColumn * 24 - 77;
	private static final int guiMapRight = KnowledgeAppedix.maxDisplayRow * 24 - 77;
	
	protected int knowledgePaneWidth = 256;
	protected int knowledgePaneHeight = 202;
	
	protected int mouseX = 0;
	protected int mouseY = 0;
	protected double field_74117_m;
	protected double field_74115_n;
	
	protected double guiMapX;
	protected double guiMapY;
	protected double field_74124_q;
	protected double field_74123_r;
	
	private int isMouseButtonDown = 0;
	private int lastMouseButtonDown = 0;
	private NBTTagCompound compound;
	
	private int currentPage = -1;
	private GuiSmallButton button;
	
	private EntityPlayer player;
	
	public GuiKnowledges(NBTTagCompound comp, EntityPlayer p){
		compound = comp;
		short short1 = 141;
        short short2 = 141;
		this.field_74117_m = this.guiMapX = this.field_74124_q = (double)(KnowledgeAppedix.old_tome.displayColumn * 24 - short1 / 2 - 12);
        this.field_74115_n = this.guiMapY = this.field_74123_r = (double)(KnowledgeAppedix.old_tome.displayRow * 24 - short2 / 2);
        player = p;
	}
	
	public void initGui(){
		this.buttonList.clear();
		this.buttonList.add(new GuiSmallButton(1, this.width / 2 + 24, this.height / 2 + 74, 80, 20, StatCollector.translateToLocal("gui.done")));
	}
	
	protected void actionPerformed(GuiButton button){
		if (button.id == 1)
        {
            this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
        }
		super.actionPerformed(button);
	}
	
	protected void keyTyped(char par1, int par2)
    {
        if (par2 == this.mc.gameSettings.keyBindInventory.keyCode)
        {
            this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
        }
        else
        {
            super.keyTyped(par1, par2);
        }
    }
	
	public void drawScreen(int par1, int par2, float par3)
    {
        if (Mouse.isButtonDown(0))
        {
            int k = (this.width - this.knowledgePaneWidth) / 2;
            int l = (this.height - this.knowledgePaneHeight) / 2;
            int i1 = k + 8;
            int j1 = l + 17;

            if ((this.isMouseButtonDown == 0 || this.isMouseButtonDown == 1) && par1 >= i1 && par1 < i1 + 224 && par2 >= j1 && par2 < j1 + 155)
            {
                if (this.isMouseButtonDown == 0)
                {
                    this.isMouseButtonDown = 1;
                    
                }
                else
                {
                    this.guiMapX -= (double)(par1 - this.mouseX);
                    this.guiMapY -= (double)(par2 - this.mouseY);
                    this.field_74124_q = this.field_74117_m = this.guiMapX;
                    this.field_74123_r = this.field_74115_n = this.guiMapY;
                }

                this.mouseX = par1;
                this.mouseY = par2;
            }

            if (this.field_74124_q < (double)guiMapTop)
            {
                this.field_74124_q = (double)guiMapTop;
            }

            if (this.field_74123_r < (double)guiMapLeft)
            {
                this.field_74123_r = (double)guiMapLeft;
            }

            if (this.field_74124_q >= (double)guiMapBottom)
            {
                this.field_74124_q = (double)(guiMapBottom - 1);
            }

            if (this.field_74123_r >= (double)guiMapRight)
            {
                this.field_74123_r = (double)(guiMapRight - 1);
            }
        }
        else
        {
            this.isMouseButtonDown = 0;
        }

        this.drawDefaultBackground();
        this.genKonwledgeBackground(par1, par2, par3);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        this.drawTitle();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }
	
	 public void updateScreen()
	    {
	        this.field_74117_m = this.guiMapX;
	        this.field_74115_n = this.guiMapY;
	        double d0 = this.field_74124_q - this.guiMapX;
	        double d1 = this.field_74123_r - this.guiMapY;

	        if (d0 * d0 + d1 * d1 < 4.0D)
	        {
	            this.guiMapX += d0;
	            this.guiMapY += d1;
	        }
	        else
	        {
	            this.guiMapX += d0 * 0.85D;
	            this.guiMapY += d1 * 0.85D;
	        }
	    }
	
	 protected void drawTitle()
	 {
	    int i = (this.width - this.knowledgePaneWidth) / 2;
	    int j = (this.height - this.knowledgePaneHeight) / 2;
	    this.fontRenderer.drawString("The Hive Appedix", i + 15, j + 5, 4210752);
	 }
	 
	 protected void genKonwledgeBackground(int par1, int par2, float par3)
	    {
	        int k = MathHelper.floor_double(this.field_74117_m + (this.guiMapX - this.field_74117_m) * (double)par3);
	        int l = MathHelper.floor_double(this.field_74115_n + (this.guiMapY - this.field_74115_n) * (double)par3);

	        if (k < guiMapTop)
	        {
	            k = guiMapTop;
	        }

	        if (l < guiMapLeft)
	        {
	            l = guiMapLeft;
	        }

	        if (k >= guiMapBottom)
	        {
	            k = guiMapBottom - 1;
	        }

	        if (l >= guiMapRight)
	        {
	            l = guiMapRight - 1;
	        }

	        int i1 = (this.width - this.knowledgePaneWidth) / 2;
	        int j1 = (this.height - this.knowledgePaneHeight) / 2;
	        int k1 = i1 + 16;
	        int l1 = j1 + 17;
	        this.zLevel = 0.0F;
	        GL11.glDepthFunc(GL11.GL_GEQUAL);
	        GL11.glPushMatrix();
	        GL11.glTranslatef(0.0F, 0.0F, -200.0F);
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        GL11.glDisable(GL11.GL_LIGHTING);
	        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
	        this.mc.renderEngine.bindTexture("/terrain.png");
	        int i2 = k + 288 >> 4;
	        int j2 = l + 288 >> 4;
	        int k2 = (k + 288) % 16;
	        int l2 = (l + 288) % 16;
	        Random random = new Random();
	        int i3;
	        int j3;
	        int k3;

	        for (i3 = 0; i3 * 16 - l2 < 155; ++i3)
	        {
	            float f1 = 0.6F - (float)(j2 + i3) / 25.0F * 0.3F;
	            GL11.glColor4f(f1, f1, f1, 1.0F);

	            for (k3 = 0; k3 * 16 - k2 < 224; ++k3)
	            {
	                random.setSeed((long)(1234 + i2 + k3));
	                random.nextInt();
	                j3 = random.nextInt(1 + j2 + i3) + (j2 + i3) / 2;
	                Icon icon = Block.sand.getIcon(0, 0);

	                if (j3 <= 37 && j2 + i3 != 35)
	                {
	                    if (j3 == 22)
	                    {
	                        if (random.nextInt(2) == 0)
	                        {
	                            icon = Block.sand.getIcon(0, 0);
	                        }
	                        else
	                        {
	                            icon = Block.gravel.getIcon(0, 0);
	                        }
	                    }
	                    else if (j3 == 10)
	                    {
	                        icon = Block.wood.getIcon(2, 0);
	                    }
	                    else if (j3 == 8)
	                    {
	                        icon = Block.wood.getIcon(0, 0);
	                    }
	                    else if (j3 > 4)
	                    {
	                        icon = Block.dirt.getIcon(0, 0);
	                    }
	                    else if (j3 > 0)
	                    {
	                        icon = Block.grass.getIcon(1, 0);
	                    }
	                }
	                else
	                {
	                    icon = Block.stone.getIcon(0, 0);
	                }

	                this.drawTexturedModelRectFromIcon(k1 + k3 * 16 - k2, l1 + i3 * 16 - l2, icon, 16, 16);
	            }
	        }

	        GL11.glEnable(GL11.GL_DEPTH_TEST);
	        GL11.glDepthFunc(GL11.GL_LEQUAL);
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
	        int l3;
	        int i4;
	        int j4;

	        List<Knowledge> knowledgeList = KnowledgeAppedix.knowledgeList;
	        for (i3 = 0; i3 < knowledgeList.size(); ++i3)
	        {
	        	Knowledge knowledge = knowledgeList.get(i3);

	            if (knowledge.parentKnowledge != null && knowledgeList.contains(knowledge.parentKnowledge))
	            {
	                k3 = knowledge.displayColumn * 24 - k + 11 + k1;
	                j3 = knowledge.displayRow * 24 - l + 11 + l1;
	                j4 = knowledge.parentKnowledge.displayColumn * 24 - k + 11 + k1;
	                l3 = knowledge.parentKnowledge.displayRow * 24 - l + 11 + l1;
	                
	                
	                
	                
	                boolean flag = KnowledgeAppedix.hasKnowledgeUnlocked(compound, knowledge);
	                boolean flag1 = KnowledgeAppedix.canUnlockKnowledge(compound, knowledge);
	                
	                i4 = Math.sin((double)(Minecraft.getSystemTime() % 600L) / 600.0D * Math.PI * 2.0D) > 0.6D ? 255 : 130;
	                int k4 = -16777216;

	                if (flag)
	                {
	                    k4 = -9408400;
	                }
	                else if (flag1)
	                {
	                    k4 = 65280 + (i4 << 24);
	                }

	                this.drawHorizontalLine(k3, j4, j3, k4);
	                this.drawVerticalLine(j4, j3, l3, k4);
	            }
	        }

	        Knowledge knowledge1 = null;
	        RenderItem renderitem = new RenderItem();
	        RenderHelper.enableGUIStandardItemLighting();
	        GL11.glDisable(GL11.GL_LIGHTING);
	        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
	        int l4;
	        int i5;

	        for (k3 = 0; k3 < knowledgeList.size(); ++k3)
	        {
	        	Knowledge knowledge2 = (Knowledge)knowledgeList.get(k3);
	            j4 = knowledge2.displayColumn * 24 - k;
	            l3 = knowledge2.displayRow * 24 - l;

	            if (j4 >= -24 && l3 >= -24 && j4 <= 224 && l3 <= 155)
	            {
	                float f2;
	                
	                

	                if (KnowledgeAppedix.hasKnowledgeUnlocked(compound, knowledge2))
	                {
	                    f2 = 1.0F;
	                    GL11.glColor4f(f2, f2, f2, 1.0F);
	                }
	                else if (KnowledgeAppedix.canUnlockKnowledge(compound, knowledge2))
	                {
	                    f2 = Math.sin((double)(Minecraft.getSystemTime() % 600L) / 600.0D * Math.PI * 2.0D) < 0.6D ? 0.6F : 0.8F;
	                    GL11.glColor4f(f2, f2, f2, 1.0F);
	                }
	                else
	                {
	                    f2 = 0.3F;
	                    GL11.glColor4f(f2, f2, f2, 1.0F);
	                }

	                this.mc.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/gui/KnowledgesBG.png");
	                i5 = k1 + j4;
	                l4 = l1 + l3;

	                if (knowledge2.getSpecial())
	                {
	                    this.drawTexturedModalRect(i5 - 2, l4 - 2, 26, 202, 26, 26);
	                }
	                else
	                {
	                    this.drawTexturedModalRect(i5 - 2, l4 - 2, 0, 202, 26, 26);
	                }

	                if (!KnowledgeAppedix.canUnlockKnowledge(compound, knowledge2))
	                {
	                    float f3 = 0.1F;
	                    GL11.glColor4f(f3, f3, f3, 1.0F);
	                    renderitem.renderWithColor = false;
	                }

	                GL11.glEnable(GL11.GL_LIGHTING);
	                GL11.glEnable(GL11.GL_CULL_FACE);
	                renderitem.renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, knowledge2.theItemStack, i5 + 3, l4 + 3);
	                GL11.glDisable(GL11.GL_LIGHTING);

	                if (!KnowledgeAppedix.canUnlockKnowledge(compound, knowledge2))
	                {
	                    renderitem.renderWithColor = true;
	                }

	                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

	                if (par1 >= k1 && par2 >= l1 && par1 < k1 + 224 && par2 < l1 + 155 && par1 >= i5 && par1 <= i5 + 22 && par2 >= l4 && par2 <= l4 + 22)
	                {
	                    knowledge1 = knowledge2;
	                }
	            }
	        }

	        GL11.glDisable(GL11.GL_DEPTH_TEST);
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        this.mc.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/gui/KnowledgesBG.png");
	        this.drawTexturedModalRect(i1, j1, 0, 0, this.knowledgePaneWidth, this.knowledgePaneHeight);
	        GL11.glPopMatrix();
	        this.zLevel = 0.0F;
	        GL11.glDepthFunc(GL11.GL_LEQUAL);
	        GL11.glDisable(GL11.GL_DEPTH_TEST);
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        super.drawScreen(par1, par2, par3);

	        if (knowledge1 != null)
	        {
	            String s = knowledge1.getTitle();
	            String s1 = knowledge1.getDesc();
	            j4 = par1 + 12;
	            l3 = par2 - 4;
	            
	            if (KnowledgeAppedix.canUnlockKnowledge(compound, knowledge1))
	            {
	                i5 = Math.max(this.fontRenderer.getStringWidth(s), 120);
	                l4 = this.fontRenderer.splitStringWidth(s1, i5);

	                if (KnowledgeAppedix.hasKnowledgeUnlocked(compound, knowledge1))
	                {
	                    l4 += 12;
	                }

	                this.drawGradientRect(j4 - 3, l3 - 3, j4 + i5 + 3, l3 + l4 + 3 + 12, -1073741824, -1073741824);
	                this.fontRenderer.drawSplitString(s1, j4, l3 + 12, i5, -6250336);

	                if (KnowledgeAppedix.hasKnowledgeUnlocked(compound, knowledge1))
	                {
	                	
	                	if(lastMouseButtonDown == 1 && this.isMouseButtonDown == 0 && this.mc.theWorld.isRemote){
	                		System.out.println("Mouse up over a knowledge!");
	                		player.openGui(HiveCraft.instance, knowledge1.myPage+500, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
	                	}
	                	lastMouseButtonDown = isMouseButtonDown;
	                    this.fontRenderer.drawStringWithShadow(StatCollector.translateToLocal("knowledge.taken"), j4, l3 + l4 + 4, -7302913);
	                }
	            }
	            else
	            {
	                i5 = Math.max(this.fontRenderer.getStringWidth(s), 120);
	                String s2 = StatCollector.translateToLocalFormatted("achievement.requires", new Object[] {knowledge1.parentKnowledge.getTitle()});
	                i4 = this.fontRenderer.splitStringWidth(s2, i5);
	                this.drawGradientRect(j4 - 3, l3 - 3, j4 + i5 + 3, l3 + i4 + 12 + 3, -1073741824, -1073741824);
	                this.fontRenderer.drawSplitString(s2, j4, l3 + 12, i5, -9416624);
	            }

	            this.fontRenderer.drawStringWithShadow(s, j4, l3, KnowledgeAppedix.canUnlockKnowledge(compound, knowledge1) ? (knowledge1.getSpecial() ? -128 : -1) : (knowledge1.getSpecial() ? -8355776 : -8355712));
	        }

	        GL11.glEnable(GL11.GL_DEPTH_TEST);
	        GL11.glEnable(GL11.GL_LIGHTING);
	        RenderHelper.disableStandardItemLighting();
	    }
	 
	 public boolean doesGuiPauseGame()
	 {
		 return true;
	 }
	
	 
}
