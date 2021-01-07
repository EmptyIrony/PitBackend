package me.cunzai.pit.packet;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: EmptyIrony
 * @Date: 2021/1/7 23:17
 */
public class PacketProcess {
    private final Map<Integer, Class<? extends IPacket>> serverPacket = new HashMap<>();
    private final Map<Integer, Class<? extends IPacket>> clientPacket = new HashMap<>();

    @Getter
    public static PacketProcess instance;

    public PacketProcess() {
        instance = this;
    }

    public void init() {


    }

    public void handleMsg(String msg) {

    }
}
