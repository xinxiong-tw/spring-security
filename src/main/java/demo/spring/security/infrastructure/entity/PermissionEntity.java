package demo.spring.security.infrastructure.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
@Getter
public class PermissionEntity {
    @Id
    private Long id;

    private String name;

    private String lable;
}
