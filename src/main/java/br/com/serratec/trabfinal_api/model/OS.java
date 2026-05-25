package br.com.serratec.trabfinal_api.model;

import jakarta.annotation.Generated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class OS {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
