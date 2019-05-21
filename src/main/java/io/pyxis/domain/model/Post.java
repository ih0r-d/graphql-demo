package io.pyxis.domain.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "POSTS")
@Data
public class Post extends BaseEntity{

    @Size(max = 100)
    @Column(nullable = false, unique = true)
    private String title;

    @Size(max = 250)
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Lob
    private String content;
}