package fr.sixczero.customteleport.managers;

import fr.sixczero.customteleport.CustomTeleport;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeleportManager {
    private final CustomTeleport plugin;

    private List<UUID> playersWaiting = new ArrayList<>();

    public TeleportManager(CustomTeleport plugin) {
        this.plugin = plugin;
    }

    public void teleport(Player player, World world, int count, float x, float y, float z, float yaw, float pitch){
        final int[] c = {count};
        playersWaiting.add(player.getUniqueId());
        new BukkitRunnable() {
            @Override
            public void run() {
                if(!(playersWaiting.contains(player.getUniqueId()))){
                    cancel();
                    plugin.getLogger().warning("CANCEL FR");
                    return;
                }
                switch (c[0]){
                    case 0:
                        playersWaiting.remove(player.getUniqueId());
                        player.teleport(new Location(world, x, y, z, yaw, pitch));
                        player.sendMessage("Teleported");
                        cancel();
                        break;
                    default:
                        player.sendMessage("Teleporte in " + c[0]);
                        break;
                }
                c[0]--;
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    public boolean isWaiting(Player player) {
        return playersWaiting.contains(player.getUniqueId());
    }

    public void cancelTeleport(Player player) {
        playersWaiting.remove(player.getUniqueId());
    }
}
