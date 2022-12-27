package demo.spring.security.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "role")
@Getter
public class RoleEntity {
    @Id
    private Long id;

    private String name;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private List<PermissionEntity> permissionEntity;
}
