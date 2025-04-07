package com.tuantran.CarShowroom.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class Controller {

    @GetMapping("/")
    public ResponseEntity<String> getGreeting() {
        String name = "Tuan Tran";
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedDate = currentDate.format(formatter);

        String html = "<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "    <meta charset='UTF-8'>" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "    <title>Welcome</title>" +
                "    <style>" +
                "        body {" +
                "            margin: 0;" +
                "            padding: 0;" +
                "            height: 100vh;" +
                "            display: flex;" +
                "            align-items: center;" +
                "            justify-content: center;" +
                "            background: linear-gradient(to right, #74ebd5, #acb6e5);" +
                "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;" +
                "        }" +
                "        .greeting-box {" +
                "            text-align: center;" +
                "            background-color: white;" +
                "            padding: 40px;" +
                "            border-radius: 15px;" +
                "            box-shadow: 0 8px 20px rgba(0,0,0,0.2);" +
                "        }" +
                "        h1 { color: #2c3e50; font-size: 2.5em; margin-bottom: 10px; }" +
                "        p { color: #34495e; font-size: 1.2em; margin: 5px 0; }" +
                "        a {" +
                "            display: inline-block;" +
                "            margin-top: 15px;" +
                "            text-decoration: none;" +
                "            color: white;" +
                "            background-color: #3498db;" +
                "            padding: 10px 20px;" +
                "            border-radius: 5px;" +
                "            transition: background-color 0.3s ease;" +
                "        }" +
                "        a:hover {" +
                "            background-color: #2980b9;" +
                "        }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <div class='greeting-box'>" +
                "        <h1>Hello! I am " + name + " 👋</h1>" +
                "        <p>Welcome to the <strong>Car Showroom</strong> system.</p>" +
                "        <p>Today is <strong>" + formattedDate + "</strong>.</p>" +
                "        <p>Wishing you a great day ahead ✨</p>" +
                "        <a href='/swagger-ui/index.html' target='_blank'>Go to Swagger UI</a>" +
                "    </div>" +
                "</body>" +
                "</html>";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        return new ResponseEntity<>(html, headers, HttpStatus.OK);
    }

}
