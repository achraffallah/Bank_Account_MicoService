package org.achraf.bankaccoutservicefellah;

import org.achraf.bankaccoutservicefellah.entities.BankAccount;
import org.achraf.bankaccoutservicefellah.enums.AccountType;
import org.achraf.bankaccoutservicefellah.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
public class BankAccoutServiceFellahApplication {

    public static void main ( String[] args ) {
        SpringApplication.run ( BankAccoutServiceFellahApplication.class , args );
    }

    @Bean
    CommandLineRunner start( BankAccountRepository bankAccountRepository ){
        return  args -> {
            for (int i = 1 ; i<10;i++){
                BankAccount bankAccount = BankAccount.builder ( )
                        .id ( UUID.randomUUID ().toString () )
                        .type ( Math.random ()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT )
                        .balance ( 10000+Math.random ()*90000 )
                        .createAt ( new Date () )
                        .currency ( "MAD" )
                        .build ( );
                bankAccountRepository.save ( bankAccount );
            }
        };

    }
}
