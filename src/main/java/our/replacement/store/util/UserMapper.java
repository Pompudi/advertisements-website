package our.replacement.store.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import our.replacement.store.dto.ShortUserDto;
import our.replacement.store.dto.UserDto;
import our.replacement.store.model.User;

@Component
public class UserMapper {
    private ProductMapper productMapper;
    @Autowired
    public UserMapper(ProductMapper productMapper){
        this.productMapper = productMapper;
    }

    public UserDto mapToUserDto(User entity, UserDto dto){
        if (dto == null){
            dto = new UserDto();
        }
        dto.setUserId(entity.getUserId());
        dto.setLogin(entity.getLogin());
        dto.setPassword(entity.getPassword());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setFullName();
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setCreateTimestamp(entity.getCreateTimestamp());
        dto.setListUserProduct(entity.getListUserProduct().stream().map(x -> productMapper.convertToShortDto(x)).toList());
        return dto;
    }

    public User mapToUserEntity(UserDto dto) {
        User entity = new User();
        entity.setUserId(dto.getUserId());
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setCreateTimestamp(dto.getCreateTimestamp());

        return entity;
    }

    public User mapShortToUserEntity(ShortUserDto dto, User entity) {
        if (entity != null) {
            entity.setUserId(dto.getUserId());
            entity.setFirstName(dto.getFirstName());
            entity.setLastName(dto.getLastName());
            entity.setPhoneNumber(dto.getPhoneNumber());
        }
        return entity;
    }
}
