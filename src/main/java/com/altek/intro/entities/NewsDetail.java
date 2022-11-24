package com.altek.intro.entities;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_NEWS_DETAIL")
public class NewsDetail extends AbstractEntity implements Serializable {
    @Column(name = "CONTENT", length = 1000)
    private String content;

    private News news;
    @OneToOne
    @JoinColumn(name = "NEWS_ID", nullable = false, unique = true)
    public News getNews() {
        return news;
    }
}