package jp.typosone.minecraft.wall_block_light;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.world.World;

/**
 * クライアント側の処理を担当するプロキシ。ベースはサーバ側用プロキシ。
 */
public class ClientProxy extends CommonProxy {
    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    @Override
    public void registerBlock() {
        super.registerBlock();
    }

    @Override
    public void registerTileEntity() {
        super.registerTileEntity();
    }
}
