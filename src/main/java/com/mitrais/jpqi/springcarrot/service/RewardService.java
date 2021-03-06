package com.mitrais.jpqi.springcarrot.service;

import com.mitrais.jpqi.springcarrot.model.Basket;
import com.mitrais.jpqi.springcarrot.model.Carrot;
import com.mitrais.jpqi.springcarrot.model.Employee;
import com.mitrais.jpqi.springcarrot.model.Reward;
import com.mitrais.jpqi.springcarrot.repository.BasketRepository;
import com.mitrais.jpqi.springcarrot.repository.RewardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RewardService {
    private RewardRepository rewardRepository;

    public RewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    public void insertRewardIntoDB (Reward reward) {
        rewardRepository.save(reward);
     }

    public void updateRewardIntoDB (Reward reward, String id) {
        Optional<Reward> cr = rewardRepository.findById(id);
        if (cr.isPresent()) {
            Reward rw = cr.get();
            rw.setActive(reward.isActive());
            rewardRepository.save(rw);
        }
    }

    public List<Reward> findAllReward () {
        return rewardRepository.findAll();
    }

    public Reward findRewardById (String id) {
        Optional<Reward> reward = rewardRepository.findById(id);
        if (reward.isPresent()) {
            return reward.get();
        }
        return null;
    }

    public void removeRewardById (String id) {
        rewardRepository.deleteById(id);
    }
}
