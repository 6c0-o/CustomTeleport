package fr.sixczero.customteleport.loaders;

import fr.sixczero.customteleport.CustomTeleport;
import fr.sixczero.customteleport.commands.*;

public class CommandsLoader {
    private final CustomTeleport plugin;

    public CommandsLoader(CustomTeleport plugin) {
        this.plugin = plugin;
    }

    public void load(){
        plugin.getCommand("spawn").setExecutor(new SpawnCommand(plugin));
        plugin.getCommand("spawn").setTabCompleter(new SpawnCommand(plugin));

        plugin.getCommand("hub").setExecutor(new HubCommand(plugin));
        plugin.getCommand("hub").setTabCompleter(new HubCommand(plugin));

        plugin.getCommand("ctsetspawn").setExecutor((new SetSpawnCommand(plugin)));
        plugin.getCommand("ctsetspawn").setTabCompleter((new SetSpawnCommand(plugin)));

        plugin.getCommand("ctsethub").setExecutor((new HubCommand(plugin)));
        plugin.getCommand("ctsethub").setTabCompleter((new HubCommand(plugin)));

        plugin.getCommand("ctdisable").setExecutor((new DisableCommand(plugin)));
        plugin.getCommand("ctdisable").setTabCompleter((new DisableCommand(plugin)));

        plugin.getCommand("ctenable").setExecutor((new EnableCommand(plugin)));
        plugin.getCommand("ctenable").setTabCompleter((new EnableCommand(plugin)));

        plugin.getCommand("ctcooldown").setExecutor((new CooldownCommand(plugin)));
        plugin.getCommand("ctcooldown").setTabCompleter((new CooldownCommand(plugin)));
    }
}
