import java.util.GregorianCalendar;

public class ItemEmprestimo{

  private Livro livro;
  private GregorianCalendar dataDevolucao;


  /*public ItemEmprestimo(){
    this.livro = new Livro();
    this.dataDevolucao = null;
  }*/


  public ItemEmprestimo(Livro livro){
    this.livro = livro;
    this.dataDevolucao = null;
  }


  public ItemEmprestimo(ItemEmprestimo item){
    this.livro = new Livro(item.livro);
    this.dataDevolucao = (GregorianCalendar)item.dataDevolucao.clone();
  }


  public Livro getLivro(){
    return this.livro;
  }


  public GregorianCalendar getDataDevolucao(){
    return this.dataDevolucao != null ? (GregorianCalendar)this.dataDevolucao.clone() : null;
  }


  public void setDataDevolucao(){
    this.dataDevolucao = new GregorianCalendar();
  }
}
