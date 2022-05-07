package com.yejipractice.bookstudy.config.auth.dto;

import com.yejipractice.bookstudy.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
// SessionUser에는 인증된 사용자 정보만 필요하기 때문에 name, email, picture만 필드로 선언
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
