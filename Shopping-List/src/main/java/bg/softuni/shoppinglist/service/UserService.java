package bg.softuni.shoppinglist.service;

import bg.softuni.shoppinglist.bindingModel.UserLoginDto;
import bg.softuni.shoppinglist.bindingModel.UserRegisterDto;
import bg.softuni.shoppinglist.dao.UserRepository;
import bg.softuni.shoppinglist.model.User;
import bg.softuni.shoppinglist.session.UserSession;
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

    public void registerUser(UserRegisterDto userRegisterDto) {
        String username = userRegisterDto.getUsername();
        if (usernameExists(username)) {
            return;
        }

        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            return;
        }
        String password = userRegisterDto.getPassword();
        userRegisterDto.setPassword(passwordEncoder.encode(password));

        User userEntity = modelMapper.map(userRegisterDto, User.class);
        userRepository.save(userEntity);
        fillUserSession(username);
    }

    public void loginUser(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        Optional<User> optionalUser = this.userRepository.findUserByUsername(username);
        if (optionalUser.isEmpty()) {
            return;
        }
        User userEntity = optionalUser.get();
        if (!passwordEncoder.matches(userLoginDto.getPassword(), userEntity.getPassword())) {
            return;
        }
        fillUserSession(username);
    }

    public boolean userEntityByUsernameAndPassword(UserLoginDto userLoginDto) {
        Optional<User> optionalUser = userRepository.findUserByUsername(userLoginDto.getUsername());
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            return false;
        }

        return passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword());
    }

    private boolean usernameExists(String username) {
        return this.userRepository.findUserByUsername(username).isPresent();
    }

    private void fillUserSession(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isPresent()) {
            User userEntity = optionalUser.get();
            userSession.setLoggedIn(true);
            userSession.setId(userEntity.getId());
            userSession.setUsername(userEntity.getUsername());
            userSession.setEmail(userEntity.getEmail());
        }
    }

    public void logout() {
        userSession.setLoggedIn(false);
        userSession.setId(null);
        userSession.setUsername(null);
        userSession.setEmail(null);
    }
}