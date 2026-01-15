package me.ryll.oyvey.features.modules.misc;

import me.ryll.oyvey.features.modules.Module;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.text.Text;
import java.util.HashSet;

public class ChunkFinder extends Module {
    private final HashSet<ChunkPos> knownChunks = new HashSet<>();

    public ChunkFinder() {
        super("ChunkFinder", "Detecta chunks cargados recientemente.", Category.MISC, true, false, false);
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;

        // Obtenemos el chunk donde está el jugador
        ChunkPos currentChunk = mc.world.getChunk(mc.player.getBlockPos()).getPos();
        
        if (!knownChunks.contains(currentChunk)) {
            knownChunks.add(currentChunk);
            // Si quieres que te avise cada vez que entras a uno "nuevo" para el cliente
            // mc.player.sendMessage(Text.of("§7[AetherX] Nuevo chunk registrado."), true);
        }
    }
}
