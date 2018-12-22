package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 22/12/2018.
 */
public class Queries {
    //select random id of an artist from artists table
    public static final String idQuery = "SELECT id FROM artist ORDER BY RAND() LIMIT 1";

    //select the date of birth of an artist according to it's id
    public static final String yearQuery = "select begin_date_year from artist where id = ";
    public static final String monthQuery = "select begin_date_month from artist where id = ";
    public static final String dayQuery = "select begin_date_day from artist where id = ";

    //select the place which the artist born in
    public static final String bornPlaceQuery = "SELECT area.name FROM area INNER JOIN artist ON area.id=artist.area and artist.id =";

    public static void main(String[] args) {

    }









}
