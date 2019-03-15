package com.mitrais.jpqi.springcarrot.controller;

import com.mitrais.jpqi.springcarrot.model.Award;
import com.mitrais.jpqi.springcarrot.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/awards")
public class AwardController {
    @Autowired
    AwardService awardService;

    @GetMapping
    public List<Award> getAllAwards() { return awardService.findAllAwards(); }

    @PostMapping
    public void create(@RequestBody Award award) { awardService.createAward(award); }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) { awardService.deleteAward(id); }

    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody Award award) { awardService.updateAward(id, award);}

}