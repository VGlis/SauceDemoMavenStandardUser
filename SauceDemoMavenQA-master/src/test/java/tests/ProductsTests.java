package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import provider.ProductsProvider;

import java.util.ArrayList;
import java.util.List;

public class ProductsTests {

    public static List<String> allItems() {
        List<String> toReturn  = new ArrayList<>();

        toReturn.add("Sauce Labs Bike Light");
        toReturn.add("Sauce Labs Onesie");
        toReturn.add("Sauce Labs Fleece Jacket");
        toReturn.add("Sauce Labs Backpack");
        toReturn.add("Sauce Labs Bolt T-Shirt");
        toReturn.add("Test.allTheThings() T-Shirt (Red)");

        return toReturn;
    }

    public static List<String> twoItems() {
        List<String> toReturn  = new ArrayList<>();

        toReturn.add("Sauce Labs Bike Light");
        toReturn.add("Test.allTheThings() T-Shirt (Red)");

        return toReturn;
    }

    @Test
    public void verifyAddAllProductToCart() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);
        //productsPage.addProductToCartByName("Sauce Labs Bike Light"); //Sauce Labs Onesie
        //productsPage.addProductToCartByName("Sauce Labs Onesie");
        List<String> productToAdd = allItems();
        for(int i = 0; i < productToAdd.size(); i++) {
            productsPage.addProductToCartByName(productToAdd.get(i));
        }

        Assert.assertEquals(productsPage.productCountInCart(), productToAdd.size(), "Product count is not equals");

        productsPage.close();
    }

    @Test
    public void verifyAddTwoProductToCart() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);
        //productsPage.addProductToCartByName("Sauce Labs Bike Light"); //Sauce Labs Onesie
        //productsPage.addProductToCartByName("Sauce Labs Onesie");
        List<String> productToAdd = twoItems();
        for(int i = 0; i < productToAdd.size(); i++) {
            productsPage.addProductToCartByName(productToAdd.get(i));
        }

        Assert.assertEquals(productsPage.productCountInCart(), productToAdd.size(), "Product count is not equals");

        productsPage.close();
    }

    @Test(dataProvider = "ProductProvider", dataProviderClass = ProductsProvider.class)
    public void verifyAddProductToCart(String productName) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);
        Integer productCountBeforeAdd = productsPage.productCountInCart();
        productsPage.addProductToCartByName(productName);

        Assert.assertEquals(productsPage.productCountInCart(), productCountBeforeAdd + 1, "Product count is not equals");

        productsPage.close();
    }

    @Test
    public void verifyAddProductToCartOnCartPage() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductToCartByName("Sauce Labs Backpack");
        productsPage.openCart();

        CartPage cartPage = new CartPage(driver);
        boolean isProductAdded = cartPage.isProductInCart("Sauce Labs Backpack");

        Assert.assertEquals(isProductAdded, true, "Product with name Sauce Labs Backpack is not added to cart");

        cartPage.close();

    }


    @Test
    public void verifySortProductByPrice() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortByValue("Price (high to low)");
        productsPage.printProductPrice();
        boolean isSorted = productsPage.isProductSortFromHighToLowByPrice();

        if(isSorted) {
            System.out.println("Sorting is as expected");
        }
        else {
            System.out.println("Sorting is not as expected");
        }

        Assert.assertEquals(isSorted, true, "Products are not sorted as expected, from high to low by price");

        productsPage.close();

    }

    @Test
    public void verifySortProductByNameAZ() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortByName( "Name (A to Z)");
        productsPage.printProductName();
        boolean isSortedByNameAZ = productsPage.isProductSortFromAZ();

        Assert.assertEquals(isSortedByNameAZ, true, "Products are not sorted as expected, from Name (A to Z)");

        productsPage.close();
    }

    @Test
    public void verifySortProductByNameZA() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortByName( "Name (Z to A)");
        productsPage.printProductName();
        boolean isProductSortFromZA = productsPage.isProductSortFromZA();

        Assert.assertEquals(isProductSortFromZA, true, "Products are not sorted as expected, from Name (A to Z)");

        productsPage.close();
    }

    @Test
    public void verifyTotalPriceProduct() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductToCartByName("Sauce Labs Backpack");
        productsPage.addProductToCartByName("Sauce Labs Bike Light");
        productsPage.addProductToCartByName("Sauce Labs Fleece Jacket");
        productsPage.addProductToCartByName("Sauce Labs Onesie");
        productsPage.addProductToCartByName("Sauce Labs Bolt T-Shirt");
        productsPage.addProductToCartByName("Test.allTheThings() T-Shirt (Red)");

        productsPage.openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        cartPage.setFirstName("Vladan");
        cartPage.setLastName("Glisovic");
        cartPage.setZip("11000");
        cartPage.clickContinue();

        System.out.println("Number of items in Cart:             " + productsPage.productCountInCart());
        System.out.println("**************************************************************************");
        System.out.println("Total sum count in CartPage by APP: $" + cartPage.getTotalCount());
        System.out.println("Total sum count in CartPage by QA:  $" + cartPage.sumTotalCount());

        Assert.assertEquals( cartPage.getTotalCount(), cartPage.sumTotalCount(), "Product count is not equals");

        cartPage.close();
    }
    @Test
    public void verifyProductDescription() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        loginPage.clickProductLinkTextSauceLabsBackpack();

        ProductsPage productsPage = new ProductsPage(driver);

        String NameProductInProductText = productsPage.getNameProductInProduct();
        String DescriptionProductInProductText = productsPage.getDescriptionProductInProduct();
        Double getPriceProductInProduct = productsPage.getPriceProductInProduct();

        System.out.println("Name of Product in Single Item:         " + NameProductInProductText);
        System.out.println("Description of Product in Single Item:  " + DescriptionProductInProductText);
        System.out.println("Price of Product in Single Item:        $" + getPriceProductInProduct);

        productsPage.clickAddCartFromSingleProduct();
        productsPage.openCart();

        CartPage cartPage = new CartPage(driver);

        System.out.println("***************************************************************************************");

        String NameProductInCartText = cartPage.getNameProductInCart();
        String DescriptionProductInCartText = cartPage.getDescriptionProductInCart();
        Double getPriceProductInCart = cartPage.getPriceProductInCart();

        System.out.println("Name of single Product in Cart Page:         " + NameProductInProductText);
        System.out.println("Description of single Product in Cart Page:  " + DescriptionProductInProductText);
        System.out.println("Price of single Product in Cart Page:        $" + getPriceProductInCart);


        Assert.assertEquals(NameProductInCartText, NameProductInProductText, "Product Name is not equals");
        Assert.assertEquals(DescriptionProductInProductText, DescriptionProductInCartText, "Product Description is not equals");
        Assert.assertEquals(getPriceProductInProduct, getPriceProductInCart, "Product Price is not equals");


        cartPage.close();
    }

    @Test
    public void verifyFinishOrder() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductToCartByName("Sauce Labs Backpack");
        productsPage.addProductToCartByName("Sauce Labs Bike Light");
        productsPage.addProductToCartByName("Sauce Labs Fleece Jacket");
        productsPage.addProductToCartByName("Sauce Labs Onesie");
        productsPage.addProductToCartByName("Sauce Labs Bolt T-Shirt");
        productsPage.addProductToCartByName("Test.allTheThings() T-Shirt (Red)");

        productsPage.openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        cartPage.setFirstName("Vladan");
        cartPage.setLastName("Glisovic");
        cartPage.setZip("11000");
        cartPage.clickContinue();
        cartPage.clickFinish();

        System.out.println("Finish message:        " + cartPage.finishCart());

        Assert.assertEquals( cartPage.finishCart(), "THANK YOU FOR YOUR ORDER", "Finish message is not equals");

        cartPage.close();
    }

}
