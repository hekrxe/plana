package com.es.spring.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * User: tanhuayou
 * Date: 2018/7/19
 */
@Document(indexName = "es_study_usr", type = "user", shards = 2, replicas = 1)
public class User extends Person {
    @Id
    private String id;
    private String name;
    private String song;
    private Integer age;
    private Integer sex;
    private String publishAddress;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getSong() {
        return song;
    }

    public User setSong(String song) {
        this.song = song;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public User setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public String getPublishAddress() {
        return publishAddress;
    }

    public User setPublishAddress(String publishAddress) {
        this.publishAddress = publishAddress;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", song='" + song + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", publishAddress=" + publishAddress +
                '}' + "\n" + super.toString();
    }
}
