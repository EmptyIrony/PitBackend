package me.cunzai.pit.packet.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import me.cunzai.pit.packet.IPacket;

/**
 * @Author: EmptyIrony
 * @Date: 2021/1/8 12:26
 */
@Data
@ClientSide
public class C01PacketHeartBeat implements IPacket {
    private long time;

    @Override
    public int getPacketId() {
        return 1;
    }

    @Override
    public String serialize() {
        JsonObject json = new JsonObject();
        json.addProperty("time", time);
        json.addProperty("id", 1);
        return json.toString();
    }

    @Override
    public IPacket deserialization(String input) {
        this.time = JsonParser.parseString(input)
                .getAsJsonObject()
                .get("time")
                .getAsLong();

        return this;
    }
}
