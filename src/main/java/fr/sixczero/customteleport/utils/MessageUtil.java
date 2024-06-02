package fr.sixczero.customteleport.utils;

import fr.sixczero.customteleport.CustomTeleport;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtil {
    private static CustomTeleport plugin;

    public MessageUtil(CustomTeleport plugin) {
        this.plugin = plugin;
    }

    public static void sendMessage(Player p, String str) {
        String prefix = plugin.getConfig().getString("prefix", "");
        if (!prefix.isEmpty()) prefix = prefix + " ";
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + str));
    }
}
