package mods.dnd91.minecraft.hivecraft.hatchling.drone;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotShadow extends Slot{
	IInventory inventory;

	public SlotShadow(IInventory inv, int par2, int par3, int par4) {
		super(inv, par2, par3, par4);
		inventory = inv;
	}

}
