import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.ArrayList;

public class InterfaceUsuario{

  private Biblioteca biblioteca;


  public InterfaceUsuario(){
    this.biblioteca = new Biblioteca();
  }


  public static int menuOpcoes(){
    Scanner entrada = new Scanner(System.in);
    int opcao;

    System.out.printf("Digite a opcao desejada:\n  (1) - Cadastrar usuario\n  (2) - Cadastrar livro\n  (3) - Cadastrar emprestimo\n  (4) - Inserir item de emprestimo\n  (5) - Excluir usuario\n  (6) - Excluir livro\n  (7) - Excluir emprestimo\n  (8) - Excluir item de emprestimo\n  (9) - Devolver todos os livros do emprestimo\n  (10) - Devolver um livro do emprestimo\n  (11) - Pesquisar livros por titulo\n  (12) - Pesquisar livros por autor\n  (13) - Listar todos os usuarios\n  (14) - Listar todos os livros\n  (15) - Listar todos os emprestimos e seus itens\n\n  (16) - Encerrar programa\n\n");

    do{
      opcao = entrada.nextInt();

      if ((opcao < 1) || (opcao > 16))
        System.out.print("Opcao invalida. Digite novamente: ");
    }while ((opcao < 1) || (opcao > 16));

    return opcao;
  }


  public void cadastrarUsuario(){
    Usuario usuario = new Usuario();
    Scanner entrada = new Scanner(System.in);

    System.out.print("Digite o nome: ");
    usuario.setNome(entrada.nextLine());
    System.out.print("Digite o cpf: ");
    usuario.setCpf(entrada.nextLine());
    System.out.print("Digite o endereco: ");
    usuario.setEndereco(entrada.nextLine());
    System.out.print("Digite o telefone: ");
    usuario.setFone(entrada.nextLine());

    this.biblioteca.inserirUsuario(usuario);
  }


  public void cadastrarLivro(){
    int codLivro, qtdeExemplares;
    String titulo, autores;
    Scanner entrada = new Scanner(System.in);

    System.out.print("Digite o codigo do livro: ");
    codLivro = entrada.nextInt();
    entrada.nextLine(); //limpeza do buffer
    System.out.print("Digite o titulo: ");
    titulo = entrada.nextLine();
    System.out.print("Digite os autores: ");
    autores = entrada.nextLine();
    System.out.print("Digite a quantidade de exemplares: ");
    qtdeExemplares = entrada.nextInt();

    this.biblioteca.inserirLivro(new Livro(codLivro, titulo, autores, qtdeExemplares));
  }


  public boolean cadastrarEmprestimo(){
    Scanner entrada = new Scanner(System.in);
    String nome;
    Usuario meuUsuario = null;

	System.out.println("* Emprestimo numero " + Emprestimo.getProximoNumero());
    System.out.print("Digite o nome do usuario: ");
    nome = entrada.nextLine();

    for (Usuario user : this.biblioteca.getUsuario()){
      if (user.getNome().equalsIgnoreCase(nome)){
        meuUsuario = user;
        break;
      }
    }

    if (meuUsuario != null){
      int dia, mes, ano;

      System.out.println("Data prevista para devolucao:");
      System.out.print("\t- Digite o dia: ");
      dia = entrada.nextInt();
      System.out.print("\t- Digite o mes: ");
      mes = entrada.nextInt();
      System.out.print("\t- Digite o ano: ");
      ano = entrada.nextInt();

      this.biblioteca.inserirEmprestimo(new Emprestimo(new GregorianCalendar(ano, mes-1, dia), meuUsuario));

      return true;
    }

    return false;
  }


  public boolean inserirItemEmprestimo(){
    Scanner entrada = new Scanner(System.in);
    int numero;
    Emprestimo meuEmprestimo = null;

    System.out.print("Digite o numero do emprestimo: ");
    numero = entrada.nextInt();

    for (Emprestimo emprestimo : this.biblioteca.getEmprestimo()){
      if (emprestimo.getNumero() == numero){
        meuEmprestimo = emprestimo;
        break;
      }
    }

    if (meuEmprestimo != null){
      int codLivro;
      Livro meuLivro = null;

      System.out.print("Digite o codigo do livro: ");
      codLivro = entrada.nextInt();

      if (this.biblioteca.getLivro().isEmpty())
        return false;

      for (Livro livro : this.biblioteca.getLivro()){
        if (livro.getCodLivro() == codLivro){
          meuLivro = livro;
          break;
        }
      }

      if (meuLivro != null){
        this.biblioteca.inserirItemEmprestimo(meuEmprestimo, new ItemEmprestimo(meuLivro));

        return true;
      }

      return false;
    }

    return false;
  }


