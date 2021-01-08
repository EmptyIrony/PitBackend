package me.cunzai.pit.packet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.SneakyThrows;
import me.cunzai.pit.packet.client.C01PacketHeartBeat;
import me.cunzai.pit.packet.client.C02PacketPlayerJoin;
import me.cunzai.pit.packet.client.C03PacketPlayerQuit;
import me.cunzai.pit.packet.client.C04PacketSendMail;
import me.cunzai.pit.packet.server.S01PacketHeartBeat;
import me.cunzai.pit.packet.server.S02PacketMailSent;
import me.cunzai.pit.packet.server.S03PacketPlayerMail;
import org.jboss.netty.channel.ChannelHandlerContext;

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

        this.clientPacket.put(1, C01PacketHeartBeat.class);
        this.clientPacket.put(2, C02PacketPlayerJoin.class);
        this.clientPacket.put(3, C03PacketPlayerQuit.class);
        this.clientPacket.put(4, C04PacketSendMail.class);

        this.serverPacket.put(1, S01PacketHeartBeat.class);
        this.serverPacket.put(2, S02PacketMailSent.class);
        this.serverPacket.put(3, S03PacketPlayerMail.class);


    }

    @SneakyThrows
    public void handleMsg(ChannelHandlerContext ctx, String msg) {
        JsonObject json = JsonParser.parseString(msg)
                .getAsJsonObject();

        int packetId = json.get("id").getAsInt();
        Class<? extends IPacket> packetClazz = clientPacket.get(packetId);

        if (packetClazz == null) {
            System.out.println("Unknown packet id: " + packetId);
            return;
        }

        IPacket packet = packetClazz.newInstance().deserialization(msg);

        if (packet instanceof C01PacketHeartBeat) {
            S01PacketHeartBeat heartBeat = new S01PacketHeartBeat();
            heartBeat.setTime(System.currentTimeMillis());
            ctx.getChannel().write(heartBeat.serialize());
            return;
        }


    }
}
