import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Wether {
    //003d9ddb8d16fa46ce2aff594374b7b7
    public static String getWether(String message, Model model) throws IOException {
       URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=003d9ddb8d16fa46ce2aff594374b7b7");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()){
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        model.setFeel(main.getDouble("feels_like"));
        model.setPressure(main.getDouble("pressure"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String)obj.get("main"));
        }
        return  "Город: " + model.getName()+ "\n" +
                "Температура: " + model.getTemp() + " °C" + "\n" +
                "Ощущается как: " + model.getFeel() + " °C" + "\n" +
                "Влажность: " + model.getHumidity() + " %" + "\n" +
                "Атм. давление: " + model.getPressure() + "мм.рт.ст" + "\n" +
                model.getMain() + "\n" +
                "http://openweathermap.org/img/wn/" + model.getIcon() + ".png";
    }
}
