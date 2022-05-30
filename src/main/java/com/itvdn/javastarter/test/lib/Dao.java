package com.itvdn.javastarter.test.lib;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Sergey Klunniy
 */
// над класом ставилься який ми хочемо заїнжектити
@Retention(RetentionPolicy.RUNTIME)
public @interface Dao {
}
