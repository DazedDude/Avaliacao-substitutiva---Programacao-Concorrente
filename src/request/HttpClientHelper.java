package request;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class HttpClientHelper {
	private String teste = "https://historical-forecast-api.open-meteo.com/v1/forecast?latitude=-10.9167&longitude=37.05&start_date=2024-01-01&end_date=2024-01-31&hourly=temperature_2m";
    private static final String BASE_URL = "https://historical-forecast-api.open-meteo.com/v1/forecast";

    public static JSONObject fetchData(double latitude, double longitude, String startDate, String endDate) throws Exception {
        String url = String.format("%s?latitude=%.4f&longitude=%.4f&start_date=%s&end_date=%s&hourly=temperature_2m", BASE_URL, latitude, longitude, startDate, endDate);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }
}