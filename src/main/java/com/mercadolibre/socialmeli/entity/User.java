package com.mercadolibre.socialmeli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @Column(name = "user_name")
    private String  userName;

    @Column(name = "user_type")
    private String userType;

    public User(Date createAt, String userName, String userType) {
        this.createAt = createAt;
        this.userName = userName;
        this.userType = userType;
    }

    public User(User user) {
        this.userId = user.getUserId();
        this.createAt = user.getCreateAt();
        this.userName = user.getUserName();
        this.userType = user.getUserType();
    }

    public User(Integer userId,String userName){
        this.userId = userId;
        this.userName = userName;
    }

}
