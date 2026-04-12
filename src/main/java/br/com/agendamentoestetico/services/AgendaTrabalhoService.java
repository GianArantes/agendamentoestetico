package br.com.agendamentoestetico.services;

import org.springframework.stereotype.Service;

import br.com.agendamentoestetico.models.AgendaTrabalho;

@Service
public class AgendaTrabalhoService {

    public boolean validaAgendaTrabalho(AgendaTrabalho agendaTrabalho) {
        if (agendaTrabalho.getAgendaInicio().isBefore(agendaTrabalho.getAgendaFim()) && agendaTrabalho.getAlmocoInicio().isBefore(agendaTrabalho.getAlmocoFim())) {
            return true;
        }
        return false;
    }

}
