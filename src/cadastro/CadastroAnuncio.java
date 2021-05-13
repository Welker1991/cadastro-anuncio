package cadastro;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

public class CadastroAnuncio {

	public static void main(String[] args) throws ParseException {

		CadastroAnuncio cadastro = new CadastroAnuncio();
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		Scanner scanner = new Scanner(System.in);

		imprimirTraco();

		System.out.print("Nome do anúncio: ");
		String anuncio = scanner.nextLine();

		System.out.print("Cliente: ");
		String cliente = scanner.nextLine();

		imprimirTraco();

		System.out.print("Data inicio: ");
		String dataInicio = scanner.nextLine();

		System.out.print("Total de Dias anuncio: ");
		Integer totDiaAnuncio = scanner.nextInt();

		Calendar gc = cadastro.toDate(dataInicio);

		gc.add((GregorianCalendar.DAY_OF_MONTH), totDiaAnuncio);

		System.out.println("Data de Término: " + df.format(gc.getTime()));

		imprimirTraco();

		System.out.print("Digite o valor a ser investido: R$ ");
		BigDecimal valorInvestido = scanner.nextBigDecimal();

		imprimirTraco();

		System.out.println(cadastro.calcular(valorInvestido, totDiaAnuncio));

		scanner.close();

	}

	public Calendar toDate(String dataInicio) {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

		try {
			cal.setTime(sdf.parse(dataInicio));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return cal;

	}

	static void imprimirTraco() {

		System.out.println("----------------------------------");
	}

	public String calcular(BigDecimal valorInvestido, Integer totDiaAnuncio) {

		int i = totDiaAnuncio;
		BigDecimal temp = new BigDecimal(i);
		
		
		BigDecimal baseVisualizacao = new BigDecimal(166.0);

		BigDecimal baseCliques = new BigDecimal(20.0);

		BigDecimal baseCompartilhamento = new BigDecimal(3.0);

		BigDecimal visualizacaoPorInvestimento = new BigDecimal(30.0);

		BigDecimal adicionalVisualizacao = new BigDecimal(40.0);

		BigDecimal mediaVisualizacao = baseVisualizacao.divide(baseCliques);

		BigDecimal mediaCompartilhamento = baseCliques.divide(baseCompartilhamento, 7, RoundingMode.DOWN);

		BigDecimal calculoVisualizacao = valorInvestido.multiply(visualizacaoPorInvestimento);
		BigDecimal calculoCliques = calculoVisualizacao.divide(mediaVisualizacao, 0, RoundingMode.DOWN);
		BigDecimal calculoCompartilhamento = calculoCliques.divide(mediaCompartilhamento, 0, RoundingMode.DOWN);

		BigDecimal calculoVisualizacao1 = calculoCompartilhamento.multiply(adicionalVisualizacao);
		BigDecimal calculoCliques1 = calculoVisualizacao1.divide(mediaVisualizacao, 0, RoundingMode.DOWN);
		BigDecimal calculoCompartilhamento1 = calculoCliques1.divide(mediaCompartilhamento, 0, RoundingMode.DOWN);

		BigDecimal calculoVisualizacao2 = calculoCompartilhamento1.multiply(adicionalVisualizacao);
		BigDecimal calculoCliques2 = calculoVisualizacao2.divide(mediaVisualizacao, 0, RoundingMode.DOWN);
		BigDecimal calculoCompartilhamento2 = calculoCliques2.divide(mediaCompartilhamento, 0, RoundingMode.DOWN);

		BigDecimal calculoVisualizacao3 = calculoCompartilhamento2.multiply(adicionalVisualizacao);
		BigDecimal calculoCliques3 = calculoVisualizacao3.divide(mediaVisualizacao, 0, RoundingMode.DOWN);
		BigDecimal calculoCompartilhamento3 = calculoCliques3.divide(mediaCompartilhamento, 0, RoundingMode.DOWN);

		BigDecimal calculoVisualizacao4 = calculoCompartilhamento3.multiply(adicionalVisualizacao);

		BigDecimal totalVisualizacao = calculoVisualizacao.add(calculoVisualizacao1).add(calculoVisualizacao2)
				.add(calculoVisualizacao3).add(calculoVisualizacao4);

		BigDecimal totalCliques = calculoCliques.add(calculoCliques1).add(calculoCliques2).add(calculoCliques3);

		BigDecimal totalCompartilhamento = calculoCompartilhamento.add(calculoCompartilhamento1)
				.add(calculoCompartilhamento2).add(calculoCompartilhamento3);

		BigDecimal totalGeralVisualizacao = totalVisualizacao.multiply(temp);
		
		BigDecimal totalGeralCliques = totalCliques.multiply(temp);
		
		BigDecimal totalGeralCompartilhamento = totalCompartilhamento.multiply(temp);
		
		

		String resultadoViews = "Total views por dia: " + totalVisualizacao.toString() + "\n"
				+ "Total de cliques por dia: " + totalCliques.toString() + "\n" + "Total de Compartilhamento por dia: "
				+ totalCompartilhamento + "\n" + "----------------------------------" + "\n" + "Total geral de views: "
				+ totalGeralVisualizacao + "\n" + "Total geral de cliques: " + totalGeralCliques + "\n"
				+ "Total geral de compartilhamento: " + totalGeralCompartilhamento;

		return resultadoViews;

	}

}
