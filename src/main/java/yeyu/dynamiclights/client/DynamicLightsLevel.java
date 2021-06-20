package yeyu.dynamiclights.client;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public enum DynamicLightsLevel {
    OFF(5, 0, 0),
    ONE(5, 0.135f, 2.2f),
    TWO(6, 0.06f, 2f),
    THREE(7, 0.03f, 1.96f),
    FOUR(8, 0.02f, 1.87f),
    FIVE(9, 0.066f, 1.4f),
    SIX(10, 0.048f, 1.4f);

    public final int RADIUS;
    public final float MULTIPLIER;
    public final float POWER;

    DynamicLightsLevel(int radius, float multiplier, float power) {
        RADIUS = radius;
        MULTIPLIER = multiplier;
        POWER = power;
    }

    void iterateLightMap(Vec3d cameraPosVec, ClientWorld clientWorld, float maxLight) {
        iterateLightMap(cameraPosVec, clientWorld, maxLight, false);
    }

    void iterateLightMap(Vec3d cameraPosVec, ClientWorld clientWorld, float maxLight, boolean force) {
        final BlockPos playerBp = new BlockPos(cameraPosVec);
        final int radius = RADIUS;
        clientWorld.getProfiler().push("queueCalculateLight");
        BlockPos.iterate(playerBp.add(-radius - 1, -radius - 1, -radius - 1), playerBp.add(radius, radius, radius)).forEach(bp -> processBlockPos(bp, cameraPosVec, clientWorld, maxLight, force));
        clientWorld.getProfiler().pop();
    }

    private void processBlockPos(BlockPos bp, Vec3d cameraPosVec, ClientWorld clientWorld, float maxLight, boolean forceOff) {
        if (this == DynamicLightsLevel.OFF || forceOff) {
            if (!DynamicLightsStorage.setLightLevel(bp, 0, true)) return;
        } else {
            final float x = bp.getX() + 0.5f;
            final float y = bp.getY() + 0.5f;
            final float z = bp.getZ() + 0.5f;
            final float camX = (float) cameraPosVec.getX();
            final float camY = (float) cameraPosVec.getY();
            final float camZ = (float) cameraPosVec.getZ();
            final float dx = camX - x;
            final float dy = camY - y;
            final float dz = camZ - z;
            final float dist = dx * dx + dy * dy + dz * dz;
            final double lightLevel = LightFunction.QUADRATIC.apply(dist, maxLight);
            if (!DynamicLightsStorage.setLightLevel(bp, lightLevel, false)) return;
        }
        // do not check blocks that has been scheduled;
        DynamicLightsStorage.BP_UPDATED.computeIfAbsent(bp.asLong(), $ -> {
            clientWorld.getChunkManager().getLightingProvider().checkBlock(bp);
            return true;
        });
    }
}