package mods.dnd91.minecraft.hivecraft.hivenetwork;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class OrderPackage {
	int packageStrength = 4;
	String name = "None";
	String msg = "None";
	TileEntity sender = null;
	TileEntity lastSender = null;
	
	public OrderPackage(String n,String m,  TileEntity s){
		this.name = n;
		this.sender = s;
		this.lastSender = s;
		this.msg = m;
	}
	
	public OrderPackage copy(){
		OrderPackage pack = new OrderPackage(name, msg, sender);
		pack.packageStrength = this.packageStrength;
		pack.name = this.name;
		pack.msg = this.msg;
		pack.sender = this.sender;
		pack.lastSender = lastSender;
		return pack;
	}
}
