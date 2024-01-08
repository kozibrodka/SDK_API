package net.kozibrodka.sdk_api.events.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerBase;

public interface WW2Plane {

    void fireKey(PlayerBase entityplayer);

    void bombKey(PlayerBase entityplayer);

    void inventoryKey(Minecraft minecraft, PlayerBase entityplayer);

    void exitKey(PlayerBase entityplayer);

    void rocketKey(PlayerBase entityplayer);

    void reloadKey(PlayerBase entityplayer);

    int getPercentHealth();
}
