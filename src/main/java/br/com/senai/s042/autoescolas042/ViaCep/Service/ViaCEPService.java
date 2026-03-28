package br.com.senai.s042.autoescolas042.ViaCep.Service;

import br.com.senai.s042.autoescolas042.ViaCep.Model.DadosConsultaCEP;
import br.com.senai.s042.autoescolas042.ViaCep.Model.DadosDetalhamentoCEP;
import br.com.senai.s042.autoescolas042.ViaCep.Execption.CEPNotFoundException;
import br.com.senai.s042.autoescolas042.ViaCep.Execption.UnknownException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ViaCEPService {

    public DadosDetalhamentoCEP conssultar (DadosConsultaCEP dadosConsultaCEP){

        String url = "https://viacep.com.br/ws/" + dadosConsultaCEP.cep() + "/json/";

            try{
                String jsonResponse = Request.Get(url)
                        .connectTimeout(100000)
                        .socketTimeout(100000)
                        .execute()
                        .returnContent()
                        .asString();
                JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

                if (jsonObject.has ("erro") && jsonObject.get("erro").getAsBoolean()){
                    throw new CEPNotFoundException("CEP não encontrado!");
                }

                Gson gson = new Gson();

                return gson.fromJson(jsonResponse, DadosDetalhamentoCEP.class);

            } catch (IOException e ){
                System.out.println("Erro ao consultar o CEP: " + e.getMessage());
                throw new UnknownException("Erro ", e);
            }
    }
}
