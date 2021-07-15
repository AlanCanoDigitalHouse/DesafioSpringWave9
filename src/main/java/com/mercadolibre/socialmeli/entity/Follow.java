package com.mercadolibre.socialmeli.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercadolibre.socialmeli.converter.FollowRequestToFollowConverter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "follow" ,uniqueConstraints=
@UniqueConstraint(columnNames={"user_id", "user_id_to_follow"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Follow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Integer followId;

    @NotNull
    @Column(name = "user_id")
    private Integer userId;

    @NotNull
    @Column(name = "user_id_to_follow")
    private Integer userIdToFollow;

    @Column(name = "follow_status")
    private Boolean status;

    public Follow(Integer userId, Integer userIdToFollow, Boolean status) {
        this.userId = userId;
        this.userIdToFollow = userIdToFollow;
        this.status = status;
    }

    public Follow(Follow follow) {
        this.followId = follow.getFollowId();
        this.userId = follow.getFollowId();
        this.userIdToFollow = follow.getUserIdToFollow();
        this.status = follow.getStatus();
    }

}
