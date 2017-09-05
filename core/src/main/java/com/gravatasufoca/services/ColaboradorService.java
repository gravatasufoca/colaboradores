package com.gravatasufoca.services;

import com.gravatasufoca.interfaces.Transacional;
import com.gravatasufoca.model.Colaborador;
import com.gravatasufoca.model.Compentencia;
import com.gravatasufoca.model.Contato;
import com.gravatasufoca.model.EntidadeBasica;
import com.gravatasufoca.repositorios.Repositorio;
import com.gravatasufoca.repositorios.RepositorioColaborador;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * criado por bruno em 30/08/17.
 */
public class ColaboradorService extends AbstractService<Colaborador> implements IService{

    @Inject
    private RepositorioColaborador repositorio;

    @Inject
    private TipoCompetenciaService tipoCompetenciaService;
    @Inject
    private ContatoService contatoService;
    @Inject
    private CompetenciaService competenciaService;

    public ColaboradorService() {
    }

    @Override
    protected Repositorio<Colaborador> getRepositorio() {
        return repositorio;
    }

    @Override
    @Transacional
    public Map<String, String> salvar(EntidadeBasica entidade) {
        Colaborador colaborador= (Colaborador) entidade;
        if(validarObrigatorios(colaborador)){
            insereTiposCompetencia(colaborador);
            inserirContatos(colaborador);
            inserirCompetencias(colaborador);
            inserir(colaborador);
        }
        return erros;
    }

    private void inserirCompetencias(Colaborador colaborador) {
        //TODO: EXCLUIR OS ANTIGOS ANTES - FAZER COM BULK DELETE
        if (colaborador.getCompetencias() != null) {
            for (Compentencia compentencia : colaborador.getCompetencias()) {
                competenciaService.inserir(compentencia);
            }
        }
    }

    private void inserirContatos(Colaborador colaborador) {
        if (colaborador.getContatos() != null) {

            for (Contato contato : colaborador.getContatos()) {
                contatoService.inserir(contato);
            }
        }

    }

    //TODO: mudar para bulk insertion
    private void insereTiposCompetencia(Colaborador colaborador) {
        if(colaborador.getCompetencias()!=null) {
            for (Compentencia competencia : colaborador.getCompetencias()) {
                if (competencia.getTipoCompetencia().getId() == null) {
                    tipoCompetenciaService.inserir(competencia.getTipoCompetencia());
                }
            }
        }
    }

    public List<Colaborador> consultar(String nome) {
        return repositorio.listarPorNome(nome);
    }
}
