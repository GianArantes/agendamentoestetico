package br.com.agendamentoestetico.models;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Funcionario extends Pessoa implements UserDetails{

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorna a autoridade baseada no campo role
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return getEmail(); // O Spring usa o termo 'username' para a credencial de login
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Se não tiver lógica de expiração, retorne true
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
