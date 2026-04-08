package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    public void exercisesChapter5(){
        //exercicios cap 5

        //1 - Dada a lista de números inteiros a seguir, encontre o maior número dela.
//        List<Integer> numeros = Arrays.asList(10, 20, 30, 40, 50);
//        System.out.println("Summing:"+numeros);
//        var result = numeros.stream()
//                .peek(numero -> System.out.println(numero))
//                .collect(Collectors.summarizingInt(e->e));
//        System.out.println("result:"+result.getSum());

        //2 -Dada a lista de palavras (strings) abaixo, agrupe-as pelo seu tamanho.
        // No código a seguir, há um exemplo prático do resultado esperado.
//        List<String> palavras = Arrays.asList("java", "stream", "lambda", "code");
//        // código do agrupamento
//        var result = palavras.stream().collect(Collectors.groupingBy(String::length));
//        System.out.println(result);
//        // Resultado Esperado: {4=[java, code], 6=[stream, lambda]}

        //3 - Dada a lista de nomes abaixo, concatene-os separados por vírgula.
        // No código a seguir, há um exemplo prático do resultado esperado.
//        List<String> nomes = Arrays.asList("Alice", "Bob", "Charlie");
//        var result= nomes.stream().collect(Collectors.joining(","));
//        System.out.println("Nomes: " + result);

        //4 - Dada a lista de números inteiros abaixo,
        // calcule a soma dos quadrados dos números pares.
//        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
//        // código da filtragem e agrupamento dos dados
//        var result = numeros.stream()
//                .filter(numero -> numero % 2 == 0)
//                .peek(e->System.out.println(e+"*"+e+"="+e*e))
//                .collect(Collectors.summarizingInt(e->e*e));
//        System.out.println(result);

        //5 - Dada uma lista de números inteiros,
        // separe os números pares dos ímpares.
//        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
//        // código do particionamento da lista
//        var pares = numeros.stream().filter(numero -> numero % 2 == 0).collect(Collectors.toList());
//        var impares = numeros.stream().filter(numero -> numero % 2 != 0).collect(Collectors.toList());
//        System.out.println("pares:"+pares.toString());
//        System.out.println("ímpares:"+impares.toString());

        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Teclado", 200.0, "Eletrônicos"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Monitor", 900.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis")
        );
        System.out.println("Dada a seguinte lista de produtos:"+produtos+"\n");
        //6 - Dada a lista de produtos acima, agrupe-os por categoria em um Map<String, List<Produto>>.

        //Map <String, <List<Produto>>
        Map<String, List<Produto> > result6 = produtos.stream().collect(Collectors.groupingBy(Produto::getCategoria));
        System.out.println("6 - Produtos: " + result6.toString());

//        7 - Dada a lista de produtos acima, conte quantos produtos há em cada categoria e armazene em um Map<String, Long>.
        Map<String, Long > result7 = produtos.stream().collect(Collectors.groupingBy(Produto::getCategoria, Collectors.counting()));
        System.out.println("7 - Produtos por categoria: " + result7.toString());
//
//        8 - Dada a lista de produtos acima, encontre o produto mais caro de cada categoria e armazene o resultado em um Map<String, Optional<Produto>>.

        Map<String, Optional<Produto> > result8 = produtos.
                stream().
                collect(Collectors
                        .groupingBy(
                                e->e.getCategoria(),
                                Collectors.maxBy(Comparator.comparing(e->e.getPreco() ) ) ) );
        System.out.println("8 - Produtos por categoria: " + result8.toString());

//
//        9 - Dada a lista de produtos acima, calcule o total dos preços dos produtos
//         em cada categoria e armazene o resultado em um Map<String, Double>.

        Map<String, Double> reult9 = produtos
                .stream()
                .collect(Collectors.groupingBy(e->e.getCategoria(), Collectors.summingDouble(e->e.getPreco())));
        System.out.println("9 - Soma de preço por categoria: " + reult9.toString());

    }
    public void executeExercisesChapter5(){
        exercisesChapter5();
        System.exit(0);
    }
    public void exibeMenu(){

        executeExercisesChapter5();

        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i<=dados.totalTemporadas(); i++){
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +"&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        //temporadas.forEach(System.out::println);

        //temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());



//        System.out.println("\nTop 5 episódios");
//        dadosEpisodios.stream()
//                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
//                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
//                .limit(5)
//                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d)))
                .collect(Collectors.toList());

        //imprimindo todos episodios
        episodios.forEach(System.out::println);

//        System.out.println("Busque por um nome de episodio (primneiro que encontrar):");
//        String nome = leitura.nextLine();
//
//        var result =    episodios.stream().filter(e->e.getTitulo().toUpperCase().contains(nome.toUpperCase())).findFirst();
//
//        if (result.isPresent()){
//            System.out.println("Episodio encontrado:");
//            System.out.println(result.get());
//        }else{
//            System.out.println("Nada encontrado");
//        }

        //fazendo a media de avaliaçcao por temporada
        Map<Integer, Double> mapaMedias = episodios.stream()
                .filter(e ->e.getAvaliacao()>0)
                .collect(Collectors.groupingBy(
                        e ->e.getTemporada(), Collectors.averagingDouble(e->e.getAvaliacao())
                ));

        Map<Integer, DoubleSummaryStatistics > mapaSummaryMediasByTemporada = episodios.stream()
                .filter(e ->e.getAvaliacao()>0)
                .collect(Collectors.groupingBy(
                        e->e.getTemporada(),
                        Collectors.summarizingDouble(e->e.getAvaliacao())));

        System.out.println("Medias de avaliação (por temporada):"+mapaSummaryMediasByTemporada);

        DoubleSummaryStatistics estatisticasDeEpisodios = episodios.stream()
                .filter(e ->e.getAvaliacao()>0)
                .collect(
                        Collectors.summarizingDouble(e->e.getAvaliacao()));

        System.out.println("Medias de avaliação (total):"+estatisticasDeEpisodios);


//        episodios.forEach(System.out::println);
//
//        System.out.println("A partir de que ano você deseja ver os episódios? ");
//        var ano = leitura.nextInt();
//        leitura.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodios.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                        "Temporada:  " + e.getTemporada() +
//                                " Episódio: " + e.getTitulo() +
//                                " Data lançamento: " + e.getDataLancamento().format(formatador)
//                ));




    }
}