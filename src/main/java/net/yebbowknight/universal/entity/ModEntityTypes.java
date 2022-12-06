package net.yebbowknight.universal.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yebbowknight.universal.Crossovermod;
import net.yebbowknight.universal.entity.custom.RaccoonEntity;
import net.yebbowknight.universal.entity.custom.TigerEntity;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Crossovermod.MOD_ID);

    public static final RegistryObject<EntityType<RaccoonEntity>> RACCOON = ENTITY_TYPES.register("raccoon",
            () -> EntityType.Builder.of(RaccoonEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 0.6f)
                    .build(new ResourceLocation(Crossovermod.MOD_ID, "raccoon").toString()));

    public static final RegistryObject<EntityType<TigerEntity>> TIGER = ENTITY_TYPES.register("tiger",
            () -> EntityType.Builder.of(TigerEntity::new, MobCategory.CREATURE)
                    .sized(1f, 0.75f)
                    .build(new ResourceLocation(Crossovermod.MOD_ID, "tiger").toString()));

  //  public static final RegistryObject<EntityType<ModBoatEntity>> BOAT_ENTITY =
  //          ENTITY_TYPES.register("mod_boat", () -> EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new,
  //                          MobCategory.MISC).fireImmune().sized(1.375F, 0.5625F)
  //                  .setCustomClientFactory((spawnEntity, world) -> new ModBoatEntity(world, 0, 0, 0))
  //                  .build("mod_boat"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}