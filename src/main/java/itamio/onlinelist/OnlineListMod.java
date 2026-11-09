package itamio.onlinelist;

import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("onlinelist")
public class OnlineListMod {
    public OnlineListMod() {
        MinecraftForge.EVENT_BUS.addListener(this::onRegisterCommands);
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("onlinelist").requires(s -> s.hasPermission(0))
            .executes(ctx -> {
                var names = ctx.getSource().getServer().getPlayerList().getPlayers().stream().map(p -> p.getName().getString()).toList(); ctx.getSource().sendSuccess(() -> Component.literal("Online: " + String.join(", ", names)), false);
                return 1;
            }));
    }
}
