package com.gravatasufoca.repositorios;

import org.hibernate.criterion.Order;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;


/**
 * Interface que define um repositrio de entidades.
 *
 * @author bruno.canto
 *
 * @param <E>
 *            Tipo da entidade.
 */
@Transactional()
public interface Repositorio<E> {

	/**
	 * Obtm do repositrio a entidade com o id dado.
	 *
	 * @param id
	 *            id para a busca.
	 * @return entidade encontrada.
	 */
	E obterPorId(Serializable id);

	/**
	 * Lista todas as entidades armazenadas no repositrio.
	 *
	 * @return entidades armazenadas.
	 */
	List<E> listar();

	/**
	 * Lista todas as entidades armazenadas no repositrio ordenada pelo campo do parametro.
	 *
	 * @return entidades armazenadas.
	 */
	List<E> listar(Order order);


	/**
	 * Lista todas as entidades armazenadas no repositrio filtradas pelo termo.
	 *
	 * @return entidades armazenadas.
	 */
	List<E> autocomplete(String campo, String termo);

	/**
	 * Insere uma entidade no repositrio.
	 *
	 * @param entidade
	 *            entidade para inserir.
	 */
	void inserir(E entidade);

	/**
	 * Atualiza uma entidade j armazenada.
	 *
	 * @param entidade
	 *            entidade para atualizar.
	 */
	E atualizar(E entidade);

	/**
	 * Remove uma entidade do repositrio
	 *
	 * @param entidade
	 *            para remover.
	 */
	void excluir(E entidade);

	/**
	 * Remove a entidade cujo id  o informado.
	 *
	 * @param id
	 *            id da entidade a remover.
	 */
	void excluir(Serializable id);

	/**
	 * Insere a entidade caso seu id seja nulo ou a atualiza caso contrrio.
	 *
	 * @param entidade
	 *            entidade para a operao.
	 */
	void inserirOuAtualizar(E entidade);

}
