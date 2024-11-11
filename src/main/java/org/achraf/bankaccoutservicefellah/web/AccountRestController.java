package org.achraf.bankaccoutservicefellah.web;

import org.achraf.bankaccoutservicefellah.dto.BankAccountRequestDTO;
import org.achraf.bankaccoutservicefellah.dto.BankAccountResponseDTO;
import org.achraf.bankaccoutservicefellah.entities.BankAccount;
import org.achraf.bankaccoutservicefellah.mappers.AccountMapper;
import org.achraf.bankaccoutservicefellah.repositories.BankAccountRepository;
import org.achraf.bankaccoutservicefellah.service.AccountService;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private BankAccountRepository bankAccountRepository;
    private AccountMapper accountMapper;
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts (){
        return bankAccountRepository.findAll ();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount ( @PathVariable String id ){
        return bankAccountRepository.findById ( id ).orElseThrow (()->new RuntimeException ( String.format ( "Account %s not found ",id ) ));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save( @RequestBody BankAccountRequestDTO requestDTO)
    {
        return accountService.addAccount (requestDTO);
    }
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update( @PathVariable String id , @RequestBody BankAccount bankAccount )
    {
        BankAccount bankAccount1=bankAccountRepository.findById ( id ).orElseThrow (  );
        if (bankAccount.getBalance () != null) bankAccount1.setBalance ( bankAccount.getBalance () );
        if (bankAccount.getCreateAt () != null) bankAccount1.setCreateAt ( new Date (  ) );
        if (bankAccount.getType () != null) bankAccount1.setType ( bankAccount.getType () );
        if (bankAccount.getCurrency () != null) bankAccount1.setCurrency ( bankAccount.getCurrency ( ) );
        return bankAccountRepository.save ( bankAccount1 );

    }
    @DeleteMapping("/bankAccount/{id}")
    public void deleteAccount ( @PathVariable String id )
        {
         bankAccountRepository.deleteById ( id );
        }
}
