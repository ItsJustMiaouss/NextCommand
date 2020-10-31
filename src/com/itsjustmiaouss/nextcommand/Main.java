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
	public List<Player> afkPlayer = new ArrayList<>();

	private static Main instance;

	@Override
	public void onEnable() {
		saveDefaultConfig();

		instance = this;

		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new PlayerEvent(), this);
		pm.registerEvents(new ChatEvent(), this);
		pm.registerEvents(new EntityEvent(), this);

		getCommand("nextcommand").setExecutor(new NextCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("feed").setExecutor(new FeedCommand());
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("god").setExecutor(new GodCommand());
		getCommand("hat").setExecutor(new HatCommand());
		getCommand("spawn").setExecutor(new SpawnCommand());
		getCommand("broadcast").setExecutor(new BroadcastCommand());
		getCommand("clear").setExecutor(new ClearCommand());
		getCommand("afk").setExecutor(new AfkCommand());

		int pluginId = 6498;
		@SuppressWarnings("unused")
		Metrics metrics = new Metrics(this, pluginId);
	}

	public static Main getInstance() {
		return instance;
	}

}
