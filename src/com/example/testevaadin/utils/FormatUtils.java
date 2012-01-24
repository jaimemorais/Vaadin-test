package com.example.testevaadin.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class FormatUtils {
	
	
	private FormatUtils(){
		
	}
	
	/**
	 * Método que formata o tamanho da string inserindo espaços a direita. 
	 * @param String descricao
	 * @return String formatada
	 * @author Guilherme Esteves
	 */
	public static String formatStringLength (String descricao, Integer length, String tipoMascara){
		if (descricao.length() >= length) {
			descricao = (String) descricao.substring(0, length);
		}			
		else {
			do {
				descricao = descricao + tipoMascara;
			} while(descricao.length() < length);
		}
		return descricao;
	}

	/**
	 * Método que formata o tamanho da string inserindo espaços a esquerda. 
	 * @param String descricao
	 * @return String formatada
	 * @author Guilherme Esteves
	 */
	public static String formatStringLengthInicio (String descricao, Integer length, String tipoMascara){
		if (descricao.length() >= length) {
			descricao = (String) descricao.substring(descricao.length()-length);
		}			
		else {
			do {
				descricao = tipoMascara + descricao;
			} while(descricao.length() < length);
		}
		return descricao;
	}

	
	/**
	 * Método que formata o numero colocando zeros a esquerda. 
	 * @param Numero a ser formatado
	 * @param numero de casas decimais
	 * @return String formatada
	 * @author Guilherme Esteves
	 */
	public static String formatNumber (Object number, Integer length ){
		if (number==null){
			return "";			
		}
		StringBuilder pattern = new StringBuilder();
		for(int i = 0; i<length; i++ ){
			pattern.append("0");
		}
		DecimalFormat formatter = (DecimalFormat) DecimalFormat.getIntegerInstance();
		formatter.applyPattern(pattern.toString());
		return formatter.format(number);
	}
	
	/**
	 * Método que formata o numero colocando zeros a esquerda. 
	 * @param Numero a ser formatado
	 * @param numero de casas decimais
	 * @return String formatada
	 * @author Guilherme Esteves
	 */
	public static String formataMatriculaUnica(Long matriculaUnica){
		if (matriculaUnica==null){
			return "";			
		}
		String strMatriculaUnica = formatNumber(matriculaUnica,8);
		return strMatriculaUnica.substring(0,7) + "-" + strMatriculaUnica.substring(7,8);
	}
	
	/**
	 * Método usado para formatar Moedas no formato brasileiro: ###.###,00<br>
	 * Método pode receber tanto um Double quanto um Double
	 * @param valor Double ou Double
	 * @return Valor formatado para o padrão de moedas brasileiro
	 * @author Gisnálbert Spínola
	 */
	public static String formataMoeda(Object valor){
		
		DecimalFormat decimal = new DecimalFormat();
		decimal.setMinimumFractionDigits(2);
		decimal.setMaximumFractionDigits(2);
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setGroupingSeparator('.');
		simbolos.setDecimalSeparator(',');
		decimal.setDecimalFormatSymbols(simbolos);

		if(valor instanceof Number){
			Double obj = ((Number)valor).doubleValue();
			String result = decimal.format(obj);
			if( result.compareTo(decimal.format(-0.00)) == 0 )
				return decimal.format(0);
			return result;
		}
		return null;
	}
	
	public static String formataMoedaReal(Object numero){
		String valor = numero.toString().replace("-", "");
		StringBuilder valorRetorno = new StringBuilder("");
		String partes[] = null;
		if(valor.contains(".")) {
			partes =  valor.split("\\.");
			partes[1] += "00"; 
		}else{
			partes = new String[2];
			partes[0] = valor;
			partes[1] = "00";
		}
		valorRetorno.append(",");
		valorRetorno.append(partes[1].substring(0,2));
		int separador = 0;
		for(int i =partes[0].length()-1; i>=0; i--){
			valorRetorno.insert(0, partes[0].substring(i, i+1));
			if(++separador == 3){
				valorRetorno.insert(0,".");
				separador = 0;
			}
		}
		valor = valorRetorno.toString();
		if(valor.startsWith(".")){
			valor = valor.replaceFirst(".", "");
		}
		return "R$ "+valor;
	}
	
	/**
	 * Método usado para formatar valor monetário de Double para text com 17 posições
	 * @param valor Double 
	 * @return Valor formatado para o padrão de moedas brasileiro
	 * @author Paulo Viallet Neto
	 */
	public static String formataMoedaParaTexto(Object valor){
		return formatStringLengthInicio(retiraCaractereNaoNumericos(formataMoeda(valor)),
				17, "0");
	}
	/**
	 * Método usado para formatar valor monetário de Double para text com n posições
	 * @param valor Double
	 * @param tamanho int numero de digitos do valor monetário. 
	 * @return Valor formatado para o padrão de moedas brasileiro
	 * @author Paulo Viallet Neto
	 */
	public static String formataMoedaParaTexto(Object valor,int tamanho){
		return formatStringLengthInicio(retiraCaractereNaoNumericos(formataMoeda(valor)),
				tamanho, "0");
	}
	
	public static Double formataMoeda(String valor, Integer casasDecimais){
		
		valor = "0"+retiraCaractereNaoNumericos(valor);
		if(valor.length() > casasDecimais){
			return new Double(valor.substring(0, valor.length() - casasDecimais)+"."+valor.substring(valor.length()-casasDecimais));
		}
		return null;
	}
	
	public static String retiraCaractereNaoNumericos(String str){
		if (str!=null && !str.equals("")){
			String regex = "[^0-9]";
			str = str.replaceAll(regex,"");
		}
		return str;
	}

	
	/**
	 * Método que retira a máscara de formatação de moedas
	 * @param valor Double
	 * @return Double
	 * @author Gisnálbert Spínola
	 */
	public static Double retiraFormatacaoMoeda(String valor){
		String temp = valor.toString();
		return new Double(temp.replaceAll("\\.","").replaceAll(",","."));
	}
	
	/**
	 * Formata Double com 2 casas decimais
	 * @param valor
	 * @return
	 * @author Lívia Paes
	 */
	public static Double formataDouble(Double valor){
		
		DecimalFormat decimal = new DecimalFormat();
		decimal.setMinimumFractionDigits(2);
		decimal.setMaximumFractionDigits(2);
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setGroupingSeparator('.');
		simbolos.setDecimalSeparator(',');
		decimal.setDecimalFormatSymbols(simbolos);

		Double result = Double.valueOf(FormatUtils.retiraFormatacaoMoeda(decimal.format(valor)));
		return result;
		
	}

	
	/**
	 * Formata Inteiro - Retorna String
	 * @param valor
	 * @return
	 * @author Lívia Paes
	 */
	public static String formataInteger(Object valor){
		
		DecimalFormat decimal = new DecimalFormat();
		decimal.setMinimumFractionDigits(0);
		decimal.setMaximumFractionDigits(0);
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setGroupingSeparator('.');
		decimal.setDecimalFormatSymbols(simbolos);

		
		String result = decimal.format(valor);
		return result;
		
	}

	
	
	/**
	 * Método que formata o número do CPF retornado via BD para o formato
	 * 000.000.000-00
	 * @param cpf Número do Cpf
	 * @return CPF formatado
	 * @author R. Terra
	 */
	public static String formataCpf(Long cpf){
		if (cpf!=null){
			String strCpf = formatNumber(cpf,11);
			return strCpf.substring(0,3) + "." + strCpf.substring(3,6) + "." + 
				   strCpf.substring(6,9) + "-" + strCpf.substring(9,11); 
		}
		return "";
	}
	
	
	/**
	 * Método que formata o número do CNPJ retornado via BD para o formato
	 * 00.000.000/0000-00
	 * @param cnpj Número do CPNJ
	 * @return CNPJ formatado
	 * @author Gisnálbert Spínola
	 */
	public static String formataCnpj(Long cnpj){
		String strCnpj = formatNumber(cnpj,14);
		return strCnpj.substring(0,2) + "." + strCnpj.substring(2,5) + "." + 
			   strCnpj.substring(5,8) + "/" + strCnpj.substring(8,12) + "-" +
			   strCnpj.substring(12,14);
	}
	

	/**
	 * Método que formata o número do CEP retornado via BD para o formato
	 * 00000-000
	 * @param cep Número do Cep
	 * @return CE formatado
	 * @author R. Terra
	 */
	public static String formataCep(Long cep){
		String strCpf = formatNumber(cep,8);
		return strCpf.substring(0,5) + "-" + strCpf.substring(5,8); 
	}
	
	/**
	 * Método que formata o número do TELEFONE retornado via BD para o formato
	 * (00)0000-0000
	 * @param telefone Número do Telefone
	 * @return Telefone formatado
	 * @author R. Terra
	 */
	public static String formataTelefone(Long telefone){
		String strTelefone = formatNumber(telefone,10);
		return "(" + strTelefone.substring(0,2) + ")" + strTelefone.substring(2,6) + "-" + 	strTelefone.substring(6,10);
	}
	
	
	/**
	 * Método que formata uma String , substituindo os caracteres
	 * especiais por seus "similares" quando possível, e quando não possível, retirando o caractere
	 * especial.
	 * @param entrada String a ser formatada
	 * @return retorno String já formatada
	 * @author Gisnálbert Spínola
	 */
	public static String formataStringFormatoSistema(String entrada){
		if (entrada!=null && possuiCaractereEspeciais(entrada)){
			entrada = entrada.replaceAll("À", "A").replaceAll("Á", "A")
				.replaceAll("Â", "A").replaceAll("Ã", "A").replaceAll("Ä", "A")
				.replaceAll("Å", "A").replaceAll("È", "E").replaceAll("É", "E")
				.replaceAll("Ê", "E").replaceAll("Ë", "E").replaceAll("Ì", "I")
				.replaceAll("Í", "I").replaceAll("Î", "I").replaceAll("Ï", "I")
				.replaceAll("Ò", "O").replaceAll("Ó", "O").replaceAll("Ô", "O")
				.replaceAll("Õ", "O").replaceAll("Ö", "O").replaceAll("Ù", "U")
				.replaceAll("Ú", "U").replaceAll("Û", "U").replaceAll("Ü", "U")
				.replaceAll("Ç", "C").replaceAll("à", "a").replaceAll("á", "a")
				.replaceAll("â", "a").replaceAll("ã", "a").replaceAll("ä", "a")
				.replaceAll("å", "a").replaceAll("è", "e").replaceAll("é", "e")
				.replaceAll("ê", "e").replaceAll("ë", "e").replaceAll("ì", "i")
				.replaceAll("í", "i").replaceAll("î", "i").replaceAll("ï", "i")
				.replaceAll("ò", "o").replaceAll("ó", "o").replaceAll("ô", "o")
				.replaceAll("õ", "o").replaceAll("ö", "o").replaceAll("ù", "u")
				.replaceAll("ú", "ú").replaceAll("û", "û").replaceAll("ü", "u")
				.replaceAll("ç", "c");
			entrada = retiraCaracteresEspeciais(entrada);
		}
		return entrada;
	}
	
	
	/**
	 * Método usado para retirar todos os caracteres especiais
	 * @param str String a ser formatada
	 * @return str String formatada
	 * @author Gisnálbert Spínola
	 */
	public static String retiraCaracteresEspeciais(String str){
		if (str!=null && !str.equals("")){
			String regex = "[^A-Za-z0-9\\,\\s]";
			str = str.replaceAll(regex,"");
		}
		return str;
	}
	
	/**
	 * Método usado para retirar todos os caracteres especiais e numéricos
	 * @param str String a ser formatada
	 * @return str String formatada
	 * @author Lívia Paes
	 */
	public static String retiraCaracteresEspeciaisNumericos(String str){
		if (str!=null && !str.equals("")){
			String regex = "[^A-Za-z\\,\\s]";
			str = str.replaceAll(regex,"");
		}
		return str;
	}
	
	/**
	 * Método usado para verificar se a String sem caracteres especiais
	 * @param str String a ser formatada
	 * @return str String formatada
	 * @author Gisnálbert Spínola/R. Terra
	 */
	public static boolean possuiCaractereEspeciais(String str){
		if (str!=null && !str.equals("")){
			String regex = "[A-Za-z0-9\\,\\s]*";
			return !str.matches(regex);
		}
		return false;
	}

	/**
	 * Método usado para mascarar a String de Quantidade de Horas
	 * @param str String a ser formatada
	 * @return str String formatada
	 * @author R. Terra
	 */
	public static String mascaraQuantidadeHoras(String str){
		return str.substring(1, 2) + "h";
	}
	
	
	/**
	 * Método que formata o código do procedimento retornado via BD para o formato
	 * 0.00.00.00-0
	 * @param código do procedimento
	 * @return código do procedimento formatado
	 * @author Lívia Paes
	 */
	public static String formataCodigoProcedimento(Long codigoProcedimento){
		if (codigoProcedimento!=null){
			String strCodigo = formatNumber(codigoProcedimento,8);
			return (strCodigo.substring(0,1)+ "." + strCodigo.substring(1,3) + "." + strCodigo.substring(3,5) + "." +
					strCodigo.substring(5,7)+ "-" + strCodigo.substring(7,8));
		}
		return "";
	}
	
	/**
	 * Método usado para formatar um Multiplicador com casas decimais variadas
	 * @param valor Double ou Double
	 * @return Valor formatado
	 * @author Lívia Paes
	 */
	public static String formataMultiplicador(Object valor){

		DecimalFormat decimal = new DecimalFormat();
		decimal.setMinimumFractionDigits(3);
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setGroupingSeparator('.');
		simbolos.setDecimalSeparator(',');
		decimal.setDecimalFormatSymbols(simbolos);

		if(valor instanceof Double){
			Double obj = (Double) valor;
			return decimal.format(obj);
		}

		return null;

	}
	
	/**
	 * Método que formata com a mascara tipo 1 ( 0.00.00.00-0 )
	 * @param codigo
	 * @return código formatado
	 * @author Livia Paes / Jaime Morais
	 */
	public static String formataMascaraTipo1(Long codigo){
		if (codigo!=null){
			String strCodigo = formatNumber(codigo,8);
			return (strCodigo.substring(0,1)+ "." + strCodigo.substring(1,3) + "." + strCodigo.substring(3,5) + "." +
					strCodigo.substring(5,7)+ "-" + strCodigo.substring(7,8));
		}
		return "";
	}
	
	
	/**
	 * retorna o ano/mes Formatado 
	 *   Ex: 200812 -> 12/2008
	 * @param anoMes
	 * @return
	 */
	public static final String formataAnoMes(Long anoMes) {   
		return  FormatUtils.formatNumber(anoMes%100, 2) + "/" + Long.toString(anoMes/100);
	}
	
	
	/**
	 * Método usado para formatar campo moeda com 4 casas decimais 
	 * @param valor Double ou Double
	 * @return Valor formatado
	 * @author Lívia Paes
	 */
	public static String formataMoeda4Decimais(Object valor){

		DecimalFormat decimal = new DecimalFormat();
		decimal.setMinimumFractionDigits(4);
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setGroupingSeparator('.');
		simbolos.setDecimalSeparator(',');
		decimal.setDecimalFormatSymbols(simbolos);

		if(valor instanceof Double){
			Double obj = (Double) valor;
			return decimal.format(obj);
		}

		return null;

	}

	public static String formataMoeda8Decimais(Object valor){

		DecimalFormat decimal = new DecimalFormat();
		decimal.setMinimumFractionDigits(8);
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setGroupingSeparator('.');
		simbolos.setDecimalSeparator(',');
		decimal.setDecimalFormatSymbols(simbolos);

		if(valor instanceof Double){
			Double obj = (Double) valor;
			return decimal.format(obj);
		}

		return null;

	}
	
	public static String retiraCaracteresEspeciaisSubstituiPorEspaco(String str) {
		if (str!=null && !str.equals("")){
			String regex = "[^A-Za-z0-9\\,\\s]";
			str = str.replaceAll(regex," ");
		}
		return str;
	}
	
	public static String retiraEspacosEmExcesso(String str) {
		return str.replaceAll("\\s+", " ");
	}
	
	public static String formataInteiroComSeparadorMilhar(Integer valor) {		
		NumberFormat nfi = NumberFormat.getIntegerInstance();
		return nfi.format(valor);		
	}


}
