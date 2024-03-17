package org.karmash.anicloudlbwfixes;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class AnicloudLBWFixes extends JavaPlugin implements Listener {

    public static JavaPlugin plugin(){
        return instance;
    }
    public static Config config(){
        return configinstance;
    }
    private static JavaPlugin instance;
    private static Config configinstance;
    @Override
    public void onEnable() {
        instance = this;
        configinstance = new Config();
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new Handler(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
