package api.bolsa.investimentosbolsa.repository;

import api.bolsa.investimentosbolsa.entity.Account;
import api.bolsa.investimentosbolsa.entity.AccountStock;
import api.bolsa.investimentosbolsa.entity.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {

}
