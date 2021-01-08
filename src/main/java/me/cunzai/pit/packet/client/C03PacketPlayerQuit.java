package me.cunzai.pit.packet.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.cunzai.pit.packet.IPacket;

import java.util.UUID;

/**
 * @Author: EmptyIrony
 * @Date: 2021/1/8 12:41
 */
public class C03PacketPlayerQuit implements IPacket {
    private UUID uuid;

    @Override
    public int getPacketId() {
        return 3;
    }

    @Override
    public String serialize() {
        JsonObject json = new JsonObject();
        json.addProperty("uuid", uuid.toString());
        json.addProperty("id", 3);
        return json.toString();
    }

    @Override
    public IPacket deserialization(String input) {
        String uuid = JsonParser.parseString(input)
                .getAsJsonObject()
                .get("uuid")
                .getAsString();
        this.uuid = UUID.fromString(uuid);

        return this;
    }
}
