package our.replacement.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import our.replacement.store.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
