package fr.sixczero.customteleport.commands;

import fr.sixczero.customteleport.CustomTeleport;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class SpawnCommand implements CommandExecutor, TabCompleter {
    private final CustomTeleport plugin;

    public SpawnCommand(CustomTeleport plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            plugin.getLogger().severe("Only player can use this command");
            return true;
        }

        Player player = (Player) sender;
        if (plugin.getTeleportManager().isWaiting(player)) {
            player.sendMessage("You have already in a teleporation execution");
            return true;
        }

        ConfigurationSection spawnSection = plugin.getConfig().getConfigurationSection("spawn");

        if (!(spawnSection != null && spawnSection.contains(player.getWorld().getName()))) {
            player.sendMessage("This world have no config for this command");
            return true;
        }

        ConfigurationSection worldSection = spawnSection.getConfigurationSection(player.getWorld().getName());

        World givenWorld = plugin.getServer().getWorld(worldSection.getString("tpToWorld", player.getWorld().getName()));
        if (givenWorld == null) {
            player.sendMessage("Please check 'tpToWorld' str for world " + player.getWorld().getName() + "  maybe not a good world name");
            return true;
        }
        if (worldSection.isSet("x") && worldSection.isSet("y") && worldSection.isSet("z")) {
            player.sendMessage("Please check 'tpToWorld' str for world " + player.getWorld().getName() + "  maybe not a good world name");
        }

        plugin.getTeleportManager().teleport(player, givenWorld, worldSection.getInt("cooldownBeforeTp", 0), (float) worldSection.getDouble("x"), (float) worldSection.getDouble("y"), (float) worldSection.getDouble("z"), (float) worldSection.getDouble("yaw", 0.0), (float) worldSection.getDouble("pitch", 0.0));

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        return Collections.emptyList();
    }
}
