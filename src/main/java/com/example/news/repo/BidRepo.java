package com.example.news.repo;

import com.example.news.model.Bid;
import com.example.news.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepo extends JpaRepository<Bid, Long> {
    List<Bid> findByUser(User user);
}
