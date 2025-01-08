package net.gildedgoobers.gildedvault.core;

import org.bukkit.plugin.java.JavaPlugin;

public final class GildedVault extends JavaPlugin {

    private final UpdateChecker updateChecker = new UpdateChecker(this);

    @Override
    public void onEnable() {
        updateChecker.isOutdatedAsync().thenAccept(outdated -> {
                if (outdated) {
                    getLogger().warning("This is not the latest version of GildedVault!");
                    getLogger().warning("Please download the latest version here: https://github.com/GildedGoobers/GildedVault/releases/tag/");
                }
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
