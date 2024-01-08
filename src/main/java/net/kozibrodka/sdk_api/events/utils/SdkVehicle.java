package net.kozibrodka.sdk_api.events.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerBase;

public interface SdkVehicle {

    void altFireKey(PlayerBase entityplayer);

    void inventoryAtvKey(Minecraft minecraft, PlayerBase entityplayer);

    void exitKey(PlayerBase entityplayer);

}
