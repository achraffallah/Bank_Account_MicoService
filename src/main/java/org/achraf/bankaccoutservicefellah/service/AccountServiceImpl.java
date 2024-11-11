package org.achraf.bankaccoutservicefellah.service;

import org.achraf.bankaccoutservicefellah.dto.BankAccountRequestDTO;
import org.achraf.bankaccoutservicefellah.dto.BankAccountResponseDTO;
import org.achraf.bankaccoutservicefellah.entities.BankAccount;
import org.achraf.bankaccoutservicefellah.mappers.AccountMapper;
import org.achraf.bankaccoutservicefellah.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.dnd.DropTarget;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
   @Autowired
    private AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccount ( BankAccountRequestDTO bankAccountDTO ) {
        BankAccount bankAccount = BankAccount.builder ( )
                .id ( UUID.randomUUID ( ).toString () )
                .createAt ( new Date (  ) )
                .balance ( bankAccountDTO.getBalance ( ) )
                .type(bankAccountDTO.getType ())
                .currency ( bankAccountDTO.getCurrency () )
                .build ( );
        BankAccount savedBankAccount = bankAccountRepository.save ( bankAccount );
//        BankAccountResponseDTO bankAccountResponseDTO = BankAccountResponseDTO.builder()
//                .id(savedBankAccount.getId ())
//                .type ( savedBankAccount.getType () )
//                .createAt ( savedBankAccount.getCreateAt () )
//                .currency ( savedBankAccount.getCurrency () )
//                .balance ( savedBankAccount.getBalance ( ) )
//                .build();
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount ( savedBankAccount );
        return bankAccountResponseDTO;
    }
}
