## Descrição

Uma empresa deseja automatizar o processamento de seus contratos. O processamento de um contrato consiste em gerar 
as parcelas a serem pagas para aquele contrato, com base no número de meses desejado.

A empresa utiliza um serviço de pagamento online para realizar o pagamento das parcelas. Os serviços de pagamento 
online tipicamente cobram um juro mensal, bem como uma taxa por pagamento. Por enquanto, o serviço contratado pela 
empresa é o do Paypal, que aplica juros simples de 1% a cada parcela, mais uma taxa de pagamento de 2%.

Fazer um programa para ler os dados de um contrato (número do contrato, data do contrato, e valor total do contrato). 
Em seguida, o programa deve ler o número de meses para parcelamento do contrato, e daí gerar os registros de parcelas a 
serem pagas (data e valor),sendo a primeira parcela a ser paga um mês após a data do contrato, a segunda parcela dois 
meses após o contrato e assim por diante. Mostrar os dados das parcelas na tela.

Veja exemplo a seguir.

### Exemplo
- Entre os dados do contrato:
- Numero: 8028
- Data (dd/MM/yyyy): 25/06/2018
- Valor do contrato: 600.00
- Entre com o numero de parcelas: 3

Parcelas:
- 25/07/2018 - 206.04
- 25/08/2018 - 208.08
- 25/09/2018 - 210.12

### Domain layer design (entities)

```mermaid
classDiagram
    class Contract {
        - number: Integer
        - date: Date
        - totalValue: Double
        - List~Installment~ installments
    }
    class Installment {
        - dueDate: Date
        - amount: Double
    }
    Contract *--"*" Installment
```

### Service layer design

```mermaid
classDiagram 
    class ContractService {
        + processContract(contract: Contract, months: Integer)
    }
    class OnlinePaymentService {
        <<interface>>
        + paymentFee(amount: Double) Double
        + interest(amount: Double, months: Integer) Double
    }
    class PaypalService {
        + paymentFee(amount: Double) Double
        + interest(amount: Double, months: Integer) Double
    }
    
    ContractService --> OnlinePaymentService
    PaypalService --|> OnlinePaymentService
```
