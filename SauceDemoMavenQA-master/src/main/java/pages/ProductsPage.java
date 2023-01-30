package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class ProductsPage {

    public WebDriver driver;

    public ProductsPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public boolean isOpen() {
        String url = driver.getCurrentUrl();

        if(url.equals("https://www.saucedemo.com/inventory.html")) {
            return true;
        }
        else {
            return false;
        }

    }

    public void close() {
        driver.close();
        driver.quit();
    }

    public void addProductToCartByName(String productName) {
        WebElement container = driver.findElement(By.id("inventory_container"));

        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));

        for(int i = 0; i < listInventoryItems.size(); i++) {
            WebElement itemName = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            String itemNameText = itemName.getText();

            if(itemNameText.equals(productName)) {
                WebElement addButton = listInventoryItems.get(i).findElement(By.xpath(".//button"));
                addButton.click();
                break;
            }
        }
    }

    public void printProductPrice() {
        WebElement container = driver.findElement(By.id("inventory_container"));

        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));

        for(int i = 0; i < listInventoryItems.size(); i++) {
            WebElement itemPrice = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_price']"));
            System.out.println(itemPrice.getText());
        }
    }

    public Integer productCountInCart() {
        //WebElement cartNumber = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
        //return Integer.parseInt(cartNumber.getText());

        List<WebElement> listCartBadge = driver.findElements(By.xpath("//span[@class='shopping_cart_badge']"));
        if(listCartBadge.size() == 0) {
            return 0;
        }
        else {
            return Integer.parseInt(listCartBadge.get(0).getText());
        }
    }

    public void sortByValue(String sortValue) {
        WebElement sortContainer = driver.findElement(By.xpath("//select[@data-test='product_sort_container']"));

        sortContainer.click();

        List<WebElement> options = sortContainer.findElements(By.xpath(".//option"));

        for(int i = 0; i < options.size(); i++) {
            String optionText = options.get(i).getText();
            if(optionText.equals(sortValue)) {
                options.get(i).click();
                break;
            }
        }
    }

    public boolean isProductSortFromHighToLowByPrice() {
        boolean toReturn = true;

        WebElement container = driver.findElement(By.id("inventory_container"));

        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));

        for(int i = 0; i < listInventoryItems.size() - 1; i++) {
            WebElement itemPriceFirst = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_price']"));
            String itemPriceFirstText = itemPriceFirst.getText();
            Double itemPriceFirstNumber = Double.parseDouble(itemPriceFirstText.substring(1));
            int j = i + 1;
            WebElement itemPriceSecond = listInventoryItems.get(j).findElement(By.xpath(".//div[@class='inventory_item_price']"));
            String itemPriceSecondText = itemPriceSecond.getText();
            Double itemPriceSecondNumber = Double.parseDouble(itemPriceSecondText.substring(1));
            if(itemPriceFirstNumber < itemPriceSecondNumber) {
                toReturn = false;
                break;
            }
        }

        return toReturn;
    }

    public WebElement getCart() {
        return driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
    }

    public void openCart() {
        //driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        getCart().click();
    }

    public Integer getProductCount() {
        WebElement cart = getCart();
        return Integer.parseInt(cart.findElement(By.xpath(".//span")).getText());
    }


}
