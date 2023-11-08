package net.kozibrodka.sdk_api.events.ingame;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.sdk_api.events.init.KeyBindingListener;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;
import org.lwjgl.input.Keyboard;

public class KeyPressedListener {

    @EventListener
    public void keyPressed(KeyStateChangedEvent event) {
        FabricLoader.getInstance().isModLoaded("twuj sraty");
    }

    public boolean zoomKeyDown;
}
