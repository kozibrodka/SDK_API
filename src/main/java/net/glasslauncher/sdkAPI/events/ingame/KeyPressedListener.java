package net.glasslauncher.sdkAPI.events.ingame;

import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;

public class KeyPressedListener {

    @EventListener
    public void keyPressed(KeyStateChangedEvent event) {
        FabricLoader.getInstance().isModLoaded("twuj sraty");
    }

    public boolean zoomKeyDown;
}
