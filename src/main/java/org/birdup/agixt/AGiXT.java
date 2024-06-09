package org.birdup.agixt;

import org.bukkit.plugin.java.JavaPlugin;

public final class AGiXT extends JavaPlugin {

    private AGiXTAPI agiXTAPI;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        String baseUri = getConfig().getString("base_uri", "http://localhost:7437");
        String apiKey = getConfig().getString("api_key", "");

        agiXTAPI = new AGiXTAPI(baseUri, apiKey);
        getCommand("getProviders").setExecutor(new AGiXTCommand(this, agiXTAPI));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public AGiXTAPI getAGiXTAPI() {
        return agiXTAPI;
    }
}
