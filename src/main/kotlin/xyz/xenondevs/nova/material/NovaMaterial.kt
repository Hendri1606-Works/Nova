package xyz.xenondevs.nova.material

import com.google.gson.JsonObject
import de.studiocode.invui.item.ItemBuilder
import org.bukkit.Material
import org.bukkit.Material.*
import org.bukkit.entity.ArmorStand
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.item.NovaItem
import xyz.xenondevs.nova.item.impl.BottledMobItem
import xyz.xenondevs.nova.item.impl.FilterItem
import xyz.xenondevs.nova.item.impl.JetpackItem
import xyz.xenondevs.nova.tileentity.EnergyTileEntity
import xyz.xenondevs.nova.tileentity.TileEntity
import xyz.xenondevs.nova.tileentity.impl.*
import xyz.xenondevs.nova.util.toIntArray
import java.util.*

private fun blockOf(data: IntArray) = ModelData(BARRIER, data)

private fun blockOf(data: Int) = ModelData(BARRIER, intArrayOf(data))

private fun structureBlockOf(data: IntArray) = ModelData(STRUCTURE_VOID, data)

private fun structureBlockOf(data: Int) = ModelData(STRUCTURE_VOID, intArrayOf(data))

private fun itemOf(data: IntArray) = ModelData(SHULKER_SHELL, data)

private fun itemOf(data: Int) = ModelData(SHULKER_SHELL, intArrayOf(data))

