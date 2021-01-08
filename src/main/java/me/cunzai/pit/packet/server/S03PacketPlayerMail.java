package me.cunzai.pit.packet.server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.cunzai.pit.data.sub.MailData;
import me.cunzai.pit.packet.IPacket;

import java.util.UUID;

/**
 * @Author: EmptyIrony
 * @Date: 2021/1/8 12:55
 */
public class S03PacketPlayerMail implements IPacket {
    private UUID uuid;
    private String name;
    private MailData data;

    @Override
    public int getPacketId() {
        return 3;
    }

    @Override
    public String serialize() {
        JsonObject json = new JsonObject();
        json.addProperty("uuid", uuid.toString());
        json.addProperty("name", name);
        json.add("mail", data.toJson());
        json.addProperty("id", 3);

        return json.toString();
    }

    @Override
    public IPacket deserialization(String input) {
        JsonObject json = JsonParser.parseString(input)
                .getAsJsonObject();

        this.uuid = UUID.fromString(json.get("uuid").getAsString());
        this.name = json.get("name").getAsString();
        this.data = new MailData().fromJson(json.get("mail").getAsJsonObject());

        return this;
    }
}
