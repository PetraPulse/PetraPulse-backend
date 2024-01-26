package com.petrapulse.PetraPulse.util.validators;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
                                                        //Making the result type a list  allows the validator to handle multiple validation violations for a given input.
public class PredicateValidator<P, R> implements Validator<P, List<R>> {
    // a boolean function.it represents a condition that is either satisfied (true) or not satisfied (false).
    private final Predicate<P> inputPredicate;
    //a functional interface in Java, and its single abstract method is: apply(P t) which takes an argument of type P and returns a result of type R (generics).
    //It's used when you want to represent a function that transforms an input of type P into a result of type R.
    private final Function<P, R> function;

    public PredicateValidator(Predicate<P> inputPredicate, R violation) {
        this.inputPredicate = inputPredicate;
        this.function = p -> violation;
    }
    //In the validate method, it checks whether the inputPredicate (Predicate function) is satisfied or not.
    //If the condition specified by inputPredicate is not satisfied (!inputPredicate.test(input) is true), it applies the function to the input using function.apply(input) -> (  this.function = p -> violation; which result in violation).
    //The result is then wrapped in a singleton list (singletonList) and returned. This list will contain the violation result.
    //If the condition is satisfied, an empty list (emptyList()) is returned, indicating that there is no violation.
    @Override
    public List<R> validate(P input) {
        return inputPredicate.test(input) ?
                  emptyList(): singletonList(function.apply(input));
    }

    /* The singletonList method, creates a list that contains only a single specified element. The resulting list is immutable,
     and its size is fixed at 1. Any attempt to add or remove elements from the list will result in an UnsupportedOperationException.
     */
}