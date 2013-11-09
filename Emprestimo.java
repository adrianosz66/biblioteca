import java.util.GregorianCalendar;
import java.util.ArrayList;

public class Emprestimo{

  private int numero;
  private GregorianCalendar dataEmprestimo;
  private GregorianCalendar dataPrevDevolucao;
  private Usuario usuario;
  private ArrayList<ItemEmprestimo> itens;
  private static int proximoNumero = 1;



  /*public Emprestimo(){
    this.numero = proximoNumero;
    proximoNumero++;
    this.dataEmprestimo = new GregorianCalendar();
    this.dataPrevDevolucao = null;
    this.usuario = null;
    this.itens = new ArrayList<ItemEmprestimo>();
  }*/


  public Emprestimo(GregorianCalendar dataPrevDevolucao, Usuario usuario){
    this.numero = proximoNumero;
    proximoNumero++;
    this.dataEmprestimo = new GregorianCalendar();
    this.dataPrevDevolucao = (GregorianCalendar)dataPrevDevolucao.clone();
    this.usuario = new Usuario(usuario);
    this.itens = new ArrayList<ItemEmprestimo>();
  }


  public Emprestimo(Emprestimo meuEmprestimo){
    this.numero = meuEmprestimo.numero;
    this.dataEmprestimo = (GregorianCalendar)meuEmprestimo.dataEmprestimo.clone();
    this.dataPrevDevolucao = (GregorianCalendar)meuEmprestimo.dataPrevDevolucao.clone();
    this.usuario = new Usuario(meuEmprestimo.usuario);
    this.itens = (ArrayList<ItemEmprestimo>)meuEmprestimo.itens.clone();
  }


  public int getNumero(){
    return this.numero;
  }


  public static int getProximoNumero(){
    return proximoNumero;
  }


  public GregorianCalendar getDataEmprestimo(){
    return (GregorianCalendar)this.dataEmprestimo.clone();
  }


  public GregorianCalendar getDataPrevDevolucao(){
    return (GregorianCalendar)this.dataPrevDevolucao.clone();
  }


  public Usuario getUsuario(){
    return new Usuario(this.usuario);
  }


  public ArrayList<ItemEmprestimo> getItens(){
    return this.itens;
  }


  public boolean addItemEmprestimo(ItemEmprestimo item){
    if (item.getLivro().decQtdeExemplares()){
      this.itens.add(item);
      return true;
    }

    return false;
  }


  public boolean excluirItemEmprestimo(ItemEmprestimo item){
    if (this.itens.contains(item)){
      this.itens.remove(this.itens.lastIndexOf(item));

      return true;
    }

    return false;
  }


  public boolean devolverItemEmprestimo(ItemEmprestimo item){
    if (this.itens.contains(item)){
      this.itens.get(this.itens.lastIndexOf(item)).setDataDevolucao();
      item.getLivro().incQtdeExemplares();
      return true;
    }

    return false;
  }


  public void devolverTodosItens(){
    if (!this.itens.isEmpty())
      for (ItemEmprestimo item : this.itens)
        if (item.getDataDevolucao() == null)
          this.devolverItemEmprestimo(item);
  }
}
