package me.danieljunek17.bootcamp;

import me.danieljunek17.bootcamp.commands.LocationCommand;
import me.danieljunek17.bootcamp.commands.MigrateCommand;
import me.danieljunek17.bootcamp.listeners.EntityDamageListener;
import me.danieljunek17.bootcamp.objects.Location;
import me.danieljunek17.bootcamp.utilities.databasedata.LocationData;
import me.danieljunek17.bootcamp.utilities.YAMLfile;
import me.jarnoboy404.databases.DatabaseConnection;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public final class Minigame extends JavaPlugin {

    private static YAMLfile settings, data;
    public static LocationData locationData;
    public static Minigame instance;

    public DatabaseConnection database;

    @Override
    public void onEnable() {
        instance = this;

        //register (de)serializer
        ConfigurationSerialization.registerClass(Location.class);

        //load all data
        loadYAML();
        intializeYAML();
        if(settings.getString("Storage").equals("mysql")) {
            enableDatabase();
        }

        //register commands
        getCommand("location").setExecutor(new LocationCommand());
        getCommand("migrate").setExecutor(new MigrateCommand());

        //register listeners
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(), this);
    }

    @Override
    public void onDisable() {
        //close the database connection (when database is started)
        if(settings.getString("Storage").equals("mysql")) {
            database.closeDatabase();
        }
    }

    public void loadYAML() {
        // load or create YAML files
        settings = new YAMLfile("Settings.yml", this);
        settings.saveDefaultConfig();
        settings.loadFile();
        data = new YAMLfile("data.yml", this);
        data.saveDefaultConfig();
        data.loadFile();
    }

    public void intializeYAML() {
        //load all data from YAML files
        if(!settings.getString("Storage").equals("local")) return;
        if(!data.isConfigurationSection("location")) return;
        for (String id : data.getConfigurationSection("location").getKeys(false)) {
            Location.Manager.newLocation(Integer.parseInt(id), (Location) data.get("location." + id));
        }
    }

    public void enableDatabase() {
        //Database startup credentials
        database = new DatabaseConnection(
                settings.getString("Database.Host"),
                settings.getInt("Database.Port"),
                settings.getString("Database.Database"),
                settings.getString("Database.User"),
                settings.getString("Database.Password"),
                settings.getInt("Database.MaxPools"));

        //register new databaseconnection, create tables and load all data from MySQL
        locationData = new LocationData(database);
        locationData.createTables();
        locationData.loadLocations();
    }

    public static YAMLfile getSettings() {
        return settings;
    }

    public static YAMLfile getData() {
        return data;
    }

    public static Minigame getInstance() {
        return instance;
    }
}
