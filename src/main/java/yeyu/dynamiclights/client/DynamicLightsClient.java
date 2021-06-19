package yeyu.dynamiclights.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class DynamicLightsClient implements ClientModInitializer {
    public static Logger LOGGER = LogManager.getLogger();
    public static Map<Item, Integer> ITEM_BLOCK_LIGHT_LEVEL = new HashMap<>();

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initialized dynamic lighting");
        DynamicLightsConfig.bootstrap();

        ITEM_BLOCK_LIGHT_LEVEL.put(Items.BEACON, Blocks.BEACON.getDefaultState().getLuminance());
        ITEM_BLOCK_LIGHT_LEVEL.put(Items.CAMPFIRE, Blocks.CAMPFIRE.getDefaultState().getLuminance());
        ITEM_BLOCK_LIGHT_LEVEL.put(Items.SOUL_CAMPFIRE, Blocks.SOUL_CAMPFIRE.getDefaultState().getLuminance());
        ITEM_BLOCK_LIGHT_LEVEL.put(Items.END_ROD, Blocks.END_ROD.getDefaultState().getLuminance());
        ITEM_BLOCK_LIGHT_LEVEL.put(Items.GLOWSTONE, Blocks.GLOWSTONE.getDefaultState().getLuminance());
        ITEM_BLOCK_LIGHT_LEVEL.put(Items.JACK_O_LANTERN, Blocks.JACK_O_LANTERN.getDefaultState().getLuminance());
        ITEM_BLOCK_LIGHT_LEVEL.put(Items.LANTERN, Blocks.LANTERN.getDefaultState().getLuminance());
        ITEM_BLOCK_LIGHT_LEVEL.put(Items.LAVA_BUCKET, Blocks.LAVA.getDefaultState().getLuminance());
        ITEM_BLOCK_LIGHT_LEVEL.put(Items.TORCH, Blocks.TORCH.getDefaultState().getLuminance());
        ITEM_BLOCK_LIGHT_LEVEL.put(Items.REDSTONE_TORCH, Blocks.REDSTONE_TORCH.getDefaultState().getLuminance());
        ITEM_BLOCK_LIGHT_LEVEL.put(Items.SOUL_TORCH, Blocks.SOUL_TORCH.getDefaultState().getLuminance());
    }
}
