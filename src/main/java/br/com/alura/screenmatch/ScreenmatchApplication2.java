package br.com.alura.screenmatch;

import br.com.alura.screenmatch.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication2 implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication2.class, args);
	}

	public static boolean isPrimo(int i){
		for(int j=i-1;j>1;j--){
			if(i%j==0){
				return false;
			}
		}
		return true;
	}
	@Override
	public void run(String... args) throws Exception {

		//chapter 3, exercise 1
		//List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
		// código para filtragem de pares somente
		//numeros.stream().filter(numero -> numero % 2 == 0).forEach(System.out::println);

		//chapter 3, exercise 2
		//List<String> palavras = Arrays.asList("java", "stream", "lambda");
		// código para conversão
		//palavras.stream().forEach(palavra-> System.out.println(palavra.toUpperCase()));

		//chapter 3, exercise 3
		//List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
		//// código para filtragem e coleta
		//var result =
		//		numeros.stream().filter(numero -> numero % 2 != 0).map(numero -> numero * 2).collect(Collectors.toList());
		//result.forEach(System.out::println);

		//chapter 3, exercise 4
		//List<String> palavras = Arrays.asList("apple", "banana", "apple", "orange", "banana");
		// código da filtragem
		//var result = Set.copyOf(palavras);
		//result.stream().forEach(System.out::println);

		//chapter 3, exercise 5
		//List<List<Integer>> listaDeNumeros = Arrays.asList(
		//		Arrays.asList(1, 2, 3, 4),
		//		Arrays.asList(5, 6, 7, 8),
		//		Arrays.asList(9, 10, 11, 12)
		//);
		//var result = 	listaDeNumeros.stream().flatMap(List::stream).filter(c->isPrimo(c)).collect(Collectors.toList());
		//result.forEach(System.out::println);

		//chapter 3, exercise 6
		//List<Pessoa> pessoas = Arrays.asList(
		//		new Pessoa("Alice", 22),
		//		new Pessoa("Bob", 17),
		//		new Pessoa("Charlie", 19)
		//);

		//var result = pessoas.stream().filter(p -> p.getIdade()>18).sorted(Comparator.comparing(Pessoa::getNome)).collect(Collectors.toList());
		//result.forEach(System.out::println);

		//chapter 3, exercise 7
		// List<Produto> produtos = Arrays.asList(
		//		new Produto("Smartphone", 800.0, "Eletrônicos"),
		//		new Produto("Notebook", 1500.0, "Eletrônicos"),
		//		new Produto("Teclado", 200.0, "Eletrônicos"),
		//		new Produto("Cadeira", 300.0, "Móveis"),
		//		new Produto("Monitor", 900.0, "Eletrônicos"),
		//		new Produto("Mesa", 700.0, "Móveis")
		//);

		//var result = produtos.stream().filter(c->c.getCategoria().equalsIgnoreCase("Eletrônicos") && c.getPreco()<1000).sorted(Comparator.comparing(Produto::getNome)).collect(Collectors.toList());
		//result.forEach(System.out::println);

		//chapter 3, exercise 8
		//List<Produto> produtos = Arrays.asList(
		//		new Produto("Smartphone", 800.0, "Eletrônicos"),
		//		new Produto("Notebook", 1500.0, "Eletrônicos"),
		//		new Produto("Teclado", 200.0, "Eletrônicos"),
		//		new Produto("Cadeira", 300.0, "Móveis"),
		//		new Produto("Monitor", 900.0, "Eletrônicos"),
		//		new Produto("Mesa", 700.0, "Móveis")
		//);

		//var result = produtos.stream()
		//		.filter(c->c.getCategoria().equalsIgnoreCase("Eletrônicos"))
		//		.sorted(Comparator.comparing(Produto::getPreco)).limit(3)
		//		.sorted(Comparator.comparing(Produto::getNome))
		//		.collect(Collectors.toList());
		//result.forEach(System.out::println);

		Principal principal = new Principal();
		principal.exibeMenu();
	}
}
