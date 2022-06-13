/*

Código implantado na AWS

Para realizar no formato, devemos aplicar o seguinte método

Acessar https://us-east-1.console.aws.amazon.com/lambda/home?region=us-east-1#/discover
Criar uma nova função Lambda para Node
Permitir acesso via URL(simplifica o processo)
Autentication  None(Acessado por qualquer um), não é aplicável em cenário real
*/
exports.handler = async (event) => {
   let corpo=JSON.parse(event.body)
   var data={
      'greetings':'Hello, '+corpo.firstName+' '+ corpo.lastName
   }
   var response = {
      statusCode: 200,
      body: JSON.stringify(data)
   };
   return response;
};