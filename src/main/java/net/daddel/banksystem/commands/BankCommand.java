package net.daddel.banksystem.commands;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.UserDoesNotExistException;
import me.clip.placeholderapi.PlaceholderAPI;
import net.daddel.banksystem.Main;
import net.daddel.banksystem.utilities.DatabaseManager;
import net.daddel.banksystem.utilities.EconomyManager;
import net.daddel.banksystem.utilities.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class BankCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("balance")){
                    if(p.hasPermission(Utilities.perms + "balance")){
                        String msg = PlaceholderAPI.setPlaceholders(p, Main.messagesData.getConfig().getString("current-balance"));
                        Utilities.sendColoredMessage(p, msg);
                    }else {
                        Utilities.noperm(p);
                    }
                }else {
                    Utilities.wrongUsage(p);
                }
            }else if(args.length == 2){
                if(args[0].equalsIgnoreCase("deposit")){
                    if(args[1].equalsIgnoreCase(args[1])){
                        if(!isString(args[1])){
                            if(p.hasPermission(Utilities.perms + "deposit")){
                                int addingAmount = Integer.parseInt(args[1]);
                                int currentAmount = DatabaseManager.getAmount(p.getUniqueId());
                                int newAmount = currentAmount + addingAmount;
                                try {
                                    if(Economy.hasEnough(p.getName(), addingAmount)){
                                        DatabaseManager.addAmount(p.getUniqueId(), newAmount);
                                        EconomyManager.removeMoney(p, addingAmount);
                                        String deposit_msg = PlaceholderAPI.setPlaceholders(p, Main.messagesData.getConfig().getString("deposit-money"));
                                        Utilities.sendColoredMessage(p, deposit_msg + addingAmount + "$");

                                        String new_balance = PlaceholderAPI.setPlaceholders(p, Main.messagesData.getConfig().getString("new-balance"));
                                        Utilities.sendColoredMessage(p, new_balance + "$");
                                    }else {
                                        EconomyManager.notEnoughMoney(p);
                                    }
                                }catch (UserDoesNotExistException exception){
                                    exception.printStackTrace();
                                }
                            }else {
                                Utilities.noperm(p);
                            }
                        }else {
                            EconomyManager.invalidAmount(p);
                        }
                    }else {
                        Utilities.wrongUsage(p);
                    }
                }else if(args[0].equalsIgnoreCase("withdraw")){
                    if(args[1].equalsIgnoreCase(args[1])){
                        if(!isString(args[1])){
                            if(p.hasPermission(Utilities.perms + "withdraw")){
                                int removingAmount = Integer.parseInt(args[1]);
                                int currentAmount = DatabaseManager.getAmount(p.getUniqueId());
                                if(currentAmount != 0 && currentAmount >= removingAmount){
                                    DatabaseManager.removeAmount(p.getUniqueId(), removingAmount);
                                    EconomyManager.addMoney(p, removingAmount);
                                    String msg = PlaceholderAPI.setPlaceholders(p, Main.messagesData.getConfig().getString("withdraw-money"));
                                    Utilities.sendColoredMessage(p, msg + removingAmount + "$");
                                }else {
                                    EconomyManager.invalidAmount(p);
                                }
                            }else {
                                Utilities.noperm(p);
                            }
                        }else {
                            EconomyManager.invalidAmount(p);
                        }
                    }else {
                        Utilities.wrongUsage(p);
                    }
                }else {
                    Utilities.wrongUsage(p);
                }
            }else {
                Utilities.wrongUsage(p);
            }
        }else {
            Utilities.consoleError(sender);
        }
        return false;
    }
    private boolean isString(String value){
        try {
            Integer.parseInt(value);
            return false;
        }catch (NumberFormatException e){
            return true;
        }
    }
}
