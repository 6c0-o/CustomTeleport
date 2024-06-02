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
import java.util.stream.Collectors;

public class SetHubCommand implements CommandExecutor, TabCompleter {
    private final CustomTeleport plugin;

    public SetHubCommand(CustomTeleport plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        if (args.length < 5 || args.length > 7) {
            MessageUtil.sendMessage(player, "&eUsage: /ctsethub <world> <TpToWorld> <x> <y> <z> [yaw] [pitch]");
            return true;
        }

        List<String> worldC = plugin.getServer().getWorlds().stream()
                .map(World::getName)
                .collect(Collectors.toList());

        String world = args[0];
        if (!worldC.contains(world)) {
            MessageUtil.sendMessage(player, "&cWorld '" + world + "' is not loaded on the server.");
            plugin.getLogger().severe("World '" + world + "' is not loaded on the server.");
            return true;
        }

        String TpToWorld = args[1];
        if (!worldC.contains(TpToWorld)) {
            MessageUtil.sendMessage(player, "&cWorld '" + TpToWorld + "' is not loaded on the server.");
            plugin.getLogger().severe("World '" + TpToWorld + "' is not loaded on the server.");
            return true;
        }

        if (!isDouble(args[2]) || !isDouble(args[3]) || !isDouble(args[4])) {
            MessageUtil.sendMessage(player, "&cInvalid coordinates provided, please enter valid numbers.");
            plugin.getLogger().severe("Invalid coordinates provided, please enter valid numbers.");
            return true;
        }

        double x = Double.parseDouble(args[2]);
        double y = Double.parseDouble(args[3]);
        double z = Double.parseDouble(args[4]);

        double yaw = 0.0;
        double pitch = 0.0;

        if (args.length >= 6) {
            if (!isDouble(args[5])) {
                MessageUtil.sendMessage(player, "&cInvalid yaw provided, please enter valid number.");
                plugin.getLogger().severe("Invalid yaw provided, please enter valid number.");
                return true;
            }
            yaw = Double.parseDouble(args[5]);
        }

        if (args.length == 7) {
            if (!isDouble(args[6])) {
                MessageUtil.sendMessage(player, "&cInvalid pitch provided, please enter valid number.");
                plugin.getLogger().severe("Invalid pitch provided, please enter valid number.");
                return true;
            }
            pitch = Double.parseDouble(args[6]);
        }

        ConfigurationSection spawnSection = plugin.getConfig().getConfigurationSection("hub");
        if (spawnSection == null) {
            spawnSection = plugin.getConfig().createSection("hub");
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
        MessageUtil.sendMessage(player, "&Hub configuration for " + world + " updated.");

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

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
