package application;

import model.entities.Contract;
import model.service.ContractService;
import model.service.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre os dados do contrato:");
        System.out.print("Número: ");
        int number = sc.nextInt();
        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Valor do contrato: ");
        double value = sc.nextDouble();

        Contract c = new Contract(number, date, value);

        System.out.print("Entre com o numero de parcelas: ");
        int numberOfInstallments = sc.nextInt();
        ContractService contractService = new ContractService(new PaypalService());
        contractService.processContract(c, numberOfInstallments);

        System.out.println("Parcelas");
        for (var installment : c.getInstallments()) {
            System.out.println(installment);
        }

        sc.close();
    }
}
