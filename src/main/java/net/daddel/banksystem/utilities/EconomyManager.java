package net.daddel.banksystem.utilities;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import me.clip.placeholderapi.PlaceholderAPI;
import net.daddel.banksystem.Main;
import net.ess3.api.MaxMoneyException;
import org.bukkit.entity.Player;

import static net.daddel.banksystem.utilities.Utilities.prefix;
import static net.daddel.banksystem.utilities.Utilities.sendColoredMessage;

public class EconomyManager {
    public static void addMoney(Player p, int amount){
        try {
            Economy.add(p.getName(), amount);
        }catch (UserDoesNotExistException | MaxMoneyException | NoLoanPermittedException exception){
            exception.printStackTrace();
        }
    }
    public static void removeMoney(Player p, int amount){
        try {
            if(Economy.hasEnough(p.getName(), amount)){
                try {
                    Economy.subtract(p.getName(), amount);
                }catch (UserDoesNotExistException | MaxMoneyException | NoLoanPermittedException exception){
                    exception.printStackTrace();
                }
            }else {
                notEnoughMoney(p);
            }
        }catch (UserDoesNotExistException exception){
            exception.printStackTrace();
        }
    }
    public static void notEnoughMoney(Player p){
        String msg = PlaceholderAPI.setPlaceholders(p, Main.messagesData.getConfig().getString("not-enough-money"));
        sendColoredMessage(p, msg);
    }
    public static void invalidAmount(Player p){
        String msg = PlaceholderAPI.setPlaceholders(p, Main.messagesData.getConfig().getString("invalid-amount"));
        Utilities.sendColoredMessage(p, msg);
    }
}
