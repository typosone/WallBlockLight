package jp.typosone.minecraft.wall_block_light;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.world.World;

/**
 * Created by tsuyoshi on 2016/02/17.
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
