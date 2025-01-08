package net.gildedgoobers.gildedvault.core;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.papermc.paper.threadedregions.scheduler.AsyncScheduler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CompletableFuture;

public class UpdateChecker {

    private static final URI LATEST_RELEASE_URI = URI.create("https://api.github.com/repos/GildedGoobers/GildedVault/releases/latest");

    private final GildedVault plugin;
    private final AsyncScheduler scheduler;
    private final int currentVersion;
    public UpdateChecker(GildedVault plugin) {
        this.plugin = plugin;
        this.scheduler = plugin.getServer().getAsyncScheduler();

        String pluginVersion = plugin.getPluginMeta().getVersion();
        this.currentVersion = parseVersion(pluginVersion);
    }

    public CompletableFuture<Boolean> isOutdatedAsync() {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        scheduler.runNow(plugin, task -> future.complete(isOutdated()));
        return future;
    }

    public boolean isOutdated() {
        String latestVersion = fetchLatestVersion();
        return latestVersion != null && parseVersion(latestVersion) >= currentVersion;
    }

    @Nullable
    public String fetchLatestVersion() {
        try {
            URL url = URL.of(LATEST_RELEASE_URI, null);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            final String response = readAll(reader);
            JsonObject releaseJson = JsonParser.parseString(response).getAsJsonObject();
            return releaseJson.get("tag_name").getAsString();
        } catch (IOException e) {
            plugin.getLogger().throwing("GildedVault", "fetchLatestVersion", e);
            return null;
        }
    }

    @NotNull
    public static String readAll(@NotNull BufferedReader reader) throws IOException {
        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();
        while ((inputLine = reader.readLine()) != null)
            stringBuilder.append(inputLine);
        return stringBuilder.toString();
    }

    public static int parseVersion(@NotNull String version) {
        int versionMetaDataSeparator = version.indexOf("-");
        String majorMinorPatch = versionMetaDataSeparator == -1 ? version : version.substring(0, versionMetaDataSeparator);
        String[] majorMinorPatchArr = majorMinorPatch.split("\\.");
        byte major = Byte.parseByte(majorMinorPatchArr[0]);
        byte minor = Byte.parseByte(majorMinorPatchArr[1]);
        byte patch = Byte.parseByte(majorMinorPatchArr[2]);
        return major << 16 | minor << 8 | patch;
    }

}
