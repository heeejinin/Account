package com.example.account.controller;

import com.example.account.service.LockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisTestController {
    private final LockService lockService;

//    @GetMapping("/get-lock")
//    public String getLock() {
//        return lockService.lock(request);
//    }
}
// 간단하게 로컬에 임베디드 레디스를 등록하고
// 자동으로 어플리케이션이 뜰 때, 빈을 등록하면서
// 자동으로 레디스를 start 시켜주고 어플리케이션이 꺼질 때 레디스가 꺼지도록 설정함
// 레디스 리파지토리 컨피그
// 레디스 리파지토리 컨피그에서 레디스 클라이언트가 해당 레디스가 접속하도록
// 접근할 수 있는 클라이언트를 만들어주도록 설정함
// 레디스 테스트 서비스에 레디스 클라이언트를 활용해서 spinlock을 실행하도록 설정함
