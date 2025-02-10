package com.example.kdh.common.util;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class UserAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        // TODO. 추후 JWT 인증된 사용자 정보를 가져오도록 수정
        return Optional.of(1L);
    }

}
