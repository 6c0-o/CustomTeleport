package fr.sixczero.customteleport.loaders;

import fr.sixczero.customteleport.CustomTeleport;
import fr.sixczero.customteleport.listeners.PlayersMoveListener;

public class ListenersLoader {
    private final CustomTeleport plugin;

    public ListenersLoader(CustomTeleport plugin) {
        this.plugin = plugin;
    }

    public void load(){
        plugin.getServer().getPluginManager().registerEvents(new PlayersMoveListener(plugin), plugin);
    }
}
