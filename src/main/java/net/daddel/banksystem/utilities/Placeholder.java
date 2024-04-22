package net.daddel.banksystem.utilities;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.daddel.banksystem.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Placeholder extends PlaceholderExpansion {
    private final JavaPlugin plugin;
    public Placeholder(JavaPlugin plugin){
        this.plugin = plugin;
    }
    @Override
    public String getIdentifier() {
        return "banksystem";
    }

    @Override
    public String getAuthor() {
        return Main.getPlugin().getDescription().getAuthors().toString();
    }

    @Override
    public String getVersion() {
        return Main.getPlugin().getDescription().getVersion();
    }
    @Override
    public String onPlaceholderRequest(Player p, String identifier){
        if(identifier.equalsIgnoreCase("balance")){
            return String.valueOf(DatabaseManager.getAmount(p.getUniqueId()));
        }else if(identifier.equalsIgnoreCase("prefix")){
            return Utilities.prefix;
        }else if(identifier.equalsIgnoreCase("commands")){
            return "/bank <deposit|withdraw|balance> [amount]";
        }else if(identifier.equalsIgnoreCase("")){

        }
        return null;
    }
}
