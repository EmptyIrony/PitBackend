package me.cunzai.pit.packet;

/**
 * @Author: EmptyIrony
 * @Date: 2021/1/7 23:43
 */
public interface IPacket {

    int getPacketId();

    String serialize();

    IPacket deserialization(String input);

}
