package br.com.locadora.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.locadora.model.connection.MysqlConnect;
import br.com.locadora.model.entity.Cliente;
import br.com.locadora.utils.SystemUtils;

public class ClienteDAO extends MysqlConnect{

	public Cliente select(int id) {
		PreparedStatement sqlSt;
		ResultSet resultSet;
		
		try{
			String sql = "SELECT * FROM cliente where id_cliente=?";
			sqlSt = conn.prepareStatement(sql);
			sqlSt.setInt(1, id);
			resultSet = sqlSt.executeQuery();
			Cliente resultado = new Cliente();
			
			if (resultSet.next()){
				resultado.setId(resultSet.getInt(1));
				resultado.setNome(resultSet.getString(2));
				resultado.setDataNascimento(resultSet.getDate(3));
				resultado.setCpf(resultSet.getString(4));
				resultado.setRg(resultSet.getString(5));
				resultado.setCnh(resultSet.getString(6));
				resultado.setValidadeCnh(resultSet.getDate(7));
				resultado.setEstadoEmissor(resultSet.getString(8));
				resultado.setGenero(resultSet.getString(9).charAt(0));
				resultado.setLogradouro(resultSet.getString(10));
				resultado.setBairro(resultSet.getString(11));
				resultado.setNumero(resultSet.getInt(12));
				resultado.setCep(resultSet.getString(13));
				resultado.setCidade(resultSet.getString(14));
				resultado.setUf(resultSet.getString(15));
				resultado.setTelefone(resultSet.getString(16));
				resultado.setEmail(resultSet.getString(17));
				resultado.setIdAgencia(resultSet.getInt(18));
				resultado.setIdFuncionario(resultSet.getInt(19));
				resultado.setDataCadastro(resultSet.getDate(20));
				resultado.setAtivo(resultSet.getBoolean(21));
			}
			return resultado;
		} catch(Exception selectError) {
			selectError.printStackTrace();
			return null;
		} finally {
			closeConnection();
		}
	}

	public boolean update(Cliente cliente) {
		PreparedStatement sqlSt;
		try{
			String sql = "UPDATE cliente " +
							"SET " +
								"nome = ?," +
								"data_nascimento = ?," +
								"cpf = ?," +
								"rg = ?," +
								"cnh = ?," +
								"validade_cnh = ?," +
								"estado_emissor = ?," +
								"genero = ?," +
								"logradouro = ?," +
								"bairro = ?," +
								"numero = ?," +
								"cep = ?," +
								"cidade = ?," +
								"uf = ?," +
								"telefone = ?," +
								"email = ?," +
								"data_manutencao = ?," +
								"ativo = ?" +
							" WHERE id_cliente = ?";
			sqlSt = conn.prepareStatement(sql);
			sqlSt.setString(1,  cliente.getNome());
			sqlSt.setDate(2, SystemUtils.dataConverter(cliente.getDataNascimento()));
			sqlSt.setString(3,  cliente.getCpf());
			sqlSt.setString(4, cliente.getRg());
			sqlSt.setString(5, cliente.getCnh());
			sqlSt.setDate(6, SystemUtils.dataConverter(cliente.getValidadeCnh()));
			sqlSt.setString(7, cliente.getEstadoEmissor());
			sqlSt.setString(8, String.valueOf(cliente.getGenero()));
			sqlSt.setString(9, cliente.getLogradouro());
			sqlSt.setString(10, cliente.getBairro());
			sqlSt.setInt(11, cliente.getNumero());
			sqlSt.setString(12, cliente.getCep());
			sqlSt.setString(13, cliente.getCidade());
			sqlSt.setString(14, cliente.getUf());
			sqlSt.setString(15, cliente.getTelefone());
			sqlSt.setString(16, cliente.getEmail());
			sqlSt.setDate(17, SystemUtils.dataConverter(new Date(System.currentTimeMillis())));
			sqlSt.setBoolean(18, cliente.isAtivo());
			sqlSt.setInt(19, cliente.getId());
			sqlSt.execute();
			return true;
		}catch(Exception selectError){
			selectError.printStackTrace();
			return false;
		} finally {
			closeConnection();
		}
	}

