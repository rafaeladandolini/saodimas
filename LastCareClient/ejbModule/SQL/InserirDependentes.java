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
import br.com.saodimas.model.beans.RelacaoVO;
import br.com.saodimas.model.dao.connection.SqlConnector;
import br.com.saodimas.model.dao.impl.AssociadoDAOImpl;
import br.com.saodimas.model.exception.MensagemSaoDimasException;

public class InserirDependentes {

	/**
	 * Retirar os ; no excel, após isso no txt substituir ;; por ; ;
	 * @param args
	 */
	public static void main(String[] args) {
		
	 String str;
	 AssociadoVO asso = null;
	 try {
		 AssociadoDAOImpl dao = new AssociadoDAOImpl();
		 
		 	Connection conn = SqlConnector.getInstance().getConnection();
	        BufferedReader in = new BufferedReader(new FileReader("C:\\java\\projetos\\Sao Dimas SQL\\LastCareClient\\ejbModule\\SQL\\SEGURO1.txt"));
	     
	            while (in.ready()) {
	                str = in.readLine();
	                asso = InserirDependentes.getDependenteByLine(str);
	                dao.save(asso, conn);
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

	public static final int ID_APOLICE = 0;
	public static final int FILIACAO = 1;
	public static final int NOME = 2;
	public static final int DATA_NASCIMENTO = 3;
 
	
	public static  AssociadoVO getDependenteByLine(String linha)
	{
		AssociadoVO vo = new AssociadoVO();
		String dados [] = linha.split(";");
		vo.setId(new Integer(dados[ID_APOLICE]));
		vo.setNome(dados[NOME]);
		
		if(dados.length > 3){
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			try {
				vo.setDataNascimento(format.parse(dados[DATA_NASCIMENTO]));
			} catch (ParseException e) {
				vo.setDataNascimento(null);
				e.printStackTrace();
			}
		}
		ApoliceVO apo = new ApoliceVO();
		apo.setId(new Integer(dados[ID_APOLICE]));
		vo.setApolice(apo);
		vo.setCPF("");
		vo.setStatus("Ativo");
		vo.setRelacao(InserirDependentes.getRelacao(dados[FILIACAO]));
		
		return vo;
	}
	
	public static RelacaoVO getRelacao(String relacao)
	{
		
		RelacaoVO rel = new RelacaoVO();
		if(relacao.trim().equals("TITULAR"))
			rel.setId(1);
		else if(relacao.trim().equals("CONJUGE"))
			rel.setId(2);
		else if(relacao.trim().equals("PAI"))
			rel.setId(3);
		else if(relacao.trim().equals("MAE"))
			rel.setId(4);
		else if(relacao.trim().equals("FILHO"))
			rel.setId(5);
		else if(relacao.trim().equals("FILHA"))
			rel.setId(6);
		else if(relacao.trim().equals("SOGRO"))
			rel.setId(7);
		else if(relacao.trim().equals("SOGRA"))
			rel.setId(8);
		else if(relacao.trim().equals("GENRO"))
			rel.setId(9);
		else if(relacao.trim().equals("NORA"))
			rel.setId(10);
		else if(relacao.trim().equals("IRMAO"))
			rel.setId(11);
		else if(relacao.trim().equals("IRMA"))
			rel.setId(12);
		else if(relacao.trim().equals("CUNHADO"))
			rel.setId(13);
		else if(relacao.trim().equals("CUNHADA"))
			rel.setId(14);
		else if(relacao.trim().equals("SOBRINHO"))
			rel.setId(15);
		else if(relacao.trim().equals("SOBRINHA"))
			rel.setId(16);
		else if(relacao.trim().equals("TIO"))
			rel.setId(17);
		else if(relacao.trim().equals("TIA"))
			rel.setId(18);
		else if(relacao.trim().equals("AVÔ"))
			rel.setId(19);
		else if(relacao.trim().equals("AVO"))
			rel.setId(20);
		else if(relacao.trim().equals("NETO"))
			rel.setId(21);
		else if(relacao.trim().equals("NETA"))
			rel.setId(22);
		else if(relacao.trim().equals("PRIMO"))
			rel.setId(23);
		else if(relacao.trim().equals("PRIMA"))
			rel.setId(24);
		else if(relacao.trim().equals("AFILHADO"))
			rel.setId(25);
		else if(relacao.trim().equals("AFILHADA"))
			rel.setId(26);
		else if(relacao.trim().equals("BISNETA"))
			rel.setId(27);
		else if(relacao.trim().equals("BISNETO"))
			rel.setId(28);
		else if(relacao.trim().equals("COMADRE"))
			rel.setId(29);
		else if(relacao.trim().equals("COMPANHEIRO"))
			rel.setId(30);
		else if(relacao.trim().equals("DEPENDENTE"))
			rel.setId(31);
		else if(relacao.trim().equals("ENTEADA"))
			rel.setId(32);
		else if(relacao.trim().equals("ENTEADO"))
			rel.setId(33);
		else if(relacao.trim().equals("MADRASTA"))
			rel.setId(34);
		else if(relacao.trim().equals("PADRASTO"))
			rel.setId(35);
		else if(relacao.trim().equals("FALTA RELACAO"))
			rel.setId(36);
		else
		{
			rel = null;
			rel.setId(0);
		}
		
		return rel;
	}
}
