package jp.typosone.minecraft.wall_block_light;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

/**
 * 壁を形成するblock. 壊れない。壊せない。
 */
public class WoolWallBlock extends Block {
    public static final String NAME = "Wool Wall Block";

    public WoolWallBlock() {
        super(Material.cloth);
        setBlockName(NAME);
        setStepSound(soundTypeCloth);
        setBlockUnbreakable();
        setResistance(6000000.0f);
        setLightLevel(1.0f);
    }

    @Override
    public int getMobilityFlag() {
        return 2; // immobility
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return Blocks.wool.getIcon(side, meta);
    }
}
