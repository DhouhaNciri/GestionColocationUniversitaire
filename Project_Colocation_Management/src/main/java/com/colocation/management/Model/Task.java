package com.colocation.management.Model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private boolean completed;

    @ManyToOne
    private User assignedTo;
    @ManyToOne
    @JoinColumn(name = "created_by_user_id", nullable = false) 
    private User createdBy;


	public Task(String description, User assignedTo, User createdBy) {
		super();
		this.description = description;
		this.assignedTo = assignedTo;
		this.createdBy = createdBy;
	}

}
