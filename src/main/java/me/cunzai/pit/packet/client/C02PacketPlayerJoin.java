package me.cunzai.pit.packet.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import me.cunzai.pit.packet.IPacket;

import java.util.UUID;

/**
 * @Author: EmptyIrony
 * @Date: 2021/1/8 12:29
 */
@Data
@ClientSide
public class C02PacketPlayerJoin implements IPacket {
    private UUID uuid;
    private String name;

    @Override
    public int getPacketId() {
        return 2;
    }

    @Override
    public String serialize() {
        JsonObject json = new JsonObject();
        json.addProperty("uuid", uuid.toString());
        json.addProperty("name", name);
        json.addProperty("id", 2);

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
