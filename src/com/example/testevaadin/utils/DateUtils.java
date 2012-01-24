package com.example.testevaadin.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.xml.datatype.XMLGregorianCalendar;


public class DateUtils {
	
	public static String PATTERN_DATE_DIA_MES_ANO = "dd/MM/yyyy";
	
	public static final long millisecondsPerDay = 86400000L;			// 24 * 60 * 60 * 1000;
	public static final long millisecondsPerHour = 3600000L;			// 60 * 60 * 1000
	public static final long millisecondsPerMinute = 60000L;			// 60 * 1000
	public static final long milliseconds = 1000;

	private DateUtils() {
	}

	/**
	 * Método que retorna uma data sem horas, minutos, segundos e milisegundos.
	 * Foi inserido também retirar os milisegundos.
	 * 
	 * @author Eduardo Santos, R. Terra
	 */
	public static Date retirarHHMMSS(Date data) {
		GregorianCalendar dataSemHHMMSS = new GregorianCalendar();
		dataSemHHMMSS.setTime(data);
		dataSemHHMMSS.set(Calendar.HOUR_OF_DAY, 0);
		dataSemHHMMSS.set(Calendar.MINUTE, 0);
		dataSemHHMMSS.set(Calendar.SECOND, 0);
		dataSemHHMMSS.set(Calendar.MILLISECOND, 0);
		return dataSemHHMMSS.getTime();
	}

	/**
	 * Método que retorna uma data sem horas, minutos, segundos e milisegundos.
	 * Foi inserido também retirar os milisegundos.
	 * 
	 * @author Eduardo Santos, R. Terra
	 */
	public static Date retirarSS(Date data) {
		GregorianCalendar dataSemSS = new GregorianCalendar();
		dataSemSS.setTime(data);
		dataSemSS.set(Calendar.SECOND, 0);
		dataSemSS.set(Calendar.MILLISECOND, 0);
		return dataSemSS.getTime();
	}

	/**
	 * Método que retorna uma data com horas, minutos, segundos
	 * a partir de uma data completa informada como parametro
	 * 
	 * @author Lívia Paes
	 */
	public static Date adicionarHHMMSS(Date data, Date dataCompleta) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(dataCompleta);

