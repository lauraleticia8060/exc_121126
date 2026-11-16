public class Funcionario {
    
    private String nome;
    private double salarioMensal;
    private final boolean pj;

    public Funcionario(String nome, double salarioMensal)
    {
        this(nome, salarioMensal, false);    
    }

    protected Funcionario(String nome, double salarioMensal, boolean pj)
    {
        if(nome == null || nome.isBlank() || salarioMensal < 0 )
        {
            throw new IllegalArgumentException("Nome e salário inválidos.");
        }
        this.nome = nome;
        this.salarioMensal = salarioMensal;
        this.pj = pj;
    }

    public String getNome()
    {
        return nome;
    }

    public double getSalarioMensal()
    {
        return salarioMensal;
    }
    
    public boolean isPJ()
    {
        return pj;
    }

    protected double Arredondar(double valor)
    {
        return Math.round(valor * 100.0) / 100.0;
    }

    public double calcularBruto()
    {
        return  salarioMensal;
    }

    public double calcularINSS()
    {
        if (isPJ()) 
        {
            return 0.0;    
        }
        double bruto = calcularBruto();

        double salario1 = Math.min(bruto, 1621.00) * 0.075;
        double salario2 = Math.max(0, Math.min(bruto, 2902.84) - 1621.00) * 0.09;
        double salario3 = Math.max(0, Math.min(bruto, 4354.27) - 2902.84) * 0.12;
        double salario4 = Math.max(0, Math.min(bruto, 8475.55) - 4354.27) * 0.14;

        return Arredondar(salario1 + salario2 + salario3 + salario4 );
    }

    public double calcularIRRF()
    {
        if (isPJ()) 
        {
            return 0.0;
        }
        double bruto = calcularBruto();

        if (bruto <= 5000.00) 
        {
            return 0.0;    
        }

        double inss = calcularINSS();
        double deducao = Math.max(inss, 607.20);
        double base = Math.max(0, bruto - deducao);

        double aliquota;
        double parcela;

        if (base <= 2428.80) 
        {
            aliquota = 0.0;
            parcela = 0.0;    
        }
        else if (base <= 2826.65) 
        {
            aliquota =   0.075;
            parcela = 182.16;  
        }
    }




}
