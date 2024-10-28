package com.example.account.service;

import com.example.account.exception.AccountException;
import com.example.account.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class LockService {
    private final RedissonClient redissonClient;

    public void lock(String accountNumber) {
        RLock lock = redissonClient.getLock(getLockKey(accountNumber));
        log.debug("Trying lock for accountNumber : {}", accountNumber);

        try {
            boolean isLock = lock.tryLock(1, 15, TimeUnit.SECONDS);
            if (!isLock) {
                log.error("=========Lock acquisition failed=========");
                throw new AccountException(ErrorCode.ACCOUNT_TRANSACTION_LOCK);
            }
        } catch (AccountException e) {
            throw e;
        } catch (Exception e) {
            log.error("Redis lock failed", e);
        }
    }

    public void unlock(String accountNumber) {
        log.debug("Unlock for accountNumber : {} ", accountNumber);
        redissonClient.getLock(getLockKey(accountNumber)).unlock();
    }

    private String getLockKey(String accountNumber) {
        return "ACLK: " + accountNumber;
    }
}
// 레디스 클라이언트에 sampleLock이라는 lock 변수를 가져오고
// 이 lock으로 spinLock 시도 => try, catch문
// 1초 동안 기다리면서 락을 시도해보고 lock 획득에 실패하면 if문으로 들어감
// lock 획득에 성공하면 최대 5초 시간 후에 자동으로 풀어주게(lease) 됨
// 명시적으로 unlock을 하고 있지 않기 때문에 다른 녀석이 lock을 획득하려고 하면 5초 동안은 실패가 뜨게 될 것임

