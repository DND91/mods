package mods.dnd91.minecraft.hivecraft.structure.buildplan;

public class BlockPlan {
	public BlockPlan(int p, int id, int me) { prio = p; blockID = id; meta = me; }
	public BlockPlan(int id, int me, boolean ma) { this(0, id,me); isMaster = ma; }
	public int prio;
	public int blockID;
	public int meta;
	public boolean isMaster = false;
}
