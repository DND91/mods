package mods.dnd91.minecraft.hivecraft.hatchling.queen;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerQueen extends Container{
	private EntityLadybugQueen owner = null;
	
	public ContainerQueen(InventoryPlayer inventoryPlayer, EntityLadybugQueen queen){
		this.owner = queen;
		this.addSlotToContainer(new Slot(owner, 0, 15, 15));
		this.addSlotToContainer(new Slot(owner, 1, 35, 15));
		this.addSlotToContainer(new Slot(owner, 2, 55, 15));
		this.addSlotToContainer(new Slot(owner, 3, 75, 15));
		
		//Add Player Inventory
		int i;
		for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.owner.isUseableByPlayer(entityplayer);
	}

	
	
	
}
