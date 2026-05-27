package br.com.serratec.trabfinal_api.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OS {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
