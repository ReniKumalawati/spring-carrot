package com.mitrais.jpqi.springcarrot.controller;

import com.mitrais.jpqi.springcarrot.model.Basket;
import com.mitrais.jpqi.springcarrot.model.Reward;
import com.mitrais.jpqi.springcarrot.service.BasketService;
import com.mitrais.jpqi.springcarrot.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/rewards")
public class RewardController {
    @Autowired
    private RewardService rewardService;

    @GetMapping
    public List<Reward> findAllReward() {
        return rewardService.findAllReward();
    }

    @GetMapping("{id}")
    public Reward findRewardById(@PathVariable String id) {
        return rewardService.findRewardById(id);
    }

    @PostMapping
    public void insertRewardIntoDB(@RequestBody Reward reward) {
        rewardService.insertRewardIntoDB(reward);
    }

    @PatchMapping("{id}")
    public void updateReward(@RequestBody Reward reward, @PathVariable String id) {
        rewardService.updateRewardIntoDB(reward, id);
    }

    @DeleteMapping("{id}")
    public void deleteReward(@PathVariable String id) {rewardService.removeRewardById(id);}
}
