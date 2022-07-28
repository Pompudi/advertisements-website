package our.replacement.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import our.replacement.store.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
