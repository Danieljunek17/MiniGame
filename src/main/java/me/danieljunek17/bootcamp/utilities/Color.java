package me.danieljunek17.bootcamp.utilities;

import org.bukkit.ChatColor;

public class Color {

    //translate color codes into colors
    public static String Color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
