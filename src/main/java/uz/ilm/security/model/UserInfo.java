package uz.ilm.security.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    private Roles roles;


}
