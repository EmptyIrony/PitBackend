package me.cunzai.pit.packet.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import me.cunzai.pit.data.sub.MailData;
import me.cunzai.pit.packet.IPacket;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: EmptyIrony
 * @Date: 2021/1/7 23:48
 */
@Data
@ClientSide
public class C04PacketSendMail implements IPacket {
    private MailData data;
    private List<UUID> receives;

    @Override
    public int getPacketId() {
        return 4;
    }

    @Override
    public String serialize() {
        JsonObject json = new JsonObject();
        json.add("mail", data.toJson());
        JsonArray array = new JsonArray();
        for (UUID receive : receives) {
            array.add(receive.toString());
        }

        json.add("receives", array);
        json.addProperty("id", 4);

        return json.toString();
    }

    @Override
    public IPacket deserialization(String input) {
        JsonElement jsonElement = JsonParser.parseString(input);
        JsonObject json = jsonElement.getAsJsonObject();

        this.data = new MailData().fromJson(json.get("mail").getAsJsonObject());
        this.receives = new ArrayList<>();
        for (JsonElement element : json.getAsJsonArray("receives")) {
            this.receives.add(UUID.fromString(element.getAsString()));
        }

        return this;
    }
}
