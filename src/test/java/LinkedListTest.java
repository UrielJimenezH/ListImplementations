import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedListTest {
    private List<String> list;
    private final String firstElement = "firstElement";
    private final String secondElement = "secondElement";
    private final String thirdElement = "thirdElement";
    private final String fourthElement = "fourthElement";
    private final String fifthElement = "fifthElement";
    private final String sixthElement = "sixthElement";
    private final String seventhElement = "seventhElement";

    @Before
    public void before() {
        list = new LinkedList<>();
        list.add(firstElement);
        list.add(secondElement);
        list.add(thirdElement);
        list.add(fourthElement);
        list.add(fifthElement);
    }

    @After
    public void after() {
        list = null;
    }

    @Test(expected = NullPointerException.class)
    public void constructorOneListParam_ThrowsException_WhenNullIsPassedForListParam() {
        new LinkedList<>(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void constructorOneListParam_CreatesEmptyList_WhenEmptyListPassedForListParam() {
        List<String> list = new LinkedList<>(new LinkedList<>());
        list.get(0);
    }

    @Test
    public void constructorOneListParam_CreatesListWithValuesPassed_WhenValidListPassedForListParam() {
        List<String> list2 = new LinkedList<>(list);
        assertEquals(list.get(0), list2.get(0));
        assertEquals(list.get(1), list2.get(1));
        assertEquals(list.get(2), list2.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_ThrowsException_WhenOutOfBoundsIndexPassed() {
        list.get(6);
        list.get(-6);
    }

    @Test
    public void get_ReturnsProperValue_WhenCalled() {
        assertEquals(firstElement, list.get(0));
        assertEquals(secondElement, list.get(1));
        assertEquals(thirdElement, list.get(2));
    }

    @Test(expected = NullPointerException.class)
    public void add_ThrowsException_WhenNullValuePassedForElementParam() {
        list.add(null);
    }

    @Test
    public void add_AddsElement_WhenListIsEmpty() {
        LinkedList<String> list = new LinkedList<>();
        list.add("1");
        assertNotEquals(list.head, null);
        assertEquals(list.head, list.tail);
        assertEquals(list.tailIndex, 0);
    }

    @Test
    public void add_AddsElement_WhenListAlreadyHasElements() {
        LinkedList<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        assertNotEquals(list.head, null);
        assertNotEquals(list.head, list.tail);
        assertEquals(list.tailIndex, 3);
        assertEquals(list.get(2), "3");
    }

    @Test(expected = NullPointerException.class)
    public void addAll_ThrowsException_WhenNullValuePassedForElementsParam() {
        list.addAll(null);
    }

    @Test
    public void addAll_AddsElements_WhenListOnLinkedListClassIsEmpty() {
        LinkedList<String> list = new LinkedList<>();
        list.addAll(this.list);

        assertEquals(list.get(0), firstElement);
        assertEquals(list.get(1), secondElement);
        assertNotEquals(list.head, null);
        assertEquals(list.tailIndex, this.list.size() - 1);
        if (this.list.size() <= 1)
            assertEquals(list.head, list.tail);
        else
            assertNotEquals(list.head, list.tail);
    }

    @Test
    public void addAll_AddsElements_WhenListOnLinkedListClassAlreadyHasElements() {
        LinkedList<String> list2 = new LinkedList<>();
        list2.add(sixthElement);
        list2.add(seventhElement);

        LinkedList<String> list = (LinkedList<String>) this.list;
        int initialSize = list.size();
        list.addAll(list2);

        assertEquals(list2.size() + initialSize, list.size());
    }

    @Test(expected = NullPointerException.class)
    public void remove_ThrowsException_WhenNullValuePassedForElementParam() {
        list.remove(null);
    }

    @Test
    public void remove_ListSizeReducedByOne_WhenValuePassedForElementParamIsHeadElement() {
        int initialSize = list.size();
        assertTrue(list.remove(firstElement));

        assertNotEquals(firstElement, list.get(0));
        assertEquals(1, initialSize - list.size());
        assertFalse(list.contains(firstElement));//Could fail if firstElement was added to the list more than once
    }

    @Test
    public void remove_ListSizeReducedByOne_WhenValuePassedForElementParamIsHeadAndTailElement() {
        LinkedList<String> list = new LinkedList<>();
        list.add(firstElement);

        assertTrue(list.remove(firstElement));
        assertNull(list.head);
        assertNull(list.tail);
        assertEquals(0, list.size());
        assertFalse(list.contains(firstElement));//Could fail if firstElement was added to the list more than once
    }

    @Test
    public void remove_ListIsLeaveAsItWas_WhenElementPassedAsParamDoesNotExistOnList() {
        int initialSize = list.size();
        assertFalse(list.remove(sixthElement));
        int currentSize = list.size();
        assertEquals(0, currentSize - initialSize);
    }

    @Test
    public void remove_ListSizeReducedByOne_WhenValueOtherThanHeadIsPassedForElementParam() {
        int initialSize = list.size();
        assertTrue(list.remove(thirdElement));
        int currentSize = list.size();
        assertEquals(1, initialSize - currentSize);
        assertFalse(list.contains(thirdElement));
    }

    @Test
    public void remove_ListSizeReducedByOne_WhenValuePassedForElementParamIsTail() {
        int initialSize = list.size();
        assertTrue(list.remove(fifthElement));
        int currentSize = list.size();
        assertEquals(1, initialSize - currentSize);
        assertFalse(list.contains(fifthElement));
        assertNotEquals(fifthElement, list.get(list.size() - 1));
    }



    @Test(expected = NullPointerException.class)
    public void removeAll_ThrowsException_WhenNullValuePassedForElementsParam() {
        list.removeAll(null);
    }

    @Test
    public void removeAll_LeavesListAsItWas_WhenEmptyListPassedForElementsParam() {
        int initialSize = list.size();
        assertFalse(list.removeAll(new LinkedList<>()));
        int currentSize = list.size();
        assertEquals(0, initialSize - currentSize);
    }

    @Test
    public void removeAll_LeavesListAsItWas_WhenNoneOfTheElementsOnTheListPassedForElementsParamExists() {
        LinkedList<String> list = new LinkedList<>();
        list.add(sixthElement);
        list.add(seventhElement);

        int initialSize = this.list.size();
        assertFalse(this.list.removeAll(list));
        int currentSize = this.list.size();
        assertEquals(0, initialSize - currentSize);
    }

    @Test
    public void removeAll_ReducesListSizeByTheNumberOfElementsFound_WhenSomeOfTheElementsPassedPassedForElementsParamExists() {
        LinkedList<String> list = new LinkedList<>();
        list.add(secondElement);
        list.add(fourthElement);
        list.add(sixthElement);
        list.add(seventhElement);

        int initialSize = this.list.size();
        assertTrue(this.list.removeAll(list));
        int currentSize = this.list.size();
        assertEquals(2, initialSize - currentSize);
    }

    @Test
    public void removeAll_ReducesListSizeByTheSizeOfListPassedAsParam_WhenAllOfTheElementsExistsOnList() {
        LinkedList<String> list = new LinkedList<>();
        list.add(firstElement);
        list.add(secondElement);
        list.add(fourthElement);
        list.add(fifthElement);

        int initialSize = this.list.size();
        assertTrue(this.list.removeAll(list));
        int currentSize = this.list.size();
        assertEquals(list.size(), initialSize - currentSize);
    }


    @Test(expected = NullPointerException.class)
    public void contains_ThrowsException_WhenNullValuePassedForElementParam() {
        list.contains(null);
    }

    @Test
    public void contains_ReturnsFalse_WhenListIsEmpty() {
        LinkedList<String> list = new LinkedList<>();
        assertFalse(list.contains(thirdElement));
    }

    @Test
    public void contains_ReturnsTrue_WhenValuePassedForElementParamExistsOnList() {
        assertTrue(list.contains(thirdElement));
    }

    @Test
    public void contains_ReturnsFalse_WhenValuePassedForElementParamExistsOnList() {
        assertFalse(list.contains(sixthElement));
    }

    @Test
    public void size_ReturnsCorrectSize_WhenCalled() {
        assertEquals(5, list.size());
    }

    @Test
    public void size_ReturnsTrue_WhenListIsEmpty() {
        assertFalse(list.isEmpty());
    }

    @Test
    public void size_ReturnsFalse_WhenListIsNotEmpty() {
        LinkedList<String> list = new LinkedList<>();
        assertTrue(list.isEmpty());
    }
}
