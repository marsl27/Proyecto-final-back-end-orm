package com.example.clinicaOdontologica.service.auth;

import com.example.clinicaOdontologica.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AppUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User email not found"));
    }
    //Este metodo valida que el que se esta conectando, tenga un rol valido para ingresar.


    /*public void register(AppUser usuario) throws UserAlreadyExistException {

        //Let's check if user already registered with us
        if(userRepository.findByEmail(usuario.getEmail()).isPresent()){
            throw new UserAlreadyExistException("User already exists for this email");
        }
        AppUser user = new AppUser();
        BeanUtils.copyProperties(usuario, user);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(usuario.getPassword()));
        userRepository.save(user);
    }

    public String processRegister(AppUser user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "register_success";
    }*/

}
