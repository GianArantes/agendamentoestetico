package br.com.agendamentoestetico.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.agendamentoestetico.dtos.AtualizaSenhaDTO;
import br.com.agendamentoestetico.dtos.AuthDTO;
import br.com.agendamentoestetico.dtos.FuncionarioDTO;
import br.com.agendamentoestetico.dtos.FuncionarioListagemDTO;
import br.com.agendamentoestetico.dtos.FuncionarioModeradoDTO;
import br.com.agendamentoestetico.dtos.FuncionarioResumoDTO;
import br.com.agendamentoestetico.dtos.LoginResponseDTO;
import br.com.agendamentoestetico.dtos.RecuperaSenhaDTO;
import br.com.agendamentoestetico.models.Funcionario;
import br.com.agendamentoestetico.models.enums.Status;
import br.com.agendamentoestetico.models.enums.UserRole;
import br.com.agendamentoestetico.repositories.FuncionarioRepository;
import br.com.agendamentoestetico.security.TokenService;
import br.com.agendamentoestetico.services.PessoaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private FuncionarioRepository funcionarioRepository;
    private TokenService tokenService;
    private PessoaService pessoaService;
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthDTO authDTO) {

        // 1. Cria o objeto de autenticação com e-mail e senha
        var loginSenha = new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.senha());

        // 2. Autentica (o Spring Security vai chamar o seu UserDetailsService aqui)
        var auth = this.authenticationManager.authenticate(loginSenha);

        // 3. Pega o usuário autenticado e gera o token
        var funcionario = (Funcionario) auth.getPrincipal();
        var token = tokenService.gerarToken(funcionario);

        // 4. Retorna o token no corpo da resposta como JSON
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        // Limpa o contexto de segurança do Spring no servidor para a requisição atual
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/registro")
    public ResponseEntity<Funcionario> registrar(@RequestBody @Valid FuncionarioDTO dto) {

        // Verifica se não há outro funcionário com esse mesmo e-mail
        if (funcionarioRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("Este e-mail já está cadastrado no sistema.");
        }

        // verifica se é uma senha válida (consultar maiores detalhes na classe
        // PessoaService)
        var errosSenha = pessoaService.validarSenha(dto.senha());
        if (!errosSenha.isEmpty()) {
            throw new RuntimeException(String.join("\n", errosSenha));
        }

        // cria o funcionário
        Funcionario funcionario = new Funcionario();
        funcionario.setEmail(dto.email());
        funcionario.setSenha(passwordEncoder.encode(dto.senha())); // criptografa a senha
        funcionario.setNome(dto.nome());
        funcionario.setCelular(dto.celular());
        funcionario.setStatus(Status.ATIVO);
        funcionario.setRole(UserRole.ROLE_FUNCIONARIO);
        funcionarioRepository.save(funcionario);
        return ResponseEntity.status(201).body(funcionario);
    }

    @PostMapping("/altera-senha")
    public ResponseEntity<FuncionarioResumoDTO> alterarSenha(@RequestBody @Valid AtualizaSenhaDTO dto) {
        // 1. Recupera o email logado pelo Token
        var email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Funcionario funcionario = funcionarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Erro ao obter o usuário logado pelo email"));

        // 2. Verifica se a senha antiga é a correta

        if (!passwordEncoder.matches(dto.senhaAntiga(), funcionario.getSenha())) {
            throw new RuntimeException("A senha antiga não confere com a senha registrada.");
        }

        // 3. Chama o método de atualização de senha no Pessoa Service.
        pessoaService.atualizaSenha(funcionario, dto.novaSenha(), dto.confirmaNovaSenha());

        var resumo = new FuncionarioResumoDTO(funcionario.getId(), funcionario.getNome(), funcionario.getEmail());
        return ResponseEntity.ok(resumo);
    }

    @PostMapping("/solicitar-recuperacao")
    public ResponseEntity<String> solicitarRecuperacao(@RequestBody String email) {

        // Chama o método no service para trocar a senha
        pessoaService.solicitarTrocaSenha(email);

        // Por segurança, sempre retornamos "OK", mesmo que o e-mail não exista
        // Isso evita que hackers "pesquem" e-mails válidos na sua base.
        return ResponseEntity.ok("Se o e-mail existir em nossa base, um link de recuperação será enviado.");
    }

    @PostMapping("/recuperar-senha")
    public ResponseEntity<String> processarRecuperacao(@RequestParam String token, @RequestBody RecuperaSenhaDTO dto) {

        // 1. Valida se o token existe e não expirou
        var funcionario = pessoaService.validarToken(token);

        // 2. Atualiza a senha do usuário associado ao token
        pessoaService.atualizaSenha(funcionario, dto.novaSenha(), dto.confirmaNovaSenha());

        return ResponseEntity.ok("Senha alterada com sucesso!");
    }

    @GetMapping("/listar-funcionarios")
    public ResponseEntity<List<FuncionarioListagemDTO>> listarFuncionarios() {
        return ResponseEntity.ok(funcionarioRepository.findAll().stream()
                .map(FuncionarioListagemDTO::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> listarFuncionarios(@PathVariable Long id) {
        return ResponseEntity.ok(funcionarioRepository.findById(id).orElseThrow());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFuncionario(@PathVariable Long id) {
        funcionarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id,
            @RequestBody @Valid FuncionarioModeradoDTO dto) {

        var email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Funcionario moderador = funcionarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Erro ao obter o usuário logado"));

        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow();

        if (pessoaService.validaPermissao(moderador, funcionario)) {
            funcionario.setEmail(dto.email());
            funcionario.setNome(dto.nome());
            funcionario.setCelular(dto.celular());
            funcionario.setRole(dto.role());
            funcionario.setStatus(dto.status());
            List<String> errosSenha = pessoaService.validarSenha(dto.novaSenha());
            if (errosSenha != null) {
                funcionario.setSenha(new BCryptPasswordEncoder().encode(dto.novaSenha()));
            }
            funcionarioRepository.save(funcionario);
            return ResponseEntity.ok(funcionario);
        }
        throw new RuntimeException("Você não tem permissão para modificar este funcionário.");

    }

}
