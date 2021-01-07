package me.cunzai.pit.packet;

import lombok.Getter;

/**
 * @Author: EmptyIrony
 * @Date: 2021/1/7 23:17
 */
public class PacketProcess {
    @Getter
    public static PacketProcess instance;

    public PacketProcess() {
        instance = this;
    }

    public void handleMsg(String msg) {

    }
}
