package jp.typosone.minecraft.wall_block_light;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;

/**
 * サーバ側の処理を担当するプロキシ
 */
public class CommonProxy {
    @SuppressWarnings("unused")
    public World getClientWorld() {
        return null;
    }

    public void registerBlock() {
        GameRegistry.registerBlock(WallBlockLightCore.woolWallBlock = new WoolWallBlock(),
                WoolWallBlock.NAME);
        GameRegistry.registerBlock(WallBlockLightCore.woolWallGenerator
                        = new WallGenerator(
                        WallBlockLightCore.woolWallBlock, "Wool Wall Generator",
                        "wall_block_light:wool_wall_block_generator", "wool_colored_white")
                , "Wool Wall Generator");
        GameRegistry.registerBlock(WallBlockLightCore.materialWallBlock = new MaterialWallBlock(), MaterialWallBlock.NAME);
        GameRegistry.registerBlock(WallBlockLightCore.materialWallGenerator
                        = new WallGenerator(
                        WallBlockLightCore.materialWallBlock, "Material Wall Generator",
                        "wall_block_light:material_wall_block_generator", "stone")
                , "Material Wall Generator");
    }

    public void registerTileEntity() {
        GameRegistry.registerTileEntity(GeneratorTileEntity.class, GeneratorTileEntity.ID);
    }
}
