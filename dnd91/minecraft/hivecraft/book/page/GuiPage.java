package mods.dnd91.minecraft.hivecraft.book.page;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiParticle;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
public class GuiPage extends GuiScreen{
	private int currPage = 0;
	private Page cPage;
	
	private int pageImageWidth = 256;
    private int pageImageHeight = 192;
    
    private int updateCount = 0;
    
    private GuiButtonNextPage buttonNextPage;
    private GuiButtonNextPage buttonPreviousPage;
    private GuiButton buttonDone;
    
    private HiveBookFontRenderer hiveFontRenderer;
	
	public GuiPage(Page page){
		cPage = page;
	}
	
	@Override
	public void setWorldAndResolution(Minecraft minecraft, int par2, int par3)
    {
        this.guiParticles = new GuiParticle(minecraft);
        this.mc = minecraft;
        this.hiveFontRenderer = new HiveBookFontRenderer(minecraft, minecraft.gameSettings, "/font/default.png", mc.renderEngine, false);
        this.width = par2;
        this.height = par3;
        this.buttonList.clear();
        this.initGui();
    }
	
	public void updateScreen()
    {
        super.updateScreen();
        ++this.updateCount;
    }
	
	public void initGui()
    {
        this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);

        this.buttonList.add(this.buttonDone = new GuiButton(0, this.width / 2 - 100, 4 + this.pageImageHeight, 200, 20, StatCollector.translateToLocal("gui.done")));

        int i = (this.width - this.pageImageWidth) / 2;
        byte b0 = 2;
        this.buttonList.add(this.buttonNextPage = new GuiButtonNextPage(1, i + 210, b0 + 154, true));
        this.buttonList.add(this.buttonPreviousPage = new GuiButtonNextPage(2, i + 16, b0 + 154, false));
        this.updateButtons();
    }
	
	public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }
	
	private void updateButtons()
    {
        this.buttonNextPage.drawButton = (this.currPage < cPage.numberOfPages() - 1);
        this.buttonPreviousPage.drawButton = this.currPage > 0;
        this.buttonDone.drawButton = true;
    }
	
	protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id == 0)
            {
                this.mc.displayGuiScreen((GuiScreen)null);
            }
            else if (par1GuiButton.id == 1)
            {
                if (this.currPage < cPage.numberOfPages() - 1)
                {
                    ++this.currPage;
                }
            }
            else if (par1GuiButton.id == 2)
            {
                if (this.currPage > 0)
                {
                    --this.currPage;
                }
            }
            this.updateButtons();
        }
    }
	
	public void drawScreen(int par1, int par2, float par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/gui/book.png");
        int k = (int) (((this.width*1.2F) - (this.pageImageWidth*1.2F)) / 4);
        k = ((this.width - (this.pageImageWidth)) / 2);
        byte b0 = 2;
        //GL11.glScalef(1.2F, 1.0F, 1.0F);
        this.drawTexturedModalRect(k, b0, 0, 0, this.pageImageWidth, this.pageImageHeight);
        //GL11.glScalef(1.0F/1.2F, 1.0F, 1.0F);
        //k = ((this.width - (this.pageImageWidth)) / 2);
        String s;
        String s1;
        int l;
        s = String.format(StatCollector.translateToLocal("book.pageIndicator"), new Object[] {Integer.valueOf(this.currPage + 1), Integer.valueOf(cPage.numberOfPages())});
        s1 = "";
            
        s1 = this.cPage.textOnPage(currPage);

        l = this.hiveFontRenderer.getStringWidth(s);
        this.hiveFontRenderer.addNextY = 0;
        this.hiveFontRenderer.drawString(s, k - l + this.pageImageWidth - 44, b0 + 16, 0);
            
        //k = (int) (((this.width*(1.2F/0.9F)) - (this.pageImageWidth*(1.2F/0.9F))) / 2);
        //GL11.glScalef(0.9F, 0.9F, 1.0F);
        this.hiveFontRenderer.drawSplitString(s1, k + 16, b0 + 16 + 16, 240 - 16, 0);
        //GL11.glScalef(1.0F/0.9F, 1.0F/0.9F, 1.0F);

        super.drawScreen(par1, par2, par3);
    }
	
	
}
