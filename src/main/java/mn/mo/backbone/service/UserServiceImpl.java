package mn.mo.backbone.service;


import mn.mo.backbone.domain.ErrorResponse;
import mn.mo.backbone.domain.Role;
import mn.mo.backbone.domain.User;
import mn.mo.backbone.exception.ErrorBuilder;
import mn.mo.backbone.exception.ErrorDetails;
import mn.mo.backbone.exception.TokenException;
import mn.mo.backbone.repo.RoleRepository;
import mn.mo.backbone.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static mn.mo.backbone.constants.TokenConstants.INVALID_PARAMETER_REASON;
import static mn.mo.backbone.constants.TokenConstants.INVALID_PARAMETER_TXT;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

  @Autowired private UserRepository userRepository;
  @Autowired private RoleRepository roleRepository;
  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      log.info("user not found in the database!");
      throw new UsernameNotFoundException("user not found in the database!");
    } else {
      log.info("User found in the database {}", username);
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles()
        .forEach(
            role -> {
              authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
    return new org.springframework.security.core.userdetails.User(
        user.getUsername(), user.getPassword(), authorities);
  }

  @Override
  public User saveUser(User user) {
    log.info("saving new user to the database {}", user.getUsername());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public Role saveRole(Role role) {
    log.info("saving role to the database {}", role.getName());
    return roleRepository.save(role);
  }

  @Override
  public void addRoleToUser(String username, String roleName) {
    log.info("adding new role {} to the user {}", roleName, username);
    User user = userRepository.findByUsername(username);
    Role role = roleRepository.findByName(roleName);
    user.getRoles().add(role);
  }

  @Override
  public User getUser(String username) {
    log.info("fetching user from the database {}", username);
    return userRepository.findByUsername(username);
  }

  @Override
  public List<User> getUsers() {
    log.info("fetching all users from database");
    return userRepository.findAll();
  }

  private <T> void handleViolationExceptions(Set<ConstraintViolation<T>> constraintViolations) {
    ErrorResponse errorResponse =
        ErrorBuilder.formError(
            HttpStatus.BAD_REQUEST.value(), INVALID_PARAMETER_REASON, INVALID_PARAMETER_TXT);
    List<ErrorDetails> errorDetailsList = new ArrayList<>();
    constraintViolations.stream()
        .filter(e -> e != null)
        .forEach(
            v -> {
              String message = v.getMessage();
              String fieldName =
                  (v.getPropertyPath() != null) ? v.getPropertyPath().toString() : null;
              ErrorDetails errorDetails = new ErrorDetails(fieldName, message);
              errorDetailsList.add(errorDetails);
            });

    if (!CollectionUtils.isEmpty(errorDetailsList)) {
      errorResponse.setErrorDetails(errorDetailsList);
    }
    log.error(" request validation failed!");
    throw new TokenException(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST, errorResponse);
  }

  private void validateRequest(){

  }
}
