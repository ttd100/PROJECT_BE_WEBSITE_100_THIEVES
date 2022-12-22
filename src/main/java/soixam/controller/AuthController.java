package soixam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import soixam.dto.reponse.JwtResponse;
import soixam.dto.reponse.ResponseMessage;
import soixam.dto.request.ChangeAvatar;
import soixam.dto.request.ChangePassword;
import soixam.dto.request.SignInForm;
import soixam.dto.request.SignUpForm;
import soixam.model.Role;
import soixam.model.RoleName;
import soixam.model.User;
import soixam.security.jwt.JwtProvider;
import soixam.security.jwt.JwtTokenFilter;
import soixam.security.userpincal.UserPrinciple;
import soixam.service.impl.RoleServiceIMPL;
import soixam.service.impl.UserServiceIMPL;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin( origins = "*" )
//@RequestMapping( "/auth" )
public class AuthController {
    @Autowired
    UserServiceIMPL userServiceIMPL;
    @Autowired
    RoleServiceIMPL roleServiceIMPL;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @PostMapping( "/signup" )
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) {
        if (userServiceIMPL.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("nouser"), HttpStatus.OK);
        }
        if (userServiceIMPL.existsByEmail(signUpForm.getEmail())){
            return new ResponseEntity<>(new ResponseMessage("noemail"),HttpStatus.OK);
        }
        User user = new User(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()));
        String avatar = "https://firebasestorage.googleapis.com/v0/b/chinhbeo-18d3b.appspot.com/o/avatar.jpg?alt=media&token=56f66b7d-6196-42da-bb8f-73828108db1e";
        user.setAvatar(avatar);
        Set<String> strRole = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRole.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleServiceIMPL.findByName(RoleName.ADMIN).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleServiceIMPL.findByName(RoleName.PM).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleServiceIMPL.findByName(RoleName.USER).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userServiceIMPL.save(user);
        return new ResponseEntity<>(new ResponseMessage("Create_success!!!"), HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<?>login(@Valid @RequestBody SignInForm signInForm){
        System.out.println("check sigin >>>>" + signInForm.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getName(),userPrinciple.getAvatar(), userPrinciple.getAuthorities()));
    }
    @PutMapping( "/change_avatar" )
    public ResponseEntity<?> changeAvatar(HttpServletRequest httpServletRequest, @Valid @RequestBody ChangeAvatar changeAvatar) {
        String jwt = jwtTokenFilter.getJwt(httpServletRequest);
        String username = jwtProvider.getUsernameFormToken(jwt);
        User user;
        try {
            if (changeAvatar.getAvatar() == null || changeAvatar.getAvatar().trim().equals("")) {
                return new ResponseEntity<>(new ResponseMessage("no"), HttpStatus.OK);
            } else {
                user = userServiceIMPL.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with ->" +
                        "username:" + username));
                user.setAvatar(changeAvatar.getAvatar());
                userServiceIMPL.save(user);
            }
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.OK);
        }
    }

    @PutMapping( "change_password" )
    public ResponseEntity<?> changePassword(HttpServletRequest httpServletRequest, @Valid @RequestBody ChangePassword changePassword) {
        String jwt = jwtTokenFilter.getJwt(httpServletRequest);
        String username = jwtProvider.getUsernameFormToken(jwt);
        User user;
        try {
            user = userServiceIMPL.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with-> username" + username));
            boolean matches = passwordEncoder.matches(changePassword.getOldPassword(), user.getPassword());
            if (changePassword.getNewPassword() != null) {
                if (matches) {
                    user.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));//ma hoa mat khau
                    userServiceIMPL.save(user);
                } else {
                    return new ResponseEntity<>(new ResponseMessage("no"), HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(new ResponseMessage("yes"),HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.OK);
        }
    }
}
