package br.com.agendamentoestetico.models;

import java.time.LocalTime;

import br.com.agendamentoestetico.dtos.AgendaTrabalhoDTOResponse;
import br.com.agendamentoestetico.models.enums.DiaDaSemana;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity (name = "agenda_trabalho")
public class AgendaTrabalho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private DiaDaSemana diaDaSemana;

    private LocalTime agendaInicio; // Ex: 08:00
    private LocalTime agendaFim;   // Ex: 18:00

    private LocalTime almocoInicio; // Ex: 12:00
    private LocalTime almocoFim;   // Ex: 13:00

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public AgendaTrabalhoDTOResponse toDTO(){
       return new AgendaTrabalhoDTOResponse(getId(), getDiaDaSemana(), getAgendaInicio(), getAgendaFim(), getAlmocoInicio(), getAlmocoFim(), getFuncionario().getId());
    }

    
}
