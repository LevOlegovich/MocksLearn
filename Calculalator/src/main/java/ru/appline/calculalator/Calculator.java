package ru.appline.calculalator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/calculator")
public class Calculator extends HttpServlet {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

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
        String operator = jobj.get("math").getAsString();

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();

        if (operator.equals("+")) {
            double resultPlus = jobj.get("a").getAsDouble() + jobj.get("b").getAsDouble();
            pw.print(gson.fromJson("{\nresult: " + resultPlus + "\n}", JsonObject.class));
        } else if (operator.equals("-")) {
            double resultMinus = jobj.get("a").getAsDouble() - jobj.get("b").getAsDouble();
            pw.print(gson.fromJson("{\n\"result\" : \"" + resultMinus + "\"\n}", JsonObject.class));
        } else if (operator.equals("*")) {
            double resultMultiplication = jobj.get("a").getAsDouble() * jobj.get("b").getAsDouble();
            pw.print(gson.fromJson("{\n\"result\" : \"" + resultMultiplication + "\"\n}", JsonObject.class));
        } else if (operator.equals("/")) {
            double resultDivision = jobj.get("a").getAsDouble() / jobj.get("b").getAsDouble();
            pw.print(gson.fromJson("{\n\"result\" : \"" + resultDivision + "\"\n}", JsonObject.class));
        } else {
            pw.print(gson.fromJson("{\n\"message\" : \"Ошибка ввода. Доступные операторы +, -, *, /\"\n}", JsonObject.class));
        }
    }

}