  public boolean excluirUsuario(){
    Scanner entrada = new Scanner(System.in);
    String nome;
    Usuario meuUsuario = null;

    System.out.print("Digite o nome do usuario: ");
    nome = entrada.nextLine();

    if (this.biblioteca.getUsuario().isEmpty())
      return false;

    for (Usuario user : this.biblioteca.getUsuario()){
      if (user.getNome().equalsIgnoreCase(nome)){
        meuUsuario = user;
        break;
      }
    }

    if (meuUsuario != null){
      this.biblioteca.excluirUsuario(meuUsuario);

      return true;
    }

    return false;
  }


  public boolean excluirLivro(){
    Scanner entrada = new Scanner(System.in);
    int codLivro;
    Livro meuLivro = null;

    System.out.print("Digite o codigo do livro: ");
    codLivro = entrada.nextInt();

    if (this.biblioteca.getLivro().isEmpty())
      return false;

    for (Livro livro : this.biblioteca.getLivro()){
      if (livro.getCodLivro() == codLivro){
        meuLivro = livro;
        break;
      }
    }

    if (meuLivro != null){
      this.biblioteca.excluirLivro(meuLivro);

      return true;
    }

    return false;
  }


  public boolean excluirEmprestimo(){
    Scanner entrada = new Scanner(System.in);
    int numero;
    Emprestimo meuEmprestimo = null;

    System.out.print("Digite o numero do emprestimo: ");
    numero = entrada.nextInt();

    if (this.biblioteca.getEmprestimo().isEmpty())
      return false;

    for (Emprestimo emprestimo : this.biblioteca.getEmprestimo()){
      if (emprestimo.getNumero() == numero){
        meuEmprestimo = emprestimo;
        break;
      }
    }

    if (meuEmprestimo != null){
      this.biblioteca.excluirEmprestimo(meuEmprestimo);

      return true;
    }

    return false;
  }


  public boolean excluirItemEmprestimo(){
    Scanner entrada = new Scanner(System.in);
    int numero;
    Emprestimo meuEmprestimo = null;

    System.out.print("Digite o numero do emprestimo: ");
    numero = entrada.nextInt();

    if (this.biblioteca.getEmprestimo().isEmpty())
      return false;

    for (Emprestimo emprestimo : this.biblioteca.getEmprestimo()){
      if (emprestimo.getNumero() == numero){
        meuEmprestimo = emprestimo;
        break;
      }
    }

    if (meuEmprestimo != null){
      int codLivro;
      ItemEmprestimo meuItem = null;

      System.out.print("Digite o codigo do livro: ");
      codLivro = entrada.nextInt();

      if (meuEmprestimo.getItens().isEmpty())
        return false;

      for (ItemEmprestimo item : meuEmprestimo.getItens()){
        if (item.getLivro().getCodLivro() == codLivro){
          meuItem = item;
          break;
        }
      }

      if (meuItem != null){
        this.biblioteca.excluirItemEmprestimo(meuEmprestimo, meuItem);

        return true;
      }
    }

    return false;
  }


  public boolean devolverTodosLivrosEmprestimo(){
    Scanner entrada = new Scanner(System.in);
    int numero;
    Emprestimo meuEmprestimo = null;

    System.out.print("Digite o numero do emprestimo: ");
    numero = entrada.nextInt();

    for (Emprestimo emprestimo : this.biblioteca.getEmprestimo()){
      if (emprestimo.getNumero() == numero){
        meuEmprestimo = emprestimo;
        break;
      }
    }

    if (meuEmprestimo != null){
      this.biblioteca.devolverTodosLivrosEmprestimo(meuEmprestimo);

      return true;
    }

    return false;
  }


