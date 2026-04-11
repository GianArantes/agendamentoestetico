package br.com.agendamentoestetico.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "cliente")
public class Cliente extends Pessoa {

    private LocalDate dataNascimento;
    private String agendamentoToken;
    private LocalDateTime agendamentoTokenExpiration;

    @Override
    public String toString() {
        return "Cliente [Id: " + getId() +
                ", Nome: " + getNome() +
                ", E-mail: " + getEmail() +
                ", Celular: " + getCelular() +
                ", Data de Nascimento: " + getDataNascimento() +
                ", Status: " + getStatus() + "]";
    }

}
