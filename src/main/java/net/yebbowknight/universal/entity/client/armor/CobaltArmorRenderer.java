package net.yebbowknight.universal.entity.client.armor;

import net.yebbowknight.universal.item.custom.CobaltArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class CobaltArmorRenderer extends GeoArmorRenderer<CobaltArmorItem> {
    public CobaltArmorRenderer() {
        super(new CobaltArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorLeftLeg";
        this.leftLegBone = "armorRightLeg";
        this.rightBootBone = "armorLeftBoot";
        this.leftBootBone = "armorRightBoot";
    }
}