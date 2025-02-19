package com.automation.webdrivermenegar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverManagerUtil {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void initializeDriver(String browserName) {
        if (driverThreadLocal.get() == null) {
            if (browserName.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driverThreadLocal.set(new ChromeDriver());
            } else if (browserName.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driverThreadLocal.set(new FirefoxDriver());
            }
        }
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}

