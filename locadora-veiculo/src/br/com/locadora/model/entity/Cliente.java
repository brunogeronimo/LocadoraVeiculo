package br.com.locadora.model.entity;
import java.util.Date;

import br.com.locadora.model.vo.Endereco;
import br.com.locadora.utils.annotation.Required;
public class Cliente {
	private int id;
	@Required(label = "Nome", minimo = 5)
	private String nome;
	private Date dataNascimento;
	@Required(label = "CPF", minimo = 14)
	private String cpf;
	@Required(label = "RG", minimo = 9)
	private String rg;
	@Required(label = "CNH", minimo = 10)
	private String cnh;
	@Required(label = "Estado", minimo = 2)
	private String estadoEmissor;
	private Date validadeCnh;
	private char genero;
	private String logradouro;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;
	private int numero;
	@Required(label = "Telefone", minimo = 10)
	private String telefone;
	@Required(label = "Email", minimo = 6)
	private String email;
	private int idAgencia;
	private int idFuncionario;
	private Date dataCadastro;
	private Date dataManutencao;
	private boolean ativo;
	
	public Cliente(){
		setId(0);
		setNome("");
		setDataNascimento(null);
		setCnh("");
		setEstadoEmissor("");
		setValidadeCnh(null);
		setLogradouro("");
		setBairro("");
		setCep("");
		setCidade("");
		setUf("");
		setNumero(0);
		setTelefone("");
		setEmail("");
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCnh() {
		return cnh;
	}
	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	public String getEstadoEmissor() {
		return estadoEmissor;
	}
	public void setEstadoEmissor(String estadoEmissor) {
		this.estadoEmissor = estadoEmissor;
	}
	public Date getValidadeCnh() {
		return validadeCnh;
	}
	public void setValidadeCnh(Date validadeCnh) {
		this.validadeCnh = validadeCnh;
	}
	public char getGenero() {
		return genero;
	}
	public void setGenero(char genero) {
		this.genero = genero;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(int agencia) {
		this.idAgencia = agencia;
	}

	/**
	 * @return the idFuncionario
	 */
	public int getIdFuncionario() {
		return idFuncionario;
	}

	/**
	 * @param idFncionario the idFncionario to set
	 */
	public void setIdFuncionario(int idFncionario) {
		this.idFuncionario = idFncionario;
	}

	/**
	 * @return the dataCadastro
	 */
	public Date getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * @return the dataManutenção
	 */
	public Date getDataManutencao() {
		return dataManutencao;
	}

	/**
	 * @param dataManutencao the dataManutenção to set
	 */
	public void setDataManutencao(Date dataManutencao) {
		this.dataManutencao = dataManutencao;
	}

	/**
	 * @return the ativo
	 */
	public boolean isAtivo() {
		return ativo;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	/**
	 * Retorna um objeto endereço com os dados presente
	 * na instacia atual do objeto
	 * @author Joaquim Neto
	 * @return Objeto Endereço
	 */
	public Endereco getEndereco() {
		Endereco endereco = new Endereco();
		endereco.setLogradouro(getLogradouro());
		endereco.setBairro(getBairro());
		endereco.setNumero(getNumero());
		endereco.setCep(getCep());
		endereco.setUf(getUf());
		endereco.setCidade(getCidade());
		endereco.setTelefone(getTelefone());
		endereco.setEmail(getEmail());
		endereco.setSite("");
		
		return endereco;
	}

}
