package com.es.spring;

import com.es.spring.dao.EsDAO;
import com.es.spring.dao.UserEsDAO;
import com.es.spring.dao.model.User;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * link: https://github.com/spring-projects/spring-boot/blob/master/spring-boot-samples/spring-boot-sample-data-elasticsearch
 * User: tanhuayou
 * Date: 2018/7/19
 */
@SpringBootApplication
@EnableElasticsearchRepositories(basePackageClasses = EsDAO.class)
public class EsSpringMain implements CommandLineRunner {
    private int count = 10;

    @Autowired
    private UserEsDAO userEsDAO;


    public static void main(String[] args) {
        SpringApplication.run(EsSpringMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userEsDAO.deleteAll();
        save();
        fetchAll();
        fetchByCondition();
    }

    private void fetchByCondition() {
//        System.out.println("ById...");
//
//        for (int i = 0; i < count; ++i) {
//            User byId = userEsDAO.findById(i + "").get();
//            System.out.println("id=" + i + ", => " + byId);
//        }
//
//        System.out.println("between");
//        userEsDAO.findByAgeBetween(10, 35).forEach(System.out::println);

        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        builder.must(QueryBuilders.termQuery("song", "song5"));

        userEsDAO.search(builder);
    }

    private void fetchAll() throws InterruptedException {
        Iterable<User> users = userEsDAO.findAll();
        Thread.sleep(1000);
        System.out.println();
        users.forEach(System.out::println);
    }

    private void save() throws InterruptedException {
        System.out.println("Save...");
        for (int i = 0; i < count; ++i) {
            User user = new User().setId(i + "").setAge(i * 2 + 1).setName("name" + i).setSex(i % 2).setSong("song" + i).setPublishAddress("address" + i);
            user.setHeight(i + 10);
            userEsDAO.save(user);
        }
        Thread.sleep(1000);
        System.out.println("Saved");
    }
}
