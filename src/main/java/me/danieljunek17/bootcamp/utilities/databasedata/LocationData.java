package me.danieljunek17.bootcamp.utilities.databasedata;

import me.danieljunek17.bootcamp.objects.Location;
import me.jarnoboy404.databases.DatabaseConnection;
import me.jarnoboy404.databases.DatabaseResult;
import me.jarnoboy404.databases.QueryParam;

import java.util.concurrent.CompletableFuture;

public class LocationData extends DatabaseConnection {
    public LocationData(DatabaseConnection databaseConnection) {
        super(databaseConnection);
    }

    //create tables if they don't excist yet
    public void createTables() {
        executeUpdateAsync("CREATE TABLE IF NOT EXISTS Locations (" +
                "id int," +
                "x int," +
                "y int," +
                "z int);");
    }

    //create a new location in the database
    public void newLocation(int id, int x, int y , int z) {
        QueryParam queryParam = new QueryParam();
        queryParam.addQueryParam("id", id);
        queryParam.addQueryParam("x", x);
        queryParam.addQueryParam("y", y);
        queryParam.addQueryParam("z", z);

        executeUpdate(queryParam.getInsertQuery("Locations"));
    }

    //load all the locations from the database
    public void loadLocations() {
        DatabaseResult result = executeQuery("SELECT * FROM `Locations`");
        while(result.getResult()) {
            Location.Manager.newLocation(result.getInt("id"), new Location(result.getInt("x"),result.getInt("y"),result.getInt("z")));
        }
        result.endResult();
    }

    //remove a location from the database (maybe comming soon)
/*    public CompletableFuture<Void> removeLocation(int id) {
        QueryParam whereQuery = new QueryParam();
        whereQuery.addQueryParam("id", id);

        return executeUpdateAsync("DELETE FROM Locations" + whereQuery.getWhereQuery());
    }*/

}
