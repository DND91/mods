package mods.dnd91.minecraft.hivecraft.structure.bioAsembler;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IBioAsemblerRecipe
{
	int getCostFood();
	int getCostWater();
	int getCostBiomass();
    /**
     * Used to check if a recipe matches current crafting inventory
     */
    boolean matches(TileEntityBioAsembler asembler, World world);

    /**
     * Returns an Item that is the result of this recipe
     */
    ItemStack getCraftingResult(TileEntityBioAsembler asembler);

    /**
     * Returns the size of the recipe area
     */
    int getRecipeSize();

    ItemStack getRecipeOutput();
}
