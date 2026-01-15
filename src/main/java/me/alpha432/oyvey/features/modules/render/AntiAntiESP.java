package com.aetherx.client.modules.render;

import com.aetherx.client.modules.Module;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.util.math.Vec3d;

public class AntiESP extends Module {
    public AntiESP() {
        super("AntiESP");
    }

    @Override
    public void onTick() {
        if (!isEnabled() || mc.world == null || mc.player == null) return;

        for (PlayerEntity enemy : mc.world.getPlayers()) {
            if (enemy == mc.player || enemy.isInvisible()) continue;

            // 1. Obtenemos la dirección hacia donde mira el enemigo
            Vec3d lookVec = enemy.getRotationVecClient();
            Vec3d eyePos = enemy.getEyePos();
            
            // 2. Trazamos una línea imaginaria desde sus ojos hacia tu posición
            HitResult hit = mc.world.raycast(new RaycastContext(
                eyePos, 
                mc.player.getPos(), 
                RaycastContext.ShapeType.COLLIDER, 
                RaycastContext.FluidHandling.NONE, 
                enemy
            ));

            // 3. Si hay un bloque en medio (Type.BLOCK) pero su rotación te apunta, 
            // es muy probable que esté usando ESP para seguirte.
            if (hit.getType() == HitResult.Type.BLOCK && isLookingAtMe(enemy)) {
                // Alerta visual en el Action Bar (encima del inventario) para no llenar el chat
                mc.player.sendMessage(Text.of("§c[!] SOSPECHA DE ESP: §f" + enemy.getName().getString()), true);
            }
        }
    }

    private boolean isLookingAtMe(PlayerEntity enemy) {
        Vec3d relativePos = mc.player.getPos().subtract(enemy.getPos()).normalize();
        double dotProduct = enemy.getRotationVecClient().dotProduct(relativePos);
        // Si el valor es cercano a 1.0, te está mirando directamente
        return dotProduct > 0.95; 
    }
}
