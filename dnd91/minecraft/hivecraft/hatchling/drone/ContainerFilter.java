package mods.dnd91.minecraft.hivecraft.hatchling.drone;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerFilter extends Container{
	private EntityDrone owner = null;
	
	public ContainerFilter(IInventory inventoryPlayer, EntityDrone o){
		owner = o;
		this.addSlotToContainer(new SlotShadow(o.filter, 0, 80,34));
		
		for (int j = 0; j < 3; ++j)
        {
            for (int k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, k + j * 9 + 9, 8 + k * 18, 81 + j * 18 + 3));
            }
        }

        for (int j = 0; j < 9; ++j)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, j, 8 + j * 18, 139 + 3));
        }
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