  public boolean devolverLivroEmprestimo(){
    Scanner entrada = new Scanner(System.in);
    int numero;
    Emprestimo meuEmprestimo = null;

    System.out.print("Digite o numero do emprestimo: ");
    numero = entrada.nextInt();

    for (Emprestimo emprestimo : this.biblioteca.getEmprestimo()){
      if (emprestimo.getNumero() == numero){
        meuEmprestimo = emprestimo;
        break;
      }
    }

    if (meuEmprestimo != null){
      int codLivro;
      ItemEmprestimo meuItem = null;

      System.out.print("Digite o codigo do livro: ");
      codLivro = entrada.nextInt();

      if (meuEmprestimo.getItens().isEmpty())
        return false;

      for (ItemEmprestimo item : meuEmprestimo.getItens()){
        if (item.getLivro().getCodLivro() == codLivro){
          meuItem = item;
          break;
        }
      }

      if (meuItem != null){
        this.biblioteca.devolverItemEmprestimo(meuEmprestimo, meuItem);

        return true;
      }
    }

    return false;
  }


  public void pesquisarLivroPorTitulo(){
    Scanner entrada = new Scanner(System.in);
    String titulo;
    ArrayList<Livro> livros;

    System.out.print("Digite o titulo: ");
    titulo = entrada.nextLine();

    livros = this.biblioteca.pesquisarLivroPorTitulo(titulo);

    for (Livro meuLivro : livros){
      System.out.println("* Dados do livro:");
      System.out.println("  Codigo do livro: " + meuLivro.getCodLivro());
      System.out.println("  Titulo: " + meuLivro.getTitulo());
      System.out.println("  Autores: " + meuLivro.getAutores());
      System.out.println("  Quantidade de exemplares: " + meuLivro.getQtdeExemplares());
      System.out.println(); //saltar linha
    }
  }


  public void pesquisarLivroPorAutor(){
    Scanner entrada = new Scanner(System.in);
    String autor;
    ArrayList<Livro> livros;

    System.out.print("Digite o titulo: ");
    autor = entrada.nextLine();

    livros = this.biblioteca.pesquisarLivroPorAutor(autor);

    for (Livro meuLivro : livros){
      System.out.println("* Dados do livro:");
      System.out.println("  Codigo do livro: " + meuLivro.getCodLivro());
      System.out.println("  Titulo: " + meuLivro.getTitulo());
      System.out.println("  Autores: " + meuLivro.getAutores());
      System.out.println("  Quantidade de exemplares: " + meuLivro.getQtdeExemplares());
      System.out.println(); //saltar linha
    }
  }


  public void listarTodosUsuarios(){
    for (Usuario meuUsuario : this.biblioteca.getUsuario()){
      System.out.println("* Dados do usuario:");
      System.out.println("  Nome: " + meuUsuario.getNome());
      System.out.println("  CPF: " + meuUsuario.getCpf());
      System.out.println("  Endereco: " + meuUsuario.getEndereco());
      System.out.println("  Telefone: " + meuUsuario.getFone());
      System.out.println(); //saltar linha
    }
  }


  public void listarTodosLivros(){
    for (Livro meuLivro : this.biblioteca.getLivro()){
      System.out.println("* Dados do livro:");
      System.out.println("  Codigo do livro: " + meuLivro.getCodLivro());
      System.out.println("  Titulo: " + meuLivro.getTitulo());
      System.out.println("  Autores: " + meuLivro.getAutores());
      System.out.println("  Quantidade de exemplares: " + meuLivro.getQtdeExemplares());
      System.out.println(); //saltar linha
    }
  }


