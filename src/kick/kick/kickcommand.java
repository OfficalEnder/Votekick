package kick.kick;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import kick.main.main;

public class kickcommand implements CommandExecutor {
	 
  int counter;
  int time = 30;
  int left = time;

  
  public boolean onCommand(CommandSender cs, Command command, String s, String[] args) {
	   if (cs instanceof Player) {
		   Player p = (Player)cs;
		   if (p.hasPermission("kick.kick")) {
			   if (args.length >= 2) {
				   String reason = "";
				   for (int i = 1; i < args.length; i++) reason = reason + args[i] + " ";
				   final Player t = Bukkit.getPlayer(args[0]);
				   if (t != null) {
					   if (!t.hasPermission("kick.bypass")) {
						   if (!vote.voting) {
							   vote.voting = true;
							   Bukkit.broadcastMessage(" ");
							   Bukkit.broadcastMessage(main.prefix + "§7Es wurde ein §eVotekick §7gestartet§8!");
							   Bukkit.broadcastMessage(main.prefix + "§7Beschuldigter§8: §e" + t.getName());
							   Bukkit.broadcastMessage(main.prefix + "§7Ersteller§8: §e" + p.getName());
							   Bukkit.broadcastMessage(main.prefix + "§7Grund§8: §e" + reason);
							   Bukkit.broadcastMessage(main.prefix + "§7Stimme ab mit §a/ja §7oder §c/nein§8!");
							   Bukkit.broadcastMessage(" ");
							   counter = Integer.valueOf(Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {
								   public void run() {
									   left--;
									   switch(left) {
									   case 20: case 10: case 5: case 4: case 3: case 2:
										   Bukkit.broadcastMessage(main.prefix + "§7Die §eAbstimmung §7endet in §6§l" + left + " Sekunden§7!");
										   break;
									   case 1:
										   Bukkit.broadcastMessage(main.prefix + "§7Die §eAbstimmung §7endet in §6§leiner Sekunde§7!");
										   break;
									   case 0:
										   Bukkit.broadcastMessage(main.prefix + "Die §eAbstimmung §7ist vorbei§8!");
										   Bukkit.broadcastMessage(main.prefix + "Stimmen für §aJa§8: §e" + vote.getJa());
										   Bukkit.broadcastMessage(main.prefix + "Stimmen für §cNein§8: §e" + vote.getNein());
										   vote.voting = false;
										   vote.votes.clear();
										   if (vote.getJa() < vote.getNein()) {
											   Bukkit.broadcastMessage(main.prefix + "§7Der Spieler wurde §cnicht §7bestraft§8!");
										   } else if (vote.getJa() > vote.getNein()) {
											   Bukkit.broadcastMessage(main.prefix + "§7Der Spieler wurde §cbestraft§8!");
											   t.kickPlayer(main.prefix + "§cDu wurdest gekickt! \n §7Grund§8: \n §eVotekick Bestrafung");
										   } else if (vote.getJa() == vote.getNein()) {
											   Bukkit.broadcastMessage(main.prefix + "§c§nUnentschieden§8!");
											   Bukkit.broadcastMessage(main.prefix + "§7Der Spieler wurde §cnicht §7bestraft§8!");
										   } 
										   left = time;
										   vote.ja = 0;
										   vote.nein = 0;
										   Bukkit.getScheduler().cancelTask(counter);
										   break;
									   	   default:
										   break;
									   }
								   }
							   },  0L, 20L));
						   } else p.sendMessage(main.prefix + "§cDerzeitig findet bereits ein Votekick statt!");
					   } else p.sendMessage(main.prefix + "§cDu kannst diesen Spieler nicht kicken!");
				   } else p.sendMessage(main.prefix + "§7Der Spieler §e" + args[0] + " §7existiert §cnicht§8!");
			   } else p.sendMessage(main.prefix + "§7Benutze §c/votekick <Spieler> <Grund>");
		   } else p.sendMessage(main.noperms);
	   }
	   return false;
  }
}
