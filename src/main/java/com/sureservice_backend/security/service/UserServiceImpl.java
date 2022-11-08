package com.sureservice_backend.security.service;

import com.sureservice_backend.security.domain.model.entity.*;
import com.sureservice_backend.security.domain.persistence.*;
import com.sureservice_backend.security.domain.service.UserService;
import com.sureservice_backend.security.domain.service.communication.*;
import com.sureservice_backend.security.middleware.JwtHandler;
import com.sureservice_backend.security.middleware.UserDetailsImpl;
import com.sureservice_backend.security.resource.AuthenticateResource;
import com.sureservice_backend.shared.exception.ResourceNotFoundException;
import com.sureservice_backend.shared.exception.ResourceValidationException;
import com.sureservice_backend.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CloudinaryService cloudinaryService;
    @Autowired
    JwtHandler handler;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EnhancedModelMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username: %s", username)));
        return UserDetailsImpl.build(user);
    }

    @Override
    public ResponseEntity<?> authenticate(AuthenticateRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()
                    ));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = handler.generateToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            AuthenticateResource resource = mapper.map(userDetails, AuthenticateResource.class);
            resource.setRoles(roles);
            resource.setToken(token);

            AuthenticateResponse response = new AuthenticateResponse(resource);

            return ResponseEntity.ok(response.getResource());


        } catch (Exception e) {
            AuthenticateResponse response = new AuthenticateResponse(String.format("An error occurred while authenticating: %s", e.getMessage()));
            return ResponseEntity.badRequest().body(response.getMessage());
        }

    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<?> delete(Long userId) {
        return userRepository.findById(userId).map(announcement-> {
            userRepository.delete(announcement);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("User", userId));
    }

    @Override
    public User updatePassword(Long userId, UpdatePasswordRequest request) {

        User user = userRepository.getById(userId);

        if(!Objects.equals(request.getNewPassword(), request.getConfirmPassword())){
            throw new ResourceValidationException("The passwords doesn't match");
        }

        try {
            user.setPassword(encoder.encode(request.getNewPassword()));

            return userRepository.save(user);

        } catch (Exception e){
            throw new ResourceValidationException(e.getMessage());
        }

    }

    @Override
    public User updateImage(Long userId, MultipartFile multipartFile) throws IOException {
        User user = userRepository.getById(userId);

        try {

            BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
            if(bi == null){
                throw new ResourceValidationException("Image not valid");
            }
            Map result = cloudinaryService.upload(multipartFile);

            if(!Objects.equals(user.getImage_Id(), "mmb0zluthi93wazo6vaa")){
                cloudinaryService.delete(user.getImage_Id());
            }

            user.setImage_Id((String)result.get("public_id"));
            user.setImage_url((String)result.get("url"));

            return userRepository.save(user);

        } catch (Exception e){
            throw new ResourceValidationException(e.getMessage());
        }

    }


}