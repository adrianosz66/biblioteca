public class Usuario{

  private String nome;
  private String cpf;
  private String endereco;
  private String fone;


  public Usuario(){
    this.nome = null;
    this.cpf = null;
    this.endereco = null;
    this.fone = null;
  }


  public Usuario(String nome, String cpf, String endereco, String fone){
    this.nome = nome;
    this.cpf = cpf;
    this.endereco = endereco;
    this.fone = fone;
  }


  public Usuario(Usuario user){
    this.nome = user.nome;
    this.cpf = user.cpf;
    this.endereco = user.endereco;
    this.fone = user.fone;
  }


  public String getNome(){
    return this.nome;
  }


  public String getCpf(){
    return this.cpf;
  }


  public String getEndereco(){
    return this.endereco;
  }


  public String getFone(){
    return this.fone;
  }


  public void setNome(String nome){
    this.nome = nome;
  }


  public void setCpf(String cpf){
    this.cpf = cpf;
  }


  public void setEndereco(String endereco){
    this.endereco = endereco;
  }


  public void setFone(String fone){
    this.fone = fone;
  }
}
