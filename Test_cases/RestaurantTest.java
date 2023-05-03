import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void mockingRestaurant(){
        restaurant = Mockito.spy(Restaurant.class);
        restaurant.setOpeningTime(LocalTime.parse("10:00:00"));
        restaurant.setClosingTime(LocalTime.parse("22:00:00"));
        restaurant.addToMenu("Veg Burger",110);
        restaurant.addToMenu("Chicken Burger", 1);
        restaurant.addToMenu("Cheese sandwich", 220);
        restaurant.addToMenu("Margherita Pizza", 230);

    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        LocalTime tempGetTime = LocalTime.parse("10:30:00");
        Mockito.when(restaurant.getCurrentTime()).thenReturn(tempGetTime);
        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        LocalTime tempGetTime = LocalTime.parse("07:30:00");
        Mockito.when(restaurant.getCurrentTime()).thenReturn(tempGetTime);
        assertFalse(restaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sundae",300);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Margherita Pizza");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("Soup"));
    }

    @Test
    public void selecting_items_in_menu_should_return_total_cost() {
        int testTotal = 0;
        List<String> mySelection = new ArrayList<>();
        mySelection.add("Cheese sandwich");
        mySelection.add("Margherita Pizza");

        assertEquals(450, restaurant.calculateOrderTotal(mySelection));


    }

    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
