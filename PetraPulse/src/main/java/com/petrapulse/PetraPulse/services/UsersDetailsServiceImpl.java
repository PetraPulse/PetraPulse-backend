package com.petrapulse.PetraPulse.services;

import com.petrapulse.PetraPulse.bo.RegisterRequest;
import com.petrapulse.PetraPulse.repositories.UserDetailsJpaRepository;
import com.petrapulse.PetraPulse.util.exceptions.BodyGuardException;
import com.petrapulse.PetraPulse.util.validators.CompositeValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

import static com.petrapulse.PetraPulse.util.validators.CompositeValidator.hasValue;
import static java.lang.String.join;
import static java.util.Objects.nonNull;
@Service
public class UsersDetailsServiceImpl implements UsersDetailsService{
    private final UserDetailsJpaRepository userDetailsJpaRepository;
    //since you have only one constructor we don't have to add @Autowired but if there was multiple we should add it
    public UsersDetailsServiceImpl(UserDetailsJpaRepository userDetailsJpaRepository) {
        this.userDetailsJpaRepository = userDetailsJpaRepository;
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
                .addValidator(r -> hasValue(r.getCountry()), "Country Cannot Be Empty")
                .addValidator(r -> hasValue(r.getEmail()), "Email Cannot Be Empty")
                .addValidator(r -> nonNull(r.getDateOfBirth()), "Date Of Birth Cannot Be Empty")
                .addValidator(r -> nonNull(r.getPassword()), "Password Cannot Be Empty")
                .addValidator(r -> r.getEmail() == null || Pattern.compile("^(.+)@(.+)\\.(.+)$").matcher(r.getEmail()).matches(), "Enter a valid email")
                .addValidator(r -> r.getUsername() == null || !userDetailsJpaRepository.findByUsername(r.getUsername().toLowerCase()).isPresent(), "Username already exists")
                .addValidator(r -> Pattern.compile("^(?=.*[A-Z]).{8,20}$").matcher(r.getPassword()).matches(), "Password should be minimum 8 characters with 1 upper case letter")
                .validate(createDto);
        validate(violation);
    }
}
