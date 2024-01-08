package net.kozibrodka.sdk_api.events.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerBase;

public interface WW2Tank {

    void firePrimaryKey(PlayerBase entityplayer);

    void fireSecondaryKey(PlayerBase entityplayer);

    void inventoryKey(Minecraft minecraft, PlayerBase entityplayer);

    void exitKey(PlayerBase entityplayer);

    void towKey(PlayerBase entityplayer);

    void reloadKey(PlayerBase entityplayer);

    int getPercentHealth();

}
