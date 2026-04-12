package org.fluxy.spring.annotation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;

@Component
@Indexed
public @interface Task {
    String name();
    String description();
    int version();
}
