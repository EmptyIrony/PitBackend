package me.cunzai.pit.packet.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.cunzai.pit.packet.IPacket;

import java.util.UUID;

/**
 * @Author: EmptyIrony
 * @Date: 2021/1/8 12:52
 */
public class C05PacketRequestMail implements IPacket {
    private UUID uuid;
    private String name;

    @Override
    public int getPacketId() {
        return 5;
    }

    @Override
    public String serialize() {
        JsonObject json = new JsonObject();
        json.addProperty("uuid", uuid.toString());
        json.addProperty("name", name);
        json.addProperty("id", 5);
        return json.toString();
    }

    @Override
    public IPacket deserialization(String input) {
        JsonObject json = JsonParser.parseString(input)
                .getAsJsonObject();

        this.uuid = UUID.fromString(json.get("uuid").getAsString());
        this.name = json.get("name").getAsString();

        return this;
    }
}
