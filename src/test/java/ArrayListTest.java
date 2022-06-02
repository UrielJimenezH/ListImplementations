import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayListTest extends ArrayList<String> {
    private List<String> list;
    private final String firstElement = "firstElement";
    private final String secondElement = "secondElement";
    private final String thirdElement = "thirdElement";
    private final String fourthElement = "fourthElement";
    private final String fifthElement = "fifthElement";
    private final String sixthElement = "sixthElement";

    @Before
    public void before() {
        list = new ArrayList<>();
        list.add(firstElement);
        list.add(secondElement);
        list.add(thirdElement);
        list.add(fourthElement);
    }

    @Test
    public void emptyConstructor_DoesNotThrowException_WhenCalled() {
        new ArrayList<>();
    }

    @Test
    public void oneParamConstructor_DoesNotThrowException_WhenValidValuePassedForInitialCapacityParam() {
        new ArrayList<>(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void oneParamConstructor_ThrowsException_WhenNegativeValuePassedForInitialCapacityParam() {
        new ArrayList<>(-10);
    }

    @Test
    public void oneParamConstructor_DoesNotThrowException_WhenValidListPassedForElementsParam() {
        var list = new ArrayList<String>();
        new ArrayList<>(list);
    }

    @Test(expected = NullPointerException.class)
    public void oneParamConstructor_ThrowsException_WhenNullPassedForElementsParam() {
        new ArrayList<>(null);
    }

    @Test
    public void add_IncrementsListSizeByOne_WhenValidObjectIsPassedAsElementParam() {
        int initialSize = list.size();
        list.add("Some");
        int currentListSize = list.size();
        assertEquals(1, currentListSize - initialSize);
    }

    @Test(expected = NullPointerException.class)
    public void add_ThrowsException_WhenNullObjectPassedAsElementParam() {
        list.add(null);
    }

    @Test
    public void add_InternalElementsArrayDoublesItsSize_WhenLoadThresholdIsSurpass() {
        var list = new ArrayList<>(10);
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(10, list.elements.length);
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        assertEquals(10, list.elements.length);
        list.add("8");
        assertEquals(20, list.elements.length);
        list.add("9");
        list.add("10");
        assertEquals(20, list.elements.length);
        list.add("11");
        list.add("12");
        list.add("13");
        list.add("14");
        list.add("15");
        assertEquals(20, list.elements.length);
        list.add("16");
        assertEquals(40, list.elements.length);
    }

    @Test(expected = NullPointerException.class)
    public void addAll_ThrowsException_WhenListPassedAsElementsParamIsNull() {
        list.addAll(null);
    }

    @Test
    public void addAll_LeavesListAsItWas_WhenEmptyListPassedAsElementsParam() {
        var newList = new ArrayList<String>();
        var initialListSize = list.size();
        list.addAll(newList);
        var currentListSize = list.size();
        assertEquals(0, currentListSize - initialListSize);
    }

    @Test
    public void addAll_IncrementsListSizeByParamListSize_WhenValidListPassedAsElementsParam() {
        var newList = new ArrayList<String>();
        newList.add("1");
        newList.add("2");
        newList.add("3");
        var initialListSize = list.size();
        list.addAll(newList);
        var currentListSize = list.size();
        assertEquals(newList.size(), currentListSize - initialListSize);
    }

    @Test(expected = NullPointerException.class)
    public void remove_ThrowsException_WhenNullValueIsPassedAsElementParam() {
        list.remove(null);
    }

    @Test
    public void remove_DecrementsListSizeByOne_ElementIsNotOnTheListAnymore_AndElementsWereMovedToTheLeft_WhenValidValueIsPassedAsElementParam() {
        var initialListSize = list.size();
        list.remove(secondElement);
        var currentListSize = list.size();
        assertEquals(1, initialListSize - currentListSize);//Decrements list size by one
        assertFalse(list.contains(secondElement));//Element is not on the list anymore
        assertEquals(thirdElement, list.get(1));//Elements were moved
    }

    @Test
    public void remove_LeavesListAsItWas_WhenObjectPassedAsElementParamIsNotContainedOnList() {
        var initialListSize = list.size();
        list.remove(sixthElement);
        var currentListSize = list.size();
        assertEquals(0, initialListSize - currentListSize);
    }

    @Test(expected = NullPointerException.class)
    public void removeAll_ThrowsException_WhenListPassedAsElementsParamIsNull() {
        list.removeAll(null);
    }

    @Test
    public void removeAll_LeavesListAsItWas_WhenEmptyListPassedAsElementsParam() {
        var newList = new ArrayList<String>();
        var initialListSize = list.size();
        list.addAll(newList);
        var currentListSize = list.size();
        assertEquals(0, currentListSize - initialListSize);
    }

    @Test
    public void removeAll_DecrementsListSizeByParamListSize_WhenElementsFromListPassedAsElementsParamAreContained() {
        var newList = new ArrayList<String>();
        newList.add(firstElement);
        newList.add(secondElement);
        newList.add(thirdElement);
        var initialListSize = list.size();
        list.removeAll(newList);
        var currentListSize = list.size();
        assertEquals(newList.size(), initialListSize - currentListSize);
    }

    @Test
    public void removeAll_DecrementsListSizeOnlyByParamListExistingItemsOnElementsList_WhenValidListPassedAsElementsParam() {
        var newList = new ArrayList<String>();
        newList.add(firstElement);
        newList.add(fourthElement);
        newList.add(fifthElement);
        newList.add(sixthElement);
        var initialListSize = list.size();
        list.removeAll(newList);
        var currentListSize = list.size();
        assertEquals(2, initialListSize - currentListSize);
    }

    @Test
    public void removeAll_LeavesListAsItWas_WhenNonOfTheElementsOnTheListPassedAsParamExistsOnElementsList() {
        var newList = new ArrayList<String>();
        newList.add(fifthElement);
        newList.add(sixthElement);
        var initialListSize = list.size();
        list.removeAll(newList);
        var currentListSize = list.size();
        assertEquals(0, currentListSize - initialListSize);
    }

    @Test(expected = NullPointerException.class)
    public void contains_ThrowsException_WhenElementPassedAsParamIsNull() {
        list.contains(null);
    }

    @Test
    public void contains_ReturnsTrue_WhenElementPassedAsParamIsContainedOnList() {
        assertTrue(list.contains(firstElement));
        assertTrue(list.contains(secondElement));
    }

    @Test
    public void contains_ReturnsFalse_WhenElementPassedAsParamIsNotContainedOnList() {
        assertFalse(list.contains(fifthElement));
        assertFalse(list.contains(sixthElement));
    }

    @Test
    public void size_DoesNotThrowException_WhenCalled() {
        list.size();
    }

    @Test
    public void isEmpty_ReturnsTrue_WhenListIsEmpty() {
        List<String> list = new ArrayList<>();
        assertTrue(list.isEmpty());
    }

    @Test
    public void isEmpty_ReturnsFalse_WhenListIsNotEmpty() {
        assertFalse(list.isEmpty());
    }
}
