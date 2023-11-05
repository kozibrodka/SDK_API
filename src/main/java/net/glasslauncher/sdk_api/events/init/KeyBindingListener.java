package net.glasslauncher.sdk_api.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.options.KeyBinding;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import org.lwjgl.input.Keyboard;

import java.util.*;

public class KeyBindingListener {

    //keybinding
    public static KeyBinding keyBinding_reload;
    public static KeyBinding keyBinding_zoom;
    public static KeyBinding keyBinding_altfire;
    public static KeyBinding keyBinding_bomb;
    public static KeyBinding keyBinding_altinventory;
    public static KeyBinding keyBinding_exit;
    public static KeyBinding keyBinding_rocket;
    public static KeyBinding keyBinding_nightvision;
    public static KeyBinding keyBinding_parachute;

    @EventListener
    public void registerKeyBindings(KeyBindingRegisterEvent event) {
        List<KeyBinding> list = event.keyBindings;
        list.add(keyBinding_reload = new KeyBinding("SDK Reload", Keyboard.KEY_R));
        list.add(keyBinding_zoom = new KeyBinding("SDK Zoom", Keyboard.KEY_Z));
        list.add(keyBinding_nightvision = new KeyBinding("SDK NightVision", Keyboard.KEY_N));
        list.add(keyBinding_parachute = new KeyBinding("SDK Parachute", Keyboard.KEY_P));

        list.add(keyBinding_altinventory = new KeyBinding("SDK/WW2 Vehicle Inventory", Keyboard.KEY_I));
        list.add(keyBinding_altfire = new KeyBinding("SDK/WW2 Vehicle Fire", Keyboard.KEY_LCONTROL));
        list.add(keyBinding_exit = new KeyBinding("SDK/WW2 Vehicle Exit", Keyboard.KEY_E));

        list.add(keyBinding_bomb = new KeyBinding("WW2 Plane Bomb/Tank Shell", Keyboard.KEY_B));
        list.add(keyBinding_rocket = new KeyBinding("WW2 Plane Rocket/Tank Special", Keyboard.KEY_P));
    }
}
