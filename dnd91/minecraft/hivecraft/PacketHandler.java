package mods.dnd91.minecraft.hivecraft;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import mods.dnd91.minecraft.hivecraft.book.HiveBookEventHandler;
import mods.dnd91.minecraft.hivecraft.book.Knowledge;
import mods.dnd91.minecraft.hivecraft.book.KnowledgeAppedix;
import mods.dnd91.minecraft.hivecraft.hatchling.EntityHatchling;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketHandler implements IPacketHandler {
	public static Packet250CustomPayload  transformNBTTagCompoundToPacket(String channel, NBTTagCompound compound){
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream outputStream = new DataOutputStream(bos);
		
		try {
		        NBTTagCompound.writeNamedTag(compound, outputStream);
		} catch (Exception ex) {
		        ex.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = channel;
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		return packet;
	}
	
	public static NBTTagCompound transformPacketToNBTTagCompound(Packet250CustomPayload packet){
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		NBTTagCompound compound = null;
        
        try {
        	compound = (NBTTagCompound) NBTTagCompound.readNamedTag(inputStream);
        } catch (IOException e) {
                e.printStackTrace();
                return null;
        }
        return compound;
	}
	
	public static Entity getEntity(World world, int id){
		for(int i = 0; i < world.loadedEntityList.size(); i++){
			if(((Entity)world.loadedEntityList.get(i)).entityId == id)
				return (Entity)world.loadedEntityList.get(i);
		}
		
		for(int i = 0; i < world.playerEntities.size(); i++){
			if(((Entity)world.playerEntities.get(i)).entityId == id)
				return (Entity)world.playerEntities.get(i);
		}
		
		return null;
	}
	
	
	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		if(packet.channel == "HiveBook")
			handleHiveBook(manager, packet, player);
		if(packet.channel == "HiveTories")
			handleHiveTories(manager, packet, player);
		if(packet.channel == "HiveOG")
			handleOdoremGlandem(manager, packet, player);
	}
	/*
	 * HiveOdoremGlandemPacket NBTTagCompound
	 * homeSlot - Where the HiveOdoremGlandem is current 
	 * type - 0. Slot update
	 * Data
	 */
	@SideOnly(Side.CLIENT)
	public static void sendOdoremGlandemSlotUpdate(int homeSlot, int slotUpdate){
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("homeSlot", homeSlot);
		compound.setInteger("type",0);
		compound.setInteger("slotUpdate",slotUpdate);
		Packet250CustomPayload packet = transformNBTTagCompoundToPacket("HiveOG", compound);
		PacketDispatcher.sendPacketToServer(packet);
	}
	
	private void handleOdoremGlandem(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if(side == Side.SERVER){
			NBTTagCompound compound = transformPacketToNBTTagCompound(packet);
			int homeSlot = compound.getInteger("homeSlot");
			int type = compound.getInteger("type");
			EntityPlayer p = (EntityPlayer) player;
			switch(type){
			case 0:
				int slot = compound.getInteger("slotUpdate");
				ItemStack stack = p.inventory.mainInventory[homeSlot];
				NBTTagCompound comp = stack.getTagCompound();
				if(comp == null){
					stack.setTagCompound(new NBTTagCompound());
					comp = stack.getTagCompound();
				}
				comp.setInteger("currentSlot", slot);
				break;
			}
			
			
		}else if(side == Side.CLIENT){
			
		}else{
			System.out.println("PACKET_HANDLER_HANDLE_SMELLY_THINGY: WHAT EVER YOU ARE DOING STOP!");
		}
	}

	/*
	 * HiveToryPacket NBTTagCompound
	 * EntityID - Entity whos inventory will be uppdated. Must be of EntityHatchling.
	 * InventorySize - Size of the inventory. If diffrent from current recreate.
	 * Items - Items in inventory. NBTTagList.
	 */
	public static NBTTagCompound createHiveToryCompound(EntityHatchling ent){
		if(ent == null)
			return null;
		return createHiveToryCompound(ent.entityId, ent.inventory);
	}
	
	public static NBTTagCompound createHiveToryCompound(Entity ent, ItemStack[] stack){
		return createHiveToryCompound(ent.entityId, stack);
	}
	
	public static NBTTagCompound createHiveToryCompound(int id, ItemStack[] stack){
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("EntityID", id);
		if(stack == null)
			compound.setInteger("InventorySize", 0);
		else
			compound.setInteger("InventorySize", stack.length);
		
		NBTTagList nbt_list = new NBTTagList();
		 for(int l = 0; stack != null && l < stack.length; l++){
			 if(stack[l] == null)
				 continue;

			 NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)l);
            stack[l].writeToNBT(nbttagcompound1);
            nbt_list.appendTag(nbttagcompound1);
		 } 
		 compound.setTag("Inventory", nbt_list);
		
		return compound;
	}
	
	public static void sendHiveTory(EntityHatchling hatch){
		NBTTagCompound compound = createHiveToryCompound(hatch);
		Packet250CustomPayload pack = transformNBTTagCompoundToPacket("HiveTories", compound);
		PacketDispatcher.sendPacketToAllPlayers(pack);
	}
	
	@SideOnly(Side.CLIENT)
	public static void callForUpdateHiveTory(Entity ent){
		callForUpdateHiveTory(ent.entityId);
	}
	
	@SideOnly(Side.CLIENT)
	public static void callForUpdateHiveTory(int id){
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("EntityID", id);
		Packet250CustomPayload pack = transformNBTTagCompoundToPacket("HiveTories", compound);
		PacketDispatcher.sendPacketToServer(pack);
	}
	
	public static void sendUpdateHiveTory(Player player, int id){
		EntityPlayer p = (EntityPlayer)player;
		EntityHatchling hatch = (EntityHatchling) getEntity(p.worldObj, id);
		if(hatch == null)
			return;
		NBTTagCompound hivetory = createHiveToryCompound(hatch);
		//if(hivetory == null)
		//	return;
		Packet250CustomPayload pack = transformNBTTagCompoundToPacket("HiveTories", hivetory);
		PacketDispatcher.sendPacketToPlayer(pack, player);
	}
	
	
	/*
	 * HANDLE HiveTories
	 */
	
	/*
	 * HiveToryPacket NBTTagCompound
	 * EntityID - Entity whos inventory will be uppdated. Must be of EntityHatchling.
	 * InventorySize - Size of the inventory. If diffrent from current recreate.
	 * Items - Items in inventory. NBTTagList.
	 */
	private void handleHiveTories(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if(side == Side.SERVER){
			NBTTagCompound hivetory = transformPacketToNBTTagCompound(packet);
			sendUpdateHiveTory(player, hivetory.getInteger("EntityID"));
		}else if(side == Side.CLIENT){
			NBTTagCompound hivetory = transformPacketToNBTTagCompound(packet);
			//if(hivetory == null)
			//	return;
			int id = hivetory.getInteger("EntityID");
			int size = hivetory.getInteger("InventorySize");
			EntityPlayer p = (EntityPlayer) player;
			
			EntityHatchling hatch = (EntityHatchling) getEntity(p.worldObj, id);
			if(hatch == null)
				return;
			if(hatch.inventorySize != size){
				hatch.inventory = new ItemStack[size];
				hatch.inventorySize = size;
			}else{
				for(int i = 0; i < hatch.inventorySize; i++)
					hatch.inventory[i] = null;
			}
			
			NBTTagList nbt_list = hivetory.getTagList("Inventory");
			 
			 for (int i = 0; i < nbt_list.tagCount(); ++i)
		        {
		            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbt_list.tagAt(i);
		            int j = nbttagcompound1.getByte("Slot") & 255;

		            if (j >= 0 && j < hatch.inventory.length)
		            {
		            	hatch.inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		            }
		        }
		}else{
			System.out.println("PACKET_HANDLER_HANDLE_HIVETORY: WHAT EVER YOU ARE DOING STOP!");
		}
	}
	
	/*
	 * HANDLE Hive Book
	 */

	public void handleHiveBook(INetworkManager manager, Packet250CustomPayload packet, Player player){
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if(side == Side.SERVER){
			sendUpdate(player);
		}else if(side == Side.CLIENT){
			NBTTagCompound new_hivebook = transformPacketToNBTTagCompound(packet);
			EntityPlayer p = (EntityPlayer)player;
			NBTTagCompound player_data = p.getEntityData();
			NBTTagCompound current_hivebook = (NBTTagCompound) player_data.getTag(p.username+".HiveBook");
			
			if(current_hivebook != null){
				for(Knowledge know : KnowledgeAppedix.knowledgeList){
					if(new_hivebook.hasKey(know.getName())){
						if(!current_hivebook.hasKey(know.getName())){
							HiveBookEventHandler.guiKnow.queueTakenAchievement(know);
						}
					}
				}
				
				
			}
			player_data.setTag(p.username+".HiveBook", new_hivebook);
		}else{
			System.out.println("PACKET_HANDLER_HANDLE_HIVEBOOK: WHAT EVER YOU ARE DOING STOP!");
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void callForUpdate(){
		NBTTagCompound compound = new NBTTagCompound();
		Packet250CustomPayload pack = transformNBTTagCompoundToPacket("HiveBook", compound);
		PacketDispatcher.sendPacketToServer(pack);
	}
	
	public static void sendUpdate(Player player){
		EntityPlayer p = (EntityPlayer)player;
		
		NBTTagCompound compound = p.getEntityData();
		if(compound.hasKey(p.username+".HiveBook")){
			NBTTagCompound hivebook = compound.getCompoundTag(p.username+".HiveBook");
			Packet250CustomPayload pack = transformNBTTagCompoundToPacket("HiveBook", hivebook);
			PacketDispatcher.sendPacketToPlayer(pack, player);
		}
	}

}
