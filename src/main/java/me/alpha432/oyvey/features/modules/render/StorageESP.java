package com.aetherx.client.modules.render;

import com.aetherx.client.modules.Module;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.text.Text;

public class StorageESP extends Module {
    public StorageESP() {
        super("StorageESP");
    }

    @Override
    public void onTick() {
        if (!isEnabled() || mc.world == null) return;

        // Lógica para detectar cofres cercanos
        for (BlockEntity entity : mc.world.blockEntities) {
            if (entity instanceof ChestBlockEntity) {
                // Aquí irá el renderizado de la caja 3D más adelante
            }
        }
    }
}
