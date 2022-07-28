package our.replacement.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import our.replacement.store.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM avito_schema.tb_product WHERE seller_id = :sellerId AND customer_id IS NOT NULL",
            nativeQuery = true)
    List<Product> findBySellerIdAndCustomerIdIsNotNull(Long sellerId);

    @Query(value = "SELECT * FROM avito_schema.tb_product WHERE customer_id = :customerId", nativeQuery = true)
    List<Product> findByCustomerId(Long customerId);

    @Query(value = "FROM Product WHERE seller.userId = :sellerId AND active IS FALSE")
    List<Product> findBySellerIdAndNotActive(Long sellerId);

    @Query(value = "FROM Product WHERE seller.userId = :sellerId AND active IS TRUE")
    List<Product> findBySellerIdAndActive(Long sellerId);

    @Query(value = "FROM Product WHERE productId = :productId AND active IS TRUE")
    Optional<Product> findByIdAndActive(Long productId);
}
