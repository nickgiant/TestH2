package com.testh2.TestH2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Service {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String createTable() {

        try {
            jdbcTemplate.execute("create table if not exists employees (id int primary key "
                    + "auto_increment, name varchar(30), email varchar(30))");
        } catch (Exception e) {
            return "error message:" + e.getMessage();
        }

        return "table is created or already exists";
    }

    public String insertData() {
        int intCount = 0;
        intCount = jdbcTemplate.update("insert into employees (name, email) "
                + "values ('Nick Smith', 'nick.smith@holywood.com')");

        intCount = intCount + jdbcTemplate.update("insert into employees (name, email) "
                + "values ('Bill Gates', 'bill.gates@microsoft.com')");

        return "inserted records:" + intCount;
    }

    public String fetch() {
        String outcome = "";
        try {
            List<String> list = jdbcTemplate.queryForList("select name from employees", String.class);
            if (list == null || list.isEmpty()) {
                outcome = "table exists but there is no data";
            } else {
                List<String> plist = new ArrayList<>();
                list.forEach(name -> plist.add(name));

                for (int r = 0; r < plist.size(); r++) {
                    outcome = outcome + "<p>" + plist.get(r) + "</p>";
                }
            }
        } catch (Exception e) {
            outcome = "error message:" + e.getMessage();
        }
        return outcome;

    }

    public String deleteData() {
        int intCount = 0;
        try {
            intCount = jdbcTemplate.update("delete employees");
            return "deleted records:" + intCount;
        } catch (Exception e) {
            return "deleted records error message:" + e.getMessage();
        }

    }
}

