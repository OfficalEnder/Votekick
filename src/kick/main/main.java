package kick.main;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import kick.kick.jacmd;
import kick.kick.neincmd;
import kick.kick.kickcommand;

public class main extends JavaPlugin implements Listener {
  public static String prefix = "Votekick > ";
  public static String noperms = prefix + "Dazu hast du keine Rechte";

  Integer counter;
  int time = 301;
  
  public void onEnable() {
	  Bukkit.getConsoleSender().sendMessage(" ");
	  Bukkit.getConsoleSender().sendMessage("§8>> §6Enders Votekick §8| §6Status§8: §aaktiviert§8!");
	  Bukkit.getConsoleSender().sendMessage(" ");
    
    init(Bukkit.getPluginManager());

  }
  
  public void init(PluginManager pm) {
	  
	  getCommand("ja").setExecutor((CommandExecutor)new jacmd());
	  getCommand("nein").setExecutor((CommandExecutor)new neincmd());
	  getCommand("votekick").setExecutor((CommandExecutor)new kickcommand());
	  
	  
  }

  
  public void onDisable() {
	  Bukkit.getConsoleSender().sendMessage(" ");
	  Bukkit.getConsoleSender().sendMessage("§8>> §6Enders Votekick §8| §6Status§8: §cdeaktiviert§8!");
	  Bukkit.getConsoleSender().sendMessage(" ");
  }
  
  public static Plugin getInstance() {
	     return (Plugin)getPlugin(main.class);
	   }

}