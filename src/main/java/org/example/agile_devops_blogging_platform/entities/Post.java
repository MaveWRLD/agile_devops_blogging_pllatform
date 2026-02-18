package org.example.agile_devops_blogging_platform.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false, length = Integer.MAX_VALUE)
    private String title;

    @Column(name = "body", length = Integer.MAX_VALUE)
    private String body;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("now()")
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "excerpt", length = 500)
    private String excerpt;

    @Column(name = "published_at")
    private Instant publishedAt;
    @ColumnDefault("0")
    @Column(name = "like_count")
    private int likeCount;

    @ColumnDefault("0")
    @Column(name = "view_count")
    private int viewCount;
    @ColumnDefault("0")
    @Column(name = "comment_count")
    private int commentCount;

    @Transient
    private double trendingScore;
}