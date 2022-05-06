package com.yejipractice.bookstudy.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // increment
    private Long id;

    // 문자열 경우 VARCHAR(225)가 기본
    @Column(length = 500, nullable = false) // VARCHAR(500)으로 변경
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // TEXT로 변경
    private String content;

    private String author;

    @Builder // 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
