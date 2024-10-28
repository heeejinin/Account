package com.example.account.service;

import com.example.account.aop.AccountLockIdInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LockAopAspect {
    private final LockService lockService;

    @Around("@annotation(com.example.account.aop.AccountLock) && args(request)")
    public Object aroundMethod(
            ProceedingJoinPoint pjp,
            AccountLockIdInterface request // UseBalance request와 CancelBalance request의 형태가 다르므로 AccountLockIdInterface 인터페이스를 이용
    ) throws Throwable {
        // lock 취득 시도
        lockService.lock(request.getAccountNumber());
        try {
            //before
            return pjp.proceed();
            //after
        } finally {
            // lock 해제
            lockService.unlock(request.getAccountNumber());
        }
    }
}
// Aspect J 문법으로 어떤 경우에 적용할 것인지 정의
// @Around는 실제 동작 before, after를 모두 둘러싸면서 우리가 원하는 동작을 넣어줄 수 있음

