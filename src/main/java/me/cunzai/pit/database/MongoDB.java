package me.cunzai.pit.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import me.cunzai.pit.data.PlayerMail;
import org.mongojack.JacksonMongoCollection;

/**
 * @Author: EmptyIrony
 * @Date: 2021/1/7 23:25
 */
@Getter
public class MongoDB {
    private final MongoClient client;
    private final MongoDatabase database;

    public MongoDB() {
        this.client = new MongoClient();
        this.database = this.client.getDatabase("thePit");
        JacksonMongoCollection.<PlayerMail>builder().build(this.database.getCollection("mail"), PlayerMail.class);
    }
}
