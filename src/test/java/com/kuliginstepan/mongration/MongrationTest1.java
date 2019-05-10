package com.kuliginstepan.mongration;

import static org.junit.Assert.assertEquals;

import changelogs.SimpleChangeLog1;
import java.util.List;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationWithMongration.class)
@Import(SimpleChangeLog1.class)
public class MongrationTest1 {

    @Autowired
    TestEntityRepository repository;
    @Autowired
    MongoTemplate template;

    @Test
    public void test() {
        List<TestEntity> entities = repository.findAll();
        List<Document> migrations = template.findAll(Document.class, "mongration_changelogs");

        assertEquals("1", entities.get(0).getIndex());
        assertEquals("1", entities.get(0).getText());
        assertEquals("2", entities.get(1).getIndex());
        assertEquals("2", entities.get(1).getText());
        assertEquals("3", entities.get(2).getIndex());
        assertEquals("3", entities.get(2).getText());

        Document migration = migrations.get(0);
        assertEquals("change1", migration.getString("changeSetId"));
        assertEquals("Stepan", migration.getString("author"));
        assertEquals(SimpleChangeLog1.class.getName(), migration.getString("changeLogClass"));
        assertEquals("migration", migration.getString("changeSetMethod"));

        assertEquals(3, entities.size());
        assertEquals(1, migrations.size());
    }
}