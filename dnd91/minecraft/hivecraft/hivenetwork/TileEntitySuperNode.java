package mods.dnd91.minecraft.hivecraft.hivenetwork;

import net.minecraft.tileentity.TileEntity;

public class TileEntitySuperNode extends TileEntityNode {
	public int pulse;
	
	@Override
	public void handlePackage(OrderPackage pack){
		System.out.println("RECIVED PACKAGE!");
		System.out.println(pack.name);
	}
	
	@Override
	public void updateEntity(){
		
	}
	
	public void sendPackage(OrderPackage pack){
		sendPackage(pack, this.blockMetadata);
	}
	
	@Override
	public void sendToAllSides(OrderPackage pack){
		sendPackage(pack);
	}
	
	@Override
	public void recivePackage(OrderPackage pack, int side){
		if(side != (this.blockMetadata))
			return;
		super.recivePackage(pack, side);
	}
	
}
