package model.service;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
    private OnlinePaymentService paymentService;

    public ContractService(OnlinePaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processContract(Contract contract, int months) {
        double grossInstallments = contract.getTotalValue() / months;
        for(int i = 0; i < months; i++) {
            var installment = grossInstallments + paymentService.interest(grossInstallments, i + 1);
            installment += paymentService.paymentFee(installment);
            contract.addInstallment(new Installment(contract.getDate().plusMonths(i + 1), installment));
        }
    }
}
