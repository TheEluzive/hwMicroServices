package validators;

import com.example.producer.data.Payment;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
@AllArgsConstructor
public class CardNumberValidator implements ConstraintValidator<CardNumber, Long> {


    @Override
    public boolean isValid(Long number, ConstraintValidatorContext constraintValidatorContext) {
        return (number >= 0000_0000_0000_0001L) && (number < 1_0000_0000_0000_0000L);
    }


}
