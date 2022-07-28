package our.replacement.store.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class ShortUserDto {
    private Long userId;
    @NotEmpty(message = "Поле имени не должно быть пустым")
    @Size(min = 2, max = 64, message = "Имя должно содержать от 2 до 64 символов")
    @Pattern(regexp = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$", message="Введите корректную фамилию")
    private String firstName;
    @NotEmpty(message = "Поле фамилии не должна быть пустым")
    @Size(min = 2, max = 64, message = "Имя должно содержать от 2 до 64 символов")
    @Pattern(regexp = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$", message="Введите корректную фамилию")
    private String lastName;
    private String fullName;
    @NotEmpty(message = "Укажите номер телефона")
    @Pattern(regexp = "^(\\+7)(\\(\\d{3}\\)\\ )\\d{3}\\-\\d{2}\\-\\d{2}$",
            message = "Укажиет телефон в формате: +7(___) ___-__-__")
    private String phoneNumber;

    public ShortUserDto() {
    }

    public ShortUserDto(Long userId, String login, String password, String firstName, String lastName, String phoneNumber, LocalDateTime createTimestamp, List<ShortProductDto> listUserProduct) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.phoneNumber = phoneNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

}


