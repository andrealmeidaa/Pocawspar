package br.edu.awsifrn.pojo;
//Classe que representa a resposta da API
public class ResponseClass {
    private String greetings;

    public String getGreetings() {
        return greetings;
    }

    public void setGreetings(String greetings) {
        this.greetings = greetings;
    }
    public ResponseClass(){

    }
    public ResponseClass(String greetings){
        this.greetings =greetings;
    }


}
