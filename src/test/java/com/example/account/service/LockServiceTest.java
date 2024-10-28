package com.example.account.service;

import com.example.account.exception.AccountException;
import com.example.account.type.ErrorCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LockServiceTest {
    @Mock
    private RedissonClient redissonClient;

    @Mock
    private RLock rLock;
    // private Rlock rLock; => 우리가 만든 Bean은 아니지만 LockService에서 Rlock lcok이 어떻게 동작하는지에 따라
    // 우리 로직이 변하기 때문에 Rlock을 모킹해서 Rlock의 동작을 우리가 원하는대로 바꿔주도록 하기 위함

    @InjectMocks
    private LockService lockService;

    // lock 취득 성공
    @Test
    void successGetLock() throws InterruptedException {
        //given
        given(redissonClient.getLock(anyString()))
                .willReturn(rLock);
        given(rLock.tryLock(anyLong(), anyLong(), any()))
                .willReturn(true);

        //when
        //then
        assertDoesNotThrow(() -> lockService.lock("123"));
    }

    // lock 취득 실패
    @Test
    void failGetLock() throws InterruptedException {
        //given
        given(redissonClient.getLock(anyString()))
                .willReturn(rLock);
        given(rLock.tryLock(anyLong(), anyLong(), any()))
                .willReturn(false);

        //when
        AccountException exception = assertThrows(AccountException.class,
                () -> lockService.lock("123"));

        //then
        assertEquals(ErrorCode.ACCOUNT_TRANSACTION_LOCK, exception.getErrorCode());
    }
}