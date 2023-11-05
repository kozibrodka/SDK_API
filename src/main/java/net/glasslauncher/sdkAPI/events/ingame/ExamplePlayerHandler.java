package net.glasslauncher.sdkAPI.events.ingame;

import net.modificationstation.stationapi.api.entity.player.PlayerHandler;

public class ExamplePlayerHandler implements PlayerHandler {

    @Override
    public boolean respawn() {
        System.out.println("SDK_API respawned player");
        return false;
    }


}
