package sinara_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sinara_project.models.user.UserApp;
import sinara_project.repositories.UserRepository;

@Service
public class UserAppDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp user = userRepository.findByName(username);

        if (user == null)
            throw new UsernameNotFoundException("user not found");

        return null;
    }
}
