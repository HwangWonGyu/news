package com.hwangwongyu.news.article;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleDTO {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @Length(min = 4, max = 100, message = "기사 제목은 {min} ~ {max}자로 작성해야 합니다.")
    @NotBlank(message = "기사 제목을 입력해주세요.")
    private String title;
    @Length(min = 10, max = 10000, message = "기사 내용은 {min} ~ {max}자로 작성해야 합니다.")
    @NotBlank(message = "기사 내용을 입력해주세요.")
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
