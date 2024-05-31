package fr.sixczero.customteleport;

import fr.sixczero.customteleport.loaders.CommandsLoader;
import fr.sixczero.customteleport.loaders.ListenersLoader;
import fr.sixczero.customteleport.managers.TeleportManager;
import fr.sixczero.customteleport.utils.MessageUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomTeleport extends JavaPlugin {

    private CommandsLoader commandsLoader;
    private ListenersLoader listenersLoader;
    private TeleportManager teleportManager;
    private MessageUtil messageUtil;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        commandsLoader = new CommandsLoader(this);
        listenersLoader = new ListenersLoader(this);
        teleportManager = new TeleportManager(this);
        messageUtil = new MessageUtil(this);

        commandsLoader.load();
        listenersLoader.load();
    }

    @Override
    public void onDisable() { }

    public TeleportManager getTeleportManager() {
        return teleportManager;
    }
}
