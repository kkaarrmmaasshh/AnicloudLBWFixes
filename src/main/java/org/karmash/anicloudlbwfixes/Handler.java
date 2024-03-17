package org.karmash.anicloudlbwfixes;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public class Handler implements Listener {
    private double height_limiter_height,rollback_value;
    private boolean height_limiter_enable,explosion_glass_protection;
    private List<String> whitelist_worlds;
    Handler(){
        FileConfiguration fileConfiguration = AnicloudLBWFixes.config().getConfigs();
        height_limiter_height = fileConfiguration.getDouble("height_limiter.height");
        rollback_value = fileConfiguration.getDouble("height_limiter.rollback_value");
        height_limiter_enable = fileConfiguration.getBoolean("height_limiter.enable");
        explosion_glass_protection = fileConfiguration.getBoolean("explosion_glass_protection");
        whitelist_worlds = fileConfiguration.getStringList("whitelist.worlds");
    }
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if (e.getTo().getY() >= height_limiter_height
                && whitelist_worlds.contains(e.getPlayer().getWorld().getName())
                && (e.getTo().getY() - e.getFrom().getY() > 0)
                &! (e.getPlayer().getGameMode().equals(GameMode.SPECTATOR))
                && height_limiter_enable == true){
            Player player = e.getPlayer();
            Location location = player.getLocation();
            if (rollback_value> 1) {
                location.setY(location.getY() - rollback_value);
                player.teleport(location);
            } else{
                e.setCancelled(true);
            }
            player.sendMessage(Component.text(":error_red: ")
                    .append(Component.text("Вы слишком высоко!").color(NamedTextColor.RED)));
        }
    }
    @EventHandler
    public void onExplosion(EntityExplodeEvent e) {
        if (explosion_glass_protection == true) {
            e.blockList().removeIf(block -> block.getBlockData().getMaterial().toString().toLowerCase().contains("glass"));
        }
    }
}
