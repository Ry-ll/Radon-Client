package com.aetherx.client.modules.render;

import com.aetherx.client.modules.Module;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.Direction;

public class FarmFinder extends Module {
    // Aquí seleccionas tu objetivo (ej: Verrugas del Nether)
    private final Block target = Blocks.NETHER_WART; 

    public FarmFinder() { super("FarmFinder"); }

    @Override
    public void onTick() {
        if (!isEnabled() || mc.world == null || mc.player == null) return;

        // Escaneamos solo un radio de 4 chunks (64 bloques) porque el server bloquea el resto
        BlockPos pPos = mc.player.getBlockPos();
        
        for (int x = -32; x <= 32; x++) {
            for (int y = -32; y <= 32; y++) {
                for (int z = -32; z <= 32; z++) {
                    BlockPos checkPos = pPos.add(x, y, z);
                    Block block = mc.world.getBlockState(checkPos).getBlock();

                    if (block == target) {
                        // Resaltamos el bloque real encontrado
                        renderHighlight(checkPos);
                    }
                }
            }
        }
    }

    private void renderHighlight(BlockPos pos) {
        // Aquí puedes poner un mensaje o una caja de renderizado
    }
}
