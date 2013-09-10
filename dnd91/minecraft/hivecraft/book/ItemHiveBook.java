package mods.dnd91.minecraft.hivecraft.book;

import java.util.List;


import mods.dnd91.minecraft.hivecraft.HiveCraft;
import mods.dnd91.minecraft.hivecraft.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;

public class ItemHiveBook extends Item {

	public ItemHiveBook(int par1) {
		super(par1);
		setCreativeTab(HiveCraft.tabHiveCraft);
		setUnlocalizedName("itemHiveBook");
		setMaxStackSize(1);
	}
	
	@Override
	public void registerIcons(IconRegister ires){
		this.itemIcon = ires.registerIcon("dnd91/minecraft/hivecraft:HiveBook");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player){

		if(!itemstack.hasTagCompound() || true){ //TODO: FIX!
			NBTTagCompound compound = new NBTTagCompound();
			compound.setString("Owner", player.username);
			itemstack.setTagCompound(compound);
		}else{
			NBTTagCompound compound = itemstack.getTagCompound();
			String owner = compound.getString("Owner");
			if(!owner.equals(player.username)){
				player.addChatMessage("You do not own this book...");
				return itemstack;
			}
		}
		
		NBTTagCompound comp = player.getEntityData();
		if(!comp.hasKey(player.username+".HiveBook")){
			NBTTagCompound hivebook = KnowledgeAppedix.makeHiveBook();
			hivebook.setString("Owner", player.username);
			comp.setTag(player.username+".HiveBook", hivebook);
			KnowledgeAppedix.unlockKnowledge(player, KnowledgeAppedix.old_tome);
		}

		player.openGui(HiveCraft.instance, 100, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		return itemstack;
	}
	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
		if(itemstack.hasTagCompound()){
			String name = itemstack.getTagCompound().getString("Owner");
			list.add("Owner: " + name);
		}
	}
	
	@Override
	public boolean getShareTag()
    {
        return true;
    }

}
