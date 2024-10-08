package api.bolsa.investimentosbolsa.service;

import api.bolsa.investimentosbolsa.client.BrapiClient;
import api.bolsa.investimentosbolsa.controller.dto.AccountStockResponseDto;
import api.bolsa.investimentosbolsa.controller.dto.AssociateAccountStockDto;
import api.bolsa.investimentosbolsa.entity.AccountStock;
import api.bolsa.investimentosbolsa.entity.AccountStockId;
import api.bolsa.investimentosbolsa.repository.AccountRepository;
import api.bolsa.investimentosbolsa.repository.AccountStockRepository;
import api.bolsa.investimentosbolsa.repository.StockRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Value("#{environment.TOKEN}")
    private String TOKEN;
    private AccountRepository accountRepository;
    private StockRepository stockRepository;
    private AccountStockRepository accountStockRepository;
    private BrapiClient brapiClient;


    public AccountService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository, BrapiClient brapiClient) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
        this.brapiClient = brapiClient;

    }

    public void associateStock(String accountId, AssociateAccountStockDto dto) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var stock = stockRepository.findById(dto.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // DTO -> ENTITY

        var  id = new AccountStockId(account.getAccountId(), stock.getStockId());
        var entity = new AccountStock(
                id,
                stock,
                dto.quantity()
        );

        accountStockRepository.save(entity);
    }

    public List<AccountStockResponseDto> listStocks(String accountId) {
        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return account.getAccountStocks()
                .stream()
                .map(as ->
                        new AccountStockResponseDto(
                                as.getStock().getStockId(),
                                as.getQuantity(),
                                getTotal(as.getQuantity(), as.getStock().getStockId())
                        ))
                .toList();
    }

    private double getTotal(Integer quantity, String stockId) {

        var response = brapiClient.getQuote(TOKEN, stockId);

        var price = response.results().getFirst().regularMarketPrice();

        return quantity * price;
    }
}
