package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer>{

    @Query("SELECT m FROM Message m WHERE m.posted_by =:posted_by")
    List<Message> findByPostedBy(@Param("posted_by") int posted_by);
}
