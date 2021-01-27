package com.hwangwongyu.news.article;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleDTO {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private String title;
    private String content;
    private Integer sectionId;
    private Integer companyId;
    private Long editorId;
    private Long viewCount;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime modificateTime;

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", title='" + title + '\'' +

                ", sectionId=" + sectionId +
                ", companyId=" + companyId +
                ", editorId=" + editorId +
                ", viewCount=" + viewCount +
                ", modificateTime=" + modificateTime +
                '}';
    }
}
