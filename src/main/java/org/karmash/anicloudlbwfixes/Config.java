package org.karmash.anicloudlbwfixes;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Config {
    private File config;
    private FileConfiguration configs;
    Config() {
        config = new File(AnicloudLBWFixes.plugin().getDataFolder(), "config.yml");
        configs = YamlConfiguration.loadConfiguration(config);
        List<String> worlds = new ArrayList<>();
        worlds.add("world");
        worlds.add("world2");
        add("height_limiter.enable",true);
        add("height_limiter.height",120);
        add("height_limiter.rollback_value",0);
        add("explosion_glass_protection",true);
        add("whitelist.worlds",worlds);
        save();
    }
    public void save(){
        try {
            configs.save(config);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public File getConfig() {
        return config;
    }
    public void add(String str, Object object){
        if (configs.get(str) == null){
            configs.set(str, object);
        }
    }

    public FileConfiguration getConfigs() {
        return configs;
    }
}
