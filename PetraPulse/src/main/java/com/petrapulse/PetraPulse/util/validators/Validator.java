package com.petrapulse.PetraPulse.util.validators;

public interface Validator <P, R>{
    R validate(P input);
}
