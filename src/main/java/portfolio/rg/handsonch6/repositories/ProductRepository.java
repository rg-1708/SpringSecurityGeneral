package portfolio.rg.handsonch6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.rg.handsonch6.domain.Product;

public interface ProductRepository
        extends JpaRepository<Product, Integer> {
}