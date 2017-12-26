package SQL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.AssociadoVO;
import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.model.beans.ColaboradorVO;
import br.com.saodimas.model.beans.PlanoVO;
import br.com.saodimas.model.dao.connection.SqlConnector;
import br.com.saodimas.model.dao.impl.ApoliceDAOImpl;
import br.com.saodimas.model.exception.MensagemSaoDimasException;

public class InserirApolice {

	/**
	 * Retirar os ; no excel, após isso no txt substituir ;; por ; ;
	 * @param args
	 */
	public static void main(String[] args) {
		
	 String str;
	 ApoliceVO apoli = null;
	 try {
		 ApoliceDAOImpl dao = new ApoliceDAOImpl();
		 
		 	Connection conn = SqlConnector.getInstance().getConnection();
	        BufferedReader in = new BufferedReader(new FileReader("C:\\java\\projetos\\Sao Dimas SQL\\LastCareClient\\ejbModule\\SQL\\SEGUROS.txt"));
	     
	            while (in.ready()) {
	                str = in.readLine();
	                apoli = InserirApolice.getApoliceByLine(str);
	                dao.saveComID(apoli, conn);
	            }
	            in.close();
	    } catch (IOException e) {
			e.printStackTrace();
	    } catch (SQLException e) {
			e.printStackTrace();
		} catch (MensagemSaoDimasException e) {
			e.printStackTrace();
		}
	}
	
	public static final int ID = 0;
	public static final int DATA_ADESAO = 1;
	public static final int NOME = 2;
	public static final int NASCIMETNO = 3;
	public static final int CPF = 4;
	public static final int RG = 5;
	public static final int END = 6;
	public static final int CIDADE = 7;
	public static final int CEP = 8;
	public static final int FONE = 9;
	public static final int OBS = 10; 
	
	public static  ApoliceVO getApoliceByLine(String linha)
	{
		ApoliceVO vo = new ApoliceVO();
		String dados [] = linha.split(";");
		vo.setId(new Integer(dados[ID]));
		vo.setNumeroContrato(vo.getId()+"");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			vo.setDataAdesao(format.parse(dados[DATA_ADESAO]));
		} catch (ParseException e) {
			vo.setDataAdesao(null);
			e.printStackTrace();
		}
		
		AssociadoVO titular = new AssociadoVO();
		titular.setNome(dados[NOME]);
		try {
			titular.setDataNascimento(format.parse(dados[NASCIMETNO]));
		} catch (ParseException e) {
			titular.setDataNascimento(null);
			e.printStackTrace();
		}
		
		titular.setCPF(dados[CPF]);
		titular.setStatus("Ativo");
		
		vo.setStatus("Ativo");
		
		vo.setTitular(titular);
		
		vo.setEndereco(dados[END]);
		
		CidadeVO cidade = new CidadeVO();
		cidade.setId(1);
		
		vo.setCidade(cidade);
		
		vo.setCep(dados[CEP]);
		vo.setTelefone(dados[FONE]);
		vo.setObservacao(dados[OBS]);
		
		ColaboradorVO col = new ColaboradorVO();
		col.setId(1);
		
		vo.setColaborador(col);
		
		PlanoVO plano = new PlanoVO();
		plano.setId(1);
		
		vo.setPlano(plano);
		return vo;
	}
}
