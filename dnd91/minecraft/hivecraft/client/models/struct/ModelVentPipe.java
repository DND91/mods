// Date: 2013-09-02 16:19:40
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package mods.dnd91.minecraft.hivecraft.client.models.struct;

import org.lwjgl.opengl.GL11;

import mods.dnd91.minecraft.hivecraft.structure.bioGrinder.TileEntityGrinder;
import mods.dnd91.minecraft.hivecraft.structure.bioVent.TileEntityVent;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVentPipe extends ModelBase
{
  //fields
    ModelRenderer bottomPlate;
    ModelRenderer frBlackSmog;
    ModelRenderer frSpire;
    ModelRenderer frMiddlePlate;
    ModelRenderer frBottomPlate;
    ModelRenderer flBlackSmog;
    ModelRenderer flSpire;
    ModelRenderer flMiddlePlate;
    ModelRenderer flBottomPlate;
    ModelRenderer brBlackSmog;
    ModelRenderer brSpire;
    ModelRenderer brMiddlePlate;
    ModelRenderer brBottomPlate;
    ModelRenderer blBlackSmog;
    ModelRenderer blSpire;
    ModelRenderer blMiddlePlate;
    ModelRenderer blBottomPlate;
  
  public ModelVentPipe()
  {
    textureWidth = 128;
    textureHeight = 128;
    
    
      bottomPlate = new ModelRenderer(this, 0, 112);
      bottomPlate.addBox(-8F, 23.9F, -8F, 16, 0, 16);
      bottomPlate.setRotationPoint(0F, 0F, 0F);
      bottomPlate.setTextureSize(128, 128);
      bottomPlate.mirror = true;
      setRotation(bottomPlate, 0F, 0F, 0F);
      frBlackSmog = new ModelRenderer(this, 0, 0);
      frBlackSmog.addBox(-1F, -13F, -1F, 2, 2, 2);
      frBlackSmog.setRotationPoint(-4F, 23F, -4F);
      frBlackSmog.setTextureSize(128, 128);
      frBlackSmog.mirror = true;
      setRotation(frBlackSmog, 0F, 0F, 0F);
    frSpire = new ModelRenderer(this, "frSpire");
    frSpire.setRotationPoint(-4F, 23F, -4F);
    setRotation(frSpire, 0F, 0F, 0F);
    frSpire.mirror = true;
    this.setTextureOffset(frSpire.boxName+".frSr", 122, 0);
    this.setTextureOffset(frSpire.boxName+".frSl", 116, 0);
    this.setTextureOffset(frSpire.boxName+".frSf", 96, 0);
    this.setTextureOffset(frSpire.boxName+".frSb", 106, 0);
      frSpire.addBox("frSr", -2F, -15F, -1F, 1, 13, 2);
      frSpire.addBox("frSl", 1F, -15F, -1F, 1, 13, 2);
      frSpire.addBox("frSf", -2F, -15F, -2F, 4, 13, 1);
      frSpire.addBox("frSb", -2F, -15F, 1F, 4, 13, 1);
    frMiddlePlate = new ModelRenderer(this, "frMiddlePlate");
    frMiddlePlate.setRotationPoint(-4F, 23F, -4F);
    setRotation(frMiddlePlate, 0F, 0F, 0F);
    frMiddlePlate.mirror = true;
    this.setTextureOffset(frMiddlePlate.boxName+".frMPr", 118, 15);
    this.setTextureOffset(frMiddlePlate.boxName+".frMPl", 108, 15);
    this.setTextureOffset(frMiddlePlate.boxName+".frMPf", 94, 15);
    this.setTextureOffset(frMiddlePlate.boxName+".frMPb", 94, 18);
      frMiddlePlate.addBox("frMPr", -3F, -2F, -2F, 1, 2, 4);
      frMiddlePlate.addBox("frMPl", 2F, -2F, -2F, 1, 2, 4);
      frMiddlePlate.addBox("frMPf", -3F, -2F, -3F, 6, 2, 1);
      frMiddlePlate.addBox("frMPb", -3F, -2F, 2F, 6, 2, 1);
    frBottomPlate = new ModelRenderer(this, "frBottomPlate");
    frBottomPlate.setRotationPoint(-4F, 23F, -4F);
    setRotation(frBottomPlate, 0F, 0F, 0F);
    frBottomPlate.mirror = true;
    this.setTextureOffset(frBottomPlate.boxName+".frBPr", 114, 21);
    this.setTextureOffset(frBottomPlate.boxName+".frBPl", 100, 21);
    this.setTextureOffset(frBottomPlate.boxName+".frBPf", 82, 21);
    this.setTextureOffset(frBottomPlate.boxName+".frBPb", 82, 23);
      frBottomPlate.addBox("frBPr", 3F, 0F, -3F, 1, 1, 6);
      frBottomPlate.addBox("frBPl", -4F, 0F, -3F, 1, 1, 6);
      frBottomPlate.addBox("frBPf", -4F, 0F, -4F, 8, 1, 1);
      frBottomPlate.addBox("frBPb", -4F, 0F, 3F, 8, 1, 1);
      flBlackSmog = new ModelRenderer(this, 0, 0);
      flBlackSmog.addBox(-1F, -13F, -1F, 2, 2, 2);
      flBlackSmog.setRotationPoint(4F, 23F, -4F);
      flBlackSmog.setTextureSize(128, 128);
      flBlackSmog.mirror = true;
      setRotation(flBlackSmog, 0F, 0F, 0F);
    flSpire = new ModelRenderer(this, "flSpire");
    flSpire.setRotationPoint(4F, 23F, -4F);
    setRotation(flSpire, 0F, 0F, 0F);
    flSpire.mirror = true;
    this.setTextureOffset(flSpire.boxName+".Sr", 76, 0);
    this.setTextureOffset(flSpire.boxName+".Sl", 70, 0);
    this.setTextureOffset(flSpire.boxName+".Sf", 60, 0);
    this.setTextureOffset(flSpire.boxName+".Sb", 50, 0);
      flSpire.addBox("Sr", -2F, -15F, -1F, 1, 13, 2);
      flSpire.addBox("Sl", 1F, -15F, -1F, 1, 13, 2);
      flSpire.addBox("Sf", -2F, -15F, -2F, 4, 13, 1);
      flSpire.addBox("Sb", -2F, -15F, 1F, 4, 13, 1);
    flMiddlePlate = new ModelRenderer(this, "flMiddlePlate");
    flMiddlePlate.setRotationPoint(4F, 23F, -4F);
    setRotation(flMiddlePlate, 0F, 0F, 0F);
    flMiddlePlate.mirror = true;
    this.setTextureOffset(flMiddlePlate.boxName+".MPr", 72, 15);
    this.setTextureOffset(flMiddlePlate.boxName+".MPl", 62, 15);
    this.setTextureOffset(flMiddlePlate.boxName+".MPf", 48, 15);
    this.setTextureOffset(flMiddlePlate.boxName+".MPb", 48, 18);
      flMiddlePlate.addBox("MPr", -3F, -2F, -2F, 1, 2, 4);
      flMiddlePlate.addBox("MPl", 2F, -2F, -2F, 1, 2, 4);
      flMiddlePlate.addBox("MPf", -3F, -2F, -3F, 6, 2, 1);
      flMiddlePlate.addBox("MPb", -3F, -2F, 2F, 6, 2, 1);
    flBottomPlate = new ModelRenderer(this, "flBottomPlate");
    flBottomPlate.setRotationPoint(4F, 23F, -4F);
    setRotation(flBottomPlate, 0F, 0F, 0F);
    flBottomPlate.mirror = true;
    this.setTextureOffset(flBottomPlate.boxName+".BPr", 68, 21);
    this.setTextureOffset(flBottomPlate.boxName+".BPl", 54, 21);
    this.setTextureOffset(flBottomPlate.boxName+".BPf", 36, 21);
    this.setTextureOffset(flBottomPlate.boxName+".BPb", 36, 23);
      flBottomPlate.addBox("BPr", 3F, 0F, -3F, 1, 1, 6);
      flBottomPlate.addBox("BPl", -4F, 0F, -3F, 1, 1, 6);
      flBottomPlate.addBox("BPf", -4F, 0F, -4F, 8, 1, 1);
      flBottomPlate.addBox("BPb", -4F, 0F, 3F, 8, 1, 1);
      brBlackSmog = new ModelRenderer(this, 0, 0);
      brBlackSmog.addBox(-1F, -13F, -1F, 2, 2, 2);
      brBlackSmog.setRotationPoint(-4F, 23F, 4F);
      brBlackSmog.setTextureSize(128, 128);
      brBlackSmog.mirror = true;
      setRotation(brBlackSmog, 0F, 0F, 0F);
    brSpire = new ModelRenderer(this, "brSpire");
    brSpire.setRotationPoint(-4F, 23F, 4F);
    setRotation(brSpire, 0F, 0F, 0F);
    brSpire.mirror = true;
    this.setTextureOffset(brSpire.boxName+".Sr", 122, 29);
    this.setTextureOffset(brSpire.boxName+".Sl", 116, 29);
    this.setTextureOffset(brSpire.boxName+".Sf", 96, 29);
    this.setTextureOffset(brSpire.boxName+".Sb", 106, 29);
      brSpire.addBox("Sr", -2F, -15F, -1F, 1, 13, 2);
      brSpire.addBox("Sl", 1F, -15F, -1F, 1, 13, 2);
      brSpire.addBox("Sf", -2F, -15F, -2F, 4, 13, 1);
      brSpire.addBox("Sb", -2F, -15F, 1F, 4, 13, 1);
    brMiddlePlate = new ModelRenderer(this, "brMiddlePlate");
    brMiddlePlate.setRotationPoint(-4F, 23F, 4F);
    setRotation(brMiddlePlate, 0F, 0F, 0F);
    brMiddlePlate.mirror = true;
    this.setTextureOffset(brMiddlePlate.boxName+".MPr", 118, 44);
    this.setTextureOffset(brMiddlePlate.boxName+".MPl", 108, 44);
    this.setTextureOffset(brMiddlePlate.boxName+".MPf", 94, 44);
    this.setTextureOffset(brMiddlePlate.boxName+".MPb", 94, 47);
      brMiddlePlate.addBox("MPr", -3F, -2F, -2F, 1, 2, 4);
      brMiddlePlate.addBox("MPl", 2F, -2F, -2F, 1, 2, 4);
      brMiddlePlate.addBox("MPf", -3F, -2F, -3F, 6, 2, 1);
      brMiddlePlate.addBox("MPb", -3F, -2F, 2F, 6, 2, 1);
    brBottomPlate = new ModelRenderer(this, "brBottomPlate");
    brBottomPlate.setRotationPoint(-4F, 23F, 4F);
    setRotation(brBottomPlate, 0F, 0F, 0F);
    brBottomPlate.mirror = true;
    this.setTextureOffset(brBottomPlate.boxName+".BPr", 114, 50);
    this.setTextureOffset(brBottomPlate.boxName+".BPl", 100, 50);
    this.setTextureOffset(brBottomPlate.boxName+".BPf", 82, 50);
    this.setTextureOffset(brBottomPlate.boxName+".BPb", 82, 52);
      brBottomPlate.addBox("BPr", 3F, 0F, -3F, 1, 1, 6);
      brBottomPlate.addBox("BPl", -4F, 0F, -3F, 1, 1, 6);
      brBottomPlate.addBox("BPf", -4F, 0F, -4F, 8, 1, 1);
      brBottomPlate.addBox("BPb", -4F, 0F, 3F, 8, 1, 1);
      blBlackSmog = new ModelRenderer(this, 0, 0);
      blBlackSmog.addBox(-1F, -13F, -1F, 2, 2, 2);
      blBlackSmog.setRotationPoint(4F, 23F, 4F);
      blBlackSmog.setTextureSize(128, 128);
      blBlackSmog.mirror = true;
      setRotation(blBlackSmog, 0F, 0F, 0F);
    blSpire = new ModelRenderer(this, "blSpire");
    blSpire.setRotationPoint(4F, 23F, 4F);
    setRotation(blSpire, 0F, 0F, 0F);
    blSpire.mirror = true;
    this.setTextureOffset(blSpire.boxName+".Sr", 76, 29);
    this.setTextureOffset(blSpire.boxName+".Sl", 70, 29);
    this.setTextureOffset(blSpire.boxName+".Sf", 50, 29);
    this.setTextureOffset(blSpire.boxName+".Sb", 60, 29);
      blSpire.addBox("Sr", -2F, -15F, -1F, 1, 13, 2);
      blSpire.addBox("Sl", 1F, -15F, -1F, 1, 13, 2);
      blSpire.addBox("Sf", -2F, -15F, -2F, 4, 13, 1);
      blSpire.addBox("Sb", -2F, -15F, 1F, 4, 13, 1);
    blMiddlePlate = new ModelRenderer(this, "blMiddlePlate");
    blMiddlePlate.setRotationPoint(4F, 23F, 4F);
    setRotation(blMiddlePlate, 0F, 0F, 0F);
    blMiddlePlate.mirror = true;
    this.setTextureOffset(blMiddlePlate.boxName+".MPr", 72, 44);
    this.setTextureOffset(blMiddlePlate.boxName+".MPl", 62, 44);
    this.setTextureOffset(blMiddlePlate.boxName+".MPf", 48, 44);
    this.setTextureOffset(blMiddlePlate.boxName+".MPb", 48, 47);
      blMiddlePlate.addBox("MPr", -3F, -2F, -2F, 1, 2, 4);
      blMiddlePlate.addBox("MPl", 2F, -2F, -2F, 1, 2, 4);
      blMiddlePlate.addBox("MPf", -3F, -2F, -3F, 6, 2, 1);
      blMiddlePlate.addBox("MPb", -3F, -2F, 2F, 6, 2, 1);
    blBottomPlate = new ModelRenderer(this, "blBottomPlate");
    blBottomPlate.setRotationPoint(4F, 23F, 4F);
    setRotation(blBottomPlate, 0F, 0F, 0F);
    blBottomPlate.mirror = true;
    this.setTextureOffset(blBottomPlate.boxName+".BPr", 68, 50);
    this.setTextureOffset(blBottomPlate.boxName+".BPl", 54, 50);
    this.setTextureOffset(blBottomPlate.boxName+".BPf", 36, 50);
    this.setTextureOffset(blBottomPlate.boxName+".BPb", 36, 52);
      blBottomPlate.addBox("BPr", 3F, 0F, -3F, 1, 1, 6);
      blBottomPlate.addBox("BPl", -4F, 0F, -3F, 1, 1, 6);
      blBottomPlate.addBox("BPf", -4F, 0F, -4F, 8, 1, 1);
      blBottomPlate.addBox("BPb", -4F, 0F, 3F, 8, 1, 1);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(entity, f, f1, f2, f3, f4, f5);
    bottomPlate.render(f5);
    frBlackSmog.render(f5);
    frSpire.render(f5);
    frMiddlePlate.render(f5);
    frBottomPlate.render(f5);
    flBlackSmog.render(f5);
    flSpire.render(f5);
    flMiddlePlate.render(f5);
    flBottomPlate.render(f5);
    brBlackSmog.render(f5);
    brSpire.render(f5);
    brMiddlePlate.render(f5);
    brBottomPlate.render(f5);
    blBlackSmog.render(f5);
    blSpire.render(f5);
    blMiddlePlate.render(f5);
    blBottomPlate.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void renderModel(TileEntityVent te, float f5) {
		int x = te.xCoord;
		int y = te.yCoord;
		int z = te.zCoord;
		
		{
			//baseX = 0, baseY = 112, length = 32, level = 0..2
			//
			//base + level*length + level
			int offX = 0;
			int offY = 112;
			if(te.stage < 10){
				offX = 0;
			}else if(te.stage < 20){
				offX = 32 + 1;
			}else{
				offX = 64 + 2;
			}
			bottomPlate.setTextureOffset(offX, offY);
			if(te.stage == 10 || te.stage == 20 || true){
				//te.stage++;
				
				bottomPlate = new ModelRenderer(this, offX, offY);
			    bottomPlate.addBox(-8F, 23.9F, -8F, 16, 0, 16);
			    bottomPlate.setRotationPoint(0F, 0F, 0F);
			    bottomPlate.setTextureSize(128, 128);
			    bottomPlate.mirror = true;
			    setRotation(bottomPlate, 0F, 0F, 0F);
				
				//te.worldObj.markBlockForRenderUpdate(x, y, z);
				//te.worldObj.markBlockForUpdate(x, y, z);
			}
		}
		
		bottomPlate.render(f5);
		
		if(te.stage > 30){
			float h = ((te.stage-30) / 10.0f);
			if(h >= 1.0F)
				h = 1.0F;
			float mH = h;
			float rH = 1.0F - mH;
			float offsetY = 1.0F - mH;
			offsetY *= 24F*f5/mH;
			
			GL11.glPushMatrix();
			GL11.glScalef(1.0F, mH, 1.0F);			
			GL11.glTranslatef(0.0F, offsetY, 0.0F); //Stretch from bottom

			frBottomPlate.render(f5);
			flBottomPlate.render(f5);
			brBottomPlate.render(f5);
			blBottomPlate.render(f5);
			GL11.glPopMatrix();
		}
		
		if(te.stage > 40){
			float h = ((te.stage-40) / 10.0f);
			if(h >= 1.0F)
				h = 1.0F;
			float mH = h;
			float rH = 1.0F - mH;
			float offsetY = 1.0F - mH;
			offsetY *= 24F*f5/mH;
			
			GL11.glPushMatrix();
			GL11.glScalef(1.0F, mH, 1.0F);			
			GL11.glTranslatef(0.0F, offsetY, 0.0F); //Stretch from bottom

			frMiddlePlate.render(f5);
			flMiddlePlate.render(f5);
			brMiddlePlate.render(f5);
			blMiddlePlate.render(f5);
			GL11.glPopMatrix();
		}
		
		if(te.stage > 50){
			float h = ((te.stage-50) / 10.0f);
			if(h >= 1.0F)
				h = 1.0F;
			float mH = h;
			float rH = 1.0F - mH;
			float offsetY = 1.0F - mH;
			offsetY *= 24F*f5/mH;
			
			GL11.glPushMatrix();
			GL11.glScalef(1.0F, mH, 1.0F);			
			GL11.glTranslatef(0.0F, offsetY, 0.0F); //Stretch from bottom

			frSpire.render(f5);
			flSpire.render(f5);
			brSpire.render(f5);
			blSpire.render(f5);
			frBlackSmog.render(f5);
		    flBlackSmog.render(f5);
		    brBlackSmog.render(f5);
		    blBlackSmog.render(f5);
			GL11.glPopMatrix();
		}
	}
  
  public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}