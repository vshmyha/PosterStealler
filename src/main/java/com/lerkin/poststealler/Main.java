package com.lerkin.poststealler;

import org.openqa.selenium.WebDriver;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        List<String> animeName = JDBCConnector.getAnimeNames();
        WebDriver driver = SeleniumUtil.initDriver();
        AnimeScriptInfo animeScriptInfo = Script.mainScript(driver, animeName);
        System.out.println(animeScriptInfo);
    }
}
