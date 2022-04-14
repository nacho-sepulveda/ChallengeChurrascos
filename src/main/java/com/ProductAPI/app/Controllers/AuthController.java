package com.ProductAPI.app.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ProductAPI.app.EntityObjects.Role;
import com.ProductAPI.app.EntityObjects.User;
import com.ProductAPI.app.PayLoad.LoginDTO;
import com.ProductAPI.app.PayLoad.SignUpDTO;
import com.ProductAPI.app.Repositories.RoleRepository;
import com.ProductAPI.app.Repositories.UserRepository;
import com.ProductAPI.app.Segurity.JWTAuthResonseDTO;
import com.ProductAPI.app.Segurity.JwtTokenProvider;


import java.util.Collections;
import java.util.Date;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResonseDTO> authenticateUser(@RequestBody @Valid LoginDTO loginDto){
    	try {
    		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsernameOrEmail(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //obtengo el token
            String token = jwtTokenProvider.tokenGeneration(authentication);
            return ResponseEntity.ok(new JWTAuthResonseDTO(token));
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDto){
    	if(userRepository.existsByUsername(signUpDto.getUsername())) {
    		return new ResponseEntity<>("This username already exists", HttpStatus.BAD_REQUEST);
    	}
    	
    	if(userRepository.existsByEmail(signUpDto.getEmail())) {
    		return new ResponseEntity<>("This user email already exists", HttpStatus.BAD_REQUEST);
    	}
    	
        // create user object
    	User userRequest = new User();
        userRequest.setFirst_name(signUpDto.getFirst_name());
        userRequest.setLast_name(signUpDto.getLast_name());
        userRequest.setCreated(new Date());
        userRequest.setUsername(signUpDto.getUsername());
        userRequest.setEmail(signUpDto.getEmail());
        userRequest.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        userRequest.setRoles(Collections.singleton(roles));

        userRepository.save(userRequest);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
}
