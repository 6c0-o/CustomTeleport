package fr.sixczero.customteleport.managers;

import fr.sixczero.customteleport.CustomTeleport;
import fr.sixczero.customteleport.utils.MessageUtil;
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
                    return;
                }
                switch (c[0]){
                    case 0:
                        playersWaiting.remove(player.getUniqueId());
                        player.teleport(new Location(world, x, y, z, yaw, pitch));
                        cancel();
                        break;
                    default:
                        MessageUtil.sendMessage(player, "&eTeleported in &6" + c[0]);
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
