package validators;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@AllArgsConstructor
public class PaymentValueValidator implements ConstraintValidator<PaymentValue, Long> {


    @Override
    public boolean isValid(Long sum, ConstraintValidatorContext constraintValidatorContext) {
        return (sum > 0);
    }


}
