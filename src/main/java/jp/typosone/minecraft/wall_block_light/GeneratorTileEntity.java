package jp.typosone.minecraft.wall_block_light;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * ジェネレータの各種情報を保持する独自TileEntity
 */
public class GeneratorTileEntity extends TileEntity {
    public static final String ID = "wall_block_light.generator_tile_entity";
    private int wallMetadata = 0;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        wallMetadata = compound.getInteger("wallMetadata");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("wallMetadata", wallMetadata);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound compound = new NBTTagCompound();
        writeToNBT(compound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
    }

    public int getMetadata() {
        return worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
    }

    public int getWallMetadata() {
        return wallMetadata;
    }

    public GeneratorTileEntity setWallMetadata(int meta) {
        this.wallMetadata = (meta + 16) & 0x0f;
        return this;
    }
}