	public boolean insert(Cliente cliente) {
		PreparedStatement sqlSt;
		try{
			String sql = "insert into cliente (nome, data_nascimento, cpf, rg, cnh, validade_cnh, estado_emissor,"
					+ " genero, logradouro, bairro, numero, cep, cidade, uf, telefone, email, id_agencia, id_funcionario, data_cadastro, ativo)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			sqlSt = conn.prepareStatement(sql);
			sqlSt.setString(1,  cliente.getNome());
			sqlSt.setDate(2, SystemUtils.dataConverter(cliente.getDataNascimento()));
			sqlSt.setString(3,  cliente.getCpf());
			sqlSt.setString(4, cliente.getRg());
			sqlSt.setString(5, cliente.getCnh());
			sqlSt.setDate(6, SystemUtils.dataConverter(cliente.getValidadeCnh()));
			sqlSt.setString(7, cliente.getEstadoEmissor());
			sqlSt.setString(8, String.valueOf(cliente.getGenero()));
			sqlSt.setString(9, cliente.getLogradouro());
			sqlSt.setString(10, cliente.getBairro());
			sqlSt.setInt(11, cliente.getNumero());
			sqlSt.setString(12, cliente.getCep());
			sqlSt.setString(13, cliente.getCidade());
			sqlSt.setString(14, cliente.getUf());
			sqlSt.setString(15, cliente.getTelefone());
			sqlSt.setString(16, cliente.getEmail());
			sqlSt.setInt(17, cliente.getIdAgencia());
			sqlSt.setInt(18, cliente.getIdFuncionario());
			sqlSt.setDate(19, SystemUtils.dataConverter(cliente.getDataCadastro()));
			sqlSt.setBoolean(20, cliente.isAtivo());
			sqlSt.execute();
			return true;
		}catch(Exception selectError){
			selectError.printStackTrace();
			return false;
		} finally {
			closeConnection();
		}
	}

	public boolean delete(int id) {
		try{
			String sqlString = "delete from cliente where id_cliente=?";
			PreparedStatement sqlSt;
			sqlSt = conn.prepareStatement(sqlString);
			sqlSt.setInt(1, id);
			sqlSt.execute();
			return true;
		}catch(Exception deleteError){
			return false;
		}
	}
	
	/**
	 * Busca todas as clientes cadastradas na base, com base na conditional 
	 * passada por parâmetro, a query usada para pesquisa é <b>SELECt * FROM cliente</b>
	 * @author Joaquim Neto
	 * @param conditional condição para a consulta sql
	 * @return Lista com os clientes encontrados
	 */
	public List<Cliente> pesquisarPorCondicao(String conditional){
		List<Cliente> lista = new ArrayList<Cliente>();
		ResultSet resultSet;
		Cliente cliente;
		
		try{
			String sql = "SELECT * FROM cliente " + conditional;
			
			PreparedStatement st = conn.prepareStatement(sql);
			resultSet = st.executeQuery();
			
			while(resultSet.next()){
				cliente = new Cliente();
				cliente.setId(resultSet.getInt(1));
				cliente.setNome(resultSet.getString(2));
				cliente.setDataNascimento(resultSet.getDate(3));
				cliente.setCpf(resultSet.getString(4));
				cliente.setRg(resultSet.getString(5));
				cliente.setCnh(resultSet.getString(6));
				cliente.setValidadeCnh(resultSet.getDate(7));
				cliente.setEstadoEmissor(resultSet.getString(8));
				cliente.setGenero(resultSet.getString(9).charAt(0));
				cliente.setLogradouro(resultSet.getString(10));
				cliente.setBairro(resultSet.getString(11));
				cliente.setNumero(resultSet.getInt(12));
				cliente.setCep(resultSet.getString(13));
				cliente.setCidade(resultSet.getString(14));
				cliente.setUf(resultSet.getString(15));
				cliente.setTelefone(resultSet.getString(16));
				cliente.setEmail(resultSet.getString(17));
				cliente.setIdAgencia(resultSet.getInt(18));
				cliente.setIdAgencia(resultSet.getInt(18));
				cliente.setIdFuncionario(resultSet.getInt(19));
				cliente.setDataCadastro(resultSet.getDate(20));
				cliente.setAtivo(resultSet.getBoolean(21));
				
				lista.add(cliente);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
		
		return lista;
	}

}
