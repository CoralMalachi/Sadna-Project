package model;

//Todo: tables we need:
/*
    new tables :

    release_group_primary_type
    release_group
    release
    area_type
    language
    artist_credit
    artist_credit_name
    artist_alias

    old:

    area
    artist
    track

    gender
    recording - ?
    l_artist_artist - ??



 */

/**
 * Created by user on 22/12/2018.
 */
public class Queries {
    //select random id and name of an artist from artists table
    public static final String idQuery = "SELECT id, name FROM artist ORDER BY RAND() LIMIT 1";

    //select random id and name of an artist from artists table - only from artists
    //public static final String idOnlyArtistQuery = "SELECT artist.id,artist.name FROM artist where artist.type=1 ORDER BY RAND() LIMIT 1";

    //NESTED QUERY - tested in mysql work - workbrench
    public static final String idOnlyArtistQuery = "SELECT artist.id,artist.name FROM artist where artist.type=(select id from artist_type where artist_type.name=\"Person\") ORDER BY RAND() LIMIT 1";

    //select random id and name of an artist from artists table - only from artists
    public static final String idOnlyGroupQuery = "SELECT artist.id,artist.name FROM artist where artist.type=(select id from artist_type where artist_type.name=\"Group\") ORDER BY RAND() LIMIT 1";
    //public static final String idOnlyGroupQuery = "SELECT artist.id,artist.name FROM artist where artist.type=2 ORDER BY RAND() LIMIT 1";

    //select the date of birth of an artist according to it's id
    public static final String yearQuery = "select begin_date_year from artist where id = ?";
    public static final String monthQuery = "select begin_date_month from artist where id = ?";
    public static final String dayQuery = "select begin_date_day from artist where id = ?";

    //select the place which the artist born in
    public static final String bornPlaceQuery = "SELECT area.name FROM area INNER JOIN artist ON area.id=artist.area and artist.id = ?";

    //Todo : Add more queries

    //select the gender of the artist
    public static final String genderQuery = "SELECT gender.name FROM gender INNER JOIN artist ON gender.id=artist.gender and artist.id = ?";

    //select the type of the artist - Person / Group (band) / Choir / Orchestra / Character / Other
    public static final String artistTypeQuery = "SELECT artist_type.name FROM artist_type INNER JOIN artist ON artist_type.id=artist.type and artist.id = ?";

    //select the type of area the artist is born -Country / Island / City / ...
    public static final String artistBornAreaTypeQuery = "SELECT area_type.name FROM area_type INNER JOIN area INNER JOIN artist ON area_type.id=area.type and area.id=artist.area and artist.id = ?";


    //select random song of the artist - by level (easy/hard)

    //select the language of song

    //select a random album - nested query
    public static final String albumRandomQuery = "select `release`.name from `release` INNER JOIN release_group ON release_group.id=`release`.release_group and release_group.type=(select id from release_group_primary_type where release_group_primary_type.name=\"Album\") ORDER BY RAND() LIMIT 1";

    //select random album of the artist - by level (easy/hard) - use Release group & release_group_primary_type (tell us if its a album/single

    //select random album by artist and language TODO:add input artist name
    public static final String albumRandomOfArtistQuery = "select `release`.id,`release`.name from `release` INNER JOIN release_group ON release_group.id=`release`.release_group and release_group.type=(select id from release_group_primary_type where release_group_primary_type.name=\"Album\") where `release`.language=(select id from language where language.name=\"English\") and `release`.artist_credit=(select id from artist_credit where artist_credit.name=\"[Disney]\" limit 1) ORDER BY RAND() LIMIT 1";

    //get artist_alias of the artist

    /**
     * UNTESTED.
     */
    //number of albums artist has - FIRST DRAFT UNTESTED.
    public static final String numberOfReleasesQuery = "SELECT count(*) FROM release_group INNER JOIN artist_credit INNER JOIN artist_credit_name ON release_group.artist_credit = artist_credit.id AND artist_credit.name = artist_credit_name.name AND atrist_credit_name.artist = ?";


    /**
     * TESTED.
     */
    // Checks if a username is already in use (registered) when trying to register a username. Set should be empty if the username is not taken.
    public static final String checkIfRegisteredQuery = "SELECT * FROM UserList WHERE UserName = ?";

    // Registeres a new username.
    public static final String registerUserUpdate = "INSERT INTO Userlist VALUES ('0', ?, ?)";

    // Tries to login a user, checks if the username and password given are in the database.
    public static final String loginUserQuery = "SELECT * FROM Userlist WHERE UserName = ? AND UserPassword = ?";





}
