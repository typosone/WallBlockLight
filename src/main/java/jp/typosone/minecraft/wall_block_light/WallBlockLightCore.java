package jp.typosone.minecraft.wall_block_light;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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


    public static Block woolWallGenerator;
    public static Block woolWallBlock;
    public static Block materialWallGenerator;
    public static Block materialWallBlock;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.registerBlock();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerTileEntity();
    }

    public static class Log {
        public static Logger logger = LogManager.getLogger("WallBlockGenerator");

        @SuppressWarnings("unused")
        public static void trace(String msg) {
            logger.trace(msg);
        }

        @SuppressWarnings("unused")
        public static void i(String msg) {
            logger.info(msg);
        }

        @SuppressWarnings("unused")
        public static void w(String msg) {
            logger.warn(msg);
        }
    }
}
