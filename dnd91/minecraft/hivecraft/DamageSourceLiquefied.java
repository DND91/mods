package mods.dnd91.minecraft.hivecraft;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

public class DamageSourceLiquefied extends DamageSource {

	protected DamageSourceLiquefied(String par1Str) {
		super(par1Str);
	}
	
	public String getDeathMessage(EntityLiving par1EntityLiving)
    {
        EntityLiving entityliving1 = par1EntityLiving.func_94060_bK();
        String s = "death.attack." + this.damageType;
        String s1 = s + ".player";
        return par1EntityLiving.getEntityName() + " was liquefied";
        //return entityliving1 != null && StatCollector.func_94522_b(s1) ? StatCollector.translateToLocalFormatted(s1, new Object[] {par1EntityLiving.func_96090_ax(), entityliving1.func_96090_ax()}): StatCollector.translateToLocalFormatted(s, new Object[] {par1EntityLiving.func_96090_ax()});
    }

}
