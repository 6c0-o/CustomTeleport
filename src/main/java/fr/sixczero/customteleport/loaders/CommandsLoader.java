package fr.sixczero.customteleport.loaders;

import fr.sixczero.customteleport.CustomTeleport;
import fr.sixczero.customteleport.commands.HubCommand;
import fr.sixczero.customteleport.commands.SetSpawnCommand;
import fr.sixczero.customteleport.commands.SpawnCommand;

public class CommandsLoader {
    private final CustomTeleport plugin;

    public CommandsLoader(CustomTeleport plugin) {
        this.plugin = plugin;
    }

    public void load(){
        plugin.getCommand("spawn").setExecutor(new SpawnCommand(plugin));
        plugin.getCommand("spawn").setTabCompleter(new SpawnCommand(plugin));

        plugin.getCommand("hub").setExecutor(new HubCommand(plugin));
        plugin.getCommand("hub").setTabCompleter(new SpawnCommand(plugin));

        plugin.getCommand("setspawn").setExecutor((new SetSpawnCommand(plugin)));
        plugin.getCommand("setspawn").setTabCompleter((new SetSpawnCommand(plugin)));
    }
}
