package demo.spring.security.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "permission")
@Getter
public class PermissionEntity {
    @Id
    private Long id;

    private String name;

    private String label;
}
