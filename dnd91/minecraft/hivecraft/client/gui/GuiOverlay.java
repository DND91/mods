package mods.dnd91.minecraft.hivecraft.client.gui;

import java.util.Collection;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;

public class GuiOverlay extends Gui{
	private Minecraft mc;
	
	private ItemStack viewStack = new ItemStack(0,1,0);
	private RenderItem itemRender = new RenderItem();

	private int lastSlot = 0;
	private boolean locked = false;
	private int lastItem = 0;

	  public GuiOverlay(Minecraft mc)
	  {
	    super();
	    this.mc = mc;
	    
	  }

	  private static final int BUFF_ICON_SIZE = 18;
	  private static final int BUFF_ICON_SPACING = BUFF_ICON_SIZE + 2; // 2 pixels between buff icons
	  private static final int BUFF_ICON_BASE_U_OFFSET = 0;
	  private static final int BUFF_ICON_BASE_V_OFFSET = 198;
	  private static final int BUFF_ICONS_PER_ROW = 8;
	  
	  private static final String[] sideNames = { "D", "U", "N", "S", "W", "E" };
	  
	  private boolean hotbatContains(Item item){
		  for(int i = 0; i < 9; i++){
			  if(mc.thePlayer.inventory.mainInventory[i] != null && mc.thePlayer.inventory.mainInventory[i].itemID == item.itemID)
				  return true;
		  }
		  return false;
	  }
	  
	  private ItemStack getHotbatItemStack(Item item){
		  for(int i = 0; i < 9; i++){
			  if(mc.thePlayer.inventory.mainInventory[i] != null && mc.thePlayer.inventory.mainInventory[i].itemID == item.itemID)
				  return mc.thePlayer.inventory.mainInventory[i];
		  }
		  return null;
	  }

	  @ForgeSubscribe(priority = EventPriority.HIGHEST)
	  public void onRenderExperienceBar(RenderGameOverlayEvent event)
	  {
		if(event.type == ElementType.HOTBAR)
			updateLock();
	    if(event.type != ElementType.EXPERIENCE) 
	      return;
	    
	    if(hotbatContains(HiveCraft.itemOdoremGlandem)){
	    	this.renderOdoremGlandemInterface();
	    }
	  }
	  
	  public void updateLock(){
		  int currentItem = mc.thePlayer.inventory.currentItem;
		  ItemStack stack = mc.thePlayer.inventory.getStackInSlot(currentItem);
		  if(stack == null){
			  if(locked)
				  mc.thePlayer.inventory.currentItem = lastSlot;
			  return;
		  }
		  int itemID = stack.itemID;
		  
		  if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			  if(!locked){
				  if(itemID == HiveCraft.itemOdoremGlandem.itemID){
					  this.lastSlot = currentItem;
					  locked = true;
				  }
			  }else{
				  mc.thePlayer.inventory.currentItem = this.lastSlot;
				  int wheel = Mouse.getDWheel();
				  int w = Mouse.getEventDWheel();
				  if(0 > wheel){
					  ItemStack item = mc.thePlayer.getCurrentEquippedItem();
					  NBTTagCompound compound = item.getTagCompound();
					  if(compound == null)
						  return;
					  int current = compound.getInteger("currentSlot");
					  current++;
					  current = current >= 4 ? 0 : current;
					  compound.setInteger("currentSlot", current);
					  PacketHandler.sendOdoremGlandemSlotUpdate(lastSlot, current);
				  }else if(wheel > 0){
					  ItemStack item = mc.thePlayer.getCurrentEquippedItem();
					  NBTTagCompound compound = item.getTagCompound();
					  if(compound == null)
						  return;
					  int current = compound.getInteger("currentSlot");
					  current--;
					  current = current < 0 ? 3 : current;
					  compound.setInteger("currentSlot", current);
					  PacketHandler.sendOdoremGlandemSlotUpdate(lastSlot, current);
				  }
			  }
			  
		  }else{
			  this.lastSlot = mc.thePlayer.inventory.currentItem;
			  this.locked = false;
		  }
	  }
	  
	  public void renderOdoremGlandemInterface(){
		  int screenWidth = mc.displayWidth / 4;
		  int posX = screenWidth - 8;
	
		  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	      GL11.glDisable(GL11.GL_LIGHTING);      
	      this.mc.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/gui/OdoremGlandem.png");
	      this.drawTexturedModalRect(posX - 40, 0, 0, 0, 81, 21);
		  
		  ItemStack item = getHotbatItemStack(HiveCraft.itemOdoremGlandem);
		  NBTTagCompound compound = item.getTagCompound();
		  if(compound == null){
			  item.setTagCompound(new NBTTagCompound());
			  compound = item.getTagCompound();
			  compound.setInteger("currentSlot", 0);
		  }
		
		  int current = compound.getInteger("currentSlot");
		  
	      for(int i = 0; i < 4; i++){
	    	  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    	  GL11.glDisable(GL11.GL_LIGHTING);
	    	  int type = -1;
	    	  NBTTagCompound comp = null;
	    	  if(compound.hasKey("Slot"+i+"ID")){
	    		  comp = compound.getCompoundTag("Slot"+i+"ID");
	    		  type = comp.getInteger("type");
	    	  }
	    	  boolean flag = false;
	    	  int side = 0;
	    	  
	    	  switch(type){
	    	  case -1: //Empty
	    		  this.mc.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/gui/OdoremGlandem.png");
	    	      this.drawTexturedModalRect(posX + 3 - 40 + i * 20, 3, 24, 22, 15, 15);
	    		  break;
	    	  case 0: //Block
	    		  int blockID = comp.getInteger("blockID");
		    	  int meta = comp.getInteger("meta");
		    	  side = comp.getInteger("side");
		    	  viewStack.itemID = blockID;
		    	  viewStack.setItemDamage(meta);
			  
		    	  this.itemRender.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, viewStack, posX + 3 - 40 + i * 20, 3);
		    	  int offset = 0;
		    	  if(current == i)
		    		  offset += 1;
		    	  this.mc.fontRenderer.drawString(sideNames[side], posX + 3 - 34 + i * 20, 22+offset, 0xFFFFFF);
		    	  break;
	    	  case 1: //Item
	    	  case 2: //Order
	    		  int itemID = comp.getInteger("itemID");
		    	  int dmg = comp.getInteger("dmg");
		    	  viewStack.itemID = itemID;
		    	  viewStack.setItemDamage(dmg);
		    	  this.itemRender.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, viewStack, posX + 3 - 40 + i * 20, 3);
		    	  break;
	    	  case 3: //Booked
	    		  this.mc.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/gui/OdoremGlandem.png");
	    	      this.drawTexturedModalRect(posX + 3 - 40 + i * 20, 3, 39, 22, 15, 15);
	    		  break;
	    	  }
	      }
	      
	      this.mc.renderEngine.bindTexture("/mods/dnd91/minecraft/hivecraft/textures/gui/OdoremGlandem.png");
	      this.drawTexturedModalRect(posX - 41 + current * 20, -1, 0, 22, 23, 45);
	      
	  }
}