		GregorianCalendar dataComHHMMSS = new GregorianCalendar();
		dataComHHMMSS.setTime(data);
		dataComHHMMSS.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR));
		dataComHHMMSS.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		dataComHHMMSS.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
		return dataComHHMMSS.getTime();
	}

	/**
	 * Método que converte um tipo Date para String de acordo com o um formato.
	 * 
	 * @param data
	 *            Data a ser convertida
	 * @param pattern
	 *            Padrão a ser passado. Ex.: dd/MM/yyyy HH:mm:ss
	 * @return String formatada
	 * @author R. Terra
	 */
	public static String dateToString(Date data, String pattern) {
		if (pattern == null) {
			pattern = "dd/MM/yyyy";
		}
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(data);
	}


	/**
	 * Método que converte um tipo String para Date de acordo com o um formato.
	 * 
	 * @param strDate
	 *            Data no formato String a ser convertida
	 * @param pattern
	 *            Padrão a ser passado. Ex.: dd/MM/yyyy HH:mm:ss
	 * @return Objeto java.util.Date
	 * @author R. Terra
	 */
	public static Date stringToDate(String strDate, String pattern) {
		if (pattern == null) {
			pattern = "dd/MM/yyyy";
		}
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setLenient(false);
		try {
			return dateFormat.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Verifica se uma data está dentro do intervado de duas datas.
	 * 
	 * @param dataIn
	 *            Data de entrada
	 * @param startDate
	 *            Data de início do período
	 * @param endDate
	 *            Data de término do período
	 * @return true/false
	 * @author R. Terra
	 */
	public static boolean between(Date dateIn, Date startDate, Date endDate) {
		return (!dateIn.before(startDate) && !dateIn.after(endDate));
	}

	/**
	 * Verifica se a data atual está dentro do intervado de duas datas.
	 * 
	 * @param startDate
	 *            Data de início do período
	 * @param endDate
	 *            Data de término do período
	 * @return true/false
	 * @author R. Terra
	 */
	public static boolean nowBetween(Date startDate, Date endDate) {
		Date dateIn = new Date();
		return between(dateIn, startDate, endDate);
	}

	/**
	 * Retorna o ano da data
	 * 
	 * @param data
	 *            Data
	 * @return Ano
	 * @author R. Terra
	 */
	public static int retornaAno(Date data) {
		return retornaCampo(data, Calendar.YEAR);
	}

	/**
	 * Retorna o mês da data.
	 * 
	 * @param data
	 *            Data
	 * @return mês
	 * @author R. Terra
	 */
	public static int retornaMes(Date data) {
		return retornaCampo(data, Calendar.MONTH) + 1;
	}

	public static int retornaHora(Date data) {
		return retornaCampo(data, Calendar.HOUR_OF_DAY);
	}

	public static int retornaMinuto(Date data) {
		return retornaCampo(data, Calendar.MINUTE);
	}

	public static int retornaSegundo(Date data) {
		return retornaCampo(data, Calendar.SECOND);
	}

	/**
	 * Retorna o dia da data.
	 * 
	 * @param data
	 *            Data
	 * @return dia
	 * @author R. Terra
	 */
	public static int retornaDia(Date data) {
		return retornaCampo(data, Calendar.DATE);
	}

	private static int retornaCampo(Date data, Integer campo) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		return cal.get(campo);
	}

	/**
	 * Retorna a data Atual.
	 * 
	 * @return Data Atual
	 * @author Guilherme Esteves
	 */
	public static Date retornaDataAtual() {
		return retirarHHMMSS(new Date());

	}

	/**
	 * Retorna a maior Data.
	 * 
	 * @return date
	 * @author R. Terra
	 */
	public static Date retornaMaiorDataYYYY() {
		GregorianCalendar calendar = new GregorianCalendar(9999,
				Calendar.DECEMBER, 31, 23, 59, 59);
		return calendar.getTime();
	}

	/**
	 * Retorna a diferença das datas em anos.
	 * 
	 * @return diferença
	 * @author Guilherme Esteves
	 */
	public static int retornaDiferencaAnos(Date dtMenor, Date dtMaior) {
		Calendar calendarMenor = GregorianCalendar.getInstance();
		calendarMenor.setTime(dtMenor);
		Calendar calendarMaior = GregorianCalendar.getInstance();
		calendarMaior.setTime(dtMaior);
		return calendarMaior.get(Calendar.YEAR)
		- calendarMenor.get(Calendar.YEAR);
	}

	/**
	 * Retorna a diferença das datas em meses.
	 * 
	 * @return diferença
	 * @author Guilherme Esteves
	 */
	public static int retornaDiferencaMeses(Date dtMenor, Date dtMaior) {
		Calendar calendarMenor = GregorianCalendar.getInstance();
		calendarMenor.setTime(dtMenor);
		Calendar calendarMaior = GregorianCalendar.getInstance();
		calendarMaior.setTime(dtMaior);
		return calendarMaior.get(Calendar.MONTH)
		- calendarMenor.get(Calendar.MONTH) + 1
		+ (retornaDiferencaAnos(dtMenor, dtMaior) * 12);
	}

	/**
	 * Retorna a diferença das datas em dias.
	 * @return diferença
	 * @author Guilherme Esteves
	 */
	public static long retornaDiferencaDias(Date dtMenor, Date dtMaior) {
		return (dtMaior.getTime() - dtMenor.getTime()) / millisecondsPerDay;
	}

	/**
	 * Retorna a diferença das datas em dias em cálculo íntegro. Se a data
	 * inicial é dia 01/fev e a data de término é dia 02/fev, a diferença
	 * integral será de 02 dias, pois ambas as datas devem ser contabilizadas
	 * como um dia completo.
	 * @return Diferença integra de dias entre as datas.
	 * @author R. Terra
	 */
	public static long retornaDiferencaDiasEmCalculoIntegro(Date dtMenor,
			Date dtMaior) {
		return (dtMaior.getTime() - dtMenor.getTime() + millisecondsPerHour) / millisecondsPerDay + 1;
	}

	/**
	 * Retorna a diferença das datas em horas.
	 * 
	 * @return diferença
	 * @author Guilherme Esteves
	 */
	public static long retornaDiferencaHoras(Date dtMenor, Date dtMaior) {
		return (dtMaior.getTime() - dtMenor.getTime()) / millisecondsPerHour;
	}

	/**
	 * Retorna a diferença das datas em minutos.
	 * 
	 * @return diferença
	 * @author Guilherme Esteves
	 */
	public static long retornaDiferencaMinutos(Date dtMenor, Date dtMaior) {
		return (dtMaior.getTime() - dtMenor.getTime()) / millisecondsPerMinute;
	}

	/**
	 * Retorna a diferença das datas em segundos.
	 * 
	 * @return diferença
	 * @author Guilherme Esteves
	 */
	public static long retornaDiferencaSegundos(Date dtMenor, Date dtMaior) {
		return (dtMaior.getTime() - dtMenor.getTime()) / milliseconds;
	}

	/**
	 * Retorna a diferença das datas em milisegundos.
	 * 
	 * @return diferença
	 * @author Guilherme Esteves
	 */
	public static long retornaDiferencaMilisegundos(Date dtMenor, Date dtMaior) {
		return dtMaior.getTime() - dtMenor.getTime();
	}

	/**
	 * Método responsável por adicionar determinada quantidade de dias de uma
	 * data. Caso deseja SUBTRAIR, basta passar a quantidade de dias NEGATIVA.
	 * 
	 * @param date
	 *            Data a ser adicinada
	 * @param dias
	 *            Quantidade de dias a se adicionar
	 * @return Data com a quantidade de dias adicionada.
	 * @author R. Terra
	 */
	public static Date adicionaDias(Date date, Integer dias) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dias);
		return calendar.getTime();
	}

	/**
	 * Método responsável por adicionar determinada quantidade de dias de uma
	 * data. Caso deseja SUBTRAIR, basta passar a quantidade de dias NEGATIVA.
	 * 
	 * @return Data com a quantidade de dias adicionada.
	 * @author R. Terra
	 */
	public static Date adicionaDiasEmCalculoIntegro(Date date, Integer dias) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		if (dias > 0) {
			dias--;
		} else if (dias < 0) {
			dias++;
		}
		calendar.add(Calendar.DATE, dias);
		return calendar.getTime();
	}

	/**
	 * Método que calcula quantos meses tem uma faixa, recebendo como parâmetros
	 * um valor em anos e um valor em meses.
	 * 
	 * @param anos
	 * @param meses
	 * @return
	 * @author Gisnálbert Spínola
	 */
	public static int calculaMesesEAnosParaMeses(String anos, String meses) {
		Integer anosInt = null;
		Integer mesesInt = null;
		if (ValidationUtils.validaCampo(anos)) {
			anosInt = Integer.valueOf(anos);
		} else {
			anosInt = 0;
		}
		if (ValidationUtils.validaCampo(meses)) {
			mesesInt = Integer.valueOf(meses);
		} else {
			mesesInt = 0;
		}
		return (anosInt.intValue() * 12) + (mesesInt.intValue());
	}

	/**
	 * Metodo que retorna um Long contendo o ano e mês
	 * Ex:  200805
	 *      200311
	 *      200009
	 *      
	 * @param data
	 * @return
	 */
	public static long retornaAnoMes(Date data) {
		return Long.valueOf(Integer.toString(DateUtils.retornaAno(data))+FormatUtils.formatNumber(DateUtils.retornaMes(data),2));
	}

	/**
	 * Metodo que retorna uma String contendo mês e ano
	 * @param data
	 * @return
	 * @author Lívia Paes
	 */
	public static String retornaMesAno(Date data) {
		return FormatUtils.formatNumber(DateUtils.retornaMes(data),2) + Integer.toString(DateUtils.retornaAno(data));
	}


	/**
	 * Metodo que retorna uma String contendo mês e ano
	 * Ex: 200805 -> 052008
	 *     200311 -> 112003 
	 *     200009 -> 092000
	 * @param data
	 * @return
	 */
	public static String retornaMesAno(Long anoMes) {
		return FormatUtils.formatNumber(anoMes%100, 2) +"/" + Long.toString(anoMes/100);
	}

	/**
	 * Metodo que retorna um Long contendo o ano mes
	 * Ex:  52008 -> 200805
	 *     112003 -> 200311
	 *     092000 -> 200009
	 *      
	 * @param Long mesAno
	 * @return
	 */
	public static long retornaAnoMes(String mesAno) {
		mesAno = FormatUtils.retiraCaractereNaoNumericos(mesAno);
		return Long.valueOf(mesAno.substring(mesAno.length() - 4) + FormatUtils.formatNumber(Long.valueOf(mesAno.substring(0, (mesAno.length()==6 ? 2:1))), 2));
	}


	/**
	 * Metodo que retorna um Long contendo o ano
	 * Ex: 200805 -> 2008
	 *     200311 -> 2003
	 *     200009 -> 2000
	 *      
	 * @param Long mesAno
	 * @return
	 */
	public static String retornaAno(String ano) {
		ano = FormatUtils.retiraCaractereNaoNumericos(ano);
		return ano.substring(0,4);
	}

	/**
	 * Metodo que retorna um Long contendo o ano mes
	 * Ex: 2008, 5 -> 200805
	 *     1990, 12 -> 200311
	 *      
	 * @param Long mesAno
	 * @return
	 */
	public static long retornaAnoMes(Integer ano, Integer mes) throws Exception{
		if(mes < 1 || mes > 12)
			throw new Exception("Mês Invalido "+mes);
		if(ano < 1900 || ano > 2100)
			throw new Exception("Ano Invalido "+ano);
		return Long.valueOf(ano.toString() + (mes < 10? "0"+mes.toString(): mes.toString()));
	}

	/**
	 * Metodo que retorna um Long contendo o ano e mês atual
	 * Ex:  200805
	 *      200311
	 *      200009
	 */
	public static long retornaAnoMesAtual() {
		Date dataAtual = new Date();
		return retornaAnoMes(dataAtual) ;
	}

	/**
	 * Metodo que retorna um Date a partir de um Long no seguinte formato:
	 *   200805, 200311, 200009. (yyyyMM)
	 */
	public static final Date retornaDate(final long anoMesData) 
	{
		final SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		format.setLenient(false);
		try {
			return format.parse(Long.toString(anoMesData));
		} catch (ParseException pe) {
			return null;
		}
	}


	/**
	 * Verifica o mes e ano de duas datas são iguais
	 * 
	 * @param data
	 * @param outraData
	 * @return retorna true se as datas possuem o mesmo mes e ano, caso
	 *         contrario retorna false
	 * @author Guilherme Esteves
	 */
	public static boolean equalsMesAno(Date data, Date outraData) {
		if (retornaMes(data) == retornaMes(outraData)
				&& retornaAno(data) == retornaAno(outraData)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Verifica se o mes/ano da primeira data é anterior ao mes/ano da segunda
	 * data
	 * 
	 * @param data
	 * @param outraData
	 * @return retorna true se o mes/ano da primeira data é anterior ao mes/ano
	 *         da segunda data, caso contratio retorna false
	 * @author Guilherme Esteves
	 */
	public static boolean lessMesAno(Date data, Date outraData) {
		int anoData = retornaAno(data);
		int mesData = retornaMes(data);
		int anoOutraData = retornaAno(outraData);
		int mesOutraData = retornaMes(outraData);

		if (anoData < anoOutraData) {
			return true;
		} else if (anoData == anoOutraData && mesData < mesOutraData) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Verifica se o primeiro mês do parâmetro é inferior ou igual ao segundo mês
	 * @param mes - primeiro mês
	 * @param outroMes - segundo mês
	 * @return true/false - true se o primeiro mês for inferior ao segundo, caso contrário retorna false
	 * @author Pierre Peres
	 */
	public static boolean mesInferiorOuIgual(int mes, int outroMes) {

		if (mes < outroMes || mes == outroMes) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método que retorna a primeira data do mês
	 * 
	 * @param data
	 * @return primeira data do mês
	 * @author Guilherme Esteves
	 */
	public static Date retornaInicioMes(Date data) {
		Calendar inicioMes = GregorianCalendar.getInstance();
		inicioMes.setTime(data);
		inicioMes.set(Calendar.DAY_OF_MONTH, 1);
		inicioMes.set(Calendar.HOUR_OF_DAY, 0);
		inicioMes.set(Calendar.MINUTE, 0);
		inicioMes.set(Calendar.SECOND, 0);
		inicioMes.set(Calendar.MILLISECOND, 0);
		return inicioMes.getTime();
	}

	/**
	 * Método que retorna a ultima data do mês
	 * 
	 * @param data
	 * @return ulitma data do mês
	 * @author Guilherme Esteves
	 */
	public static Date retornaTerminoMes(Date data) {
		Calendar terminoMes = GregorianCalendar.getInstance();
		terminoMes.setTime(data);
		terminoMes.set(Calendar.DAY_OF_MONTH, terminoMes
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		terminoMes.set(Calendar.HOUR_OF_DAY, 0);
		terminoMes.set(Calendar.MINUTE, 0);
		terminoMes.set(Calendar.SECOND, 0);
		terminoMes.set(Calendar.MILLISECOND, 0);
		return terminoMes.getTime();
	}

	/**
	 * Método que retorna a ultima data do mês com as horas, minutos e segundos
	 * completos
	 * 
	 * @param data
	 * @return ulitma data hora do mês
	 * @author Guilherme Esteves
	 */
	public static Date retornaTerminoMesCompleto(Date data) {
		Calendar terminoMes = GregorianCalendar.getInstance();
		terminoMes.setTime(data);
		terminoMes.set(Calendar.DAY_OF_MONTH, terminoMes
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		terminoMes.set(Calendar.HOUR_OF_DAY, 23);
		terminoMes.set(Calendar.MINUTE, 59);
		terminoMes.set(Calendar.SECOND, 59);
		terminoMes.set(Calendar.MILLISECOND, 999);
		return terminoMes.getTime();
	}


	public static Date adicionaMinutos(Date date, int minutos) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutos);
		return calendar.getTime();
	}
	
	public static Date adicionaSegundos(Date date, int segundos) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, segundos);
		return calendar.getTime();
	}
	
	public static Date adicionaMilissegundos(Date date, int milissegundos) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MILLISECOND, milissegundos);
		return calendar.getTime();
	}
	
	/**
	 * Método responsável por adicionar determinada quantidade de meses em uma
	 * data. Caso deseja SUBTRAIR, basta passar a quantidade de meses NEGATIVA.
	 * 
	 * @param date
	 *            Data a ser adicinada
	 * @param meses
	 *            Quantidade de meses a se adicionar
	 * @return Data com a quantidade de meses adicionada.
	 * @author Raquel Lopes
	 */
	public static Date adicionaMeses(Date date, int meses) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, meses);
		return calendar.getTime();
	}

	/**
	 * Método responsável por adicionar determinada quantidade de meses em uma
	 * data. Caso deseja SUBTRAIR, basta passar a quantidade de meses NEGATIVA.
	 * 
	 * @param date
	 *            Data a ser adicinada
	 * @param anos
	 *            Quantidade de anos a se adicionar
	 * @return Data com a quantidade de meses adicionada.
	 * @author Danniel Nascimento
	 */
	public static Date adicionaAnos(Date date, int anos) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, anos);
		return calendar.getTime();
	}

	/**
	 * Método que retorna a primeira data a ser considerada para o exercicio da
	 * data de competência. O mes será considerado valido se o ultimo dia do mês
	 * menos o dia da data de inicio resultar em um numero maior que 15. Ex:
	 * dataInicio = 18/03/2007 e dataCompetencia = 21/03/2007 retorno =
	 * 01/04/2007 dataInicio = 13/03/2007 e dataCompetencia = 21/03/2007 retorno =
	 * 01/03/2007 dataInicio = 13/10/2006 e dataCompetencia = 21/03/2007 retorno =
	 * 01/01/2007
	 * 
	 * @param dataInico
	 * @param dataCompetencia
	 * @return primeira data considerada
	 * @author Guilherme Esteves
	 */
	public static Date retornaInicioMesConsideradoAno(Date dataInicio,
			Date dataCompetencia) {
		Calendar dataInicioConsiderada = GregorianCalendar.getInstance();
		Calendar dataCompetenciaAvalidada = GregorianCalendar.getInstance();
		dataCompetenciaAvalidada.setTime(dataCompetencia);
		dataInicioConsiderada.setTime(dataInicio);
		if (dataInicioConsiderada.get(Calendar.YEAR) < dataCompetenciaAvalidada
				.get(Calendar.YEAR)) {
			dataInicioConsiderada.set(Calendar.DAY_OF_MONTH, 1);
			dataInicioConsiderada.set(Calendar.MONTH, 1);
			dataInicioConsiderada.set(Calendar.YEAR, dataCompetenciaAvalidada
					.get(Calendar.YEAR));
		} else {
			if (dataInicioConsiderada.getActualMaximum(Calendar.DAY_OF_MONTH)
					- dataInicioConsiderada.get(Calendar.DAY_OF_MONTH) <= 15) {
				dataInicioConsiderada.add(Calendar.MONTH, 1);
				dataInicioConsiderada.set(Calendar.DAY_OF_MONTH, 1);
			} else {
				dataInicioConsiderada.set(Calendar.DAY_OF_MONTH, 1);
			}
		}
		return dataInicioConsiderada.getTime();
	}

	/**
	 * Método que retorna a primeira data a ser considerada para o exercicio da
	 * data de competência. O mes será considerado valido se o ultimo dia do mês
	 * menos o dia da data de inicio resultar em um numero maior que 15. Ex:
	 * dataFim = 18/03/2007 e dataCompetencia = 21/03/2007 retorno = 31/03/2007
	 * dataFim = 13/03/2007 e dataCompetencia = 21/03/2007 retorno = 28/02/2007
	 * dataFim = 13/10/2010 e dataCompetencia = 21/03/2007 retorno = 31/01/2007
	 * 
	 * @param dataInico
	 * @param dataCompetencia
	 * @return ulitma data considerada
	 * @author Guilherme Esteves
	 */
	public static Date retornaFimMesConsideradoAno(Date dataFim,
			Date dataCompetencia) {

		Calendar dataFimConsiderada = GregorianCalendar.getInstance();
		Calendar dataCompetenciaAvalidada = GregorianCalendar.getInstance();
		dataCompetenciaAvalidada.setTime(dataCompetencia);
		if (dataFim == null) {
			dataFimConsiderada.set(Calendar.DAY_OF_MONTH, 31);
			dataFimConsiderada.set(Calendar.MONTH, 12);
			dataFimConsiderada.set(Calendar.YEAR, dataCompetenciaAvalidada
					.get(Calendar.YEAR));
		} else {
			dataFimConsiderada.setTime(dataFim);

			if (dataFimConsiderada.get(Calendar.YEAR) > dataCompetenciaAvalidada
					.get(Calendar.YEAR)) {
				dataFimConsiderada.set(Calendar.DAY_OF_MONTH, 31);
				dataFimConsiderada.set(Calendar.MONTH, 12);
				dataFimConsiderada.set(Calendar.YEAR, dataCompetenciaAvalidada
						.get(Calendar.YEAR));
			} else {
				if (dataFimConsiderada.get(Calendar.DAY_OF_MONTH) > 15) {
					dataFimConsiderada.set(Calendar.DAY_OF_MONTH,
							dataFimConsiderada
							.getActualMaximum(Calendar.DAY_OF_MONTH));
				} else {
					dataFimConsiderada.add(Calendar.MONTH, -1);
					dataFimConsiderada.set(Calendar.DAY_OF_MONTH,
							dataFimConsiderada
							.getActualMaximum(Calendar.DAY_OF_MONTH));
				}
			}
		}
		return dataFimConsiderada.getTime();
	}

	/**
	 * Converte um XMLGregorianCalendar para um Date
	 * 
	 * @param XMLGregorianCalendar
	 * @author Jaime Morais
	 */
	public static Date XMLGregorianCalendarToDate(
			XMLGregorianCalendar dataXMLGregorianCalendar) {
		int ano = dataXMLGregorianCalendar.getYear();
		int mes = dataXMLGregorianCalendar.getMonth();
		int dia = dataXMLGregorianCalendar.getDay();

		String strDate = String.valueOf(dia) + "/" + String.valueOf(mes) + "/"
		+ String.valueOf(ano);

		return stringToDate(strDate, PATTERN_DATE_DIA_MES_ANO);
	}


	/**
	 * Formata hora a partir de argumento em segundos
	 * @param tempoEmSegundos - double em segundos com fracao
	 * @return string como a anos, m meses, d dias, h horas, i minutos e s segundos
	 */
	public static String formatTime(double tempoEmSegundos)	{
		Double tempo = tempoEmSegundos * milliseconds;
		// cuidado para nao chamar o metodo sem converter para long, pois gerara loop infinito
		return formatTime(tempo.longValue());
	}

	/**
	 * Formata hora a partir de argumento.
	 * @param diff - long de tempo em milisegundos
	 * @return string como a anos, m meses, d dias, h horas, i minutos e s segundos
	 */
	public static String formatTime(long diff)	{
		long anos, meses, dias, horas, minutos, segundos;
		// passa diff para segundos
		diff /= milliseconds;
		// calcula os segundos restantes
		segundos = diff % 60;
		diff /= 60;
		// calcula os minutos restantes
		minutos = diff % 60;
		diff /= 60;
		// calcula as horas restantes
		horas = diff % 24;
		diff /= 24;
		// calcula os dias restantes
		dias = diff % 30;
		diff /= 30;
		// calcula os meses restantes
		meses = diff % 12;
		// obtem os anos restantes
		anos = diff / 12;

		// monta a string de retorno
		String strTime = (anos > 0 ? anos + " anos " : "")
		+ (meses > 0 ? meses + " meses " : "")
		+ (dias > 0 ? dias + " dias " : "")
		+ (horas > 0 ? horas + " horas " : "")
		+ (minutos > 0 ? minutos + " minutos " : "")
		+ (segundos > 0 ? segundos + " segundos " : "");
		return strTime.length() > 0 ? strTime : "infimo";
	}

	/**
	 * Retorna string com informacao de tempo decorrido em anos, meses, dias,
	 * horas, minutos e segundos.
	 * 
	 * @param left -
	 *            Tempo atual
	 * @param right -
	 *            Tempo anterior
	 * @return - string de tempo decorrido
	 * @author lma
	 */
	public static String ElapsedTime(Date left, Date right) {
		// calcula a diferenca de tempo em segundos
		long diff = left.getTime() - right.getTime();
		return formatTime(diff);
	}


	/**
	 * Verifica o dia, mes e ano de duas datas são iguais
	 * 
	 * @param data
	 * @param outraData
	 * @return retorna true se as datas possuem o mesmo dia, mes e ano, caso
	 *         contrario retorna false
	 * @author Rogério de Souza
	 */
	public static boolean equalsMesAnoDia(Date data, Date outraData) {
		if (retornaMes(data) == retornaMes(outraData)
				&& retornaAno(data) == retornaAno(outraData)
				&& retornaDia(data) == retornaDia(outraData)) {
			return true;
		} else {
			return false;
		}
	}	

	/**
	 * Método que verifica se a data é a ultimo dia do mês
	 * 
	 * @param data
	 * @return boolean
	 * @author Rogério de Souza
	 */
	public static boolean isUltimoDiaMes(Date data) {		
		Date ultimaDataMes = DateUtils.retornaTerminoMes(data);				
		if(DateUtils.equalsMesAnoDia(data, ultimaDataMes))
			return true;		
		return false;
	}	

	/**
	 * Método que verifica se a data é o primeiro dia do mês
	 * 
	 * @param data
	 * @return boolean
	 * @author Rogério de Souza
	 */
	public static boolean isPrimeiroDiaMes(Date data) {		
		Date primeiraDataMes = DateUtils.retornaInicioMes(data);				
		if(DateUtils.equalsMesAnoDia(data, primeiraDataMes))
			return true;		
		return false;
	}		

	/**
	 * metodo que formata um periodo da seguinte forma "dd/MM/aaaa - dd/MM/aaaa"
	 * @param dataInicio 
	 * @param dataFim 
	 * @return String 
	 * @author Guilherme Esteves
	 */
	public static String formatarPeriodo(Date dataInicio, Date dataFim) {		
		return dateToString(dataInicio, PATTERN_DATE_DIA_MES_ANO) + " - " +  dateToString(dataFim, PATTERN_DATE_DIA_MES_ANO);
	}	



	public static String retornaMesExtenso(Integer mes){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(Calendar.MONTH, mes -1);
		DateFormat df2 = new SimpleDateFormat ("MMMMM", new Locale ("pt", "BR"));  
		return df2.format(calendar.getTime());	
	}


	/**
	 * Atualiza a data informada, somando 1 ano e diminuindo 1 dia 
	 * @param dataAtual
	 * @return data atualizada
	 * @author Raquel Lopes
	 */
	public static Date somaUmAnoSubtraiUmDia(Date data){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.YEAR, 1);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}	


	public static int retornaDiferencaMeses(long competenciaInicial, long competenciaFinal){
		return DateUtils.retornaDiferencaMeses(DateUtils.retornaDate(competenciaInicial), DateUtils.retornaDate(competenciaFinal));
	}

	public static long adicionaMeses(long anoMes, long meses){
		long ano = anoMes/100;
		long mes = anoMes%100;
		ano += meses/12;
		mes += meses%12;
		if(mes > 12){
			ano ++; 
			mes = mes%12;
		}else if (mes < 1){
			ano --; 
			mes = 12+mes;
		}
		return ano*100+mes; 
	}

	public static int retornaIdadePorCompetencia(long competenciaNascimento, long competenciaAtual){
		long anoNascimento = competenciaNascimento/100;
		long mesNascimento = competenciaNascimento%100;
		long anoAtual = competenciaAtual/100;
		long mesAtual = competenciaAtual%100;
		Long idade = (anoAtual - anoNascimento)*12;
		idade += mesAtual - mesNascimento;
		return idade > 0? idade.intValue(): 0;
	}

	public static Collection<Long> retornaCompetenciasPorPeriodo(long anoMesInicio, long anoMesFim){
		Collection<Long> listaCompetencia = new ArrayList<Long>();
		while (anoMesInicio <= anoMesFim){
			listaCompetencia.add(anoMesInicio);
			anoMesInicio = adicionaMeses(anoMesInicio, 1);
		}
		return listaCompetencia;
	}



	public static boolean datasEmIntercessao(
			Date dataInicioA, Date dataTerminoA, Date dataInicioB, Date dataTerminoB) throws Exception {

		if (dataInicioA == null || dataTerminoA == null || dataInicioB == null || dataTerminoB == null) {
			throw new Exception("datasEmIntercessao() : todas as datas parametro devem ser informadas.");
		}

		Date dataInicioIntercessao = null;
		Date dataTerminoIntercessao = null;

		if (dataInicioA.compareTo(dataInicioB) > 0) {
			dataInicioIntercessao = dataInicioA;
		} else {
			dataInicioIntercessao = dataInicioB;
		}

		if (dataTerminoA.compareTo(dataTerminoB) < 0) {
			dataTerminoIntercessao = dataTerminoA;
		} else {
			dataTerminoIntercessao = dataTerminoB;
		}		

		return (retornaDiferencaDiasEmCalculoIntegro(dataInicioIntercessao, dataTerminoIntercessao) > -1);
	}


	public static int retornaQuantidadeDiasIntercessaoEntrePeriodos(
			Date dataInicioA, Date dataTerminoA, Date dataInicioB, Date dataTerminoB) throws Exception {

		long qtd = 0;

		if (dataInicioA == null || dataTerminoA == null || dataInicioB == null || dataTerminoB == null) {
			throw new Exception("retornaQuantidadeDiasIntercessaoEntrePeriodos() : todas as datas parametro devem ser informadas.");
		}

		Date dataInicioIntercessao = null;
		Date dataTerminoIntercessao = null;

		if (dataInicioA.compareTo(dataInicioB) > 0) {
			dataInicioIntercessao = dataInicioA;
		} else {
			dataInicioIntercessao = dataInicioB;
		}

		if (dataTerminoA.compareTo(dataTerminoB) < 0) {
			dataTerminoIntercessao = dataTerminoA;
		} else {
			dataTerminoIntercessao = dataTerminoB;
		}

		qtd = retornaDiferencaDiasEmCalculoIntegro(dataInicioIntercessao, dataTerminoIntercessao);

		if (qtd < 0 ||dataTerminoIntercessao.before(dataInicioIntercessao) ) {
			qtd = 0;
		}

		return (int) qtd;
	}


	public static int calculaIdade(Date dataNascimento, Date dataComparar) {  

		GregorianCalendar agora = new GregorianCalendar();  
		GregorianCalendar nascimento = new GregorianCalendar();

		int ano = 0, mes = 0, dia = 0;  
		int anoNasc = 0, mesNasc = 0, diaNasc = 0;
		int idade = 0;  

		if (dataNascimento != null) {  

			nascimento.setTime(dataNascimento);  
			agora.setTime(dataComparar);

			ano = agora.get(Calendar.YEAR);  
			mes = agora.get(Calendar.MONTH) + 1;  
			dia = agora.get(Calendar.DAY_OF_MONTH);  

			anoNasc = nascimento.get(Calendar.YEAR);  
			mesNasc = nascimento.get(Calendar.MONTH) + 1;  
			diaNasc = nascimento.get(Calendar.DAY_OF_MONTH);  

			idade = ano - anoNasc;  

			// Calcula diferencas de mes e dia
			if (mes < mesNasc) {  
				idade--;  
			} else if(mes == mesNasc){
				if (dia < diaNasc) {  
					idade--;  
				}  
			}	         

			if	(idade < 0) {  
				idade = 0;  
			}  

		}  

		return (idade);
	}  

	public static String retornaMesExtenso(String mes) throws ParseException {		 
		DateFormat df = new SimpleDateFormat ("MM");  
		Date dt = df.parse (mes);  
		DateFormat df2 = new SimpleDateFormat ("MMMMM", new Locale ("pt", "BR"));   
		return df2.format (dt);
	}
	
	
	public static int avosEntrePeriodos(Date dataInicial, Date dataFinal){
		int meses =0;
		Long anoMesFinal = null;
		if(retornaDia(dataInicial) > retornaDia(dataFinal)){
			anoMesFinal = retornaAnoMes(adicionaMeses(dataFinal,-1));
			int fimMesAnterior = retornaDia(retornaTerminoMes(adicionaMeses(dataFinal,-1)));
			if(retornaDia(dataFinal) +(fimMesAnterior- retornaDia(dataInicial))+1 > 14){
				meses = 1;
			}
		}else{
			anoMesFinal = retornaAnoMes(dataFinal);
			if(retornaDia(dataFinal)- retornaDia(dataInicial)+1  > 14){
				meses = 1;
			}
		}	
      	meses += retornaDiferencaMeses(retornaAnoMes(dataInicial), anoMesFinal) -1;
      	return meses;
	}

}
