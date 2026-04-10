package br.com.agendamentoestetico.model;

import java.time.LocalTime;

import br.com.agendamentoestetico.model.enums.DiaDaSemana;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity (name = "agenda_trabalho")
public class AgendaTrabalho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private DiaDaSemana diaDaSemana;

    private LocalTime agendaInicio; // Ex: 08:00
    private LocalTime agendaFim;   // Ex: 18:00

    private LocalTime almocoInicio; // Ex: 12:00
    private LocalTime almocoFim;   // Ex: 13:00

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    
}
