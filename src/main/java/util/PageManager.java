package util;

import annotation.PageTitle;
import exception.AutotestError;
import org.openqa.selenium.support.PageFactory;
import org.reflections.Reflections;
import pages.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ramich on 03.10.2018.
 */
public class PageManager {

    private Map<String, String> pages = new HashMap<>();
    private Page currentPage;
    private static PageManager pageManager;

    private PageManager() {
        Reflections reflections = new Reflections("pages");
        reflections
                .getTypesAnnotatedWith(PageTitle.class)
                .forEach(clazz -> pages.put(clazz.getAnnotation(PageTitle.class).value(), clazz.getName()));
    }

    public static PageManager getPageManager() {
        if(pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    private <T extends Page> T getPage(String title) throws AutotestError {
        try {
            return (T) PageFactory.initElements(DriverManager.getDriver(), Class.forName(pages.get(title)));
        } catch (ClassNotFoundException e) {
            throw new AutotestError(String.format("Page with title %s is not found", title), e);
        }
    }

    public void setCurrentPageAs(String title) throws AutotestError {
        currentPage = getPage(title);
    }


    public Page getCurrentPage() throws AutotestError {
        if(currentPage != null) {
            return currentPage;
        } else {
            throw new AutotestError("Current page isn't set yet");
        }
    }
}
