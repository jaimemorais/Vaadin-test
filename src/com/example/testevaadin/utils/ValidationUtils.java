package com.example.testevaadin.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidationUtils {
	private ValidationUtils() {}

	private static final String MASCARA_CPF = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";
	private static final String MASCARA_CNPJ = "^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$";
	//private static final String VALIDACAO_TEXTO = "[^a-zA-Z\\s]";
	private static final String VALIDACAO_TEXTO = "[^\\x20-\\x2F\\x3A-\\x7E]";
	//private static final String VALIDACAO_TEXTO_NUMERO = "[^a-zA-Z0-9\\s]";
	private static final String VALIDACAO_TEXTO_NUMERO = "[^\\x20-\\x7E]";
	//private static final String VALIDACAO_TEXTO_NUMERO_SEM_ESPACO = "[^a-zA-Z0-9]";
	private static final String VALIDACAO_TEXTO_NUMERO_SEM_ESPACO = "[^\\x21-\\x7E]";
	private static final String VALIDACAO_NUMERO = "[^0-9]";
	private static final String MASCARA_CEP = "^\\d{5}\\-\\d{3}$";
	private static final String MASCARA_DATA = "^\\d{2}\\/\\d{2}/\\d{4}$";
	private static final String MASCARA_MES_ANO = "^\\d{2}\\/\\d{4}$";
	private static final String MASCARA_PERCENTUAL = "^\\d{1,3},\\d{2}$";
	private static final String MASCARA_BOOLEAN = "TRUE|FALSE";
	private static final String MASCARA_TELEFONE = "(^\\d{2}\\)\\d{4}-\\d{4}$";
	

	/**
	 * Função que a partir do tamanho da String passada,
	 * passa a resposabilidade para o método específico.
	 * @see ValidationUtils.validaCPF
	 * @see ValidationUtils.validaCNPJ
	 * @return true se o CPF or CNPJ é válido e false se for válido
	 * @param cpfOrCnpj número do CPF ou CNPJ a ser validado
	 * @version 0.1 Beta
	 */
	public static boolean validaCPForCNPJ (String cpfOrCnpj ){
		if (cpfOrCnpj==null){
			return false;
		}
		cpfOrCnpj = FormatUtils.retiraCaractereNaoNumericos(cpfOrCnpj); //Retira a máscara
		if (cpfOrCnpj.length()==11){ //Tamanho válido do CPF
			return validaCPF(cpfOrCnpj);
		}else if (cpfOrCnpj.length()==14){ //Tamanho válido do CNPJ
			return validaCNPJ(cpfOrCnpj);
		}
		return false;
	}

	/**
	 * Função retirada no site http://www.gurj.net/GRUPO/Artigos/ArtigosGURJ/92.aspx
	 * Função convertida de JavaScript para Java
	 * @return true se o CPF é válido e false se não é válido
	 * @param cpf número de CPF a ser validado
	 * @version 1.0 (testada)
	 */
	public static boolean validaCPF (String cpf ){
		if (cpf==null){
			return false;
		}
		if (cpf.length()!=11){
			cpf = FormatUtils.retiraCaractereNaoNumericos(cpf);
			if (cpf.length()!=11){
				return false;
			}
		}

		/*Verifica se TODOS os dígitos são repetidos*/
		boolean digitosRepetidos = true;
		for(int i = 1; i < 11; i++) {
			if(cpf.charAt(i) != cpf.charAt(0)){
				digitosRepetidos=false;
				break;
			}
		}
		if(digitosRepetidos){
			return false;
		}

		String c = cpf.substring(0,9);
		String dv = cpf.substring(9,11);
		int d1 = 0;
		for (int i = 0; i < 9; i++) {
			d1 += Integer.parseInt(""+c.charAt(i))*(10-i);
		}
		if (d1 == 0){
			return false;
		}        
		d1 = 11 - (d1 % 11);
		if (d1 > 9) d1 = 0;        
		if (Integer.parseInt(""+dv.charAt(0)) != d1) {
			return false;        
		}
		d1 *= 2;
		for (int i = 0; i < 9; i++) {
			d1 += Integer.parseInt(""+c.charAt(i))*(11-i);
		}
		d1 = 11 - (d1 % 11);
		if (d1 > 9) d1 = 0;
		if (Integer.parseInt(""+dv.charAt(1)) != d1) {
			return false;
		}
		return true;		
	}


	/**
	 * Função que valida a máscara da data.
	 * @param matricula
	 * @return true/false
	 */
	public static boolean validaMascaraData (String strDate){
		if (strDate==null || strDate.trim().equals("")){
			return false;
		}
		return strDate.matches(MASCARA_DATA);
	}

	/**
	 * Função que valida a máscara de mes/ano.
	 * @param matricula
	 * @return true/false
	 */
	public static boolean validaMascaraMesAno (String strMesAno){
		if (strMesAno==null || strMesAno.trim().equals("")){
			return false;
		}
		return strMesAno.matches(MASCARA_MES_ANO);
	}

	/**
	 * Função que valida a máscara de campo percentual
	 * @param matricula
	 * @return true/false
	 */
	public static boolean validaMascaraPercentual (String str){
		if (str==null || str.trim().equals("")){
			return false;
		}
		return str.matches(MASCARA_PERCENTUAL);
	}

	/**
	 * Função que valida se a data é válida
	 * @param matricula
	 * @return true/false
	 * @author R. Terra
	 */
	public static boolean validaData (String strDate, String pattern){
		if(strDate == null || strDate.trim().equals("")){
			return false;
		}
		DateFormat df = new SimpleDateFormat(pattern);
		df.setLenient(false);
		try {
			df.parse(strDate);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * Função que valida se o mes ano eh valido é válido
	 * @param mes/ano
	 * @return true/false
	 * @author Breno Lima
	 */
	public static boolean validaMesAno (String strMesAno){
		if(strMesAno == null || strMesAno.trim().equals("")){
			return false;
		}
		if(strMesAno.length()!=7)
			return false;
		else{
			String mes = strMesAno.substring(0, 2);
			if(validaNumero(mes)){
				if(Long.valueOf(mes).compareTo(Long.valueOf("12")) > 0 || Long.valueOf(mes).compareTo(Long.valueOf("0")) != 1)
					return false;
			}else
				return false;
			String ano = strMesAno.substring(3,7);
			if(!validaNumero(ano))
				return false;
			else{
				if(Long.valueOf(ano).compareTo(Long.valueOf("0")) != 1)
					return false;
			}
		}
		return true;
	}

	/**
	 * Função que valida se o mes ano são superiores ou iguais ao mes e ano da data atual
	 * @param mes/ano
	 * @return true/false
	 * @author Breno Lima
	 */
	public static boolean verificaMesAnoMaiorOuIgualDataAtual(String strMesAno) 
	{
		String ano = strMesAno.trim().substring(3, 7);
		Integer anoAtual = DateUtils.retornaAno(DateUtils.retornaDataAtual());
		if (Long.valueOf(ano).compareTo(Long.valueOf(anoAtual)) == -1)
			return false;

		String mes = strMesAno.trim().substring(0, 2);
		Integer mesAtual = DateUtils.retornaMes(DateUtils.retornaDataAtual());
		if (Long.valueOf(mes).compareTo(Long.valueOf(mesAtual)) == -1 && Long.valueOf(ano).compareTo(Long.valueOf(anoAtual)) == 0)
			return false;

		return true;
	}



	/**
	 * Função que valida a máscara do CEP.
	 * @param matricula
	 * @return true/false
	 */
	public static boolean validaMascaraCEP(String cep){
		if (cep==null){
			return false;
		}
		return cep.matches(MASCARA_CEP);
	}

	/**
	 * Função que valida a máscara de um campo Boolean.
	 * @param str
	 * @return true/false
	 * @author R. Terra
	 */
	public static boolean validaBoolean(String str){
		if (str==null || str.trim().equals("")){
			return false;
		}
		return str.matches(MASCARA_BOOLEAN);
	}

	/**
	 * Função que valida se a String contem valor Booleano
	 * @param matricula
	 * @return true/false
	 */
	public static boolean validaCampoBoolean(String strBool){
		try{
			Boolean.valueOf(strBool);
		}catch(Throwable t){
			return false;
		}
		return true;
	}

	/**
	 * Função retirada no site http://www.gurj.net/GRUPO/Artigos/ArtigosGURJ/92.aspx
	 * Função convertida de JavaScript para Java
	 * @return true se o CNPJ é válido e false se não é válido
	 * @param cpf número de CNPJ a ser validado
	 * @author Ricardo Terra
	 * @version 1.0 Testada
	 */
	public static boolean validaCNPJ (String cnpj ){
		if (cnpj==null){
			return false;
		}
		if (cnpj.length()!=14){
			cnpj = FormatUtils.retiraCaractereNaoNumericos(cnpj);
			if (cnpj.length()!=14) {
				return false;
			}
		}
		String c = cnpj.substring(0,12);
		String dv = cnpj.substring(12,14);
		int d1 = 0;
		for (int i = 0; i <12; i++){
			d1 += Integer.parseInt(""+c.charAt(11-i))*(2+(i % 8));
		}
		if (d1 == 0){
			return false;
		}
		d1 = 11 - (d1 % 11);
		if (d1 > 9) d1 = 0;
		if (Integer.parseInt(""+dv.charAt(0)) != d1){
			return false;
		}
		d1 *= 2;
		for (int i = 0; i < 12; i++){
			d1 += Integer.parseInt(""+c.charAt(11-i))*(2+((i+1) % 8));
		}
		d1 = 11 - (d1 % 11);
		if (d1 > 9){
			d1 = 0;
		}
		if (Integer.parseInt(""+dv.charAt(1)) != d1){
			return false;
		}
		return true;
	}

	/**
	 * Função que valida a máscara de um CPF no seguinte formato: 000.000.000-00
	 * @return true se a máscara do CPF é válida e false se não é válida
	 * @param cpf campo do CPF a ser validado
	 * @author R. Terra
	 */
	public static boolean validaMascaraCPF (String cpf ){
		return (cpf!=null) ? cpf.matches(MASCARA_CPF) : false;
	}

	/**
	 * Função que valida a máscara de um CNPJ no seguinte formato: 00.000.000/0000-00
	 * @return true se a máscara do CNPJ é válida e false se não é válida
	 * @param cnpj campo do CNPJ a ser validado
	 * @author Breno Lima
	 */
	public static boolean validaMascaraCNPJ (String cnpj ){
		return (cnpj!=null) ? cnpj.matches(MASCARA_CNPJ) : false;
	}

	/**
	 *  Função que valida a máscara de um telefone no seguinte formato: (00)0000-00
	 * @return true se a máscara do Telefone é válida e false se não é válida
	 * @param telefone campo do Telefone a ser validado
	 * @author Raquel Lopes
	 */
	public static boolean validaMascaraTelefone (String telefone ){
		return (telefone!=null) ? telefone.matches(MASCARA_TELEFONE) : false;
	}

	/**
	 * Método genérico para validar se um certo campo (ou certa String qualquer)
	 * é não é nula e não está vazia.
	 * @param campo String a ser validada
	 * @return true/false
	 * @author R. Terra
	 */
	public static final boolean validaCampo(String campo){
		return campo!=null && !campo.trim().equals(""); 		
	}

	public static final boolean validaCampo(Double campo)
	{
		return campo != null && !campo.equals("");
	}


	/**
	 * Método genérico para validar se um certo campo é diferente de null
	 * ou se for String, ser diferente de null e vazio.
	 * @param obj Objeto a ser validada
	 * @return true/false
	 * @author R. Terra
	 */
	public static final boolean validaObjeto(Object obj){
		if (obj instanceof String){
			return obj!=null && !((String)obj).trim().equals("");	
		}
		return obj!=null; 		
	}




	/**
	 * Método genérico para validar se um certo campo possui somente caracteres alfabeticos.
	 * @param campo String a ser validada
	 * @return true/false
	 * @author Guilherme Esteves
	 */
	public static boolean validaTexto(final String campo) {
		if (campo ==null){
			return false;
		}
		Pattern p = Pattern.compile(VALIDACAO_TEXTO);
		Matcher m = p.matcher(campo);
		if (m.find()) {
			return false;
		}
		return true;
	}

	/**
	 * Método genérico para validar se um certo campo possui somente caracteres numéricos.
	 * é não é nula ou vazia.
	 * @param campo String a ser validada
	 * @return true/false
	 * @author Guilherme Esteves
	 */
	public static boolean validaNumero(final String campo) {
		if (campo == null || campo.trim().length() == 0){
			return false;
		}
		Pattern p = Pattern.compile(VALIDACAO_NUMERO);
		Matcher m = p.matcher(campo);
		if (m.find()) {
			return false;
		}
		return true;
	}

	/**
	 * Método genérico para validar se um certo campo possui somente caracteres alfaNuméricos e espacos.
	 * é não é nula e não está vazia.
	 * @param campo String a ser validada
	 * @return true/false
	 * @author Guilherme Esteves
	 */
	public static boolean validaTextoNumero(final String campo) {
		if (campo == null){
			return false;
		}
		Pattern p = Pattern.compile(VALIDACAO_TEXTO_NUMERO);
		Matcher m = p.matcher(campo);
		if (m.find()) {
			return false;
		}
		return true;
	}

	/**
	 * Método genérico para validar se um certo campo possui somente caracteres alfaNuméricos (com exceção de espaços).
	 * @param campo String a ser validada
	 * @return true/false
	 * @author R. Terra
	 */
	public static boolean validaTextoNumeroSemEspaco(final String campo){
		if (campo == null){
			return false;
		}
		Pattern p = Pattern.compile(VALIDACAO_TEXTO_NUMERO_SEM_ESPACO);
		Matcher m = p.matcher(campo);
		if (m.find()) {
			return false;
		}
		return true;
	}


	/**
	 * Verifica se algum campo foi preenchido
	 * @param listaCampo Lista de campos a serem validados
	 * @return true/false
	 * @author R. Terra
	 */
	public static Boolean campoFormularioPreenchido(String... listaCampo){
		for (String campo : listaCampo){
			if (validaCampo(campo)){
				return true;
			}
		}
		return false;
	}

	

}
