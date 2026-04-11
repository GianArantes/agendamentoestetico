package br.com.agendamentoestetico.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.agendamentoestetico.models.Funcionario;
import br.com.agendamentoestetico.models.enums.UserRole;
import br.com.agendamentoestetico.repositories.FuncionarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PessoaService {

    private FuncionarioRepository funcionarioRepository;
    private EmailService emailService;

    public static String formataExibicaoCelular(String celular) {
        if (celular != null && celular.matches("\\d{11}")) {
            return String.format("(%s) %s-%s",
                    celular.substring(0, 2),
                    celular.substring(2, 7),
                    celular.substring(7));
        }
        return celular;
    }

    public static String limparCelular(String celular) {
        if (celular == null)
            return null;
        return celular.replaceAll("\\D", "");
    }

    /**
     * Valida uma senha de acordo com as regras de negócio.
     * 
     * @param senha A senha a ser validada.
     * @return Uma lista de strings com as mensagens de erro. A lista estará vazia
     *         se a senha for válida.
     */

    @SuppressWarnings("null")
    public List<String> validarSenha(String senha) {
        List<String> erros = new ArrayList<>();

        if (senha == null || senha.length() < 6) {
            erros.add("A senha deve ter pelo menos 6 caracteres.");
        }
        if (!senha.matches(".*[A-Z].*")) {
            erros.add("A senha deve conter pelo menos uma letra maiúscula.");
        }
        if (!senha.matches(".*[a-z].*")) {
            erros.add("A senha deve conter pelo menos uma letra minúscula.");
        }
        if (!senha.matches(".*[0-9].*")) {
            erros.add("A senha deve conter pelo menos um número.");
        }
        if (!senha.matches(".*[^a-zA-Z0-9].*")) {
            erros.add("A senha deve conter pelo menos um símbolo (ex: @, #, $, %).");
        }

        return erros;
    }

    public void solicitarTrocaSenha(String email) {
        var funcionario = funcionarioRepository.findByEmail(email);

        if (funcionario.isPresent()) {
            Funcionario f = funcionario.get();

            // Verifica Rate Limit (ex: só pode pedir a cada 2 min)
            if (f.getSenhaTokenExpiration() != null &&
                    f.getSenhaTokenExpiration().isAfter(LocalDateTime.now().plusMinutes(2))) {
                throw new RuntimeException("Aguarde alguns minutos para solicitar um novo e-mail.");
            }

            String token = UUID.randomUUID().toString();
            f.setResetSenhaToken(token);
            f.setSenhaTokenExpiration(LocalDateTime.now().plusMinutes(15)); // Expira em 15 min

            funcionarioRepository.save(f);
            emailService.enviarEmailRecuperacaoSenha(f.getNome(), email, token);
        }
    }

    public Funcionario validarToken(String token) {
        return funcionarioRepository.findByResetSenhaToken(token)
                .filter(f -> f.getSenhaTokenExpiration().isAfter(LocalDateTime.now()))
                .orElseThrow(() -> new RuntimeException("Token inválido ou expirado."));
    }

    public void atualizaSenha(Funcionario funcionario, String senhaNova, String confirmaSenha) {

        // 1. Verifica se a nova senha é igual a confirmação de nova senha
        if (!senhaNova.equals(confirmaSenha)) {
            throw new RuntimeException("A nova senha e a confirmação de senha não confere.");
        }
        // 2. Verifica se a nova senha é uma senha válida
        var erros = validarSenha(senhaNova);
        if (!erros.isEmpty()) {
            throw new RuntimeException(String.join("\n", erros));
        }
        // 3. Atualiza a senha
        funcionario.setSenha(new BCryptPasswordEncoder().encode(senhaNova));
        funcionarioRepository.save(funcionario);

    }

    public boolean validaPermissao(Funcionario moderador, Funcionario funcionario) {
        if (moderador.getRole() == UserRole.ROLE_GERENTE && funcionario.getRole() == UserRole.ROLE_GERENTE) {
            return false;
        }
        if (moderador.getRole() == UserRole.ROLE_GERENTE && funcionario.getRole() == UserRole.ROLE_ADMIN) {
            return false;
        }
        return true;
    }

}
