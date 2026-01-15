package me.ryll.oyvey.features.modules.render;

import me.ryll.oyvey.features.modules.Module;
import net.minecraft.block.entity.*;

public class StorageESP extends Module {
    public StorageESP() {
        super("StorageESP", "Resalta contenedores.", Category.RENDER, true, false, false);
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;

        for (BlockEntity entity : mc.world.blockEntities) {
            if (entity instanceof ChestBlockEntity || entity instanceof ShulkerBoxBlockEntity || entity instanceof BarrelBlockEntity) {
                // El renderizado se suele manejar en el evento onRender3D de OyVey
            }
        }
    }
}
