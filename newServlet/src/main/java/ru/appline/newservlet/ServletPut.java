package ru.appline.newservlet;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.newservlet.logic.Model;
import ru.appline.newservlet.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/put")
public class ServletPut extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);
        request.setCharacterEncoding("UTF-8");
        int id = jobj.get("id").getAsInt();

        response.setContentType("application/json;charset=utf-8");

        boolean containsKeyId = model.getFromList().containsKey(id);
        PrintWriter pw = response.getWriter();

        if (containsKeyId) {
            User user = model.getFromList().get(id);

            user.setName(jobj.get("name").getAsString());
            user.setSurname(jobj.get("surname").getAsString());
            user.setSalary(jobj.get("salary").getAsDouble());
            model.add(user, id);
            pw.print(gson.toJson(model.getFromList()));
        } else {
            pw.print(gson.fromJson("{\n\"message\" : \"Нет такого пользователя с id=" + id + "\"\n}", JsonObject.class));
        }
    }

}
