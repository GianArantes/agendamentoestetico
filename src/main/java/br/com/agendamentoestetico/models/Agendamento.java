package br.com.agendamentoestetico.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Data
@Entity (name = "agendamento")
public class Agendamento {

    @Id
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @OneToOne
    @JoinColumn(name = "procedimento_id")
    private Procedimento procedimento;
    private LocalDateTime inicioProcedimento;
    private LocalDateTime fimProcedimento; //calcular o fim do agendamento baseado na duração do procedimento


}
