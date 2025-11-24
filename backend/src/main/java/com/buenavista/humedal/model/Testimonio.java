package com.buenavista.humedal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Data
@Entity
@Getter
@Setter
@Table(name = "testimonio")
public class Testimonio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String autor;

    @Column(length = 1000)
    private String texto;

    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "humedal_id")
    private Humedal humedal;
}
