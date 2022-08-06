public class Flight
{
    private String timeOfArrival;
    private String timeOfDeparture;
    private String  startingAirport;
    private String landingAirport;

    public String getTimeOfArrival()
    {
        return timeOfArrival;
    }

    public String getTimeOfDeparture()
    {
        return timeOfDeparture;
    }

    public String getStartingAirport()
    {
        return startingAirport;
    }

    public String getLandingAirport()
    {
        return landingAirport;
    }

    public Flight(String timeOfArrival, String timeOfDeparture, String startingAirport, String landingAirport)
    {
        this.timeOfArrival = timeOfArrival;
        this.timeOfDeparture = timeOfDeparture;
        this.startingAirport = startingAirport;
        this.landingAirport = landingAirport;
    }
}
