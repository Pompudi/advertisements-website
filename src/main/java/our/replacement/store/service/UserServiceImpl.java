package our.replacement.store.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import our.replacement.store.dto.ShortProductDto;
import our.replacement.store.dto.ShortUserDto;
import our.replacement.store.dto.UserDto;
import our.replacement.store.exception.EntityNotFoundException;
import our.replacement.store.model.User;
import our.replacement.store.repository.ProductRepository;
import our.replacement.store.repository.UserRepository;
import our.replacement.store.util.UserMapper;
import our.replacement.store.util.ProductMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private ProductRepository productRepository;
    private UserMapper userMapper;
    private ProductMapper productMapper;

    private final PasswordEncoder passwordEncoder;

    private final Logger logger;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository, UserMapper userMapper, ProductMapper productMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.userMapper = userMapper;
        this.productMapper = productMapper;
        this.passwordEncoder = passwordEncoder;
        this.logger = LogManager.getLogger(UserDetailServiceImpl.class);
    }

    @Override
    public UserDto findUserById(Long id) {
        logger.info("Attempt to get user entity with id {}", id);
        User user = userRepository.findById(id).orElseThrow(() ->  new EntityNotFoundException(id.toString(), User.class.getSimpleName(), "User not found"));
        logger.info("Successful get user entity with id {}", id);
        return userMapper.mapToUserDto(user, null);
    }
    @Override
    @Transactional
    public UserDto update(Long id, ShortUserDto userUpdateDto) {
        logger.info("Attempt to update user entity with id {}", id);
        userUpdateDto.setUserId(id);
        User user = userRepository.findById(id).orElseThrow(() ->  new EntityNotFoundException(id.toString(), User.class.getSimpleName(), "User not found"));
        userRepository.save(userMapper.mapShortToUserEntity(userUpdateDto, user));
        logger.info("Successful update user entity with id {}", id);
        return userMapper.mapToUserDto(user, null);
    }
    @Override
    public List<ShortProductDto> getUserDealHistoryPurchased(Long id) {
        logger.info("Successful get of a list of products purchased by the user with id {}", id);
        return productRepository.findBySellerIdAndCustomerIdIsNotNull(id).stream().map(
                x-> productMapper.convertToShortDto(x)).toList();

    }

    @Override
    public List<ShortProductDto> getUserDealHistorySales(Long id) {
        logger.info("Successful receipt of a list of products sold by the user with id {}", id);
        return productRepository.findByCustomerId(id).stream().map(
                x-> productMapper.convertToShortDto(x)).toList();
    }

    @Transactional
    public void register(UserDto user){
        logger.info("Attempt to register a user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTimestamp(LocalDateTime.now());
        userRepository.save(userMapper.mapToUserEntity(user));
        logger.info("Successful to register a user");
    }


}
