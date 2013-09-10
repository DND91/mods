package mods.dnd91.minecraft.hivecraft.structure.bioWorks;

import mods.dnd91.minecraft.hivecraft.eggs.SlotFood;
import mods.dnd91.minecraft.hivecraft.structure.block.SlotOutput;
import mods.dnd91.minecraft.hivecraft.structure.queenNest.TileEntityQueenNest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerBioWorks extends Container{

	private TileEntityBioWorks works = null;
	
	public ContainerBioWorks(InventoryPlayer par1InventoryPlayer, TileEntityBioWorks ent)
    {
		this.works = ent;
		for(int x = 0; x < 6; x++)
		for(int y = 0; y < 6; y++){
			this.addSlotToContainer(new Slot(ent, x + y*6, 9 + x * 20, 13 + y * 20 ));
		}
		
		this.addSlotToContainer(new Slot(ent,36, 132, 68)); //Water Bucket
		this.addSlotToContainer(new Slot(ent,37, 152, 68)); //Biomass
		
		for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.works.isUseableByPlayer(entityplayer);
	}

}
