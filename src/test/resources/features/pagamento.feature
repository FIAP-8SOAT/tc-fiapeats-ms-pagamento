# language: pt
Funcionalidade: : Criação código QR de pagamento e Atualização do status de pagamento do pedido

  Cenario: Criar um código QR de pagamento com sucesso
    Dado que eu tenho os dados de um novo pagamento:
      | idProduto                            | urlNotificacao                                                          |
      | d212192c-8155-440a-9eda-3d77732458bb | https://34f9-45-173-179-9.ngrok-free.app/fiapeats/pagamento/notificacao |
    Quando eu crio um código QR com os dados
    Entao o sistema retorna o código QR de pagamento

  Cenario: Atualizar o status de pagamento do pedido
    Dado que eu tenho os dados do status de pagamento do pedido:
      | topic          | id  |
      | merchant_order | 28237058216 |
    Quando eu atualizo o status do pagamento do pedido
    Entao o status do pagamento do pedido é atualizado com sucesso
