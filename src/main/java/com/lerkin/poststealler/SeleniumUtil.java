package com.lerkin.poststealler;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.function.Supplier;

public class SeleniumUtil {

    private static Supplier<WebDriver> driverSupplier;

    static {
        WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
        driverSupplier = FirefoxDriver::new;
    }

    public static WebDriver initDriver() {
        WebDriver driver = driverSupplier.get();
        return driver;
    }
}
