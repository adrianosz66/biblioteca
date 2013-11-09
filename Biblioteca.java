import java.util.ArrayList;

public class Biblioteca{

  private ArrayList<Usuario> usuario;
  private ArrayList<Livro> livro;
  private ArrayList<Emprestimo> emprestimo;


  public Biblioteca(){
    usuario = new ArrayList<Usuario>();
    livro = new ArrayList<Livro>();
    emprestimo = new ArrayList<Emprestimo>();
  }


  public void inserirUsuario(Usuario usuario){
    this.usuario.add(new Usuario(usuario));
  }


  public void inserirLivro(Livro livro){
    this.livro.add(new Livro(livro));
  }


  public void inserirEmprestimo(Emprestimo emprestimo){
    this.emprestimo.add(new Emprestimo(emprestimo));
  }


  public boolean inserirItemEmprestimo(Emprestimo emprestimo, ItemEmprestimo item){
    return emprestimo.addItemEmprestimo(item);
  }


  public boolean excluirUsuario(Usuario usuario){
    if (!this.usuario.contains(usuario))
      return false;

    boolean temEmprestimo = false;
    Usuario meuUsuario;

    for (Emprestimo meuEmprestimo : this.emprestimo){
      meuUsuario = meuEmprestimo.getUsuario();

      if ( ( meuUsuario.getNome().equalsIgnoreCase(usuario.getNome()) && meuUsuario.getCpf().equalsIgnoreCase(usuario.getCpf()) ) &&
           ( meuUsuario.getEndereco().equalsIgnoreCase(usuario.getEndereco()) && meuUsuario.getFone().equalsIgnoreCase(usuario.getFone()) ) ){

        temEmprestimo = true;
        break;
      }
    }

    if (temEmprestimo)
      return false;

    this.usuario.remove(this.usuario.indexOf(usuario));
    return true;
  }


  public boolean excluirLivro(Livro livro){
    if (!this.livro.contains(livro))
      return false;

    boolean temEmprestimo = false;
    Livro meuLivro;

    for (Emprestimo meuEmprestimo : this.emprestimo){
      for (ItemEmprestimo item : meuEmprestimo.getItens()){
        meuLivro = item.getLivro();

        if ( ( meuLivro.getCodLivro() == livro.getCodLivro() && meuLivro.getTitulo().equalsIgnoreCase(livro.getTitulo()) ) &&
             ( meuLivro.getAutores().equalsIgnoreCase(livro.getAutores()) && meuLivro.getQtdeExemplares() == livro.getQtdeExemplares() ) ){

          temEmprestimo = true;
          break;
        }
      }

      if (temEmprestimo)
        break;
    }

    if (temEmprestimo)
      return false;

    this.livro.remove(this.usuario.indexOf(livro));
    return true;
  }


  public boolean excluirItemEmprestimo(Emprestimo emprestimo, ItemEmprestimo item){
    return emprestimo.excluirItemEmprestimo(item);
  }


  public boolean excluirEmprestimo(Emprestimo emprestimo){
    if (!this.emprestimo.contains(emprestimo))
      return false;

    this.emprestimo.remove(emprestimo.getNumero()-1);
    return true;
  }


  public boolean devolverItemEmprestimo(Emprestimo emprestimo, ItemEmprestimo item){
    return emprestimo.devolverItemEmprestimo(item);
  }


  public void devolverTodosLivrosEmprestimo(Emprestimo emprestimo){
    emprestimo.devolverTodosItens();
  }


  public ArrayList<Livro> pesquisarLivroPorTitulo(String titulo){
    ArrayList<Livro> livro = new ArrayList<Livro>();

    for (Livro meuLivro : this.livro)
      if (meuLivro.getTitulo().indexOf(titulo) >= 0)
        livro.add(new Livro(meuLivro));

    return (ArrayList<Livro>)livro.clone();
  }


  public ArrayList<Livro> pesquisarLivroPorAutor(String autores){
    ArrayList<Livro> livro = new ArrayList<Livro>();

    for (Livro meuLivro : this.livro)
      if (meuLivro.getAutores().indexOf(autores) >= 0)
        livro.add(new Livro(meuLivro));

    return (ArrayList<Livro>)livro.clone();
  }


  public ArrayList<Usuario> getUsuario(){
    return this.usuario;
  }


  public ArrayList<Livro> getLivro(){
    return this.livro;
  }


  public ArrayList<Emprestimo> getEmprestimo(){
    return this.emprestimo;
  }
}
