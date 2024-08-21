package api.bolsa.investimentosbolsa.entity;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_accounts")
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private BillingAddress billingAddress;


    @Column(name = "description")
    private String description;

    @OneToMany
    @JoinColumn(name = "account_id")
    private List<AccountStock> accountStocks;


    public Account() {
    }

    @Column(name = "user_id")
    private UUID userId;

    public Account(UUID accountId, UUID userId, BillingAddress billingAddress, String description, List<AccountStock> accountStocks) {
        this.accountId = accountId;
        this.billingAddress = billingAddress;
        this.description = description;
        this.accountStocks = accountStocks;
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<AccountStock> getAccountStocks() {
        return accountStocks;
    }

    public void setAccountStocks(List<AccountStock> accountStocks) {
        this.accountStocks = accountStocks;
    }
}
