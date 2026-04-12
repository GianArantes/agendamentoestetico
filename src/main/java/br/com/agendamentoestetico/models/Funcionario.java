package br.com.agendamentoestetico.models;


import java.time.LocalDateTime;
import java.util.List;
import br.com.agendamentoestetico.models.enums.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity (name = "funcionario")
@EqualsAndHashCode(callSuper = true)
public class Funcionario extends Pessoa {

    private String senha;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String resetSenhaToken;
    private LocalDateTime senhaTokenExpiration;


    // Relação com as configurações de horário
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<AgendaTrabalho> agendasTrabalhos;

    @Override
    public String toString() {
        return "Funcionario [Id: " + getId() + 
        ", Nome: " + getNome() + 
        ", E-mail: " + getEmail() + 
        ", Celular: " + getCelular() + 
        ", Role: " + getRole() + 
        ", Status: " + getStatus() + "]";
    }

    
}
