package com.itsjustmiaouss.nextcommand.events;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityEvent implements Listener {

    private NextCommand nextCommand;
    private ConfigManager configManager;

    public EntityEvent(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
        this.configManager = nextCommand.getConfigManager();
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        World world = event.getEntity().getWorld();
        Entity eventEntity = event.getEntity();

        if(eventEntity instanceof TNTPrimed) {
            if(configManager.getBoolean("entity-event.protection.tnt")) {
                event.setCancelled(true);
                createFakeExplosion(world, eventEntity);
            }
        }

        if(eventEntity instanceof Creeper) {
            if(configManager.getBoolean("entity-event.protection.creeper")) {
                event.setCancelled(true);
                createFakeExplosion(world, eventEntity);
            }
        }

        if(eventEntity instanceof Minecart) {
            if(configManager.getBoolean("entity-event.protection.tnt-minecart")) {
                event.setCancelled(true);
                createFakeExplosion(world, eventEntity);
            }
        }

        if(eventEntity instanceof Wither || eventEntity instanceof WitherSkull) {
            if(configManager.getBoolean("entity-event.protection.wither")) {
                event.setCancelled(true);
                createFakeExplosion(world, eventEntity);
            }
        }
    }

    private void createFakeExplosion(World world, Entity entity) {
        world.createExplosion(entity.getLocation(), 0);
    }

}
