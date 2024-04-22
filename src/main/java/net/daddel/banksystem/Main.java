package net.daddel.banksystem;

import net.daddel.banksystem.commands.BankSystemAdminCommand;
import net.daddel.banksystem.files.databaseData;
import net.daddel.banksystem.files.messagesData;
import net.daddel.banksystem.tabcompleter.BSATabCompleter;
import net.daddel.banksystem.tabcompleter.BSTabCompleter;
import net.daddel.banksystem.utilities.DatabaseManager;
import net.daddel.banksystem.utilities.Placeholder;
import org.bukkit.plugin.java.JavaPlugin;
import net.daddel.banksystem.commands.BankCommand;

import static net.daddel.banksystem.utilities.Utilities.databasecfg;
import static net.daddel.banksystem.utilities.Utilities.version;

public final class Main extends JavaPlugin {
    public static Main plugin;
    public static String host;
    public static String database;
    public static int port;
    public static String username;
    public static String password;
    public static databaseData databaseData;
    public static messagesData messagesData;

    @Override
    public void onEnable() {
        plugin = this;

        databaseData = new databaseData(this);
        messagesData = new messagesData(this);

        getLogger().info("BankSystem " + version + " enabled");

        getCommand("bank").setExecutor(new BankCommand());
        getCommand("bank").setTabCompleter(new BSTabCompleter());
        getCommand("banksystemadmin").setExecutor(new BankSystemAdminCommand());
        getCommand("banksystemadmin").setTabCompleter(new BSATabCompleter());

        //Database
        host = databasecfg.getString("database.host");
        port = databasecfg.getInt("database.port");
        database = databasecfg.getString("database.database");
        username = databasecfg.getString("database.username");
        password = databasecfg.getString("database.password");

        DatabaseManager.connectDB();
        DatabaseManager.createBankSystemTable();

        checkPlugins();
    }

    @Override
    public void onDisable() {
        getLogger().info("BankSystem " + version + " disabled");

        DatabaseManager.disconnectDatabase();
    }

    public static Main getPlugin() {
        return plugin;
    }
    private void checkPlugins(){
        if(getServer().getPluginManager().getPlugin("Essentials") == null){
            getLogger().warning("Could not find Essentials! This plugin is required.");
        }
        if(getServer().getPluginManager().getPlugin("PlaceholderAPI") != null){
            new Placeholder(Main.getPlugin()).register();
        }else {
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
        }
    }
}
