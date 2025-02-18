# language: pt
Funcionalidade: : Criação código QR de pagamento e Atualização do status de pagamento do pedido

  Cenario: Atualizar o status de pagamento do pedido
    Dado que eu tenho os dados do status de pagamento do pedido:
      | topic          | id  |
      | merchant_order | 28237058216 |
    Quando eu tento atualizar o status do pagamento do pedido
    Entao o sistema retorna pedido não encontrado associado ao pagamento
