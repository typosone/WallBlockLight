package jp.typosone.minecraft.wall_block_light;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;

/**
 * Created by tsuyoshi on 2016/02/17.
 */
public class CommonProxy {
    public World getClientWorld() {
        return null;
    }

    public void registerBlock() {
        GameRegistry.registerBlock(WallBlockLightCore.wollWallBlockGenerator = new WoolWallBlockGenerator(), WoolWallBlockGenerator.NAME);
    }

    public void registerTileEntity() {
        GameRegistry.registerTileEntity(GeneratorTileEntity.class, GeneratorTileEntity.ID);
    }
}
