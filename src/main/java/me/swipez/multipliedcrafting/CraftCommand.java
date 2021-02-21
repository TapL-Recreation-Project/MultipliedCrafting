package me.swipez.multipliedcrafting;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CraftCommand implements CommandExecutor {

    Multipliedcrafting plugin;

    public CraftCommand(Multipliedcrafting plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (sender.hasPermission("craftingmultiply.toggle")){
                if (args.length <= 2){
                    if (args[0].equals("start")){
                        plugin.gamestarted = true;
                        Bukkit.broadcastMessage(ChatColor.GREEN + "Multiplied Crafting challenge has started!");
                    }
                    else if (args[0].equals("stop")){
                        plugin.gamestarted = false;
                        Bukkit.broadcastMessage(ChatColor.GREEN + "Multiplied Crafting challenge has started!");
                    }
                    else if (args[0].equals("value")){
                        if (args.length == 2){
                            try {
                                int value = Integer.parseInt(args[1]);
                                plugin.craftamount.put(p.getUniqueId(), value);
                                p.sendMessage(ChatColor.DARK_PURPLE+"Set your multiplication value to"+ChatColor.YELLOW+" "+value);
                            }
                            catch (NumberFormatException exception){
                                sender.sendMessage(ChatColor.RED+"/craftingchallenge value <number>");
                            }
                        }
                        else {
                            sender.sendMessage(ChatColor.RED+"/craftingchallenge value <number>");
                        }

                    }
                    else if (args[0].equals("reload")) {
                        plugin.reloadConfig();
                        p.sendMessage(ChatColor.GREEN+"Config reloaded!");
                    }
                    else if (!args[0].equals("start") && !args[0].equals("stop") && !args[0].equals("value") && !args[0].equals("reload")){
                        sender.sendMessage(ChatColor.RED+"/craftingchallenge <start/stop/value>");
                        sender.sendMessage(ChatColor.RED+"Start: Starts the challenge");
                        sender.sendMessage(ChatColor.RED+"Stop: Stops the challenge");
                        sender.sendMessage(ChatColor.RED+"Value: Sets your multiplication value");
                        sender.sendMessage(ChatColor.RED+"Reload: Reloads the config");
                    }
                }
                else if (args.length == 0 || args.length > 2){
                    sender.sendMessage(ChatColor.RED+"/craftingchallenge <start/stop/value>");
                    sender.sendMessage(ChatColor.RED+"Start: Starts the challenge");
                    sender.sendMessage(ChatColor.RED+"Stop: Stops the challenge");
                    sender.sendMessage(ChatColor.RED+"Value: sets your multiplication value");
                }
            }
            else{
                sender.sendMessage(ChatColor.RED+"You do not have the required permission!");
            }
        }
        else {
            sender.sendMessage("This command is for players only!");
        }
        return true;
    }
}
