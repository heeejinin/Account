package com.example.account.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AccountLock {
    // 해당 시간 동안 기다려보겠다
    long tryLockTime() default 5000L;
}
