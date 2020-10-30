package com.itsjustmiaouss.nextcommand;

import com.itsjustmiaouss.nextcommand.bstats.Metrics;
import com.itsjustmiaouss.nextcommand.commands.*;
import com.itsjustmiaouss.nextcommand.events.ChatEvent;
import com.itsjustmiaouss.nextcommand.events.EntityEvent;
import com.itsjustmiaouss.nextcommand.events.PlayerEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements Listener {

	public String prefix = getConfig().getString("prefix").replaceAll("&", "§") + " ";
	public String error_prefix = getConfig().getString("prefix-error").replaceAll("&", "§") + " ";

	public List<Player> godPlayer = new ArrayList<>();

	@Override
	public void onEnable() {
		saveDefaultConfig();

		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new PlayerEvent(this), this);
		pm.registerEvents(new ChatEvent(this), this);
		pm.registerEvents(new EntityEvent(this), this);

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
