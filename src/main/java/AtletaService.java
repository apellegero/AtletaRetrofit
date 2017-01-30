import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by Albert on 29/01/2017.
 */
public interface AtletaService {

    @GET("/atleta")
    Call<List<Atleta>> GetAllAtletas();

    @POST("/atleta")
    Call<Atleta> crearAtleta(@Body Atleta atleta);

    @POST("/atleta/{id}")
    Call<Void> deleteAtletaId(@Path("id") Long id);

    @GET("/atleta/{id}")
    Call<Atleta> getAtleta(@Path("id") Long id);

    @GET("/atletaError")
    Call<List<Atleta>> getError();

    @PUT("/atleta")
    Call<Atleta> updateAtleta(@Body Atleta atleta);

    /*
    @GET("/atleta/nacionalidadLike/{nacionalidad}")
    Call<List<Atleta>> findByNacinalidadLike(@Path("nacionalidad") String nacionalidad);

    @GET("/atleta/fechaNacimientoLessThan/{fechaNacimiento}")
    Call<List<Atleta>> findByFecha_nacimientoLessThan(@Path("fechaNacimiento") Date fechaNacimiento);

    @GET("/atleta/ordenarPerNacionalidad")
    Call<Map<String, List<Atleta>>> findByNacionalidadIs();

    @GET("/atleta/groupByCategoriaMedalla")
    Call<Map<CategoriaMedalla, List<Atleta>>> getAtletaGroupByCategoriaMedalla();
    */
}
