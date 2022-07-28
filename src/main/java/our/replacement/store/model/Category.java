package our.replacement.store.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_category", schema = "avito_schema")
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp;

    public Category(Long categoryId, String name, LocalDateTime createTimestamp) {
        this.categoryId = categoryId;
        this.name = name;
        this.createTimestamp = createTimestamp;
    }

    public Category() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(LocalDateTime createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}
