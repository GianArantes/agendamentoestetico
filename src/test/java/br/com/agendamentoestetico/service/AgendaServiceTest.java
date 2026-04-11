package br.com.agendamentoestetico.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.agendamentoestetico.models.*;
import br.com.agendamentoestetico.models.enums.DiaDaSemana;
import br.com.agendamentoestetico.repositories.*;
import br.com.agendamentoestetico.services.AgendaService;

import java.util.List;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class AgendaServiceTest {
    @Mock
    private AgendamentoRepository agendamentoRepository;
    @Mock
    private AgendaTrabalhoRepository agendaTrabalhoRepository;

    @InjectMocks
    private AgendaService agendaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveListarHorariosDisponiveis() {
        LocalDate data = LocalDate.of(2026, 4, 10);
        Long funcionarioId = 1L;
        Procedimento procedimento = new Procedimento();
        procedimento.setDuracao(Duration.ofMinutes(30));

        AgendaTrabalho agenda = new AgendaTrabalho();
        agenda.setAgendaInicio(LocalTime.of(8, 0));
        agenda.setAgendaFim(LocalTime.of(12, 0));
        agenda.setAlmocoInicio(LocalTime.of(11, 30));
        agenda.setAlmocoFim(LocalTime.of(12, 0));
        agenda.setDiaDaSemana(DiaDaSemana.SEXTA);
        when(agendaTrabalhoRepository.findByFuncionarioIdAndDiaDaSemana(eq(funcionarioId), any())).thenReturn(Optional.of(agenda));
        
        LocalDateTime inicioDia = data.atStartOfDay();
        LocalDateTime fimDia = data.atTime(LocalTime.MAX);
        when(agendamentoRepository.findAllByFuncionarioIdAndInicioProcedimentoBetween(funcionarioId, inicioDia, fimDia)).thenReturn(Collections.emptyList());

        List<LocalTime> horarios = agendaService.listarHorariosDisponiveis(data, funcionarioId, procedimento);
        assertFalse(horarios.isEmpty());
    }

    @Test
    void deveLancarExcecaoQuandoFuncionarioNaoTrabalhaNoDia() {
        LocalDate data = LocalDate.of(2026, 4, 10);
        Long funcionarioId = 1L;
        Procedimento procedimento = new Procedimento();
        procedimento.setDuracao(Duration.ofMinutes(30));
        when(agendaTrabalhoRepository.findByFuncionarioIdAndDiaDaSemana(eq(funcionarioId), any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> agendaService.listarHorariosDisponiveis(data, funcionarioId, procedimento));
    }
}
