package br.com.agendamentoestetico.services;

import org.springframework.stereotype.Service;

import br.com.agendamentoestetico.models.AgendaBloqueio;

@Service
public class AgendaBloqueioService {

    public boolean valida(AgendaBloqueio agendaBloqueio){
        if(!agendaBloqueio.getInicioBloqueio().isBefore(agendaBloqueio.getFimBloqueio())){
            return false;
        }
        return true;
    }

}
