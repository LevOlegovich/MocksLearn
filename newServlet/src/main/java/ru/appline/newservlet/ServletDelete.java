package ru.appline.newservlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.newservlet.logic.Model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
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


        if (model.getFromList().isEmpty()) {
            pw.print(gson.fromJson("{\n\"message\" : \"Пользователи отсутствуют в базе\"\n}", JsonObject.class));
        }
        if (!containsKeyId) {
            pw.print(gson.fromJson("{\n\"message\" : \"Нет такого пользоватлея с id=" + id + "\"\n}", JsonObject.class));
        } else {
            model.getFromList().remove(id);
            pw.print(gson.fromJson("{\n\"message\" : \"Пользователь с id=" + id + " успешно удален\"\n}", JsonObject.class));
        }

    }

}
