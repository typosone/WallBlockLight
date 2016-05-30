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
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

/**
 * 壁ブロックを生成するジェネレータブロッククラス
 */
public class WallGenerator extends BlockContainer {
    private Block wall;
    private String frontTextureName;
    private String otherTextureName;

    @SideOnly(Side.CLIENT)
    private IIcon frontIcon;

    @SideOnly(Side.CLIENT)
    private IIcon otherIcon;

    protected WallGenerator(Block wall, String name, String frontTextureName, String otherTextureName) {
        super(Material.rock);
        setBlockName(name);
        setBlockTextureName(frontTextureName);
        setStepSound(soundTypeMetal);
        setCreativeTab(CreativeTabs.tabRedstone);
        setHardness(1.0f);
        setResistance(2000.0f);
        setLightLevel(1.0f);
        setHarvestLevel("pickaxe", 0);
        this.wall = wall;
        this.frontTextureName = frontTextureName;
        this.otherTextureName = otherTextureName;
    }

    @Override
    public boolean canProvidePower() {
        return false;
    }

    @Override
    public int getMobilityFlag() {
        return 2; // immobility
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        int l = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
        generateWall(world, x, y, z);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
                                    EntityPlayer player, int side, float px, float py, float pz) {
        GeneratorTileEntity tile = (GeneratorTileEntity) world.getTileEntity(x, y, z);
        if (tile != null && !world.isRemote) {
            if (!player.isSneaking()) {
                tile.setWallMetadata(tile.getWallMetadata() + 1);
            } else {
                tile.setWallMetadata(tile.getWallMetadata() - 1);
            }
            generateWall(world, x, y, z);
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
            world.scheduleBlockUpdate(x, y, z, this, 1);
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote) {
            if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
                clearWall(world, x, y, z);
            } else {
                generateWall(world, x, y, z);
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
        otherIcon = register.registerIcon(otherTextureName);
        frontIcon = register.registerIcon(frontTextureName);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side == 1 ? otherIcon :
                (side == 0 ? otherIcon :
                        (meta == 2 && side == 2 ? frontIcon :
                                (meta == 3 && side == 5 ? frontIcon :
                                        (meta == 0 && side == 3 ? frontIcon :
                                                (meta == 1 && side == 4 ? frontIcon : otherIcon)))));
    }

    protected void generateWall(World world, int x, int y, int z) {
        GeneratorTileEntity tile = (GeneratorTileEntity) world.getTileEntity(x, y, z);
        if (tile.getMetadata() == 1 || tile.getMetadata() == 3) {
            for (int dz = -1; dz < 2; dz++) {
                for (int dy = 2; dy < 5; dy++) {
                    world.setBlock(x, y + dy, z + dz, wall);
                    world.setBlockMetadataWithNotify(x, y + dy, z + dz, tile.getWallMetadata(), 2);
                }
            }
        } else if (tile.getMetadata() == 0 || tile.getMetadata() == 2) {
            for (int dx = -1; dx < 2; dx++) {
                for (int dy = 2; dy < 5; dy++) {
                    world.setBlock(x + dx, y + dy, z, wall);
                    world.setBlockMetadataWithNotify(x + dx, y + dy, z, tile.getWallMetadata(), 2);
                }
            }
        }
    }

    protected void clearWall(World world, int x, int y, int z) {
        GeneratorTileEntity tile = (GeneratorTileEntity) world.getTileEntity(x, y, z);
        if (tile.getMetadata() == 1 || tile.getMetadata() == 3) {
            for (int dz = -1; dz < 2; dz++) {
                for (int dy = 2; dy < 5; dy++) {
                    world.setBlockToAir(x, y + dy, z + dz);
                    world.setBlockMetadataWithNotify(x, y + dy, z + dz, tile.getWallMetadata(), 2);
                }
            }
        } else if (tile.getMetadata() == 0 || tile.getMetadata() == 2) {
            for (int dx = -1; dx < 2; dx++) {
                for (int dy = 2; dy < 5; dy++) {
                    world.setBlockToAir(x + dx, y + dy, z);
                    world.setBlockMetadataWithNotify(x + dx, y + dy, z, tile.getWallMetadata(), 2);
                }
            }
        }
    }
}
