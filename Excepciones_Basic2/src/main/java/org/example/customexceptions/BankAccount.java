package org.example.customexceptions;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BankAccount {
    private double balance;
    //Constructor para establecer saldo inicial
    public BankAccount(double initialBalance){
        this.balance = initialBalance;
    }
    //Excepcion personalizada
    public static class InsufficientFundsException extends Exception {
        private double amount;

        //constructor que inicializa la excepcion y un mensaje
        public InsufficientFundsException(double amount, String message){
            super(message);
            this.amount = amount;
        }
        //metodo para obtener el metodo deficiente
        public double getAmount(){
            return amount;
        }
    }
    //metodo para realizar el retiro y si el saldo es insuficiente lanza el metodo InsufficientFundsException
    public void withdraw(double amount) throws InsufficientFundsException{
        if (amount > balance){
            throw new InsufficientFundsException(amount, "Fondo insuficiente para retirar: $" + amount);

        }
        balance -= amount;
        System.out.println("Retiro realizado correctamente. Saldo actual: $" +balance);
    }
    //Metodo para obtener saldo actual
    public double getBalance(){
        return balance;
    }

    public static void main(String[] args) {
        //Creamos instancia de BankAccount con saldo inicial
        BankAccount account = new BankAccount(700);

        //intentamos realizar retiro mayor al saldo para provocare excepcion
        try{
            account.withdraw(600);
        } catch (InsufficientFundsException e){
            System.out.println("Excepcion capturada: " + e.getMessage());
            System.out.println("Monto en deficit: $"+ e.getAmount());
        }
    }
}