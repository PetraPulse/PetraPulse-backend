package com.petrapulse.PetraPulse.services;

import com.petrapulse.PetraPulse.bo.AuthenticationRequest;
import com.petrapulse.PetraPulse.bo.RegisterRequest;
import com.petrapulse.PetraPulse.entities.AppUsersEntity;
import com.petrapulse.PetraPulse.repositories.UserDetailsJpaRepository;
import com.petrapulse.PetraPulse.util.exceptions.BodyGuardException;
import com.petrapulse.PetraPulse.util.validators.CompositeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.petrapulse.PetraPulse.util.validators.CompositeValidator.hasValue;
import static java.lang.String.join;
import static java.util.Objects.nonNull;
@Service
public class UsersDetailsServiceImpl implements UsersDetailsService{
    private final UserDetailsJpaRepository userDetailsJpaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    //since you have only one constructor we don't have to add @Autowired but if there was multiple we should add it
    public UsersDetailsServiceImpl(UserDetailsJpaRepository userDetailsJpaRepository,BCryptPasswordEncoder passwordEncoder) {
        this.userDetailsJpaRepository = userDetailsJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    protected void validate(List<String> violations) {
        if (!violations.isEmpty()) {
            throw new BodyGuardException(join(",", violations));
        }
    }
    @Override
    public void createPreValidation(RegisterRequest createDto) {
        List<String> violation = new CompositeValidator<RegisterRequest, String>()
                .addValidator(r -> hasValue(r.getUsername()), "User Name Cannot Be Empty")
                .addValidator(r -> r.getUsername() == null || !userDetailsJpaRepository.findByUsername(r.getUsername().toLowerCase()).isPresent(), "Username already exists")
                .addValidator(r -> nonNull(r.getPassword()), "Password Cannot Be Empty")
                .addValidator(r -> Pattern.compile("^(?=.*[A-Z]).{8,20}$").matcher(r.getPassword()).matches(), "Password should be minimum 8 characters with 1 upper case letter")
                .addValidator(r -> nonNull(r.getConfirmPassword()) && r.getConfirmPassword().equals(r.getPassword()), "Passwords do not match")
                .addValidator(r -> hasValue(r.getEmail()), "Email Cannot Be Empty")
                .addValidator(r -> r.getEmail() == null || Pattern.compile("^(.+)@(.+)\\.(.+)$").matcher(r.getEmail()).matches(), "Enter a valid email")
                .addValidator(r -> hasValue(r.getCountry()), "Country Cannot Be Empty")
                .addValidator(r -> nonNull(r.getDateOfBirth()), "Date Of Birth Cannot Be Empty")
                .addValidator(r -> r.getDateOfBirth() == null || !r.getDateOfBirth().isAfter(LocalDate.now()), "Invalid Date Of Birth")
                .validate(createDto);
        validate(violation);
    }

    private boolean doesUserExist(String username) {
        return userDetailsJpaRepository.findByUsername(username).isPresent();
    }
    private boolean doesPasswordMatch(String username, String password) {
        Optional<AppUsersEntity> userOptional = userDetailsJpaRepository.findByUsername(username);
        return userOptional.map(user -> passwordEncoder.matches(password, user.getPassword())).orElse(false);
    }


    @Override
    public void createPostValidation(AuthenticationRequest request) {
        List<String> violation = new CompositeValidator<AuthenticationRequest, String>()
                .addValidator(r -> hasValue(r.getUsername()), "User Name Cannot Be Empty")
                .addValidator(r -> nonNull(r.getPassword()), "Password Cannot Be Empty")
                .addValidator(r->doesUserExist(r.getUsername()),"User Not Found")
                .addValidator(r->doesPasswordMatch(r.getUsername(),r.getPassword()),"Incorrect Password")
                .validate(request);
        validate(violation);
    }
}
