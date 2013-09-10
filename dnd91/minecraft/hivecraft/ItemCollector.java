package mods.dnd91.minecraft.hivecraft;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraftforge.common.IShearable;

public class ItemCollector extends Item {

	public ItemCollector(int id){
		super(id);
		setMaxStackSize(1);
		setMaxDamage(10);
		setCreativeTab(HiveCraft.tabHiveCraft);
		setUnlocalizedName("itemCollector");
	}
	
	@Override
	public void registerIcons(IconRegister ires){
		this.itemIcon = ires.registerIcon("dnd91/minecraft/hivecraft:Collector");
	}
	
	public boolean canHarvestBlock(Block par1Block)
    {
        return par1Block instanceof IBioNest;
    }
	
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return par2Block instanceof IBioNest ? 15.0f : 1.0f;
    }
	
	@Override
    public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player) 
    {
        if (player.worldObj.isRemote)
        {
            return false;
        }
        int id = player.worldObj.getBlockId(x, y, z);
        if (Block.blocksList[id] instanceof IBioNest)
        {
        	IBioNest target = (IBioNest)Block.blocksList[id];
            if (target.isMature(itemstack, player.worldObj, x, y, z))
            {
                ArrayList<ItemStack> drops = target.onCollected(player, itemstack, player.worldObj, x, y, z,
                        EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, itemstack));
                if(!player.worldObj.isRemote){
                Random rand = new Random();

                for(ItemStack stack : drops)
                {
                    float f = 0.7F;
                    double d  = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                    double d1 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                    double d2 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                    EntityItem entityitem = new EntityItem(player.worldObj, (double)x + d, (double)y + d1, (double)z + d2, stack);
                    entityitem.delayBeforeCanPickup = 10;
                    player.worldObj.spawnEntityInWorld(entityitem);
                }

                itemstack.damageItem(1, player);
                player.addStat(StatList.mineBlockStatArray[id], 1);
                }
            }
        }
        return false;
    }
}
