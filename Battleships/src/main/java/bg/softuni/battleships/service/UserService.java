package bg.softuni.battleships.service;

import bg.softuni.battleships.bindingModel.UserLoginDto;
import bg.softuni.battleships.bindingModel.UserRegisterDto;
import bg.softuni.battleships.dao.UserRepository;
import bg.softuni.battleships.model.User;
import bg.softuni.battleships.session.UserSession;
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
        String email = userRegisterDto.getEmail();
        if (userRepository.findByUsernameOrEmail(username, email).isPresent()) {
            return;
        }

        userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        User userEntity = modelMapper.map(userRegisterDto, User.class);
        userRepository.save(userEntity);
        initUserSession(userEntity);
    }

    public boolean loginUser(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            return false;
        }
        User userEntity = optionalUser.get();

        if (!passwordEncoder.matches(userLoginDto.getPassword(), userEntity.getPassword())) {
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

    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    private void initUserSession(User user) {
        userSession.setId(user.getId());
        userSession.setUsername(user.getUsername());
        userSession.setEmail(user.getEmail());
    }

}