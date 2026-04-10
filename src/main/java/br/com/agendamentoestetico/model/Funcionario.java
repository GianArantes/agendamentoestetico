package br.com.agendamentoestetico.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "funcionario")
public class Funcionario extends Pessoa {

    private String cargo;
    private String senha;

    // Relação com as configurações de horário
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<AgendaTrabalho> agendasTrabalhos;

    @Override
    public String toString() {
        return "Funcionario [Id: " + getId() + 
        ", Nome: " + getNome() + 
        ", E-mail: " + getEmail() + 
        ", Telefone: " + getTelefone() + 
        ", Cargo: " + getCargo() + 
        ", Status: " + getStatus() + "]";
    }

    
}
