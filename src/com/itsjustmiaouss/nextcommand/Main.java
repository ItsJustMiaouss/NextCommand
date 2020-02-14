package com.itsjustmiaouss.nextcommand;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.itsjustmiaouss.nextcommand.bstats.Metrics;
import com.itsjustmiaouss.nextcommand.commands.BroadcastCommand;
import com.itsjustmiaouss.nextcommand.commands.FeedCommand;
import com.itsjustmiaouss.nextcommand.commands.FlyCommand;
import com.itsjustmiaouss.nextcommand.commands.GodCommand;
import com.itsjustmiaouss.nextcommand.commands.HatCommand;
import com.itsjustmiaouss.nextcommand.commands.HealCommand;
import com.itsjustmiaouss.nextcommand.commands.NextCommand;
import com.itsjustmiaouss.nextcommand.commands.SpawnCommand;
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
		
		getCommand("nextcommand").setExecutor(new NextCommand(this));
		getCommand("fly").setExecutor(new FlyCommand(this));
		getCommand("feed").setExecutor(new FeedCommand(this));
		getCommand("heal").setExecutor(new HealCommand(this));
		getCommand("god").setExecutor(new GodCommand(this));
		getCommand("hat").setExecutor(new HatCommand(this));
		getCommand("spawn").setExecutor(new SpawnCommand(this));
		getCommand("broadcast").setExecutor(new BroadcastCommand(this));
		
		int pluginId = 6498;
		@SuppressWarnings("unused")
		Metrics metrics = new Metrics(this, pluginId);
	}
}
