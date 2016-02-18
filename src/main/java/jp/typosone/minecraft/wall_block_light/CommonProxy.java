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
        GameRegistry.registerBlock(WallBlockLightCore.wollWallBlockGenerator = new WoolWallGenerator(),
                WoolWallGenerator.NAME);
        GameRegistry.registerBlock(WallBlockLightCore.woolWallBlock = new WoolWallBlock(),
                WoolWallBlock.NAME);
    }

    public void registerTileEntity() {
        GameRegistry.registerTileEntity(GeneratorTileEntity.class, GeneratorTileEntity.ID);
    }
}
