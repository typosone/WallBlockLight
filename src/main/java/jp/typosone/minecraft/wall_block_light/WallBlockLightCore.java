package jp.typosone.minecraft.wall_block_light;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;

/**
 * This class is mod's main class
 */
@Mod(
        modid = "wall_block_light"
        , name = "Wall Block Light"
        , version = "0.1"
)
public class WallBlockLightCore {
    @Mod.Instance("wall_block_light")
    public static WallBlockLightCore instance;

    @SidedProxy(
            clientSide = "jp.typosone.minecraft.wall_block_light.ClientProxy"
            , serverSide = "jp.typosone.minecraft.wall_block_light.CommonProxy"
    )
    public static CommonProxy proxy;


    public static Block wollWallBlockGenerator;
    public static Block woolWallBlock;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.registerBlock();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerTileEntity();
    }
}
