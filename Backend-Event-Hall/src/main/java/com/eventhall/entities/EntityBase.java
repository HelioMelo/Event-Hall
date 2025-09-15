package com.eventhall.entities;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EntityBase {
	
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(columnDefinition = "uuid", updatable = false, nullable = false, name = "id")
    @JsonProperty
    private UUID id;
	
	@Column(name = "created_at", updatable = false)
	@JsonProperty
	@CreationTimestamp
	private Instant created_at;

    @Column(name = "updated_at")
    @JsonProperty
    @UpdateTimestamp
    private Instant updated_at;
	
	@Column(name = "is_active")
	@JsonProperty
	private Boolean isActive = true;
	
	
	public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }


	@Override
	public String toString() {
		return "EntityBase [isActive=" + isActive + "]";
	}


	
	 
}