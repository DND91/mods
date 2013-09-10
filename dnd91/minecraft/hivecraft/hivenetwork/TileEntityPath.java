package mods.dnd91.minecraft.hivecraft.hivenetwork;

public class TileEntityPath extends TileEntityNode {

	@Override
	public void recivePackage(OrderPackage pack, int side){
		if(side != (this.blockMetadata & 15) && side != sideToSide((this.blockMetadata & 15)))
			return;
		super.recivePackage(pack, side);
	}
	
	@Override
	public void sendToAllSides(OrderPackage pack){
		sendPackage(pack.copy(), (this.blockMetadata & 15));
		sendPackage(pack, sideToSide(this.blockMetadata & 15));
	}
}
