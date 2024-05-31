package fr.sixczero.customteleport.listeners;

import fr.sixczero.customteleport.CustomTeleport;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayersMoveListener implements Listener {
    private final CustomTeleport plugin;

    public PlayersMoveListener(CustomTeleport plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        if (isMove(e))  return;
        if (plugin.getTeleportManager().isWaiting(e.getPlayer())){
            e.getPlayer().sendMessage("Canceled");
            plugin.getTeleportManager().cancelTeleport(e.getPlayer());
        }
    }

    private boolean isMove (PlayerMoveEvent e){
        double deltaX = Math.abs(e.getTo().getX() - e.getFrom().getX());
        double deltaY = Math.abs(e.getTo().getY() - e.getFrom().getY());
        double deltaZ = Math.abs(e.getTo().getZ() - e.getFrom().getZ());

        if (deltaX >= 0.1 || deltaY >= 0.1 || deltaZ >= 0.1) {
            return false;
        }
        return true;
    }
}
