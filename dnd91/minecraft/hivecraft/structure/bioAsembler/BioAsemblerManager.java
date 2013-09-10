package mods.dnd91.minecraft.hivecraft.structure.bioAsembler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import mods.dnd91.minecraft.hivecraft.HiveCraft;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BioAsemblerManager {
	private static final BioAsemblerManager instance = new BioAsemblerManager();
	
	private List recipes = new ArrayList();
	
	public static final BioAsemblerManager getInstance() { return instance; }
	
	private BioAsemblerManager(){
		this.addRecipe(new ItemStack(Block.cloth, 2),6*500,2*500,4*500, new Object[] {"##", "##", '#', Item.silk});
		this.addRecipe(new ItemStack(HiveCraft.itemBioHelm, 1),2*500,1*500,1*500, new Object[] {"###", "# #", '#', HiveCraft.itemBiomass});
		this.addRecipe(new ItemStack(HiveCraft.itemBioPlate, 1),5*500,4*500,2*500, new Object[] {"# #", "###", "###", '#', HiveCraft.itemBiomass});
		this.addRecipe(new ItemStack(HiveCraft.itemBioLegs, 1),3*500,2*500,2*500, new Object[] {"###", "# #","# #", '#', HiveCraft.itemBiomass});
		this.addRecipe(new ItemStack(HiveCraft.itemBioBoots, 1),2*500,1*500,1*500, new Object[] {"# #", "# #", '#', HiveCraft.itemBiomass});
	}
	
	public ShapedBioAsemblerRecipes addRecipe(ItemStack par1ItemStack,int cF, int cW, int cB, Object ... par2ArrayOfObj)
    {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (par2ArrayOfObj[i] instanceof String[])
        {
            String[] astring = (String[])((String[])par2ArrayOfObj[i++]);

            for (int l = 0; l < astring.length; ++l)
            {
                String s1 = astring[l];
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }
        else
        {
            while (par2ArrayOfObj[i] instanceof String)
            {
                String s2 = (String)par2ArrayOfObj[i++];
                ++k;
                j = s2.length();
                s = s + s2;
            }
        }

        HashMap hashmap;

        for (hashmap = new HashMap(); i < par2ArrayOfObj.length; i += 2)
        {
            Character character = (Character)par2ArrayOfObj[i];
            ItemStack itemstack1 = null;

            if (par2ArrayOfObj[i + 1] instanceof Item)
            {
                itemstack1 = new ItemStack((Item)par2ArrayOfObj[i + 1]);
            }
            else if (par2ArrayOfObj[i + 1] instanceof Block)
            {
                itemstack1 = new ItemStack((Block)par2ArrayOfObj[i + 1], 1, 32767);
            }
            else if (par2ArrayOfObj[i + 1] instanceof ItemStack)
            {
                itemstack1 = (ItemStack)par2ArrayOfObj[i + 1];
            }

            hashmap.put(character, itemstack1);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int i1 = 0; i1 < j * k; ++i1)
        {
            char c0 = s.charAt(i1);

            if (hashmap.containsKey(Character.valueOf(c0)))
            {
                aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c0))).copy();
            }
            else
            {
                aitemstack[i1] = null;
            }
        }

        ShapedBioAsemblerRecipes shapedrecipes = new ShapedBioAsemblerRecipes(j, k, aitemstack, par1ItemStack, cF,cW,cB);
        this.recipes.add(shapedrecipes);
        return shapedrecipes;
    }

    public void addShapelessRecipe(ItemStack par1ItemStack,int cF, int cW, int cB, Object ... par2ArrayOfObj)
    {
        ArrayList arraylist = new ArrayList();
        Object[] aobject = par2ArrayOfObj;
        int i = par2ArrayOfObj.length;

        for (int j = 0; j < i; ++j)
        {
            Object object1 = aobject[j];

            if (object1 instanceof ItemStack)
            {
                arraylist.add(((ItemStack)object1).copy());
            }
            else if (object1 instanceof Item)
            {
                arraylist.add(new ItemStack((Item)object1));
            }
            else
            {
                if (!(object1 instanceof Block))
                {
                    throw new RuntimeException("Invalid shapeless recipy!");
                }

                arraylist.add(new ItemStack((Block)object1));
            }
        }

        this.recipes.add(new ShapelessBioAsemblerRecipes(par1ItemStack, arraylist, cF, cW, cB));
    }

    public ItemStack findMatchingRecipe(TileEntityBioAsembler asembler, World par2World)
    {
        int i = 0;
        ItemStack itemstack = null;
        ItemStack itemstack1 = null;
        int j;

        for (j = 0; j < 9; ++j)
        {
            ItemStack itemstack2 = asembler.getStackInSlot(j);

            if (itemstack2 != null)
            {
                if (i == 0)
                {
                    itemstack = itemstack2;
                }

                if (i == 1)
                {
                    itemstack1 = itemstack2;
                }

                ++i;
            }
        }

        if (i == 2 && itemstack.itemID == itemstack1.itemID && itemstack.stackSize == 1 && itemstack1.stackSize == 1 && Item.itemsList[itemstack.itemID].isRepairable())
        {
            Item item = Item.itemsList[itemstack.itemID];
            int k = item.getMaxDamage() - itemstack.getItemDamageForDisplay();
            int l = item.getMaxDamage() - itemstack1.getItemDamageForDisplay();
            int i1 = k + l + item.getMaxDamage() * 5 / 100;
            int j1 = item.getMaxDamage() - i1;

            if (j1 < 0)
            {
                j1 = 0;
            }
            
            return new ItemStack(itemstack.itemID, 1, j1);
        }
        else
        {
        	
            for (j = 0; j < this.recipes.size(); ++j)
            {
            	IBioAsemblerRecipe irecipe = (IBioAsemblerRecipe)this.recipes.get(j);
                if (irecipe.matches(asembler, par2World))
                {
                	
                    return irecipe.getCraftingResult(asembler);
                }
            }

            return null;
        }
    }
    
    public IBioAsemblerRecipe findMatchingIRecipe(TileEntityBioAsembler asembler, World world)
    {
        int i = 0;
        ItemStack itemstack = null;
        ItemStack itemstack1 = null;
        int j;

        for (j = 0; j < 9; ++j)
        {
            ItemStack itemstack2 = asembler.getStackInSlot(j);

            if (itemstack2 != null)
            {
                if (i == 0)
                {
                    itemstack = itemstack2;
                }

                if (i == 1)
                {
                    itemstack1 = itemstack2;
                }

                ++i;
            }
        }

        {
        	
            for (j = 0; j < this.recipes.size(); ++j)
            {
            	IBioAsemblerRecipe irecipe = (IBioAsemblerRecipe)this.recipes.get(j);
                if (irecipe.matches(asembler, world))
                {
                    return irecipe;
                }
            }

            return null;
        }
    }

    /**
     * returns the List<> of all recipes
     */
    public List getRecipeList()
    {
        return this.recipes;
    }
}
