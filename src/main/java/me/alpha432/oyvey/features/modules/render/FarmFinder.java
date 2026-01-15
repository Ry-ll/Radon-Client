package me.ryll.oyvey.features.modules.render;

import me.ryll.oyvey.features.modules.Module;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.text.Text;
import java.util.Arrays;
import java.util.List;

public class FarmFinder extends Module {
    
    // Lista de bloques objetivo para Donut SMP
    private final List<Block> targets = Arrays.asList(
        Blocks.KELP, 
        Blocks.KELP_PLANT, 
        Blocks.BAMBOO, 
        Blocks.BAMBOO_SAPLING
    );

    private final int range = 32; // Radio de búsqueda

    public FarmFinder() {
        super("FarmFinder", "Busca granjas de Kelp y Bamboo.", Category.RENDER, true, false, false);
    }

    @Override
    public void onTick() {
        // nullCheck() es vital en OyVey. Escaneamos cada 2 segundos (40 ticks) para evitar lag
        if (nullCheck() || mc.player.age % 40 != 0) return;

        BlockPos pPos = mc.player.getBlockPos();

        for (int x = -range; x <= range; x++) {
            for (int y = -range; y <= range; y++) {
                for (int z = -range; z <= range; z++) {
                    BlockPos checkPos = pPos.add(x, y, z);
                    Block block = mc.world.getBlockState(checkPos).getBlock();

                    if (targets.contains(block)) {
                        // Enviamos alerta al chat con las coordenadas
                        mc.player.sendMessage(Text.of("§6[AetherX] §a" + block.getName().getString() + " §fdetectado en: §7" + checkPos.toShortString()), false);
                        
                        // Opcional: Sonido de notificación para avisarte si estás AFK
                        // mc.player.playSound(net.minecraft.sound.SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                        
                        return; // Detenemos el escaneo de este ciclo para no inundar el chat
                    }
                }
            }
        }
    }
}
