import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

     /** This tests addFirst in a short number list */
     @Test
     void addFirstTest() {
        Deque61B<Integer> alist = new ArrayDeque61B<>();

        alist.addFirst(3);
        alist.addFirst(4);
        alist.addFirst(5);

        assertThat(alist.toList()).containsExactly(5, 4, 3).inOrder();
     }

    /** This tests addFirst in a longer number list */
    @Test
    void addFirstLongerTest() {
        Deque61B<Integer> alist = new ArrayDeque61B<>();

        alist.addFirst(3);
        alist.addFirst(4);
        alist.addFirst(5);
        alist.addFirst(6);
        alist.addFirst(7);
        alist.addFirst(8);
        alist.addFirst(9);
        alist.addFirst(10);
        alist.addFirst(11);
        alist.addFirst(12);

        assertThat(alist.toList()).containsExactly(12, 11, 10, 9, 8, 7, 6, 5, 4, 3).inOrder();
    }

    /** This tests addFirst and addLast and get in a longer number list */
    @Test
    void addFirstAddLastGetTest() {
        Deque61B<Integer> alist = new ArrayDeque61B<>();

        alist.addFirst(3);
        alist.addLast(4);
        alist.addFirst(5);

        assertThat(alist.toList()).containsExactly(5, 3, 4).inOrder();
        assertThat(alist.get(0)).isEqualTo(5);
        assertThat(alist.get(1)).isEqualTo(3);
        assertThat(alist.get(2)).isEqualTo(4);
    }

    /** This tests addFirst and addLast and get and size and isEmpty in a longer number list */
    @Test
    void addFirstAddLastSizeIsEmptyLongerTest() {
        Deque61B<Integer> alist = new ArrayDeque61B<>();

        assertThat(alist.isEmpty()).isTrue();
        assertThat(alist.size()).isEqualTo(0);

        alist.addFirst(3);
        alist.addLast(4);
        alist.addFirst(5);
        alist.addLast(6);
        alist.addFirst(7);
        alist.addLast(8);
        alist.addFirst(9);
        alist.addLast(10);
        alist.addFirst(11);
        alist.addLast(12);  // [11, 9, 7, 5, 3, 4, 6, 8, 10, 12]

        assertThat(alist.toList()).containsExactly(11, 9, 7, 5, 3, 4, 6, 8, 10, 12).inOrder();
        assertThat(alist.size()).isEqualTo(10);
        assertThat(alist.get(2)).isEqualTo(7);
        assertThat(alist.get(8)).isEqualTo(10);
        assertThat(alist.get(9)).isEqualTo(12);
        assertThat(alist.get(10)).isNull();
    }

     @Test
    void removeFirstRemoveLastShortTest() {
        Deque61B<Integer> alist = new ArrayDeque61B<>();

        alist.addFirst(3);
        alist.addLast(4);
        alist.addFirst(5);

        assertThat(alist.toList()).containsExactly(5, 3, 4).inOrder();

        alist.removeFirst();
        alist.removeLast();

         assertThat(alist.toList()).containsExactly(3).inOrder();
         assertThat(alist.removeLast()).isEqualTo(3);
         assertThat(alist.removeLast()).isNull();
         assertThat(alist.isEmpty()).isTrue();

    }

    @Test
    void removeFirstRemoveLastLongerTest() {
        Deque61B<Integer> alist = new ArrayDeque61B<>();

        assertThat(alist.isEmpty()).isTrue();
        assertThat(alist.size()).isEqualTo(0);

        alist.addFirst(3);
        alist.addLast(4);
        alist.addFirst(5);
        alist.addLast(6);
        alist.addFirst(7);
        alist.addLast(8);
        alist.addFirst(9);
        alist.addLast(10);
        alist.addFirst(11);
        alist.addLast(12);  // [11, 9, 7, 5, 3, 4, 6, 8, 10, 12]

        assertThat(alist.toList()).containsExactly(11, 9, 7, 5, 3, 4, 6, 8, 10, 12).inOrder();

        alist.removeLast();
        alist.removeLast();
        alist.removeFirst();
        alist.removeLast();
        alist.removeLast();

        assertThat(alist.toList()).containsExactly(9, 7, 5, 3, 4).inOrder();

        alist.removeFirst();
        alist.removeFirst();
        alist.removeFirst();
        alist.removeLast();
        alist.removeFirst();

        assertThat(alist.removeLast()).isNull();
        assertThat(alist.size()).isEqualTo(0);
    }

    @Test
    void removeFirstRemoveLastEvenLongerTest() {
        Deque61B<Integer> alist = new ArrayDeque61B<>();

        assertThat(alist.isEmpty()).isTrue();
        assertThat(alist.size()).isEqualTo(0);

        alist.addFirst(3);
        alist.addLast(4);
        alist.addFirst(5);
        alist.addLast(6);
        alist.addFirst(7);
        alist.addLast(8);
        alist.addFirst(9);
        alist.addLast(10);
        alist.addFirst(11);
        alist.addLast(12);  // [11, 9, 7, 5, 3, 4, 6, 8, 10, 12]
        alist.addLast(13);  // [11, 9, 7, 5, 3, 4, 6, 8, 10, 12]
        alist.addLast(14);  // [11, 9, 7, 5, 3, 4, 6, 8, 10, 12]
        alist.addLast(15);  // [11, 9, 7, 5, 3, 4, 6, 8, 10, 12]
        alist.addLast(16);  // [11, 9, 7, 5, 3, 4, 6, 8, 10, 12]
        alist.addLast(17);  // [11, 9, 7, 5, 3, 4, 6, 8, 10, 12]
        alist.addLast(18);  // [11, 9, 7, 5, 3, 4, 6, 8, 10, 12]
        alist.addLast(19);  // [11, 9, 7, 5, 3, 4, 6, 8, 10, 12]
        alist.addLast(20);  // [11, 9, 7, 5, 3, 4, 6, 8, 10, 12]
        alist.addLast(21);  // [11, 9, 7, 5, 3, 4, 6, 8, 10, 12]
        alist.addLast(22);  // [11, 9, 7, 5, 3, 4, 6, 8, 10, 12]
        alist.addLast(23);  // [11, 9, 7, 5, 3, 4, 6, 8, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23]

        for(int i = 0; i < 21; i++) {
            alist.removeLast();
        }

        assertThat(alist.removeLast()).isNull();
        assertThat(alist.size()).isEqualTo(0);
    }

}
