package com.aetherx.client.modules.render;

import com.aetherx.client.modules.Module;
import net.minecraft.world.chunk.WorldChunk;

public class ChunkFinder extends Module {
    public ChunkFinder() { super("ChunkFinder"); }

    @Override
    public void onTick() {
        if (!isEnabled() || mc.world == null) return;
        // Lógica para resaltar chunks con actividad o datos antiguos
    }
}
