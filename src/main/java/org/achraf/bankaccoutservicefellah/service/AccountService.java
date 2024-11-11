package org.achraf.bankaccoutservicefellah.service;

import org.achraf.bankaccoutservicefellah.dto.BankAccountRequestDTO;
import org.achraf.bankaccoutservicefellah.dto.BankAccountResponseDTO;
import org.achraf.bankaccoutservicefellah.entities.BankAccount;

public interface AccountService  {
    public BankAccountResponseDTO addAccount( BankAccountRequestDTO bankAccountDTO);



}
