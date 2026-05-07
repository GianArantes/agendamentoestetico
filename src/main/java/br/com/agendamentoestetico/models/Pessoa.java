package br.com.agendamentoestetico.models;

import br.com.agendamentoestetico.models.enums.Status;
import br.com.agendamentoestetico.services.PessoaService;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@MappedSuperclass // Define que não terá tabela própria, apenas cede campos às filhas
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @Column(unique = true)
    private String celular;
    @Column(unique = true)
    private String email;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    
    public void setCelular(String celular){
        this.celular = PessoaService.limparCelular(celular);
    }


}
