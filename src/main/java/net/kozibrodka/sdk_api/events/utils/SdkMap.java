package net.kozibrodka.sdk_api.events.utils;

import org.w3c.dom.UserDataHandler;

import java.util.LinkedList;
import java.util.List;

public class SdkMap {
    /**
     * Weapons that has SCOPE feature
     */
    public static List scopedList = new LinkedList(); //Item

    /**
     * Sniper Rifles - BIG scope
     */
    public static List sniperList = new LinkedList(); //Item

    /**
     * Weapons that use OIL as ammo (black GUI)
     */
    public static List oilAmmoList = new LinkedList(); //Item

    /**
     * Weapons that use REDSTONE as ammo (red GUI)
     */
    public static List redAmmoList = new LinkedList(); //Item

    /**
     * Weapons that doesnt reload
     */
    public static List noReloadList = new LinkedList(); //Item

    /**
     * Heavy Machine Gun list - HIGHER recoil/spread mechanics
     */
    public static List minigunList = new LinkedList(); //Item

    /**
     * Vehicles where players cannot use GUNS as PASSENGER
     */
    //TODO: LOGIC - coś tu nie gra, czy w ATV można strzelać???
    public static List pojazdList = new LinkedList(); //Entity

    /**
     * Bullets with custom hitsound - aircraft
     */
    public static List hitsoundList = new LinkedList(); //Entity

    /**
     * Telescopes - HUGE scope
     */
    public static List telescopeList = new LinkedList(); //Item

}
