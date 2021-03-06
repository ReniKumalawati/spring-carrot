package com.mitrais.jpqi.springcarrot.controller;

import com.mitrais.jpqi.springcarrot.model.Achievement;
import com.mitrais.jpqi.springcarrot.model.AggregateModel.AchievementEachMonth;
import com.mitrais.jpqi.springcarrot.model.AggregateModel.Hasil;
import com.mitrais.jpqi.springcarrot.model.Transaction;
import com.mitrais.jpqi.springcarrot.responses.TransactionResponse;
import com.mitrais.jpqi.springcarrot.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

@CrossOrigin
@RestController
@RequestMapping("api/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping
    TransactionResponse findAllTransactions() {
        return transactionService.findAllTransactions();
    }

    @GetMapping("pending")
    TransactionResponse findAllPendingTransactions() {
        return transactionService.findAllPendingTransaction();
    }

    @GetMapping("{id}")
    TransactionResponse findTransactionsByEmployee(@PathVariable String id) {
        return transactionService.findTransactionByEmployee(id);
    }

    @PostMapping
    public TransactionResponse createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @PatchMapping("approve")
    public TransactionResponse approveTransaction(@RequestParam String id) {
        return transactionService.approveTransaction(id);
    }

    @PatchMapping("approve-donation")//use social foundation ID
    public TransactionResponse approveDonation(@RequestParam String id) {
        return transactionService.approveDonation(id);
    }

    @PatchMapping("approve-request")//use transaction id
    public TransactionResponse approveRequest(@RequestParam String id) {
        return transactionService.approveRequestTransaction(id);
    }

    @PatchMapping("decline")
    public TransactionResponse declineTransaction(@RequestParam String id) {
        return transactionService.declineTransaction(id);
    }

    @PatchMapping("decline-donation")
    public TransactionResponse declineDonation(@RequestParam String id) {
        return transactionService.declineDonation(id);
    }

    @GetMapping("{id}/spent_for_reward")
    public int getTotalSpentForReward(@PathVariable String id) {
        return transactionService.countCarrotSpentForRewardItem(id);
    }

    @GetMapping("{id}/spent_for_sharing")
    public int getTotalSpentForSharing(@PathVariable String id) {
        return transactionService.countCarrotSpentForSharing(id);
    }
    @GetMapping("total-earned-this-month/{id}")
    public int getTotalEarnedThisMonth(@PathVariable String id) {
        return transactionService.countCarrotEarnedThisMonth(id);
    }

    @GetMapping("by-bazaar/{id}")
    public TransactionResponse getAllTransactionByBazaarId(@PathVariable String id) {
        return transactionService.findTransactionByBazaarId(id);
    }

    @GetMapping("by-status")
    public TransactionResponse getAllTransactionByStatus(@RequestParam String type) {
        return transactionService.findTransactionByType(type);
    }

    @GetMapping("by-date-status")
    public TransactionResponse getAllTransactionByStatusAndDate(@RequestParam (required = false) String type,
                                                              @RequestParam Long startDate, @RequestParam Long endDate) {
        //convert the timestamp to dates
        LocalDateTime startDateC =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(startDate),
                        TimeZone.getDefault().toZoneId());

        LocalDateTime endDateC =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(endDate),
                        TimeZone.getDefault().toZoneId());
        System.out.println(startDateC);
        System.out.println(endDateC);

        if (type == null){
            return transactionService.findTransactionByDate(startDateC, endDateC);
        }

        String[] types = new String[4];
        switch (type) {
            case "ALL":
                types[0] = "SHARED";
                types[1] = "DONATION";
                types[2] = "BAZAAR";
                types[3] = "REWARD";
                break;
            default:
                types[0] = type;
                break;
        }
        return transactionService.findTransactionByTypeAndDate(types, startDateC, endDateC);
    }

    @GetMapping("mostearned")
    public  List<Hasil> getEmployeeByCarrotEarned() {
        return transactionService.sortByMostEarn();
    }

    @GetMapping("total-earned/{id}")
    public  List<Hasil> getEmployeeTotalEarnedCarrot(@PathVariable String id) {
        return transactionService.getTotalEarnedAmt(id);
    }


    @GetMapping("achieved-achievements")
    public List<AchievementEachMonth> getAchievementsAchievedInCurrentMonth(){
        return transactionService.findAchievedAchievementInCurrentMonth();
    }

    @GetMapping("employee-by-donation")
    public List<Hasil> getEmployeeSortedByDonationAmount(){
        return transactionService.findAllEmployeeSortedByCarrotSpentForDonation();
    }
    @GetMapping("employee-by-reward")
    public List<Hasil> getEmployeeSortedByRewardAmount(){
        return  transactionService.findAllEmployeeSortedBySpentCarrotForRewards();
    }
    @GetMapping("employee-by-sharing")
    public List<Hasil> getEmployeeSortedByShareAmount(){
        return  transactionService.findAllEmployeeSortedBySpentCarrotForSharing();
    }

}
