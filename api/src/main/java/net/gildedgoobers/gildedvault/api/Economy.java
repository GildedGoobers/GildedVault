package net.gildedgoobers.gildedvault.api;

import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * This interface provides the basic functionality used when dealing with an economy.
 * Implement this interface to allow functions to interact with your implementation.
 */
public interface Economy {

    /**
     * @return The name of this economy
     */
    String getName();

    /**
     * @return The name of this currency in singular form
     */
    String getSingularCurrencyName();


    /**
     * @return The name of this currency in plural form
     */
    String getPluralCurrencyName();

    /**
     * @param amount The relevant amount
     * @return If the amount is non-zero and between -1 and 1, returns the singular currency name. Otherwise, returns the plural currency name.
     */
    default String getCurrencyName(double amount) {
        return -1 <= amount && amount <= 1 && amount != 0 ? getSingularCurrencyName() : getPluralCurrencyName();
    }

    /**
     * @return The amount formatted to be more human-readable
     */
    Component formatToComponent(double amount);

    /**
     * @return The amount formatted to be more human-readable
     */
    String format(double amount);

    /**
     * @param player The player
     * @return The balance of the player
     */
    default double getBalance(@NotNull OfflinePlayer player) {
        return getBalance(player.getUniqueId());
    }

    /**
     * @param uniqueId The uuid of the player
     * @return The balance of the player by the uuid
     */
    double getBalance(@NotNull UUID uniqueId);

    /**
     * Sets the balance of the player to the amount.
     * @param player The player
     * @param amount The new amount
     * @return The new amount
     */
    default double setBalance(@NotNull OfflinePlayer player, double amount) {
        return setBalance(player.getUniqueId(), amount);
    }

    /**
     * Sets the balance of the player by the uuid to the amount.
     * @param uniqueId The uuid of the player
     * @param amount The new amount
     * @return The new amount
     */
    double setBalance(@NotNull UUID uniqueId, double amount);

    /**
     * Adds the amount to the balance of the player.
     * @param player The player
     * @param amount The amount to add
     * @return The new amount
     */
    default double addBalance(@NotNull OfflinePlayer player, double amount) {
        return addBalance(player.getUniqueId(), amount);
    }

    /**
     * Adds the amount to the balance of the player by the uuid.
     * @param uniqueId The uuid of the player
     * @param amount The amount to add
     * @return The new amount
     */
    double addBalance(@NotNull UUID uniqueId, double amount);

    /**
     * Subtracts the amount to the balance of the player.
     * @param player The player
     * @param amount The amount to subtract
     * @return The new amount
     */
    default double subtractBalance(@NotNull OfflinePlayer player, double amount) {
        return subtractBalance(player.getUniqueId(), amount);
    }

    /**
     * Adds the amount to the balance of the player by the uuid.
     * @param uniqueId The uuid of the player
     * @param amount The amount to subtract
     * @return The new amount
     */
    double subtractBalance(@NotNull UUID uniqueId, double amount);

    /**
     * @param player The player
     * @param amount The minimum amount
     * @return true if the player has at least the amount specified
     */
    default boolean hasBalance(@NotNull OfflinePlayer player, double amount) {
        return hasBalance(player.getUniqueId(), amount);
    }

    /**
     * @param uniqueId The uuid of the player
     * @param amount The minimum amount
     * @return true if the player by the uuid has at least the amount specified
     */
    default boolean hasBalance(@NotNull UUID uniqueId, double amount) {
        return getBalance(uniqueId) >= amount;
    }

}
