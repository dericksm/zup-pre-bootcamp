# Zup-pre-bootcamp
Projeto como prova de teste para aplicação ao Zup Bootcamp


**Setting up the environment:**

***Google OAuth***
1. Para habilitar o envio de imagens, deve ser realizada a integração com o Google Drive, dessa forma, deve ser criado um projeto em https://console.developers.google.com/ com a API Google Drive habilitada. Um tutorial para este processo pode ser encontrado no link a seguir https://www.iperiusbackup.net/pt-br/como-habilitar-a-api-do-google-drive-e-obter-credenciais-de-cliente/.
2. Após criar o app, baixe as credencials, a armazene no diretório de sua preferência, mas não se esqueça de configurar seu caminho na chave `google.secret.key.path` do arquivo `application.properties`.
3. Ainda no Console da sua aplicação Google, configure a sua URI de calllback em `Authorized redirect URIs` e depois adicione seu valor na chave `google.oauth.callback.uri` do arquivo `application.properties`.

***Mail service***
1. Para o envio de e-mails as chaves `default.sender` e `default.recipient` no arquivo `application.properties` devem ser configuradas com um endereço de e-mail desejado.
2. Para o envio de e-maisl com templates HTML, as chaves `spring.mail.username` e `spring.mail.password` com um endereço de e-mail Gmail e sua respectiva senha devem ser informados. Esta opção pode ser evitada utilizando o Perfil Test.

***Dev configs***
1. O envio de e-mails HTML é realizado somente com a execução do Perfil Dev, e para sua execução os dados do banco de dados devem ser configurados em `application-dev.properties`.


**Usage:**

**Criar um novo usuário**
`POST /clients`

**Adicionar endereço para o usuário**
`POST /address`

**Adicionar imagem do CPF**
1. URI para autorização OAUTH `GET /drive-auth/auth-uri`
2. Envio da imagem do CPF `POST /drive-auth/upload`
3. Retorno da imagem do CPF `GET /clients/{id cliente}/cpf-upload`

**Envio da proposta**
`POST /account-proposal`

**Troca de senha**
`POST /accounts/change-password`

**Envio de pagamentos a serem processados**
`POST /payments/receive-payment-list`
