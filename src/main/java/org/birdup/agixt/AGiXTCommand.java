package org.birdup.agixt;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AGiXTCommand implements CommandExecutor {

    private final AGiXT plugin;
    private final AGiXTAPI agiXTAPI;

    public AGiXTCommand(AGiXT plugin, AGiXTAPI agiXTAPI) {
        this.plugin = plugin;
        this.agiXTAPI = agiXTAPI;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("getProviders")) {
            try {
                JsonArray providers = agiXTAPI.getProviders();
                StringBuilder responseMessage = new StringBuilder("AGiXT Providers:\n");
                for (JsonElement provider : providers) {
                    responseMessage.append(provider.getAsString()).append("\n");
                }
                sender.sendMessage(responseMessage.toString());
            } catch (Exception e) {
                sender.sendMessage("Error retrieving providers: " + e.getMessage());
            }
            return true;
        }
        return false;
    }
}
