package br.com.locadora.model.entity;
import java.util.Date;
import java.util.List;

public class Locacao {
	public int id;
	private int numeroLocacao;
	private Date dataHoraLocacao;
	private Date dataHoraDevolucao;
	private int agenciaDevolucao;
	private int tipoTarifa;
	private double kmLocacao;
	private double kmDevolucao;
	private double valor;
	private double valorAcrescimo;
	private String tipoPagamento;
	private Pagamento pagamento;
	private Devolucao devolucao;
	private Cliente cliente;
	private Veiculo veiculo;
	public Locacao(){
		setNumeroLocacao(0);
		setDataHoraLocacao(null);
		setDataHoraDevolucao(null);
		setAgenciaDevolucao(0);
		setTipoTarifa(0);
		setKmLocacao(0.0);
		setPagamento(null);
		setDevolucao(null);
		setCliente(null);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumeroLocacao() {
		return numeroLocacao;
	}
	public void setNumeroLocacao(int numeroLocacao) {
		this.numeroLocacao = numeroLocacao;
	}
	public Date getDataHoraLocacao() {
		return dataHoraLocacao;
	}
	public void setDataHoraLocacao(Date dataHoraLocacao) {
		this.dataHoraLocacao = dataHoraLocacao;
	}
	public Date getDataHoraDevolucao() {
		return dataHoraDevolucao;
	}
	public void setDataHoraDevolucao(Date dataHoraDevolucao) {
		this.dataHoraDevolucao = dataHoraDevolucao;
	}
	public int getAgenciaDevolucao() {
		return agenciaDevolucao;
	}
	public void setAgenciaDevolucao(int agenciaDevolucao) {
		this.agenciaDevolucao = agenciaDevolucao;
	}
	public int getTipoTarifa() {
		return tipoTarifa;
	}
	public void setTipoTarifa(int tipoTarifa) {
		this.tipoTarifa = tipoTarifa;
	}
	public double getKmLocacao() {
		return kmLocacao;
	}
	public void setKmLocacao(double kmLocacao) {
		this.kmLocacao = kmLocacao;
	}
	public double getKmDevolucao() {
		return kmDevolucao;
	}
	public void setKmDevolucao(double kmDevolucao) {
		this.kmDevolucao = kmDevolucao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getValorAcrescimo() {
		return valorAcrescimo;
	}
	public void setValorAcrescimo(double valorAcrescimo) {
		this.valorAcrescimo = valorAcrescimo;
	}
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	public Devolucao getDevolucao() {
		return devolucao;
	}
	public void setDevolucao(Devolucao devolucao) {
		this.devolucao = devolucao;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public void consultar() {
	}
	public void locar() {
	}
	public List<Locacao> consultarLocacaoDiaria() {
		return null;
	}

}
