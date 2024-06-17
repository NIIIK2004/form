package com.example.news.controller;

import com.example.news.impl.BidImpl;
import com.example.news.impl.UserImpl;
import com.example.news.model.Bid;
import com.example.news.model.Role;
import com.example.news.model.User;
import com.example.news.repo.BidRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BidController {
    private final UserImpl userImpl;
    private final BidImpl bidImpl;
    private final BidRepo bidRepo;

    @GetMapping("/")
    public String home(Authentication authentication, Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userImpl.findByUsername(username);
            if (user == null) {
                return "redirect:/login?logout";
            }

            if (authentication != null && authentication.isAuthenticated()) {
                model.addAttribute("user", user);
                model.addAttribute("bidList", bidImpl.findByUser(user));

                List<Bid> allBids = bidImpl.findAll();
                model.addAttribute("allBids", allBids);
                return "index";
            }
        }
        return "auth";
    }

    @GetMapping("/bid/create")
    public String createBidPage(Model model) {
        model.addAttribute("bid", new Bid());
        return "create";
    }

    @PostMapping("/bid/save")
    public String createBid(Authentication authentication, @ModelAttribute("bid") Bid bid) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            User user = userImpl.findByUsername(username);
            bid.setUser(user);
            bid.setStatus("На рассмотрении");
            bidImpl.save(bid);
        }
        return "redirect:/";
    }

    @GetMapping("/bid/accept/{id}")
    public String acceptBid(@PathVariable Long id) {
        Bid bid = bidRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный идентификатор"));
        bid.setStatus("Принят");
        bidRepo.save(bid);
        return "redirect:/";
    }

    @GetMapping("/bid/reject/{id}")
    public String rejectBid(@PathVariable Long id) {
        Bid bid = bidRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный идентификатор"));
        bid.setStatus("Отклонен");
        bidRepo.save(bid);
        return "redirect:/";
    }

}













