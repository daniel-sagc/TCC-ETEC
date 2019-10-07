package Telas;
public class Usuario {
    private String Nome,DataNasci,Email,Senha,Tipo,Codigo,Login;
    
    
    public String getCodigo(){
    	return Codigo;
    }
    public void  setCodigo(String Codigo){
    	this.Codigo = Codigo;
    }
    
    
    public String getNome() {
        return Nome;
    }
    public void setNome(String Nome) {
        this.Nome = Nome;
    }
    public String getDataNasci() {
        return DataNasci;
    }
    public void setDataNasci(String DataNasci) {
        this.DataNasci =  DataNasci;
    }
    
    
    public String getEmail() {
        return Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getSenha() {
        return Senha;
    }
    public void setSenha(String Senha) {
        this.Senha = Senha;
    }
    
    public String getTipo() {
        return Tipo;
    }
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    
    public String getLogin() {
        return Login;
    }
    public void setLogin(String Login) {
        this.Login = Login;
    }
    
    
  
}
