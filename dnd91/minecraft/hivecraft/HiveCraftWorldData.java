package mods.dnd91.minecraft.hivecraft;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import mods.dnd91.minecraft.hivecraft.genetics.Genetics;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class HiveCraftWorldData extends WorldSavedData {

	final static String key = "HiveCraftWorldData";
	
	public boolean isNew = false;
	
	private static Map<String, NBTTagCompound> playerData = new HashMap<String, NBTTagCompound>();
	
	
	public void setKnowFull(String user, NBTTagCompound compound){
		playerData.put(user, compound);
		this.markDirty();
	}
	
	public NBTTagCompound getKnowFull(String user){
		return playerData.get(user);
	}
	
	public void setKnow(String user, String know, int value){
		NBTTagCompound compound = playerData.get(user);
		if(compound == null)
			return;
		compound.setInteger(know, value);
		this.markDirty();
	}
	
	public int getKnow(String user, String know){
		NBTTagCompound compound = playerData.get(user);
		if(compound == null)
			return -1;
		int i = compound.getInteger(know);
		return i;
	}
	
	
	
	public static HiveCraftWorldData forWorld(World world) {
        // Retrieves the MyWorldData instance for the given world, creating it if necessary
		playerData.clear();
		MapStorage storage = world.perWorldStorage;
		HiveCraftWorldData result = (HiveCraftWorldData)storage.loadData(HiveCraftWorldData.class, key);
		if (result == null) {
			result = new HiveCraftWorldData();
			storage.setData(key, result);
			result.isNew = true;	
		}
		return result;
	}
	
	public HiveCraftWorldData() {
		super(key);
	}
	
	public HiveCraftWorldData(String key){
		super(key);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {		
        if(compound.hasKey("playerData")){
        	NBTTagList clist = compound.getTagList("playerData");
        	for(int i = 0; i < clist.tagCount(); i++){
        		NBTTagCompound com = (NBTTagCompound) clist.tagAt(i);
        		String user = com.getString("user");
        		if(user != null)
        			playerData.put(user, com);
        	}
        }
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		if(playerData.size() > 0){
			NBTTagList taglist = new NBTTagList();
			for (Map.Entry<String, NBTTagCompound> entry : playerData.entrySet()) {
			    String key = entry.getKey();
			    NBTTagCompound value = entry.getValue();
			    value.setString("user", key);
			    taglist.appendTag(value);
			}
			compound.setTag("playerData", taglist);
		}
	}

}
