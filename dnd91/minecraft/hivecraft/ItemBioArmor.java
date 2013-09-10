package mods.dnd91.minecraft.hivecraft;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.dnd91.minecraft.hivecraft.client.BioArmorModel;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

/**
 * The Bio Armor will be mutateble.
 * Helmet: Armor, Nightvision, Some immunity to bad effects.
 * Plate: Armor, Thorns, Smell(So a hive thinks you belongs to them. IMPORTANT! In the begining).
 * Legs: Armor, Jump, Fire Resistence
 * Boots: Armor, Speed, Feather falling.
 */

public class ItemBioArmor extends ItemArmor implements ISpecialArmor {

	public ItemBioArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial,
			int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		setMaxStackSize(1);
        setCreativeTab(HiveCraft.tabHiveCraft);
        switch(par4){
        case 0:
        	setUnlocalizedName("Bio Helmet");
        	break;
        case 1:
        	setUnlocalizedName("Bio Plate");
        	break;
        case 2:
        	setUnlocalizedName("Bio Leggings");
        	break;
        case 3:
        	setUnlocalizedName("Bio Boots");
        	break;
        }
        
	}
	
	@Override
	public void registerIcons(IconRegister ires){
		this.itemIcon = ires.registerIcon("dnd91/minecraft/hivecraft:Biomass");
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer){
		return "/mods/dnd91/minecraft/hivecraft/textures/armors/BioArmorModel.png";
	}
	
	@SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLiving entityLiving, ItemStack itemStack, int armorSlot)
    {
        return new BioArmorModel(itemStack, armorSlot);
    }
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add("IT'S A HELMET");
	}

	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack)
    {
		//if(world.isRemote)
		//	return;
		player.landMovementFactor += 0.1f;
		player.jumpMovementFactor += 0.1f;
		if(player.isJumping){
			player.motionY += 0.05;
		}
		player.fallDistance = 0.f;
		//player.fireResistance = 1000;
		//player.hurtResistantTime = 1000;
		
		
		player.curePotionEffects(new ItemStack(Item.bucketMilk));
		
		//player.capabilities.setPlayerWalkSpeed(10.0f);
    }

	@Override
	public ArmorProperties getProperties(EntityLiving player, ItemStack armor,
			DamageSource source, double damage, int slot) {
		// TODO Auto-generated method stub
		
		return new ArmorProperties(0, 2.d,20000);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void damageArmor(EntityLiving entity, ItemStack stack,
			DamageSource source, int damage, int slot) {
		// TODO Auto-generated method stub
		
	}

}
