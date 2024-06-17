package com.example.news.dao;

import com.example.news.model.Bid;

import java.util.List;
import java.util.Optional;

public interface BidDao {
    Bid save(Bid bid);
    void delete(Long id);
    Optional<Bid> findById(Long id);
    List<Bid> findAll();

}
