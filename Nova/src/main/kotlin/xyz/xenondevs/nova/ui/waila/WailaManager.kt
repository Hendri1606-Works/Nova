package xyz.xenondevs.nova.ui.waila

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.scheduler.BukkitTask
import xyz.xenondevs.nova.data.config.DEFAULT_CONFIG
import xyz.xenondevs.nova.data.config.configReloadable
import xyz.xenondevs.nova.initialize.Initializable
import xyz.xenondevs.nova.util.registerEvents
import xyz.xenondevs.nova.util.runTaskTimer
import xyz.xenondevs.nova.util.unregisterEvents
import xyz.xenondevs.nova.world.pos

private val UPDATE_INTERVAL by configReloadable { DEFAULT_CONFIG.getLong("waila.update_interval") }

internal object WailaManager : Initializable(), Listener {
    
    override val inMainThread = true
    override val dependsOn = emptySet<Initializable>()
    
    private var tickTask: BukkitTask? = null
    private val overlays = HashMap<Player, Waila>()
    
    override fun init() {
        reload()
    }
    
    fun reload() {
        unregisterEvents()
        overlays.values.forEach { it.setActive(false) }
        overlays.clear()
        tickTask?.cancel()
        if (DEFAULT_CONFIG.getBoolean("waila.enabled")) {
            registerEvents()
            Bukkit.getOnlinePlayers().forEach(::addWailaOverlay)
            tickTask = runTaskTimer(0, UPDATE_INTERVAL, ::handleTick)
        }
    }
    
    override fun disable() {
        overlays.values.forEach { it.setActive(false) }
    }
    
    private fun handleTick() {
        overlays.forEach { (player, waila) ->
            waila.update(player.getTargetBlockExact(5)?.pos)
        }
    }
    
    private fun addWailaOverlay(player: Player) {
        overlays[player] = Waila(player)
    }
    
    @EventHandler
    private fun handleJoin(event: PlayerJoinEvent) {
        addWailaOverlay(event.player)
    }
    
    @EventHandler
    private fun handleQuit(event: PlayerQuitEvent) {
        overlays.remove(event.player)?.setActive(false)
    }
    
}