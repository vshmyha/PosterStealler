package com.lerkin.poststealler;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class Script {

    public static AnimeScriptInfo mainScript(WebDriver webDriver, List<String> animeName) {

        AnimeScriptInfo animeScriptInfo = new AnimeScriptInfo();

        webDriver.get("https://shikimori.one");
        waitFor(5);

        HashMap<String, String> addToMap;
        List<String> addToList;

        for (int i = 0; i < animeName.size(); i++) {
            WebElement parentSearchLine = webDriver.findElement(By.className("field"));
            waitFor(2);
            WebElement searchLine = parentSearchLine.findElement(By.tagName("input"));
            String anime = animeName.get(i);
            searchLine.sendKeys(anime);
            waitFor(2);
            AnimePoster animePoster = getPosterLink(anime, webDriver);
            if (animePoster.founded) {
                addToMap = animeScriptInfo.getFounded();
                addToMap.put(animePoster.animeName, animePoster.posterLink);
            } else {
                addToList = animeScriptInfo.getNotFounded();
                addToList.add(animePoster.animeName);
            }
        }
        return animeScriptInfo;
    }

    private static AnimePoster getPosterLink(String anime, WebDriver webDriver) {

        AnimePoster animePoster;
        try {
            List<WebElement> searchResults = webDriver.findElements(By.className("b-db_entry-variant-list_item"));
            WebElement searchedAnime = searchResults.get(0);
            click(searchedAnime);
            WebElement divPoster = webDriver.findElement(By.className("c-poster"));
            WebElement poster = divPoster.findElement(By.tagName("img"));
            String posterLink = poster.getAttribute("src");

            animePoster = new AnimePoster(anime, posterLink, true);
        } catch (NoSuchElementException e) {
            animePoster = new AnimePoster(anime, false);
        }
        return animePoster;
    }

    private static void click(WebElement webElement) {
        click(webElement, 3);
    }

    private static void click(WebElement webElement, int waitForSec) {

        webElement.click();
        waitFor(waitForSec);
    }

    private static void waitFor(int sec) {

        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    @Data
    static class AnimePoster {

        String animeName;
        String posterLink;
        boolean founded;

        public AnimePoster(String animeName, boolean founded) {
            this.animeName = animeName;
            this.founded = founded;
        }

        public AnimePoster(String animeName, String posterLink, boolean founded) {
            this.animeName = animeName;
            this.posterLink = posterLink;
            this.founded = founded;
        }
    }
}

