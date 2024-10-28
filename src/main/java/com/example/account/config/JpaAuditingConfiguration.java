package com.example.account.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration // 컴포넌트 일종 자동으로 Bean 등록
@EnableJpaAuditing
public class JpaAuditingConfiguration {
    // JpaAuditingConfiguration 클래스 자체가 어플리케이션 작동 시,
    // 엔티티 객체가 생성이 되거나 변경이 되었을 때 @EnableJpaAuditing 어노테이션을 활용해서 자동으로 값을 등록할 수 있음
}
