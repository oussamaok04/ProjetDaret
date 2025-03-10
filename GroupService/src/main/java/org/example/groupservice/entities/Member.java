package org.example.groupservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.groupservice.feign.UserApp;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String role;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userId;

    @Transient
    private UserApp userApp;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "groupId")
    private Group group;
}
