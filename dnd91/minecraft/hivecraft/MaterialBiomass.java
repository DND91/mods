package mods.dnd91.minecraft.hivecraft;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialBiomass extends Material
{
    public MaterialBiomass(MapColor par1MapColor)
    {
        super(par1MapColor);
    }

    /**
     * Returns if blocks of these materials are liquids.
     */
    public boolean isLiquid()
    {
        return false;
    }

    /**
     * Returns if this material is considered solid or not
     */
    public boolean blocksMovement()
    {
        return true;
    }

    public boolean isSolid()
    {
        return true;
    }
}
