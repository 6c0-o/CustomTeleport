package fr.sixczero.customteleport.commands;

import fr.sixczero.customteleport.CustomTeleport;
import fr.sixczero.customteleport.utils.MessageUtil;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SetSpawnCommand implements CommandExecutor, TabCompleter {
    private final CustomTeleport plugin;

    public SetSpawnCommand(CustomTeleport plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length < 5 || args.length > 7) {
            sender.sendMessage("Usage: /ctsetspawn <world> <TpToWorld> <x> <y> <z> [yaw] [pitch]");
            return false;
        }

        Player player = (Player) sender;

        String world = args[0];
        String TpToWorld = args[1];
        double x = Double.parseDouble(args[2]);
        double y = Double.parseDouble(args[3]);
        double z = Double.parseDouble(args[4]);

        double yaw = 0.0;
        double pitch = 0.0;

        if (args.length >= 6) {
            yaw = Double.parseDouble(args[5]);
        }

        if (args.length == 7) {
            pitch = Double.parseDouble(args[6]);
        }

        ConfigurationSection spawnSection = plugin.getConfig().getConfigurationSection("spawn");
        if (spawnSection == null) {
            spawnSection = plugin.getConfig().createSection("spawn");
        }

        ConfigurationSection worldSection = spawnSection.getConfigurationSection(world);
        if (worldSection == null) {
            worldSection = spawnSection.createSection(world);
        }

        worldSection.set("TpToWorld", TpToWorld);
        worldSection.set("x", x);
        worldSection.set("y", y);
        worldSection.set("z", z);
        worldSection.set("yaw", yaw);
        worldSection.set("pitch", pitch);

        plugin.saveConfig();
        MessageUtil.sendMessage(player, "&eSpawn configuration for " + world + " updated.");

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        switch (args.length){
            case 1:
                List<String> worldNames = new ArrayList<>();
                for(World w : plugin.getServer().getWorlds()){
                    worldNames.add(w.getName());
                }

                return worldNames;
            case 2:
                List<String> worldNames2 = new ArrayList<>();
                for(World w : plugin.getServer().getWorlds()){
                    worldNames2.add(w.getName());
                }

                return worldNames2;
            case 3:
                return Arrays.asList(String.format("%.3f", player.getLocation().getX()));
            case 4:
                return Arrays.asList(String.format("%.3f", player.getLocation().getY()));
            case 5:
                return Arrays.asList(String.format("%.3f", player.getLocation().getZ()));
            case 6:
                return Arrays.asList(String.format("%.3f", player.getLocation().getYaw()));
            case 7:
                return Arrays.asList(String.format("%.3f", player.getLocation().getPitch()));
            default:
                return Collections.emptyList();
        }
    }
}
