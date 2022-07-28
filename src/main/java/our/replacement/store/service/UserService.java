package our.replacement.store.service;

import our.replacement.store.dto.ShortProductDto;
import our.replacement.store.dto.ShortUserDto;
import our.replacement.store.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto findUserById(Long id);
    UserDto update(Long id, ShortUserDto userUpdateDTO);
    List<ShortProductDto> getUserDealHistoryPurchased(Long id);
    List<ShortProductDto> getUserDealHistorySales(Long id);
}
