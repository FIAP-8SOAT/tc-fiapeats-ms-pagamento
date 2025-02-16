# FiapEats - Microserviço de Pagamento

Este repositório contém o microserviço de pagamento do FiapEats, uma aplicação que gerencia o fluxo de pagamento de um pedido. Este serviço foi refatorado a partir de um monolito para operar de forma independente, seguindo boas práticas de arquitetura de microserviços.

## Estrutura Projeto

### Diretórios usados
- /docs -> arquivos pertinentes a documentações
- /variables -> variaveis de ambiente usadas na app localmente, via kubernetes e via container
- /src -> código fonte da app
- /kubernetes -> configs de deployment,service,hpa e ingress da aplicação

### Plugins
- Jacoco -> cobertura de testes unitários e análise
- Spotless -> identação de código padronizada

---
## Ambiente Docker
![Ambiente Docker](docs/docker_logo.png)

Foi criado o arquivo na raiz docker-compose.yaml que contempla a configuração necessários para compor o ambiente completo de nosso sistema.

Para executar o ambiente utilize o seguinte comando:
```bash
docker compose --profile app up -d
```

### Configurando variáveis de ambiente

O projeto tem uma pasta chamada 'variables' e contém dois arquivos, eles representam as variaveis que a app usa em tempo de desenvolvimento 'local.env' e em tempo de execução no container 'ambient.env'.

No arquivo docker a referência já está criada e nada precisa ser feito, porém localmente iremos necessitar adicionar em nossa IDE um plugin para substituir automaticamente os valores do arquivo na nossa app.

- No menu superior, clique em File.
- Selecione Settings (ou Preferences no macOS).
- Navegar até Plugins:
- No painel de configurações, vá até a seção Plugins, que geralmente está localizada na coluna à esquerda.
- No painel de Plugins, clique na aba Marketplace para acessar a loja de plugins.
- Na barra de pesquisa, digite Enviar.file e pressione Enter.
- Encontre o plugin Enviar.file na lista de resultados.
- Clique no botão Install ao lado do nome do plugin para iniciar a instalação.
- Após a instalação, você será solicitado a reiniciar o IntelliJ IDEA. Clique em Restart IDE para aplicar as mudanças.

![Plugin no Marketplace](docs/plugin.png)

Após isso, na aba de configurações de execução da app só habiliar o uso do plugin e referenciar o arquivo 'local.env' dentro da pasta variables
![Configuração do arquivo](docs/configure.png)


---
# Documentação APIs

O Swagger da aplicação pode ser acessado através da URL: `http://localhost:8081/fiapeats/swagger-ui/index.html`.

Abaixo estão os principais endpoints para gerenciamento de pagamento, com exemplos de requisições e respostas.


---
## 1. Criar um QRCode para pagamento
**Descrição**: Cria um QRCode para pagamento associada a um pedido.

**Endpoint**: `POST /pagamento`

**Exemplo de Requisição**:
```json
{
  "idPedido": "d212192c-8155-440a-9eda-3d77732458bb",
  "urlNotificacao": "https://ba08-45-173-179-15.ngrok-free.app/fiapeats/pagamento/notificacao"
}
```

**urlNotificacao** é opcional, quando não passado utiliza a url default.

**Exemplo de Resposta (201 Created)**:
```json
{
  "codigoQR": "00020101021243650016COM.MERCADOLIBRE020130636bc32f6ed-3273-4cba-96ec-98eb93ba526e5204000053039865802BR5909Test Test6009SAO PAULO62070503***63043838"
}
```

## 2. Rota de webhook para o MERCADO PAGO enviar atualização de pagamento do pedido
**Descrição**: Recebe a requisição do mercado pago referente ao status de pagamento do pedido e envia atualização de pagamento via integração para o serviço ms-pedido

**Endpoint**: `POST /pagamento/notificacao`

**Exemplo de Requisição**:
```json
{
  "topic": "merchant_order",
  "id": "28237058216"
}
```

**Qualquer requisição com o valor do "tópico" diferente de "merchant_order" será descartada**

**Exemplo de Resposta (200 OK) - Sem BODY**

---
### Evidência Coverage Sonar