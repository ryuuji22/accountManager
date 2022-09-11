package com.nttdata.account.infraestructure.persistance.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nttdata.account.domain.entities.Account;
import com.nttdata.account.infraestructure.models.AccountModel;

@Mapper(componentModel = "spring" ,uses = {ClientMapper.class})
public interface AccountMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "openingBalance", target = "openingBalance")
    @Mapping(source = "actualBalance", target = "actualBalance")
    @Mapping(source = "client", target = "client")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "enabled", target = "enabled")
    Account toAccount(AccountModel accountModel);

    List<Account> toAccounts(List<AccountModel> accounts);

    @InheritInverseConfiguration
    AccountModel toAccountModel(Account account);
}
