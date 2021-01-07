package me.cunzai.pit.data.sub;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: EmptyIrony
 * @Date: 2021/1/7 23:34
 */
@Data
public class MailData {
    private String title;
    private String content;
    private double exp;
    private double coins;
    private double renown;
    private String item;
    private List<String> perks = new ArrayList<>();
    private long sendTime;
    private long expireTime;

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("title", title);
        json.addProperty("content", content);
        json.addProperty("exp", exp);
        json.addProperty("coins", coins);
        json.addProperty("renown", renown);
        json.addProperty("sendTime", sendTime);
        json.addProperty("expireTime", expireTime);
        json.addProperty("item", item);

        JsonArray perkArray = new JsonArray();
        for (String perk : perks) {
            perkArray.add(perk);
        }

        json.add("perk", perkArray);

        return json;
    }

    public MailData fromJson(JsonObject json) {
        this.title = json.get("title").getAsString();
        this.content = json.get("content").getAsString();
        this.exp = json.get("exp").getAsDouble();
        this.coins = json.get("coins").getAsDouble();
        this.renown = json.get("renown").getAsInt();
        this.sendTime = json.get("sendTime").getAsLong();
        this.expireTime = json.get("expireTime").getAsLong();
        this.item = json.get("item").getAsString();

        JsonArray perk = json.getAsJsonArray("perk");
        if (perk.size() > 0) {
            for (JsonElement jsonElement : perk) {
                this.perks.add(jsonElement.getAsString());
            }
        }

        return this;
    }
}
