package net.daddel.banksystem.utilities;

import me.clip.placeholderapi.PlaceholderAPI;
import net.daddel.banksystem.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Utilities {
    public static String prefix = "§eBankSystem §8»§7";
    public static String version = "v" + Main.getPlugin().getDescription().getVersion() + "-mc" + Main.getPlugin().getDescription().getAPIVersion();
    public static FileConfiguration databasecfg = Main.databaseData.getConfig();
    public static String perms = "banksystem.";
    public static String x = perms + "*";
    public static void noperm(Player p){
        String msg = PlaceholderAPI.setPlaceholders(p, Main.messagesData.getConfig().getString("no-permissions"));
        sendColoredMessage(p, msg);
    }
    public static void sendMSG(Player p, String msg){
        p.sendMessage(prefix + "§7" + msg);
    }
    public static void consoleError(CommandSender sender){
        sender.sendMessage("The console can't execute this command!");
    }
    public static void sendColoredMessage(Player p, String msg){
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }
    public static void wrongUsage(Player p){
        String msg = PlaceholderAPI.setPlaceholders(p, Main.messagesData.getConfig().getString("wrong-usage"));
        sendColoredMessage(p, msg);
    }
}
