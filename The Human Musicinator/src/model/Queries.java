package model;

import java.util.Map;
import java.util.HashMap;



/**
 * Created by user on 22/12/2018.
 */
public final class Queries {
    /**
     * TESTED.
     */

     public static final Map<String, String> HINT_QUERY_MAP;
     static {
             HINT_QUERY_MAP = new HashMap<>();
            // HINT_QUERY_MAP.put("yearQuery", "SELECT begin_date_year FROM artist WHERE id = ?");
            // HINT_QUERY_MAP.put("monthQuery", "SELECT begin_date_month FROM artist WHERE id = ?");
           //  HINT_QUERY_MAP.put("dayQuery", "SELECT begin_date_day FROM artist WHERE id = ?");
             HINT_QUERY_MAP.put("bornPlaceQuery", "SELECT area.name FROM area INNER JOIN artist ON area.id=artist.area and artist.id = ?");
             HINT_QUERY_MAP.put("genderQuery", "SELECT gender.name FROM gender INNER JOIN artist ON gender.id=artist.gender and artist.id = ?");
             HINT_QUERY_MAP.put("artistTypeQuery", "SELECT artist_type.name FROM artist_type INNER JOIN artist ON artist_type.id=artist.type and artist.id = ?");
             //HINT_QUERY_MAP.put("artistBornAreaTypeQuery", "SELECT area_type.name FROM area_type INNER JOIN area INNER JOIN artist ON area_type.id=area.type and area.id=artist.area and artist.id = ?");

            HINT_QUERY_MAP.put("englishAlbumOfArtist","select `release`.name from `release` INNER JOIN release_group ON release_group.id=`release`.release_group and release_group.type=(select id from release_group_primary_type where release_group_primary_type.name=\"Album\")\n" +
                 "              where `release`.language=(select id from language where language.name=\"English\") and `release`.artist_credit= ? ORDER BY RAND() LIMIT 1");

         //Todo: Coral Changed it, now it takes less time and take only id as argument. GAL: if you dont need the id remove it
             /*HINT_QUERY_MAP.put("englishAlbumOfArtist","select `release`.id,`release`.name from `release` INNER JOIN release_group ON release_group.id=`release`.release_group and release_group.type=(select id from release_group_primary_type where release_group_primary_type.name=\"Album\")\n" +
                     "              where `release`.language=(select id from language where language.name=\"English\") and `release`.artist_credit= ? ORDER BY RAND() LIMIT 1");
*/
             HINT_QUERY_MAP.put("englishSingleOfArtist","select `release`.name from `release` INNER JOIN release_group ON release_group.id=`release`.release_group and release_group.type=(select id from release_group_primary_type where release_group_primary_type.name=\"Single\")\n" +
                 "              where `release`.language=(select id from language where language.name=\"English\") and `release`.artist_credit= ? ORDER BY RAND() LIMIT 1");
             //QUERY_MAP.put("easySingleOfArtistQuery", "select `release`.id,`release`.name from `release` INNER JOIN release_country on release_country.release=`release`.id  INNER JOIN release_group ON release_group.id=`release`.release_group and release_group.type=(select id from release_group_primary_type where release_group_primary_type.name= ?)\n" +
             //" where `release`.language=(select id from language where language.name=\"English\") and `release`.artist_credit=(select id from artist_credit where artist_credit.name= ? limit 1) and release_country.date_year>2000 ORDER BY RAND() LIMIT 1");

             HINT_QUERY_MAP.put("numberOfArtistAlbumsQuery","select DISTINCT count(`release`.id) from `release` INNER JOIN release_group ON release_group.id=`release`.release_group and release_group.type=(select id from release_group_primary_type where release_group_primary_type.name=\"Album\") \n" +
                    "             and `release`.artist_credit= ?");

             HINT_QUERY_MAP.put("numberOfSingleAlbumsQuery","select DISTINCT count(`release`.id) from `release` INNER JOIN release_group ON release_group.id=`release`.release_group and release_group.type=(select id from release_group_primary_type where release_group_primary_type.name=\"Single\") \n" +
                 "             and `release`.artist_credit= ?");


             HINT_QUERY_MAP.put("mostFreqReleasesAreaOfArtistQuery","select area.name from area where area.id=(\n" +
                     "-- \n" +
                     "select area from country_area \n" +
                     "INNER JOIN release_country ON release_country.country=country_area.area\n" +
                     "inner join `release` on `release`.id=release_country.release\n" +
                     " \n" +
                     "\t\tand `release`.artist_credit=? group by area order by count(`release`.name) DESC limit 1\n" +
                     "         )");

             HINT_QUERY_MAP.put("mostActiveYearArtistQuery","select release_country.date_year from release_country \n" +
                     "inner join `release` on `release`.id=release_country.release\n" +
                     "where `release`.artist_credit= ? group by date_year order by count(`release`.name) DESC limit 1");

             HINT_QUERY_MAP.put("mostFreqLanguageAlbumsArtist","select language.name from language where language.id=(\n" +
                     "\n" +
                     "select language from `release` \n" +
                     "INNER JOIN release_group ON release_group.id=`release`.release_group\n" +
                     " and release_group.type=(select id from release_group_primary_type where release_group_primary_type.name=\"Album\") \n" +
                     "\t\tand `release`.artist_credit= ? group by language order by count(`release`.name) DESC limit 1\n" +
                     "        )");

             HINT_QUERY_MAP.put("mostFreqLanguageSinglesArtist","select language.name from language where language.id=(\n" +
                 "\n" +
                 "select language from `release` \n" +
                 "INNER JOIN release_group ON release_group.id=`release`.release_group\n" +
                 " and release_group.type=(select id from release_group_primary_type where release_group_primary_type.name=\"Single\") \n" +
                 "\t\tand `release`.artist_credit= ? group by language order by count(`release`.name) DESC limit 1\n" +
                 "        )");


             //QUERY_MAP.put("languageOfReleaseQuery", "select language.name from language inner join `release` on `release`.language=language.id where `release`.id= ?");
//             QUERY_MAP.put("albumRandomQuery", "select `release`.name from `release` INNER JOIN release_group ON release_group.id=`release`.release_group and release_group.type=(select id from release_group_primary_type where release_group_primary_type.name=\"Album\") ORDER BY RAND() LIMIT 1");
             //QUERY_MAP.put("albumRandomOfArtistQuery", "select `release`.id,`release`.name from `release` INNER JOIN release_group ON release_group.id=`release`.release_group and release_group.type=(select id from release_group_primary_type where release_group_primary_type.name=\"Album\") where `release`.language=(select id from language where language.name=\"English\") and `release`.artist_credit=(select id from artist_credit where artist_credit.name= ? limit 1) ORDER BY RAND() LIMIT 1");
             //QUERY_MAP.put("singleRandomOfArtistQuery", "select `release`.id,`release`.name from `release` INNER JOIN release_group ON release_group.id=`release`.release_group and release_group.type=(select id from release_group_primary_type where release_group_primary_type.name=\"Single\") where `release`.language=(select id from language where language.name=\"English\") and `release`.artist_credit=(select id from artist_credit where artist_credit.name= ? limit 1) ORDER BY RAND() LIMIT 1");

         //fixed!
              //HINT_QUERY_MAP.put("artistAliasNameQuery", "select artist_alias.name from artist_alias where artist_alias.artist= ? order by artist_alias.last_updated DESC limit 1");

            //TODO: Gal,here the argument need to be the id of release since it return the release country
              //HINT_QUERY_MAP.put("getReleaseCountryQuery", "select area.name from area inner join country_area on country_area.area=area.id inner join release_country on release_country.country=country_area.area inner join `release` on `release`.id=release_country.release and `release`.id= ?");

         //QUERY_MAP.put("getNumberOfArtistAlbumsQuery", "select count(`release`.name) from `release` INNER JOIN release_group ON release_group.id=`release`.release_group \n"+
             //"and release_group.type=(select id from release_group_primary_type where release_group_primary_type.name=\"Album\") \n"+
             //"and `release`.artist_credit=(select id from artist_credit where artist_credit.name= ? limit 1)");
     }
}
