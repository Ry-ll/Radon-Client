package me.ryll.oyvey.features.modules.render;

import me.ryll.oyvey.features.modules.Module;
import net.minecraft.entity.player.PlayerEntity;

public class AntiAntiESP extends Module {
    public AntiAntiESP() {
        super("AntiAntiESP", "Intenta saltar protecciones anti-esp del server.", Category.RENDER, true, false, false);
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;

        for (PlayerEntity player : mc.world.getPlayers()) {
            if (player == mc.player) continue;
            // Forzamos al motor de renderizado a no ignorar al jugador
            player.ignoreCameraFrustum = true;
        }
    }
}
