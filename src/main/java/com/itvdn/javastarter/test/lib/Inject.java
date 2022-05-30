package com.itvdn.javastarter.test.lib;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//над тим полем яке ми хочемо ініціалізувати
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}
