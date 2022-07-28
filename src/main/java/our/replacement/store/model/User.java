package our.replacement.store.model;

import our.replacement.store.dto.ShortProductDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_user", schema = "avito_schema")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;
    @Column(name="login")
    private String login;
    @Column(name="password")
    private String password;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="create_timestamp")
    private LocalDateTime createTimestamp;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seller") // LAZY - ???
    private List<Product> listUserProduct; // продажи


    public User(Long userId, String login, String password, String firstName, String lastName, String phoneNumber, LocalDateTime createTimestamp, List<Product> listUserProduct) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.createTimestamp = createTimestamp;
        this.listUserProduct = listUserProduct;
    }

    public User() {}

    public Long getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getCreateTimestamp() {
        return createTimestamp;
    }

    public List<Product> getListUserProduct(){ return listUserProduct;}


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCreateTimestamp(LocalDateTime createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public void setListUserProduct(List<Product> listUserProduct){this.listUserProduct = listUserProduct;}

}
