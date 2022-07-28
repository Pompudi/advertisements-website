package our.replacement.store.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import our.replacement.store.model.User;
import our.replacement.store.repository.UserRepository;
import our.replacement.store.security.UserDetailsImpl;

import java.util.Optional;


@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    private final Logger logger;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.logger = LogManager.getLogger(UserDetailServiceImpl.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Attempt to load user with username {}", username);

        Optional<User> user = userRepository.findByLogin(username);

        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found!");
        logger.info("Successful load user with username {}", username);
        return new UserDetailsImpl(user.get());
    }

    public UserDetailsImpl getAuthUser() {
        logger.info("Attempt to load currently logged in user");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
            logger.info("User not authenticated");
            return null;
        }
        logger.info("Successful load of the logged in user");
        return (UserDetailsImpl) authentication.getPrincipal();
    }

}
