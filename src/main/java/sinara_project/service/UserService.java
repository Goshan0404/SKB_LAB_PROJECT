package sinara_project.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sinara_project.models.user.UserApp;
import sinara_project.models.user.UserRegisterDto;
import sinara_project.repositories.UserRepository;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    // убарть 12 в констатнту как и в конфиге
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public UserApp register(UserRegisterDto user) {
        log.info("Регистрация пользователя");
        UserApp userApp = modelMapper.map(user, UserApp.class);
        userApp.setPassword(bCryptPasswordEncoder.encode(userApp.getPassword()));
        return userRepository.save(userApp);
    }

    public String verify(UserRegisterDto user) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));

        if (!authentication.isAuthenticated()) {
            log.info("Аунтификация неудалась");
            return "fail";
        }
        return jwtService.generate(user.getName());
    }
}
