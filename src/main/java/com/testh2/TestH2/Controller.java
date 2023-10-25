package com.testh2.TestH2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;


@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    Service service;

    @GetMapping("setup")
    public String setup() {

       return service.createTable();
    }

    @GetMapping("insert")
    public String insert() {
        String strInserted = service.insertData();

        return strInserted;
    }

    @GetMapping("fetch")
    public String fetch() {
        String strRecords = service.fetch();

        return strRecords;
    }

    @GetMapping("delete")
    public String delete() {
        String strDeleted = service.deleteData();

        return strDeleted;
    }


    @GetMapping("dir")
    public String dir() {
        String path = Paths.get(".").toAbsolutePath().normalize().toString();
        String separator = System.getProperty("file.separator");
        String fileoutput = path + separator;
        return "  dir:" + fileoutput;
    }
}
