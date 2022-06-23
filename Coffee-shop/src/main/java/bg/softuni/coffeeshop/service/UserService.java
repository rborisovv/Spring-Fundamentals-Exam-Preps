package bg.softuni.coffeeshop.service;

import bg.softuni.coffeeshop.bindingModel.UserLoginDto;
import bg.softuni.coffeeshop.bindingModel.UserRegisterDto;
import bg.softuni.coffeeshop.dao.UserRepository;
import bg.softuni.coffeeshop.model.User;
import bg.softuni.coffeeshop.session.UserSession;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserSession userSession;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserSession userSession) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
    }

    public boolean registerUser(UserRegisterDto userRegisterDto) {
        String username = userRegisterDto.getUsername();
        String email = userRegisterDto.getEmail();
        String password = userRegisterDto.getPassword();
        String confirmPassword = userRegisterDto.getConfirmPassword();
        if (userRepository.findByUsernameOrEmail(username, email).isPresent()) {
            return false;
        }

        if (!password.equals(confirmPassword)) {
            return false;
        }
        userRegisterDto.setPassword(passwordEncoder.encode(password));
        User userEntity = modelMapper.map(userRegisterDto, User.class);
        userRepository.save(userEntity);

        initUserSession(userEntity);
        return true;
    }

    public boolean loginUser(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            return false;
        }

        User userEntity = optionalUser.get();
        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            return false;
        }

        initUserSession(userEntity);
        return true;
    }

    public void logout() {
        userSession.setId(null);
        userSession.setUsername(null);
        userSession.setEmail(null);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    private void initUserSession(User user) {
        userSession.setId(user.getId());
        userSession.setUsername(user.getUsername());
        userSession.setEmail(user.getEmail());
    }

    public Map<String, Integer> findAllUsersOrderByOrdersCount() {
        List<User> sortedUsers = userRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(user -> user.getOrders().size()))
                .collect(Collectors.toList());

        Collections.reverse(sortedUsers);

        Map<String, Integer> userOrdersMap = new HashMap<>();
        sortedUsers.forEach(user -> userOrdersMap.putIfAbsent(user.getUsername(), user.getOrders().size()));

        return userOrdersMap;
    }
}