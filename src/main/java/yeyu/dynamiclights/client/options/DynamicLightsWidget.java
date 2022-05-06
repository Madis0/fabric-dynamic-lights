package yeyu.dynamiclights.client.options;

import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.option.Option;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static yeyu.dynamiclights.client.options.DynamicLightsOptions.*;

@SuppressWarnings("unused")
public class DynamicLightsWidget {
    public final Option DYNAMIC_LIGHTS_OPTIONS =
            CyclingOption.create("dynamiclights.level",
                    () -> IntStream.range(0, DynamicLightsLevel.values().length).boxed().collect(Collectors.toList()),
                    (level) -> new LiteralText(DynamicLightsLevel.values()[level].name()),
                    $ -> getCurrentOption().ordinal(),
                    ($, $$, level) -> setLightsLevel(level));

    public final Option DYNAMIC_LIGHTS_ENTITIES =
            new DoubleOption("dynamiclights.entities_tick",
                    4,
                    50,
                    2,
                    $ -> (double) getMaxEntitiesToTick(),
                    ($, value) -> setMaxEntitiesToTick(value.intValue()),
                    ($, option) -> {
                        int d = getMaxEntitiesToTick();
                        return new TranslatableText("options.generic_value", new TranslatableText("dynamiclights.entities_tick"), d);
                    },
                    (client) -> client.textRenderer.wrapLines(new TranslatableText("dynamiclights.entities_tick.desc"), 200));

    public final Option DYNAMIC_LIGHTS_PERFORMANCE =
            CyclingOption.create("dynamiclights.performance",
                    () -> IntStream.range(0, DynamicLightsTickDelays.values().length).boxed().collect(Collectors.toList()),
                    (level) -> new LiteralText(DynamicLightsTickDelays.values()[level].name()),
                    ($) -> getTickLevel().ordinal(),
                    ($, $$, level) -> setTickLevel(level));
    public final Option DYNAMIC_LIGHTS_PRECISION =
            CyclingOption.create("dynamiclights.precision",
                    () -> IntStream.range(0, DynamicLightsPrecision.values().length).boxed().collect(Collectors.toList()),
                    (level) -> new LiteralText(DynamicLightsPrecision.values()[level].name()),
                    ($) -> getPrecision().ordinal(),
                    ($, $$, level) -> setPrecision(DynamicLightsPrecision.values()[level]));

    public final ArrayList<Option> OPTIONS = new ArrayList<>() {{
            add(DYNAMIC_LIGHTS_ENTITIES);
            add(DYNAMIC_LIGHTS_OPTIONS);
            add(DYNAMIC_LIGHTS_PERFORMANCE);
            add(DYNAMIC_LIGHTS_PRECISION);
    }};

    public static final DynamicLightsWidget INSTANCE = new DynamicLightsWidget();
}
