import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by Albert on 29/01/2017.
 */
public class RestSync {
    private static Retrofit retrofit;

    //DB connection
    public static void main(String[] args) throws IOException {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AtletaService atletaService = retrofit.create(AtletaService.class);

        //GetAllAtletas
        Call<List<Atleta>> call = atletaService.GetAllAtletas();
        Response<List<Atleta>> response= call.execute();
        if(response.isSuccessful()) {
            List<Atleta> atletasList = response.body();
            System.out.println("Status code: " + response.code() + System.lineSeparator() +
                    "GetAllAtletas: " + atletasList);
        } else {
            System.out.println("Status code: " + response.code() +
                    "Error: " + response.errorBody());
        }

        //getError
        call = atletaService.getError();
        response= call.execute();
        if(!response.isSuccessful()) {
            System.out.println("Status code: " + response.code() + System.lineSeparator() +
                    "Error: " + response.raw() );
        }

        //POST Atleta
        Atleta atleta = new Atleta();
        atleta.setNombre("Albert");
        atleta.setApellido("Pellegero");
        atleta.setNacionalidad("Es");
        Call<Atleta> callAtleta = atletaService.crearAtleta(atleta);
        Response<Atleta> responseAtleta= callAtleta.execute();

        if(responseAtleta.isSuccessful()) {
            //CrearAtleta
            Atleta atletaResp = responseAtleta.body();
            System.out.println("Status code: " + responseAtleta.code() + System.lineSeparator() +
                    "Atleta añadido: " + atletaResp);

            //UpdateAtleta
            atletaResp.setApellido("Montané");
            callAtleta = atletaService.updateAtleta(atletaResp);
            responseAtleta= callAtleta.execute();
            System.out.println("Status code: " + responseAtleta.code() + System.lineSeparator() +
                    "Atleta modificado: " + responseAtleta.body());

            //GetAllAtleta de Comprobacion*
            call = atletaService.GetAllAtletas();
            response= call.execute();
            System.out.println("Status code: " + response.code() + System.lineSeparator() +
                    "GetAllAtletas: " + response.body());

            //DeleteAtletaId
            Call<Void> callDelete= atletaService.deleteAtletaId(atletaResp.getId());
            Response<Void> responseDelete= callDelete.execute();
            System.out.println("Status code: " + responseDelete.code());

            //GetAllAtleta de Comprobacion*
            call = atletaService.GetAllAtletas();
            response= call.execute();
            System.out.println("Status code: " + response.code() + System.lineSeparator() +
                    "GetAllAtletas: " + response.body());
        } else {
            System.out.println("Error de conexion.");
            System.out.println("Status code: " + responseAtleta.code() +
                    "Error: " + responseAtleta.errorBody());
        }

        //GetAtleta
        callAtleta = atletaService.getAtleta(2L);
        responseAtleta = callAtleta.execute();
        if(responseAtleta.isSuccessful()) {
            System.out.println("Status code: " + responseAtleta.code() + System.lineSeparator() +
                    "GetAtleta: " + responseAtleta.body() );
        }
    }
}
