/*
    AdminChannel Plugin for Minecraft Bukkit Servers
    Copyright (C) 2014 Kyle Smith

    This file is part of ChunkClaim.

    ChunkClaim is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    ChunkClaim is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with ChunkClaim.  If not, see <http://www.gnu.org/licenses/>.

    Contact: Kyle Smith <kMaiSmith@gmail.com>
 */


package com.kmaismith.adminchannel;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class AdminChannel extends JavaPlugin {

    private Set<Player> admins;

    public AdminChannel() {
        admins = new HashSet<Player>();
    }

    public void onEnable() {
        PluginManager pluginManager = this.getServer().getPluginManager();

        ChatHandler eventHandler = new ChatHandler(admins);
        pluginManager.registerEvents(eventHandler, this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String description, String[] args) {
        if (!(commandSender instanceof Player)) {
            return false;
        }

        Player sender = (Player) commandSender;

        if (sender.hasPermission("adminchannel.use") && command.getName().equals("adminchannel")) {
            if (admins.contains(sender)) {
                admins.remove(sender);
            } else {
                admins.add(sender);
                sender.sendMessage("Welcome to the admin channel!");
            }
            return true;
        }

        return false;
    }

}
