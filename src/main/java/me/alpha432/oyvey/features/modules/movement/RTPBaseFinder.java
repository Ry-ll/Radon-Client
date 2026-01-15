package com.aetherx.client.modules.movement;

import com.aetherx.client.modules.Module;
import net.minecraft.block.Blocks;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class BaseFinder extends Module {
    public BaseFinder() { 
        super("BaseFinder"); 
    }

    @Override
    public void onEnable() {
        if (mc.player == null) return;
        // Ejecuta el comando de teletransporte del servidor
        mc.player.networkHandler.sendCommand("rtp east");
        mc.player.sendMessage(Text.of("§b[AetherX] §fBuscando base... Teletransportando y bajando a Y: 0"), false);
    }

    @Override
    public void onTick() {
        if (!isEnabled() || mc.player == null || mc.world == null) return;

        BlockPos posDebajo = mc.player.getBlockPos().down();
        
        // --- SEGURIDAD (Anti-Lava / Anti-Vacío) ---
        if (mc.world.getBlockState(posDebajo).getBlock() == Blocks.LAVA || 
            mc.world.getBlockState(posDebajo).getBlock() == Blocks.AIR && mc.player.getY() < -60) {
            mc.player.sendMessage(Text.of("§c[AetherX] Peligro detectado bajo tus pies. Deteniendo descenso."), true);
            toggle();
            return;
        }

        // --- LÓGICA DE DESCENSO ---
        if (mc.player.getY() > 0) {
            // Forzamos al jugador a agacharse/bajar si está volando o rompiendo bloques hacia abajo
            mc.options.sneakKey.setPressed(true);
        } else {
            mc.options.sneakKey.setPressed(false);
            mc.player.sendMessage(Text.of("§a[AetherX] Has llegado a la capa 0. ¡Buena suerte con el raideo!"), false);
            toggle(); // Se apaga solo al llegar al objetivo
        }
    }
}
