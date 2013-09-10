package mods.dnd91.minecraft.hivecraft.book;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public class HiveBookEventHandler {
	
	@ForgeSubscribe(priority = EventPriority.NORMAL)
    public void livingSpawnHandler(LivingSpawnEvent event){
		
	}
	
	public static GuiKnowledge guiKnow = new GuiKnowledge(Minecraft.getMinecraft());
	
	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void renderGameOverlayEventHandler(RenderGameOverlayEvent event){
		guiKnow.updateKnowledgeWindow();
		
	}
	
	
}
