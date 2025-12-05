package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Main {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Entre com os dados do contrato:");
		System.out.print("Numero: ");
		int number = input.nextInt();
		input.nextLine();
		System.out.print("Data (dd/MM/yyyy): ");
		String date = input.nextLine();
		System.out.println("Valor do contrato: ");
		double valuePerContract = input.nextDouble();
		input.nextLine();
	    Contract contract = new Contract(number, LocalDate.parse(date, dtf), valuePerContract);
		System.out.print("Entre com o numero de parcelas: ");
		int installments = input.nextInt();

		ContractService contractService = new ContractService(new PaypalService());
		contractService.processContract(contract, installments);
		
		System.out.println("Parcelas: ");
		for(Installment in: contract.getInstallments()){
			System.out.println(in.toString());
		}
		
		
		input.close();
	}

}
