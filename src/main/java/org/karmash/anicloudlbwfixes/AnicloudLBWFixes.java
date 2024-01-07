package org.karmash.anicloudlbwfixes;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AnicloudLBWFixes extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if (e.getTo().getY() >= 100
                && (e.getTo().getY() - e.getFrom().getY() > 0)
                && !(e.getPlayer().getGameMode().equals(GameMode.SPECTATOR))){
            Player player = e.getPlayer();
            Location location = player.getLocation();
            location.setY(location.getY()-10);
            player.teleport(location);
            player.sendMessage(Component.text(":error_red: ")
                    .append(Component.text("Вы слишком высоко!").color(NamedTextColor.RED)));
        }
    }
    @EventHandler
    public void onExplosion(EntityExplodeEvent e){
        e.blockList().removeIf(block -> block.getBlockData().getMaterial().toString().toLowerCase().contains("glass"));
            }
        }
