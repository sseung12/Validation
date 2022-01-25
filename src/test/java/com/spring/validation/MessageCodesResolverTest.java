package com.spring.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.*;

public class MessageCodesResolverTest {

    // rejectValue 는 내부에서, 이러한 과정을 거침
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodeResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required","user");
        //규칙 우선순위 ( 복잡한게 우선시됌)
        // 1. errorcode + objectName
        // 2. errorcode
        assertThat(messageCodes).containsExactly("required.user","required");
    }
}
