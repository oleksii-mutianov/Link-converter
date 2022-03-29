package com.trendyol.linkconverter.validation;

import com.trendyol.linkconverter.constants.ErrorMessage;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for validating Trendyol web link.
 */
@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "^(http|https)://www\\.trendyol\\.co(m$|m/$|m/.+$)", message = ErrorMessage.INVALID_WEBLINK)
public @interface ValidTrendyolWeblink {

    /**
     * Exception message, if the constraint is violated
     */
    String message() default ErrorMessage.INVALID_WEBLINK;

    /**
     * Needed for all constraint annotations
     */
    Class<?>[] groups() default {};

    /**
     * Needed for all constraint annotations
     */
    Class<? extends Payload>[] payload() default {};

}
