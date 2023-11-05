package net.glasslauncher.sdk_api.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import net.modificationstation.stationapi.api.util.Null;

public class ItemCasingListener {

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    public static TemplateItemBase itemBulletCasing;
    public static TemplateItemBase itemBulletCasingShell;

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        itemBulletCasing = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "itemBulletCasing")).setTranslationKey(MOD_ID, "itemBulletCasing").setMaxStackSize(64);
        itemBulletCasingShell = (TemplateItemBase) new TemplateItemBase(Identifier.of(MOD_ID, "itemBulletCasingShell")).setTranslationKey(MOD_ID, "itemBulletCasingShell").setMaxStackSize(64);
    }

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        itemBulletCasing.setTexture(Identifier.of(MOD_ID, "item/itemBulletCasing"));
        itemBulletCasingShell.setTexture(Identifier.of(MOD_ID, "item/itemBulletCasingShell"));
    }
}
