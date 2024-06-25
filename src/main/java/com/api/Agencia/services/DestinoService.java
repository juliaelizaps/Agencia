package com.api.Agencia.services;

import com.api.Agencia.entities.Destino;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DestinoService {

    private List<Destino> destinos;

    public DestinoService(){
        destinos = new ArrayList<>();
    }

    public void salvar(Destino destino) {
        destinos.add(destino);
        if (destino.getId() == null) {
            destino.setId((long) (destinos.size() + 1));
        }
        destinos.add(destino);
    }

    public List<Destino> listar() {
        return destinos;
    }

    public List<Destino> buscarPorNomeOuLocalidade(String query) {
        return destinos.stream()
                .filter(destino -> destino.getNome().equalsIgnoreCase(query) || destino.getLocalidade().equalsIgnoreCase(query))
                .collect(Collectors.toList());
    }

    public Destino buscarPorId(Long id) {
        Optional<Destino> destino = destinos.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
        return destino.orElse(null);
    }

    public boolean avaliarDestino(Long id, double nota) {
        Destino destino = buscarPorId(id);
        if (destino != null) {
            double totalNota = destino.getNota() * destino.getContaNota();
            destino.setContaNota(destino.getContaNota() + 1);
            destino.setNota((totalNota + nota) / destino.getContaNota());
            return true;
        } else {
            return false;
        }
    }

    public boolean excluir(Long id) {
        return destinos.removeIf(d -> d.getId().equals(id));
    }
}