enum class NovaMaterial(
    val itemName: String,
    val item: ModelData,
    val novaItem: NovaItem? = null,
    createItemBuilderFunction: ((NovaMaterial, TileEntity?) -> ItemBuilder)? = null,
    val block: ModelData? = null,
    val hitbox: Material? = null,
    val createTileEntity: ((UUID?, NovaMaterial, JsonObject, ArmorStand) -> TileEntity)? = null
) {
    
    // 1 - 1000: Blocks
    FURNACE_GENERATOR("Furnace Generator", blockOf(1), null, EnergyTileEntity::createItemBuilder, blockOf(1), COBBLESTONE, ::FurnaceGenerator),
    MECHANICAL_PRESS("Mechanical Press", blockOf(2), null, EnergyTileEntity::createItemBuilder, blockOf(2), COBBLESTONE, ::MechanicalPress),
    BASIC_POWER_CELL("Basic Power Cell", blockOf(3), null, EnergyTileEntity::createItemBuilder, blockOf(3), IRON_BLOCK, ::BasicPowerCell),
    ADVANCED_POWER_CELL("Advanced Power Cell", blockOf(4), null, EnergyTileEntity::createItemBuilder, blockOf(4), IRON_BLOCK, ::AdvancedPowerCell),
    ELITE_POWER_CELL("Elite Power Cell", blockOf(5), null, EnergyTileEntity::createItemBuilder, blockOf(5), IRON_BLOCK, ::ElitePowerCell),
    ULTIMATE_POWER_CELL("Ultimate Power Cell", blockOf(6), null, EnergyTileEntity::createItemBuilder, blockOf(6), IRON_BLOCK, ::UltimatePowerCell),
    CREATIVE_POWER_CELL("Creative Power Cell", blockOf(7), null, EnergyTileEntity::createItemBuilder, blockOf(7), IRON_BLOCK, ::CreativePowerCell),
    PULVERIZER("Pulverizer", blockOf(8), null, EnergyTileEntity::createItemBuilder, blockOf(8), COBBLESTONE, ::Pulverizer),
    SOLAR_PANEL("Solar Panel", blockOf(9), null, EnergyTileEntity::createItemBuilder, blockOf(9), BARRIER, ::SolarPanel),
    QUARRY("Quarry", blockOf(10), null, EnergyTileEntity::createItemBuilder, blockOf(10), COBBLESTONE, ::Quarry),
    ELECTRICAL_FURNACE("Electrical Furnace", blockOf(11), null, EnergyTileEntity::createItemBuilder, blockOf(11), COBBLESTONE, ::ElectricalFurnace),
    CHUNK_LOADER("Chunk Loader", blockOf(12), null, EnergyTileEntity::createItemBuilder, blockOf(12), COBBLESTONE, ::ChunkLoader),
    BLOCK_BREAKER("Block Breaker", blockOf(13), null, EnergyTileEntity::createItemBuilder, blockOf(13), COBBLESTONE, ::BlockBreaker),
    BLOCK_PLACER("Block Placer", blockOf(14), null, EnergyTileEntity::createItemBuilder, blockOf(14), COBBLESTONE, ::BlockPlacer),
    STORAGE_UNIT("Storage Unit", blockOf(15), null, null, blockOf(15), BARRIER, ::StorageUnit),
    CHARGER("Charger", blockOf(16), null, EnergyTileEntity::createItemBuilder, blockOf(16), COBBLESTONE, ::Charger),
    MOB_KILLER("Mob Killer", blockOf(17), null, EnergyTileEntity::createItemBuilder, blockOf(17), COBBLESTONE, ::MobKiller),
    VACUUM_CHEST("Vacuum Chest", blockOf(18), null, null, blockOf(18), BARRIER, ::VacuumChest),
    BREEDER("Breeder", blockOf(19), null, EnergyTileEntity::createItemBuilder, blockOf(19), COBBLESTONE, ::Breeder),
    MOB_DUPLICATOR("Mob Duplicator", blockOf(20), null, EnergyTileEntity::createItemBuilder, blockOf(20), COBBLESTONE, ::MobDuplicator),
    
    // 1000 - 2000: Crafting Items
    IRON_PLATE("Iron Plate", itemOf(1000)),
    GOLD_PLATE("Gold Plate", itemOf(1001)),
    DIAMOND_PLATE("Diamond Plate", itemOf(1002)),
    NETHERITE_PLATE("Netherite Plate", itemOf(1003)),
    EMERALD_PLATE("Emerald Plate", itemOf(1004)),
    REDSTONE_PLATE("Redstone Plate", itemOf(1005)),
    LAPIS_PLATE("Lapis Plate", itemOf(1006)),
    COPPER_PLATE("Copper Plate", itemOf(1007)),
    IRON_GEAR("Iron Gear", itemOf(1010)),
    GOLD_GEAR("Gold Gear", itemOf(1011)),
    DIAMOND_GEAR("Diamond Gear", itemOf(1012)),
    NETHERITE_GEAR("Netherite Gear", itemOf(1013)),
    EMERALD_GEAR("Emerald Gear", itemOf(1014)),
    REDSTONE_GEAR("Redstone Gear", itemOf(1015)),
    LAPIS_GEAR("Lapis Gear", itemOf(1016)),
    COPPER_GEAR("Copper Gear", itemOf(1017)),
    IRON_DUST("Iron Dust", itemOf(1020)),
    GOLD_DUST("Gold Dust", itemOf(1021)),
    DIAMOND_DUST("Diamond Dust", itemOf(1022)),
    NETHERITE_DUST("Netherite Dust", itemOf(1023)),
    EMERALD_DUST("Emerald Dust", itemOf(1024)),
    LAPIS_DUST("Lapis Dust", itemOf(1025)),
    COAL_DUST("Coal Dust", itemOf(1026)),
    COPPER_DUST("Copper Dust", itemOf(1027)),
    
    NETHERITE_DRILL("Netherite Drill", itemOf(1030)),
    SOLAR_CELL("Solar Cell", itemOf(1031)),
    BOTTLED_MOB("Bottled Mob", itemOf(1032), BottledMobItem),
    
    // 2000 - 3000: Upgrades and similar
    WRENCH("Wrench", itemOf(2000)),
    ITEM_FILTER("Item Filter", itemOf(2001), FilterItem),
    
    // 3000 - 4000: Equipment, Attachments
    JETPACK("Jetpack", ModelData(IRON_CHESTPLATE, intArrayOf(3000)), JetpackItem),
    
    // 5000 - 10.000 MultiModel Blocks
    BASIC_CABLE("Basic Cable", structureBlockOf(5004), null, null, structureBlockOf(intArrayOf(-1) + (5000..5003).toIntArray() + (5025..5033).toIntArray()), CHAIN, ::BasicCable),
    ADVANCED_CABLE("Advanced Cable", structureBlockOf(5009), null, null, structureBlockOf(intArrayOf(-1) + (5005..5008).toIntArray() + (5025..5033).toIntArray()), CHAIN, ::AdvancedCable),
    ELITE_CABLE("Elite Cable", structureBlockOf(5014), null, null, structureBlockOf(intArrayOf(-1) + (5010..5013).toIntArray() + (5025..5033).toIntArray()), CHAIN, ::EliteCable),
    ULTIMATE_CABLE("Ultimate Cable", structureBlockOf(5019), null, null, structureBlockOf(intArrayOf(-1) + (5015..5018).toIntArray() + (5025..5033).toIntArray()), CHAIN, ::UltimateCable),
    CREATIVE_CABLE("Creative Cable", structureBlockOf(5024), null, null, structureBlockOf(intArrayOf(-1) + (5020..5023).toIntArray() + (5025..5033).toIntArray()), CHAIN, ::CreativeCable),
    SCAFFOLDING("Quarry Scaffolding", itemOf(5040), null, null, itemOf((5041..5046).toIntArray()), null, null),
    
    // 9.000 - 10.000 UI Elements
    SIDE_CONFIG_BUTTON("", itemOf(9000)),
    GRAY_BUTTON("", itemOf(9001)),
    ORANGE_BUTTON("", itemOf(9002)),
    BLUE_BUTTON("", itemOf(9003)),
    GREEN_BUTTON("", itemOf(9004)),
    PLATE_ON_BUTTON("", itemOf(9005)),
    PLATE_OFF_BUTTON("", itemOf(9006)),
    GEAR_ON_BUTTON("", itemOf(9007)),
    GEAR_OFF_BUTTON("", itemOf(9008)),
    ENERGY_ON_BUTTON("", itemOf(9009)),
    ENERGY_OFF_BUTTON("", itemOf(9010)),
    ITEM_ON_BUTTON("", itemOf(9011)),
    ITEM_OFF_BUTTON("", itemOf(9012)),
    WHITELIST_BUTTON("", itemOf(9013)),
    BLACKLIST_BUTTON("", itemOf(9014)),
    PLUS_ON_BUTTON("", itemOf(9015)),
    PLUS_OFF_BUTTON("", itemOf(9016)),
    MINUS_ON_BUTTON("", itemOf(9017)),
    MINUS_OFF_BUTTON("", itemOf(9018)),
    AREA_ON_BUTTON("", itemOf(9019)),
    AREA_OFF_BUTTON("", itemOf(9020)),
    NBT_ON_BUTTON("", itemOf(9021)),
    NBT_OFF_BUTTON("", itemOf(9022)),
    
    // 10.000 - ? Multi-Texture UI Elements
    PROGRESS_ARROW("", itemOf((10_000..10_016).toIntArray())),
    ENERGY_PROGRESS("", itemOf((10_100..10_116).toIntArray())),
    RED_BAR("", itemOf((10_200..10_216).toIntArray())),
    GREEN_BAR("", itemOf((10_300..10_316).toIntArray())),
    BLUE_BAR("", itemOf((10_400..10_416).toIntArray())),
    PRESS_PROGRESS("", itemOf((10_500..10_508).toIntArray())),
    PULVERIZER_PROGRESS("", itemOf((10_600..10_614).toIntArray())),
    
    // 100.000 - ? Numbers
    NUMBER("", itemOf((100_000..100_999).toIntArray()));
    
    val isBlock = block != null && createTileEntity != null
    private val createItemBuilderFunction: ((TileEntity?) -> ItemBuilder)? = if (createItemBuilderFunction != null) {
        { createItemBuilderFunction(this, it) }
    } else null
    
    /**
     * Creates a basic [ItemBuilder] without any additional information
     * like an energy bar added to the [ItemStack].
     *
     * Can be used for just previewing the item type or as a base in
     * a `createItemBuilder` function for a [TileEntity].
     */
    fun createBasicItemBuilder(): ItemBuilder = item.getItemBuilder(itemName)
    
    /**
     * Creates an [ItemBuilder] for this [NovaMaterial].
     *
     * The [TileEntity] provided must be of the same type as the [TileEntity]
     * returned in the [createTileEntity] function.
     *
     * If there is no custom [createItemBuilderFunction] for this [NovaMaterial],
     * it will return the result of [createBasicItemBuilder].
     */
    fun createItemBuilder(tileEntity: TileEntity? = null): ItemBuilder =
        createItemBuilderFunction?.invoke(tileEntity) ?: novaItem?.getDefaultItemBuilder(createBasicItemBuilder())
        ?: createBasicItemBuilder()
    
    /**
     * Creates an [ItemStack] for this [NovaMaterial].
     *
     * This is the same as calling `createItemBuilder.build()`
     */
    fun createItemStack(): ItemStack = createItemBuilder().build()
    
    /**
     * Creates an [ItemStack] with the specified amount for this [NovaMaterial].
     *
     * This is the same as calling `createItemBuilder.setAmount([amount]).build()`
     */
    fun createItemStack(amount: Int): ItemStack = createItemBuilder().setAmount(amount).build()
    
}