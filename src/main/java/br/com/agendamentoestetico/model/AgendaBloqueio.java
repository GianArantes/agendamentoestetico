package br.com.agendamentoestetico.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "agenda_bloqueio")
public class AgendaBloqueio {

    @Id
    @GeneratedValue
    private long id;
    private LocalDateTime inicioBloqueio;
    private LocalDateTime fimBloqueio;
    private String motivo;
    
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

}