package api.bolsa.investimentosbolsa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_accounts_stocks")
public class AccountStock {

    @EmbeddedId
    private AccountStockId id;


    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "quantity")
    private Integer quantity;

    public AccountStock(AccountStockId id,  Stock stock, Integer quantity) {
        this.id = id;
        this.stock = stock;
        this.quantity = quantity;
    }

    public AccountStock() {
    }

    public AccountStockId getId() {
        return id;
    }

    public void setId(AccountStockId id) {
        this.id = id;
    }


    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
