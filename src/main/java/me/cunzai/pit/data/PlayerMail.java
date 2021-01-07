package me.cunzai.pit.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import me.cunzai.pit.data.sub.MailData;
import me.cunzai.pit.database.MongoDB;
import org.mongojack.DBQuery;

import java.util.*;

/**
 * @Author: EmptyIrony
 * @Date: 2021/1/7 23:24
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PlayerMail {
    private static Map<UUID, PlayerMail> cache = new HashMap<>();

    private String uuid;
    private String name;
    private String nameLower;
    private List<MailData> mailData = new ArrayList<>();

    public static PlayerMail load(UUID uuid, String name) {
        PlayerMail mail = cache.get(uuid);
        if (mail != null) {
            return mail;
        }

        PlayerMail first = MongoDB.getInstance()
                .getPlayerMailCollection()
                .find(DBQuery.is("uuid", uuid.toString()))
                .first();

        if (first == null) {
            PlayerMail newData = new PlayerMail();
            newData.setUuid(uuid.toString());
            newData.setName(name);
            newData.setNameLower(name.toLowerCase());

            cache.put(uuid, newData);
            return newData;
        }

        cache.put(uuid, first);
        return first;
    }

    public void save() {
        MongoDB.getInstance()
                .getPlayerMailCollection()
                .replaceOne(DBQuery.is("uuid", this.uuid), this);
    }
}
