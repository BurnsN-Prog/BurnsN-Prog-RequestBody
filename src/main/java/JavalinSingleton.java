

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;


/**
 * Background: A json string representing a song will be sent in this POST request with the following fields: 
 *      songName, artistName
 */
public class JavalinSingleton {

    public static Javalin getInstance(){
        Javalin app = Javalin.create();
        ObjectMapper om = new ObjectMapper();
        
        /**
         * problem1: retrieve the song object from the request body...
         *      1. return the song object as JSON in the response body.
         * 
         * Note: Please refer to the "RequestBody.MD" file for more assistance.
         */
        app.post("/echo", ctx -> {
            
            //retrieve request body and put into a string variable
            String jsonString = ctx.body();

            // use jackson to convert the string to a java object
            ObjectMapper m = new ObjectMapper();
           Song song = m.readValue(jsonString, Song.class );

            // return the object as the response body, but converted as a JSON
             ctx.json(song);   
        });

        /**
         * problem2: retrieve the song object from the request body...
         *      1. update the artist in the song object to "Beatles"
         *      2. return the updated song object as JSON in the response body
         * 
         * Note: Please refer to the "RequestBody.MD" file for more assistance.
         */
        app.post("/changeartisttobeatles", ctx -> {

            //retrieve body and place into string variable
            String jsonObject = ctx.body();

            // use jackson to convert to Java object
            ObjectMapper mapper = new ObjectMapper();
            Song song = mapper.readValue(jsonObject, Song.class);

            //Set artist to "Beatles"
            song.setArtistName("Beatles");
            // return updated song as a json
            ctx.json(song);   
        });


        return app;
    }
    
}
