package com.itsjustmiaouss.nextcommand;

import java.util.ArrayList;
import java.util.List;

import com.itsjustmiaouss.nextcommand.commands.*;
import com.itsjustmiaouss.nextcommand.events.EntityEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.itsjustmiaouss.nextcommand.bstats.Metrics;
import com.itsjustmiaouss.nextcommand.events.ChatEvent;
import com.itsjustmiaouss.nextcommand.events.PlayerEvent;

public class Main extends JavaPlugin implements Listener {
	
	public String prefix = (getConfig().getString("prefix").replaceAll("&", "§") + " ").toString();
	public String prefixerror = (getConfig().getString("prefix-error").replaceAll("&", "§") + " ").toString();
	
	public List<Player> godPlayer = new ArrayList<>();

	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		getServer().getPluginManager().registerEvents(new PlayerEvent(this), this);
		getServer().getPluginManager().registerEvents(new ChatEvent(this), this);
		getServer().getPluginManager().registerEvents(new EntityEvent(this), this);

		getCommand("nextcommand").setExecutor(new NextCommand(this));
		getCommand("fly").setExecutor(new FlyCommand(this));
		getCommand("feed").setExecutor(new FeedCommand(this));
		getCommand("heal").setExecutor(new HealCommand(this));
		getCommand("god").setExecutor(new GodCommand(this));
		getCommand("hat").setExecutor(new HatCommand(this));
		getCommand("spawn").setExecutor(new SpawnCommand(this));
		getCommand("broadcast").setExecutor(new BroadcastCommand(this));
		getCommand("clear").setExecutor(new ClearCommand(this));
		
		int pluginId = 6498;
		@SuppressWarnings("unused")
		Metrics metrics = new Metrics(this, pluginId);
	}
}
