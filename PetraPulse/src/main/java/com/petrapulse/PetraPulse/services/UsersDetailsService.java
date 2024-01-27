package com.petrapulse.PetraPulse.services;

import com.petrapulse.PetraPulse.bo.AuthenticationRequest;
import com.petrapulse.PetraPulse.bo.RegisterRequest;
import com.petrapulse.PetraPulse.repositories.UserDetailsJpaRepository;

public interface UsersDetailsService {
    void createPreValidation(RegisterRequest createDto);
    void createPostValidation(AuthenticationRequest userInfo);
}
