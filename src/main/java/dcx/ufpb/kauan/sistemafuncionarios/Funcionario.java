package dcx.ufpb.kauan.sistemafuncionarios;

public class Funcionario {

    private String nome;
    private String cpf;
    double salario;
    private tipoFuncionario tipo;


    public void funcionario (String nome, String cpf, double salario){

        this.nome = nome;
        this.cpf = cpf;
        double.salario = salario;

    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public tipoFuncionario getTipo() {
        return tipo;
    }

    public void setTipo(tipoFuncionario tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
