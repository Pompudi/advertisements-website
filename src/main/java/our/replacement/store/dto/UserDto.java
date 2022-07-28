package our.replacement.store.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class UserDto {

    private Long userId;

    @Pattern(regexp = "^[a-zA-Z0-9_\\@.+&?\\-]+", message = "Введите корректный логин. " +
            "Допускаются буквы латинского алфавита, цифры и спец символы : _@.+&-?")
    @NotEmpty(message = "Поле логина не должно быть пустым")
    private String login;
    @NotEmpty(message = "Поле пароля не должно быть пустым")
    @Pattern(regexp = "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[#$%^&+=])\\S{8,}\\z",
            message = "Пароль должен содержать минимум 8 символов, заглавную и строчную букву латинского алфавита," +
                    " цифру и спец. символ и не должен содержать пробелы")
    private String password;
    @NotEmpty(message = "Поле имени не должно быть пустым")
    @Size(min = 2, max = 64, message = "Имя должно содержать от 2 до 64 символов")
    @Pattern(regexp = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$", message="Введите корректное имя")
    private String firstName;
    @NotEmpty(message = "Поле фамилии не должна быть пустым")
    @Size(min = 2, max = 64, message = "Имя должно содержать от 2 до 64 символов")
    @Pattern(regexp = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$", message="Введите корректную фамилию")
    private String lastName;
    private String fullName;
    @Pattern(regexp = "^(\\+7)(\\(\\d{3}\\)\\ )\\d{3}\\-\\d{2}\\-\\d{2}$",
            message = "Укажиет телефон в формате: +7(___) ___-__-__")
    @NotEmpty(message = "Укажите номер телефона")
    private String phoneNumber;
    private LocalDateTime createTimestamp;
    private List<ShortProductDto> listUserProduct ;

    public UserDto() {
    }

    public UserDto(Long userId, String login, String password, String firstName, String lastName, String phoneNumber, LocalDateTime createTimestamp, List<ShortProductDto> listUserProduct) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.phoneNumber = phoneNumber;
        this.createTimestamp = createTimestamp;
        this.listUserProduct = listUserProduct;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFullName(){this.fullName = this.firstName  + " " + this.lastName;}

    public String getFullName(){return fullName;}

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public List<ShortProductDto> getListUserProduct(){
        return listUserProduct;
    }




    public LocalDateTime getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(LocalDateTime createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public void setListUserProduct(List<ShortProductDto> listUserProduct){
        this.listUserProduct = listUserProduct;
    }
}
