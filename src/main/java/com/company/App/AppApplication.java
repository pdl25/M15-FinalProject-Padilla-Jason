package com.company.App;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class AppApplication {
	static void Weather() {
		System.out.println("\nWEATHER IN A CITY");
		while(true) {
			Scanner userInput = new Scanner(System.in);
			System.out.printf("Enter city name or 'B' to go back: ");
			String city = userInput.nextLine();
			if(city.equals("B")){
				break;
			}
			else {
				try {
					WebClient client = WebClient.create("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=5b430fa8c6e04027e005f8bd80b984dd&units=imperial");
					Mono<WeatherResponse> response = client.get().retrieve().bodyToMono(WeatherResponse.class);
					WeatherResponse json = response.share().block();
					System.out.println("\nCity: " + json.name + " " + json.main.temp + "F\u00B0");
					System.out.println("Description: " + json.weather[0].description);
					System.out.println("Longitude: " + json.coord.lon + " Latitude: " + json.coord.lat);
					System.out.println();
				} catch(Exception e) {
					System.out.println("\nInvalid City Please Try Again.");
					System.out.println();
				}
			}
		}
	}
	static void ISS() {
		System.out.println("\nISS LOCATION");
		while(true) {
			try {
				WebClient client = WebClient.create("http://api.open-notify.org/iss-now.json");
				Mono<SpaceResponse> response = client.get().retrieve().bodyToMono(SpaceResponse.class);
				SpaceResponse json = response.share().block();
				String lon = json.iss_position.longitude;
				String lat = json.iss_position.latitude;
				WebClient client2 = WebClient.create("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=5b430fa8c6e04027e005f8bd80b984dd&units=imperial");
				Mono<WeatherResponse> response2 = client2.get().retrieve().bodyToMono(WeatherResponse.class);
				WeatherResponse json2 = response2.share().block();
				String location;
				System.out.println("\nLongitude: " + lon);
				System.out.println("Latitude: " + lat);
				if (json2.sys.country == null) {
					location = "Location: Not currently in a country";
				} else {
					location = "Location: " + json2.name + "," + json2.sys.country;
				}
				System.out.println(location);
				System.out.println();

				Scanner userInput = new Scanner(System.in);
				System.out.printf("Press Enter to rerun or 'B' to go back: ");
				String coin = userInput.nextLine();
				if (coin.equals("B")) {
					break;
				}
			} catch(Exception e) {
				System.out.println("\nError occurred please try again.");
				System.out.println();
			}
		}
	}
	static void WeatherISS() {
		System.out.println("\nWEATHER OF ISS");
		while(true) {
			try {
				WebClient client = WebClient.create("http://api.open-notify.org/iss-now.json");
				Mono<SpaceResponse> response = client.get().retrieve().bodyToMono(SpaceResponse.class);
				SpaceResponse json = response.share().block();
				String lon = json.iss_position.longitude;
				String lat = json.iss_position.latitude;
				WebClient client2 = WebClient.create("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=5b430fa8c6e04027e005f8bd80b984dd&units=imperial");
				Mono<WeatherResponse> response2 = client2.get().retrieve().bodyToMono(WeatherResponse.class);
				WeatherResponse json2 = response2.share().block();
				String location;
				System.out.println("Longitude: " + lon);
				System.out.println("Latitude: " + lat);
				if (json2.sys.country == null) {
					location = "Location: Not currently in a country";
				} else {
					location = "Location: " + json2.name + "," + json2.sys.country;
				}
				System.out.println("Weather: " + json2.main.temp + "F\u00B0");
				System.out.println("Description: " + json2.weather[0].description);
				System.out.println(location);
				System.out.println();

				Scanner userInput = new Scanner(System.in);
				System.out.printf("Enter city name or 'B' to go back: ");
				String key = userInput.nextLine();
				if (key.equals("B")) {
					break;
				}
			} catch (Exception e) {
				System.out.println("\nError occurred please try again.");
				System.out.println();
			}
		}
	}
	static void Crypto() {
		System.out.println("\nCRYPTO PRICES");
		while(true) {
			Scanner userInput = new Scanner(System.in);
			System.out.printf("Enter crypto abbreviation or 'B' to go back: ");
			String coin = userInput.nextLine();
			if(coin.equals("B")){
				break;
			}
			else {
				try {
					WebClient client = WebClient.create("https://rest.coinapi.io/v1/assets/" + coin + "?apikey=919353ED-A83D-47CB-BE8E-5F6F3C1F2CF1");
					Mono<CryptoResponse[]> response = client.get().retrieve().bodyToMono(CryptoResponse[].class);
					CryptoResponse[] json = response.share().block();
					System.out.println("\nID: " + json[0].asset_id);
					System.out.println("Name: " + json[0].name);
					if(json[0].price_usd  >= 0.01 ) {
						String price = String.format("%.2f", json[0].price_usd);
						double amount = Double.parseDouble(price);
						DecimalFormat formatted = new DecimalFormat("Price: $#,###.00");
						System.out.println(formatted.format(amount));
					}
					else {
						String price = String.format("Price: $%f",json[0].price_usd);
						System.out.println(price);
					}
					System.out.println();
				} catch( Exception e) {
					System.out.println("\nPlease enter a valid crypto name.");
					System.out.println();
				}
			}
		}
	}
	public static void main(String[] args) {
		while(true) {
			Scanner userInput = new Scanner(System.in);
			System.out.println("\nW - Weather in a City");
			System.out.println("I - Location of the International Space Station");
			System.out.println("WI - Weather in the location of the ISS");
			System.out.println("C - Current Crypto Prices");
			System.out.println("E - Exit");
			System.out.printf("SELECT AN OPTION: ");
			String key = userInput.nextLine();
			if(key.equals("W")) {
				Weather();
			}
			else if(key.equals("I")) {
				ISS();
			}
			else if(key.equals("WI")) {
				WeatherISS();
			}
			else if(key.equals("C")) {
				Crypto();
			}
			else if(key.equals("E")) {
				System.out.println("Goodbye!");
				break;
			}
			else {
				System.out.println("\nPlease Enter A Valid Option");
			}
		}
	}

}
