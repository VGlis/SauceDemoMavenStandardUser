package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

    public WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {

    }

    public boolean isProductInCart(String productName) {
        boolean toReturn = false;

        WebElement cartList = driver.findElement(By.xpath("//div[@class='cart_list']"));

        List<WebElement> listItems = cartList.findElements(By.xpath(".//div[@class='cart_item']"));

        for(int i = 0; i < listItems.size(); i++) {
            WebElement name = listItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            if(name.getText().equals(productName)) {
                toReturn = true;
                break;
            }
        }
        return toReturn;
    }

    public void close() {
        driver.close();
        driver.quit();
    }

}
