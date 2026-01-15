package com.aetherx.client.modules.render;

import com.aetherx.client.modules.Module;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class AntiESP extends Module {
    public AntiESP() { super("AntiESP"); }

    @Override
    public void onTick() {
        if (!isEnabled() || mc.world == null || mc.player == null) return;

        for (PlayerEntity player : mc.world.getPlayers()) {
            if (player == mc.player) continue;
            // Si un jugador te está apuntando directamente a través de bloques
            if (player.isPointingAt(mc.player)) {
                mc.player.sendMessage(Text.of("§c[!] Alguien podría estar usándote ESP"), true);
            }
        }
    }
}
