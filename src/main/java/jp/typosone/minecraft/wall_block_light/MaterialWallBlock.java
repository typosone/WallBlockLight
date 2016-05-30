package jp.typosone.minecraft.wall_block_light;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

/**
 * 見た目が建材(石や木材など16種)の壁ブロック
 */
public class MaterialWallBlock extends Block {
    public static final String NAME = "Material Wall Block";

    public MaterialWallBlock() {
        super(Material.rock);
        setBlockName(NAME);
        setStepSound(soundTypeStone);
        setBlockUnbreakable();
        setResistance(6000000.0f);
        setLightLevel(1.0f);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getMobilityFlag() {
        return 2; // immobility
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        switch (meta) {
            case 1:
                return Blocks.dirt.getIcon(side, 0);
            case 2:
                return Blocks.cobblestone.getIcon(side, 0);
            case 3:
                // no break
            case 4:
                // no break
            case 5:
                // no break
            case 6:
                // no break
            case 7:
                // no break
            case 8:
                return Blocks.planks.getIcon(side, meta - 3);
            case 9:
                // no break
            case 10:
                return Blocks.sand.getIcon(side, meta - 9);
            case 11:
                return Blocks.gravel.getIcon(side, 0);
            case 12:
                return Blocks.glass.getIcon(side, 0);
            case 13:
                return Blocks.brick_block.getIcon(side, 0);
            case 14:
                return Blocks.mossy_cobblestone.getIcon(side, 0);
            case 15:
                return Blocks.stonebrick.getIcon(side, 0);
            default:
                return Blocks.stone.getIcon(side, 0);
        }
    }
}
