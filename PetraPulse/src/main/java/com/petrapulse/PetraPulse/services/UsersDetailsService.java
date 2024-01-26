package com.petrapulse.PetraPulse.services;

import com.petrapulse.PetraPulse.bo.RegisterRequest;

public interface UsersDetailsService {
    void createPreValidation(RegisterRequest createDto);
}
