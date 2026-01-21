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
        assertThat(alist.get(1)).isEqualTo(5);
        assertThat(alist.get(2)).isEqualTo(3);
        assertThat(alist.get(3)).isEqualTo(4);
    }

}
