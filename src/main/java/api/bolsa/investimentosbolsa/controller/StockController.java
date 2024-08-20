package api.bolsa.investimentosbolsa.controller;

import api.bolsa.investimentosbolsa.controller.dto.CreateStockDto;
import api.bolsa.investimentosbolsa.controller.dto.CreateUserDto;
import api.bolsa.investimentosbolsa.entity.User;
import api.bolsa.investimentosbolsa.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/v1/stocks")
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDto createStockDto) {

        stockService.createStock(createStockDto);

        return ResponseEntity.ok().build();
    }
}
