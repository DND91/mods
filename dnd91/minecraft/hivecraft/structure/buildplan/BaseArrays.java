package mods.dnd91.minecraft.hivecraft.structure.buildplan;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


import mods.dnd91.minecraft.hivecraft.HiveCraft;
import net.minecraft.block.Block;



public class BaseArrays {
	
	public static BlockPlan[][][] masterBuildPlan(int buildType, int buildPlan){
		switch(buildType){
		case 0:
			return getQueenNest(buildPlan);
		case 1:
			return getBioAsembler(buildPlan);
		case 2:
			return getBioWorks(buildPlan);
		}
		return null;
	}
	
	
					//Y X Z
	public static BlockPlan[][][] getQueenNest(int i){
		BlockPlan[][][] plan = BaseArrays.BaseArray();
		try {
			plan = (BlockPlan[][][]) queenNests.get(i).invoke(null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		if(plan == null)
			return null;
		else
			return plan;
	}
	
	
	
	private static Map<Integer, Method> queenNests = new HashMap<Integer, Method>();
	static {
		try {
			queenNests.put(0, BaseArrays.class.getMethod("SmallQueenNestCube"));
			queenNests.put(1, BaseArrays.class.getMethod("FireStarQueenNest"));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	/** 0. Orgininal/Base
	 *  1. Master/Core
	 *  2. Hard/Edge
	 *  3. Normal/Outer/Skin/Hide
	 *  4. HiveNetwork in/out
	 *  5. Normal/Inner/Flesh/Organic
	 **/
	
	public static BlockPlan queenNestCore = new BlockPlan(HiveCraft.blockQueenNest.blockID, 1, true);
	public static BlockPlan queenNestHard = new BlockPlan(1,HiveCraft.blockQueenNest.blockID, 2);
	public static BlockPlan queenNestNormal = new BlockPlan(2,HiveCraft.blockQueenNest.blockID, 3);
	public static BlockPlan queenNestNetwork = new BlockPlan(3,HiveCraft.blockQueenNest.blockID, 4);
	
	public static BlockPlan[][][] BaseArray(){
		BlockPlan[][][] a = new BlockPlan[5][5][5];
		a[0] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		a[1] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		a[2] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		a[3] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		a[4] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		return a;
	}
	
	public static BlockPlan[][][] SmallQueenNestCube(){
		BlockPlan[][][] a = new BlockPlan[5][5][5];
		a[0] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,queenNestHard,queenNestNormal,queenNestHard,null},
				{null,queenNestNormal,queenNestNetwork,queenNestNormal,null},
				{null,queenNestHard,queenNestNormal,queenNestHard,null},
				{null,null,null,null,null}
		};
		a[1] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,queenNestNormal,queenNestNetwork,queenNestNormal,null},
				{null,queenNestNetwork,queenNestCore,queenNestNetwork,null},
				{null,queenNestNormal,queenNestNetwork,queenNestNormal,null},
				{null,null,null,null,null}
		};
		a[2] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,queenNestHard,queenNestNormal,queenNestHard,null},
				{null,queenNestNormal,queenNestNetwork,queenNestNormal,null},
				{null,queenNestHard,queenNestNormal,queenNestHard,null},
				{null,null,null,null,null}
		};
		a[3] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		a[4] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		return a;
	}
	
	//queenNestHard, queenNestNormal, queenNestNetwork
	
	
	public static BlockPlan[][][] FireStarQueenNest(){
		BlockPlan[][][] a = new BlockPlan[5][5][5];
		a[0] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,queenNestNetwork,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		a[1] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,queenNestNormal,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		a[2] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,queenNestHard,queenNestNormal,queenNestHard,null},
				{null,queenNestNormal,queenNestCore,queenNestNormal,null},
				{null,queenNestHard,queenNestNormal,queenNestHard,null},
				{null,null,null,null,null}
		};
		a[3] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,queenNestHard,queenNestHard,queenNestHard,null},
				{null,queenNestHard,queenNestHard,queenNestHard,null},
				{null,queenNestHard,queenNestHard,queenNestHard,null},
				{null,null,null,null,null}
		};
		a[4] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		return a;
	}
	
	/** BIO ASEMBLER **/
	
	public static BlockPlan[][][] getBioAsembler(int i){
		BlockPlan[][][] plan = BaseArrays.BaseArray();
		try {
			plan = (BlockPlan[][][]) bioAsemblers.get(i).invoke(null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		if(plan == null)
			return null;
		else
			return plan;
	}
	
	private static Map<Integer, Method> bioAsemblers = new HashMap<Integer, Method>();
	
	static {
		try {
			bioAsemblers.put(0, BaseArrays.class.getMethod("StandardBioAsembler"));
			bioAsemblers.put(1, BaseArrays.class.getMethod("LowLandBioAsembler"));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	/** 0. Orgininal/Base
	 *  1. Master/Core
	 *  2. Hard/Edge
	 *  3. Normal/Outer/Skin/Hide
	 *  4. HiveNetwork in/out
	 *  5. Normal/Inner/Flesh/Organic
	 *  6. 
	 **/
	
	public static BlockPlan bioAsemblerCore = new BlockPlan(HiveCraft.blockBioAsembler.blockID, 1, false);
	public static BlockPlan bioAsemblerHard = new BlockPlan(1,HiveCraft.blockBioAsembler.blockID, 2);
	public static BlockPlan bioAsemblerNormal = new BlockPlan(2,HiveCraft.blockBioAsembler.blockID, 3);
	public static BlockPlan bioAsemblerNetwork = new BlockPlan(3,HiveCraft.blockBioAsembler.blockID, 4);
	
	public static BlockPlan[][][] LowLandBioAsembler(){
		BlockPlan[][][] a = new BlockPlan[5][5][5];
		a[0] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,bioAsemblerHard,bioAsemblerNormal,bioAsemblerHard,null},
				{null,bioAsemblerNormal,bioAsemblerNormal,bioAsemblerNormal,null},
				{null,bioAsemblerHard,bioAsemblerNormal,bioAsemblerHard,null},
				{null,null,null,null,null}
		};
		a[1] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,bioAsemblerCore,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		a[2] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,bioAsemblerNetwork,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		a[3] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		a[4] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		return a;
	}
	
	public static BlockPlan[][][] StandardBioAsembler(){
		BlockPlan[][][] a = new BlockPlan[5][5][5];
		a[0] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,bioAsemblerNetwork,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		a[1] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,bioAsemblerNormal,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		a[2] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,bioAsemblerCore,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		a[3] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,bioAsemblerHard,null,null},
				{null,bioAsemblerHard,bioAsemblerNormal,bioAsemblerHard,null},
				{null,null,bioAsemblerHard,null,null},
				{null,null,null,null,null}
		};
		a[4] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,bioAsemblerNormal,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		return a;
	}
	
	/** BIO WORKS **/
	
	public static BlockPlan[][][] getBioWorks(int i){
		BlockPlan[][][] plan = BaseArrays.BaseArray();
		try {
			plan = (BlockPlan[][][]) bioWorks.get(i).invoke(null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return plan;
	}
	
	
	
	private static Map<Integer, Method> bioWorks = new HashMap<Integer, Method>();
	static {
		try {
			bioWorks.put(0, BaseArrays.class.getMethod("BioWorksPoolDesign"));
			bioWorks.put(1, BaseArrays.class.getMethod("BioWorksPoolDesign"));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	/** 0. Orgininal/Base
	 *  1. Master/Core
	 *  2. Hard/Edge
	 *  3. Normal/Outer/Skin/Hide
	 *  4. HiveNetwork in/out
	 *  5. Normal/Inner/Flesh/Organic
	 *  6. 
	 **/
	
	public static BlockPlan bioWorksCore = new BlockPlan(HiveCraft.blockBioWorks.blockID, 1, false);
	public static BlockPlan bioWorksHard = new BlockPlan(1,HiveCraft.blockBioWorks.blockID, 2);
	public static BlockPlan bioWorksNormal = new BlockPlan(2, Block.glass.blockID, 3);//new BlockPlan(2,HiveCraft.blockBioWorks.blockID, 3);
	public static BlockPlan bioWorksNetwork = new BlockPlan(3,HiveCraft.blockBioWorks.blockID, 4);
	
	public static BlockPlan[][][] BioWorksPoolDesign(){
		BlockPlan[][][] a = new BlockPlan[5][5][5];
		a[0] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,bioWorksHard,bioWorksNetwork,bioWorksHard,null},
				{null,bioWorksNetwork,bioWorksCore,bioWorksNetwork,null},
				{null,bioWorksHard,bioWorksNetwork,bioWorksHard,null},
				{null,null,null,null,null}
		};
		a[1] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,bioWorksHard,bioWorksNormal,bioWorksHard,null},
				{null,bioWorksNormal,null,bioWorksNormal,null},
				{null,bioWorksHard,bioWorksNormal,bioWorksHard,null},
				{null,null,null,null,null}
		};
		a[2] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,bioWorksHard,bioWorksNormal,bioWorksHard,null},
				{null,bioWorksNormal,null,bioWorksNormal,null},
				{null,bioWorksHard,bioWorksNormal,bioWorksHard,null},
				{null,null,null,null,null}
		};
		a[3] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		a[4] = new BlockPlan[][]{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		return a;
	}
	
}
