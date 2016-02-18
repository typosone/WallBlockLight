package jp.typosone.minecraft.wall_block_light;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by tsuyoshi on 2016/02/18.
 */
public class GeneratorTileEntity extends TileEntity {
    public static final String ID = "wall_block_light.generator_tile_entity";
    private int input = 0;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.input = compound.getInteger("input");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("input", input);
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

    public int getInputSide() {
        return input;
    }

    public GeneratorTileEntity setInputSide(int side) {
        input = side;
        return this;
    }

    public int getMetadata() {
        return worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
    }

    public GeneratorTileEntity setMetadata(int metadata) {
        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, metadata, 2);
        return this;
    }
}
