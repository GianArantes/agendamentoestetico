package br.com.agendamentoestetico.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity (name = "cliente")
public class Cliente extends Pessoa {

    private LocalDate dataNascimento;


    @Override
    public String toString() {
        return "Cliente [Id: " + getId() + 
        ", Nome: " + getNome() + 
        ", E-mail: " + getEmail() + 
        ", Telefone: " + getTelefone() + 
        ", Data de Nascimento: " + getDataNascimento() + 
        ", Status: " + getStatus() + "]";
    }


}
