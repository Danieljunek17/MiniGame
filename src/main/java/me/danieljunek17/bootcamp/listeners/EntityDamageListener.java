package me.danieljunek17.bootcamp.listeners;

import com.destroystokyo.paper.Title;
import me.danieljunek17.bootcamp.Minigame;
import me.danieljunek17.bootcamp.objects.Location;
import me.danieljunek17.bootcamp.utilities.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        //check if player is hit by a arrow and check if shooter is player
        if (event.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) return;
        if (!(event.getDamager() instanceof Arrow)) return;
        Arrow arrow = (Arrow) event.getDamager();
        arrow.remove();
        if (!(arrow.getShooter() instanceof Player)) return;
        Player shooter = (Player) arrow.getShooter();
        Player target = (Player) event.getEntity();

        //send the kill message and give the arrow back
        shooter.sendTitle(new Title(Color.Color("&a+1 Kill")));
        shooter.getInventory().addItem(new ItemStack(Material.ARROW));

        //chose a random spawn location
        int random = new Random().nextInt(Location.Manager.getLastId());

        //send the died message and teleport to random spawn location and cancel damage
        Location location = Location.Manager.getLocation(random);
        target.setLastDamage(0);
        target.sendTitle(new Title(Color.Color("&cYou died")));
        target.teleport(new org.bukkit.Location(target.getWorld(), location.getX(), location.getY(), location.getZ()));
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        //check if shooter is a player and if the arrow didn't hit a player
        if(!(event.getEntity().getShooter() instanceof Player)) return;
        if(event.getHitEntity() instanceof Player) return;

        Player player = ((Player) event.getEntity().getShooter()).getPlayer();

        //remove the arrow and start a countdown timer until you get the next arrow
        event.getEntity().remove();
        long duration = TimeUnit.SECONDS.toMillis(10);
        long start = System.currentTimeMillis();
        new BukkitRunnable() {
            public void run() {
                long diff = System.currentTimeMillis() - start;
                //end the countdown and set level/xp to 0
                if (diff > duration) {
                    player.setExp(0F);
                    player.setLevel(0);
                    ItemStack arrow = new ItemStack(Material.ARROW);
                    player.getInventory().addItem(arrow);
                    this.cancel();
                }

                //change level and xp in the countdown
                float percentage = 1F - (float) diff / (float) duration;
                if (percentage < 0.99 && percentage > 0) {
                    player.setLevel(10 - Math.round((diff / 1000)));
                    player.setExp(percentage);
                }

            }
        }.runTaskTimer(Minigame.getInstance(), 0, 1);
    }
}
