package br.com.locadora.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.locadora.model.connection.MysqlConnect;
import br.com.locadora.model.entity.Agencia;
import br.com.locadora.utils.SystemUtils;

public class AgenciaDAO extends MysqlConnect {
	
	public Agencia select(int id) {
		PreparedStatement sqlSt;
		ResultSet resultSet;
		try {
			String sql = "SELECT * FROM agencia WHERE id_agencia = ?";
			sqlSt = conn.prepareStatement(sql);
			sqlSt.setInt(1, id);
			resultSet = sqlSt.executeQuery();
			Agencia agencia = new Agencia();
			
			if (resultSet.next()) {
				agencia.setIdAgencia(resultSet.getInt(1));
				agencia.setRazaoSocial(resultSet.getString(2));
				agencia.setNomeFantasia(resultSet.getString(3));
				agencia.setCnpj(resultSet.getString(4));
				agencia.setInscricaoEstadual(resultSet.getString(5));
				agencia.setResponsavel(resultSet.getString(6));
				agencia.setLogradouro(resultSet.getString(7));
				agencia.setBairro(resultSet.getString(8));
				agencia.setNumero(resultSet.getInt(9));
				agencia.setCep(resultSet.getString(10));
				agencia.setCidade(resultSet.getString(11));
				agencia.setUf(resultSet.getString(12));
				agencia.setTelefone(resultSet.getString(13));
				agencia.setEmail(resultSet.getString(14));
				agencia.setSite(resultSet.getString(15));
				agencia.setDataCadastro(resultSet.getDate(16));
				agencia.setDataManutencao(resultSet.getDate(17));
				agencia.setAtivo(resultSet.getBoolean(18));
			}
			return agencia;
		} catch (Exception selectError) {
			return null;
		} finally {
			closeConnection();
		}
	}

	public boolean update(Agencia agencia) {
		PreparedStatement sqlSt;
		try {
			String sql = "UPDATE agencia SET razao_social = ?, fantasia = ?, cnpj = ?, insc_estadual = ?, responsavel = ?, " 
					+ "logradouro = ?, bairro = ?, numero = ?, cep = ?, cidade = ?, uf = ?, telefone = ?, email = ?, "
					+ "site = ?, data_manutencao = ?, ativo = ? WHERE id_agencia = ? ";
			
			sqlSt = conn.prepareStatement(sql);
			sqlSt.setString(1, agencia.getRazaoSocial());
			sqlSt.setString(2, agencia.getNomeFantasia());
			sqlSt.setString(3, agencia.getCnpj());
			sqlSt.setString(4, agencia.getInscricaoEstadual());
			sqlSt.setString(5, agencia.getResponsavel());
			sqlSt.setString(6, agencia.getLogradouro());
			sqlSt.setString(7, agencia.getBairro());
			sqlSt.setInt(8, agencia.getNumero());
			sqlSt.setString(9, agencia.getCep());
			sqlSt.setString(10, agencia.getCidade());
			sqlSt.setString(11, agencia.getUf());
			sqlSt.setString(12, agencia.getTelefone());
			sqlSt.setString(13, agencia.getEmail());
			sqlSt.setString(14, agencia.getSite());
			sqlSt.setDate(15, SystemUtils.dataConverter(agencia.getDataManutencao()));
			sqlSt.setBoolean(16, agencia.isAtivo());
			sqlSt.setInt(17, agencia.getIdAgencia());
			sqlSt.executeUpdate();
			return true;
		} catch (Exception selectError) {
			selectError.printStackTrace();
			return false;
		} finally {
			closeConnection();
		}
	}

	public boolean insert(Agencia agencia) {
		PreparedStatement sqlSt;
		try {
			String sql = "INSERT INTO agencia " +
							"(razao_social," +
							"fantasia," +
							"cnpj," +
							"insc_estadual," +
							"responsavel," +
							"logradouro," +
							"bairro," +
							"numero," +
							"cep," +
							"cidade," +
							"uf," +
							"telefone," +
							"email," +
							"site," +
							"data_cadastro," +
							"ativo)" +
						"VALUES" +
						"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			sqlSt = conn.prepareStatement(sql);
			sqlSt.setString(1, agencia.getRazaoSocial());
			sqlSt.setString(2, agencia.getNomeFantasia());
			sqlSt.setString(3, agencia.getCnpj());
			sqlSt.setString(4, agencia.getInscricaoEstadual());
			sqlSt.setString(5, agencia.getResponsavel());
			sqlSt.setString(6, agencia.getLogradouro());
			sqlSt.setString(7, agencia.getBairro());
			sqlSt.setInt(8, agencia.getNumero());
			sqlSt.setString(9, agencia.getCep());
			sqlSt.setString(10, agencia.getCidade());
			sqlSt.setString(11, agencia.getUf());
			sqlSt.setString(12,  agencia.getTelefone());
			sqlSt.setString(13,  agencia.getEmail());
			sqlSt.setString(14, agencia.getSite());
			sqlSt.setDate(15, SystemUtils.dataConverter(agencia.getDataCadastro()));
			sqlSt.setBoolean(16, agencia.isAtivo());
			sqlSt.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeConnection();
		}
	}

	public boolean delete(int id) {
		try{
			String sqlString = "delete from cliente where id_cliente = ?";
			PreparedStatement sqlSt;
			sqlSt = conn.prepareStatement(sqlString);
			sqlSt.setInt(1, id);
			sqlSt.executeQuery();
			return true;
		}catch(Exception deleteError){
			return false;
		} finally {
			closeConnection();
		}
	}
	
	/**
	 * Busca todas as agências cadastradas na base, com base na conditional 
	 * passada por parâmtro, a query usada para pesquisa é <b>SELECt * FROM agencia</b>
	 * @author Joaquim Neto
	 * @param conditional condição para a consulta sql
	 * @return Lista com as agências encontradas
	 */
	public List<Agencia> pesquisarPorCondicao(String conditional){
		List<Agencia> lista = new ArrayList<Agencia>();
		ResultSet resultSet;
		Agencia agencia;
		
		try{
			String sql = "SELECT * FROM agencia " + conditional;
			
			PreparedStatement st = conn.prepareStatement(sql);
			resultSet = st.executeQuery();
			
			while(resultSet.next()){
				agencia = new Agencia();
				agencia.setIdAgencia(resultSet.getInt(1));
				agencia.setRazaoSocial(resultSet.getString(2));
				agencia.setNomeFantasia(resultSet.getString(3));
				agencia.setCnpj(resultSet.getString(4));
				agencia.setInscricaoEstadual(resultSet.getString(5));
				agencia.setResponsavel(resultSet.getString(6));
				agencia.setLogradouro(resultSet.getString(7));
				agencia.setBairro(resultSet.getString(8));
				agencia.setNumero(resultSet.getInt(9));
				agencia.setCep(resultSet.getString(10));
				agencia.setCidade(resultSet.getString(11));
				agencia.setUf(resultSet.getString(12));
				agencia.setTelefone(resultSet.getString(13));
				agencia.setEmail(resultSet.getString(14));
				agencia.setSite(resultSet.getString(15));
				agencia.setDataCadastro(resultSet.getDate(16));
				agencia.setDataManutencao(resultSet.getDate(17));
				agencia.setAtivo(resultSet.getBoolean(18));
				lista.add(agencia);
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
