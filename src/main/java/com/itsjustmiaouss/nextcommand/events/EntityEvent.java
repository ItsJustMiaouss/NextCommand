package com.itsjustmiaouss.nextcommand.events;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityEvent implements Listener {

    private final NextCommand nextCommand;

    public EntityEvent(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        World world = event.getEntity().getWorld();
        Entity eventEntity = event.getEntity();

        if(eventEntity instanceof TNTPrimed) {
            if(nextCommand.getConfigManager().getBoolean("entity-event.protection.tnt")) {
                event.setCancelled(true);
                createFakeExplosion(world, eventEntity);
            }
        }

        if(eventEntity instanceof Creeper) {
            if(nextCommand.getConfigManager().getBoolean("entity-event.protection.creeper")) {
                event.setCancelled(true);
                createFakeExplosion(world, eventEntity);
            }
        }

        if(eventEntity instanceof Minecart) {
            if(nextCommand.getConfigManager().getBoolean("entity-event.protection.tnt-minecart")) {
                event.setCancelled(true);
                createFakeExplosion(world, eventEntity);
            }
        }

        if(eventEntity instanceof Wither || eventEntity instanceof WitherSkull) {
            if(nextCommand.getConfigManager().getBoolean("entity-event.protection.wither")) {
                event.setCancelled(true);
                createFakeExplosion(world, eventEntity);
            }
        }
    }

    private void createFakeExplosion(World world, Entity entity) {
        world.createExplosion(entity.getLocation(), 0);
    }

}
