package jp.typosone.minecraft.wall_block_light;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by tsuyoshi on 2016/02/18.
 */
public class WoolWallBlockGenerator extends BlockContainer {

    public static final String NAME = "Wool Wall Block Generator";

    @SideOnly(Side.CLIENT)
    private IIcon icon;

    protected WoolWallBlockGenerator() {
        super(Material.rock);
        setBlockName(NAME);
        setBlockTextureName("wall_block_light:wool_wall_block_generator");
        setStepSound(soundTypeMetal);
        setCreativeTab(CreativeTabs.tabRedstone);
        setHardness(1.0f);
        setResistance(2000.0f);
        setLightLevel(1.0f);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
        return 1;
    }

    @Override
    public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side) {
        return isProvidingWeakPower(world, x, y, z, side);
    }

    @Override
    public int getMobilityFlag() {
        return 2; // immobility
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
                                    EntityPlayer player, int side, float px, float py, float pz) {
        GeneratorTileEntity tile = (GeneratorTileEntity) world.getTileEntity(x, y, z);

        if (tile != null && !world.isRemote) {
            int meta = tile.getMetadata();
            meta = (byte) ((meta + 1) & 0xff);
            tile.setMetadata(meta);
        }

        return true;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!world.isRemote) {
            world.scheduleBlockUpdate(x, y, z, this, 4);
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {

    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new GeneratorTileEntity();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icon = register.registerIcon("wall_block_light:wool_wall_block_generator");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return icon;
    }
}
