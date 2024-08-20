package api.bolsa.investimentosbolsa.service;

import api.bolsa.investimentosbolsa.controller.dto.CreateStockDto;
import api.bolsa.investimentosbolsa.entity.Stock;
import api.bolsa.investimentosbolsa.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(CreateStockDto createStockDto) {

        // DTO -> ENTITY

        var stock = new Stock(
                createStockDto.stockId(),
                createStockDto.description()
        );

        stockRepository.save(stock);
    }
}
