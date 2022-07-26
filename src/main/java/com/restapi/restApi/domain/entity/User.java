package com.restapi.restApi.domain.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    // OAuth2.0 이용하는 경우 대비해서 nullable = false로 두지 않는다
    @Column(length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(length = 100)
    private String provider;

    /*@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Builder.Default
    private List<RoleType> roles = new ArrayList<>();*/

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    @Builder.Default
    private List<Role> roles = new ArrayList<>();

    public static User createUser(String email, String password, String name, String nickname) {
        User user = new User();
        user.email = email;
        user.name = name;
        user.password = password;
        user.nickname = nickname;
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole().name())).collect(Collectors.toList());

        /*return this.roles
                .stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());*/
    }

    @Override
    public String getPassword() {
        return password;
    }

    // pk 반환
    @Override
    public String getUsername() {
        return String.valueOf(id);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
