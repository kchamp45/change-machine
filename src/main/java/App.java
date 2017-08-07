import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

/**
 * Created by spunek, kim on 8/7/17.
 */
public class App {

    public static void main(String[] args) {


        get("/form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String customer = request.queryParams("customer");
            String cashInput = request.queryParams("cashInput");
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/coin_change", (request,  response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String customer = request.queryParams("customer");
            Float cashInput = Float.parseFloat(request.queryParams("cashInput"));
            model.put("customer", customer);
            model.put("cashInput", cashInput);

            ChangeMachine machine = new ChangeMachine();
            String coinChange = machine.makeChange(cashInput);

            model.put("change", coinChange);
            return new ModelAndView(model, "coin_change.hbs");

        }, new HandlebarsTemplateEngine());


//        ;
//
//


    }

}
