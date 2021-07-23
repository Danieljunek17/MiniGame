package me.danieljunek17.bootcamp.objects;

import me.danieljunek17.bootcamp.Minigame;
import me.danieljunek17.bootcamp.utilities.Color;
import me.danieljunek17.bootcamp.utilities.YAMLfile;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Location implements ConfigurationSerializable {

    final static YAMLfile settings = Minigame.getSettings();
    final static YAMLfile data = Minigame.getData();

    private int x, y, z;

    public Location(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Location deserialize(Map<String, Object> map) {
        return new Location((Integer) map.get("x"), (Integer) map.get("y"), (Integer) map.get("z"));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("x", x);
        map.put("y", y);
        map.put("z", z);
        return map;
    }

    public static class Manager {
        public final static Map<Integer, Location> locationdata = new HashMap<>();

        //create a new location
        public static void newLocation(int id, Location location) {
            locationdata.put(id, location);

            //check the storage method and save accordingly
            String type = settings.getString("Storage");
            if (type.equals("local")) {
                data.set("location." + id, location);
                data.saveFile();
            } else if (type.equals("mysql")) {
                Minigame.locationData.newLocation(id, location.getX(), location.getY(), location.getZ());
            } else {
                Bukkit.broadcastMessage(Color.Color("&c&lDoor een fout type storagemethode in de config resetten de locaties als je restart"));
            }
        }

        //get the last id
        public static int getLastId() {
            return locationdata.size();
        }

        //get location from id
        public static Location getLocation(int id) {
            return locationdata.get(id);
        }

    }
}
