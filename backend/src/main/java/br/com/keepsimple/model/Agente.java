package br.com.keepsimple.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_agente")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Agente {

    @Id
    private Long codigo;

    @Column(nullable = false)
    private LocalDateTime data;
}