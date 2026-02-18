package com.aw.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.Test;
import org.openqa.selenium.Dimension;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class Androidtest {
    AndroidDriver androidDriver;
    @Test
    public void login() throws MalformedURLException, InterruptedException {
        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
        uiAutomator2Options.setPlatformName("Android");
        uiAutomator2Options.setPlatformVersion("14.0");
        uiAutomator2Options.setDeviceName("Samsung");
        uiAutomator2Options.setAutomationName("UiAutomator2");
        //uiAutomator2Options.setApp("C:\\Users\\vchinni\\IdeaProjects\\AppiumWorkshop\\src\\main\\resources\\AndroidSauceLabs.apk");
        uiAutomator2Options.setApp("bs://bd4a70c4844dd942ccf064338715a3c7e6213c49");

        //uiAutomator2Options.setAppPackage("com.swaglabsmobileapp");
        //uiAutomator2Options.setAppWaitActivity("com.swaglabsmobileapp.MainActivity");

        //androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), uiAutomator2Options);
        androidDriver = new AndroidDriver(new URL("https://venkatapavanchin_0Likjg:sjuxUxpT5FkkXU2wpJeW@hub.browserstack.com/wd/hub"), uiAutomator2Options);


        Thread.sleep(5000);
        androidDriver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys("standard_user");
        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Password\")")).sendKeys("secret_sauce");
        androidDriver.findElement(AppiumBy.xpath("//*[@content-desc=\"test-LOGIN\"]")).click();
        Thread.sleep(5000);

        swipeVertical();
        Thread.sleep(3000);

        androidDriver.findElement(AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]")).click();
        androidDriver.findElement(AppiumBy.accessibilityId("test-Cart")).click();
        Thread.sleep(2000);
        swipeHorizontal();
        androidDriver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Delete\"]")).click();


        //  androidDriver.quit();
    }

    public void swipeVertical(){
        Dimension dimension = androidDriver.manage().window().getSize();
        System.out.println(dimension);

        int x = dimension.getWidth() /2;

        int startY = (int) (dimension.getHeight() * 0.9);
        int endY = (int) (dimension.getHeight() * 0.3);


        PointerInput pointerInput = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(pointerInput, 1);

        sequence.addAction(pointerInput.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, startY));
        sequence.addAction(pointerInput.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        sequence.addAction(pointerInput.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), x, endY));
        sequence.addAction(pointerInput.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        androidDriver.perform(Collections.singletonList(sequence));
    }

    public void swipeHorizontal(){
        Dimension dimension = androidDriver.manage().window().getSize();
        System.out.println(dimension);

        int startX = (int) (dimension.getWidth() * 0.8);
        int endX = (int) (dimension.getWidth() * 0.4);

        int y = dimension.getHeight()/3;

        PointerInput pointerInput = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(pointerInput, 1);

        sequence.addAction(pointerInput.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, y));
        sequence.addAction(pointerInput.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        sequence.addAction(pointerInput.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), endX, y));
        sequence.addAction(pointerInput.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        androidDriver.perform(Collections.singletonList(sequence));
    }
}
