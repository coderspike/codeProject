package task;

import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;

public class MongoQueryTest {
    @Resource
    private MongoTemplate mongoTemplate;


    //spring-data-mongo 注入来查询
    public void findList() {
//        mongoTemplate.find();
    }

}
