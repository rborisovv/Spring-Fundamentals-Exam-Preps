package bg.softuni.gira.service;

import bg.softuni.gira.bindingModel.UserLoginDto;
import bg.softuni.gira.bindingModel.UserRegisterDto;
import bg.softuni.gira.dao.UserRepository;
import bg.softuni.gira.model.User;
import bg.softuni.gira.session.UserSession;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final UserSession userSession;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, UserSession userSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.userSession = userSession;
    }

    public boolean registerUser(UserRegisterDto userRegisterDto) {
        String username = userRegisterDto.getUsername();
        String email = userRegisterDto.getEmail();
        String password = userRegisterDto.getPassword();
        String confirmPassword = userRegisterDto.getConfirmPassword();

        if (userRepository.findUserByUsernameOrEmail(username, email).isPresent()) {
            return false;
        }

        if (!password.equals(confirmPassword)) {
            return false;
        }

        userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        User userEntity = modelMapper.map(userRegisterDto, User.class);
        userRepository.save(userEntity);

        initUserSession(userEntity);
        return true;
    }

    private void initUserSession(User user) {
        userSession.setId(user.getId());
        userSession.setUsername(user.getUsername());
        userSession.setEmail(user.getEmail());
    }

    public boolean loginUser(UserLoginDto userLoginDto) {
        String email = userLoginDto.getEmail();
        String password = userLoginDto.getPassword();

        Optional<User> optionalUser = userRepository.findUserByEmail(email);
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

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void logout() {
        userSession.setId(null);
        userSession.setUsername(null);
        userSession.setEmail(null);
    }
}