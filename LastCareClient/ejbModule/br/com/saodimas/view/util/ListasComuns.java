package br.com.saodimas.view.util;

import javax.swing.LookAndFeel;

import org.jvnet.substance.SubstanceLegacyDefaultLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessLookAndFeel;
import org.jvnet.substance.skin.SubstanceMistSilverLookAndFeel;
import org.jvnet.substance.skin.SubstanceModerateLookAndFeel;
import org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel;
import org.jvnet.substance.skin.SubstanceNebulaLookAndFeel;
import org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel;
import org.jvnet.substance.skin.SubstanceStDismasLookAndFeel;


public final class ListasComuns {
	public static final String[] ESTADOS = {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"};
	public static final String[] STATUS_ITENS = {"Ativo","Cancelado", "Suspenso"};
	public static final String[] STATUS_APOLICE = {"Ativo","Suspenso","Cancelado"};
	public static final String[] REF_PRODUTOS = {"Unidade", "Par", "Dezena", "Dúzia", "Cento" ,"Conjunto"};
	public static final String[] REF_SERVICOS = {"Hora", "KM", "Execução"};
	public static final String[] PARENTESCO = {"Conjuge", "Filha/Filho", "Mãe/Pai", "Avó/Avô", "Irmã/Irmão", "Neta/Neto"};
	public static final String[] STATUS_FATURA = {"Em aberto","Pago","Cancelada"};
	public static final String[] STATUS_EQUIPAMENTO = {"Em uso","Arrumando","Estragado","Não disponível"};
	public static final String[] STATUS_CLIENTE = {"Ativo","Inadimplente","Inativo"};
	public static final String[] STATUS_EMPRESTIMO_EQUIPAMENTO = {"Todos","Emprestado", "Devolvido"};
	public static final String[] STATUS_VENDA = {"Em aberto","Finalizada"};
	
	public static final String[] TIPO_PAGAMENTO = {"Dinheiro","Cartão", "Cheque", "Boleto", "Nota Promissória", "Tansfência Bancária"};
	public static final String[] PARCELAMENTO   = {"01x (30 Dias)",
											 	 "02x (30 Dias)",
											 	 "03x (30 Dias)",
											 	 "04x (30 Dias)",
												 "05x (30 Dias)",
												 "06x (30 Dias)",
												 "07x (30 Dias)",
												 "08x (30 Dias)",
												 "09x (30 Dias)",
												 "10x (30 Dias)",
												 "À Vista"};
										
	public static final String ADMINISTRADOR = "Administrador";
	public static final String FUNCIONARIO = "Funcionário";
	
	@SuppressWarnings("serial")
	public static final LookAndFeel[] TEMAS = {
		new SubstanceStDismasLookAndFeel(){@Override
			public String toString() {
			return "São Dimas";
		}},
		new SubstanceLegacyDefaultLookAndFeel(){@Override
			public String toString() {
			return "Substance Core";
		}},
		new SubstanceBusinessBlackSteelLookAndFeel(){@Override
			public String toString() {
			return "Business Black";
		}},
		new SubstanceBusinessLookAndFeel(){@Override
			public String toString() {
			return "Business";
		}},
		new SubstanceMistSilverLookAndFeel(){@Override
			public String toString() {
			return "Mist Silver";
		}},
		new SubstanceModerateLookAndFeel(){@Override
			public String toString() {
			return "Moderate";
		}},
		new SubstanceNebulaBrickWallLookAndFeel(){@Override
			public String toString() {
			return "Nebula Brick";
		}},
		new SubstanceNebulaLookAndFeel(){@Override
			public String toString() {
			return "Nebula";
		}},
		new SubstanceOfficeSilver2007LookAndFeel(){@Override
			public String toString() {
			return "Office Silver";
		}}
	};
	
}
