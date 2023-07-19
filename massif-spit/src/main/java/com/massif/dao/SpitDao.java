package com.massif.dao;

import com.massif.entity.Spit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpitDao extends MongoRepository<Spit,String> {
}
