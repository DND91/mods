package mods.dnd91.minecraft.hivecraft.hivenetwork;

import java.util.AbstractQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
/**
 * 
 * 0: Down y--
 * 1: Up y++
 * 2: north z--
 * 3: south z++
 * 4: west x--
 * 5: east x++
 *
 */

public class TileEntityNode extends TileEntity{
	private List<OrderPackage> orders = new LinkedList<OrderPackage>();
	
	public static int sideToSide(int side){
		switch(side){
		case 0:
			return 1;
		case 1:
			return 0;
		case 2:
			return 3;
		case 3:
			return 2;
		case 4:
			return 5;
		case 5:
			return 4;
		}
		return 15;
	}
	
	public TileEntity getEntityOnSideOfNodeType(World world, int side){
		int x = this.xCoord;
		int y = this.yCoord;
		int z = this.zCoord;
		TileEntity tileEntity = null;
		switch(side){
		case 0: //DOWN Y--
			y--;
			break;
		case 1: //UP Y++
			y++;
			break;
		case 2: //NORTH Z--
			z--;
			break;
		case 3: //SOUTH Z++
			z++;
			break;
		case 4: //WEST X--
			x--;
			break;
		case 5: //EAST X++
			x++;
			break;
		}
		tileEntity = world.getBlockTileEntity(x, y, z);
		if(tileEntity != null && tileEntity instanceof TileEntityNode)
			return tileEntity;
		else
			return null;
	}
	
	public void sendPackage(OrderPackage pack, int side){
		TileEntityNode node = (TileEntityNode)getEntityOnSideOfNodeType(this.worldObj, side);
		if(node == null || pack.lastSender == node)
			return;
		pack.lastSender = this;
		node.recivePackage(pack, sideToSide(side));
	}
	
	public void handlePackage(OrderPackage pack){
		orders.add(pack);
	}
	
	public void recivePackage(OrderPackage pack, int side){
		pack.packageStrength--;
		if(pack.packageStrength <= 0 || pack.sender == this)
			return;

		handlePackage(pack);
	}
	
	public void sendToAllSides(OrderPackage pack){
		for(int i = 1; i < 6; i++)
			sendPackage(pack.copy(), i);
		sendPackage(pack, 0);
	}
	
	@Override
	public void updateEntity()
    {
		if(!orders.isEmpty()){
			sendToAllSides(orders.remove(0));
		}
    }
	
}
