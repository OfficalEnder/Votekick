package kick.kick;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import kick.main.main;

public class jacmd implements CommandExecutor {
	public boolean onCommand(CommandSender cs, Command command, String s, String[] args) {
		if (cs instanceof Player) {
			Player p = (Player) cs;
			if (vote.voting) {
				vote.addJa(p);
				vote.votes.put(p, p.getName());
			} else {
				p.sendMessage(main.prefix + "Es findet derzeit §ckein §7Voting statt§8!");
			}
		} else {
			cs.sendMessage(main.noperms);
		}
		return false;
	}
}
