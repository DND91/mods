package mods.dnd91.minecraft.hivecraft.book;

import mods.dnd91.minecraft.hivecraft.book.page.Page;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.util.StatCollector;

public class Knowledge{
	public final int displayColumn;
	public final int displayRow;
	
	public final Knowledge parentKnowledge;
	
	private final String knowledgeDescription;	
	
	public final ItemStack theItemStack;
	private boolean isSpecial;
	public boolean isIndenpendent = false;
	
	public int myPage;
	
	public Knowledge setIndependent(){
		isIndenpendent = true;
		return this;
	}
	
	public Knowledge setSpecial()
    {
        this.isSpecial = true;
        return this;
    }
	
	public boolean getSpecial()
    {
        return this.isSpecial;
    }
	
	public Knowledge registerKnowledge(){
		KnowledgeAppedix.knowledgeList.add(this);
		return this;
	}
	
	public Knowledge(int id, String name, int colum, int row, Item item, Knowledge parent, int p){
		this(id, name, colum, row, new ItemStack(item), parent, p);
	}
	
	public Knowledge(int id, String name, int colum, int row, Block block, Knowledge parent, int p){
		this(id, name, colum, row, new ItemStack(block), parent, p);
	}
	
	public Knowledge(int id, String desc, int colum, int row, ItemStack itemstack, Knowledge parent, int p){
		theItemStack = itemstack;
		knowledgeDescription = desc;
		displayColumn = colum;
		displayRow = row;
		parentKnowledge = parent;
		myPage = p;
		isSpecial = false;
		
		if (colum < KnowledgeAppedix.minDisplayColumn)
        {
			KnowledgeAppedix.minDisplayColumn = colum;
        }

        if (row < KnowledgeAppedix.minDisplayRow)
        {
        	KnowledgeAppedix.minDisplayRow = row;
        }

        if (colum > KnowledgeAppedix.maxDisplayColumn)
        {
        	KnowledgeAppedix.maxDisplayColumn = colum;
        }

        if (row > KnowledgeAppedix.maxDisplayRow)
        {
        	KnowledgeAppedix.maxDisplayRow = row;
        }
	}

	public String getName() {
		return knowledgeDescription;
	}
	
	public String getTitle() {
		return StatCollector.translateToLocal("knowledge."+knowledgeDescription+".title");
	}
	
	public String getDesc(){
		String s = StatCollector.translateToLocal("knowledge."+knowledgeDescription+".text");
		return s;
	}
	
	
}
