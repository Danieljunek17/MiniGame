package me.danieljunek17.bootcamp.utilities;

import org.bukkit.Color;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class YAMLfile {

    private Plugin plugin;
    private File file;
    private FileConfiguration fileConfiguration;

    public YAMLfile(String path, JavaPlugin plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder() + File.separator + path);
    }

    public void loadFile() {
        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);

        if (file.exists()) {
            try {
                fileConfiguration.load(file);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        } else {
            try {
                fileConfiguration.options().copyDefaults(true);
                fileConfiguration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveDefaultConfig() {
        if (!file.exists()) plugin.saveResource(file.getName(), false);
        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void saveFile() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDefault(String path, Object value) {
        fileConfiguration.addDefault(path, value);
    }

    public void addDefaults(Configuration defaults) {
        fileConfiguration.addDefaults(defaults);
    }

    public void addDefault(Map<String, Object> defaults) {
        fileConfiguration.addDefaults(defaults);
    }

    public boolean contains(String arg0) {
        return fileConfiguration.contains(arg0);
    }

    public boolean contains(String arg0, boolean arg1) {
        return fileConfiguration.contains(arg0, arg1);
    }

    public ConfigurationSection createSection(String arg0) {
        return fileConfiguration.createSection(arg0);
    }

    public ConfigurationSection createSection(String arg0, Map<?, ?> arg1) {
        return fileConfiguration.createSection(arg0, arg1);
    }

    public boolean equals(Object obj) {
        return fileConfiguration.equals(obj);
    }

    public Object get(String arg0) {
        return fileConfiguration.get(arg0);
    }

    public Object get(String arg0, Object arg1) {
        return fileConfiguration.get(arg0, arg1);
    }

    public boolean getBoolean(String arg0) {
        return fileConfiguration.getBoolean(arg0);
    }

    public boolean getBoolean(String arg0, boolean arg1) {
        return fileConfiguration.getBoolean(arg0, arg1);
    }

    public List<Boolean> getBooleanList(String arg0) {
        return fileConfiguration.getBooleanList(arg0);
    }

    public List<Byte> getByteList(String arg0) {
        return fileConfiguration.getByteList(arg0);
    }

    public List<Character> getCharacterList(String arg0) {
        return fileConfiguration.getCharacterList(arg0);
    }

    public Color getColor(String arg0) {
        return fileConfiguration.getColor(arg0);
    }

    public Color getColor(String arg0, Color arg1) {
        return fileConfiguration.getColor(arg0, arg1);
    }

    public ConfigurationSection getConfigurationSection(String arg0) {
        return fileConfiguration.getConfigurationSection(arg0);
    }

    public String getCurrentPath() {
        return fileConfiguration.getCurrentPath();
    }

    public Configuration getDefaults() {
        return fileConfiguration.getDefaults();
    }

    public ConfigurationSection getDefaultSection() {
        return fileConfiguration.getDefaultSection();
    }

    public double getDouble(String arg0) {
        return fileConfiguration.getDouble(arg0);
    }

    public double getDouble(String arg0, double arg1) {
        return fileConfiguration.getDouble(arg0, arg1);
    }

    public List<Double> getDoubleList(String arg0) {
        return fileConfiguration.getDoubleList(arg0);
    }

    public List<Float> getFloatList(String arg0) {
        return fileConfiguration.getFloatList(arg0);
    }

    public int getInt(String arg0) {
        return fileConfiguration.getInt(arg0);
    }

    public int getInt(String arg0, int arg1) {
        return fileConfiguration.getInt(arg0, arg1);
    }

    public List<Integer> getIntegerList(String arg0) {
        return fileConfiguration.getIntegerList(arg0);
    }

    public ItemStack getItemStack(String arg0) {
        return fileConfiguration.getItemStack(arg0);
    }

    public ItemStack getItemStack(String arg0, ItemStack arg1) {
        return fileConfiguration.getItemStack(arg0, arg1);
    }

    public Set<String> getKeys(boolean arg0) {
        return fileConfiguration.getKeys(arg0);
    }

    public List<?> getList(String arg0) {
        return fileConfiguration.getList(arg0);
    }

    public List<?> getList(String arg0, List<?> arg1) {
        return fileConfiguration.getList(arg0, arg1);
    }

    public Long getLong(String arg0) {
        return fileConfiguration.getLong(arg0);
    }

    public Long getLong(String arg0, Long arg1) {
        return fileConfiguration.getLong(arg0, arg1);
    }

    public List<Map<?, ?>> getMapList(String arg0) {
        return fileConfiguration.getMapList(arg0);
    }

    public String getName() {
        return fileConfiguration.getName();
    }

    public OfflinePlayer getOfflinePlayer(String arg0) {
        return fileConfiguration.getOfflinePlayer(arg0);
    }

    public OfflinePlayer getOfflinePlayer(String arg0, OfflinePlayer arg1) {
        return fileConfiguration.getOfflinePlayer(arg0, arg1);
    }

    public ConfigurationSection getParent() {
        return fileConfiguration.getParent();
    }

    public Configuration getRoot() {
        return fileConfiguration.getRoot();
    }

    public List<Short> getShortList(String arg0) {
        return fileConfiguration.getShortList(arg0);
    }

    public String getString(String arg0) {
        return fileConfiguration.getString(arg0);
    }

    public String getString(String arg0, String arg1) {
        return fileConfiguration.getString(arg0);
    }

    public List<String> getStringList(String arg0) {
        return fileConfiguration.getStringList(arg0);
    }

    public Map<String, Object> getValues(boolean arg0) {
        return fileConfiguration.getValues(arg0);
    }

    public Vector getVector(String arg0) {
        return fileConfiguration.getVector(arg0);
    }

    public Vector getVector(String arg0, Vector arg1) {
        return fileConfiguration.getVector(arg0, arg1);
    }

    public boolean isBoolean(String arg0) {
        return fileConfiguration.isBoolean(arg0);
    }

    public boolean isColor(String arg0) {
        return fileConfiguration.isColor(arg0);
    }

    public boolean isConfigurationSection(String arg0) {
        return fileConfiguration.isConfigurationSection(arg0);
    }

    public boolean isDouble(String arg0) {
        return fileConfiguration.isDouble(arg0);
    }

    public boolean isInt(String arg0) {
        return fileConfiguration.isInt(arg0);
    }

    public boolean isItemStack(String arg0) {
        return fileConfiguration.isItemStack(arg0);
    }

    public boolean isList(String arg0) {
        return fileConfiguration.isList(arg0);
    }

    public boolean isLong(String arg0) {
        return fileConfiguration.isLong(arg0);
    }

    public boolean isOfflinePlayer(String arg0) {
        return fileConfiguration.isOfflinePlayer(arg0);
    }

    public boolean isSet(String arg0) {
        return fileConfiguration.isSet(arg0);
    }

    public boolean isString(String arg0) {
        return fileConfiguration.isString(arg0);
    }

    public boolean isVector(String arg0) {
        return fileConfiguration.isVector(arg0);
    }

    public void loadFromString(String arg0) {
        try {
            fileConfiguration.loadFromString(arg0);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfigurationOptions options() {
        return fileConfiguration.options();
    }

    public void saveToString() {
        fileConfiguration.saveToString();
    }

    public void set(String arg0, Object arg1) {
        fileConfiguration.set(arg0, arg1);
    }

    public void setDefaults(Configuration defaults) {
        fileConfiguration.setDefaults(defaults);
    }

    @SuppressWarnings("static-access")
    public String createPath(ConfigurationSection section, String key) {
        return fileConfiguration.createPath(section, key);
    }

    @SuppressWarnings("static-access")
    public String createPath(ConfigurationSection section, String key, ConfigurationSection relativeTo) {
        return fileConfiguration.createPath(section, key, relativeTo);
    }

}
