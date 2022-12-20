package com.company.cpluss.service;

import com.company.cpluss.Configurations.securityConfig.AuthenticationRequest;
import com.company.cpluss.Configurations.securityConfig.AuthenticationResponse;
import com.company.cpluss.Configurations.securityConfig.MyUserDetailsService;
import com.company.cpluss.Configurations.securityConfig.jwt.JWTUtil;
import com.company.cpluss.model.userInfo.Role;
import com.company.cpluss.model.userInfo.User;
import com.company.cpluss.repository.AddressRepo;
import com.company.cpluss.repository.RoleRepository;
import com.company.cpluss.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepo addressRepo;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;
    private final JWTUtil jwtTokenUtil;

    public UserService(UserRepository userRepository, AddressRepo addressRepo, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, MyUserDetailsService myUserDetailsService, JWTUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.addressRepo = addressRepo;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.myUserDetailsService = myUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public User signup(User user){
        user.setRoles(List.of(Role.builder().roleName("ROLE_USER").build()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        roleRepository.saveAll(user.getRoles());
        addressRepo.saveAll(user.getAddresses());
        return userRepository.save(user);
    }

    public AuthenticationResponse/*ResponseEntity<?>*/ createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        log.info("entering auth");
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);
    }


    public List<User> viewUsersList(){
        return userRepository.findAll();
    }
}
