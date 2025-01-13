package net.gildedgoobers.gildedvault.api;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

/**
 * This class represents the display meta (prefix and suffix) of an entity (player or group)
 */
public interface DisplayMeta {

    /**
     * @return The platform that this object is built from
     */
    PermissionsPlatform getPlatform();

    /**
     * Sets the prefix to the given plain text
     * @param prefix The plain text
     */
    default void setTextPrefix(String prefix) {
        setPrefix(Component.text(prefix));
    }

    /**
     * Sets the prefix to the given text as parsed by {@link MiniMessage#miniMessage()}
     * @param prefix The MiniMessage text
     */
    default void setRichPrefix(String prefix) {
        setPrefix(MiniMessage.miniMessage().deserialize(prefix));
    }

    /**
     * Sets the prefix to the given component
     * @param prefix The prefix
     */
    void setPrefix(Component prefix);

    /**
     * @return The visible prefix
     */
    Component getPrefix();

    /**
     * Sets the suffix to the given plain text
     * @param suffix The plain text
     */
    default void setTextSuffix(String suffix) {
        setSuffix(Component.text(suffix));
    }

    /**
     * Sets the suffix to the given text as parsed by {@link MiniMessage#miniMessage()}
     * @param suffix The MiniMessage text
     */
    default void setRichSuffix(String suffix) {
        setSuffix(MiniMessage.miniMessage().deserialize(suffix));
    }

    /**
     * Sets the suffix to the given component
     * @param suffix The suffix
     */
    void setSuffix(Component suffix);

    /**
     * @return The visible suffix
     */
    Component getSuffix();

}
