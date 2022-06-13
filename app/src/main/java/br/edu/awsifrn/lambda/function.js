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