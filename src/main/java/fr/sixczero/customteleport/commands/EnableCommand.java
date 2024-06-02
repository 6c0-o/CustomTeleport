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

public class EnableCommand implements CommandExecutor, TabCompleter {
    private final CustomTeleport plugin;

    public EnableCommand(CustomTeleport plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        if (args.length != 2) {
            MessageUtil.sendMessage(player, "&eUsage: /ctenable <type> <world>");
            return true;
        }

        String type = args[0];
        if (!type.equals("hub") && !type.equals("spawn")) {
            MessageUtil.sendMessage(player, "&eInvalid type, only 'hub' or 'spawn' are allowed.");
            return true;
        }

        ConfigurationSection qSection = plugin.getConfig().getConfigurationSection(type);
        if (qSection == null) {
            MessageUtil.sendMessage(player, "&cPlease create "+type+" before.");
            plugin.getLogger().severe("Please create "+type+" before");
            return true;
        }

        String world = args[1];
        if (!qSection.contains(world)) {
            MessageUtil.sendMessage(player, "&cWorld '" + world + "' is not configured in the "+type+" section.");;
            plugin.getLogger().severe("World '" + world + "' is not configured in the "+type+" section.");
            return true;
        }

        ConfigurationSection worldSection = qSection.getConfigurationSection(world);
        if (worldSection == null) {
            MessageUtil.sendMessage(player, "&c"+ world + " don't have any "+type+" config");
            plugin.getLogger().severe(world + " don't have any "+type+" config");
            return true;
        }
        worldSection.set("disable", false);

        plugin.saveConfig();
        MessageUtil.sendMessage(player, "&e" + world + " " + type +" command is now enable");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        switch (args.length){
            case 1:
                return Arrays.asList("spawn", "hub");
            case 2:
                List<String> worldNames2 = new ArrayList<>();
                for(World w : plugin.getServer().getWorlds()){
                    worldNames2.add(w.getName());
                }

                return worldNames2;
            default:
                return Collections.emptyList();
        }
    }
}
