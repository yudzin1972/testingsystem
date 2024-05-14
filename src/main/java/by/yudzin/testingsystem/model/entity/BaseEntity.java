package by.yudzin.testingsystem.model.entity;

import by.yudzin.testingsystem.enums.Statuses;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Column(name = "created")
    @CreationTimestamp
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime created;

    @Column(name = "updated")
    @UpdateTimestamp
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime updated;

    @Column(name = "status",length=10)
    @Enumerated(EnumType.STRING)
    private Statuses status;
}