
package br.com.locadora.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.locadora.model.DAO.LocacaoDAO;
import br.com.locadora.model.entity.Locacao;
import br.com.locadora.model.entity.Pagamento;
import br.com.locadora.model.entity.Veiculo;
import br.com.locadora.model.enums.StatusLocacaoEnum;
import br.com.locadora.model.enums.StatusVeiculoEnum;
import br.com.locadora.utils.SystemUtils;

public class DevolucaoControl implements Serializable{
	private static final long serialVersionUID = 4056318420856682823L;
	
	private LocacaoDAO locacaoDAO;
	
	public DevolucaoControl() {
		locacaoDAO = new LocacaoDAO();
	}
	
	
	public Locacao buscaLocacaoPorCodigo(int id){
		ClienteControl clienteControl = new ClienteControl();
		VeiculoControl veiculoControl = new VeiculoControl();
		AgenciaControl agenciaControl = new AgenciaControl();

		locacaoDAO = new LocacaoDAO();
		Locacao locacao = locacaoDAO.select(id);
		double valorAcrescimo = 0.0;
		
		if (!SystemUtils.isNuloOuVazio(locacao)) {
			if (locacao.getAgenciaDevolucao() != SystemUtils.getAgenciaSelecionado().getIdAgencia()) {
				valorAcrescimo += 30;
			}
			if (locacao.getDataHoraPrevistaDevolucao().compareTo(new Date()) <= 0) {
				valorAcrescimo += 100;
			}
			
			locacao.setValorAcrescimo(valorAcrescimo);
			locacao.setIdVeiculo(veiculoControl.buscarPorId(locacao.getVeiculo().getId()));
			locacao.setCliente(clienteControl.buscarPorId(locacao.getCliente().getId()));
			locacao.setObjetoAgenciaDevolucao(agenciaControl.buscarPorId(locacao.getIdAgencia()));
		}

		return locacao;
	}
	
	/**
	 * Faz uma locação validando os dados informados
	 * @author Joaquim Neto
	 * @param locacao
	 * @return Objeto locação com os dados validados
	 */
	public Locacao fazerDevolucao(Locacao locacao) {
		
		// Altera o status da locação
		locacao.setStatus(StatusLocacaoEnum.FECHADA.getValue());
		locacao.setDataHoraDevolucao(new Date());
		
		
		alterar(locacao);
		
		// Persiste o pagamento
		PagamentoControl pagamentoControl = new PagamentoControl();
		Pagamento pagamento = locacao.getPagamento();
		pagamento.setIdLocacao(buscarIdLocacao(locacao));
		pagamento.setValor(locacao.getValor());
		
		// Atribui o id da locação realizada ao pagamento
		pagamento.setIdLocacao(locacao.getId());
		
		// Presiste o pagamento
		pagamentoControl.salvarPagamento(locacao.getPagamento());
		
		// Altera o status do veículo para locado
		VeiculoControl veiculoControl = new VeiculoControl();
		Veiculo veiculo = locacao.getVeiculo();
		veiculo.setStatus(StatusVeiculoEnum.DISPONIVEL.getValue());
		veiculoControl.salvarOuAlterar(veiculo);
				
		return locacao;
	}
	
	/**
	 * Persiste uma locação na base de dados
	 * @author Joaquim Neto
	 * @param devolucaoObjeto Locação
	 * @return <b>true</b> Se for cadastrado com sucesso
	 */
	private boolean salvar(Locacao locacao) {
		// Cria uma nova conexão com o banco de dados
		locacaoDAO = new LocacaoDAO();
		
		locacao.setStatus(StatusLocacaoEnum.ABERTA.getValue());
		locacao.setIdAgencia(SystemUtils.getAgenciaSelecionado().getIdAgencia());
		locacao.setIdFuncionario(SystemUtils.getFuncionarioLogado().getId());
		return locacaoDAO.insert(locacao);
	}
	
	/**
	 * Altera uma locação já persistida na base de dados
	 * @author Joaquim Neto
	 * @param devolucaoObjeto Locação
	 * @return <b>true</b> Se for alterado com sucesso
	 */
	private boolean alterar(Locacao locacao) {
		// Cria uma nova conexão com o banco de dados
		locacaoDAO = new LocacaoDAO();
		
		return locacaoDAO.update(locacao);
	}
	
	/**
	 * Busca todas as locaçãos cadastradas na base de dados
	 * @author Joaquim Neto
	 * @return List com todas as locaçãos cadastradas
	 */
	public List<Locacao> buscarTodos() {
		// Cria uma nova conexão com o banco de dados
		locacaoDAO = new LocacaoDAO();		
		return locacaoDAO.pesquisarPorCondicao("");
	}
	
	/**
	 * Busca uma locação na base pelo id informado por parâmetro
	 * @author Joaquim Neto
	 * @param id INT ind
	 * @return Locacao
	 */
	public Locacao buscarPorId(int id) {
		// Cria uma nova conexão com o banco de dados
		locacaoDAO = new LocacaoDAO();
		return locacaoDAO.select(id);
	}
	
	private int buscarIdLocacao(Locacao locacao) {
		locacaoDAO = new LocacaoDAO();
		return locacaoDAO.buscarIdLocacao(locacao);
	}
	
	/**
	 * Consulta todas as locaçãos cadastradas na base
	 * @author Joaquim Neto
	 * @param parametro
	 * @param valor
	 * @return List com as locaçãos encontradas
	 */
	public List<Locacao> buscarPorCondicao(int parametro, String valor) {
		// Cria uma nova conexão com o banco de dados
		locacaoDAO = new LocacaoDAO();
		
		String condicao = "";
		
		switch (parametro) {
		case 1:
			condicao = " WHERE ativo = 1";
			break;
		case 2:
			condicao = " WHERE " + "id_devolucao = " + valor + " AND ativo = 1";
			break;
			
		default:
			condicao = " WHERE ativo = 1";
			break;
		}	
		
		return locacaoDAO.pesquisarPorCondicao(condicao);
	}
}
