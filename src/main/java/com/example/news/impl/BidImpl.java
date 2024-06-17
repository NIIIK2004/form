package com.example.news.impl;

import com.example.news.dao.BidDao;
import com.example.news.model.Bid;
import com.example.news.model.User;
import com.example.news.repo.BidRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BidImpl implements BidDao {
    private final BidRepo bidRepo;

    @Override
    public Bid save(Bid bid) {
        return bidRepo.save(bid);
    }

    @Override
    public void delete(Long id) {
        bidRepo.deleteById(id);
    }

    @Override
    public Optional<Bid> findById(Long id) {
        return bidRepo.findById(id);
    }

    @Override
    public List<Bid> findAll() {
        return bidRepo.findAll();
    }

    public List<Bid> findByUser(User user) {
        return bidRepo.findByUser(user);
    }
}
