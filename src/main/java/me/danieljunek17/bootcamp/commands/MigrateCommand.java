package me.danieljunek17.bootcamp.commands;

import me.danieljunek17.bootcamp.Minigame;
import me.danieljunek17.bootcamp.objects.Location;
import me.danieljunek17.bootcamp.utilities.Color;
import me.danieljunek17.bootcamp.utilities.YAMLfile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MigrateCommand implements CommandExecutor {

    final static YAMLfile data = Minigame.getData();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            //no argument
            sender.sendMessage(Color.Color("&cje moet opgeven naar wat je wil migraten\n&clocal of mysql"));

        } else if (args[0].equals("local")) {
            //migrate to local file structure
            for (int i = 0; i < Location.Manager.locationdata.size(); i++) {
                //get all loaded data and save in local file structure
                data.set("location." + i, Location.Manager.getLocation(i));
                data.saveFile();
            }
            sender.sendMessage(Color.Color("&aAlles uit de database is succesvol gemigreert naar je local file"));

        } else if (args[0].equals("mysql") || args[0].equals("database")) {
            //migrate to MySQL
            Minigame.getInstance().enableDatabase();
            //enable database for when local file structure is chosen in settings
            for (int i = 0; i < Location.Manager.locationdata.size(); i++) {
                //get all loaded data and save in database
                Location location = Location.Manager.getLocation(i);
                Minigame.locationData.newLocation(i, location.getX(), location.getY(), location.getZ());
            }
            Minigame.getInstance().database.closeDatabase();
            sender.sendMessage(Color.Color("&aAlles uit je local files is succesvol gemigreert naar de database"));

        } else {
            //wrong argument(s)
            sender.sendMessage(Color.Color("&cJe moet opgeven naar wat je wil migraten je kan alleen kiezen uit\n&clocal of mysql"));
        }
        return true;
    }
}