  public void listarTodosEmprestimos(){
    for (Emprestimo meuEmprestimo : this.biblioteca.getEmprestimo()){
      System.out.println("* Dados do emprestimo:");

      System.out.println("  Numero: " + meuEmprestimo.getNumero());

      System.out.println("  Data de emprestimo: " + meuEmprestimo.getDataEmprestimo().get(GregorianCalendar.DAY_OF_MONTH) +"/"+ (meuEmprestimo.getDataEmprestimo().get(GregorianCalendar.MONTH)+1) +"/"+ meuEmprestimo.getDataEmprestimo().get(GregorianCalendar.YEAR));

      System.out.println("  Data prevista para devolucao: " + meuEmprestimo.getDataPrevDevolucao().get(GregorianCalendar.DAY_OF_MONTH) +"/"+ (meuEmprestimo.getDataPrevDevolucao().get(GregorianCalendar.MONTH)+1) +"/"+ meuEmprestimo.getDataPrevDevolucao().get(GregorianCalendar.YEAR));

      if (meuEmprestimo.getUsuario() != null){
        System.out.println("  Usuario:");
        System.out.println("    - Dados do usuario:");
        System.out.println("      Nome: " + meuEmprestimo.getUsuario().getNome());
        System.out.println("      CPF: " + meuEmprestimo.getUsuario().getCpf());
        System.out.println("      Endereco: " + meuEmprestimo.getUsuario().getEndereco());
        System.out.println("      Telefone: " + meuEmprestimo.getUsuario().getFone());
      }

      if (!meuEmprestimo.getItens().isEmpty()){
        System.out.println("  Itens:");      
        for (ItemEmprestimo item : meuEmprestimo.getItens()){
          System.out.println("    - Dados do livro:");
          System.out.println("      Codigo do livro: " + item.getLivro().getCodLivro());
          System.out.println("      Titulo: " + item.getLivro().getTitulo());
          System.out.println("      Autores: " + item.getLivro().getAutores());
          System.out.println("      Quantidade de exemplares: " + item.getLivro().getQtdeExemplares());
          if (item.getDataDevolucao() != null)
            System.out.println("      Data de devolucao: " + item.getDataDevolucao().get(GregorianCalendar.DAY_OF_MONTH) + "/" + (item.getDataDevolucao().get(GregorianCalendar.MONTH)+1) + "/" + item.getDataDevolucao().get(GregorianCalendar.YEAR));
        }
      }

      System.out.println(); //saltar linha
    }
  }


  public static void main(String[] args){
    InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
    int op;

    do{
      op = menuOpcoes();

      if (op == 1){
        interfaceUsuario.cadastrarUsuario();
        System.out.println("Cadastro realizado com sucesso!");
      }else if (op == 2){
        interfaceUsuario.cadastrarLivro();
        System.out.println("Cadastro realizado com sucesso!");
      }else if (op == 3){
        if (interfaceUsuario.cadastrarEmprestimo())
          System.out.println("Cadastro realizado com sucesso!");
        else
          System.out.println("Falha ao realizar cadastro. Usuario invalido!");
      }else if (op == 4){
        if (interfaceUsuario.inserirItemEmprestimo())
          System.out.println("Insercao realizada com sucesso!");
        else
          System.out.println("Falha ao realizar insercao. Emprestimo e/ou livro invalido(s)!");
      }else if (op == 5){
        if (interfaceUsuario.excluirUsuario())
          System.out.println("Exclusao realizada com sucesso!");
        else
          System.out.println("Falha ao realizar exclusao. Usuario invalido!");
      }else if (op == 6){
        if (interfaceUsuario.excluirLivro())
          System.out.println("Exclusao realizada com sucesso!");
        else
          System.out.println("Falha ao realizar exclusao. Livro invalido!");
      }else if (op == 7){
        if (interfaceUsuario.excluirEmprestimo())
          System.out.println("Exclusao realizada com sucesso!");
        else
          System.out.println("Falha ao realizar exclusao. Emprestimo invalido!");
      }else if (op == 8){
        if (interfaceUsuario.excluirItemEmprestimo())
          System.out.println("Exclusao realizada com sucesso!");
        else
          System.out.println("Falha ao realizar exclusao. Emprestimo e/ou item de emprestimo invalido(s)!");
      }else if (op == 9){
        if (interfaceUsuario.devolverTodosLivrosEmprestimo())
          System.out.println("Devolucao realizada com sucesso!");
        else
          System.out.println("Falha ao realizar devolucao. Emprestimo invalido!");
      }else if (op == 10){
        if (interfaceUsuario.devolverLivroEmprestimo())
          System.out.println("Devolucao realizada com sucesso!");
        else
          System.out.println("Falha ao realizar devolucao. Emprestimo e/ou item de emprestimo invalido(s)!");
      }else if (op == 11){
        interfaceUsuario.pesquisarLivroPorTitulo();
      }else if (op == 12){
        interfaceUsuario.pesquisarLivroPorAutor();
      }else if (op == 13){
        interfaceUsuario.listarTodosUsuarios();
      }else if (op == 14){
        interfaceUsuario.listarTodosLivros();
      }else if (op == 15){
        interfaceUsuario.listarTodosEmprestimos();
      }
    }while (op != 16);
  }
}
