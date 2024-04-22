package net.daddel.banksystem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.daddel.banksystem.utilities.Utilities.*;

public class BankSystemAdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("reload")){
                    if(p.hasPermission(perms + "reload") || p.hasPermission(x)){
                        p.sendMessage("§7");
                        p.sendMessage("§eYou've successfully reloaded following configurations:");
                        p.sendMessage(" §8» §amessages.yml");
                        p.sendMessage("§c§oIf the changes have not been applied, please restart the server!");
                        p.sendMessage("§7");
                    }else {
                        noperm(p);
                    }
                }else {
                    p.sendMessage("§c/bsa reload");
                }
            }else {
                p.sendMessage("§c/bsa reload");
            }
        }else {
            consoleError(sender);
        }
        return false;
    }
}
