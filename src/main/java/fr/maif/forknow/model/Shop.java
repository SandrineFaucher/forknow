package fr.maif.forknow.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Shop {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    private String type;

    private String imageUrl; 

    @Column(name = "creationDate", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "user_id") 
    private User user;

    @OneToMany
    final Collection<Food> food = new ArrayList<Food>();

    private String generateImageUrl(Long id) {
        // Génère une URL d'image dynamique avec l'API Placeholder
        return "https://via.placeholder.com/300x200?text=Shop+" + id;
    }
}
