package sinara_project.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       JWTService jwtService,
                       AuthenticationManager authenticationManager,
                       ModelMapper modelMapper,
                       @Value("${security.encoder.strength}") int encoderStrength) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder(encoderStrength);
    }

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
