package com.itsjustmiaouss.nextcommand.events;

import com.itsjustmiaouss.nextcommand.Main;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityEvent implements Listener {

    private final Main main;

    public EntityEvent(Main main) { this.main = main; }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e){
        World w = e.getEntity().getWorld();

        if(e.getEntity() instanceof TNTPrimed){
            if(main.getConfig().getBoolean("explosion.tnt")) return;
            e.setCancelled(true);
            w.createExplosion(e.getEntity().getLocation(), 0);
        }

        if(e.getEntity() instanceof Creeper){
            if(main.getConfig().getBoolean("explosion.creeper")) return;
            e.setCancelled(true);
            w.createExplosion(e.getEntity().getLocation(), 0);
        }

        if(e.getEntity() instanceof Minecart){
            if(main.getConfig().getBoolean("explosion.minecart")) return;
            e.setCancelled(true);
            w.createExplosion(e.getEntity().getLocation(), 0);
        }

        if(e.getEntity() instanceof Wither || e.getEntity() instanceof WitherSkull){
            if(main.getConfig().getBoolean("explosion.wither")) return;
            e.setCancelled(true);
            w.createExplosion(e.getEntity().getLocation(), 0);
        }

    }

}
