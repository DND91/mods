package mods.dnd91.minecraft.hivecraft.structure.bioAsembler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ShapelessBioAsemblerRecipes implements IBioAsemblerRecipe
{
    /** Is the ItemStack that you get when craft the recipe. */
    private final ItemStack recipeOutput;

    /** Is a List of ItemStack that composes the recipe. */
    public final List recipeItems;
    
    public int costFood = 0;
	public int costWater = 0;
	public int costBiomass = 0;

    public ShapelessBioAsemblerRecipes(ItemStack par1ItemStack, List par2List, int cF, int cW, int cB)
    {
        this.recipeOutput = par1ItemStack;
        this.recipeItems = par2List;
        this.costFood = cF;
        this.costWater = cW;
        this.costBiomass = cB;
    }

    public ItemStack getRecipeOutput()
    {
        return this.recipeOutput;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(TileEntityBioAsembler asembler, World par2World)
    {
    	if(!(asembler.currentFood >= this.costFood && asembler.currentWater >= this.costWater && asembler.currentBiomass >= this.costBiomass))
        	return false;
    	
        ArrayList arraylist = new ArrayList(this.recipeItems);

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                ItemStack itemstack = asembler.getStackInRowAndColumn(j, i);

                if (itemstack != null)
                {
                    boolean flag = false;
                    Iterator iterator = arraylist.iterator();

                    while (iterator.hasNext())
                    {
                        ItemStack itemstack1 = (ItemStack)iterator.next();

                        if (itemstack.itemID == itemstack1.itemID && (itemstack1.getItemDamage() == 32767 || itemstack.getItemDamage() == itemstack1.getItemDamage()))
                        {
                            flag = true;
                            arraylist.remove(itemstack1);
                            break;
                        }
                    }

                    if (!flag)
                    {
                        return false;
                    }
                }
            }
        }
        return arraylist.isEmpty();
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(TileEntityBioAsembler asembler)
    {
        return this.recipeOutput.copy();
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize()
    {
        return this.recipeItems.size();
    }

	@Override
	public int getCostFood() {
		return costFood;
	}

	@Override
	public int getCostWater() {
		return costWater;
	}

	@Override
	public int getCostBiomass() {
		return costBiomass;
	}
}
