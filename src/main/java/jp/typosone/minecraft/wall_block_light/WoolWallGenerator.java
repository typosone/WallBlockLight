package jp.typosone.minecraft.wall_block_light;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by tsuyoshi on 2016/02/18.
 */
public class WoolWallGenerator extends BlockContainer {

    public static final String NAME = "Wool Wall Block Generator";

    @SideOnly(Side.CLIENT)
    private IIcon icon;

    protected WoolWallGenerator() {
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
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        world.scheduleBlockUpdate(x, y, z, this, 4);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
                                    EntityPlayer player, int side, float px, float py, float pz) {
        GeneratorTileEntity tile = (GeneratorTileEntity) world.getTileEntity(x, y, z);

        if (tile != null && !world.isRemote) {
            int newMeta = tile.getMetadata() + 1;
            tile.setMetadata(newMeta & 0xf);
            world.scheduleBlockUpdate(x, y, z, this, 2);
        }

        return true;
    }

    @Override
    public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
        clearWall(world, x, y, z);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!world.isRemote) {
            world.scheduleBlockUpdate(x, y, z, this, 4);
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        GeneratorTileEntity tile = (GeneratorTileEntity) world.getTileEntity(x, y, z);

        if (tile != null && !world.isRemote) {
            WallBlockLightCore.Log.i("updateTick#metadata: " + tile.blockMetadata);
            for (int offset = 2; offset < 5; offset++) {
                world.setBlock(x, y + offset, z, WallBlockLightCore.woolWallBlock);
                world.setBlockMetadataWithNotify(x, y + offset, z, tile.getMetadata(), 2);
            }
        }
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

    public void clearWall(World world, int x, int y, int z) {
        for (int offset = 2; offset < 5; offset++) {
            world.setBlockToAir(x, y + offset, z);
        }
    }
}
