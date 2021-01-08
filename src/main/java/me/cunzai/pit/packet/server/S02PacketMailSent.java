package me.cunzai.pit.packet.server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.cunzai.pit.packet.IPacket;

/**
 * @Author: EmptyIrony
 * @Date: 2021/1/8 12:52
 */
public class S02PacketMailSent implements IPacket {
    private long time;

    @Override
    public int getPacketId() {
        return 2;
    }

    @Override
    public String serialize() {
        JsonObject json = new JsonObject();
        json.addProperty("time", time);
        json.addProperty("id", 2);
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
