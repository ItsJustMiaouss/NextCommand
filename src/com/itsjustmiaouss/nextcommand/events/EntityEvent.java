package com.itsjustmiaouss.nextcommand.events;

import com.itsjustmiaouss.nextcommand.Main;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityEvent implements Listener {

    private final Main main = Main.getInstance();

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        World world = event.getEntity().getWorld();

        if (event.getEntity() instanceof TNTPrimed) {
            if (main.getConfig().getBoolean("explosion.tnt")) return;
            event.setCancelled(true);
            world.createExplosion(event.getEntity().getLocation(), 0);
        }

        if (event.getEntity() instanceof Creeper) {
            if (main.getConfig().getBoolean("explosion.creeper")) return;
            event.setCancelled(true);
            world.createExplosion(event.getEntity().getLocation(), 0);
        }

        if (event.getEntity() instanceof Minecart) {
            if (main.getConfig().getBoolean("explosion.minecart")) return;
            event.setCancelled(true);
            world.createExplosion(event.getEntity().getLocation(), 0);
        }

        if (event.getEntity() instanceof Wither || event.getEntity() instanceof WitherSkull) {
            if (main.getConfig().getBoolean("explosion.wither")) return;
            event.setCancelled(true);
            world.createExplosion(event.getEntity().getLocation(), 0);
        }

    }

}
