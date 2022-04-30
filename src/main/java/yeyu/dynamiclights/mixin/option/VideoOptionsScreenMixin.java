package yeyu.dynamiclights.mixin.option;

import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.option.Option;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import yeyu.dynamiclights.client.options.DynamicLightsOptions;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(VideoOptionsScreen.class)
public class VideoOptionsScreenMixin {
    @Mutable
    @Shadow
    @Final
    private static Option[] OPTIONS;

    static {
        final ArrayList<Option> optionsArray = new ArrayList<>(Arrays.asList(OPTIONS));
        optionsArray.addAll(Arrays.asList(DynamicLightsOptions.OPTIONS));

        OPTIONS = new Option[optionsArray.size()];
        for (int i = 0; i < optionsArray.size(); i++) {
            OPTIONS[i] = optionsArray.get(i);
        }
    }
}
