package xyz.xenondevs.nova.ui.menu.item.recipes.craftingtype

import de.studiocode.invui.gui.GUI
import de.studiocode.invui.item.ItemProvider
import xyz.xenondevs.nova.data.recipe.RecipeContainer
import xyz.xenondevs.nova.ui.overlay.CustomCharacters

abstract class RecipeGroup : Comparable<RecipeGroup> {
    
    private val guiCache = HashMap<RecipeContainer, GUI>()
    
    abstract val overlay: CustomCharacters
    abstract val icon: ItemProvider
    abstract val priority: Int
    
    protected abstract fun createGUI(container: RecipeContainer): GUI
    
    fun getGUI(container: RecipeContainer): GUI {
        return guiCache.getOrPut(container) { createGUI(container) }
    }
    
    override fun compareTo(other: RecipeGroup): Int {
        return this.priority.compareTo(other.priority)
    }
    
}