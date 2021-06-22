package yeyu.dynamiclights.client;

import java.util.function.BiFunction;

public enum LightFunction implements BiFunction<Float, Float, Float> {
    QUADRATIC;

    @Override
    public Float apply(Float squaredDistance, Float maxLight) {
        final float multiplier = DynamicLightsOptions.getCurrentLightMultiplier();
        final float power = DynamicLightsOptions.getCurrentLightPower();
        final float amp = DynamicLightsOptions.getCurrentAmplifier();
        return (maxLight / 15) * (float) Math.min(15, 15.1 + amp + (-multiplier * (Math.pow(squaredDistance, power))));
    }
}
