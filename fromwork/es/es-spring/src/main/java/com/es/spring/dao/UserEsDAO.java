package com.es.spring.dao;

import com.es.spring.dao.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * User: tanhuayou
 * Date: 2018/7/19
 */
public interface UserEsDAO extends ElasticsearchRepository<User, String> {

    List<User> findByAgeBetween(int min, int max);
}
