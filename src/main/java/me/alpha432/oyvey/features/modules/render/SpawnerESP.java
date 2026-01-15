package com.aetherx.client.modules.render;

import com.aetherx.client.modules.Module;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SpawnerBlockEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class SpawnerESP extends Module {
    public SpawnerESP() {
        super("SpawnerESP");
    }

    @Override
    public void onTick() {
        if (!isEnabled() || mc.world == null || mc.player == null) return;

        // Recorremos las entidades de bloque cargadas en los chunks renderizados
        for (BlockEntity entity : mc.world.blockEntities) {
            if (entity instanceof SpawnerBlockEntity) {
                BlockPos pos = entity.getPos();
                
                // Si es la primera vez que lo ve en este tick, podemos mandar una alerta
                // Usamos el age del jugador para que no spammee el chat cada milisegundo
                if (mc.player.age % 100 == 0) {
                    mc.player.sendMessage(Text.of("§d[AetherX] §a¡Spawner localizado! §7en " + pos.toShortString()), false);
                }
                
                // Aquí el motor de renderizado dibujará la caja (si tienes el sistema de render listo)
                renderSpawnerBox(pos);
            }
        }
    }

    private void renderSpawnerBox(BlockPos pos) {
        // Esta función se conectará con tu clase de RenderUtils más adelante
        // para dibujar un cubo de color neón alrededor del spawner.
    }
}
