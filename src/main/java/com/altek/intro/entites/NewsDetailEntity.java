package com.altek.intro.entites;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_DETAIL_NEWS")
public class NewsDetailEntity extends AbstractEntity implements Serializable {

    @Column(name = "CONTENT")
    private String content;

    //    @Column(name = "NEWS_ID")
//    private Long newsId;
    @OneToOne(fetch = FetchType.LAZY)
    @Column(name = "NEWS_ID")
    @MapsId
    private NewsEntity newsEntity;
}