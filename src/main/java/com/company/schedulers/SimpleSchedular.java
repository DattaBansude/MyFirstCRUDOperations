package com.company.schedulers;

import com.company.entity.Developer;
import com.company.helper.DeveloperIdGenrator;
import com.company.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimpleSchedular {

    @Autowired
    private DeveloperRepository developerRepository;

    @Scheduled(fixedDelay = 5000)
    public void printSomething(){
        System.err.println("today is thursday");
    }

    @Scheduled(cron = "0 * * * * ?\n")
    public void printSomething2(){
        System.err.println("today is good day");
    }

    @Scheduled(cron = "0 0/2 * * * *")
    public void missdeveloperId()
    {
        List<Developer> missdeveloperChecks = developerRepository.findmissDeveloperId();

        for (Developer dev : missdeveloperChecks) {
            String newdevId = DeveloperIdGenrator.genratedeveloperId(dev);
            developerRepository.updateDeveloperId(dev.getId(), newdevId);
            System.out.println("updated developer id for :" +dev.getFName());
        }
    }

}
