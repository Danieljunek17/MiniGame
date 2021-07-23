package me.danieljunek17.bootcamp.commands;

import me.danieljunek17.bootcamp.Minigame;
import me.danieljunek17.bootcamp.objects.Location;
import me.danieljunek17.bootcamp.utilities.Color;
import me.danieljunek17.bootcamp.utilities.YAMLfile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocationCommand implements CommandExecutor {

    final YAMLfile settings = Minigame.getSettings();
    final YAMLfile data = Minigame.getData();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = ((Player) sender).getPlayer();

        if (args.length == 0) {
            //no arguments
            player.sendMessage(Color.Color("&cje moet een locatie toevoegen of verwijderen"));
        } else if (args[0].equals("add")) {
            //add command
            org.bukkit.Location location = player.getLocation();

            int x = location.getBlockX();
            int y = location.getBlockY();
            int z = location.getBlockZ();

            Location.Manager.newLocation(Location.Manager.getLastId(), new Location(x, y, z));
            player.sendMessage(Color.Color("&cJe hebt een nieuwe locatie toegevoegd op: " + x + " " + y + " " + z));

        } else if (args[0].equals("remove")) {
            //remove command
            player.sendMessage(Color.Color("&cJe kan dit handmatig in de data file doen of in de database!"));
        } else if (args[0].equals("list")) {
            //list command
            for (int i = 0; i < Location.Manager.locationdata.size(); i++) {
                //list all locations and send message with the locations
                Location location = Location.Manager.getLocation(i);
                player.sendMessage(Color.Color("&c" + i + ": " + location.getX() + ", " + location.getY() + ", " + location.getZ()));
            }

        } else if (args[0].equals("help")) {
            //help command
            player.sendMessage(Color.Color("&aJe kan /location (add/remove/list/help) doen"));

        } else {
            //non excisting argument
            player.sendMessage(Color.Color("&cje moet een locatie toevoegen of verwijderen"));
        }
        return true;
    }
}
