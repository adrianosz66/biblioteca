public class Livro{

  private int codLivro;
  private String titulo;
  private String autores;
  private int qtdeExemplares;


  /*public Livro(){
    this.codLivro = 0;
    this.titulo = null;
    this.autores = null;
    this.qtdeExemplares = 0;
  }*/


  public Livro(int codLivro, String titulo, String autores, int qtdeExemplares){
    this.codLivro = codLivro;
    this.titulo = titulo;
    this.autores = autores;
    this.qtdeExemplares = qtdeExemplares;
  }


  public Livro(int codLivro, String titulo, String autores){
    this.codLivro = codLivro;
    this.titulo = titulo;
    this.autores = autores;
    this.qtdeExemplares = 0;
  }


  public Livro(Livro meuLivro){
    this.codLivro = meuLivro.codLivro;
    this.titulo = meuLivro.titulo;
    this.autores = meuLivro.autores;
    this.qtdeExemplares = meuLivro.qtdeExemplares;
  }


  public int getCodLivro(){
    return this.codLivro;
  }


  public String getTitulo(){
    return this.titulo;
  }


  public String getAutores(){
    return this.autores;
  }


  public int getQtdeExemplares(){
    return this.qtdeExemplares;
  }


  public void setTitulo(String titulo){
    this.titulo = titulo;
  }


  public void setAutores(String autores){
    this.autores = autores;
  }


  public void incQtdeExemplares(){
    this.qtdeExemplares++;
  }


  public boolean decQtdeExemplares(){
    if (this.qtdeExemplares > 0){
      this.qtdeExemplares--;
      return true;
    }

    return false;
  }
}
